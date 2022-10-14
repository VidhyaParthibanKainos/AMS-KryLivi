package com.workday.custom.aunit.int006.utilities.ssk128;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent128TestCase extends UtilitiesTestCase {

	private static final String PROP_PARAMETER_IN_ENDPOINT = "inEndpoint";
	private static final String PROP_PARAMETER_IN_QUEUE_NAME = "inQueueName";
	private static final String PROP_PARAMETER_IN_USERNAME = "inUsername";
	private static final String PROP_PARAMETER_IN_PASSWORD = "inPassword";
	private static final String PROP_PARAMETER_IN_LOG_BEFORE_DELETE = "inIsLogMessageBeforeDelete";

	private static final String PROP_LOCAL_POLL_URI = "localPollURI";
	private static final String PROP_LOCAL_IS_LOOP = "localIsContinueLoop";
	private static final String PROP_LOCAL_MESSAGE_ID = "localMessageId";
	private static final String PROP_LOCAL_MESSAGE_URI = "localMessageURI";
	private static final String PROP_LOCAL_MESSAGE_TIMESTAMP = "localMessageTimestamp";
	
	private static final String VALUE_QUEUE_NAME = "INT-SSK-MQ";
	private static final String VALUE_MOCK_ENDPOINT = "https://aunit.workday.com";
	private static final String VALUE_MOCK_USERNAME = "mqUser";
	private static final String VALUE_MOCK_PASSWORD = "mqPass";
	private static final String VALUE_MESSAGE_URI = VALUE_MOCK_ENDPOINT + "/ccx/wd-queue/INT-SSK-MQ/messages/";
	private static final String VALUE_MESSAGE_TIMESTAMP = "2020-09-05T21:27:05.000Z";
	private static final String VALUE_POLL_URI = "/ccx/wd-queue/" + VALUE_QUEUE_NAME + "/fresh?maxMessages=999";
	
	private static final String MOCK_MESSAGE_RESPONSE_POLL_EMPTY = "test/ssk/ssk128/SSK128_XML_GET_Response_Poll_Empty.xml";
	private static final String MOCK_MESSAGE_RESPONSE_POLL_PART_1OF1 = "test/ssk/ssk128/SSK128_XML_GET_Response_Poll_Single_Stop.xml";
	private static final String MOCK_MESSAGE_RESPONSE_POLL_PART_1OF2 = "test/ssk/ssk128/SSK128_XML_GET_Response_Poll_Multiple_Continue.xml";
	private static final String MOCK_MESSAGE_RESPONSE_POLL_PART_2OF2 = "test/ssk/ssk128/SSK128_XML_GET_Response_Poll_Multiple_Stop.xml";
	
	private static final String MOCK_MESSAGE_RESPONSE_GET_DATA_1 = "test/ssk/ssk128/SSK128_XML_MessageData_Split1.xml";
	private static final String MOCK_MESSAGE_RESPONSE_GET_DATA_2 = "test/ssk/ssk128/SSK128_XML_MessageData_Split2.xml";
	private static final String MOCK_MESSAGE_RESPONSE_GET_DATA_3 = "test/ssk/ssk128/SSK128_XML_MessageData_Split3.xml";
	private static final String MOCK_MESSAGE_RESPONSE_GET_DATA_4 = "test/ssk/ssk128/SSK128_XML_MessageData_Split4.xml";
	private static final String MOCK_MESSAGE_RESPONSE_GET_DATA_5 = "test/ssk/ssk128/SSK128_XML_MessageData_Split5.xml";
	
	private static final String MOCK_MESSAGE_RESPONSE_DELETE_1 = "test/ssk/ssk128/SSK128_XML_DELETE_Response_Split1.xml";
	private static final String MOCK_MESSAGE_RESPONSE_DELETE_2 = "test/ssk/ssk128/SSK128_XML_DELETE_Response_Split2.xml";
	private static final String MOCK_MESSAGE_RESPONSE_DELETE_3 = "test/ssk/ssk128/SSK128_XML_DELETE_Response_Split3.xml";
	private static final String MOCK_MESSAGE_RESPONSE_DELETE_4 = "test/ssk/ssk128/SSK128_XML_DELETE_Response_Split4.xml";
	private static final String MOCK_MESSAGE_RESPONSE_DELETE_5 = "test/ssk/ssk128/SSK128_XML_DELETE_Response_Split5.xml";

	private static final String MOCK_MESSAGE_DATA_SPLIT1 = "test/ssk/ssk128/SSK128_XML_Message_Split1.xml";
	private static final String MOCK_MESSAGE_DATA_SPLIT2 = "test/ssk/ssk128/SSK128_XML_Message_Split2.xml";
	private static final String MOCK_MESSAGE_DATA_SPLIT3 = "test/ssk/ssk128/SSK128_XML_Message_Split3.xml";
	private static final String MOCK_MESSAGE_DATA_SPLIT4 = "test/ssk/ssk128/SSK128_XML_Message_Split4.xml";
	private static final String MOCK_MESSAGE_DATA_SPLIT5 = "test/ssk/ssk128/SSK128_XML_Message_Split5.xml";

	private int pollCounter;
	private int splitCounter;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();

		componentProperties.add(PROP_LOCAL_POLL_URI);
		componentProperties.add(PROP_LOCAL_IS_LOOP);
		componentProperties.add(PROP_LOCAL_MESSAGE_ID);
		componentProperties.add(PROP_LOCAL_MESSAGE_URI);
		componentProperties.add(PROP_LOCAL_MESSAGE_TIMESTAMP);
		
		pollCounter = 0;
		splitCounter = 0;
		
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);

		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="PurgeQueue")
	public void testEmpty() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_BEFORE_DELETE, true);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}

	@UnitTest(startComponent="PurgeQueue")
	public void testSingleSetWithLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_BEFORE_DELETE, true);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}

	@UnitTest(startComponent="PurgeQueue")
	public void testSingleSetWithoutLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_BEFORE_DELETE, false);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}

	@UnitTest(startComponent="PurgeQueue")
	public void testMultipleSets() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_BEFORE_DELETE, true);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
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

	@AssertAfter(id="InitializeAndFinalize_128", step="SetValues")
	public void verifyInitialization() throws Exception {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_POLL_URI), VALUE_MOCK_ENDPOINT + VALUE_POLL_URI, (String)ctx.getProperty(PROP_LOCAL_POLL_URI));
		assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_IS_LOOP), (boolean)ctx.getProperty(PROP_LOCAL_IS_LOOP));
	}

	@AssertAfter(id="AllMessages_128")
	public void updatePollCounter() throws Exception {
		pollCounter++;
		splitCounter = 0;
	}

	@AssertAfter(id="EvaluateQueue_128")
	public void verifyQueueProcessing() throws Exception {

		if ("testEmpty".equalsIgnoreCase(getName()) ||
				"testSingleSetWithLog".equalsIgnoreCase(getName()) ||
				"testSingleSetWithoutLog".equalsIgnoreCase(getName())) {
			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_IS_LOOP), (boolean)ctx.getProperty(PROP_LOCAL_IS_LOOP));
		} else if ("testMultipleSets".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_IS_LOOP), (pollCounter == 1), (boolean)ctx.getProperty(PROP_LOCAL_IS_LOOP));
		}
	}

	@AssertAfter(id="SplitMessages_128")
	public void verifySplitter() throws Exception {
		if (!"testEmpty".equalsIgnoreCase(getName())) {
			splitCounter++;
			
			switch (splitCounter) {
				case 1 :
					if (pollCounter == 1) {
						assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_DATA_SPLIT1),
								compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_DATA_SPLIT1, CONTENT_TYPE_TEXT_XML));
					} else if ((pollCounter > 1) && "testMultipleSets".equalsIgnoreCase(getName())) {
						assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_DATA_SPLIT5),
								compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_DATA_SPLIT5, CONTENT_TYPE_TEXT_XML));
					} else {
						fail("unexpected case for " + getName() + " pollCounter = " + pollCounter + " splitCounter = " + splitCounter);
					}
					break;
					
				case 2 :
					if (pollCounter == 1) {
						assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_DATA_SPLIT2),
								compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_DATA_SPLIT2, CONTENT_TYPE_TEXT_XML));
					} else if (pollCounter > 2) {
						fail(getName() + " should not have made another loop with pollCounter = " + String.valueOf(splitCounter));
					}
					break;
					
				case 3 :
					assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_DATA_SPLIT3),
							compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_DATA_SPLIT3, CONTENT_TYPE_TEXT_XML));
					break;
					
				case 4 :
					assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_DATA_SPLIT4),
							compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_DATA_SPLIT4, CONTENT_TYPE_TEXT_XML));
					break;
					
				default :
					//No op for final splitter loop to confirm empty on multiple sets test
					break;
			}
		}
	}

	@AssertAfter(id="CatchLocalOutErrors_128")
	public void verifyMessageProcessing() throws Exception {
		if ("testEmpty".equalsIgnoreCase(getName())) {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		}
		
		if (pollCounter == 1) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_MESSAGE_ID), String.valueOf(splitCounter), (String)ctx.getProperty(PROP_LOCAL_MESSAGE_ID));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_MESSAGE_URI), VALUE_MESSAGE_URI + String.valueOf(splitCounter), (String)ctx.getProperty(PROP_LOCAL_MESSAGE_URI));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_MESSAGE_TIMESTAMP), VALUE_MESSAGE_TIMESTAMP, (String)ctx.getProperty(PROP_LOCAL_MESSAGE_TIMESTAMP));
		} else if (pollCounter == 2) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_MESSAGE_ID), "5", (String)ctx.getProperty(PROP_LOCAL_MESSAGE_ID));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_MESSAGE_URI), VALUE_MESSAGE_URI + "5", (String)ctx.getProperty(PROP_LOCAL_MESSAGE_URI));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_MESSAGE_TIMESTAMP), VALUE_MESSAGE_TIMESTAMP, (String)ctx.getProperty(PROP_LOCAL_MESSAGE_TIMESTAMP));
		} else {
			fail(getName() + " should not have made another splitter loop with counter = " + String.valueOf(splitCounter));
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
	protected Throwable loadRestCallQueue128() throws Exception {
		if ("testEmpty".equalsIgnoreCase(getName())) {
			setMessagePart(0, MOCK_MESSAGE_RESPONSE_POLL_EMPTY, CONTENT_TYPE_TEXT_XML);
		} else {
			switch (pollCounter) {
				case 1 :
					if ("testSingleSetWithLog".equalsIgnoreCase(getName()) ||
							"testSingleSetWithoutLog".equalsIgnoreCase(getName())) {
						setMessagePart(0, MOCK_MESSAGE_RESPONSE_POLL_PART_1OF1, CONTENT_TYPE_TEXT_XML);
					} else if ("testMultipleSets".equalsIgnoreCase(getName())) {
						setMessagePart(0, MOCK_MESSAGE_RESPONSE_POLL_PART_1OF2, CONTENT_TYPE_TEXT_XML);
					}	
					break;
					
				case 2 :
					if ("testSingleSetWithLog".equalsIgnoreCase(getName()) ||
							"testSingleSetWithoutLog".equalsIgnoreCase(getName())) {
						fail(getName() + " should not have made a REST call with counter = " + String.valueOf(pollCounter));
					} else if ("testMultipleSets".equalsIgnoreCase(getName())) {
						setMessagePart(0, MOCK_MESSAGE_RESPONSE_POLL_PART_2OF2, CONTENT_TYPE_TEXT_XML);
					}	
					break;
					
				default :
					fail(getName() + " should not have made a REST call with counter = " + String.valueOf(splitCounter));
					break;
			}
		}
	
		return null;
	}

	@Override
	protected Throwable loadRestCallQueueRead128() throws Exception {
		switch (splitCounter) {
			case 1 :
				if (pollCounter == 1) {
					setMessagePart(0, MOCK_MESSAGE_RESPONSE_GET_DATA_1, CONTENT_TYPE_TEXT_XML);
				} else {
					setMessagePart(0, MOCK_MESSAGE_RESPONSE_GET_DATA_5, CONTENT_TYPE_TEXT_XML);
				}
				break;
				
			case 2 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_GET_DATA_2, CONTENT_TYPE_TEXT_XML);
				break;
				
			case 3 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_GET_DATA_3, CONTENT_TYPE_TEXT_XML);
				break;
				
			case 4 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_GET_DATA_4, CONTENT_TYPE_TEXT_XML);
				break;
				
			default :
				fail(getName() + " should not have made a GET call with counter = " + String.valueOf(splitCounter));
				break;
		}
		
		return null;
	}

	@Override
	protected Throwable loadRestCallQueueDelete128() throws Exception {
		switch (splitCounter) {
			case 1 :
				if (pollCounter == 1) {
					setMessagePart(0, MOCK_MESSAGE_RESPONSE_DELETE_1, CONTENT_TYPE_TEXT_XML);
				} else {
					setMessagePart(0, MOCK_MESSAGE_RESPONSE_DELETE_5, CONTENT_TYPE_TEXT_XML);
				}
				break;
				
			case 2 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_DELETE_2, CONTENT_TYPE_TEXT_XML);
				break;
				
			case 3 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_DELETE_3, CONTENT_TYPE_TEXT_XML);
				break;
				
			case 4 :
				setMessagePart(0, MOCK_MESSAGE_RESPONSE_DELETE_4, CONTENT_TYPE_TEXT_XML);
				break;
				
			default :
				fail(getName() + " should not have made a DELETE call with counter = " + String.valueOf(splitCounter));
				break;
		}

		return null;
	}


}
