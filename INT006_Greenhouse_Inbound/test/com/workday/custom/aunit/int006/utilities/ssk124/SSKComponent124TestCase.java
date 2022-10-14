package com.workday.custom.aunit.int006.utilities.ssk124;

import com.capeclear.mediation.MediationException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent124TestCase extends UtilitiesTestCase {

	private static final String PROP_PARAMETER_IN_ENDPOINT = "inEndpoint";
	private static final String PROP_PARAMETER_IN_POLL_URI = "inPollURI";
	private static final String PROP_PARAMETER_IN_USERNAME = "inUsername";
	private static final String PROP_PARAMETER_IN_PASSWORD = "inPassword";

	private static final String PROP_PARAMETER_OUT_MESSAGE_ID = "outMessageId";
	private static final String PROP_PARAMETER_OUT_MESSAGE_URI = "outMessageURI";
	
	private static final String PROP_LOCAL_LOCAL_URI = "localURI";

	private static final String VALUE_QUEUE_NAME = "INT-SSK-MQ";
	private static final String VALUE_MOCK_ENDPOINT = "https://aunit.workday.com";
	private static final String VALUE_MOCK_USERNAME = "mqUser";
	private static final String VALUE_MOCK_PASSWORD = "mqPass";
	private static final String VALUE_POLL_URI = "ccx/wd-queue/" + VALUE_QUEUE_NAME + "/fresh";
	
	private static final String MOCK_MESSAGE_RESPONSE_EXISTS = "test/ssk/ssk124/SSK124_XML_GET_Response_Messages.xml";
	private static final String MOCK_MESSAGE_RESPONSE_EMPTY = "test/ssk/ssk124/SSK124_XML_GET_Response_NoMessages.xml";
	private static final String MOCK_MESSAGE_RESPONSE_NOT_EXISTS = "test/ssk/ssk124/SSK124_TXT_GET_Response_NotExists.txt";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();

		componentProperties.add(PROP_LOCAL_LOCAL_URI);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="PollQueue")
	public void testQueueNotExists() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_POLL_URI, VALUE_POLL_URI);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}
	
	@UnitTest(startComponent="PollQueue")
	public void testQueueExistsNotEmpty() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_POLL_URI, VALUE_POLL_URI);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}
	
	@UnitTest(startComponent="PollQueue")
	public void testQueueExistsEmpty() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_POLL_URI, VALUE_POLL_URI);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
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

	@AssertAfter(id="InitializeAndFinalize_124", step="SetValues")
	public void verifyInitialization() throws Exception {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_LOCAL_URI), VALUE_MOCK_ENDPOINT + VALUE_POLL_URI, (String)ctx.getProperty(PROP_LOCAL_LOCAL_URI));
	}

	@AssertAfter(id="QueueResults_124", step="UpdateValues")
	public void verifyReturnValue() throws Exception {
		if ("testQueueExistsNotEmpty".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_PARAMETER_OUT_MESSAGE_ID), "1", (String)ctx.getProperty(PROP_PARAMETER_OUT_MESSAGE_ID));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_PARAMETER_OUT_MESSAGE_URI), "/ccx/wd-queue/INT-SSK-MQ/messages/1", String.valueOf(ctx.getProperty(PROP_PARAMETER_OUT_MESSAGE_URI)));
		} else if ("testQueueExistsEmpty".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_PARAMETER_OUT_MESSAGE_ID), "", (String)ctx.getProperty(PROP_PARAMETER_OUT_MESSAGE_ID));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_PARAMETER_OUT_MESSAGE_URI), "", String.valueOf(ctx.getProperty(PROP_PARAMETER_OUT_MESSAGE_URI)));
		} else if ("testQueueNotExists".equalsIgnoreCase(getName())) {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
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
	protected Throwable loadRestCallQueue124() throws Exception {
		Throwable returnValue = null;
		
		if ("testQueueExistsNotEmpty".equalsIgnoreCase(getName())) {
			setMessagePart(0, MOCK_MESSAGE_RESPONSE_EXISTS, CONTENT_TYPE_TEXT_XML);
		} else if ("testQueueExistsEmpty".equalsIgnoreCase(getName())) {
			setMessagePart(0, MOCK_MESSAGE_RESPONSE_EMPTY, CONTENT_TYPE_TEXT_XML);
		} else if ("testQueueNotExists".equalsIgnoreCase(getName())) {
			setMessagePart(0, MOCK_MESSAGE_RESPONSE_NOT_EXISTS, CONTENT_TYPE_TEXT_PLAIN);
			returnValue = new MediationException("Mocked error polling queue");
		}
		
		return returnValue;
	}

}
