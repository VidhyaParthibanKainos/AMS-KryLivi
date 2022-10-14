package com.workday.custom.aunit.int006.utilities.ssk108;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent108TestCase extends UtilitiesTestCase {
	
	public static final String PROP_PARAMETER_IN_PERCENTAGE = "inProgressPercentage"; 
	public static final String PROP_PARAMETER_IN_MESSAGE = "inProgressMessage"; 

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
	@UnitTest(startComponent="IntegrationEventProgress")
	public void testMessageLogging() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PERCENTAGE, "20");
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE, "A really informative message...");
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
		assertPrimaryCloudLogEntryHTML("INFO", "Integration 20% Complete", "A really informative message...", null, null, null, null, null);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

}
