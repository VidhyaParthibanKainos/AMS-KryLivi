package com.workday.custom.aunit.int006.utilities.ssk107;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent107AggregateSourcesTestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_RAAS_RESULTS = "globalRaaSResults";
	public static final String VAR_GLOBAL_SOAP_RESULTS = "globalSoapResults";
	public static final String VAR_GLOBAL_INPUT_DATA = "globalInputData";

	public static final String VAR_LOCAL_PRE_TRANSFORM = "localPreTransformData107";

	public static final String PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS = "inDataSourceLocationList";

	public static final String VALUE_PARAMETER_CACHE_MESSAGE = "localCacheMessage";
	
	public static final String MOCK_MESSAGE_SAMPLE_01 = "test/ssk/ssk107/SSK107_XML_SampleRecord01.xml";
	public static final String MOCK_MESSAGE_SAMPLE_02 = "test/ssk/ssk107/SSK107_XML_SampleRecord02.xml";
	public static final String MOCK_MESSAGE_SAMPLE_03 = "test/ssk/ssk107/SSK107_XML_SampleRecord03.xml";
	public static final String MOCK_MESSAGE_RESULTS_MMM = "test/ssk/ssk107/SSK107_XML_AggregateSources_Results_mmm.xml";
	public static final String MOCK_MESSAGE_RESULTS_MMV = "test/ssk/ssk107/SSK107_XML_AggregateSources_Results_mmv.xml";
	public static final String MOCK_MESSAGE_RESULTS_MVM = "test/ssk/ssk107/SSK107_XML_AggregateSources_Results_mvm.xml";
	public static final String MOCK_MESSAGE_RESULTS_MVV = "test/ssk/ssk107/SSK107_XML_AggregateSources_Results_mvv.xml";
	public static final String MOCK_MESSAGE_RESULTS_VMM = "test/ssk/ssk107/SSK107_XML_AggregateSources_Results_vmm.xml";
	public static final String MOCK_MESSAGE_RESULTS_VMV = "test/ssk/ssk107/SSK107_XML_AggregateSources_Results_vmv.xml";
	public static final String MOCK_MESSAGE_RESULTS_VVM = "test/ssk/ssk107/SSK107_XML_AggregateSources_Results_vvm.xml";
	public static final String MOCK_MESSAGE_RESULTS_VVV = "test/ssk/ssk107/SSK107_XML_AggregateSources_Results_vvv.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="AggregateSources_107")
	public void testAggregationMessageMessageMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS,  VALUE_PARAMETER_TARGET_TYPE_MESSAGE + "," + VALUE_PARAMETER_TARGET_TYPE_MESSAGE + "," + VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		
		setMessagePart(0, MOCK_MESSAGE_SAMPLE_01, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SAMPLE_02, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_RAAS_RESULTS, MOCK_MESSAGE_SAMPLE_03, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AggregateSources_107")
	public void testAggregationMessageMessageVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS,  VALUE_PARAMETER_TARGET_TYPE_MESSAGE + "," + VALUE_PARAMETER_TARGET_TYPE_MESSAGE + "," + VAR_GLOBAL_INPUT_DATA);
		
		setMessagePart(0, MOCK_MESSAGE_SAMPLE_01, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SAMPLE_02, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_RAAS_RESULTS, MOCK_MESSAGE_SAMPLE_03, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AggregateSources_107")
	public void testAggregationMessageVariableMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS,  VALUE_PARAMETER_TARGET_TYPE_MESSAGE + "," + VAR_GLOBAL_INPUT_DATA + "," + VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		
		setMessagePart(0, MOCK_MESSAGE_SAMPLE_01, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SAMPLE_02, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AggregateSources_107")
	public void testAggregationMessageVariableVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS,  VALUE_PARAMETER_TARGET_TYPE_MESSAGE + "," + VAR_GLOBAL_INPUT_DATA + "," + VAR_GLOBAL_RAAS_RESULTS);
		
		setMessagePart(0, MOCK_MESSAGE_SAMPLE_01, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SAMPLE_02, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_RAAS_RESULTS, MOCK_MESSAGE_SAMPLE_03, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AggregateSources_107")
	public void testAggregationVariableMessageMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS,  VAR_GLOBAL_INPUT_DATA + "," + VALUE_PARAMETER_TARGET_TYPE_MESSAGE + "," + VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SAMPLE_01, CONTENT_TYPE_TEXT_XML);
		setMessagePart(0, MOCK_MESSAGE_SAMPLE_02, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_RAAS_RESULTS, MOCK_MESSAGE_SAMPLE_03, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AggregateSources_107")
	public void testAggregationVariableMessageVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS,  VAR_GLOBAL_INPUT_DATA + "," + VALUE_PARAMETER_TARGET_TYPE_MESSAGE + "," + VAR_GLOBAL_RAAS_RESULTS);
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SAMPLE_01, CONTENT_TYPE_TEXT_XML);
		setMessagePart(0, MOCK_MESSAGE_SAMPLE_02, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_RAAS_RESULTS, MOCK_MESSAGE_SAMPLE_03, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AggregateSources_107")
	public void testAggregationVariableVariableMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS,  VAR_GLOBAL_INPUT_DATA + "," + VAR_GLOBAL_RAAS_RESULTS + "," + VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SAMPLE_01, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_RAAS_RESULTS, MOCK_MESSAGE_SAMPLE_02, CONTENT_TYPE_TEXT_XML);
		setMessagePart(0, MOCK_MESSAGE_SAMPLE_03, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="AggregateSources_107")
	public void testAggregationVariableVariableVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS,  VAR_GLOBAL_INPUT_DATA + "," + VAR_GLOBAL_SOAP_RESULTS + "," + VAR_GLOBAL_RAAS_RESULTS);
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_MESSAGE_SAMPLE_01, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_SOAP_RESULTS, MOCK_MESSAGE_SAMPLE_02, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_GLOBAL_RAAS_RESULTS, MOCK_MESSAGE_SAMPLE_03, CONTENT_TYPE_TEXT_XML);
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
		switch(getName()) {
			case "testAggregationMessageMessageMessage" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_PRE_TRANSFORM, MOCK_MESSAGE_RESULTS_MMM), 
						compareAgainstVariable(VAR_LOCAL_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESULTS_MMM, CONTENT_TYPE_TEXT_XML));
				break;

			case "testAggregationMessageMessageVariable" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_PRE_TRANSFORM, MOCK_MESSAGE_RESULTS_MMV), 
						compareAgainstVariable(VAR_LOCAL_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESULTS_MMV, CONTENT_TYPE_TEXT_XML));
				break;

			case "testAggregationMessageVariableMessage" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_PRE_TRANSFORM, MOCK_MESSAGE_RESULTS_MVM), 
						compareAgainstVariable(VAR_LOCAL_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESULTS_MVM, CONTENT_TYPE_TEXT_XML));
				break;

			case "testAggregationMessageVariableVariable" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_PRE_TRANSFORM, MOCK_MESSAGE_RESULTS_MVV), 
						compareAgainstVariable(VAR_LOCAL_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESULTS_MVV, CONTENT_TYPE_TEXT_XML));
				break;

			case "testAggregationVariableMessageMessage" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_PRE_TRANSFORM, MOCK_MESSAGE_RESULTS_VMM), 
						compareAgainstVariable(VAR_LOCAL_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESULTS_VMM, CONTENT_TYPE_TEXT_XML));
				break;

			case "testAggregationVariableMessageVariable" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_PRE_TRANSFORM, MOCK_MESSAGE_RESULTS_VMV), 
						compareAgainstVariable(VAR_LOCAL_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESULTS_VMV, CONTENT_TYPE_TEXT_XML));
				break;

			case "testAggregationVariableVariableMessage" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_PRE_TRANSFORM, MOCK_MESSAGE_RESULTS_VVM), 
						compareAgainstVariable(VAR_LOCAL_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESULTS_VVM, CONTENT_TYPE_TEXT_XML));
				break;

			case "testAggregationVariableVariableVariable" :
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_PRE_TRANSFORM, MOCK_MESSAGE_RESULTS_VVV), 
						compareAgainstVariable(VAR_LOCAL_PRE_TRANSFORM, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESULTS_VVV, CONTENT_TYPE_TEXT_XML));
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
	}

	@AssertAfter(id="PrepareDataSources_107")
	public void verifyPrepareDataSources() throws Exception {
		switch(getName()) {
			case "testAggregationMessageMessageMessage" :
				assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE, VALUE_PARAMETER_CACHE_MESSAGE), 
						(Boolean)ctx.getProperty(VALUE_PARAMETER_CACHE_MESSAGE));
				break;
	
			case "testAggregationMessageMessageVariable" :
				assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE, VALUE_PARAMETER_CACHE_MESSAGE), 
						(Boolean)ctx.getProperty(VALUE_PARAMETER_CACHE_MESSAGE));
				break;
				
			case "testAggregationMessageVariableMessage" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, VALUE_PARAMETER_CACHE_MESSAGE), 
						(Boolean)ctx.getProperty(VALUE_PARAMETER_CACHE_MESSAGE));
				break;
	
			case "testAggregationMessageVariableVariable" :
				assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE, VALUE_PARAMETER_CACHE_MESSAGE), 
						(Boolean)ctx.getProperty(VALUE_PARAMETER_CACHE_MESSAGE));
				break;
	
			case "testAggregationVariableMessageMessage" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, VALUE_PARAMETER_CACHE_MESSAGE), 
						(Boolean)ctx.getProperty(VALUE_PARAMETER_CACHE_MESSAGE));
				break;
	
			case "testAggregationVariableMessageVariable" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, VALUE_PARAMETER_CACHE_MESSAGE), 
						(Boolean)ctx.getProperty(VALUE_PARAMETER_CACHE_MESSAGE));
				break;
	
			case "testAggregationVariableVariableMessage" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, VALUE_PARAMETER_CACHE_MESSAGE), 
						(Boolean)ctx.getProperty(VALUE_PARAMETER_CACHE_MESSAGE));
				break;
	
			case "testAggregationVariableVariableVariable" :
				assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE, VALUE_PARAMETER_CACHE_MESSAGE), 
						(Boolean)ctx.getProperty(VALUE_PARAMETER_CACHE_MESSAGE));
				break;
				
			default :
				break;
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
