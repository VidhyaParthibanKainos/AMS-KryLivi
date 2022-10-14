package com.workday.custom.aunit.int006.utilities.ssk111;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent111TestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_DA_METADATA = "globalDAMetadata";
	public static final String VAR_GLOBAL_INPUT_DATA = "globalInputData";

	public static final String PROP_PARAMETER_IN_FILENAME = "inFilename";
	public static final String PROP_PARAMETER_IN_COLLECTION = "inCollection";
	public static final String PROP_PARAMETER_IN_ENTRY_ID = "inEntryID";
	public static final String PROP_PARAMETER_IN_RETURN_RESULTS = "inReturnResults";

	public static final String PROP_LOCAL_METADATA_FILENAME = "localMetadataFilename";

	public static final String VALUE_INVALID_FILENAME = "doesNotExist.xml";
	public static final String VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED = "cc-retrieve";
	public static final String VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID = "50684860-7622-412a-3014-4f092f012129";
	public static final String VALUE_FILE_VENDOR2_XML_EVENT_DOCUMENT_ENTRYID = "7277537f-4505-4b38-243c-242a3f21220d";
	public static final String VALUE_FILE_VENDOR3_XML_EVENT_DOCUMENT_ENTRYID = "4d5b7233-586b-444b-3d4c-215811332815";

	public static final String MOCK_FILE_SSK111_VENDOR1_XML = "test/ssk/ssk111/vendor-file1.xml";
	public static final String MOCK_FILE_SSK111_VENDOR2_XML = "test/ssk/ssk111/vendor-file2.xml";
	public static final String MOCK_FILE_SSK111_VENDOR3_XML = "test/ssk/ssk111/vendor-file3.xml";
	public static final String MOCK_INITIALIZATION_SSK111_EVENT_DOCUMENTS_3RETRIEVED = "test/ssk/ssk111/SSK111_SOAP_Get_Event_Documents_Response3.xml";
	public static final String MOCK_INITIALIZATION_SSK111_EVENT_DOCUMENTS_1RETRIEVED = "test/ssk/ssk111/SSK111_SOAP_Get_Event_Documents_Response1.xml";
	public static final String MOCK_MESSAGE_SSK111_GLOBAL_DA_METADATA_1FILE = "test/ssk/ssk111/SSK111_XML_GlobalDAMetadata_1File.xml";
	public static final String MOCK_MESSAGE_SSK111_GLOBAL_DA_METADATA_3FILES = "test/ssk/ssk111/SSK111_XML_GlobalDAMetadata_3Files.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setupGlobalInitialization();
	}

	@Override
	protected void initializeEventDocuments() throws Exception {
		super.initializeEventDocuments();
		
		switch(getName()) {
			case "testLoadOneRetrievedToVariable" :
				mockOneFile();				
				setIntegrationEventDocuments(MOCK_INITIALIZATION_SSK111_EVENT_DOCUMENTS_1RETRIEVED);
				break;
			
			case "testLoadThreeRetrievedToVariable" :
				mockThreeFiles();				
				setIntegrationEventDocuments(MOCK_INITIALIZATION_SSK111_EVENT_DOCUMENTS_3RETRIEVED);
				break;
		
			case "testLoadOneRetrievedToMessage" :
				mockOneFile();				
				setIntegrationEventDocuments(MOCK_INITIALIZATION_SSK111_EVENT_DOCUMENTS_1RETRIEVED);
				break;
			
			case "testLoadThreeRetrievedToMessage" :
				mockThreeFiles();				
				setIntegrationEventDocuments(MOCK_INITIALIZATION_SSK111_EVENT_DOCUMENTS_3RETRIEVED);
				break;
		
			case "testFileDoesNotExistError" :
				mockThreeFiles();				
				setIntegrationEventDocuments(MOCK_INITIALIZATION_SSK111_EVENT_DOCUMENTS_3RETRIEVED);
				break;
		
			default :
				break;
		}
	}
	
	private void mockOneFile() throws Exception {
		putToBlobitory(MOCK_FILE_SSK111_VENDOR1_XML, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
	}

	private void mockThreeFiles() throws Exception {
		putToBlobitory(MOCK_FILE_SSK111_VENDOR1_XML, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK111_VENDOR2_XML, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR2_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
		putToBlobitory(MOCK_FILE_SSK111_VENDOR3_XML, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR3_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
	}


	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="LoadFile")
	public void testLoadOneRetrievedToVariable() throws Exception {
		initializeEventDocuments();
		ctx.setProperty(PROP_PARAMETER_IN_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK111_VENDOR1_XML, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_COLLECTION, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_ID, VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID);
		
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_INPUT_DATA);
	}

	@UnitTest(startComponent="LoadFile")
	public void testLoadThreeRetrievedToVariable() throws Exception {
		initializeEventDocuments();
		ctx.setProperty(PROP_PARAMETER_IN_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK111_VENDOR2_XML, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_COLLECTION, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_ID, VALUE_FILE_VENDOR2_XML_EVENT_DOCUMENT_ENTRYID);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_INPUT_DATA);
	}

	@UnitTest(startComponent="LoadFile")
	public void testLoadOneRetrievedToMessage() throws Exception {
		initializeEventDocuments();
		ctx.setProperty(PROP_PARAMETER_IN_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK111_VENDOR1_XML, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_COLLECTION, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_ID, VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID);
	}

	@UnitTest(startComponent="LoadFile")
	public void testLoadThreeRetrievedToMessage() throws Exception {
		initializeEventDocuments();
		ctx.setProperty(PROP_PARAMETER_IN_FILENAME, StringUtils.substringAfterLast(MOCK_FILE_SSK111_VENDOR2_XML, "/"));
		ctx.setProperty(PROP_PARAMETER_IN_COLLECTION, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_ID, VALUE_FILE_VENDOR2_XML_EVENT_DOCUMENT_ENTRYID);
	}

	@UnitTest(startComponent="LoadFile")
	public void testFileDoesNotExistError() throws Exception {
		initializeEventDocuments();
		ctx.setProperty(PROP_PARAMETER_IN_FILENAME, VALUE_INVALID_FILENAME);
		ctx.setProperty(PROP_PARAMETER_IN_COLLECTION, VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_ID, VALUE_FILE_VENDOR2_XML_EVENT_DOCUMENT_ENTRYID);
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
			
			assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_METADATA_FILENAME), PROP_LOCAL_METADATA_FILENAME.equalsIgnoreCase(propertyName));
			
			verifyGlobalPropertyNotModified(propertyName, null);
		}
		
		switch(getName()) {
			case "testFileDoesNotExistError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "1", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
				break;
				
			case "testLoadOneRetrievedToVariable" :
				assertTrue(String.format(MESSAGE_VARIABLE_NOT_ADDED, VAR_GLOBAL_INPUT_DATA), ctx.getVariables().containsKey(VAR_GLOBAL_INPUT_DATA));
				break;
			
			case "testLoadThreeRetrievedToVariable" :
				assertTrue(String.format(MESSAGE_VARIABLE_NOT_ADDED, VAR_GLOBAL_INPUT_DATA), ctx.getVariables().containsKey(VAR_GLOBAL_INPUT_DATA));
				break;
		
			default :
				break;
		}
	}
	
	@Override
	protected void handledExceptionVerification(Throwable t) throws Exception {
		if (!(getName().equalsIgnoreCase("testFileDoesNotExistError"))) {
			super.handledExceptionVerification(t);
		} 
	}

	@AssertAfter(id="LoadFile_Variable_111", step="MoveMsgToVar")
	public void verifyFileLoadVariable() throws Exception {
		switch(getName()) {
			case "testLoadOneRetrievedToVariable" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_GLOBAL_INPUT_DATA), 
						compareAgainstVariable(VAR_GLOBAL_INPUT_DATA, CONTENT_TYPE_TEXT_XML, MOCK_FILE_SSK111_VENDOR1_XML, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testLoadThreeRetrievedToVariable" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_GLOBAL_INPUT_DATA), 
						compareAgainstVariable(VAR_GLOBAL_INPUT_DATA, CONTENT_TYPE_TEXT_XML, MOCK_FILE_SSK111_VENDOR2_XML, CONTENT_TYPE_TEXT_XML));
				break;
			
			default :
				fail(String.format(MESSAGE_TEST_MISSING_VALIDATION, getName()));
				break;
		}
	}


	@AssertAfter(id="LoadFile_111", step="Retrieve")
	public void verifyFileLoadMessage() throws Exception {
		switch(getName()) {
			case "testLoadOneRetrievedToMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_FILE_SSK111_VENDOR1_XML), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_FILE_SSK111_VENDOR1_XML, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testLoadThreeRetrievedToMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_FILE_SSK111_VENDOR2_XML), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_FILE_SSK111_VENDOR2_XML, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testLoadOneRetrievedToVariable" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_FILE_SSK111_VENDOR1_XML), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_FILE_SSK111_VENDOR1_XML, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testLoadThreeRetrievedToVariable" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_FILE_SSK111_VENDOR2_XML), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_FILE_SSK111_VENDOR2_XML, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testFileDoesNotExistError" :
				break;
			
			default :
				fail(String.format(MESSAGE_TEST_MISSING_VALIDATION, getName()));
				break;
		}
	}

	@AssertAfter(id="Call_HandleError_LoadFile_Error_111")
	public void verifyErrorHandler() throws Exception {
		switch(getName()) {
			case "testFileDoesNotExistError" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_STUDIO_IS_ERROR_HANDLED), ctx.isErrorHandled());
				break;
			
			default :
				fail(String.format(MESSAGE_TEST_MISSING_VALIDATION, getName()));
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
