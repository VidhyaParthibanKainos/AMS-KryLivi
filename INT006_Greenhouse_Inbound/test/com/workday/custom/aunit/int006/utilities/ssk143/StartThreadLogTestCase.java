package com.workday.custom.aunit.int006.utilities.ssk143;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.CommonTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class StartThreadLogTestCase extends CommonTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupGlobalInitialization();
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="StartThreadLog")
	public void testStartThreadLog() throws Exception {

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
		assertPrimaryCloudLogEntryHTML("INFO", "Initialized Logging for Parallel Execution", "A data structure capable of capturing messages for cloud logging is being setup to support logging while executing parallel threads.  This is typically used when running parallel processing endpoints with either PagedGet where the is.paged.get.parallel parameter is true, or with use of the ParallelSubroutine component.", null, null, null, null, null);

		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG), ctx.getProperty(PROP_LOCAL_PARALLEL_LOG) instanceof ConcurrentLinkedQueue);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

}
