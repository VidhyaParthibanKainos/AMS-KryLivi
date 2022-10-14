package com.workday.custom.int006.ssk109;

import static com.capeclear.assembly.annotation.Component.Type.mediation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jms.IllegalStateException;

import org.apache.commons.collections.CollectionUtils;
import org.w3c.dom.Document;

import com.capeclear.assembly.annotation.Component;
import com.capeclear.assembly.annotation.ComponentMethod;
import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.impl.mediators.MVELUtilHelper;
import com.capeclear.mediation.impl.mediators.utils.XPath;
import com.workday.custom.int006.MediationConstants;
import com.workday.custom.int006.SSKUtils;
import com.workday.custom.int006.ssk144.StringDeduplicator;
import com.workday.esb.intsys.ParsedIntegrationSystems;
import com.workday.integration.launch.LaunchParametersHelper;

import org.apache.commons.lang3.StringUtils;


/**
 * Custom mediation
 *
 * @author john.smail
 */
@Component(
        name = "CoreInitializationBean",
        type = mediation,
        toolTip = "",
        scope = "prototype",
        smallIconPath = "icons/CoreInitializationBean_16.png",
        largeIconPath = "icons/CoreInitializationBean_24.png"
        )
public class CoreInitializationBean {

	//Logging at a Java level to the server.log file
	private Logger log = LogControl.getLogger(getClass());
	//Cross-initialization tracking for properties to add to default debug configuration
	private Set<String> initializedAttributesAndParameters = new HashSet<String>();
	
    /**
     * This method is called by the Assembly framework.
     * 
     * The functional purpose of this method to the SSK Framework is to initialize the following:
     * <ul>
     * <li>Integration Attribute Services</li>
     * <li>Launch Parameters</li>
     * <li>Helper Objects expected and used by SSK</li>
     * </ul>
     * Some Services, Attributes and/or Launch Parameters are required by SSK, while others are not.  If a required Service, Attribute or Launch Parameter is removed, then a <code>RuntimeException</code> will be thrown and the integration will not advance.  
     * If the Service, Attribute or Launch Parameter is not required, initialization will be handled on a case-by-case basis.  The property based on that Attribute or Launch Parameter may be initialized to a default value, or it may not be created at all.
     * 
     * If Launch Parameters or Attributes on existing SSK services are added to the Workday-In, those values will have be configured separately.  This class is SSK Framework specific and handles only that which is predefined by SSK.
     *
     * Use the <code>MediationContext</code> to access objects in the context,
     * such as the message, properties and variables e.g.
     * <ul>
     * <li><code>MediationMessage msg = arg0.getMessage();</code></li>
     * <li><code>String myPropValue = (String)arg0.getProperty("myprop");</code></li>
     * <li><code>Source myVar = arg0.getVariables().getVariable("myvar");</code></li>
     * </ul>    
     */
    @ComponentMethod
    public void process(MediationContext mc) throws Throwable {    	
    	MVELUtilHelper util = new MVELUtilHelper(mc);
    	LaunchParametersHelper lp = new LaunchParametersHelper(mc);
    	ParsedIntegrationSystems intsys = (ParsedIntegrationSystems)mc.getProperty(ParsedIntegrationSystems.INTEGRATION_SYSTEM_PROPERTY);
    	
   		configureVersion(mc, util);
   		
   		configureXsltLibraryPaths(mc);
    	
   		configureLaunchParameters(mc, lp);
  		configureIntegrationAttributes(mc, intsys);
  		
  		configureGeneralSettings(mc);

  		// configureDebug should always be the last call in process so that any additionally configured parameters or attributes can be included in default debugging configuration.
  		configureDebug(mc, util);
    }
    
