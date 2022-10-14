package com.workday.custom.aunit.int006.utilities.ssk113;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.soap.SOAPException;

import com.capeclear.mediation.MediationException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent113TestCase extends UtilitiesTestCase {
	
	public static final String VAR_LOCAL_REQUEST_CACHE = "localRequestCache113";
	public static final String VAR_LOCAL_REQUEST_ARCHIVE = "localImportRequestArchive113";
	public static final String VAR_LOCAL_SSK113_CACHED_REQUEST = "localRequestCache113";
	
	public static final String PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG = "inSupportDetailedResultReporting";
	public static final String PROP_PARAMETER_IN_FINALIZE = "inIsFinalImportCall";
	public static final String PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION = "inWebServiceApplication";
	public static final String PROP_PARAMETER_IN_API_VERSION = "inApiVersion";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inBuildRequestDataLocation";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION_ID = "inBuildRequestDataLocationId";
	public static final String PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH = "inBuildRequestPathToXsltFile";
	public static final String PROP_PARAMETER_IN_RETURN_RESULTS = "inReturnResults";
	public static final String PROP_PARAMETER_IN_INTEGRATION_EVENT_WID = "inImportIntegrationEventWID";
	public static final String PROP_PARAMETER_IN_VALIDATE_ONLY = "inValidateOnly";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	public static final String PROP_PARAMETER_IN_AGGREGATOR_COLLATE ="inCollate";
	public static final String PROP_PARAMETER_IN_AGGREGATOR_BATCH ="inBatch";

	public static final String PROP_PARAMETER_OUT_IMPORT_PROCESS_REFERENCE = "outImportProcessReference";
	public static final String PROP_PARAMETER_OUT_SOAP_ERROR = "outIsSoapError";
	public static final String PROP_PARAMETER_OUT_SOAP_ERROR_MESSAGE = "outIsSoapError";

	public static final String VALUE_WS_APP_HUMAN_RESOURCES = "Human_Resources";
	public static final String VALUE_DATA_LOCATION_MESSAGE = "message";
	public static final String VALUE_DATA_LOCATION_VARIABLE = "variable";
	public static final String VALUE_DATA_LOCATION_PROPERTY = "property";

	public static final String MOCK_MESSAGE_SSK113_IMPORT_REQUEST = "test/ssk/ssk113/SSK113_SOAP_Request.xml";
	public static final String MOCK_MESSAGE_SSK113_REQUEST_DATA = "test/ssk/ssk113/SSK113_SOAP_Data.xml";
	public static final String MOCK_MESSAGE_SSK113_TRANSFORMED_REQUEST = "test/ssk/ssk113/SSK113_SOAP_TransformedRequest.xml";
	public static final String MOCK_XSLT_SSK113_TRANSFORM_TO_IMPORT = "test/ssk/ssk113/SSK113_DataToRequest.xsl";
	public static final String MOCK_SOAP_SSK113_RESPONSE = "test/ssk/ssk113/SSK113_SOAP_Import_Response.xml";
	public static final String MOCK_MESSAGE_SSK113_DO_NOT_MATCH = "test/ssk/ssk113/SSK113_Message_DoNotMatch.xml";
	public static final String MOCK_VARIABLE_SSK113_FILLER = "test/ssk/ssk113/SSK113_Variable_FillerContent.xml";

	public static final String MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES = "test/ssk/ssk113/SSK113_ImportRequest_MultipleInstances.xsl";
	public static final String MOCK_MESSAGE_SSK113_VARIABLE_MAP_NONXML = "test/ssk/ssk113/SSK113_TXT_inMapVariableName_NonXml.txt";
	public static final String MOCK_MESSAGE_SSK113_VARIABLE_MAP_3WORKERS = "test/ssk/ssk113/SSK113_XML_inMapVariableName_3Workers.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		ctx.setProperty(PROP_PARAMETER_IN_API_VERSION, VALUE_API_VERSION);
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_HUMAN_RESOURCES);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FINALIZE, true);
		
		ctx.setProperty(PROP_PARAMETER_IN_INTEGRATION_EVENT_WID, VALUE_WID_1);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="CallSoapImport")
	public void testSingleRequestByMessageWithDetail() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK113_IMPORT_REQUEST, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testSingleRequestByTransformWithDetail() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_TRANSFORM_TO_IMPORT));
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK113_REQUEST_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testSingleRequestByMessageWithDetailDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK113_IMPORT_REQUEST, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testSingleRequestByTransformWithDetailDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_TRANSFORM_TO_IMPORT));
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK113_REQUEST_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testSingleRequestByMessageToVariableWithDetailDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK113_IMPORT_REQUEST, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testSingleRequestByTransformToVariableWithDetailDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_TRANSFORM_TO_IMPORT));
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK113_REQUEST_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testSingleRequestByMessageNoDetail() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, false);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK113_IMPORT_REQUEST, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testSingleRequestByTransformNoDetail() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_TRANSFORM_TO_IMPORT));
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, false);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK113_REQUEST_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testSingleRequestByMessageSoapError() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, false);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK113_IMPORT_REQUEST, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="FinalizeCallSoapImport")
	public void testFinalizeCallSoapImport() throws Exception {
		setVariable(VAR_LOCAL_REQUEST_CACHE, MOCK_VARIABLE_SSK113_FILLER, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_LOCAL_REQUEST_ARCHIVE, MOCK_VARIABLE_SSK113_FILLER, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Aggregate_113")
	public void testValidationError() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATOR_COLLATE, false);
		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATOR_BATCH, false);
		ctx.setProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG, false);
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_NAME, null);
	}

	@UnitTest(startComponent="CallSoapImport")
	public void testValidationError11300() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testValidationError11301() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));

		setMessagePart(0, MOCK_MESSAGE_SSK113_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testMessageLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));

		setMessagePart(0, MOCK_MESSAGE_SSK113_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="CallSoapImport")
	public void testValidationError11302() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK113_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testValidationError11303() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myVarData", MOCK_MESSAGE_SSK113_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testVariableLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK113_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testVariableLocalInValidationErrorDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myVarData", MOCK_MESSAGE_SSK113_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testVariableLocalInValidationErrorDataLocationAndDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK113_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testValidationError11304() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testValidationError11305NotMap() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));
		
		List<String> prompts = new ArrayList<String>();
		prompts.add("typeFromMap");
		prompts.add("valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testValidationError11305EmptyMap() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));
		
		Map<String,String> prompts = new HashMap<String,String>();
		ctx.setProperty("mapSingleLevel", prompts);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testPropertyLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));

		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testPropertyLocalInValidationErrorDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
	}
	
	@UnitTest(startComponent="CallSoapImport")
	public void testPropertyLocalInValidationErrorDataLocationAndDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK113_IMPORT_REQUEST_MULTIPLE_INSTANCES));
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
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
		if (!testsExpectingHandledExceptions.contains(getName()) &&
				!testsExpectingUnhandledExceptions.contains(getName()) &&
				!"testFinalizeCallSoapImport".equalsIgnoreCase(getName())) {
			verifyLoggedMessage();
		}
		
		if ("testSingleRequestByMessageSoapError".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_PARAMETER_OUT_SOAP_ERROR), "http", (String)ctx.getProperty(PROP_PARAMETER_OUT_SOAP_ERROR));
			verifyLoggedError();
		}
	}
	
	@Override
	protected void unhandledExceptionVerification(Throwable t) throws Exception {
		if ("testValidationError".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, "exception"), t instanceof MediationException);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error code"), 11300, ((MediationException)t).getErrorNumber());
		} else {
			super.unhandledExceptionVerification(t);
		}
	}

	private void verifyLoggedMessage() throws Exception {
		String level = "INFO";
		String message = "Import Web Service Started";
		String messageDetail = "Launched an import process on the Human_Resources application.  See the Reference ID for the associated WID of the Import Process Reference.";
		String reference = VALUE_WID_10;
		String localIn = null;
		String errorCode = null;
		String recordNumber = null;
		String supportData = null;
		
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}

	private void verifyLoggedError() throws Exception {
		String level = "ERROR";
		String message = "Human_Resources application HTTP request error";
		String messageDetail = "An error occurred while executing a SOAP request for the Human_Resources application.";
		String reference = null;
		String localIn = "CallSoap_113";
		String errorCode = "-1";
		String recordNumber = null;
		String supportData = "Bad things happened on the way to the tenant";
		
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}

	@AssertAfter(id="Transform_113")
	public void verifyImportRequest() throws Exception {
		assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_SSK113_TRANSFORMED_REQUEST), 
				compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK113_TRANSFORMED_REQUEST, CONTENT_TYPE_TEXT_XML));
	}
	
	@AssertAfter(id="CacheRequest_113")
	public void verifyCachedRequest() throws Exception {
		Object propertyValue = ctx.getProperty(PROP_PARAMETER_IN_SUPPORT_DETAIL_LOG);
		boolean isDetailedLog = (propertyValue == null) ? false : (boolean)propertyValue;
		
		if (isDetailedLog) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_LOCAL_SSK113_CACHED_REQUEST), 
					compareAgainstVariable(VAR_LOCAL_SSK113_CACHED_REQUEST, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK113_TRANSFORMED_REQUEST, CONTENT_TYPE_TEXT_XML));
		}
	}
	
	@AssertAfter(id="RetrieveProcessReference_113")
	public void verifyResponseWID() throws Exception {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_PARAMETER_OUT_IMPORT_PROCESS_REFERENCE), VALUE_WID_10, String.valueOf(ctx.getProperty(PROP_PARAMETER_OUT_IMPORT_PROCESS_REFERENCE)));
	}
	
	@AssertAfter(id="Results_113")
	public void verifyResults() throws Exception {
		Object propertyValue = ctx.getProperty(PROP_PARAMETER_IN_RETURN_RESULTS);
		boolean isTestable = ((propertyValue == null) && "variable".equalsIgnoreCase(String.valueOf(propertyValue)));
		
		if (isTestable) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, PROP_PARAMETER_IN_RETURN_RESULTS), 
					compareAgainstVariable(PROP_PARAMETER_IN_RETURN_RESULTS, CONTENT_TYPE_TEXT_XML, MOCK_SOAP_SSK113_RESPONSE, CONTENT_TYPE_TEXT_XML));
		}
	}
	
	@AssertAfter(id="AggregateToArchive_113")
	public void verifyZipContent() throws Exception {
		if (!"testFinalizeCallSoapImport".equalsIgnoreCase(getName())) {
			ZipInputStream zipStream = new ZipInputStream(getMessageInputStream(), StandardCharsets.UTF_8);
			
			List<String> archivedFilenames = new ArrayList<String>();
			List<InputStream> archivedEntries = new ArrayList<InputStream>();
			
			while (zipStream.available() > 0) {
				ZipEntry file = zipStream.getNextEntry();
				if (file != null) {
					archivedFilenames.add(file.getName());
	
					byte[] buffer = new byte[1024];
					int length = 0;
	
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					while ((length = zipStream.read(buffer, 0, buffer.length)) > 0) {
						baos.write(buffer, 0, length);
					}
	
					archivedEntries.add(new ByteArrayInputStream(baos.toByteArray()));
				}
			}
	
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "number of files in the zip archive"), 1, archivedFilenames.size());
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "file name in zip archive"), VALUE_WID_10 + ".xml", archivedFilenames.get(0));
			
			if ("testSingleRequestByMessageWithDetail".equalsIgnoreCase(getName()) ||
					"testSingleRequestByTransformWithDetail".equalsIgnoreCase(getName()) ||
					"testSingleRequestByMessageWithDetailDebug".equalsIgnoreCase(getName()) ||
					"testSingleRequestByTransformWithDetailDebug".equalsIgnoreCase(getName()) ||
					"testSingleRequestByMessageToVariableWithDetailDebug".equalsIgnoreCase(getName()) ||
					"testSingleRequestByTransformToVariableWithDetailDebug".equalsIgnoreCase(getName())) {
				verifyImportRequestsContentWithDetail(archivedEntries.get(0));
			} else {
				verifyImportRequestsContentNoDetail(archivedEntries.get(0));
			}
		}
	}

	@AssertAfter(id="Call_Aggregate_Finalize_113")
	public void verifyFinalizedZip() throws Exception {
		ZipInputStream zipStream = new ZipInputStream(getMessageInputStream(), StandardCharsets.UTF_8);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "availability of zip archive"), 1, zipStream.available());
	}

	private void verifyImportRequestsContentWithDetail(InputStream zippedFile) throws Exception {
		assertTrue(String.format("Zip archive contents were not the expected value at [%1$s]", "zipped file " + VALUE_WID_10 + ".xml"), 
				compare(
						getTestResourceInputStream(MOCK_MESSAGE_SSK113_IMPORT_REQUEST), 
						CONTENT_TYPE_TEXT_XML, 
						zippedFile, 
						CONTENT_TYPE_TEXT_XML, 
						Comparator.dom));
	}
	
	private void verifyImportRequestsContentNoDetail(InputStream zippedFile) throws Exception {
		assertTrue(String.format("Zip archive contents were not the expected value at [%1$s]", "zipped file " + VALUE_WID_10 + ".xml"), 
				compare(
						getTestResourceInputStream(MOCK_MESSAGE_SSK113_DO_NOT_MATCH), 
						CONTENT_TYPE_TEXT_XML, 
						zippedFile, 
						CONTENT_TYPE_TEXT_XML, 
						Comparator.dom));
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected Throwable loadSoapResponse() throws Exception {
		Throwable returnValue = "testSingleRequestByMessageSoapError".equalsIgnoreCase(getName()) ? new SOAPException("Bad things happened on the way to the tenant") : super.loadSoapResponse();
		
		setMessagePart(0, MOCK_SOAP_SSK113_RESPONSE, CONTENT_TYPE_TEXT_XML);
		return returnValue;
	}
	
}
