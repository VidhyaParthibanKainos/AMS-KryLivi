package com.workday.custom.aunit.int006.utilities.ssk127;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent127TestCase extends UtilitiesTestCase {

	private static final String PROP_PARAMETER_IN_ENDPOINT = "inEndpoint";
	private static final String PROP_PARAMETER_IN_QUEUE_NAME = "inQueueName";
	private static final String PROP_PARAMETER_IN_USERNAME = "inUsername";
	private static final String PROP_PARAMETER_IN_PASSWORD = "inPassword";
	private static final String PROP_PARAMETER_IN_MESSAGE_URI = "inMessageURI";
	private static final String PROP_PARAMETER_IN_MESSAGE_TIMESTAMP = "inMessageTimestamp";
	private static final String PROP_PARAMETER_IN_DELETE_AFTER_READ = "inIsDeleteAfterRead";
	private static final String PROP_PARAMETER_IN_CHILD_CONTEXT = "inIsChildThreadContext";

	private static final String PROP_LOCAL_LOCAL_URI = "localURI";
	
	private static final String VALUE_QUEUE_NAME = "INT-SSK-MQ";
	private static final String VALUE_MOCK_ENDPOINT = "https://aunit.workday.com";
	private static final String VALUE_MOCK_USERNAME = "mqUser";
	private static final String VALUE_MOCK_PASSWORD = "mqPass";
	private static final String VALUE_MESSAGE_URI = "/ccx/wd-queue/INT-SSK-MQ/messages/1";
	private static final String VALUE_MESSAGE_TIMESTAMP = "2020-09-05T21:27:05.000Z";
	
	private static final String MOCK_MESSAGE_RESPONSE_DELETE = "test/ssk/ssk127/SSK127_XML_DELETE_Response.xml";
	private static final String MOCK_MESSAGE_RESPONSE_ERROR = "test/ssk/ssk127/SSK127_TXT_DELETE_Response_Error.txt";

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
	@UnitTest(startComponent="DeleteMessageFromQueue")
	public void testDeleteError() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_URI, VALUE_MESSAGE_URI);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_TIMESTAMP, VALUE_MESSAGE_TIMESTAMP);
		ctx.setProperty(PROP_PARAMETER_IN_DELETE_AFTER_READ, false);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_CHILD_CONTEXT, false);
	}
	
	@UnitTest(startComponent="DeleteMessageFromQueue")
	public void testDeleteMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_URI, VALUE_MESSAGE_URI);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_TIMESTAMP, VALUE_MESSAGE_TIMESTAMP);
		ctx.setProperty(PROP_PARAMETER_IN_DELETE_AFTER_READ, true);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_CHILD_CONTEXT, false);
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

	@AssertAfter(id="InitializeAndFinalize_127", step="SetValues")
	public void verifyInitialization() throws Exception {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_LOCAL_URI), VALUE_MOCK_ENDPOINT + VALUE_MESSAGE_URI, (String)ctx.getProperty(PROP_LOCAL_LOCAL_URI));
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	@Override
	protected Throwable loadRestCallQueueDelete127() throws Exception {
		Throwable returnValue = null;
		
		if ("testDeleteError".equalsIgnoreCase(getName())) {
			setMessagePart(0, MOCK_MESSAGE_RESPONSE_ERROR, CONTENT_TYPE_TEXT_XML);
		} else if ("testDeleteMessage".equalsIgnoreCase(getName())) {
			setMessagePart(0, MOCK_MESSAGE_RESPONSE_DELETE, CONTENT_TYPE_TEXT_XML);
		}
		
		return returnValue;
	}
}
