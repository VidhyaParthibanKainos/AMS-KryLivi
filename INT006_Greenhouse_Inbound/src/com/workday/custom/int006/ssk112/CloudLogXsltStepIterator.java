package com.workday.custom.int006.ssk112;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An iterator over messages produced by an XSLT+ step
 * @author doug.lee
 * @author john.smail
 *
 */
public class CloudLogXsltStepIterator implements Iterator<CloudLogAdapter> {
	
	/**
	 * The minimum severity level that will be output.  By default all information will be output
	 */
	private CloudLogLevel minLevel;
	private Iterator<String> messageIterator;
	private CloudLogAdapter next;

	private CloudLogXsltStepIterator(List<String> messages, CloudLogLevel minLevel) {
		this.minLevel = minLevel;
		//
		// Get an iterator over all messages
		//
		messageIterator = messages.iterator();
		//
		// Find the first message which meets our minimum severity criteria
		//
		moveToNext();
	}
	
	/**
	 * 
	 * @param messages Note that we are forced to specify the raw type here as the MVEL use of reflection is unable to find the method if
	 * we use a generic
	 * @param min_level The minimum level for which messages will be output.  Must be one of "debug", "info", "warn", "error", "critical"
	 * @return
	 * @throws IllegalArgumentException If an invalid minimum level is specified
	 */
	@SuppressWarnings("unchecked")
	public static Iterator<CloudLogAdapter> getIterator(@SuppressWarnings("rawtypes") List messages, String minLevel) throws IllegalArgumentException {
		return new CloudLogXsltStepIterator((List<String>)messages, CloudLogLevel.parse(minLevel));
	}
	
	/**
	 * 
	 * @param messages Note that we are forced to specify the raw type here as the MVEL use of reflection is unable to find the method if
	 * we use a generic
	 * @return
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public static Iterator<CloudLogAdapter> getIterator(@SuppressWarnings("rawtypes") List messages) throws IllegalArgumentException {
		return new CloudLogXsltStepIterator((List<String>)messages, CloudLogLevel.debug);
	}
	
	private void moveToNext() {
		do {
			try {
				next = messageIterator.hasNext() ? CloudLogEntry.getCloudLogEntry(messageIterator.next()) : null;
			} catch (Throwable t) {
				throw new RuntimeException(t.getMessage(), t);
			}
		} while ((next != null) && (next.getLevel().compareTo(minLevel) < 0));
	}
	
	//
	// Interface Iterator<CloudLogAdaptor>
	//

	@Override
	public boolean hasNext() {
		return next != null;
	}

	/**
	 * Obtain the a CloudLogAdapter instance wrapping the next message
	 * 
	 * @throws NoSuchElementException if no messages are available
	 */
	
	@Override
	public CloudLogAdapter next() {
		if (next == null) {
			throw new NoSuchElementException("No more XSLT messages are available");
		}
		
		CloudLogAdapter nextValue = next;		
		moveToNext();		
		return nextValue;
	}
}
