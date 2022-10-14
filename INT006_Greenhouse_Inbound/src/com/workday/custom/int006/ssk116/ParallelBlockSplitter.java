package com.workday.custom.int006.ssk116;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import com.capeclear.assembly.impl.AssemblyUtils;
import com.capeclear.assembly.model.SplitterStrategyType;
import com.capeclear.capeconnect.attachments.io.FileBackedManagedData;
import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.MediationContextSplitter;
import com.capeclear.mediation.MediationMessage;
import com.capeclear.mediation.MediationMessageSplitter;
import com.capeclear.mediation.impl.DataHandlerSource;
import com.capeclear.mediation.impl.cc.MediationTube;
import com.capeclear.xml.utils.XmlUtils;

/**
 * An example splitter which illustrates how to produce a custom splitter which can be used either
 * as a 'classic' custom splitter or as a parallel splitter invoked from wcc://ParallelSubroutine.
 * 
 *  This relatively simple example splits an XML document into blocks of second level elements with a
 *  configurable block size.  For example, the document <root><row/><row/><row/><row/><row/></root> when
 *  run with a block size of 2 would result in three split messages, two with <root><row/><row/></root>
 *  and one with <root><row/></root>
 * 
 * @author Doug Lee
 * @author john.smail
 */

public class ParallelBlockSplitter implements MediationContextSplitter, MediationMessageSplitter  {
	Logger	log = LogControl.getLogger(getClass());
	
	/**
	 * The number of second level elements which will be included in each split message.  This is configurable
	 * from a spring bean held in the assembly.  An alternative, or additional, approach would be to configure
	 * the name of a property which would contain the block size so that the property value could be set from
	 * an integration attribute and then be dynamically picked up by the bean when handling the split message.
	 */
	int	blockSize;
	
	/**
	 * Determines whether the bean is operating in parallel or single-threaded mode.  The default is
	 * parallel mode
	 */
	boolean classicSplitter = false;
	
	/**
	 * The hasMore flag is used to indicate whether any more records exist to be split
	 */
	boolean hasMore = true;

	/**
	 * The reader with which we're going to iterate through the input file
	 */
	XMLEventReader reader;

	/**
	 * The root element for our output documents
	 */
	StartElement root;

	/**
	 * The end element for our output documents 
	 */
	EndElement endRoot;
	
	/**
	 * The source message
	 */
	MediationMessage sourceMessage;
		
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Interface MediationContextSplitter
	 * 
	 * 		This interface must be implemented by any custom splitter.
	 **************************************************************************************************************
	 **************************************************************************************************************/
	/**
     * Sets the Mediation Context containing the <code>MediationMessage</code> to be split.
     */
	@Override
	public void setMediationContext(MediationContext context) {	}
	
	@Override
	public boolean hasMore() {
		return hasMore;
	}
	
