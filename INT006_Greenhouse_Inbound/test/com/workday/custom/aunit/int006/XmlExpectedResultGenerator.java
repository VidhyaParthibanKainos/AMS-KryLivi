package com.workday.custom.aunit.int006;

import java.util.Map;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;

public class XmlExpectedResultGenerator {

	Logger log = LogControl.getLogger(XmlExpectedResultGenerator.class);

	public XmlExpectedResultGenerator() {
		super();
	}

	public void copyResourceWithTokenReplacement(Map<String, String> mapReplacementKeys, XMLStreamReader streamReader, XMLStreamWriter streamWriter) throws Exception {
		while (streamReader.hasNext()) {
			
			int readerEvent = streamReader.getEventType();
			switch (readerEvent) {
				case XMLStreamReader.START_ELEMENT:
					final String localName = streamReader.getLocalName();
					final String namespaceURI = streamReader.getNamespaceURI();
					
					if (namespaceURI != null && namespaceURI.length() > 0) {
						final String prefix = streamReader.getPrefix();
						if (prefix != null) {
							streamWriter.writeStartElement(prefix, localName, namespaceURI);
						} else {
							streamWriter.writeStartElement(namespaceURI, localName);
						}
					} else {
						streamWriter.writeStartElement(localName);
					}
					
					for (int i = 0, len = streamReader.getNamespaceCount(); i < len; i++) {
						streamWriter.writeNamespace(streamReader.getNamespacePrefix(i), streamReader.getNamespaceURI(i));
					}
					
					for (int i = 0, len = streamReader.getAttributeCount(); i < len; i++) {
						String attributeURI = streamReader.getAttributeNamespace(i);
						if (attributeURI != null) {
							streamWriter.writeAttribute(attributeURI, streamReader.getAttributeLocalName(i), streamReader.getAttributeValue(i));
						} else {
							streamWriter.writeAttribute(streamReader.getAttributeLocalName(i), streamReader.getAttributeValue(i));
						}
					}
					break;
					
			    case XMLStreamReader.END_ELEMENT:
			      streamWriter.writeEndElement();
			      break;
			      
			    case XMLStreamReader.SPACE:
			    	//fall-through
			    	
			    case XMLStreamReader.CHARACTERS:
					String sourceValue = streamReader.getText();
					
					if (mapReplacementKeys.containsKey(sourceValue)) {
						streamWriter.writeCharacters(mapReplacementKeys.get(sourceValue));
					} else {
						streamWriter.writeCharacters(sourceValue);
					}
					break;
					
			    case XMLStreamReader.PROCESSING_INSTRUCTION:
			    	streamWriter.writeProcessingInstruction(streamReader.getPITarget(), streamReader.getPIData());
			    	break;
			    	
			    case XMLStreamReader.CDATA:
			    	streamWriter.writeCData(streamReader.getText());
			    	break;

			    case XMLStreamReader.COMMENT:
			    	streamWriter.writeComment(streamReader.getText());
			    	break;
			    	
			    case XMLStreamReader.ENTITY_REFERENCE:
			    	streamWriter.writeEntityRef(streamReader.getLocalName());
			    	break;
			    	
			    case XMLStreamReader.START_DOCUMENT:
			    	String encoding = streamReader.getCharacterEncodingScheme();
			    	String version = streamReader.getVersion();

			    	if (encoding != null && version != null) {
			    		streamWriter.writeStartDocument(encoding, version);
			    	} else if (version != null) {
			    		streamWriter.writeStartDocument(streamReader.getVersion());
			    	}
			    	break;
			    	
			    case XMLStreamReader.END_DOCUMENT:
			    	streamWriter.writeEndDocument();
			    	break;
			    	
			    case XMLStreamReader.DTD:
			    	streamWriter.writeDTD(streamReader.getText());
		    		break;
		    		
		    	default:
		    		log.warning("Encountered unmatched XMLStreamReader event code ["+ readerEvent +"]");
		    		break;
		    }
		    
		    streamReader.next();
		}
		
		streamWriter.flush();
	}
}
