package com.workday.custom.aunit.int006.utilities.ssk109;

import java.util.ArrayList;
import java.util.HashSet;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.CommonTestCase;
import com.workday.custom.int006.MediationConstants;
import com.workday.custom.int006.ssk144.StringDeduplicator;

import wd.esb.mc.shaded.org.apache.commons.lang.StringUtils;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class InitializeTestCase extends CommonTestCase {
	public static final String MOCK_XML_INTSYS_COMPLETE = "test/ssk/ssk109/SSK109_SOAP_Attr_Complete.xml";

	public static final String MOCK_XML_INTSYS_GENERAL_SERVICE = "test/ssk/ssk109/SSK109_SOAP_Attr_NoGeneralService.xml";
	public static final String MOCK_XML_INTSYS_FUNCTIONAL_SERVICE = "test/ssk/ssk109/SSK109_SOAP_Attr_NoFunctionalService.xml";
	public static final String MOCK_XML_INTSYS_PRIMARYLOG_SERVICE = "test/ssk/ssk109/SSK109_SOAP_Attr_NoPrimaryLogService.xml";
	public static final String MOCK_XML_INTSYS_SECONDARYLOG_SERVICE = "test/ssk/ssk109/SSK109_SOAP_Attr_NoSecondaryLogService.xml";
	
	public static final String MOCK_XML_INTSYS_GENERAL_RETENTION = "test/ssk/ssk109/SSK109_SOAP_Attr_NoGeneralRetention.xml";

	public static final String MOCK_XML_INTSYS_FUNCTIONAL_RETRIEVAL_TAG = "test/ssk/ssk109/SSK109_SOAP_Attr_NoFunctionalRetrievalTag.xml";
	public static final String MOCK_XML_INTSYS_FUNCTIONAL_OUTPUT_FILENAME = "test/ssk/ssk109/SSK109_SOAP_Attr_NoFunctionalOutputFilename.xml";
	public static final String MOCK_XML_INTSYS_FUNCTIONAL_DELIVERY_TAG = "test/ssk/ssk109/SSK109_SOAP_Attr_NoFunctionalDeliveryTag.xml";

	public static final String MOCK_XML_INTSYS_PRIMARYLOG_FILENAME = "test/ssk/ssk109/SSK109_SOAP_Attr_NoPrimaryLogFilename.xml";
	public static final String MOCK_XML_INTSYS_PRIMARYLOG_RETENTION = "test/ssk/ssk109/SSK109_SOAP_Attr_NoPrimaryLogRetention.xml";
	public static final String MOCK_XML_INTSYS_PRIMARYLOG_MAX_SIZE = "test/ssk/ssk109/SSK109_SOAP_Attr_NoPrimaryLogMaxSize.xml";
	public static final String MOCK_XML_INTSYS_PRIMARYLOG_FORMAT = "test/ssk/ssk109/SSK109_SOAP_Attr_NoPrimaryLogFormat.xml";

	public static final String MOCK_XML_INTSYS_SECONDARYLOG_FILENAME = "test/ssk/ssk109/SSK109_SOAP_Attr_NoSecondaryLogFilename.xml";
	public static final String MOCK_XML_INTSYS_SECONDARYLOG_RETENTION = "test/ssk/ssk109/SSK109_SOAP_Attr_NoSecondaryLogRetention.xml";
	public static final String MOCK_XML_INTSYS_SECONDARYLOG_MAX_SIZE = "test/ssk/ssk109/SSK109_SOAP_Attr_NoSecondaryLogMaxSize.xml";
	public static final String MOCK_XML_INTSYS_SECONDARYLOG_FORMAT = "test/ssk/ssk109/SSK109_SOAP_Attr_NoSecondaryLogFormat.xml";

	public static final String MOCK_XML_LP_COMPLETE = "test/ssk/ssk109/SSK109_SOAP_LP_Complete.xml";
	public static final String MOCK_XML_LP_LISTENER = "test/ssk/ssk109/SSK109_SOAP_LP_ListenerService.xml";

	public static final String MOCK_XML_LP_DEBUG = "test/ssk/ssk109/SSK109_SOAP_LP_NoDebug.xml";
	public static final String MOCK_XML_LP_VALIDATION = "test/ssk/ssk109/SSK109_SOAP_LP_NoValidation.xml";
	public static final String MOCK_XML_LP_DEBUG_WID = "test/ssk/ssk109/SSK109_SOAP_LP_NoDebugWID.xml";
	
	protected String errorMessage = null;
	protected String errorDetails = null;

	@Override
 	protected void setupIntegrationSystem() throws Exception {
		String intSysResponseResourcePath;
		
		switch (getName()) {
			case "testNoGeneralService" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_GENERAL_SERVICE;
				break;
				
			case "testNoFunctionalService" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_FUNCTIONAL_SERVICE;
				break;
				
			case "testNoPrimayLogService" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_PRIMARYLOG_SERVICE;
				break;
				
			case "testNoSecondayLogService" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_SECONDARYLOG_SERVICE;
				break;
				
			case "testNoGeneralRetention" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_GENERAL_RETENTION;
				break;
				
			case "testNoFunctionalRetrievalTag" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_FUNCTIONAL_RETRIEVAL_TAG;
				break;
				
			case "testNoFunctionalOutputFilename" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_FUNCTIONAL_OUTPUT_FILENAME;
				break;
				
			case "testNoFunctionalDeliveryTag" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_FUNCTIONAL_DELIVERY_TAG;
				break;
				
			case "testNoPrimaryLogFilename" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_PRIMARYLOG_FILENAME;
				break;
				
			case "testNoPrimaryLogRetention" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_PRIMARYLOG_RETENTION;
				break;
				
			case "testNoPrimaryLogMaxSize" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_PRIMARYLOG_MAX_SIZE;
				break;
				
			case "testNoPrimaryLogFormat" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_PRIMARYLOG_FORMAT;
				break;
				
			case "testNoSecondaryLogFilename" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_SECONDARYLOG_FILENAME;
				break;
				
			case "testNoSecondaryLogRetention" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_SECONDARYLOG_RETENTION;
				break;
				
			case "testNoSecondaryLogMaxSize" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_SECONDARYLOG_MAX_SIZE;
				break;
				
			case "testNoSecondaryLogFormat" :
				intSysResponseResourcePath = MOCK_XML_INTSYS_SECONDARYLOG_FORMAT;
				break;
				
			default :
				intSysResponseResourcePath = MOCK_XML_INTSYS_COMPLETE;
				break;
		}
		
		setIntegrationSystem(intSysResponseResourcePath);
	}

	@Override
	protected void setupIntegrationLaunchEvent() throws Exception {
		String launchEventResourcePath;
		
		switch (getName()) {
			case "testNoDebug" :
				launchEventResourcePath = MOCK_XML_LP_DEBUG;
				break;
				
			case "testNoValidation" :
				launchEventResourcePath = MOCK_XML_LP_VALIDATION;
				break;
				
			case "testNoDebugWID" :
				launchEventResourcePath = MOCK_XML_LP_DEBUG_WID;
				break;
				
			case "testListenerService" :
				launchEventResourcePath = MOCK_XML_LP_LISTENER;
				break;
				
			default :
				launchEventResourcePath = MOCK_XML_LP_COMPLETE;
				break;
		}
		
		setIntegrationLaunchEvent(launchEventResourcePath);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="Initialize_109")
	public void testFullConfiguration() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testNoGeneralService() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testNoFunctionalService() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testNoPrimayLogService() throws Exception {
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to complete configuration of the primary log.";
		errorDetails = "The 'int006 Greenhouse Inbound Attribute Map Service - Primary Logging' service is required but appears to have been removed.  Please restore this service, as well as the attributes it contained, on the Workday-In component.  The service is a core part of the SSK Framework and required for correct behavior.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoSecondayLogService() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testNoGeneralRetention() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testNoFunctionalRetrievalTag() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testNoFunctionalOutputFilename() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testNoFunctionalDeliveryTag() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testNoPrimaryLogFilename() throws Exception { 
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to complete configuration of the primary log.";
		errorDetails = "The 'Log File Name (Exclude File Extension)' on the 'int006 Greenhouse Inbound Attribute Map Service - Primary Logging' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The service and its attributes are a core part of the SSK Framework and required for correct behavior.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoPrimaryLogRetention() throws Exception {
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to complete configuration of the primary log.";
		errorDetails = "The 'Log Retention in Days' on the 'int006 Greenhouse Inbound Attribute Map Service - Primary Logging' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The service and its attributes are a core part of the SSK Framework and required for correct behavior.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoPrimaryLogMaxSize() throws Exception {
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to complete configuration of the primary log.";
		errorDetails = "The 'Max Entries per Log File' on the 'int006 Greenhouse Inbound Attribute Map Service - Primary Logging' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The service and its attributes are a core part of the SSK Framework and required for correct behavior.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoPrimaryLogFormat() throws Exception {
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to complete configuration of the primary log.";
		errorDetails = "The 'Cloud Log Output File Type' on the 'int006 Greenhouse Inbound Attribute Map Service - Primary Logging' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The service and its attributes are a core part of the SSK Framework and required for correct behavior.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoSecondaryLogFilename() throws Exception {
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to complete configuration of the secondary log.";
		errorDetails = "The 'Log File Name (Exclude File Extension)' on the 'int006 Greenhouse Inbound Attribute Map Service - Secondary Logging' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the 'int006 Greenhouse Inbound Attribute Map Service - Secondary Logging' service is in use.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoSecondaryLogRetention() throws Exception {
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to complete configuration of the secondary log.";
		errorDetails = "The 'Log Retention in Days' on the 'int006 Greenhouse Inbound Attribute Map Service - Secondary Logging' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the 'int006 Greenhouse Inbound Attribute Map Service - Secondary Logging' service is in use.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoSecondaryLogMaxSize() throws Exception {
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to complete configuration of the secondary log.";
		errorDetails = "The 'Max Entries per Log File' on the 'int006 Greenhouse Inbound Attribute Map Service - Secondary Logging' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the 'int006 Greenhouse Inbound Attribute Map Service - Secondary Logging' service is in use.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoSecondaryLogFormat() throws Exception {
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to complete configuration of the secondary log.";
		errorDetails = "The 'Cloud Log Output File Type' on the 'int006 Greenhouse Inbound Attribute Map Service - Secondary Logging' service is required but appears to have been removed.  Please restore this attribute to the service on the Workday-In component.  The attribute is a core part of the SSK Framework and required for correct behavior when the 'int006 Greenhouse Inbound Attribute Map Service - Secondary Logging' service is in use.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoDebug() throws Exception {
		expectHandledException();

		errorMessage = "Custom step id=InitializeCoreSSK, ref=SSK109InitializeCore - Error while invoking bean, reason: Unable to configure the 'sskIsDebugMode' property.";
		errorDetails = "The 'Run with Debug Logging' launch parameter is required but appears to have been removed.  Please restore this launch parameter on the Workday-In.  The launch parameter is a core part of the SSK Framework and required for correct behavior.";
	}

	@UnitTest(startComponent="Initialize_109")
	public void testNoValidation() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testNoDebugWID() throws Exception { }

	@UnitTest(startComponent="Initialize_109")
	public void testListenerService() throws Exception {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE, false);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {
		if (testsExpectingHandledExceptions.contains(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "count of calls to PIM_InitializationFailed_109"), 1, mockTracker.getCallCount("PIM_InitializationFailed_109"));
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error message"), errorMessage, ctx.getException().getMessage());
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error details"), errorDetails, ctx.getErrorMessage());
		} else {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "count of calls to PIM_InitializationFailed_109"), 0, mockTracker.getCallCount("PIM_InitializationFailed_109"));

			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_SSK_VERSION), ctx.getProperty(PROP_GLOBAL_SSK_VERSION));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SSK_VERSION), VALUE_SSK_VERSION, (String)ctx.getProperty(PROP_GLOBAL_SSK_VERSION));

			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_STRING_DEDUPE), ctx.getProperty(PROP_GLOBAL_STRING_DEDUPE));
			assertEquals(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_STRING_DEDUPE), StringDeduplicator.class, ctx.getProperty(PROP_GLOBAL_STRING_DEDUPE).getClass());

			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_STATIC_ANALYSIS_EXEMPTIONS), ctx.getProperty(PROP_GLOBAL_STATIC_ANALYSIS_EXEMPTIONS));
			assertEquals(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_STATIC_ANALYSIS_EXEMPTIONS), ArrayList.class, ctx.getProperty(PROP_GLOBAL_STATIC_ANALYSIS_EXEMPTIONS).getClass());

			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_DEBUG_TARGET_TYPE), ctx.getProperty(PROP_GLOBAL_DEBUG_TARGET_TYPE));
			assertEquals(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_DEBUG_TARGET_TYPE), HashSet.class, ctx.getProperty(PROP_GLOBAL_DEBUG_TARGET_TYPE).getClass());

			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_DEBUG_TARGET_NAME), ctx.getProperty(PROP_GLOBAL_DEBUG_TARGET_NAME));
			assertEquals(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_DEBUG_TARGET_NAME), HashSet.class, ctx.getProperty(PROP_GLOBAL_DEBUG_TARGET_NAME).getClass());

			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_DEBUG_ARCHIVE_FILENAME), ctx.getProperty(PROP_GLOBAL_DEBUG_ARCHIVE_FILENAME));
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_DEBUG_ARCHIVE_FILENAME), StringUtils.startsWith((String)ctx.getProperty(PROP_GLOBAL_DEBUG_ARCHIVE_FILENAME), VALUE_PARAMETER_DEBUG_ARCHIVE_FILENAME));
			
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_FILENAME), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_FILENAME) instanceof String);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_FILENAME), VALUE_PRIMARYLOG_FILE, (String)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_FILENAME));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_EXPIRES), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_EXPIRES) instanceof String);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_EXPIRES), VALUE_LOG_EXPIRATION, (String)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_EXPIRES));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT) instanceof String);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT), VALUE_LOGFILE_FORMAT_HTML, (String)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_MAX_COUNT_PER_FILE), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_MAX_COUNT_PER_FILE) instanceof Integer);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_MAX_COUNT_PER_FILE), VALUE_LOG_MAX_ENTRIES, (Integer)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_MAX_COUNT_PER_FILE));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE) instanceof Integer);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE), 0, (int)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG) instanceof Integer);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG), 0, (int)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_INFO), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_INFO) instanceof Integer);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_INFO), 0, (int)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_INFO));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_WARN), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_WARN) instanceof Integer);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_WARN), 0, (int)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_WARN));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR) instanceof Integer);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), 0, (int)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_FATAL), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_FATAL) instanceof Integer);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_FATAL), 0, (int)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_FATAL));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL) instanceof Integer);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL), 0, (int)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL));

			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_FILES_STORED), ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_FILES_STORED) instanceof Integer);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_FILES_STORED), 0, (int)ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_FILES_STORED));

			if ("testFullConfiguration".equalsIgnoreCase(getName()) ||
					"testNoValidation".equalsIgnoreCase(getName()) ||
					"testNoDebugWID".equalsIgnoreCase(getName()) ||
					StringUtils.startsWith(getName(), "testNoGeneral") ||
					StringUtils.startsWith(getName(), "testNoFunctional")) {
				
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_IS_ENABLED), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_IS_ENABLED) instanceof Boolean);
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_IS_ENABLED), (Boolean)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_IS_ENABLED));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_FILENAME), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_FILENAME) instanceof String);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_FILENAME), VALUE_SECONDARYLOG_FILE, (String)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_FILENAME));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_EXPIRES), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_EXPIRES) instanceof String);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_EXPIRES), VALUE_LOG_EXPIRATION, (String)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_EXPIRES));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT) instanceof String);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT), VALUE_LOGFILE_FORMAT_CSV, (String)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_MAX_COUNT_PER_FILE), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_MAX_COUNT_PER_FILE) instanceof Integer);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_MAX_COUNT_PER_FILE), VALUE_LOG_MAX_ENTRIES, (Integer)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_MAX_COUNT_PER_FILE));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE) instanceof Integer);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE), 0, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG) instanceof Integer);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG), 0, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_INFO), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_INFO) instanceof Integer);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_INFO), 0, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_INFO));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_WARN), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_WARN) instanceof Integer);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_WARN), 0, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_WARN));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR) instanceof Integer);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR), 0, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_FATAL), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_FATAL) instanceof Integer);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_FATAL), 0, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_FATAL));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL) instanceof Integer);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL), 0, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL));
	
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_FILES_STORED), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_FILES_STORED) instanceof Integer);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_FILES_STORED), 0, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_FILES_STORED));
			} else if ("testListenerService".equalsIgnoreCase(getName())) {
				assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE, MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE), (boolean)ctx.getProperty(MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE));
			}
		}
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@AtComponent(id="Call_Debug_Core_109")
	public Action mockAdvanceToNext() throws Exception {
		return new StandardAction(Action.Type.terminate);
	}
}
