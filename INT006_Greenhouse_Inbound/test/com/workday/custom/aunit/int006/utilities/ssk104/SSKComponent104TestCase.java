package com.workday.custom.aunit.int006.utilities.ssk104;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.w3c.dom.Document;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;
import com.workday.custom.int006.ssk142.CloudLogMessage;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent104TestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_SOAP_RESULTS = "globalSoapResults";
	
	public static final String VAR_LOCAL_C2P_RESULTS = "c2pAggregatedResult104";

	public static final String PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION = "inWebServiceApplication";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inBuildRequestDataLocation";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION_ID = "inBuildRequestDataLocationId";
	public static final String PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH = "inBuildRequestPathToXsltFile";
	public static final String PROP_PARAMETER_IN_RESULT_XPATH = "inXpathToResultsToAggregate";
	public static final String PROP_PARAMETER_IN_IS_PARALLEL = "inIsParallelPagedGet";
	public static final String PROP_PARAMETER_IN_PARALLEL_ENDPOINT = "inParallelPageProcessorEndpoint";
	public static final String PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION = "inParallelSuppressAggregation";
	
	public static final String PROP_LOCAL_WWS_CACHE_EFFECTIVE = "localIsQueryCacheEffective";
	public static final String PROP_LOCAL_WWS_CACHE_ENTRY = "localIsQueryCacheEntryDate";
	public static final String PROP_LOCAL_QUALIFIER_TYPED = "localIsQualifierTyped";
	public static final String PROP_LOCAL_VALUE_DEFINED = "localIsValueDefined";
	public static final String PROP_LOCAL_VALUE_TYPED = "localIsValueTyped";
	public static final String PROP_LOCAL_PARALLEL_LOG = "localParallelLog143";

	public static final String VALUE_WS_APP_HUMAN_RESOURCES = "Human_Resources";
	public static final String VALUE_WS_APP_INTEGRATIONS = "Integrations";
	public static final String VALUE_PAGED_GET_RESPONSE_GET_WORKERS = "/env:Envelope/env:Body/wd:Get_Workers_Response/wd:Response_Data/wd:Worker";
	public static final String VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL = "/env:Envelope/env:Body/wd:Get_Event_Details_Response/wd:Response_Data/wd:Event";
	public static final String VALUE_DYNAMIC_ENDPOINT = "vm://int006_Greenhouse_Inbound/StaticCodeAnalysis";
	public static final String VALUE_DATA_LOCATION_MESSAGE = "message";
	public static final String VALUE_DATA_LOCATION_VARIABLE = "variable";
	public static final String VALUE_DATA_LOCATION_PROPERTY = "property";

	public static final String MOCK_MESSAGE_SSK104_MAP_3WORKERS = "test/ssk/ssk104/SSK104_XML_inMapVariableName_3Workers.xml";
	public static final String MOCK_MESSAGE_SSK104_VARIABLE_MAP_NONXML = "test/ssk/ssk104/SSK104_TXT_inMapVariableName_NonXml.txt";
	public static final String MOCK_MESSAGE_SSK104_VARIABLE_MAP_EMPTY_FILE = "test/ssk/ssk104/SSK104_XML_inMapVariableName_EmptyFile.xml";
	public static final String MOCK_MESSAGE_SSK104_VARIABLE_MAP_EMPTY_ROOT = "test/ssk/ssk104/SSK104_XML_inMapVariableName_EmptyRoot.xml";
	public static final String MOCK_MESSAGE_SSK104_VARS_PROCESS_WIDS = "test/ssk/ssk104/SSK104_XML_Process_WIDs.xml";
	public static final String MOCK_MESSAGE_SSK104_GET_EVENT_DETAIL_PAGED_RESULTS = "test/ssk/ssk104/SSK104_XML_Get_Event_Detail_ResponsePagedGet.xml";
	public static final String MOCK_MESSAGE_SSK104_PAGEDGET_SOAP_REQUEST = "test/ssk/ssk104/SSK104_SOAP_Get_Event_Detail_Request.xml";
	public static final String MOCK_MESSAGE_SSK104_PAGEDGET_SOAP_REQUEST_EFFECTIVE = "test/ssk/ssk104/SSK104_SOAP_Get_Event_Detail_Request2.xml";
	public static final String MOCK_MESSAGE_SSK104_PAGEDGET_SOAP_REQUEST_ENTRY = "test/ssk/ssk104/SSK104_SOAP_Get_Event_Detail_Request3.xml";
	public static final String MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT = "test/ssk/ssk104/SSK104_XML_AggregatorResult.xml";

	public static final String MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES = "test/ssk/ssk104/SSK104_GetWorkersRequest_MultipleInstances.xsl";
	public static final String MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_SINGLE_INSTANCE = "test/ssk/ssk104/SSK104_GetWorkersRequest_SingleInstance.xsl";

	int expectedSoapSerialCalls = 0;
	int expectedSoapParallelCalls = 0;
	
	boolean isEvaluateMultipleInstances = false;
	boolean isEvaluateSingleInstance = false;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
			
		List<String> componentsToTrack = new ArrayList<String>();
		componentsToTrack.add("Call_HandleError_Validation_Error_104");
		componentsToTrack.add("Call_HandleError_Transform_Error_104");
		componentsToTrack.add("CallSoapParallel_104");
		componentsToTrack.add("CallSoapSerial_104");

		mockTracker.addComponentTracking(componentsToTrack);

		setupGlobalInitialization();
		
		ctx.setProperty(PROP_PARAMETER_IN_API_VERSION, VALUE_API_VERSION);
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_HUMAN_RESOURCES);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_PRIMARY);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	//////////Message
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testMessageWithXsltSerial() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setMessagePart(0, MOCK_MESSAGE_SSK104_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 1;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testMessageWithXsltParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setMessagePart(0, MOCK_MESSAGE_SSK104_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, true);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 1;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testValidationError10400() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setMessagePart(1, MOCK_MESSAGE_SSK104_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testValidationError10401() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setMessagePart(0, MOCK_MESSAGE_SSK104_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testMessageLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setMessagePart(0, MOCK_MESSAGE_SSK104_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}

	//////////Variable
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testVariableWithXsltSerial() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setVariable("myVarData", MOCK_MESSAGE_SSK104_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 1;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testVariableWithXsltParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setVariable("myVarData", MOCK_MESSAGE_SSK104_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, true);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 1;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testValidationError10402() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK104_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testValidationError10403() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setVariable("myVarData", MOCK_MESSAGE_SSK104_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testVariableLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK104_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testVariableLocalInValidationErrorDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setVariable("myVarData", MOCK_MESSAGE_SSK104_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testVariableLocalInValidationErrorDataLocationAndDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK104_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	//////////Property

	@UnitTest(startComponent="CallSoapPaged")
	public void testPropertyWithXsltSerial() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_SINGLE_INSTANCE));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);

		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 1;
		expectedSoapParallelCalls = 0;
		isEvaluateSingleInstance = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testPropertyWithXsltParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_SINGLE_INSTANCE));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, true);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 1;
		isEvaluateSingleInstance = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testValidationError10404() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testValidationError10405NotMap() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);
		
		List<String> prompts = new ArrayList<String>();
		prompts.add("typeFromMap");
		prompts.add("valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testValidationError10405EmptyMap() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);
		
		Map<String,String> prompts = new HashMap<String,String>();
		ctx.setProperty("mapSingleLevel", prompts);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testPropertyLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);

		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testPropertyLocalInValidationErrorDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testPropertyLocalInValidationErrorDataLocationAndDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK104_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_WORKERS);
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
		isEvaluateMultipleInstances = true;
	}
	
	//////////General
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testPagedGetQueryCacheOK() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);

		setMessagePart(0, MOCK_MESSAGE_SSK104_PAGEDGET_SOAP_REQUEST, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 1;
		expectedSoapParallelCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testQueryCacheValidationError10408() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);

		setMessagePart(0, MOCK_MESSAGE_SSK104_PAGEDGET_SOAP_REQUEST_ENTRY, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoapPaged")
	public void testQueryCacheValidationError10409() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);

		setMessagePart(0, MOCK_MESSAGE_SSK104_PAGEDGET_SOAP_REQUEST_EFFECTIVE, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);

		expectedSoapSerialCalls = 0;
		expectedSoapParallelCalls = 0;
	}
	
	//Cannot run these tests because of some aUnit execution logistics.  The tests begin on a conditional local-out.
	//When inIsParallelPagedGet is false, then the local-out should be skipped.  However, aUnit is running it because
	//it's the startComponent.
