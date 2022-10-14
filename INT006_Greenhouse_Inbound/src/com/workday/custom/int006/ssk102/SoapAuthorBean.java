package com.workday.custom.int006.ssk102;

import static com.capeclear.assembly.annotation.Component.Type.mediation;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.AbstractCollection;
import java.util.Map;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.lang3.StringUtils;

import com.capeclear.assembly.annotation.Component;
import com.capeclear.assembly.annotation.ComponentMethod;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.impl.cc.MediationTube;
import com.capeclear.mediation.impl.mediators.MVELUtilHelper;
import com.capeclear.xml.utils.XmlUtils;
import com.workday.custom.int006.MediationConstants;


/**
 * Custom mediation
 *
 * TODO Modify the Component annotation. Also add Property annotations to any
 * bean pattern methods you add and want to appear in the Assembly Editor.
 */
@Component(
        name = "SoapAuthorBean",
        type = mediation,
        toolTip = "",
        scope = "prototype",
        smallIconPath = "icons/SoapAuthorBean_16.png",
        largeIconPath = "icons/SoapAuthorBean_24.png"
        )
public class SoapAuthorBean {

	private final String nsWorkdayAlias = "wd";
	private final String nsWorkdayURI = "urn:com.workday/bsvc";

	private final String nsSoapAlias = "soapenv";
	private final String nsSoapURI = "http://schemas.xmlsoap.org/soap/envelope/";

	private final String nsSecurityAlias = "wsse";
	private final String nsSecurityURI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	

    /**
     * This method is called by the Assembly framework.    
     */
    @ComponentMethod
    public void process(InputStream in, OutputStream out) {
    	MediationContext mc = MediationTube.getCurrentMediationContext();
		MVELUtilHelper util = new MVELUtilHelper(mc);

    	XMLStreamWriter streamWriter = null;

    	try {
	    	XMLOutputFactory outputFactory = XmlUtils.getXMLOutputFactory();
	    	streamWriter = outputFactory.createXMLStreamWriter(out);
	    	
	    	writeRequestHeader(mc, streamWriter);
	    	writeRequestBody(mc, streamWriter, util);
	    	writeRequestFooter(mc, streamWriter);
	    	
	    	streamWriter.close();
    	} catch (Throwable t) {
    		throw new RuntimeException(t);
    	} finally {
    		try {
	    		if (streamWriter != null) {
	    			streamWriter.close();
	    		}
    		} catch (Throwable t) {
        		throw new RuntimeException(t);
    		}
    	}
    }
    
    private void writeRequestHeader(MediationContext mc, XMLStreamWriter streamWriter) throws Exception {
		streamWriter.writeStartDocument("UTF-8", "1.0");
		streamWriter.setPrefix(nsSoapAlias, nsSoapURI);
		streamWriter.setPrefix(nsSecurityAlias, nsSecurityURI);
		streamWriter.setPrefix(nsWorkdayAlias, nsWorkdayURI);
		
		streamWriter.writeStartElement(nsSoapAlias, "Envelope", nsSoapURI);	//soapenv:Envelope
		
		streamWriter.writeStartElement(nsSoapAlias, "Header", nsSoapURI);	//soapenv:Header

		streamWriter.writeStartElement(nsSecurityAlias, "Security", nsSecurityURI);	//wsse:Security
		streamWriter.writeAttribute(nsSoapAlias, nsSoapURI, "mustUnderstand", "1");
		
		streamWriter.writeStartElement(nsSecurityAlias, "UsernameToken", nsSecurityURI);	//wsse:UsernameToken

		streamWriter.writeStartElement(nsSecurityAlias, "Username", nsSecurityURI);	//wsse:Username
		streamWriter.writeCharacters((String)mc.getProperty(MediationConstants.STUDIO_PROPERTY_TOKEN_USERNAME));
		streamWriter.writeEndElement();	//wsse:Username

		streamWriter.writeStartElement(nsSecurityAlias, "Password", nsSecurityURI);	//wsse:Password
		streamWriter.writeAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		streamWriter.writeCharacters((String)mc.getProperty(MediationConstants.STUDIO_PROPERTY_TOKEN_PASSWORD));
		streamWriter.writeEndElement();	//wsse:Password

		streamWriter.writeEndElement();	//wsse:UsernameToken
		
		streamWriter.writeEndElement(); //wsse:Security
		
		streamWriter.writeEndElement();	//soapenv:Header
    }
    
    private void writeRequestBody(MediationContext mc, XMLStreamWriter streamWriter, MVELUtilHelper util) throws Exception {
		streamWriter.writeStartElement(nsSoapAlias, "Body", nsSoapURI);	//soapenv:Body

		streamWriter.writeStartElement(nsWorkdayAlias, "Execute_Report", nsWorkdayURI);	//soapenv:Execute_Report

		streamWriter.writeStartElement(nsWorkdayAlias, "Report_Parameters", nsWorkdayURI);	//soapenv:Report_Parameters

		@SuppressWarnings("unchecked")
		Map<String, Object> theMap = (Map<String, Object>)mc.getProperty((String)mc.getProperty(MediationConstants.PROP_PARAMETER_IN_PROMPT_MAP));
		
		for (String k : theMap.keySet()) {
			boolean isReferenceId = StringUtils.contains(k, "!");
			
			String element = isReferenceId ? StringUtils.substringBefore(k, "!") : k;
			String attribute = isReferenceId ? StringUtils.substringAfter(k, "!") : null;
			
			Object v = theMap.get(k);
			if (v instanceof AbstractCollection<?>) {
				for (Object o : ((AbstractCollection<?>) v).toArray()) {
					writeMapKeyValue(streamWriter, util, element, attribute, isReferenceId, o);
				}
			} else {
				writeMapKeyValue(streamWriter, util, element, attribute, isReferenceId, v);
			}			
		}
		
		streamWriter.writeEndElement();	//soapenv:Report_Parameters

		streamWriter.writeEndElement();	//soapenv:Execute_Report

		streamWriter.writeEndElement();	//soapenv:Body
	}
    
    private void writeMapKeyValue(XMLStreamWriter streamWriter, MVELUtilHelper util, String element, String attribute, boolean isReferenceId, Object value) throws Exception {
		streamWriter.writeStartElement(nsWorkdayAlias, element, nsWorkdayURI);	//dynamic by map key
		
		if (isReferenceId) {
			streamWriter.writeStartElement(nsWorkdayAlias, "ID", nsWorkdayURI);	//wd:ID
			streamWriter.writeAttribute(nsWorkdayAlias, nsWorkdayURI, "type", attribute);
		}
		
		//The streamWriter is escape-aware and will automatically XMLencode characters for safety without need for additional intervention 
		streamWriter.writeCharacters(String.valueOf(value));
		
		if (isReferenceId) {
			streamWriter.writeEndElement();	//wd:ID
		}

		streamWriter.writeEndElement();	//dynamic by map key
    }
    
    private void writeRequestFooter(MediationContext mc, XMLStreamWriter streamWriter) throws Exception {
		streamWriter.writeEndElement();	//soapenv:Envelope
		
		streamWriter.writeEndDocument();
		streamWriter.flush();
    }

}
