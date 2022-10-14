package com.workday.custom.aunit.int006.utilities.ssk106;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.capeclear.xml.utils.DOMUtils;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AssertBefore;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent106TestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_OUTPUT_DATA = "globalOutputData";

	public static final String VAR_LOCAL_RESULT = "localStoreResult106";

	public static final String PROP_PARAMETER_IN_FULL_FILENAME = "inOutputFilename";
	public static final String PROP_PARAMETER_IN_OUTPUT_IS_DELIVERABLE = "inOutputIsDeliverable";
	public static final String PROP_PARAMETER_IN_OUTPUT_DOCUMENT_TAG = "inOutputDocumentTag";
	public static final String PROP_PARAMETER_IN_DATA_SOURCE = "inDataSource";
	public static final String PROP_PARAMETER_IN_SECURED_TARGETS_IDS = "inSecuredInstanceRefs";
	public static final String PROP_PARAMETER_IN_SECURED_TARGETS_TYPE = "inSecuredInstanceRefTypes";
	
	public static final String PROP_LOCAL_SECURED_TARGETS = "localSecuredRefs";
	
	public static final String MOCK_RESPONSE_SSK106_XML_DATA = "test/ssk/ssk106/SSK106_XML_Data.xml";
	public static final String MOCK_RESPONSE_SSK106_XML_STORE_RESPONSE = "test/ssk/ssk106/SSK106_XML_StoreResponse.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="GenerateOutput")
	public void testWriteStorageVariableNoTag() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_OUTPUT_IS_DELIVERABLE, true);
		ctx.setProperty(PROP_PARAMETER_IN_FULL_FILENAME, VALUE_OUTPUT_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_OUTPUT_DATA);
		
		setVariable(VAR_GLOBAL_OUTPUT_DATA, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML);		
	}

	@UnitTest(startComponent="GenerateOutput")
	public void testWriteStorageMessageNoTag() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_OUTPUT_IS_DELIVERABLE, true);
		ctx.setProperty(PROP_PARAMETER_IN_FULL_FILENAME, VALUE_OUTPUT_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		
		setMessagePart(0, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML);		
	}

	@UnitTest(startComponent="GenerateOutput")
	public void testWriteStorageMessageWithOneTag() throws Exception {
		List<String> tags = new ArrayList<String>();
		tags.add(VALUE_DOCUMENT_DELIVERY_TAG1);
		
		ctx.setProperty(PROP_PARAMETER_IN_OUTPUT_IS_DELIVERABLE, true);
		ctx.setProperty(PROP_PARAMETER_IN_FULL_FILENAME, VALUE_OUTPUT_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		
		setMessagePart(0, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML);		
	}

	@UnitTest(startComponent="GenerateOutput")
	public void testWriteStorageMessageWithTags() throws Exception {
		List<String> tags = new ArrayList<String>();
		tags.add(VALUE_DOCUMENT_DELIVERY_TAG1);
		tags.add(VALUE_DOCUMENT_DELIVERY_TAG2);

		ctx.setProperty(PROP_PARAMETER_IN_OUTPUT_IS_DELIVERABLE, true);
		ctx.setProperty(PROP_PARAMETER_IN_FULL_FILENAME, VALUE_OUTPUT_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		
		setMessagePart(0, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML);		
	}

	@UnitTest(startComponent="GenerateOutput")
	public void testSecuredTargetsAsString() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_OUTPUT_IS_DELIVERABLE, true);
		ctx.setProperty(PROP_PARAMETER_IN_FULL_FILENAME, VALUE_OUTPUT_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_SECURED_TARGETS_IDS, VALUE_WID_1);
		ctx.setProperty(PROP_PARAMETER_IN_SECURED_TARGETS_TYPE, "WID");
		
		setMessagePart(0, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML);		
	}

	@UnitTest(startComponent="GenerateOutput")
	public void testSecuredTargetsAsList() throws Exception {
		List<String> targets = new ArrayList<String>();
		targets.add(VALUE_WID_1);
		targets.add(VALUE_WID_2);

		ctx.setProperty(PROP_PARAMETER_IN_OUTPUT_IS_DELIVERABLE, true);
		ctx.setProperty(PROP_PARAMETER_IN_FULL_FILENAME, VALUE_OUTPUT_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_SECURED_TARGETS_IDS, targets);
		ctx.setProperty(PROP_PARAMETER_IN_SECURED_TARGETS_TYPE, "WID");
		
		setMessagePart(0, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML);		
	}

	@UnitTest(startComponent="GenerateOutput")
	public void testSecuredTargetsAsWrongType() throws Exception {
		expectUnhandledException();
		
		Set<String> targets = new HashSet<String>();
		targets.add(VALUE_WID_1);
		targets.add(VALUE_WID_2);

		ctx.setProperty(PROP_PARAMETER_IN_OUTPUT_IS_DELIVERABLE, true);
		ctx.setProperty(PROP_PARAMETER_IN_FULL_FILENAME, VALUE_OUTPUT_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_SECURED_TARGETS_IDS, targets);
		ctx.setProperty(PROP_PARAMETER_IN_SECURED_TARGETS_TYPE, "WID");
		
		setMessagePart(0, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML);		
	}

	@UnitTest(startComponent="GenerateOutput")
	public void testSecuredTargetsMissingType() throws Exception {
		expectUnhandledException();
		
		List<String> targets = new ArrayList<String>();
		targets.add(VALUE_WID_1);
		targets.add(VALUE_WID_2);

		ctx.setProperty(PROP_PARAMETER_IN_OUTPUT_IS_DELIVERABLE, true);
		ctx.setProperty(PROP_PARAMETER_IN_FULL_FILENAME, VALUE_OUTPUT_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_SECURED_TARGETS_IDS, targets);
		
		setMessagePart(0, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML);		
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
		
	}
	
	@AssertAfter(id="InitializeAndFinalize_106", step="InitValues")
	public void verifySecureTargets() throws Exception {
		if ("testSecuredTargetsAsString".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_SECURED_TARGETS), VALUE_WID_1, (String)ctx.getProperty(PROP_LOCAL_SECURED_TARGETS));
		} else if ("testSecuredTargetsAsList".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_SECURED_TARGETS), VALUE_WID_1 + "," + VALUE_WID_2, (String)ctx.getProperty(PROP_LOCAL_SECURED_TARGETS));
		}
	}
	
	@AssertAfter(id="StoreVariable_106", step="MoveVarToMsg")
	public void verifyStoreContentVariable() throws Exception {
		assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESPONSE_SSK106_XML_DATA), 
				compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML));
	}
	
	@AssertBefore(id="StoreMessage_106", step="StoreContent")
	public void verifyStoreContentMessage() throws Exception {
		assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESPONSE_SSK106_XML_DATA), 
				compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_RESPONSE_SSK106_XML_DATA, CONTENT_TYPE_TEXT_XML));

		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_PARAMETER_IN_FULL_FILENAME), VALUE_OUTPUT_FILE, (String)ctx.getProperty(PROP_PARAMETER_IN_FULL_FILENAME));
	}
	
	@AssertAfter(id="StoreMessage_106", step="StoreContent")
	public void verifyStorage() throws Exception {
		Document doc = DOMUtils.parseToDOM(new InputSource(new InputStreamReader(getVariableInputStream(VAR_LOCAL_RESULT), "UTF-8")));
		String xpath = "count(/*:entry[*:title = '" + VALUE_OUTPUT_FILE + "'])";
		
		assertEqualsXPath(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_RESULT, "1 xpath match"), "1", xpath, doc);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

}