	/**
	 * Handle the message in a parallel child context
	 */
	@Override
	public boolean splitIntoContext(MediationContext childContext) throws Exception {

		MediationMessage message = getNextBlockMessage(childContext);

		if (message != null) {
			childContext.setMessage(message);
		}

		return classicSplitter ? (message != null) : hasMore;
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * MediationMessageSplitter
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	public String getFooterForReport() throws Exception {
		// Not used for parallel splitting
		return null;
	}

	@Override
	public String getHeaderForReport() throws Exception {
		// Not used for parallel splitting
		return null;
	}

    /**
     * Obtain the next MediationMessage resulting from splitting the input message.  A null MediationMessage is
     * returned in order to indicate that no further messages are available from the source.
     * 
     * This method is not used when operating as a parallel splitter
     * 
     * @return the next result of the splitting as a <code>MediationMessage</code>, 
     * or null if there are no more messages.  
     */
	public MediationMessage getNextMessage() throws Exception {
		return getNextBlockMessage(MediationTube.getCurrentMediationContext());
	}

	@Override
	public void setConfig(SplitterStrategyType strategy) {
		// Not used for parallel splitting
	}

	/**
	 * Obtain the message to be split
	 * @param message
	 * @throws Exception
	 */	
	@Override
	public void setMediationMessage(MediationMessage message) throws Exception {
		if (blockSize <= 0) {
			throw new RuntimeException("Splitter block size must be positive and nonzero");
		}
		
		@SuppressWarnings("rawtypes")
		Class allowedSources[] = new Class[] {StreamSource.class};

		@SuppressWarnings("unchecked")
		StreamSource source = (StreamSource)message.getRootPart(allowedSources);
		
		reader = XmlUtils.getXMLInputFactory().createXMLEventReader(source);
		
		// Find the root element.  We need to store this since we will be using it as
		// the root element of each of the output split messages
		root = null;
		XMLEvent event = null;
		
		do {
			event = reader.nextEvent();
		} while (!(event.isStartElement() || event.isEndDocument()));
		

		if (event.isStartElement()) {
			root = event.asStartElement();
			
			// Create an end element to match the root StartElement
			endRoot = XmlUtils.getXMLEventFactory().createEndElement(root.getName(), null);
		}

		this.sourceMessage = message;

		// We need to stop the input message being garbage collected whilst we iterate through it
		setLongLived(message, true);
		
		// Assume that we have something in the message until we prove otherwise
		hasMore = true;
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Utility methods
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	private MediationMessage getNextBlockMessage(MediationContext ctx) throws Exception {
		try {
			XMLEvent event = null;
			int count = 0;
			MediationMessage outputMessage = null;
	
			XMLEventWriter writer = null;
			FileBackedManagedData fbmd = null;
			
			if (reader != null) {
				try {
					do {
						event = reader.nextEvent();
						
						if (event.isStartElement()) {
							++count;
			
							// If this is the first time that we've had to write an event, then create a FileBackedManagedData into which we can write our events
							if (writer == null) {
								fbmd = getFileBackedManagedData(ctx);
								writer = XmlUtils.getXMLOutputFactory().createXMLEventWriter(fbmd.getOutputStream(), "utf-8");
								writer.add(root);
							}
							StartElement start = event.asStartElement();
							writer.add(start);
			
							// Now iterate through any other events until we come to the end element matching this start
							int depth = 0;
							do {
								event = reader.nextEvent();
								if (event.isStartElement()) {
									++depth;
								} else if (event.isEndElement()) {
									--depth;
								}
								writer.add(event);
							} while (depth >= 0);
						} else if (event.isEndElement()) {
							endInput();
							break;
						}
					} while ((count < blockSize) && !event.isEndDocument());

					// if we are not at the end of the document then read ahead until we encounter either another StartElement or EndElement.  A StartElement indicates
					// that we've got at least one more page of data to process, an EndElement means that we have reached the end of our data to be split
					if (reader != null) {
						do {
							event = reader.peek();
							if (event.isEndElement() || event.isStartElement()) {
								break;
							}
							event = reader.nextEvent();
						} while (true);
	
						if (event.isEndElement()) {
							endInput();
						}
					}

				} catch(XMLStreamException xse) {
					LogControl.getLogger(getClass()).error("Error reading XML at " + xse.getLocation().toString(), xse);
					throw xse;
				}
			}
	
			// If we've written any events to the output write the end element and complete the output
			if (writer != null) {
				writer.add(endRoot);
				writer.flush();
				writer.close();
	
				outputMessage = createMessage(fbmd);
			}
			
			return outputMessage;
		} catch (XMLStreamException xse) {
			getLog().error("Error reading XML at " + xse.getLocation().toString(), xse);
			throw xse;
		} catch (Exception e) {
			// Ensure that we've got a log message
			getLog().error("Error in block splitter", e);
			throw e;
		}
	}
	
	Logger getLog() {
		if (log == null) {
			log = LogControl.getLogger(getClass());
		}
		return log;
	}
	
	/**
	 * The endInput method when we've reached the end of the information to be read from the input document.  It's used
	 * to modify the hasMore flag to indicate that there is no more input and to release resources assigned to handling
	 * the input.
	 */
	void endInput() {
		try {
			reader.close();
		} catch (XMLStreamException xse) {
			getLog().error("Error closing reader", xse);
		}
		reader = null;
		hasMore = false;
		
		// We've reached the end of the input so we can now release the input message for garbage collection.
		// Strictly speaking, this is not necessary, but may free up resources earlier in a large assembly
		setLongLived(sourceMessage, false);
	}

	/**
	 * 
	 * @return A FilebackedManagedData DataHandler
	 */
	FileBackedManagedData getFileBackedManagedData(MediationContext ctx) {
		FileBackedManagedData fbmd = new FileBackedManagedData("text/xml");
		ctx.addDisposable(fbmd);
		return fbmd;
	}
	
	/**
	 * Create a MediationMessage to wrap the output to our FilebackedManagedData root part
	 * 
	 * @param fbmd
	 * @return
	 * @throws IOException
	 */
	MediationMessage createMessage(FileBackedManagedData fbmd) throws IOException {
		MediationMessage msg = AssemblyUtils.getMediationHelper().createMessage();
        msg.setRootPart(new DataHandlerSource(fbmd.getDataSource()), fbmd.getContentType());
        msg.setHeader("Content-Type", fbmd.getContentType());

        return msg;
	}

	/**
	 * If the specified message is stored with FileBackedManagedData then set this to be long lived in order to prevent
	 * garbage collection whilst we are still interested in using it. 
	 * 
	 * @param msg
	 * @param longLived
	 */

	private void setLongLived(MediationMessage msg, boolean longLived) {
		@SuppressWarnings("unchecked")
		DataHandlerSource dhs = (DataHandlerSource)msg.getMessage(new Class[] {DataHandlerSource.class});
		DataHandler dh = dhs.getDataHandler();
		
		FileBackedManagedData fbmd = FileBackedManagedData.getFBMDFromDataHandler(dh);
		if (fbmd != null) {
			fbmd.setLongLived(true);
		}
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Bean getters and setters
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	/**
	 * Obtains the numbers of records in an output message
	 * 
	 * @return
	 */
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 * Sets the number of records in an output message
	 * 
	 * @param blockSize
	 */
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
	/**
	 * Determines whether the bean is operating in parallel or single-threaded mode.  The default parallel
	 */
	public boolean isClassicSplitter() {
		return classicSplitter;
	}
	
	public void setClassicSplitter(boolean classicSplitter) {
		this.classicSplitter = classicSplitter;
	}
}
