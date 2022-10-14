package com.workday.custom.aunit.int006.utilities.ssk116;

import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.capeclear.mediation.MediationException;
import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

/**
 * Basic test of functionality for the XSLT Block Splitter
 * 
 * @author Doug Lee
 * @author Krister Schwertfuehrer
 * @author john.smail
 */
@AssemblyTest(project="INT006_Greenhouse_Inbound", displayLabel="Test runner for XSLT block splitter")
public class SSKComponent116TestCase extends UtilitiesTestCase {
	
	Logger log = LogControl.getLogger( SSKComponent116TestCase.class );
	
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inDataLocation";
	public static final String PROP_PARAMETER_IN_BLOCK_SIZE = "inBlockSize";
	public static final String PROP_PARAMETER_IN_ITEM_XPATH = "inItemXPath";
	public static final String PROP_PARAMETER_IN_IS_USE_PARALLEL = "inIsUseParallel";
	public static final String PROP_PARAMETER_IN_PROCESS_ENDPOINT = "inProcessEndpoint";
	public static final String PROP_PARAMETER_IN_AGGREGATION_ENDPOINT = "inAggregationEndpoint";
	public static final String PROP_PARAMETER_IN_THREAD_COUNT = "inParallelThreadCount";
	public static final String PROP_PARAMETER_IN_THREAD_TIMEOUT = "inParallelThreadTimeout";
	public static final String PROP_PARAMETER_IN_SPLITTER_TIMEOUT = "inParallelSplitterTimeout";	
	public static final String PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION = "inReturnResults";
	public static final String PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR = "inIsNoBlockSplitAnError";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	
	public static final String PROP_PARAMETER_IN_PROGRESS_PERCENTAGE = "inProgressPercentage";
	public static final String VALUE_INPUT_VARIABLE_NAME = "blockSplitSource";
	public static final String VALUE_RETURN_OUTPUT_VARIABLE_NAME = "blockSplitResults";
	public static final int VALUE_REPORT_BLOCKS = 18;

	public static final String MOCK_SSK116_PROCESS_ENDPOINT = "vm://int006_Greenhouse_Inbound/IntegrationEventProgress";
	public static final String MOCK_SSK116_AGGREGATION_ENDPOINT = "vm://int006_Greenhouse_Inbound/CloudLogXSLTMessages";
	
	public static final String MOCK_SSK116_RAAS_RESPONSE = "test/ssk/ssk116/SSK116_ReportResponse.xml";
	public static final String MOCK_SSK116_RAAS_RESPONSE_NO_SPLIT = "test/ssk/ssk116/SSK116_ReportResponse_NoSplit.xml";
	public static final String MOCK_SSK116_XML_BLOCKS_NOSPLIT = "test/ssk/ssk116/SSK116_XML_Blocks_NoSplit.xml";
	public static final String MOCK_SSK116_XML_BLOCKS_SIZE3 = "test/ssk/ssk116/SSK116_XML_Blocks_BlockSize3.xml";
	public static final String MOCK_SSK116_XML_BLOCKS_SIZE4 = "test/ssk/ssk116/SSK116_XML_Blocks_BlockSize4.xml";
	public static final String MOCK_SSK116_XML_BLOCKS_SIZE6 = "test/ssk/ssk116/SSK116_XML_Blocks_BlockSize6.xml";
	public static final String MOCK_SSK116_XML_BLOCK6_SPLIT1 = "test/ssk/ssk116/SSK116_XML_BlockSize6_Split1.xml";
	public static final String MOCK_SSK116_XML_BLOCK6_SPLIT2 = "test/ssk/ssk116/SSK116_XML_BlockSize6_Split2.xml";
	public static final String MOCK_SSK116_XML_BLOCK6_SPLIT3 = "test/ssk/ssk116/SSK116_XML_BlockSize6_Split3.xml";
	public static final String MOCK_SSK116_XML_BLOCK3_SPLIT1 = "test/ssk/ssk116/SSK116_XML_BlockSize3_Split1.xml";
	public static final String MOCK_SSK116_XML_BLOCK3_SPLIT2 = "test/ssk/ssk116/SSK116_XML_BlockSize3_Split2.xml";
	public static final String MOCK_SSK116_XML_BLOCK3_SPLIT3 = "test/ssk/ssk116/SSK116_XML_BlockSize3_Split3.xml";
	public static final String MOCK_SSK116_XML_BLOCK3_SPLIT4 = "test/ssk/ssk116/SSK116_XML_BlockSize3_Split4.xml";
	public static final String MOCK_SSK116_XML_BLOCK3_SPLIT5 = "test/ssk/ssk116/SSK116_XML_BlockSize3_Split5.xml";
	public static final String MOCK_SSK116_XML_BLOCK3_SPLIT6 = "test/ssk/ssk116/SSK116_XML_BlockSize3_Split6.xml";

