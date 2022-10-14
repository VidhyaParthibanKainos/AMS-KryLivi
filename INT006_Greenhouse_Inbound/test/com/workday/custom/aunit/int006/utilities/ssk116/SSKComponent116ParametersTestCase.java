package com.workday.custom.aunit.int006.utilities.ssk116;

import com.capeclear.mediation.MediationException;
import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.Action.Type;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

/**
 * Basic test of functionality for the XSLT Block Splitter parameters
 * 
 * @author Krister Schwertfuehrer
 * @author john.smail
 */
@AssemblyTest(project="INT006_Greenhouse_Inbound", displayLabel="Test runner for XSLT block splitter Sub-Assembly Parameters")
public class SSKComponent116ParametersTestCase extends UtilitiesTestCase {
	
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inDataLocation";
	public static final String PROP_PARAMETER_IN_BLOCK_SIZE = "inBlockSize";
	public static final String PROP_PARAMETER_IN_ITEM_XPATH = "inItemXPath";
	public static final String PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR = "inIsNoBlockSplitAnError";
	public static final String PROP_PARAMETER_IN_IS_USE_PARALLEL = "inIsUseParallel";
	public static final String PROP_PARAMETER_IN_PROCESS_ENDPOINT = "inProcessEndpoint";
	public static final String PROP_PARAMETER_IN_AGGREGATION_ENDPOINT = "inAggregationEndpoint";
	public static final String PROP_PARAMETER_IN_THREAD_COUNT = "inParallelThreadCount";
	public static final String PROP_PARAMETER_IN_THREAD_TIMEOUT = "inParallelThreadTimeout";
	public static final String PROP_PARAMETER_IN_SPLITTER_TIMEOUT = "inParallelSplitterTimeout";	
	public static final String PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION = "inReturnOutputLocation";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";

	public static final String MOCK_SSK116_PROCESS_ENDPOINT = "vm://int006_Greenhouse_Inbound/IntegrationEventProgress";
	public static final String MOCK_SSK116_AGGREGATION_ENDPOINT = "vm://int006_Greenhouse_Inbound/CloudLogXSLTMessages";
	public static final String MOCK_SSK116_XML_BLOCKS_SIZE4 = "test/ssk/ssk116/SSK116_XML_Blocks_BlockSize4.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();

		// Set the inBlockSize param to a sample value
		ctx.setProperty(PROP_PARAMETER_IN_BLOCK_SIZE, 12);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_NO_BLOCK_SPLIT_AN_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*");
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
	public void testInReturnOutputLocationNotSet() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testInReturnOutputLocationAsMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}

	@UnitTest(startComponent="BlockSplitter")
	public void testInReturnOutputLocationAsVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "someVariableName");
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}

	@UnitTest(startComponent="BlockSplitter")
	public void testInDataLocationNotSet() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, null);
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testInDataLocationAsMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}

	@UnitTest(startComponent="BlockSplitter")
	public void testInDataLocationAsInvalidVariable() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "someVariableName");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "someVariableName");
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
	}

	@UnitTest(startComponent="BlockSplitter")
	public void testInDataLocationAsValidVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "someVariableName");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, "someVariableName");
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, false);
		
		setVariable("someVariableName", MOCK_SSK116_XML_BLOCKS_SIZE4, CONTENT_TYPE_TEXT_XML);
	}

	///////////////////////////////////////////////////////
	//		Tests for parallel version
	///////////////////////////////////////////////////////
	
	@UnitTest(startComponent="BlockSplitter")
	public void testParallelParameters() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATION_ENDPOINT, MOCK_SSK116_AGGREGATION_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_THREAD_COUNT, 4);
		ctx.setProperty(PROP_PARAMETER_IN_THREAD_TIMEOUT, 60);
		ctx.setProperty(PROP_PARAMETER_IN_SPLITTER_TIMEOUT, 3600);	

		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, true);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testParallelParametersWithDefaults() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATION_ENDPOINT, MOCK_SSK116_AGGREGATION_ENDPOINT);

		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, true);
	}
	
	@UnitTest(startComponent="BlockSplitter")
	public void testParallelParametersMissingProcess() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATION_ENDPOINT, MOCK_SSK116_AGGREGATION_ENDPOINT);

		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, true);
	}

	@UnitTest(startComponent="BlockSplitter")
	public void testParallelParametersMissingAggregation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, MOCK_SSK116_PROCESS_ENDPOINT);

		ctx.setProperty(PROP_PARAMETER_IN_RETURN_OUTPUT_LOCATION, null);
		ctx.setProperty(PROP_PARAMETER_IN_IS_USE_PARALLEL, true);
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
	
	@Override
	protected void unhandledExceptionVerification(Throwable t) throws Exception {
		if (!testsExpectingHandledExceptions.contains(getName())) {
			super.unhandledExceptionVerification(t);
		}
		
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, "exception"), t instanceof MediationException);
		
		if ("testInReturnOutputLocationNotSet".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error code"), 11601, ((MediationException)t).getErrorNumber());
		} else if ("testInDataLocationNotSet".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error code"), 11600, ((MediationException)t).getErrorNumber());
		} else if ("testInDataLocationAsInvalidVariable".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error code"), 11600, ((MediationException)t).getErrorNumber());
		} else {
			super.unhandledExceptionVerification(t);
		}
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	@AtComponent(id="BuildBlockMessage_116")
	public Action end() {
		return new StandardAction(Type.terminate);
	}
	
}
