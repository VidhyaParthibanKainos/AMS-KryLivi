package com.workday.custom.aunit.int006.utilities.ssk115;

import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.capeclear.xml.utils.DOMUtils;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent115TestCase extends UtilitiesTestCase {
	
	public static final String VAR_GLOBAL_DIS_RESULTS = "globalDISResults";

	public static final String VAR_LOCAL_XSLT115 = "localXslt115";
	
	public static final String PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG = "inRetrievalDocTag";
	public static final String PROP_PARAMETER_IN_RETURN_RESULTS = "inReturnResults";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	public static final String PROP_PARAMETER_IN_USE_SERIAL = "inIsUseOrderedAggregation";

	public static final String PROP_LOCAL_PREDICATE_DEFAULT = "localXPathPredicateDefault";
	public static final String PROP_LOCAL_PREDICATE_SERVICE = "localXPathPredicateService";
	public static final String PROP_LOCAL_LABELS = "localLabels";

	public static final String VALUE_SSK115_DIS_LABEL_SERVICE = "SA115 DIS";
	public static final String VALUE_EVENT_DOCUMENT_COLLECTION_DIS = "de7ef94f0ec701da5b37bd1109000101";
    public static final String VALUE_EVENT_DOCUMENT_ENTRYID_DIS_1 = "637d016a-071a-486e-2c6b-29311e3f753e";
    public static final String VALUE_EVENT_DOCUMENT_ENTRYID_DIS_2 = "130d1360-163a-491c-2d63-7d0d69447300";
    public static final String VALUE_EVENT_DOCUMENT_ENTRYID_DIS_3 = "570e030d-7331-4974-2f6d-1c026c1c7d73";
    public static final String VALUE_EVENT_DOCUMENT_ENTRYID_DIS_4 = "0e3f4849-3072-4e54-1b08-267b2e6f7c3c";
    public static final String VALUE_EVENT_DOCUMENT_ENTRYID_DIS_5 = "5f776d43-1767-421e-1856-1c503b6e7e5a";
    public static final String VALUE_EVENT_DOCUMENT_ENTRYID_DIS_6 = "6d020639-3b75-4167-1d29-1d4f36152646";
    public static final String VALUE_EVENT_DOCUMENT_ENTRYID_DIS_7 = "1f6a531e-1128-4521-1f4c-5f156f3e4041";
    public static final String VALUE_EVENT_DOCUMENT_ENTRYID_DIS_8 = "483b2e67-1306-4b19-1b31-370b50470f55";
    public static final String VALUE_EVENT_DOCUMENT_ENTRYID_DIS_9 = "28421b5f-4e42-4319-2156-517209083b79";

	public static final String MOCK_SSK115_RESPONSE_GET_EVENT_DOCUMENTS = "test/ssk/ssk115/SSK115_SOAP_Get_Event_Documents_Response.xml";
	public static final String MOCK_SSK115_RESPONSE_GET_EVENT_DOCUMENTS_NONE = "test/ssk/ssk115/SSK115_SOAP_Get_Event_Documents_Response_NoFiles.xml";
	public static final String MOCK_FILE_SSK115_PARTIAL_1 = "test/ssk/ssk115/SSK115_Workers_Results-1.xml";
	public static final String MOCK_FILE_SSK115_PARTIAL_2 = "test/ssk/ssk115/SSK115_Workers_Results-2.xml";
	public static final String MOCK_FILE_SSK115_PARTIAL_3 = "test/ssk/ssk115/SSK115_Workers_Results-3.xml";
	public static final String MOCK_FILE_SSK115_PARTIAL_4 = "test/ssk/ssk115/SSK115_Workers_Results-4.xml";
	public static final String MOCK_FILE_SSK115_PARTIAL_5 = "test/ssk/ssk115/SSK115_Workers_Results-5.xml";
	public static final String MOCK_FILE_SSK115_PARTIAL_6 = "test/ssk/ssk115/SSK115_Workers_Results-6.xml";
	public static final String MOCK_FILE_SSK115_PARTIAL_7 = "test/ssk/ssk115/SSK115_Workers_Results-7.xml";
	public static final String MOCK_FILE_SSK115_PARTIAL_8 = "test/ssk/ssk115/SSK115_Workers_Results-8.xml";
	public static final String MOCK_FILE_SSK115_PARTIAL_9 = "test/ssk/ssk115/SSK115_Workers_Results-9.xml";
	public static final String MOCK_SSK115_VARIABLE_XSLT_DEFAULT = "test/ssk/ssk115/SSK115_XSLT_default.xml";
	public static final String MOCK_SSK115_VARIABLE_XSLT_SERVICE = "test/ssk/ssk115/SSK115_XSLT_multiTags.xml";
	public static final String MOCK_SSK115_XML_GET_EVENT_DOCUMNETS_TRANSFORMED = "test/ssk/ssk115/SSK115_XML_GetEventDocumentsResponseTransformed.xml";
	public static final String MOCK_SSK115_XML_GET_EVENT_DOCUMNETS_TRANSFORMED_NO_FILES = "test/ssk/ssk115/SSK115_XML_GetEventDocumentsResponseNoFilesTransformed.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		super.setupGlobalInitialization();
	}
	
	@Override
	protected void initializeFileBlobitory() throws Exception {
		super.initializeFileBlobitory();

		putToBlobitory(MOCK_FILE_SSK115_PARTIAL_1, VALUE_EVENT_DOCUMENT_COLLECTION_DIS, VALUE_EVENT_DOCUMENT_ENTRYID_DIS_1, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK115_PARTIAL_2, VALUE_EVENT_DOCUMENT_COLLECTION_DIS, VALUE_EVENT_DOCUMENT_ENTRYID_DIS_2, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK115_PARTIAL_3, VALUE_EVENT_DOCUMENT_COLLECTION_DIS, VALUE_EVENT_DOCUMENT_ENTRYID_DIS_3, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK115_PARTIAL_4, VALUE_EVENT_DOCUMENT_COLLECTION_DIS, VALUE_EVENT_DOCUMENT_ENTRYID_DIS_4, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK115_PARTIAL_5, VALUE_EVENT_DOCUMENT_COLLECTION_DIS, VALUE_EVENT_DOCUMENT_ENTRYID_DIS_5, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK115_PARTIAL_6, VALUE_EVENT_DOCUMENT_COLLECTION_DIS, VALUE_EVENT_DOCUMENT_ENTRYID_DIS_6, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK115_PARTIAL_7, VALUE_EVENT_DOCUMENT_COLLECTION_DIS, VALUE_EVENT_DOCUMENT_ENTRYID_DIS_7, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK115_PARTIAL_8, VALUE_EVENT_DOCUMENT_COLLECTION_DIS, VALUE_EVENT_DOCUMENT_ENTRYID_DIS_8, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK115_PARTIAL_9, VALUE_EVENT_DOCUMENT_COLLECTION_DIS, VALUE_EVENT_DOCUMENT_ENTRYID_DIS_9, CONTENT_TYPE_TEXT_XML);
	}
	
	@Override
	protected void initializeEventDocuments() throws Exception {
		super.initializeEventDocuments();

		if ("testNoDISFiles".equalsIgnoreCase(getName()) ||
				"testMultipleTagsNoDISFiles".equalsIgnoreCase(getName())) {
			setIntegrationEventDocuments(MOCK_SSK115_RESPONSE_GET_EVENT_DOCUMENTS_NONE);
			setMessagePart(0, MOCK_SSK115_RESPONSE_GET_EVENT_DOCUMENTS_NONE, CONTENT_TYPE_TEXT_XML);
		} else {
			setIntegrationEventDocuments(MOCK_SSK115_RESPONSE_GET_EVENT_DOCUMENTS);
			setMessagePart(0, MOCK_SSK115_RESPONSE_GET_EVENT_DOCUMENTS, CONTENT_TYPE_TEXT_XML);
		}
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="GetDISResults")
	public void testSimpleAggregationToVariable() throws Exception {
		initializeInParameters(VAR_GLOBAL_DIS_RESULTS, false, null, false);
	}

	@UnitTest(startComponent="GetDISResults")
	public void testSimpleAggregationToMessage() throws Exception {
		initializeInParameters("message", false, null, false);
	}

	@UnitTest(startComponent="GetDISResults")
	public void testMultipleTagsToMessage() throws Exception {
		initializeInParameters("message", false, VALUE_SSK115_DIS_LABEL_SERVICE, false);
	}

	@UnitTest(startComponent="GetDISResults")
	public void testNoDISFiles() throws Exception {
		initializeInParameters("message", false, null, false);
	}
	
	@UnitTest(startComponent="GetDISResults")
	public void testMultipleTagsNoDISFiles() throws Exception {
		initializeInParameters("message", false, VALUE_SSK115_DIS_LABEL_SERVICE, false);
	}
	
	@UnitTest(startComponent="GetDISResults")
	public void testSerialSimpleAggregationToVariable() throws Exception {
		initializeInParameters(VAR_GLOBAL_DIS_RESULTS, false, null, true);
	}

	@UnitTest(startComponent="GetDISResults")
	public void testSerialSimpleAggregationToMessage() throws Exception {
		initializeInParameters("message", false, null, true);
	}

	@UnitTest(startComponent="GetDISResults")
	public void testSerialMultipleTagsToMessage() throws Exception {
		initializeInParameters("message", false, VALUE_SSK115_DIS_LABEL_SERVICE, true);
	}

	@UnitTest(startComponent="GetDISResults")
	public void testSerialNoDISFiles() throws Exception {
		initializeInParameters("message", false, null, true);
	}
	
	@UnitTest(startComponent="GetDISResults")
	public void testSerialMultipleTagsNoDISFiles() throws Exception {
		initializeInParameters("message", false, VALUE_SSK115_DIS_LABEL_SERVICE, true);
	}
	
	private void initializeInParameters(String returnResults, boolean isDebugMode, String documentTag, boolean isSerial) throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, returnResults);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, isDebugMode);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, false);
		ctx.setProperty(PROP_PARAMETER_IN_USE_SERIAL, isSerial);

		if (StringUtils.isNotBlank(documentTag)) {
			ctx.setProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG, documentTag);
		}
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
			
			assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_LABELS), PROP_LOCAL_LABELS.equalsIgnoreCase(propertyName));
			
			verifyGlobalPropertyNotModified(propertyName, null);
		}
		
		switch(getName()) {
			case "testSimpleAggregationToVariable" :
				assertTrue(String.format(MESSAGE_VARIABLE_NOT_ADDED, VAR_GLOBAL_DIS_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_DIS_RESULTS));
				break;
				
			case "testSerialSimpleAggregationToVariable" :
				assertTrue(String.format(MESSAGE_VARIABLE_NOT_ADDED, VAR_GLOBAL_DIS_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_DIS_RESULTS));
				break;
				
			default :
				assertFalse(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VAR_GLOBAL_DIS_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_DIS_RESULTS));
				break;
		}
	}
	
	@AssertAfter(id="PrepareFileResults_Parallel_115", step="InitValues")
	public void verifyParallelInitValues() throws Exception {
		String expectedValue = null;
		switch(getName()) {
			case "testMultipleTagsToMessage" :
				expectedValue = "wd:Document_Tag_Reference/wd:ID[@wd:type='Integration_Service_Name'] = '" + VALUE_SSK115_DIS_LABEL_SERVICE + "'";
				break;
			
			case "testMultipleTagsNoDISFiles" :
				expectedValue = "wd:Document_Tag_Reference/wd:ID[@wd:type='Integration_Service_Name'] = '" + VALUE_SSK115_DIS_LABEL_SERVICE + "'";
				break;
			
			default :
				expectedValue = "";
				break;
		}
		
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PREDICATE_DEFAULT), "wd:Document_Tag_Reference/wd:ID[@wd:type='Workday_Document_Tag_Name'] = 'Data - Partial'", ctx.getProperty(PROP_LOCAL_PREDICATE_DEFAULT));
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PREDICATE_SERVICE), expectedValue, ctx.getProperty(PROP_LOCAL_PREDICATE_SERVICE));
	}
	
	@AssertAfter(id="PrepareFileResults_Serial_115", step="InitValues")
	public void verifySerialInitValues() throws Exception {
		if (!getName().contains("Serial")) {
			fail(getName() + " should not have reached this point of execution.");
		}
		
		String expectedValue = null;
		switch(getName()) {
			case "testSerialMultipleTagsToMessage" :
				expectedValue = "Data - Partial, " + VALUE_SSK115_DIS_LABEL_SERVICE;
				break;
			
			case "testSerialMultipleTagsNoDISFiles" :
				expectedValue = "Data - Partial, " + VALUE_SSK115_DIS_LABEL_SERVICE;
				break;
			
			default :
				expectedValue = "Data - Partial";
				break;
		}
		
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_LABELS), expectedValue, ctx.getProperty(PROP_LOCAL_LABELS));
	}
	
	@AssertAfter(id="PrepareFileResults_Parallel_115", step="LoadXslt")
	public void verifyXsltAuthorship() throws Exception {
		if (getName().contains("Serial")) {
			fail(getName() + " should not have reached this point of execution.");
		}
		
		switch(getName()) {
			case "testMultipleTagsToMessage" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_XSLT115, MOCK_SSK115_VARIABLE_XSLT_SERVICE),
						compareAgainstVariable(VAR_LOCAL_XSLT115, CONTENT_TYPE_TEXT_XML, MOCK_SSK115_VARIABLE_XSLT_SERVICE, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testMultipleTagsNoDISFiles" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_XSLT115, MOCK_SSK115_VARIABLE_XSLT_SERVICE),
						compareAgainstVariable(VAR_LOCAL_XSLT115, CONTENT_TYPE_TEXT_XML, MOCK_SSK115_VARIABLE_XSLT_SERVICE, CONTENT_TYPE_TEXT_XML));
				break;
			
			default :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_XSLT115, MOCK_SSK115_VARIABLE_XSLT_DEFAULT),
						compareAgainstVariable(VAR_LOCAL_XSLT115, CONTENT_TYPE_TEXT_XML, MOCK_SSK115_VARIABLE_XSLT_DEFAULT, CONTENT_TYPE_TEXT_XML));
				break;
		}
	}
	
	@AssertAfter(id="PrepareFileResults_Parallel_115")
	public void verifyXsltTransformResults() throws Exception {
		switch(getName()) {
			case "testNoDISFiles" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK115_XML_GET_EVENT_DOCUMNETS_TRANSFORMED_NO_FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK115_XML_GET_EVENT_DOCUMNETS_TRANSFORMED_NO_FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testMultipleTagsNoDISFiles" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK115_XML_GET_EVENT_DOCUMNETS_TRANSFORMED_NO_FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK115_XML_GET_EVENT_DOCUMNETS_TRANSFORMED_NO_FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			default :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK115_XML_GET_EVENT_DOCUMNETS_TRANSFORMED),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK115_XML_GET_EVENT_DOCUMNETS_TRANSFORMED, CONTENT_TYPE_TEXT_XML));
				break;
		}
	}
	
	@AssertAfter(id="CopyResultFromThreads_115", step="CopyVarToMsg")
	public void verifyFinalAggregation() throws Exception {
		if (!"testNoDISFiles".equalsIgnoreCase(getName())) {
			int expectedValue = 424;
			String xpath = "count(/DISRoot/*:worker)";
			Document doc = DOMUtils.parseToDOM(new InputSource(new InputStreamReader(getMessageInputStream(), "UTF-8")));
			
			assertEqualsXPath(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, expectedValue + " xpath matches"), String.valueOf(expectedValue), xpath, doc);
			
			expectedValue = 1;
			String[] employeeIDs = {"21001", "21156", "21206", "21274", "21313", "21358", "21408", "21461", "21075"};
	
			for (String eeID : employeeIDs) {
				xpath = "count(/DISRoot/*:worker[*:Employee_ID = \"" + eeID + "\"])";
				assertEqualsXPath(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, expectedValue + " xpath matches"), String.valueOf(expectedValue), xpath, doc);
			}
		}
	}
	
	@AssertAfter(id="Results_115")
	public void verifyResults() throws Exception {
		switch(getName()) {
			case "testSimpleAggregationToVariable" :
				int expectedValue = 424;
				String xpath = "count(/DISRoot/*:worker)";
				Document doc = DOMUtils.parseToDOM(new InputSource(new InputStreamReader(getVariableInputStream(VAR_GLOBAL_DIS_RESULTS), "UTF-8")));
				
				assertEqualsXPath(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_GLOBAL_DIS_RESULTS, expectedValue + " xpath matches"), String.valueOf(expectedValue), xpath, doc);
				
				expectedValue = 1;
				String[] employeeIDs = {"21001", "21156", "21206", "21274", "21313", "21358", "21408", "21461", "21075"};

				for (String eeID : employeeIDs) {
					xpath = "count(/DISRoot/*:worker[*:Employee_ID = \"" + eeID + "\"])";
					assertEqualsXPath(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_GLOBAL_DIS_RESULTS, expectedValue + " xpath matches"), String.valueOf(expectedValue), xpath, doc);
				}
				break;
				
			default :
				break;
		}
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

}
