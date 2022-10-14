package com.workday.custom.int006;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMSource;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.capeclear.logger.Logger;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.impl.DataHandlerSource;
import com.capeclear.mediation.impl.mediators.utils.XPath;
import com.capeclear.xml.utils.DOMUtils;
import com.workday.custom.int006.ssk112.CloudLogEntry;

public final class SSKUtils {
	
	public static JAXBContext JAXB_CONTEXT;
	public static Unmarshaller UNMARSHALLER;
	public static Marshaller MARSHALLER;

	/*
	 * NOTE:
	 * 
	 * General Workday Studio development guidance is to avoid use of static instances in Java.  The reason for which is
	 * that the integration server may not be torn down after the event completes, and in a data center optimization, may
	 * instead be recycled to serve the next event sooner.  If data is stored in static references, then this can result
	 * in two consequences: reduced memory availability to the second integration, and potential leakage of data between
	 * events.
	 * 
	 * The static references below are used because:
	 * a) They are small memory footprints, and as the use of SSK becomes broader, the existing instances will save the
	 * second integration the overhead of reinstantiating them, and...
	 * b) They contain no customer data and have no opportunity to leakage information across events. 
	 */
	static {
		try {
			JAXB_CONTEXT = JAXBContext.newInstance(CloudLogEntry.class);

			UNMARSHALLER = JAXB_CONTEXT.createUnmarshaller();
			
			MARSHALLER = JAXB_CONTEXT.createMarshaller();
			MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
			MARSHALLER.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
	}
	
	public static String getContextualURL(MediationContext mc, String relativeFilepathToWSARINF) throws Exception {
		String returnValue = null;
		String base = mc.getBaseURL().toExternalForm();
		if (StringUtils.contains(base, "WSAR-INF")) {
			returnValue = base + relativeFilepathToWSARINF;
		} else if (base.endsWith("/")) {
			returnValue = base + "WSAR-INF/" + relativeFilepathToWSARINF;
		} else {
			returnValue = base + "/WSAR-INF/" + relativeFilepathToWSARINF;
		}
		
		return returnValue;
	}
	
	public static boolean isVariableNullOrUndefined(MediationContext mc, String variableName) throws Exception {
		return (!mc.getVariables().containsKey(variableName)) || mc.getVariables().getVariable(variableName) == null;
	}
	
	public static InputStream getVariableInputStream(MediationContext mc, String variableName) throws Exception {
		return ((DataHandlerSource)mc.getVariables().getVariable(variableName)).getInputStream();
	}
	
	public static InputStream getMessageInputStream(MediationContext mc) throws Exception {
		return (InputStream)mc.getMessage().getMessagePart(0, InputStream.class);
	}
	
	public static Document getVariableAsXmlDocument(MediationContext mc, String variableName) throws Exception {
		return DOMUtils.parseToDOM(new InputSource(new InputStreamReader(((DataHandlerSource)mc.getVariables().getVariable(variableName)).getInputStream(), "UTF-8")));
	}
	
	public static Document getMessageAsXmlDocument(MediationContext mc) throws Exception {
		return mc.getMessage().isNullPart(0) ? null : DOMUtils.parseToDOM(new InputSource(new InputStreamReader(getMessageInputStream(mc), "UTF-8")));
	}
	
	@SuppressWarnings("unchecked")
	public static DOMSource getMessageAsDOMSource(MediationContext mc) throws Exception {
		return mc.getMessage().isNullPart(0) ? null : (DOMSource)mc.getMessage().getMessage(new Class[] {DOMSource.class});
	}
	
	public static Node getDocumentRootNode(DOMSource source) {
		return (source == null) ? null : source.getNode();
	}

	public static void logErrorAndThrowRuntimeException(Logger log, String message, Throwable t) throws Throwable {
		log.error(message, t);
		throw new RuntimeException(message, t);
	}
	
	public static String getValueFromVariableXml(MediationContext mc, String variableName, String xpathToValue) throws Exception {
    	return new XPath(xpathToValue, MediationConstants.ASSEMBLY_VERSION, mc).stringValueOf(getVariableAsXmlDocument(mc, variableName));
	}
}
