package com.workday.custom.aunit.int006.utilities.ssk118;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent118TestCase extends UtilitiesTestCase {
	
	public static final String VAR_LOCAL_SSK118_RESPONSE = "c2pAggregatedResult118";
	public static final String VAR_LOCAL_SSK118_PRE_TRANSFORM = "localPreTransformData118";
	public static final String VAR_LOCAL_SSK118_ORIGINAL_REQUESTS = "localOriginalRequests118";
	public static final String VAR_LOCAL_SSK118_XSLT = "localXslt118";

	public static final String PROP_PARAMETER_IN_API_VERSION = "inApiVersion";
	public static final String PROP_PARAMETER_IN_INTEGRATION_EVENT_WID = "inImportIntegrationEventWID";
	public static final String PROP_PARAMETER_IN_REQUEST_FILENAME = "inRequestFilename";
	public static final String PROP_PARAMETER_IN_VALIDATE_ONLY = "inValidateOnly";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	public static final String PROP_PARAMETER_IN_NO_IMPORT_LOG_LEVEL = "inLogLevelForNoImportProcessesFound";

	public static final String PROP_LOCAL_PROCESS_WID = "localProcessWID";
	public static final String PROP_LOCAL_IS_IMPORT_REQUESTS = "localIsImportRequests";
	public static final String PROP_LOCAL_IS_DO_NOT_MATCH = "localIsDoNotMatch";
	public static final String PROP_LOCAL_NODE_NAME = "localNodeName";
	public static final String PROP_LOCAL_EFFECTIVE_DATE= "localEffectiveAsOf";
	public static final String PROP_LOCAL_ENTRY_DATETIME = "localEntryAsOf";

	public static final String VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED = "cc-retrieve";
	public static final String VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID = "50684860-7622-412a-3014-4f092f012129";
	public static final String VALUE_FILE_VENDOR2_XML_EVENT_DOCUMENT_ENTRYID = "7277537f-4505-4b38-243c-242a3f21220d";
	public static final String VALUE_FILE_VENDOR3_XML_EVENT_DOCUMENT_ENTRYID = "4d5b7233-586b-444b-3d4c-215811332815";
	public static final String VALUE_FILE_EVENT_DOCUMENT_ENTRYID4 = "73567631-5c0e-495c-3d25-26150c6b550c";
	public static final String VALUE_FILE_EVENT_DOCUMENT_ENTRYID5 = "0d540205-1e3b-4d4e-1f3a-053d317d7c7b";

	public static final String MOCK_SSK118_AGGREGATED_GET_IMPORT_PROCESS_MESSAGES = "test/ssk/ssk118/SSK118_localImportProcessMessages118_Results.xml";
	public static final String MOCK_SSK118_AGGREGATED_GET_IMPORT_PROCESS_MESSAGES_NONE = "test/ssk/ssk118/SSK118_localImportProcessMessages118_NoResults.xml";
	public static final String MOCK_FILE_SSK118_REQUESTS_SINGLE_NO_DETAIL = "test/ssk/ssk118/SSK118_1Requests_NoDetail.zip";
	public static final String MOCK_FILE_SSK118_REQUESTS_SINGLE_WITH_DETAIL = "test/ssk/ssk118/SSK118_1Requests_WithDetail.zip";
	public static final String MOCK_FILE_SSK118_REQUESTS_DOUBLE_NO_DETAIL = "test/ssk/ssk118/SSK118_2Requests_NoDetail.zip";
	public static final String MOCK_FILE_SSK118_REQUESTS_DOUBLE_WITH_DETAIL = "test/ssk/ssk118/SSK118_2Requests_WithDetail.zip";
	public static final String MOCK_FILE_SSK118_REQUESTS_NONE = "test/ssk/ssk118/SSK118_NoRequests.zip";
	public static final String MOCK_SSK118_RESPONSE_GET_EVENT_DOCUMENTS = "test/ssk/ssk118/SSK118_SOAP_Get_Event_Documents.xml";
	public static final String MOCK_SSK118_RESPONSE_GET_EVENT_DOCUMENTS_NONE = "test/ssk/ssk118/SSK118_SOAP_Get_Event_Documents_NoFiles.xml";
	public static final String MOCK_SSK118_REQUEST_GET_IMPORT_PROCESS_MESSAGES_964d = "test/ssk/ssk118/SSK118_SOAP_GetImportProcessMessagesRequest_964d.xml";
	public static final String MOCK_SSK118_REQUEST_GET_IMPORT_PROCESS_MESSAGES_daf1 = "test/ssk/ssk118/SSK118_SOAP_GetImportProcessMessagesRequest_daf1.xml";
	public static final String MOCK_SSK118_PRE_TRANSFORM_NO_RESULTS_964d = "test/ssk/ssk118/SSK118_localPreTransformData118_964d_NoResults.xml";
	public static final String MOCK_SSK118_PRE_TRANSFORM_RESULTS_964d = "test/ssk/ssk118/SSK118_localPreTransformData118_964d_Results.xml";
	public static final String MOCK_SSK118_PRE_TRANSFORM_NO_RESULTS_daf1 = "test/ssk/ssk118/SSK118_localPreTransformData118_daf1_NoResults.xml";
	public static final String MOCK_SSK118_PRE_TRANSFORM_RESULTS_daf1 = "test/ssk/ssk118/SSK118_localPreTransformData118_daf1_Results.xml";
	public static final String MOCK_SSK118_MESSAGES_RESULTS_DECORATED_daf1 = "test/ssk/ssk118/SSK118_MessagesToLog_daf1_Results.xml";
	public static final String MOCK_SSK118_MESSAGES_NO_RESULTS_DECORATED_daf1 = "test/ssk/ssk118/SSK118_MessagesToLog_daf1_NoResults.xml";
	public static final String MOCK_SSK118_MESSAGES_RESULTS_DECORATED_964d = "test/ssk/ssk118/SSK118_MessagesToLog_964d_Results.xml";
	public static final String MOCK_SSK118_MESSAGES_NO_RESULTS_DECORATED_964d = "test/ssk/ssk118/SSK118_MessagesToLog_964d_NoResults.xml";

	protected boolean isExecuting964d = false;
	protected boolean isExecutingdaf1 = false;
	protected boolean isExecuted964d = false;
	protected boolean isExecuteddaf1 = false;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		ctx.setProperty(PROP_PARAMETER_IN_API_VERSION, VALUE_API_VERSION);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_PRIMARY);
		ctx.setProperty(PROP_PARAMETER_IN_NO_IMPORT_LOG_LEVEL, "warn");
		
		ctx.setProperty(PROP_PARAMETER_IN_INTEGRATION_EVENT_WID, VALUE_WID_1);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testSingleRequestNoDetails() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_SINGLE_NO_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testSingleRequestWithDetails() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_SINGLE_WITH_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testDoubleRequestNoDetails() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_DOUBLE_NO_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testDoubleRequestWithDetails() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_DOUBLE_WITH_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testNoRequests() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_NONE, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testDoubleRequestNoDetailsNoErrors() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_DOUBLE_NO_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testDoubleRequestWithDetailsNoErrors() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_DOUBLE_WITH_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testSingleRequestNoDetailsDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_SINGLE_NO_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testSingleRequestWithDetailsDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_SINGLE_WITH_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testDoubleRequestNoDetailsDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_DOUBLE_NO_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testDoubleRequestWithDetailsDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_DOUBLE_WITH_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testNoRequestsDebug() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_NONE, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testDoubleRequestNoDetailsNoErrorsIsDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_DOUBLE_NO_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testDoubleRequestWithDetailsNoErrorsIsDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_DOUBLE_WITH_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
	}

	@UnitTest(startComponent="ReportResultsOfCallSoapImport")
	public void testNoFileFound() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK118_REQUESTS_DOUBLE_WITH_DETAIL, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
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
		if (!StringUtils.contains(getName(), "NoErrors") && (!this.testsExpectingHandledExceptions.contains(getName()))) {
			if (isExecuted964d) {
				verifyLoggedMessagesFor964d();
			} else if (isExecuteddaf1) {
				verifyLoggedMessagesFordaf1();
			}
		} else if ("testNoFileFound".equalsIgnoreCase(getName())) {
			verifyLoggedMessageNoImports();
		} else if ("testNoRequests".equalsIgnoreCase(getName()) || "testNoRequestsDebug".equalsIgnoreCase(getName())) {
			verifyLoggedMessageEmptyFile();
		}

		List<String> localPropertiesRemovedInCleanUp = new ArrayList<String>();
		localPropertiesRemovedInCleanUp.add(PROP_LOCAL_PROCESS_WID);
		localPropertiesRemovedInCleanUp.add(PROP_LOCAL_IS_IMPORT_REQUESTS);
		localPropertiesRemovedInCleanUp.add(PROP_LOCAL_IS_DO_NOT_MATCH);
		localPropertiesRemovedInCleanUp.add(PROP_LOCAL_NODE_NAME);
		
		Iterator<?> propertyNameIterator = ctx.getPropertyNames();

		while (propertyNameIterator.hasNext()) {
			String propertyName = String.valueOf(propertyNameIterator.next());

			if (localPropertiesRemovedInCleanUp.contains(propertyName)) {
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED,  propertyName), ctx.containsProperty(propertyName));
			}

			verifyGlobalPropertyNotModified(propertyName, null);
		}
		
		List<String> localVariablesRemovedInCleanUp = new ArrayList<String>();
		localVariablesRemovedInCleanUp.add(VAR_LOCAL_SSK118_RESPONSE);
		localVariablesRemovedInCleanUp.add(VAR_LOCAL_SSK118_PRE_TRANSFORM);
		localVariablesRemovedInCleanUp.add(VAR_LOCAL_SSK118_ORIGINAL_REQUESTS);
		localVariablesRemovedInCleanUp.add(VAR_LOCAL_SSK118_XSLT);
		
		verifyMediationContextVariablesRemoved(localVariablesRemovedInCleanUp);
	}
	
	@AssertAfter(id="LoggingDetail_118", step="InitValues")
	public void maintainTrackingState() throws Exception {
		String processWID = String.valueOf(ctx.getProperty(PROP_LOCAL_PROCESS_WID));
		
		isExecuting964d = VALUE_WID_20.equalsIgnoreCase(processWID);
		isExecutingdaf1 = VALUE_WID_21.equalsIgnoreCase(processWID);
		
		isExecuted964d = isExecuted964d || isExecuting964d;
		isExecuteddaf1 = isExecuteddaf1 || isExecutingdaf1;
	}
	
	@AssertAfter(id="GetImportProcessMessages_InitializeAndFinalize_118")
	public void verifyGetImportProcessMessagesPrepareSoap() throws Exception {
		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put(PROP_LOCAL_EFFECTIVE_DATE, String.valueOf(ctx.getProperty(PROP_LOCAL_EFFECTIVE_DATE)));
		replacements.put(PROP_LOCAL_ENTRY_DATETIME, String.valueOf(ctx.getProperty(PROP_LOCAL_ENTRY_DATETIME)));
		
		if (isExecuting964d) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK118_REQUEST_GET_IMPORT_PROCESS_MESSAGES_964d), 
					dynamicCompareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK118_REQUEST_GET_IMPORT_PROCESS_MESSAGES_964d, CONTENT_TYPE_TEXT_XML, replacements));
		} else if (isExecutingdaf1) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK118_REQUEST_GET_IMPORT_PROCESS_MESSAGES_daf1), 
					dynamicCompareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK118_REQUEST_GET_IMPORT_PROCESS_MESSAGES_daf1, CONTENT_TYPE_TEXT_XML, replacements));
		}
	}
	
	@AssertAfter(id="Call_GetImportProcessMessages_118")
	public void verifyGetImportProcessMessages() throws Exception {
		if (StringUtils.contains(getName(), "NoErrors")) {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_SSK118_RESPONSE, MOCK_SSK118_AGGREGATED_GET_IMPORT_PROCESS_MESSAGES_NONE), 
					compareAgainstVariable(VAR_LOCAL_SSK118_RESPONSE, CONTENT_TYPE_TEXT_XML, MOCK_SSK118_AGGREGATED_GET_IMPORT_PROCESS_MESSAGES_NONE, CONTENT_TYPE_TEXT_XML));
		} else {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_SSK118_RESPONSE, MOCK_SSK118_AGGREGATED_GET_IMPORT_PROCESS_MESSAGES), 
					compareAgainstVariable(VAR_LOCAL_SSK118_RESPONSE, CONTENT_TYPE_TEXT_XML, MOCK_SSK118_AGGREGATED_GET_IMPORT_PROCESS_MESSAGES, CONTENT_TYPE_TEXT_XML));
		}
	}
	
	@AssertAfter(id="Call_MergeRequestsWithResponses_118")
	public void verifyMergeRequestsWithResponses() throws Exception {
		if (isExecuting964d) {
			if (StringUtils.contains(getName(), "NoErrors")) {
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_SSK118_PRE_TRANSFORM, MOCK_SSK118_PRE_TRANSFORM_NO_RESULTS_964d), 
						compareAgainstVariable(VAR_LOCAL_SSK118_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_SSK118_PRE_TRANSFORM_NO_RESULTS_964d, CONTENT_TYPE_TEXT_XML));
			} else {
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_SSK118_PRE_TRANSFORM, MOCK_SSK118_PRE_TRANSFORM_RESULTS_964d), 
						compareAgainstVariable(VAR_LOCAL_SSK118_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_SSK118_PRE_TRANSFORM_RESULTS_964d, CONTENT_TYPE_TEXT_XML));
			}
		} else if (isExecutingdaf1) {
			if (StringUtils.contains(getName(), "NoErrors")) {
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_SSK118_PRE_TRANSFORM, MOCK_SSK118_PRE_TRANSFORM_NO_RESULTS_daf1), 
						compareAgainstVariable(VAR_LOCAL_SSK118_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_SSK118_PRE_TRANSFORM_NO_RESULTS_daf1, CONTENT_TYPE_TEXT_XML));
			} else {
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_SSK118_PRE_TRANSFORM, MOCK_SSK118_PRE_TRANSFORM_RESULTS_daf1), 
						compareAgainstVariable(VAR_LOCAL_SSK118_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_SSK118_PRE_TRANSFORM_RESULTS_daf1, CONTENT_TYPE_TEXT_XML));
			}
		}
	}
	
	@AssertAfter(id="PrepareDetailedMessages_118")
	public void verifyDecoratedMessages() throws Exception {
		if (isExecuting964d) {
			if (StringUtils.contains(getName(), "NoErrors")) {
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK118_MESSAGES_NO_RESULTS_DECORATED_964d), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK118_MESSAGES_NO_RESULTS_DECORATED_964d, CONTENT_TYPE_TEXT_XML));
			} else {
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK118_MESSAGES_RESULTS_DECORATED_964d), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK118_MESSAGES_RESULTS_DECORATED_964d, CONTENT_TYPE_TEXT_XML));
			}
		} else if (isExecutingdaf1) {
			if (StringUtils.contains(getName(), "NoErrors")) {
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK118_MESSAGES_NO_RESULTS_DECORATED_daf1), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK118_MESSAGES_NO_RESULTS_DECORATED_daf1, CONTENT_TYPE_TEXT_XML));
			} else {
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK118_MESSAGES_RESULTS_DECORATED_daf1), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK118_MESSAGES_RESULTS_DECORATED_daf1, CONTENT_TYPE_TEXT_XML));
			}
		}
	}

	private void verifyLoggedMessagesFor964d() throws Exception {
		boolean isBasicMessage = StringUtils.contains(getName(), "NoDetails");
		
		String level = "ERROR";
		String message = "Issue with Import WWS Data";
		String messageDetail = "Invalid ID value.  'POS_21015' is not a valid ID value for type = 'Position_ID'";
		String reference = VALUE_WID_20;
		String localIn = isBasicMessage ? "Call_CreateLogEntry_ResponseMessage_Dynamic_118" : "Call_CreateLogEntry_DetailedMessage_Dynamic_118";
		String errorCode = isBasicMessage ? "11803" : "11802";
		String recordNumber = "5";
		String supportData = isBasicMessage ? 
				"The Reference Id contains the WID of the background process executing the import.  The timestamp of the error reported by the tenant is 2019-04-28T13:47:01.488-07:00." :
				"The Reference Id contains the WID of the background process executing the import.  The timestamp of the error reported by the tenant is 2019-04-28T13:47:01.488-07:00.  The original import request data was: <wd:Request_Data xmlns:wd=\\\"urn:com.workday/bsvc\\\"><wd:Eligible_Earnings_Override_Period_Reference> <wd:ID wd:type=\\\"Eligible_Earnings_Period_ID\\\">EARN_PD_2019</wd:ID> </wd:Eligible_Earnings_Override_Period_Reference><wd:Eligible_Earnings> <wd:Eligible_Earnings_Data> <wd:Eligible_Earnings_ID>31005_EARN_PD_2019</wd:Eligible_Earnings_ID> <wd:Employee_Reference><wd:ID wd:type=\\\"Employee_ID\\\">31005</wd:ID></wd:Employee_Reference> <wd:Apply_to_All_Bonus_Plans>true</wd:Apply_to_All_Bonus_Plans> <wd:Position_Reference><wd:ID wd:type=\\\"Position_ID\\\">POS_00031005</wd:ID></wd:Position_Reference> <wd:Amount>31005.00</wd:Amount> <wd:Currency_Reference><wd:ID wd:type=\\\"Currency_ID\\\">USD</wd:ID></wd:Currency_Reference> </wd:Eligible_Earnings_Data> </wd:Eligible_Earnings></wd:Request_Data>";
		
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);

		level = "ERROR";
		messageDetail = "Invalid ID value.  'POS_21020' is not a valid ID value for type = 'Position_ID'";
		recordNumber = "10";
		supportData = isBasicMessage ? 
				"The Reference Id contains the WID of the background process executing the import.  The timestamp of the error reported by the tenant is 2019-04-28T13:47:01.488-07:00." :
				"The Reference Id contains the WID of the background process executing the import.  The timestamp of the error reported by the tenant is 2019-04-28T13:47:01.488-07:00.  The original import request data was: <wd:Request_Data xmlns:wd=\\\"urn:com.workday/bsvc\\\"><wd:Eligible_Earnings_Override_Period_Reference> <wd:ID wd:type=\\\"Eligible_Earnings_Period_ID\\\">EARN_PD_2019</wd:ID> </wd:Eligible_Earnings_Override_Period_Reference><wd:Eligible_Earnings> <wd:Eligible_Earnings_Data> <wd:Eligible_Earnings_ID>31010_EARN_PD_2019</wd:Eligible_Earnings_ID> <wd:Employee_Reference><wd:ID wd:type=\\\"Employee_ID\\\">31010</wd:ID></wd:Employee_Reference> <wd:Apply_to_All_Bonus_Plans>true</wd:Apply_to_All_Bonus_Plans> <wd:Position_Reference><wd:ID wd:type=\\\"Position_ID\\\">POS_00031010</wd:ID></wd:Position_Reference> <wd:Amount>31010.00</wd:Amount> <wd:Currency_Reference><wd:ID wd:type=\\\"Currency_ID\\\">USD</wd:ID></wd:Currency_Reference> </wd:Eligible_Earnings_Data> </wd:Eligible_Earnings></wd:Request_Data>";
		
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}
	
	private void verifyLoggedMessagesFordaf1() throws Exception {
		boolean isBasicMessage = StringUtils.contains(getName(), "NoDetails");
		
		String level = "ERROR";
		String message = "Issue with Import WWS Data";
		String messageDetail = "Invalid ID value.  'POS_21015' is not a valid ID value for type = 'Position_ID'";
		String reference = VALUE_WID_21;
		String localIn = isBasicMessage ? "Call_CreateLogEntry_ResponseMessage_Dynamic_118" : "Call_CreateLogEntry_DetailedMessage_Dynamic_118";
		String errorCode = isBasicMessage ? "11803" : "11802";
		String recordNumber = "5";
		String supportData = isBasicMessage ? 
				"The Reference Id contains the WID of the background process executing the import.  The timestamp of the error reported by the tenant is 2019-04-28T13:47:01.488-07:00." :
				"The Reference Id contains the WID of the background process executing the import.  The timestamp of the error reported by the tenant is 2019-04-28T13:47:01.488-07:00.  The original import request data was: <wd:Request_Data xmlns:wd=\\\"urn:com.workday/bsvc\\\"><wd:Eligible_Earnings_Override_Period_Reference> <wd:ID wd:type=\\\"Eligible_Earnings_Period_ID\\\">EARN_PD_2019</wd:ID> </wd:Eligible_Earnings_Override_Period_Reference><wd:Eligible_Earnings> <wd:Eligible_Earnings_Data> <wd:Eligible_Earnings_ID>21005_EARN_PD_2019</wd:Eligible_Earnings_ID> <wd:Employee_Reference><wd:ID wd:type=\\\"Employee_ID\\\">21005</wd:ID></wd:Employee_Reference> <wd:Apply_to_All_Bonus_Plans>true</wd:Apply_to_All_Bonus_Plans> <wd:Position_Reference><wd:ID wd:type=\\\"Position_ID\\\">POS_00021005</wd:ID></wd:Position_Reference> <wd:Amount>21005.00</wd:Amount> <wd:Currency_Reference><wd:ID wd:type=\\\"Currency_ID\\\">USD</wd:ID></wd:Currency_Reference> </wd:Eligible_Earnings_Data> </wd:Eligible_Earnings></wd:Request_Data>";
		
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);

		level = "ERROR";
		messageDetail = "Invalid ID value.  'POS_21020' is not a valid ID value for type = 'Position_ID'";
		recordNumber = "10";
		supportData = isBasicMessage ? 
				"The Reference Id contains the WID of the background process executing the import.  The timestamp of the error reported by the tenant is 2019-04-28T13:47:01.488-07:00." :
				"The Reference Id contains the WID of the background process executing the import.  The timestamp of the error reported by the tenant is 2019-04-28T13:47:01.488-07:00.  The original import request data was: <wd:Request_Data xmlns:wd=\\\"urn:com.workday/bsvc\\\"><wd:Eligible_Earnings_Override_Period_Reference> <wd:ID wd:type=\\\"Eligible_Earnings_Period_ID\\\">EARN_PD_2019</wd:ID> </wd:Eligible_Earnings_Override_Period_Reference><wd:Eligible_Earnings> <wd:Eligible_Earnings_Data> <wd:Eligible_Earnings_ID>21010_EARN_PD_2019</wd:Eligible_Earnings_ID> <wd:Employee_Reference><wd:ID wd:type=\\\"Employee_ID\\\">21010</wd:ID></wd:Employee_Reference> <wd:Apply_to_All_Bonus_Plans>true</wd:Apply_to_All_Bonus_Plans> <wd:Position_Reference><wd:ID wd:type=\\\"Position_ID\\\">POS_00021010</wd:ID></wd:Position_Reference> <wd:Amount>21010.00</wd:Amount> <wd:Currency_Reference><wd:ID wd:type=\\\"Currency_ID\\\">USD</wd:ID></wd:Currency_Reference> </wd:Eligible_Earnings_Data> </wd:Eligible_Earnings></wd:Request_Data>";
		
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}
	
	private void verifyLoggedMessageNoImports() throws Exception {
		String level = "WARN";
		String message = "No Import Process File Found";
		String messageDetail = "The ImportRequests.zip file was not found; therefore, the background process WIDs are unknown and status cannot be queried.";
		String reference = VALUE_WID_1;
		String localIn = "DocumentAccessor_118";
		String errorCode = "11800";
		String recordNumber = "";
		String supportData = "ImportRequests.zip was not found on Integration Event.  See the Reference Id for the WID of that event.";
		
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}

	private void verifyLoggedMessageEmptyFile() throws Exception {
		String level = "WARN";
		String message = "No Import Processes Found Within File";
		String messageDetail = "The ImportRequests.zip file was found; however, it did not contain any files identifying the background process WIDs.";
		String reference = VALUE_WID_1;
		String localIn = "ImportRequestsSplitter_118";
		String errorCode = "11801";
		String recordNumber = "";
		String supportData = "ImportRequests.zip was found on the Integration Event, but was empty.  See the Reference Id for the WID of that event.";
		
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected void initializeFileBlobitory() throws Exception {
		super.initializeFileBlobitory();

		putToBlobitory(MOCK_FILE_SSK118_REQUESTS_SINGLE_NO_DETAIL, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK118_REQUESTS_SINGLE_WITH_DETAIL, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR2_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK118_REQUESTS_DOUBLE_NO_DETAIL, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR3_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK118_REQUESTS_DOUBLE_WITH_DETAIL, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_EVENT_DOCUMENT_ENTRYID4, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK118_REQUESTS_NONE, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_EVENT_DOCUMENT_ENTRYID5, CONTENT_TYPE_TEXT_XML);
	}

	@Override
	protected void initializeEventDocuments() throws Exception {
		super.initializeEventDocuments();

		if ("testNoFileFound".equalsIgnoreCase(getName())) {
			setIntegrationEventDocuments(MOCK_SSK118_RESPONSE_GET_EVENT_DOCUMENTS_NONE);
			setMessagePart(0, MOCK_SSK118_RESPONSE_GET_EVENT_DOCUMENTS_NONE, CONTENT_TYPE_TEXT_XML);
		} else {
			setIntegrationEventDocuments(MOCK_SSK118_RESPONSE_GET_EVENT_DOCUMENTS);
			setMessagePart(0, MOCK_SSK118_RESPONSE_GET_EVENT_DOCUMENTS, CONTENT_TYPE_TEXT_XML);
		}
	}

	@Override
	protected Throwable loadSoapPagedResponse() throws Exception {
		Throwable returnValue = super.loadSoapPagedResponse();
		
		if (StringUtils.contains(getName(), "NoErrors")) {
			setVariable(VAR_LOCAL_SSK118_RESPONSE, MOCK_SSK118_AGGREGATED_GET_IMPORT_PROCESS_MESSAGES_NONE, CONTENT_TYPE_TEXT_XML);
		} else {
			setVariable(VAR_LOCAL_SSK118_RESPONSE, MOCK_SSK118_AGGREGATED_GET_IMPORT_PROCESS_MESSAGES, CONTENT_TYPE_TEXT_XML);
		}
		
		return returnValue;
	}

}