    private void configureVersion(MediationContext mc, MVELUtilHelper util) throws Throwable {
    	String wsApplicationFile = "ws-application.xml";
    	String temporaryVariableName = "sskWsApplication";
    	String xpathToSskVersion = "/*:CapeConnect/ws-application/initParams/property[name=\"cc.originator.type\"]/value";

    	try {
        	util.readFileToVar(temporaryVariableName, wsApplicationFile, "text/xml");
        	
        	Document varAsDocument = SSKUtils.getVariableAsXmlDocument(mc, temporaryVariableName);
        	String valueFromFile = new XPath(xpathToSskVersion, MediationConstants.ASSEMBLY_VERSION, mc).stringValueOf(varAsDocument);
        	
        	mc.setProperty(MediationConstants.PROPERTY_SSK_VERSION, valueFromFile);
        	mc.getVariables().remove(temporaryVariableName);
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to set the ["+ MediationConstants.PROPERTY_SSK_VERSION +"] property.  Confirm the " + wsApplicationFile + " is present under the ws/WSAR-INF folder.", 
    				xcpn); 
    	}
    }
    
    private void configureXsltLibraryPaths(MediationContext mc) throws Throwable {
    	mc.setProperty(MediationConstants.PROPERTY_SSK_XSLT_FUNCTION_LIBRARY_MESSAGES, SSKUtils.getContextualURL(mc, MediationConstants.VALUE_XSLT_LIB_MESSAGE));
    }
    
    private void configureLaunchParameters(MediationContext mc, LaunchParametersHelper lp) throws Throwable {
    	// This value insulates the scenario where the code is running under AUnit test and the Launch Parameters have not been initialized.
    	// A default state is assumed per LP.
    	boolean isSet = lp.isSet();
    	
    	configureLaunchParameterRunWithDebugLogging(mc, lp, isSet);
    	configureLaunchParameterRunInValidationMode(mc, lp, isSet);
    	configureLaunchParameterSourceIntegrationEventWID(mc, lp, isSet);
    	configureLaunchParameterIntegrationEventWID(mc, lp, isSet);
    }
    
    private void configureLaunchParameterRunWithDebugLogging(MediationContext mc, LaunchParametersHelper lp, boolean isSet) throws Throwable {
    	try {
    		if (isSet && !mc.containsProperty(MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE)) {
	        	if (lp.exists(MediationConstants.LAUNCH_PARAMETER_DEBUG_MODE)) {
	            	mc.setProperty(MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE, "1".equals(lp.getSimpleData(MediationConstants.LAUNCH_PARAMETER_DEBUG_MODE)));
		    		initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE);
	        	} else {
	        		throw new IllegalStateException("The '" + MediationConstants.LAUNCH_PARAMETER_DEBUG_MODE + "' launch parameter is required but appears to have been removed.  Please restore this launch parameter on the Workday-In.  The launch parameter is a core part of the SSK Framework and required for correct behavior.");
	        	}
    		} else if (!mc.containsProperty(MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE)) {
	    		mc.setProperty(MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE, Boolean.FALSE);
    		}
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to configure the '"+ MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE +"' property.", 
    				xcpn); 
    	}
    }
    
    private void configureLaunchParameterRunInValidationMode(MediationContext mc, LaunchParametersHelper lp, boolean isSet) throws Throwable {
    	try {
    		if (isSet) {
	        	if (lp.exists(MediationConstants.LAUNCH_PARAMETER_VALIDATION_MODE)) {
	            	mc.setProperty(MediationConstants.PROPERTY_SSK_IS_VALIDATION_MODE, "1".equals(lp.getSimpleData(MediationConstants.LAUNCH_PARAMETER_VALIDATION_MODE)));
		    		initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_IS_VALIDATION_MODE);
	        	} else {
	        		log.info("The '" + MediationConstants.LAUNCH_PARAMETER_VALIDATION_MODE + "' launch parameter appears to have been removed from the Workday-In.  The SSK Framework property '" + MediationConstants.PROPERTY_SSK_IS_VALIDATION_MODE + "' will be initialized to a default value.");
	        	}
    		} else if (!mc.containsProperty(MediationConstants.PROPERTY_SSK_IS_VALIDATION_MODE)) {
	    		mc.setProperty(MediationConstants.PROPERTY_SSK_IS_VALIDATION_MODE, Boolean.FALSE);
    		}
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to configure the '"+ MediationConstants.PROPERTY_SSK_IS_VALIDATION_MODE +"' property.", 
    				xcpn); 
    	}
    }
    
    private void configureLaunchParameterSourceIntegrationEventWID(MediationContext mc, LaunchParametersHelper lp, boolean isSet) throws Throwable {
    	try {
    		if (isSet) {
	    		if (lp.exists(MediationConstants.LAUNCH_PARAMETER_SOURCE_EVENT_WID)) {
	            	mc.setProperty(MediationConstants.PROPERTY_SSK_DEBUG_EVENT_WID, lp.getSimpleData(MediationConstants.LAUNCH_PARAMETER_SOURCE_EVENT_WID));
		    		initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_DEBUG_EVENT_WID);
	        	} else {
	        		log.info("The '" + MediationConstants.LAUNCH_PARAMETER_SOURCE_EVENT_WID + "' launch parameter appears to have been removed from the Workday-In.  The SSK Framework property '" + MediationConstants.PROPERTY_SSK_DEBUG_EVENT_WID + "' will not be initialized.");
	        	}
    		} else if (!mc.containsProperty(MediationConstants.PROPERTY_SSK_DEBUG_EVENT_WID)) {
	    		mc.setProperty(MediationConstants.PROPERTY_SSK_DEBUG_EVENT_WID, null);
    		}
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to configure the '"+ MediationConstants.PROPERTY_SSK_DEBUG_EVENT_WID +"' property.", 
    				xcpn); 
    	}
    }
    
    private void configureLaunchParameterIntegrationEventWID(MediationContext mc, LaunchParametersHelper lp, boolean isSet) throws Throwable {
    	try {
    		if (isSet) {
		    	mc.setProperty(MediationConstants.PROPERTY_SSK_EVENT_WID, lp.getIntegrationEventWID());
	    		initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_EVENT_WID);
    		} else if (!mc.containsProperty(MediationConstants.PROPERTY_SSK_EVENT_WID)) {
	    		mc.setProperty(MediationConstants.PROPERTY_SSK_EVENT_WID, null);
    		}
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to configure the '"+ MediationConstants.PROPERTY_SSK_EVENT_WID +"' property.", 
    				xcpn); 
    	}
    }
    
    private void configureIntegrationAttributes(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.isServiceEnabled(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG)) {
    		configurePrimaryLogIntegrationService(mc, intsys);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the primary log.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG + "' service is required but appears to have been removed.  Please restore this service, as well as the attributes it contained, on the Workday-In component.  The service is a core part of the SSK Framework and required for correct behavior.")); 
    	}
    	
    	if (intsys.isServiceEnabled(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG)) {
    		mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_IS_ENABLED, Boolean.TRUE);
    		configureSecondaryLogIntegrationService(mc, intsys);
    	} else {
    		mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_IS_ENABLED, Boolean.FALSE);
    		log.info("The '" + MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG + "' service appears to have been removed from the Workday-In.  The SSK Framework will skip initialization of the associated properties on that service.");
    	}
    	
    	if (intsys.isServiceEnabled(MediationConstants.ATTRIBUTE_SERVICE_GENERAL)) {
    		configureGeneralIntegrationService(mc, intsys);
    	} else {
    		log.info("The '" + MediationConstants.ATTRIBUTE_SERVICE_GENERAL + "' service appears to have been removed from the Workday-In.  The SSK Framework will skip initialization of the associated properties on that service.  SSK Components dependent on properties related to this service may use Component-specific default values when the global properties are not defined.");
    	}
    	
    	if (intsys.isServiceEnabled(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL)) {
    		configureFunctionalIntegrationService(mc, intsys);
    	} else {
    		log.info("The '" + MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL + "' service appears to have been removed from the Workday-In.  The SSK Framework will skip initialization of the associated properties on that service.  SSK Components dependent on properties related to this service may use Component-specific default values when the global properties are not defined.");
    	}
    	
    	if (intsys.isServiceEnabled(MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE)) {
    		configureMessageQueueIntegrationService(mc, intsys);
    	} else {
    		log.info("The '" + MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE + "' service appears to have been removed from the Workday-In.  The SSK Framework will skip initialization of the associated properties on that service.  SSK Components dependent on properties related to this service may use Component-specific default values when the global properties are not defined.");
    	}
    }
    
    private void configurePrimaryLogIntegrationService(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	configurePrimaryLogFilename(mc, intsys);
    	configurePrimaryLogRetention(mc, intsys);
    	configurePrimaryLogMaxSize(mc, intsys);
    	configurePrimaryLogFormat(mc, intsys);
    	
    	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_BY_FILE, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_DEBUG, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_ERROR, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_FATAL, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_INFO, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_TOTAL, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_WARN, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_FILES_STORED, 0);
    }
    
    private void configurePrimaryLogFilename(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_FILENAME)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_FILENAME, 
        			intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_FILENAME) != null ? 
        					intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_FILENAME) : 
        						"Log");
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_FILENAME);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the primary log.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_LOG_FILENAME + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The service and its attributes are a core part of the SSK Framework and required for correct behavior.")); 
    	}
    }
    
    private void configurePrimaryLogRetention(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_RETENTION)) {
        	StringBuilder sb = new StringBuilder();
        	sb.append("P")
        		.append(intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_RETENTION) != null ? 
        				intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_RETENTION) : 
        					"30")
        		.append("D");
        	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_RETENTION, sb.toString());
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_RETENTION);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the primary log.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_LOG_RETENTION + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The service and its attributes are a core part of the SSK Framework and required for correct behavior.")); 
    	}
    }

    private void configurePrimaryLogMaxSize(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_MAX_SIZE)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_MAX_SIZE, 
        			intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_MAX_SIZE) != null ? 
        					Integer.valueOf(intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_MAX_SIZE)) : 
        						0);
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_MAX_SIZE);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the primary log.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_LOG_MAX_SIZE + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The service and its attributes are a core part of the SSK Framework and required for correct behavior.")); 
    	}
    }
    
    private void configurePrimaryLogFormat(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_FORMAT)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_FORMAT, 
        			intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_FORMAT) != null ? 
        					intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG, MediationConstants.ATTRIBUTE_LOG_FORMAT) : 
        						MediationConstants.PROPERTY_SSK_CLOUD_LOG_FORMAT_HTML);
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_FORMAT);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the primary log.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_LOG_FORMAT + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_PRIMARY_LOG + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The service and its attributes are a core part of the SSK Framework and required for correct behavior.")); 
    	}
    }
    
    private void configureSecondaryLogIntegrationService(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	configureSecondaryLogFilename(mc, intsys);
    	configureSecondaryLogRetention(mc, intsys);
    	configureSecondaryLogMaxSize(mc, intsys);
    	configureSecondaryLogFormat(mc, intsys);
    	
    	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_BY_FILE, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_DEBUG, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_ERROR, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_FATAL, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_INFO, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_TOTAL, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_WARN, 0);
    	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_FILES_STORED, 0);
    }
    
    private void configureSecondaryLogFilename(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_FILENAME)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_FILENAME, 
        			intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_FILENAME) != null ? 
        					intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_FILENAME) : 
        						"Log");
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_FILENAME);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the secondary log.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_LOG_FILENAME + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the '" + MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG + "' service is in use.")); 
    	}
    }
    
    private void configureSecondaryLogRetention(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_RETENTION)) {
        	StringBuilder sb = new StringBuilder();
        	sb.append("P")
        		.append(intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_RETENTION) != null ? 
        				intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_RETENTION) : 
        					"30")
        		.append("D");
        	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_RETENTION, sb.toString());
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_RETENTION);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the secondary log.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_LOG_RETENTION + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the '" + MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG + "' service is in use.")); 
    	}
    }
    
    private void configureSecondaryLogMaxSize(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_MAX_SIZE)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_MAX_SIZE, 
        			intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_MAX_SIZE) != null ? 
        					Integer.valueOf(intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_MAX_SIZE)) : 
        						0);
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_MAX_SIZE);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the secondary log.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_LOG_MAX_SIZE + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the '" + MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG + "' service is in use.")); 
    	}
    }
    
    private void configureSecondaryLogFormat(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_FORMAT)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_FORMAT, 
        			intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_FORMAT) != null ? 
        					intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG, MediationConstants.ATTRIBUTE_LOG_FORMAT) : 
        						MediationConstants.PROPERTY_SSK_CLOUD_LOG_FORMAT_HTML);
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_FORMAT);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the secondary log.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_LOG_FORMAT + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the '" + MediationConstants.ATTRIBUTE_SERVICE_SECONDARY_LOG + "' service is in use.")); 
    	}
    }
    
    private void configureGeneralIntegrationService(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	configureGeneralDocumentRetention(mc, intsys);
    }
    
    private void configureGeneralDocumentRetention(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	try {
    		if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_GENERAL, MediationConstants.ATTRIBUTE_DOCUMENT_RETENTION)) {
	        	StringBuilder sb = new StringBuilder();
	        	sb.append("P")
	        		.append(intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_GENERAL, MediationConstants.ATTRIBUTE_DOCUMENT_RETENTION) != null ? 
	        				intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_GENERAL, MediationConstants.ATTRIBUTE_DOCUMENT_RETENTION) : 
	        					"30")
	        		.append("D");
	        	mc.setProperty(MediationConstants.PROPERTY_SSK_DOCUMENT_RETENTION_PERIOD, sb.toString());
	        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_DOCUMENT_RETENTION_PERIOD);
        	} else {
        		log.info("The '" + MediationConstants.ATTRIBUTE_DOCUMENT_RETENTION + "' attribute on the '" + MediationConstants.ATTRIBUTE_SERVICE_GENERAL + "' service appears to have been removed.  The SSK Framework property '" + MediationConstants.PROPERTY_SSK_DOCUMENT_RETENTION_PERIOD + "' will not be initialized.  SSK Components dependent on properties related to this service may use Component-specific default values when the global properties are not defined.");
	    	}
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to configure the ["+ MediationConstants.PROPERTY_SSK_DOCUMENT_RETENTION_PERIOD +"] property.", 
    				xcpn); 
    	}
    }
    
    private void configureFunctionalIntegrationService(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	configureFunctionalRetrievalDocumentTagType(mc, intsys);
    	configureFunctionalRetrievalDocumentTag(mc, intsys);
    	configureFunctionalOutputFilename(mc, intsys);
    	configureFunctionalDeliveryDocumentTag(mc, intsys);
    }
    
    private void configureFunctionalRetrievalDocumentTagType(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	try {
	    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG_TYPE)) {
	        	mc.setProperty(MediationConstants.PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG_TYPE, 
	        			intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG_TYPE) != null ? 
	        					intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG_TYPE) : 
	        						MediationConstants.PROPERTY_SSK_RETRIEVAL_MATCH_ALL);
	        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG_TYPE);
	    	} else {
        		log.info("The '" + MediationConstants.ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG_TYPE + "' attribute on the '" + MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL + "' service appears to have been removed.  The SSK Framework property '" + MediationConstants.ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG_TYPE + "' will not be initialized.  SSK Components dependent on properties related to this service may use Component-specific default values when the global properties are not defined.");
	    	}
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to configure the ["+ MediationConstants.ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG_TYPE +"] property.", 
    				xcpn); 
    	}
    }
    
    private void configureFunctionalRetrievalDocumentTag(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	try {
	    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG)) {
	    		List<String> tags = intsys.getAttributeReferenceDataList(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG, "WID");
	    		
	        	mc.setProperty(MediationConstants.PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG, 
	        			CollectionUtils.isNotEmpty(tags) ? 
	        					tags : 
	        						null);
	        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG);
        	} else {
        		log.info("The '" + MediationConstants.ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG + "' attribute on the '" + MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL + "' service appears to have been removed.  The SSK Framework property '" + MediationConstants.PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG + "' will not be initialized.  SSK Components dependent on properties related to this service may use Component-specific default values when the global properties are not defined.");
	    	}
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to configure the ["+ MediationConstants.PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG +"] property.", 
    				xcpn); 
    	}
    }
    
    private void configureFunctionalOutputFilename(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	try {
	    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_OUTPUT_FILENAME)) {
	        	mc.setProperty(MediationConstants.PROPERTY_SSK_OUTPUT_FILENAME, 
	        			intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_OUTPUT_FILENAME) != null ? 
	        					intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_OUTPUT_FILENAME) : 
	        						"INT_Results");
	        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_OUTPUT_FILENAME);
        	} else {
        		log.info("The '" + MediationConstants.ATTRIBUTE_DOCUMENT_OUTPUT_FILENAME + "' attribute on the '" + MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL + "' service appears to have been removed.  The SSK Framework property '" + MediationConstants.PROPERTY_SSK_OUTPUT_FILENAME + "' will not be initialized.  SSK Components dependent on properties related to this service may use Component-specific default values when the global properties are not defined.");
	    	}
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to configure the ["+ MediationConstants.PROPERTY_SSK_OUTPUT_FILENAME +"] property.", 
    				xcpn); 
    	}
    }
    
    private void configureFunctionalDeliveryDocumentTag(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	try {
	    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_DELIVERY_TAG)) {
	    		List<String> tags = intsys.getAttributeReferenceDataList(MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL, MediationConstants.ATTRIBUTE_DOCUMENT_DELIVERY_TAG, "Document_Tag_Name");
	    		
	        	mc.setProperty(MediationConstants.PROPERTY_SSK_DELIVERY_DOCUMENT_TAG, 
	        			CollectionUtils.isNotEmpty(tags) ? 
	        					tags : 
	        						null);
	        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_DELIVERY_DOCUMENT_TAG);
        	} else {
        		log.info("The '" + MediationConstants.ATTRIBUTE_DOCUMENT_DELIVERY_TAG + "' attribute on the '" + MediationConstants.ATTRIBUTE_SERVICE_FUNCTIONAL + "' service appears to have been removed.  The SSK Framework property '" + MediationConstants.PROPERTY_SSK_DELIVERY_DOCUMENT_TAG + "' will not be initialized.  SSK Components dependent on properties related to this service may use Component-specific default values when the global properties are not defined.");
	    	}
    	} catch (Throwable xcpn) {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to configure the ["+ MediationConstants.PROPERTY_SSK_DELIVERY_DOCUMENT_TAG +"] property.", 
    				xcpn); 
    	}
    }
    
    private void configureMessageQueueIntegrationService(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	configureMessageQueueEndpoint(mc, intsys);
    	configureMessageQueueUsername(mc, intsys);
    	configureMessageQueuePassword(mc, intsys);
    	configureMessageQueueName(mc, intsys);
    }
    
    private void configureMessageQueueEndpoint(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE, MediationConstants.ATTRIBUTE_QUEUE_ENDPOINT)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_QUEUE_ENDPOINT, intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE, MediationConstants.ATTRIBUTE_QUEUE_ENDPOINT));
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_QUEUE_ENDPOINT);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the message queue.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_QUEUE_ENDPOINT + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the '" + MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE + "' service is in use.")); 
    	}
    }
    
    private void configureMessageQueueUsername(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE, MediationConstants.ATTRIBUTE_QUEUE_USERNAME)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_QUEUE_USERNAME, intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE, MediationConstants.ATTRIBUTE_QUEUE_USERNAME) + "@" + (String)mc.getProperty(MediationConstants.STUDIO_PROPERTY_TENANT_ID));
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_QUEUE_USERNAME);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the message queue.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_QUEUE_USERNAME + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the '" + MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE + "' service is in use.")); 
    	}
    }
    
    private void configureMessageQueuePassword(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE, MediationConstants.ATTRIBUTE_QUEUE_PASSWORD)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_QUEUE_PASSWORD, intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE, MediationConstants.ATTRIBUTE_QUEUE_PASSWORD));
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the message queue.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_QUEUE_PASSWORD + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the '" + MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE + "' service is in use.")); 
    	}
    }
    
    private void configureMessageQueueName(MediationContext mc, ParsedIntegrationSystems intsys) throws Throwable {
    	if (intsys.integrationAttributeExists(MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE, MediationConstants.ATTRIBUTE_QUEUE_NAME)) {
        	mc.setProperty(MediationConstants.PROPERTY_SSK_QUEUE_NAME, intsys.getAttribute(MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE, MediationConstants.ATTRIBUTE_QUEUE_NAME));
        	initializedAttributesAndParameters.add(MediationConstants.PROPERTY_SSK_QUEUE_NAME);
    	} else {
    		SSKUtils.logErrorAndThrowRuntimeException(
    				log, 
    				"Unable to complete configuration of the message queue.", 
    				new IllegalStateException("The '" + MediationConstants.ATTRIBUTE_QUEUE_NAME + "' on the '" + MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE + "' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the '" + MediationConstants.ATTRIBUTE_SERVICE_MESSAGE_QUEUE + "' service is in use.")); 
    	}
    }
    
    private void configureGeneralSettings(MediationContext mc) throws Throwable {
    	mc.setProperty(MediationConstants.PROPERTY_SSK_STATIC_ANALYSIS_EXEMPTIONS, new ArrayList<String>());
    }
    
    private void configureDebug(MediationContext mc, MVELUtilHelper util) throws Throwable {
  		configureDebugTypes(mc);
   		configureDebugNames(mc);
   		configureDefaultDebugProperties(mc);
    	
    	mc.setProperty(MediationConstants.PROPERTY_SSK_DEDUPLICATOR, new StringDeduplicator());
    	mc.setProperty(MediationConstants.PROPERTY_SSK_DEBUG_ARCHIVE, "DebugFiles-int006_Greenhouse_Inbound-" + StringUtils.replace(util.currentDateTimeAsString(), ":", null) + ".zip");
    }

    private void configureDebugTypes(MediationContext mc) throws Throwable {
    	Set<String> debugTypes = new HashSet<String>();
    	debugTypes.add("message");
    	debugTypes.add("property");
    	debugTypes.add("properties");
    	debugTypes.add("variable");
    	debugTypes.add("map");
    	debugTypes.add("list");
    	debugTypes.add("set");
    	debugTypes.add("finalize");
    	
    	mc.setProperty(MediationConstants.PROPERTY_SSK_DEBUG_TARGET_TYPE_VALIDATION, debugTypes);
    }
    
    private void configureDebugNames(MediationContext mc) throws Throwable {
    	Set<String> debugNames = new HashSet<String>();
    	debugNames.add("message");
    	debugNames.add("property");
    	debugNames.add("properties");
    	debugNames.add("variable");
    	debugNames.add("map");
    	debugNames.add("list");
    	debugNames.add("set");
    	
    	mc.setProperty(MediationConstants.PROPERTY_SSK_DEBUG_TARGET_NAME_VALIDATION, debugNames);
    }
    
    private void configureDefaultDebugProperties(MediationContext mc) throws Throwable {
    	List<String> propertyNames = new ArrayList<String>();
    	propertyNames.add(MediationConstants.PROPERTY_SSK_VERSION);
    	propertyNames.add(MediationConstants.PROPERTY_GLOBAL_API_VERSION);
    	
    	for (Iterator<String> iterator = initializedAttributesAndParameters.iterator(); iterator.hasNext();) {
    		propertyNames.add(iterator.next());			
		}
    	
    	mc.setProperty(MediationConstants.PROPERTY_SSK_DEBUG_LIST, propertyNames);
    }
}
