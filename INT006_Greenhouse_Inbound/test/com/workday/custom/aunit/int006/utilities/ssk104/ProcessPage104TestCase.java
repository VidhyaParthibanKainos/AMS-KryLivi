package com.workday.custom.aunit.int006.utilities.ssk104;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class ProcessPage104TestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_SOAP_RESULTS = "globalSoapResults";

	public static final String PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION = "inWebServiceApplication";
	public static final String PROP_PARAMETER_IN_RESULT_XPATH = "inXpathToResultsToAggregate";
	public static final String PROP_PARAMETER_IN_AGGREGATION_HEADER = "inAggregatedResultsHeader";
	public static final String PROP_PARAMETER_IN_AGGREGATION_FOOTER = "inAggregatedResultsFooter";
	public static final String PROP_PARAMETER_IN_RETURN_RESULTS = "inReturnResults";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";

	public static final String VALUE_WS_APP_HUMAN_RESOURCES = "Human_Resources";
	public static final String VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL = "/env:Envelope/env:Body/wd:Get_Event_Details_Response/wd:Response_Data/wd:Event";

	public static final String MOCK_RESPONSE_SSK104_SOAP_GET_EVENT_DETAIL_RESPONSE = "test/ssk/ssk104/SSK104_SOAP_Get_Event_Detail_Response.xml";
	public static final String MOCK_XML_SSK104_AGGREGATOR_RESULTS = "test/ssk/ssk104/SSK104_XML_AggregatorResult.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		setupGlobalInitialization();

		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATION_HEADER, "<SoapResults xmlns:wd=\"urn:com.workday/bsvc\">");
		ctx.setProperty(PROP_PARAMETER_IN_AGGREGATION_FOOTER, "</SoapResults>");
		ctx.setProperty(PROP_STUDIO_PAGEDGET_LAST_PAGE, true);
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_HUMAN_RESOURCES);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="ProcessPage_104")
	public void testProcessPage10ResultsToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_SOAP_RESULTS);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);

		setMessagePart(0, MOCK_RESPONSE_SSK104_SOAP_GET_EVENT_DETAIL_RESPONSE, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ProcessPage_104")
	public void testProcessPage10ResultsToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);

		setMessagePart(0, MOCK_RESPONSE_SSK104_SOAP_GET_EVENT_DETAIL_RESPONSE, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ProcessPage_104")
	public void testProcessPage10ResultsToVariableDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_SOAP_RESULTS);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);

		setMessagePart(0, MOCK_RESPONSE_SSK104_SOAP_GET_EVENT_DETAIL_RESPONSE, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ProcessPage_104")
	public void testProcessPage10ResultsToMessageDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_RESULT_XPATH, VALUE_PAGED_GET_RESPONSE_GET_EVENT_DETAIL);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);

		setMessagePart(0, MOCK_RESPONSE_SSK104_SOAP_GET_EVENT_DETAIL_RESPONSE, CONTENT_TYPE_TEXT_XML);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception { }

	@AssertAfter(id="MoveResultsToVariable_104")
	public void verifyAggregatorVariable() throws Exception {
		assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_GLOBAL_SOAP_RESULTS), 
				compareAgainstVariable(VAR_GLOBAL_SOAP_RESULTS, CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK104_AGGREGATOR_RESULTS, CONTENT_TYPE_TEXT_XML));
	}

	@AssertAfter(id="AggregateMessage_104")
	public void verifyAggregatorMessage() throws Exception {
		assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK104_AGGREGATOR_RESULTS), 
				compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK104_AGGREGATOR_RESULTS, CONTENT_TYPE_TEXT_XML));
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

}
