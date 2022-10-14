package com.workday.custom.aunit.int006.utilities.ssk107;

import com.capeclear.mediation.MediationException;
import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent107ValidationTestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_XSLT_PATH = "inPathToXsltFile";
	public static final String PROP_PARAMETER_IN_RETURN_LOCATION = "inReturnOutputLocation";
	public static final String PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS = "inDataSourceLocationList";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	public static final String PROP_PARAMETER_IN_LOG_TARGET_XSLT = "inLogTargetForXsltMessages";

	public static final String MOCK_XSLT = "test/ssk/ssk107/SSK107_StreamWithEnrichment_AccumulatorMap.xsl";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();
		
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, true);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="StreamDataMerge")
	public void testError10700() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS, "");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_PATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET_XSLT, VALUE_LOG_PRIMARY);		
	}

	@UnitTest(startComponent="StreamDataMerge")
	public void testError10701() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_PATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET_XSLT, VALUE_LOG_PRIMARY);		
	}

	@UnitTest(startComponent="StreamDataMerge")
	public void testError10702() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_LOCATIONS, "message,globalInputData");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_PATH, "");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET_XSLT, VALUE_LOG_PRIMARY);		
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
		if (!testsExpectingHandledExceptions.contains(getName())) {
			fail(getName() + " should have thrown an unhandled exception.");
		}
	}

	@Override
	protected void unhandledExceptionVerification(Throwable t) throws Exception {
		if (!testsExpectingHandledExceptions.contains(getName())) {
			super.unhandledExceptionVerification(t);
		}
		
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, "exception"), t instanceof MediationException);

		if ("testError10700".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error code"), 10700, ((MediationException)t).getErrorNumber());
		} else if ("testError10701".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error code"), 10701, ((MediationException)t).getErrorNumber());
		} else if ("testError10702".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "error code"), 10702, ((MediationException)t).getErrorNumber());
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
	@AtComponent(id="Call_AggregateSources_107")
	public Action mockAggregateSources() throws Exception {
		return new StandardAction(Action.Type.terminate);
	}
}
