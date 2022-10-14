package com.workday.custom.aunit.int006.utilities.ssk104;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;
import com.workday.custom.int006.ssk142.CloudLogMessage;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class AggregateParallel104TestCase extends UtilitiesTestCase {

	public static final String VAR_LOCAL_C2P_AGGREGATE_PAGE = "c2pPageToAggregate104";
	public static final String VAR_LOCAL_AGGREGATOR_RESULT = "c2pAggregatedResult104";

	public static final String PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION = "inParallelSuppressAggregation";
	public static final String PROP_PARAMETER_IN_RESULT_HEADER = "inAggregatedResultsHeader";
	public static final String PROP_PARAMETER_IN_RESULT_FOOTER = 	"inAggregatedResultsFooter";
	
	public static final String PROP_LOCAL_IS_DATA_TRANSFERRED = "localIsC2PData";
	public static final String PROP_LOCAL_PARALLEL_LOG = "localParallelLog143";
	

	public static final String VALUE_HEADER = "<SoapResults xmlns:wd=\"urn:com.workday/bsvc\">";
	public static final String VALUE_FOOTER = "</SoapResults>";

	public static final String MOCK_MESSAGE_SSK104_TRANSFERRED_DATA = "test/ssk/ssk104/SSK104_XML_PreAggregatorData.xml";
	public static final String MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT = "test/ssk/ssk104/SSK104_XML_AggregatorResult.xml";
	public static final String MOCK_MESSAGE_SSK104_EMPTY_AGGREGATOR_RESULT = "test/ssk/ssk104/SSK104_XML_EmptyAggregatorResult.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		setupGlobalInitialization();
		
		@SuppressWarnings("rawtypes")
		ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, queue);
		
		ctx.setProperty(PROP_STUDIO_PARALLEL_AGGREGATOR_COLLATE, true);
		ctx.setProperty(PROP_STUDIO_PARALLEL_AGGREGATOR_BATCH, true);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="AggregateParallel_104")
	public void testCollateAndBatchData() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, false);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_HEADER, VALUE_HEADER);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_FOOTER, VALUE_FOOTER);

		setVariable(VAR_LOCAL_C2P_AGGREGATE_PAGE, MOCK_MESSAGE_SSK104_TRANSFERRED_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AggregateParallel_104")
	public void testCollateAndBatchNoData() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, false);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_HEADER, VALUE_HEADER);
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_FOOTER, VALUE_FOOTER);
	}

	@UnitTest(startComponent="AggregateParallel_104")
	public void testAggregationSuppressed() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, true);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@SuppressWarnings("rawtypes")
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {
		switch (getName()) {
		case "testCollateAndBatchData" :
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_LOCAL_AGGREGATOR_RESULT),
					compareAgainstVariable(VAR_LOCAL_AGGREGATOR_RESULT, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML));
			break;
			
		case "testCollateAndBatchNoData" :
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_LOCAL_AGGREGATOR_RESULT),
					compareAgainstVariable(VAR_LOCAL_AGGREGATOR_RESULT, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK104_EMPTY_AGGREGATOR_RESULT, CONTENT_TYPE_TEXT_XML));
			break;
			
		case "testAggregationSuppressed" :
			assertTrue(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VAR_LOCAL_AGGREGATOR_RESULT), isVariableNullOrUndefined(VAR_LOCAL_AGGREGATOR_RESULT));
			break;
			
		default :
			break;
		}
		
		if ("testAggregationSuppressed".equalsIgnoreCase(getName())) {
			ConcurrentLinkedQueue queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PARALLEL_LOG), 1, queue.size());
			
			Object message = queue.remove();
			if (message instanceof CloudLogMessage) {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "queue message"), "PagedGet Aggregation Suppressed", ((CloudLogMessage)message).getSummary());
			} else {
				fail(String.format(MESSAGE_UNEXPECTED_TYPE, "threaded queue message"));
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


}
