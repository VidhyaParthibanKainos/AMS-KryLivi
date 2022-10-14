package com.workday.custom.aunit.int006.main;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.CommonTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class FoundationTestCase extends CommonTestCase {


	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="Foundation")
	public void testFoundation() throws Exception { }

	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception { }
	
	@AssertAfter(id="Foundation", step="SetConstantValues")
	public void verifyInitialization() throws Exception {
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_API_VERSION), ctx.getProperty(PROP_GLOBAL_API_VERSION) instanceof String);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_API_VERSION), VALUE_API_VERSION, (String)ctx.getProperty(PROP_GLOBAL_API_VERSION));
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@AtComponent(id="Call_InitializeFrameworkThenRunMain")
	public Action mockFramework() throws Exception {
		return new StandardAction(Action.Type.terminate);
	}
}