	int	blockSize;
	String processEndpoint;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		super.setupGlobalInitialization();
		mockTracker.addComponentTracking("InitializeAndFinalize_XSLT_112");

		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, true);

		processEndpoint = null;
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	///////////////////////////////////////////////////////
	//		Tests for serial version
	///////////////////////////////////////////////////////
	
	@UnitTest(startComponent="BlockSplitter")
	public void testBlockSize3FromVariableToMessageWildcardNoEndpoint() throws Exception {
		setVariable(VALUE_INPUT_VARIABLE_NAME, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_INPUT_VARIABLE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 3);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testBlockSize3FromMessageToMessageWildcardNoEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 3);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testBlockSize4FromMessageToMessageWildcardNoEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 4);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testBlockSize4FromMessageToVariableWildcardNoEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 4);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, VALUE_RETURN_OUTPUT_VARIABLE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testBlockSize3FromMessageToMessageWildcardWithEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 3);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, processEndpoint = MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);

		//this is set just to satisfy the dependencies of using 108.  108 was chosen to make things simple for the test.
		ctx.setProperty("inProgressPercentage", "10");
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testBlockSize6FromMessageToVariableWildcardWithEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 6);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, VALUE_RETURN_OUTPUT_VARIABLE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, processEndpoint = MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);

		//this is set just to satisfy the dependencies of using 108.  108 was chosen to make things simple for the test.
		ctx.setProperty("inProgressPercentage", "10");
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testBlockSize4FromMessageToVariableXPathNoEnpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 4);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*:Report_Data/*:Report_Entry");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, VALUE_RETURN_OUTPUT_VARIABLE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testNoSplitNotErrorFromMessageToMessageXPathNoEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE_NO_SPLIT, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 4);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*:Report_Data/*:Report_Entry_NOMATCH");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testNoSplitNotErrorFromMessageToMessageXPathWithEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 6);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*:Report_Data/*:Report_Entry_NOMATCH");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, processEndpoint = MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);

		//this is set just to satisfy the dependencies of using 108.  108 was chosen to make things simple for the test.
		ctx.setProperty("inProgressPercentage", "10");
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testNoSplitThrowsError() throws Exception {
		expectHandledException();
		
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE_NO_SPLIT, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 6);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}

	///////////////////////////////////////////////////////
	//		Tests for parallel version
	///////////////////////////////////////////////////////
	
	@UnitTest(startComponent="BlockSplitter")
	public void testBlockSize3FromMessageToMessageWildcardWithParallelEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 3);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, processEndpoint = MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATION_ENDPOINT, MOCK_SSK116_AGGREGATION_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_THREAD_COUNT, 4);
		ctx.setProperty(PROP_PARAMETER_IN_THREAD_TIMEOUT, 60);
		ctx.setProperty(PROP_PARAMETER_IN_SPLITTER_TIMEOUT, 3600);	

		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, true);

		//this is set just to satisfy the dependencies of using 108.  108 was chosen to make things simple for the test.
		ctx.setProperty("inProgressPercentage", "10");
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testBlockSize6FromMessageToVariableWildcardWithParallelEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 6);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, processEndpoint = MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATION_ENDPOINT, MOCK_SSK116_AGGREGATION_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_THREAD_COUNT, 4);
		ctx.setProperty(PROP_PARAMETER_IN_THREAD_TIMEOUT, 60);
		ctx.setProperty(PROP_PARAMETER_IN_SPLITTER_TIMEOUT, 3600);	

		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, true);

		//this is set just to satisfy the dependencies of using 108.  108 was chosen to make things simple for the test.
		ctx.setProperty("inProgressPercentage", "10");
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testNoSplitNotErrorFromMessageToMessageXPathWithParallelEndpoint() throws Exception {
		setMessagePart(0, MOCK_SSK116_RAAS_RESPONSE, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, blockSize = 6);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*:Report_Data/*:Report_Entry_NOMATCH");
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, false);
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, processEndpoint = MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATION_ENDPOINT, MOCK_SSK116_AGGREGATION_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_THREAD_COUNT, 4);
		ctx.setProperty(PROP_PARAMETER_IN_THREAD_TIMEOUT, 60);
		ctx.setProperty(PROP_PARAMETER_IN_SPLITTER_TIMEOUT, 3600);	

		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, true);

		//this is set just to satisfy the dependencies of using 108.  108 was chosen to make things simple for the test.
		ctx.setProperty("inProgressPercentage", "10");
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
		int countOfEndpointCalls = mockTracker.getCallCount("PIE_108");
		int countOfAggregationCalls = mockTracker.getCallCount("InitializeAndFinalize_XSLT_112");
		
		log.info("Number of endpoint calls = " + countOfEndpointCalls);
		log.info("Number of aggregation calls = " + countOfAggregationCalls);
		
		switch (getName()) {
			case "testBlockSize3FromVariableToMessageWildcardNoEndpoint" :
				assertEquals("No blocks should have been sent to a processing endpoint", 0, countOfEndpointCalls);
				break;
			case "testBlockSize3FromMessageToMessageWildcardNoEndpoint" :
				assertEquals("No blocks should have been sent to a processing endpoint", 0, countOfEndpointCalls);
				break;
			case "testBlockSize4FromMessageToMessageWildcardNoEndpoint" :
				assertEquals("No blocks should have been sent to a processing endpoint", 0, countOfEndpointCalls);
				break;
			case "testBlockSize4FromMessageToVariableWildcardNoEndpoint" :
				assertEquals("No blocks should have been sent to a processing endpoint", 0, countOfEndpointCalls);
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VALUE_RETURN_OUTPUT_VARIABLE_NAME),
						compareAgainstVariable(VALUE_RETURN_OUTPUT_VARIABLE_NAME, CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE4, CONTENT_TYPE_TEXT_XML));
				break;
			case "testBlockSize3FromMessageToMessageWildcardWithEndpoint" :
				assertEquals("The incorrect number of blocks was sent to the processing endpoint", VALUE_REPORT_BLOCKS/blockSize, countOfEndpointCalls);
				break;
			case "testBlockSize6FromMessageToVariableWildcardWithEndpoint" :
				assertEquals("The incorrect number of blocks was sent to the processing endpoint", VALUE_REPORT_BLOCKS/blockSize, countOfEndpointCalls);
				assertTrue(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VALUE_RETURN_OUTPUT_VARIABLE_NAME), isVariableNullOrUndefined(VALUE_RETURN_OUTPUT_VARIABLE_NAME));
				break;
			case "testBlockSize4FromMessageToVariableXPathNoEnpoint" :
				assertEquals("No blocks should have been sent to a processing endpoint", 0, countOfEndpointCalls);
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VALUE_RETURN_OUTPUT_VARIABLE_NAME),
						compareAgainstVariable(VALUE_RETURN_OUTPUT_VARIABLE_NAME, CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE4, CONTENT_TYPE_TEXT_XML));
				break;
			case "testNoSplitNotErrorFromMessageToMessageXPathNoEndpoint" :
				assertEquals("No blocks should have been sent to a processing endpoint", 0, countOfEndpointCalls);
				break;
			case "testNoSplitNotErrorFromMessageToMessageXPathWithEndpoint" :
				assertEquals("No blocks should have been sent to a processing endpoint", 0, countOfEndpointCalls);
				break;
			case "testNoSplitThrowsError" :
				assertEquals("No blocks should have been sent to a processing endpoint", 0, countOfEndpointCalls);
				break;
			case "testBlockSize3FromMessageToMessageWildcardWithParallelEndpoint" :
				assertEquals("The incorrect number of blocks was sent to the processing endpoint", VALUE_REPORT_BLOCKS/blockSize, countOfEndpointCalls);
				assertEquals("The incorrect number of aggregation endpoints occurred", 1 + VALUE_REPORT_BLOCKS/blockSize, countOfAggregationCalls);
				break;
			case "testBlockSize6FromMessageToVariableWildcardWithParallelEndpoint" :
				assertEquals("The incorrect number of blocks was sent to the processing endpoint", VALUE_REPORT_BLOCKS/blockSize, countOfEndpointCalls);
				assertEquals("The incorrect number of aggregation endpoints occurred", 1 + VALUE_REPORT_BLOCKS/blockSize, countOfAggregationCalls);
				assertTrue(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VALUE_RETURN_OUTPUT_VARIABLE_NAME), isVariableNullOrUndefined(VALUE_RETURN_OUTPUT_VARIABLE_NAME));
				break;
			case "testNoSplitNotErrorFromMessageToMessageXPathWithParallelEndpoint" :
				assertEquals("No blocks should have been sent to a processing endpoint", 0, countOfEndpointCalls);
				assertEquals("No aggregation endpoints should have occurred", 0, countOfAggregationCalls);
				break;
			default :
				break;
		}
	}
	
	@Override
	protected void unhandledExceptionVerification(Throwable t) throws Exception {
		if (!testsExpectingHandledExceptions.contains(getName())) {
			super.unhandledExceptionVerification(t);
		}
		
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, "exception"), t instanceof MediationException);
		
		if ("testNoSplitThrowsError".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error code"), 11602, ((MediationException)t).getErrorNumber());
		} else {
			super.unhandledExceptionVerification(t);
		}
	}
	
	@AssertAfter(id="BuildBlockMessage_116")
	public void validateBlockGeneration() throws Exception {
		switch (getName()) {
			case "testBlockSize3FromVariableToMessageWildcardNoEndpoint" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_SIZE3),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE3, CONTENT_TYPE_TEXT_XML));
				break;
			case "testBlockSize3FromMessageToMessageWildcardNoEndpoint" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_SIZE3),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE3, CONTENT_TYPE_TEXT_XML));
				break;
			case "testBlockSize4FromMessageToMessageWildcardNoEndpoint" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_SIZE4),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE4, CONTENT_TYPE_TEXT_XML));
				break;
			case "testBlockSize4FromMessageToVariableWildcardNoEndpoint" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_SIZE4),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE4, CONTENT_TYPE_TEXT_XML));
				break;
			case "testBlockSize3FromMessageToMessageWildcardWithEndpoint" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_SIZE3),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE3, CONTENT_TYPE_TEXT_XML));
				break;
			case "testBlockSize6FromMessageToVariableWildcardWithEndpoint" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_SIZE6),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE6, CONTENT_TYPE_TEXT_XML));
				break;
			case "testBlockSize4FromMessageToVariableXPathNoEnpoint" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_SIZE4),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE4, CONTENT_TYPE_TEXT_XML));
				break;
			case "testNoSplitNotErrorFromMessageToMessageXPathNoEndpoint" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_NOSPLIT),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_NOSPLIT, CONTENT_TYPE_TEXT_XML));
				break;
			case "testNoSplitNotErrorFromMessageToMessageXPathWithEndpoint" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_NOSPLIT),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_NOSPLIT, CONTENT_TYPE_TEXT_XML));
				break;
			case "testNoSplitThrowsError" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCKS_NOSPLIT),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_NOSPLIT, CONTENT_TYPE_TEXT_XML));
				break;
			default :
				break;
		}
	}
	
	@AssertAfter(id="StoreOutput_116")
	public void validateVariableStorage() throws Exception {
		if ("testBlockSize3FromVariableToMessageWildcardNoEndpoint".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VALUE_RETURN_OUTPUT_VARIABLE_NAME),
					compareAgainstVariable(VALUE_RETURN_OUTPUT_VARIABLE_NAME, CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE3, CONTENT_TYPE_TEXT_XML));
		} else if ("testBlockSize4FromMessageToVariableWildcardNoEndpoint".equalsIgnoreCase(getName()) ||
				"testBlockSize4FromMessageToVariableXPathNoEnpoint".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VALUE_RETURN_OUTPUT_VARIABLE_NAME),
					compareAgainstVariable(VALUE_RETURN_OUTPUT_VARIABLE_NAME, CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE4, CONTENT_TYPE_TEXT_XML));
		} else if ("testBlockSize6FromMessageToVariableWildcardWithEndpoint".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VALUE_RETURN_OUTPUT_VARIABLE_NAME),
					compareAgainstVariable(VALUE_RETURN_OUTPUT_VARIABLE_NAME, CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCKS_SIZE6, CONTENT_TYPE_TEXT_XML));
		} else if ("testNoSplitThrowsError".equalsIgnoreCase(getName())) {
			fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
		} else {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VALUE_RETURN_OUTPUT_VARIABLE_NAME), !ctx.getVariables().containsKey(VALUE_RETURN_OUTPUT_VARIABLE_NAME));
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
	@AtComponent(id="PIE_108")
	public Action mockIntegrationEventProgressPIE() throws Exception {
		if ("testBlockSize6FromMessageToVariableWildcardWithEndpoint".equalsIgnoreCase(getName())) {
			switch (mockTracker.getCallCount("PIE_108") + 1) {
			case 1 :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VALUE_RETURN_OUTPUT_VARIABLE_NAME),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCK6_SPLIT1, CONTENT_TYPE_TEXT_XML));
				break;
			case 2 :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VALUE_RETURN_OUTPUT_VARIABLE_NAME),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCK6_SPLIT2, CONTENT_TYPE_TEXT_XML));
				break;
			case 3 :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VALUE_RETURN_OUTPUT_VARIABLE_NAME),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCK6_SPLIT3, CONTENT_TYPE_TEXT_XML));
				break;
			default :
				break;
			}
		} else if ("testBlockSize3FromMessageToMessageWildcardWithEndpoint".equalsIgnoreCase(getName())) {
			switch (mockTracker.getCallCount("PIE_108") + 1) {
			case 1 :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCK3_SPLIT1),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCK3_SPLIT1, CONTENT_TYPE_TEXT_XML));
				break;
			case 2 :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCK3_SPLIT2),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCK3_SPLIT2, CONTENT_TYPE_TEXT_XML));
				break;
			case 3 :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCK3_SPLIT3),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCK3_SPLIT3, CONTENT_TYPE_TEXT_XML));
				break;
			case 4 :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCK3_SPLIT4),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCK3_SPLIT4, CONTENT_TYPE_TEXT_XML));
				break;
			case 5 :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCK3_SPLIT5),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCK3_SPLIT5, CONTENT_TYPE_TEXT_XML));
				break;
			case 6 :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK116_XML_BLOCK3_SPLIT6),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK116_XML_BLOCK3_SPLIT6, CONTENT_TYPE_TEXT_XML));
				break;
			default :
				break;
			}
		}
		return super.mockIntegrationEventProgressPIE();
	}
	
	@AtComponent(id="InitializeAndFinalize_XSLT_112")
	public Action mockCloudLogXsltMessages() throws Exception {
		mockTracker.incrementCallCount("InitializeAndFinalize_XSLT_112");
		return new StandardAction(Action.Type.mock);
	}
	
}
