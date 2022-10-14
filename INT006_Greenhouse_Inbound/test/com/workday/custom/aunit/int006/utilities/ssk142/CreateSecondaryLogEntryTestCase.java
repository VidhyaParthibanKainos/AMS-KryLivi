package com.workday.custom.aunit.int006.utilities.ssk142;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.CommonTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class CreateSecondaryLogEntryTestCase extends CommonTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		setupSecondaryLogInitialization();
		setupGlobalInitialization();
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlFatalMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "fatal");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Fatal Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Fatal Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testFatalMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "1");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10000");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10000");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlErrorMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "error");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Error Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Error Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testErrorMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "2");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10001");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10001");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlWarnMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "warn");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Warn Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Warn Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testWarnMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "3");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10002");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10002");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlInfoMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Info Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Info Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testInfoMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "4");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlDebugMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "debug");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Debug Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Debug Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testDebugMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "5");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Deep technical details supporting debugging");
	}
	
	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlInfoMessageMinimal() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Info Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Minimum Detail");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvFatalMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "fatal");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Fatal Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Fatal Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testFatalMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "1");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10000");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10000");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvErrorMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "error");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Error Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Error Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testErrorMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "2");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10001");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10001");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvWarnMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "warn");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Warn Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Warn Message, Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testWarnMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "3");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10002");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10002");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvInfoMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Info Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Info Message with \"Detail\"");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testInfoMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "4");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvDebugMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "debug");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Debug Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Debug Message, \"Detail\"");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testDebugMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "5");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Deep technical details supporting debugging");
	}
	
	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvInfoMessageMinimal() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Info Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Minimum Detail");
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	@AssertAfter(id="UpdateSecondaryLogTracking_142")
	public void verifyCountsHTML() throws Exception {
		Object debugLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG);
		Object infoLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_INFO);
		Object warnLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_WARN);
		Object errorLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR);
		Object fatalLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_FATAL);
		Object totalLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL);
		Object fileLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE);

		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG), debugLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_INFO), infoLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_WARN), warnLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), errorLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_FATAL), fatalLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL), totalLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE), fileLogCount);

		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG), debugLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_INFO), infoLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_WARN), warnLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), errorLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_FATAL), fatalLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL), totalLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE), fileLogCount instanceof Integer);
		
		switch(getName()) {
		case "testHtmlFatalCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 0, 0, 0, 0, 1, 1, 1);
			break;

		case "testHtmlErrorCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 0, 0, 0, 1, 0, 1, 1);
			break;

		case "testHtmlWarnCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 0, 0, 1, 0, 0, 1, 1);
			break;

		case "testHtmlInfoCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 0, 1, 0, 0, 0, 1, 1);
			break;

		case "testHtmlDebugCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 1, 0, 0, 0, 0, 1, 1);
			break;
			
		case "testHtmlInfoMessageMinimal":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 0, 1, 0, 0, 0, 1, 1);
			break;

		default:
			break;
		}
	}
	
	private void validateLogCounts(Object debugLogCount, Object infoLogCount, Object warnLogCount, Object errorLogCount, Object fatalLogCount, Object totalLogCount, Object fileLogCount, Object debugExpectedValue, Object infoExpectedValue, Object warnExpectedValue, Object errorExpectedValue, Object fatalExpectedValue, Object totalExpectedValue, Object fileExpectedValue) {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG), debugExpectedValue, debugLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_INFO), infoExpectedValue, infoLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_WARN), warnExpectedValue, warnLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), errorExpectedValue, errorLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_FATAL), fatalExpectedValue, fatalLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL), totalExpectedValue, totalLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE), fileExpectedValue, fileLogCount);
	}
	
	@AssertAfter(id="SecondaryLog_HTML_142")
	public void verifyLoggedMessageHTML() throws Exception {
		if ("testHtmlFatalMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("FATAL", "Fatal Message", "Fatal Message Detail", "101", "testFatalMessage", "10000", "1", "Details about 10000");
		} else if ("testHtmlErrorMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("ERROR", "Error Message", "Error Message Detail", "101", "testErrorMessage", "10001", "2", "Details about 10001");
		} else if ("testHtmlWarnMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("WARN", "Warn Message", "Warn Message Detail", "101", "testWarnMessage", "10002", "3", "Details about 10002");
		} else if ("testHtmlInfoMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("INFO", "Info Message", "Info Message Detail", "101", "testInfoMessage", null, "4", null);
		} else if ("testHtmlDebugMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("DEBUG", "Debug Message", "Debug Message Detail", "101", "testDebugMessage", null, "5", "Deep technical details supporting debugging");
		} else if ("testHtmlInfoMessageMinimal".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("INFO", "Info Message", "Minimum Detail", null, null, null, null, null);
		}
	}
	
	@AssertAfter(id="SecondaryLog_CSV_142")
	public void verifyLoggedMessageCSV() throws Exception {
		if ("testCsvFatalMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("FATAL", "Fatal Message", "Fatal Message Detail", "101", "testFatalMessage", "10000", "1", "Details about 10000");
		} else if ("testCsvErrorMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("ERROR", "Error Message", "Error Message Detail", "101", "testErrorMessage", "10001", "2", "Details about 10001");
		} else if ("testCsvWarnMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("WARN", "Warn Message", "Warn Message, Detail", "101", "testWarnMessage", "10002", "3", "Details about 10002");
		} else if ("testCsvInfoMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("INFO", "Info Message", "Info Message with \"Detail\"", "101", "testInfoMessage", null, "4", null);
		} else if ("testCsvDebugMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("DEBUG", "Debug Message", "Debug Message, \"Detail\"", "101", "testDebugMessage", null, "5", "Deep technical details supporting debugging");
		} else if ("testCsvInfoMessageMinimal".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("INFO", "Info Message", "Minimum Detail", null, null, null, null, null);
		}
	}

	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {

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
