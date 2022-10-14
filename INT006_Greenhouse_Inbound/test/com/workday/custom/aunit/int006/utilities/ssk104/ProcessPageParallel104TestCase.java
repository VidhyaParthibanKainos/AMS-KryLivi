package com.workday.custom.aunit.int006.utilities.ssk104;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;
import com.workday.custom.int006.ssk142.CloudLogMessage;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class ProcessPageParallel104TestCase extends UtilitiesTestCase {

	public static final String VAR_LOCAL_C2P_AGGREGATE_PAGE = "c2pPageToAggregate104";
	public static final String VAR_LOCAL_AGGREGATION_XSLT = "localAggregationXslt104";

	public static final String PROP_PARAMETER_IN_RESULT_XPATH = "inXpathToResultsToAggregate";
	public static final String PROP_PARAMETER_IN_PARALLEL_ENDPOINT = "inParallelPageProcessorEndpoint";
	public static final String PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION = "inParallelSuppressAggregation";
	public static final String PROP_PARAMETER_IN_DYNAMIC_ENDPOINT_REQUIRED = "inProgressPercentage";
	
	public static final String PROP_LOCAL_PARALLEL_LOG = "localParallelLog143";
	
	public static final String VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL = "/env:Envelope/env:Body/wd:Get_Event_Details_Response/wd:Response_Data/wd:Event";
	public static final String VALUE_DYNAMIC_ENDPOINT = "vm://int006_Greenhouse_Inbound/IntegrationEventProgress";
	public static final String VALUE_INVALID_ENDPOINT = "vm://int006_Greenhouse_Inbound/DoesNotExist";

	public static final String MOCK_MESSAGE_SSK104_GET_EVENT_DETAIL_PAGED_RESULTS = "test/ssk/ssk104/SSK104_SOAP_Get_Event_Detail_Response.xml";
	public static final String MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT = "test/ssk/ssk104/SSK104_XML_AggregatorResult.xml";
	public static final String MOCK_MESSAGE_SSK104_VARIABLE_TRANSFORM = "test/ssk/ssk104/SSK104_XSL_TransformVariable.xsl";
	public static final String MOCK_MESSAGE_SSK104_ENDPOINT_RESULT = "test/ssk/ssk104/SSK104_XML_DynamicEndpointMockResult.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		setupGlobalInitialization();
		
		@SuppressWarnings("rawtypes")
		ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, queue);

		setMessagePart(0, MOCK_MESSAGE_SSK104_GET_EVENT_DETAIL_PAGED_RESULTS, CONTENT_TYPE_TEXT_XML);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="ProcessPageParallel_104")
	public void testProcessPageEndpointAggregation() throws Exception {
		//mock required parameters to satisfy ability to use endpoint for this purpose
		ctx.setProperty(PROP_PARAMETER_IN_DYNAMIC_ENDPOINT_REQUIRED, "10");
		
		ctx.setProperty(PROP_PARAMETER_IN_PARALLEL_ENDPOINT, VALUE_DYNAMIC_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, false);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
	}

	@UnitTest(startComponent="ProcessPageParallel_104")
	public void testProcessPageEndpointSuppressAggregation() throws Exception {
		//mock required parameters to satisfy ability to use endpoint for this purpose
		ctx.setProperty(PROP_PARAMETER_IN_DYNAMIC_ENDPOINT_REQUIRED, "10");
		
		ctx.setProperty(PROP_PARAMETER_IN_PARALLEL_ENDPOINT, VALUE_DYNAMIC_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
	}

	@UnitTest(startComponent="ProcessPageParallel_104")
	public void testProcessPageDefault() throws Exception {
		setVariable(VAR_LOCAL_AGGREGATION_XSLT, MOCK_MESSAGE_SSK104_VARIABLE_TRANSFORM, CONTENT_TYPE_TEXT_XML);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
	}

	@UnitTest(startComponent="ProcessPageParallel_104")
	public void testProcessPageEndpointSuppressAggregationDebug() throws Exception {
		//mock required parameters to satisfy ability to use endpoint for this purpose
		ctx.setProperty(PROP_PARAMETER_IN_DYNAMIC_ENDPOINT_REQUIRED, "10");
		
		ctx.setProperty(PROP_PARAMETER_IN_PARALLEL_ENDPOINT, VALUE_DYNAMIC_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, true);
	}

	@UnitTest(startComponent="ProcessPageParallel_104")
	public void testProcessPageDefaultDebug() throws Exception {
		setVariable(VAR_LOCAL_AGGREGATION_XSLT, MOCK_MESSAGE_SSK104_VARIABLE_TRANSFORM, CONTENT_TYPE_TEXT_XML);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, true);
	}

	@UnitTest(startComponent="ProcessPageParallel_104")
	public void testProcessPageInvalidEndpoint() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_PARALLEL_ENDPOINT, VALUE_INVALID_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_IS_PARALLEL_SUPPRESS_AGGREGATION, false);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
	}

	@UnitTest(startComponent="ProcessPageParallel_104")
	public void testProcessPageDefaultError() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
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
		if ("testProcessPageEndpointAggregation".equalsIgnoreCase(getName()) ||
				"testProcessPageEndpointSuppressAggregation".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "endpoint invocations"), 1, mockTracker.getCallCount("PIE_108"));
		}
		
		if ((boolean)ctx.getProperty(PROP_PARAMETER_IN_DEBUG_MODE)) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PARALLEL_LOG), 1, ((ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG)).size());
		}
		
		switch (getName()) {
		case "testProcessPageEndpointAggregation" :
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_LOCAL_C2P_AGGREGATE_PAGE),
					compareAgainstVariable(VAR_LOCAL_C2P_AGGREGATE_PAGE, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK104_ENDPOINT_RESULT, CONTENT_TYPE_TEXT_XML));
			break;
			
		case "testProcessPageEndpointSuppressAggregation" :
			assertTrue(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VAR_LOCAL_C2P_AGGREGATE_PAGE), isVariableNullOrUndefined(VAR_LOCAL_C2P_AGGREGATE_PAGE));
			break;
			
		case "testProcessPageDefault" :
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_LOCAL_C2P_AGGREGATE_PAGE),
					compareAgainstVariable(VAR_LOCAL_C2P_AGGREGATE_PAGE, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML));
			break;
			
		case "testProcessPageEndpointSuppressAggregationDebug" :
			assertTrue(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VAR_LOCAL_C2P_AGGREGATE_PAGE), isVariableNullOrUndefined(VAR_LOCAL_C2P_AGGREGATE_PAGE));
			break;
			
		case "testProcessPageDefaultDebug" :
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_LOCAL_C2P_AGGREGATE_PAGE),
					compareAgainstVariable(VAR_LOCAL_C2P_AGGREGATE_PAGE, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK104_EVENT_DETAILS_AGGREGATED_RESULT, CONTENT_TYPE_TEXT_XML));
			break;
			
		default :
			break;
		}
		
		if ("testProcessPageInvalidEndpoint".equalsIgnoreCase(getName())) {
			ConcurrentLinkedQueue queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PARALLEL_LOG), 1, queue.size());
			
			Object message = queue.remove();
			if (message instanceof CloudLogMessage) {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "queue message"), "SOAP Page Processing Error on Custom Endpoint", ((CloudLogMessage)message).getSummary());
			} else {
				fail(String.format(MESSAGE_UNEXPECTED_TYPE, "threaded queue message"));
			}
		} else if ("testProcessPageDefaultError".equalsIgnoreCase(getName())) {
			ConcurrentLinkedQueue queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PARALLEL_LOG), 1, queue.size());
			
			Object message = queue.remove();
			if (message instanceof CloudLogMessage) {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "queue message"), "SOAP Page Processing Error", ((CloudLogMessage)message).getSummary());
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
	@AtComponent(id="Call_CreateLogEntry_Info_108")
	public Action mockDynamicEndpointForMessage() throws Exception {
		setMessagePart(0, MOCK_MESSAGE_SSK104_ENDPOINT_RESULT, CONTENT_TYPE_TEXT_XML);
		return new StandardAction(Action.Type.mock);
	}
}
