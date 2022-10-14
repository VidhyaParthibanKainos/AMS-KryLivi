package com.workday.custom.aunit.int006.utilities.ssk126;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent126TestCase extends UtilitiesTestCase {

	private static final String PROP_PARAMETER_IN_ENDPOINT = "inEndpoint";
	private static final String PROP_PARAMETER_IN_QUEUE_NAME = "inQueueName";
	private static final String PROP_PARAMETER_IN_USERNAME = "inUsername";
	private static final String PROP_PARAMETER_IN_PASSWORD = "inPassword";
	private static final String PROP_PARAMETER_IN_XSLT_SPLIT = "inXsltSplitDataToMessages";
	private static final String PROP_PARAMETER_IN_NAMESPACE = "inNamespaceSplitDataToMessages";
	private static final String PROP_PARAMETER_IN_DATA_SOURCE = "inDataSource";
	
	private static final String PROP_LOCAL_LOCAL_URI = "localURI";
	private static final String PROP_LOCAL_PRESERVE_MESSAGE = "localIsPreserveMessage";
	
	private static final String VAR_MESSAGE_CACHE = "localMessageCache126";

	private static final String VALUE_QUEUE_NAME = "INT-SSK-MQ";
	private static final String VALUE_MOCK_ENDPOINT = "https://aunit.workday.com";
	private static final String VALUE_MOCK_USERNAME = "mqUser";
	private static final String VALUE_MOCK_PASSWORD = "mqPass";
	private static final String VALUE_VAR_RETURN_RESULTS = "myResults";

	private static final String MOCK_MESSAGE_NOISE = "test/ssk/ssk126/SSK126_XML_Message_Noise.xml";
	private static final String MOCK_MESSAGE_DATA = "test/ssk/ssk126/SSK126_XML_Message.xml";
	private static final String MOCK_MESSAGE_DATA_SPLIT1 = "test/ssk/ssk126/SSK126_XML_Message_Split1.xml";
	private static final String MOCK_MESSAGE_DATA_SPLIT2 = "test/ssk/ssk126/SSK126_XML_Message_Split2.xml";
	private static final String MOCK_MESSAGE_DATA_SPLIT3 = "test/ssk/ssk126/SSK126_XML_Message_Split3.xml";
	private static final String MOCK_MESSAGE_DATA_SPLIT4 = "test/ssk/ssk126/SSK126_XML_Message_Split4.xml";
	private static final String MOCK_MESSAGE_RESPONSE_WRITE = "test/ssk/ssk126/SSK126_XML_POST_Response.xml";
	private static final String MOCK_MESSAGE_RESPONSE_WRITE_1 = "test/ssk/ssk126/SSK126_XML_POST_Response_Split1.xml";
	private static final String MOCK_MESSAGE_RESPONSE_WRITE_2 = "test/ssk/ssk126/SSK126_XML_POST_Response_Split2.xml";
	private static final String MOCK_MESSAGE_RESPONSE_WRITE_3 = "test/ssk/ssk126/SSK126_XML_POST_Response_Split3.xml";
	private static final String MOCK_MESSAGE_RESPONSE_WRITE_4 = "test/ssk/ssk126/SSK126_XML_POST_Response_Split4.xml";

	private int splitCounter;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();
		
		componentProperties.add(PROP_LOCAL_LOCAL_URI);
		componentProperties.add(PROP_LOCAL_PRESERVE_MESSAGE);
		
		splitCounter = 0;
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="AddMessageToQueue")
	public void testSingleOnMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		
		setMessagePart(0, MOCK_MESSAGE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AddMessageToQueue")
	public void testSingleOnVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VALUE_VAR_RETURN_RESULTS);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		
		setMessagePart(0, MOCK_MESSAGE_NOISE, CONTENT_TYPE_TEXT_XML);
		setVariable(VALUE_VAR_RETURN_RESULTS, MOCK_MESSAGE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AddMessageToQueue")
	public void testMultipleOnMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_SPLIT, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_NAMESPACE, "wd urn:com.workday.report/CR_INT_Workers");
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		
		setMessagePart(0, MOCK_MESSAGE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AddMessageToQueue")
	public void testMultipleOnVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_SPLIT, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_NAMESPACE, "wd urn:com.workday.report/CR_INT_Workers");
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VALUE_VAR_RETURN_RESULTS);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		
		setMessagePart(0, MOCK_MESSAGE_NOISE, CONTENT_TYPE_TEXT_XML);
		setVariable(VALUE_VAR_RETURN_RESULTS, MOCK_MESSAGE_DATA, CONTENT_TYPE_TEXT_XML);
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

		assertTrue(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_MESSAGE_CACHE), isVariableNullOrUndefined(VAR_MESSAGE_CACHE));

		if ("testSingleOnMessage".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_RESPONSE_WRITE),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESPONSE_WRITE, CONTENT_TYPE_TEXT_XML));
		} else if ("testMultipleOnMessage".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_RESPONSE_WRITE_4),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESPONSE_WRITE_4, CONTENT_TYPE_TEXT_XML));
		} else if ("testSingleOnVariable".equalsIgnoreCase(getName()) ||
				"testMultipleOnVariable".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_NOISE),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_NOISE, CONTENT_TYPE_TEXT_XML));
		}
	}

	@AssertAfter(id="InitializeAndFinalize_126", step="InitValues")
	public void verifyInitialization() throws Exception {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_LOCAL_URI), VALUE_MOCK_ENDPOINT + "/ccx/wd-queue/" + VALUE_QUEUE_NAME, (String)ctx.getProperty(PROP_LOCAL_LOCAL_URI));
		if (getName().contains("Variable")) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PRESERVE_MESSAGE), getName().contains("Variable") == (Boolean)ctx.getProperty(PROP_LOCAL_PRESERVE_MESSAGE));
		}
	}
	
	@AssertAfter(id="PrepareData_126", step="CopyMsgToVar")
	public void verifyMessageCache() throws Exception {
		if ("testSingleOnMessage".equalsIgnoreCase(getName()) ||
				"testMultipleOnMessage".equalsIgnoreCase(getName())) {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		} else {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_MESSAGE_CACHE),
					compareAgainstVariable(VAR_MESSAGE_CACHE, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_NOISE, CONTENT_TYPE_TEXT_XML));
		}
	}

	@AssertAfter(id="SplitMessages_126")
	public void verifySplitter() throws Exception {
		if ("testSingleOnMessage".equalsIgnoreCase(getName()) ||
				"testSingleOnVariable".equalsIgnoreCase(getName())) {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		} else {
			splitCounter++;
			
			switch (splitCounter) {
				case 1 :
					assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_DATA_SPLIT1),
							compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_DATA_SPLIT1, CONTENT_TYPE_TEXT_XML));
					break;
					
				case 2 :
					assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_DATA_SPLIT2),
							compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_DATA_SPLIT2, CONTENT_TYPE_TEXT_XML));
					break;
					
				case 3 :
					assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_DATA_SPLIT3),
							compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_DATA_SPLIT3, CONTENT_TYPE_TEXT_XML));
					break;
					
				case 4 :
					assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_DATA_SPLIT4),
							compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_DATA_SPLIT4, CONTENT_TYPE_TEXT_XML));
					break;
					
				case 5 :
					//No op for final splitter loop to confirm empty
					break;
					
				default :
					break;
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

	@Override
	protected Throwable loadRestCallQueue126() throws Exception {
		switch (splitCounter) {
			case 0 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_WRITE, CONTENT_TYPE_TEXT_XML);
				break;
				
			case 1 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_WRITE_1, CONTENT_TYPE_TEXT_XML);
				break;
				
			case 2 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_WRITE_2, CONTENT_TYPE_TEXT_XML);
				break;
				
			case 3 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_WRITE_3, CONTENT_TYPE_TEXT_XML);
				break;
				
			case 4 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_WRITE_4, CONTENT_TYPE_TEXT_XML);
				break;
				
			default :
				fail(getName() + " should not have made a REST call with counter = " + String.valueOf(splitCounter));
				break;
		}
		
		return null;
	}
}
