package com.workday.custom.aunit.int006.utilities.ssk142;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.CommonTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class StoreLogSecondaryTestCase extends CommonTestCase {
	
	public static final String MOCK_SSK142_HTML_LOG_CONTENT = "test/ssk/ssk142/SSK142_CloudLog.html";
	public static final String MOCK_SSK142_CSV_LOG_CONTENT = "test/ssk/ssk142/SSK142_CloudLog.csv";

	private boolean isAssertionRun;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		setupSecondaryLogInitialization();
		setupGlobalInitialization();
		
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE, 1);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL, 1);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_FINALIZE, Boolean.TRUE);
		
		isAssertionRun = false;
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	@UnitTest(startComponent="StoreLogSecondary")
	public void testLogStorageHTML() throws Exception {
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setVariable(VAR_CLOUD_LOG_SECONDARY, MOCK_SSK142_HTML_LOG_CONTENT, CONTENT_TYPE_TEXT_HTML);
	}

	@UnitTest(startComponent="StoreLogSecondary")
	public void testLogStorageCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setVariable(VAR_CLOUD_LOG_SECONDARY, MOCK_SSK142_CSV_LOG_CONTENT, CONTENT_TYPE_TEXT_CSV);
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
			if (!isAssertionRun) {
				fail(getName() + " did not execute the AssertAfter method, presumably because the Mediation condition evaluated to skip inappropriately.");
			} else {
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_SECONDARYLOG_FILENAME), ctx.containsProperty(PROP_LOCAL_SECONDARYLOG_FILENAME));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE), "0", String.valueOf(ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE)));
				assertTrue(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_CLOUD_LOG_SECONDARY), isVariableNullOrUndefined(VAR_CLOUD_LOG_SECONDARY));
			}
		}
	}
	
	@AssertAfter(id="OutputSecondaryLog_142", step="Setup")
	public void verifySetup() throws Exception {
		isAssertionRun = true;
		
		if ("testLogStorageHTML".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_SECONDARYLOG_FILENAME), ctx.getProperty(PROP_LOCAL_SECONDARYLOG_FILENAME) instanceof String);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_SECONDARYLOG_FILENAME), VALUE_SECONDARYLOG_FILE + ".html", (String)ctx.getProperty(PROP_LOCAL_SECONDARYLOG_FILENAME));
		} else if ("testLogStorageCSV".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_SECONDARYLOG_FILENAME), ctx.getProperty(PROP_LOCAL_SECONDARYLOG_FILENAME) instanceof String);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_SECONDARYLOG_FILENAME), VALUE_SECONDARYLOG_FILE + ".csv", (String)ctx.getProperty(PROP_LOCAL_SECONDARYLOG_FILENAME));
		}
		
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_FILES_STORED), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_FILES_STORED) instanceof Integer);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_FILES_STORED), 1, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_FILES_STORED));

		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE) instanceof Integer);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE), 1, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE));

		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL), ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL) instanceof Integer);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL), 1, (int)ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL));
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	//No additional mocks necessary
}
