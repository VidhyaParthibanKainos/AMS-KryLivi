package com.workday.custom.aunit.int006;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import com.capeclear.capeconnect.attachments.io.FileBackedManagedData;
import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.impl.DataHandlerSource;
import com.capeclear.xml.utils.XmlUtils;

public class ResourceConverter {
	
	Logger log = LogControl.getLogger(ResourceConverter.class);

    public InputStream prepareResourceWithReplacements(MediationContext mc, InputStream in, Map<String, String> replacementKeys) throws IOException {
    	XmlExpectedResultGenerator generator = new XmlExpectedResultGenerator();
    	
    	XMLStreamReader streamReader = null;
    	XMLStreamWriter streamWriter = null;

    	FileBackedManagedData fbmd = new FileBackedManagedData("text/xml");
    	mc.addDisposable(fbmd);
    	OutputStream out = fbmd.getOutputStream(); 
		
    	try {
        	XMLInputFactory inputFactory = XmlUtils.getXMLInputFactory();
	    	XMLOutputFactory outputFactory = XmlUtils.getXMLOutputFactory();

        	streamReader = inputFactory.createXMLStreamReader(in);
	    	streamWriter = outputFactory.createXMLStreamWriter(out);

	    	generator.copyResourceWithTokenReplacement(replacementKeys, streamReader, streamWriter);
	    	
        	streamReader.close();
	    	streamWriter.close();
	    	
    	} catch (Throwable t) {
    		throw new RuntimeException(t);
    	} finally {
    		try {
	    		if (streamReader != null) {
	    			streamReader.close();
	    		}
    		} catch (Throwable t) {
    			
    		}

    		try {
	    		if (streamWriter != null) {
	    			streamWriter.close();
	    		}
    		} catch (Throwable t) {
    			
    		}
    	}
    	
    	return new DataHandlerSource(fbmd.getDataSource()).getInputStream();
    }
}
