package com.workday.custom.aunit.int006.utilities.ssk143;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.CommonTestCase;
import com.workday.custom.int006.ssk142.CloudLogMessage;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class WriteThreadLogsToCloudLogTestCase extends CommonTestCase {

	@SuppressWarnings("rawtypes")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupGlobalInitialization();
		
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, new java.util.concurrent.ConcurrentLinkedQueue());
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@UnitTest(startComponent="WriteThreadLogsToCloudLog")
	public void testWriteLogs() throws Exception {
		ConcurrentLinkedQueue q = new java.util.concurrent.ConcurrentLinkedQueue();

		CloudLogMessage m1 = new CloudLogMessage();
		m1.setSummary("Warn Message");
		m1.setDetail("Warn Message Detail");
		m1.setReferenceId("101");
		m1.setLevel("warn");
		m1.setLocalIn("testWarnMessage");
		m1.setRecordNumber("3");
		m1.setErrorCode("10002");
		m1.setSupportData("Details about 10002");
		q.add(m1);
		
		CloudLogMessage m2 = new CloudLogMessage();
		m2.setSummary("Error Message");
		m2.setDetail("Error Message Detail");
		m2.setReferenceId("101");
		m2.setLevel("error");
		m2.setLocalIn("testErrorMessage");
		m2.setRecordNumber("2");
		m2.setErrorCode("10001");
		m2.setSupportData("Details about 10001");
		q.add(m2);
		
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, q);
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
		Object queue = ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), queue instanceof ConcurrentLinkedQueue);

		assertPrimaryCloudLogEntryHTML("ERROR", "Error Message", "Error Message Detail", "101", "testErrorMessage", "10001", "2", "Details about 10001");
		assertPrimaryCloudLogEntryHTML("WARN", "Warn Message", "Warn Message Detail", "101", "testWarnMessage", "10002", "3", "Details about 10002");
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
}
