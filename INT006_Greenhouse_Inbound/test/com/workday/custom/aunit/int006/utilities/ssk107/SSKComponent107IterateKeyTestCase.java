package com.workday.custom.aunit.int006.utilities.ssk107;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AssertBefore;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent107IterateKeyTestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_RAAS_RESULTS = "globalRaaSResults";
	public static final String VAR_GLOBAL_INPUT_DATA = "globalInputData";
	public static final String VAR_GLOBAL_DATA = "globalData";

	public static final String VAR_LOCAL_PRE_TRANSFORM = "localPreTransformData107";

	public static final String PROP_PARAMETER_IN_XSLT_PATH = "inPathToXsltFile";
	public static final String PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS = "inDataSourceLocationList";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	
	public static final String MOCK_MESSAGE_SSK107_ITERATE_KEY_ENRICHMENT_DATA = "test/ssk/ssk107/SSK107_XML_IterateKey_EnrichmentData.xml";
	public static final String MOCK_MESSAGE_SSK107_ITERATE_KEY_PAYLOAD_DATA = "test/ssk/ssk107/SSK107_XML_IterateKey_PayloadData.xml";
	public static final String MOCK_XSLT_SSK107_STREAM_WITH_ENRICHMENT_ITERATE_KEY = "test/ssk/ssk107/SSK107_StreamWithEnrichment_IterateKey.xsl";
	public static final String MOCK_MESSAGE_SSK107_ITERATE_KEY_CONCATENATED_DATA = "test/ssk/ssk107/SSK107_XML_IterateKey_ConcatenatedData.xml";
	public static final String MOCK_MESSAGE_SSK107_ITERATE_KEY_TRANSFORM_RESULT = "test/ssk/ssk107/SSK107_XML_IterateKey_Transform_Results.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();
		
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_PATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK107_STREAM_WITH_ENRICHMENT_ITERATE_KEY));		
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="StreamDataMerge")
	public void testDataInMessageMapInVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS, VAR_GLOBAL_RAAS_RESULTS + "," + VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		
		setMessagePart(0, MOCK_MESSAGE_SSK107_ITERATE_KEY_PAYLOAD_DATA, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_RAAS_RESULTS, MOCK_MESSAGE_SSK107_ITERATE_KEY_ENRICHMENT_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="StreamDataMerge")
	public void testDataInVariableMapInVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS, VAR_GLOBAL_RAAS_RESULTS + "," + VAR_GLOBAL_INPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_DATA);
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SSK107_ITERATE_KEY_PAYLOAD_DATA, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_RAAS_RESULTS, MOCK_MESSAGE_SSK107_ITERATE_KEY_ENRICHMENT_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="StreamDataMerge")
	public void testDataInVariableMapInMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS, VALUE_PARAMETER_TARGET_TYPE_MESSAGE + "," + VAR_GLOBAL_INPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_DATA);
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SSK107_ITERATE_KEY_PAYLOAD_DATA, CONTENT_TYPE_TEXT_XML);
		setMessagePart(0, MOCK_MESSAGE_SSK107_ITERATE_KEY_ENRICHMENT_DATA, CONTENT_TYPE_TEXT_XML);
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
		if (String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_RETURN_RESULTS)).equalsIgnoreCase(VALUE_PARAMETER_TARGET_TYPE_MESSAGE)) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_SSK107_ITERATE_KEY_TRANSFORM_RESULT), 
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK107_ITERATE_KEY_TRANSFORM_RESULT, CONTENT_TYPE_TEXT_XML));
		} else {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_GLOBAL_DATA), 
					compareAgainstVariable(VAR_GLOBAL_DATA, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK107_ITERATE_KEY_TRANSFORM_RESULT, CONTENT_TYPE_TEXT_XML));
		}
	}

	@Override
	protected void unhandledExceptionVerification(Throwable t) throws Exception {
		if (!testsExpectingHandledExceptions.contains(getName())) {
			super.unhandledExceptionVerification(t);
		}
	}

	@AssertBefore(id="Transform_107")
	public void verifyAggregateData() throws Exception {
		assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_LOCAL_PRE_TRANSFORM), 
				compareAgainstVariable(VAR_LOCAL_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK107_ITERATE_KEY_CONCATENATED_DATA, CONTENT_TYPE_TEXT_XML));
	}

	@AssertAfter(id="Transform_107", step="ExecuteTransform")
	public void verifyTransform() throws Exception {
		assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_SSK107_ITERATE_KEY_TRANSFORM_RESULT), 
				compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_SSK107_ITERATE_KEY_TRANSFORM_RESULT, CONTENT_TYPE_TEXT_XML));
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
}