//	@UnitTest(startComponent="Call_ParallelPostProcess_104")
//	public void testSyncReturnToMessageSerial() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
//		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);
//
//		setMessagePart(0, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML);
//		
//		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);
//	}
	
//	@UnitTest(startComponent="Call_ParallelPostProcess_104")
//	public void testSyncReturnToVariableSerial() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
//		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);
//		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_SOAP_RESULTS);
//
//		setVariable(VAR_GLOBAL_SOAP_RESULTS, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML);
//		
//		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, false);
//	}

	@UnitTest(startComponent="Call_ParallelPostProcess_104")
	public void testSyncReturnToMessageParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);

		setVariable(VAR_LOCAL_C2P_RESULTS, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, false);

		@SuppressWarnings("rawtypes")
		ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, queue);
	}
	
	@UnitTest(startComponent="Call_ParallelPostProcess_104")
	public void testSyncReturnToVariableParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_SOAP_RESULTS);

		setVariable(VAR_LOCAL_C2P_RESULTS, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, false);

		@SuppressWarnings("rawtypes")
		ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, queue);
	}
	
	@UnitTest(startComponent="Call_ParallelPostProcess_104")
	public void testSyncSuppressAggregationParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);

		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, true);

		@SuppressWarnings("rawtypes")
		ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, queue);
	}

	@UnitTest(startComponent="Call_Finally_104")
	public void testSyncReturnToMessageParallelFlushThreadLogs() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);

		CloudLogMessage message = new CloudLogMessage();
		message.setSummary("Test Message");
		message.setDetail("Test Message Detail");
		message.setLevel("Info");

		ConcurrentLinkedQueue<CloudLogMessage> queue = new ConcurrentLinkedQueue<CloudLogMessage>();
		queue.add(message);
		
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, queue);

		setVariable(VAR_LOCAL_C2P_RESULTS, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, false);
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

			verifyGlobalPropertyNotModified(propertyName, null);
		}
		
		assertProperPagedGetCallForTest();
		
		if (getName().startsWith("testValidationError104")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "1", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
			
			assertEquals(String.format(MESSAGE_EXPECTED_STEP_NOT_CALLED, "Call_HandleError_Validation_Error_104"), 1, mockTracker.getCallCount("Call_HandleError_Validation_Error_104"));
		}
		
		if ("testQueryCacheValidationError10408".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML(
					"ERROR", 
					"WWS Query Cache will be voided", 
					"Both the Response_Filter/As_Of_Effective_Date and the Response_Filter/As_Of_Entry_DateTime must be set to avoid the tenanted PagedGet query cache from being cleared and recalculated on each page query.  This does not affect functionality, but it does undermine performance and could affect other integrations making PagedGet calls as well.",
					null,
					"ValidateQueryCache@ValidatedAndSetHeaders_104",
					"10408",
					null,
					"props.localIsQueryCacheEffective = false\\nprops.localIsQueryCacheEntryDate = true");
		} else if ("testQueryCacheValidationError10409".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML(
					"ERROR", 
					"WWS Query Cache will be voided", 
					"Both the Response_Filter/As_Of_Effective_Date and the Response_Filter/As_Of_Entry_DateTime must be set to avoid the tenanted PagedGet query cache from being cleared and recalculated on each page query.  This does not affect functionality, but it does undermine performance and could affect other integrations making PagedGet calls as well.",
					null,
					"ValidateQueryCache@ValidatedAndSetHeaders_104",
					"10409",
					null,
					"props.localIsQueryCacheEffective = true\\nprops.localIsQueryCacheEntryDate = false");
		} else if ("testPagedGetQueryCacheOK".equalsIgnoreCase(getName())) {
			assertNoPrimaryCloudLogEntryHTML("WWS Query Cache will be voided");
		}
		
		if ("testSyncReturnToMessageParallelFlushThreadLogs".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML(
					"INFO", 
					"Unlogged thread messages found", 
					"All messages from child threads in parallel PagedGet were not logged.  Messages will be flushed to the log.  This may result in out-of-order message sequences.",
					null,
					null,
					null,
					"1",
					null);

			assertPrimaryCloudLogEntryHTML(
					"INFO", 
					"Test Message", 
					"Test Message Detail",
					null,
					null,
					null,
					null,
					null);
		} else {
			assertNoPrimaryCloudLogEntryHTML("Unlogged thread messages found");
		}
	}

	private void assertProperPagedGetCallForTest() {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "number of calls to CallSoapSerial_104"), expectedSoapSerialCalls, mockTracker.getCallCount("CallSoapSerial_104"));
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "number of calls to CallSoapParallel_104"), expectedSoapParallelCalls, mockTracker.getCallCount("CallSoapParallel_104"));
	}
	
	@AssertAfter(id="Call_HandleError_Validation_Error_104")
	public void updateInitializeError() throws Exception {
		mockTracker.incrementCallCount("Call_HandleError_Validation_Error_104");
	}
	
	@AssertAfter(id="Call_HandleError_Transform_Error_104")
	public void updateTransformError() throws Exception {
		mockTracker.incrementCallCount("Call_HandleError_Transform_Error_104");
	}
	
	@AssertAfter(id="ValidateAndSetHeaders_104", step="SetHeaders")
	public void verifySoapRequest() throws Exception {
		int expectedValue = -1;
		String xpath = null;
		Document doc = getMessageAsDocument();

		if (isEvaluateMultipleInstances) {
			expectedValue = 3;
			xpath = "count(/*:Envelope/*:Body/*:Get_Workers_Request/*:Request_References/*:Worker_Reference[*:ID[@*:type = \"typeFromMap1\"] = \"valueFromMap1\" or *:ID[@*:type = \"typeFromMap2\"] = \"valueFromMap2\" or *:ID[@*:type = \"typeFromMap3\"] = \"valueFromMap3\"])";
		} else if (isEvaluateSingleInstance) {
			expectedValue = 1;
			xpath = "count(/*:Envelope/*:Body/*:Get_Workers_Request/*:Request_References/*:Worker_Reference[*:ID[@*:type = \"typeFromMap\"] = \"valueFromMap\"])";
		}
		
		if (doc == null &&
				!("testValidationError10400".equalsIgnoreCase(getName()) ||
						"testValidationError10401".equalsIgnoreCase(getName()) ||
						"testValidationError10402".equalsIgnoreCase(getName()) ||
						"testValidationError10403".equalsIgnoreCase(getName()) ||
						"testValidationError10404".equalsIgnoreCase(getName()) ||
						"testValidationError10405".equalsIgnoreCase(getName()))) {
			fail("The Mediation Message root was not available or could not be parsed to a DOM.  This was not expected for this test case and should be investigated.");
		}
		
		if (xpath != null) {
			assertEqualsXPath(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, expectedValue + " xpath matches"), String.valueOf(expectedValue), xpath, doc);
		}
	}
	
	@AssertAfter(id="Call_Finally_104")
	public void verifyPagedGetAggregation() throws Exception {
		if ("testSyncReturnToMessageSerial".equalsIgnoreCase(getName()) ||
				"testSyncReturnToMessageParallel".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT), 
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML));
		} else if ("testSyncReturnToVariableSerial".equalsIgnoreCase(getName()) ||
				"testSyncReturnToVariableParallel".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_GLOBAL_SOAP_RESULTS), 
					compareAgainstVariable(VAR_GLOBAL_SOAP_RESULTS, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML));
		}
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@AtComponent(id="CallSoapParallel_104")
	public Action handleCallSoapParallel() throws Exception {
		mockTracker.incrementCallCount("CallSoapParallel_104");
		return new StandardAction(Action.Type.terminate);
	}

	@AtComponent(id="CallSoapSerial_104")
	public Action handleCallSoapSerial() throws Exception {
		mockTracker.incrementCallCount("CallSoapSerial_104");
		return new StandardAction(Action.Type.terminate);
	}

}
