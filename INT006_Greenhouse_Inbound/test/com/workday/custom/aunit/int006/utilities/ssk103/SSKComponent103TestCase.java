package com.workday.custom.aunit.int006.utilities.ssk103;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.capeclear.xml.utils.DOMUtils;
import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent103TestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_SOAP_RESULTS = "globalSoapResults";

	public static final String PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION = "inWebServiceApplication";
	public static final String PROP_PARAMETER_IN_API_VERSION = "inApiVersion";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inBuildRequestDataLocation";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION_ID = "inBuildRequestDataLocationId";
	public static final String PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH = "inBuildRequestPathToXsltFile";
	public static final String PROP_PARAMETER_IN_VALIDATE_ONLY = "inValidateOnly";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	public static final String PROP_PARAMETER_IN_IS_CHILD_THREAD = "inIsChildThreadContext";

	public static final String PROP_LOCAL_QUALIFIER_TYPED = "localIsQualifierTyped";
	public static final String PROP_LOCAL_VALUE_DEFINED = "localIsValueDefined";
	public static final String PROP_LOCAL_VALUE_TYPED = "localIsValueTyped";

	public static final String VALUE_WS_APP_HUMAN_RESOURCES = "Human_Resources";
	public static final String VALUE_DATA_LOCATION_MESSAGE = "message";
	public static final String VALUE_DATA_LOCATION_VARIABLE = "variable";
	public static final String VALUE_DATA_LOCATION_PROPERTY = "property";

	public static final String MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS = "test/ssk/ssk103/SSK103_XML_inMapVariableName_3Workers.xml";
	public static final String MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML = "test/ssk/ssk103/SSK103_TXT_inMapVariableName_NonXml.txt";
	public static final String MOCK_MESSAGE_SSK103_VARIABLE_MAP_EMPTY_FILE = "test/ssk/ssk103/SSK103_XML_inMapVariableName_EmptyFile.xml";
	public static final String MOCK_MESSAGE_SSK103_VARIABLE_MAP_EMPTY_ROOT = "test/ssk/ssk103/SSK103_XML_inMapVariableName_EmptyRoot.xml";
	public static final String MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE1 = "test/ssk/ssk103/SSK103_SOAP_Get_Workers_Response1.xml";
	public static final String MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3 = "test/ssk/ssk103/SSK103_SOAP_Get_Workers_Response3.xml";
	public static final String MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES = "test/ssk/ssk103/SSK103_GetWorkersRequest_MultipleInstances.xsl";
	public static final String MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_SINGLE_INSTANCE = "test/ssk/ssk103/SSK103_GetWorkersRequest_SingleInstance.xsl";
	public static final String MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_UNPARAMETERIZED = "test/ssk/ssk103/SSK103_GetWorkersRequest_Unparameterized.xsl";
	public static final String MOCK_REQUEST_SSK103_SOAP_GET_WORKERS = "test/ssk/ssk103/SSK103_SOAP_Get_Workers_Request.xml";

	int expectedSoapCalls = 0;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
			
		List<String> componentsToTrack = new ArrayList<String>();
		componentsToTrack.add("Call_HandleError_Validation_Error_103");
		componentsToTrack.add("Call_HandleError_Transform_Error_103");

		mockTracker.addComponentTracking(componentsToTrack);
		
		ctx.setProperty(PROP_PARAMETER_IN_API_VERSION, VALUE_API_VERSION);
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_HUMAN_RESOURCES);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_SOAP_RESULTS);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_PRIMARY);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);

		setupGlobalInitialization();
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10300ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10300ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10301ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setMessagePart(0, MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10301ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setMessagePart(0, MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setMessagePart(0, MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
	}

	@UnitTest(startComponent="CallSoap")
	public void testValidationError10302ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10302ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10303ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10303ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testVariableLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testVariableLocalInValidationErrorDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testVariableLocalInValidationErrorDataLocationAndDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10304ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10304ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10305NotMap() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		
		List<String> prompts = new ArrayList<String>();
		prompts.add("typeFromMap");
		prompts.add("valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10305EmptyMap() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		
		Map<String,String> prompts = new HashMap<String,String>();
		ctx.setProperty("mapSingleLevel", prompts);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testPropertyLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testPropertyLocalInValidationErrorDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testPropertyLocalInValidationErrorDataLocationAndDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testSingleLevelPropertyMap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_SINGLE_INSTANCE));

		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");

		ctx.setProperty("mapSingleLevel", prompts);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMultiLevelVariableMap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("mapMultiLevel", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapMultiLevel");
	}
		
	@UnitTest(startComponent="CallSoap")
	public void testEmptyVariableMap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapMultiLevel");

		setVariable("mapMultiLevel", MOCK_MESSAGE_SSK103_VARIABLE_MAP_EMPTY_ROOT, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithErrorWithDebug() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
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
		Iterator<?> propertyNameIterator = ctx.getPropertyNames();

		while (propertyNameIterator.hasNext()) {
			String propertyName = String.valueOf(propertyNameIterator.next());
			
			if (!(boolean)ctx.getProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD)) {
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_QUALIFIER_TYPED), PROP_LOCAL_QUALIFIER_TYPED.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_VALUE_DEFINED), PROP_LOCAL_VALUE_DEFINED.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_VALUE_TYPED), PROP_LOCAL_VALUE_TYPED.equalsIgnoreCase(propertyName));
			}

			verifyGlobalPropertyNotModified(propertyName, null);
		}
		
		if (getName().startsWith("testValidationError103") && getName().endsWith("ParentThread")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "1", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
			
			assertEquals(String.format(MESSAGE_EXPECTED_STEP_NOT_CALLED, "Call_HandleError_Validation_Error_103"), 1, mockTracker.getCallCount("Call_HandleError_Validation_Error_103"));
		} else if (getName().startsWith("testValidationError103") && getName().endsWith("ChildThread")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "0", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
			
			assertEquals(String.format(MESSAGE_EXPECTED_STEP_NOT_CALLED, "Call_HandleError_Validation_Error_103"), 1, mockTracker.getCallCount("Call_HandleError_Validation_Error_103"));
			
			@SuppressWarnings("rawtypes") 
			ConcurrentLinkedQueue queue = ctx.containsProperty(PROP_LOCAL_PARALLEL_LOG) ? (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG) : null;

			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_PARALLEL_LOG), queue);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "number of messages on the threaded queue"), 1, queue.size());
		} else if (getName().startsWith("testValidationError103")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "1", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
			
			assertEquals(String.format(MESSAGE_EXPECTED_STEP_NOT_CALLED, "Call_HandleError_Validation_Error_103"), 1, mockTracker.getCallCount("Call_HandleError_Validation_Error_103"));
		} else if (getName().equalsIgnoreCase("testMessageRootRequestWithErrorWithDebug")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "1", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
			
			assertPrimaryCloudLogEntryHTML("ERROR", "Human_Resources application HTTP request error", "An error occurred while executing a SOAP request for the Human_Resources application.", null, "CallSoap_103", "-1", null, "RuntimeException thrown by the Test Plan");
		} else if (!getName().contains("LocalInValidationError")) {
			assertTrue(String.format(MESSAGE_VARIABLE_NOT_ADDED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
		}
	}
	
	@AssertAfter(id="Call_HandleError_Validation_Error_103")
	public void updateInitializeError() throws Exception {
		mockTracker.incrementCallCount("Call_HandleError_Validation_Error_103");
	}
	
	@AssertAfter(id="Call_HandleError_Transform_Error_103")
	public void updateTransformError() throws Exception {
		mockTracker.incrementCallCount("Call_HandleError_Transform_Error_103");
	}
	
	@AssertAfter(id="Transform_103", step="WriteSoap")
	public void verifySoapRequest() throws Exception {
		int expectedValue = 1;
		String xpath = null;
		Document doc = DOMUtils.parseToDOM(new InputSource(new InputStreamReader(getMessageInputStream(), "UTF-8")));

		switch(getName()) {
			case "testSingleLevelPropertyMap" :
				xpath = "count(/*:Envelope/*:Body/*:Get_Workers_Request/*:Request_References/*:Worker_Reference[*:ID[@*:type = \"typeFromMap\"] = \"valueFromMap\"])";
				break;
				
			case "testMultiLevelVariableMap" :
				expectedValue = 3;
				xpath = "count(/*:Envelope/*:Body/*:Get_Workers_Request/*:Request_References/*:Worker_Reference[*:ID[@*:type = \"typeFromMap1\"] = \"valueFromMap1\" or *:ID[@*:type = \"typeFromMap2\"] = \"valueFromMap2\" or *:ID[@*:type = \"typeFromMap3\"] = \"valueFromMap3\"])";
				break;
				
			case "testEmptyVariableMap" :
				expectedValue = 0;
				xpath = "count(/*:Envelope/*:Body/*:Get_Workers_Request/*:Request_References/*:Worker_Reference[*:ID[@*:type = \"typeFromMap\"] = \"valueFromMap\"])";
				break;
				
			default :
				break;
		}
		
		if (xpath != null) {
			assertEqualsXPath(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, expectedValue + " xpath matches"), String.valueOf(expectedValue), xpath, doc);
		}
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@AtComponent(id="CallSoap_103")
	public Action handleCallSoap() throws Exception {
		if ("testMessageRootRequestWithErrorWithDebug".equalsIgnoreCase(getName())) {
			return new StandardAction(Action.Type.mock_exception);
		}
		
		switch (getName()) {
			case "testSingleLevelPropertyMap" :
				setMessagePart(0, MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE1, CONTENT_TYPE_TEXT_XML);
				break;
			
			case "testMultiLevelVariableMap" :
				setMessagePart(0, MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3, CONTENT_TYPE_TEXT_XML);
				break;
			
			case "testEmptyVariableMap" :
				setMessagePart(0, MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3, CONTENT_TYPE_TEXT_XML);
				break;
				
			case "testMessageRootRequestWithDebug" :
				setMessagePart(0, MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3, CONTENT_TYPE_TEXT_XML);
				break;
			
			default :
				break;
		}

		return new StandardAction(Action.Type.mock);
	}

}
