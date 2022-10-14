package com.workday.custom.aunit.int006.utilities.ssk142;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.CommonTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class CreateLogEntryLogCountsTestCase extends CommonTestCase {

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

	@UnitTest(startComponent="WritePrimaryLog_HTML_142")
	public void testHtmlFatalCount() throws Exception {
		setupFatalLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_HTML_142")
	public void testHtmlErrorCount() throws Exception {
		setupErrorLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_HTML_142")
	public void testHtmlWarnCount() throws Exception {
		setupWarnLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_HTML_142")
	public void testHtmlInfoCount() throws Exception {
		setupInfoLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_HTML_142")
	public void testHtmlDebugCount() throws Exception {
		setupDebugLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_CSV_142")
	public void testCsvFatalCount() throws Exception {
		setupFatalLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_CSV_142")
	public void testCsvErrorCount() throws Exception {
		setupErrorLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_CSV_142")
	public void testCsvWarnCount() throws Exception {
		setupWarnLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_CSV_142")
	public void testCsvInfoCount() throws Exception {
		setupInfoLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_CSV_142")
	public void testCsvDebugCount() throws Exception {
		setupDebugLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_XLSX_142")
	public void testXlsxFatalCount() throws Exception {
		setupFatalLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_XLSX_142")
	public void testXlsxErrorCount() throws Exception {
		setupErrorLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_XLSX_142")
	public void testXlsxWarnCount() throws Exception {
		setupWarnLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_XLSX_142")
	public void testXlsxInfoCount() throws Exception {
		setupInfoLog();
	}

	@UnitTest(startComponent="WritePrimaryLog_XLSX_142")
	public void testXlsxDebugCount() throws Exception {
		setupDebugLog();
	}

	private void setupFatalLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "fatal");
	}
	
	private void setupErrorLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "error");
	}
	
	private void setupWarnLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "warn");
	}
	
	private void setupInfoLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
	}
	
	private void setupDebugLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "debug");
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	private void validateLogCounts(Object debugLogCount, Object infoLogCount, Object warnLogCount, Object errorLogCount, Object fatalLogCount, Object totalLogCount, Object fileLogCount, Object debugExpectedValue, Object infoExpectedValue, Object warnExpectedValue, Object errorExpectedValue, Object fatalExpectedValue, Object totalExpectedValue, Object fileExpectedValue) {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG), debugExpectedValue, debugLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_INFO), infoExpectedValue, infoLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_WARN), warnExpectedValue, warnLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), errorExpectedValue, errorLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_FATAL), fatalExpectedValue, fatalLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL), totalExpectedValue, totalLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE), fileExpectedValue, fileLogCount);
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
	@AtComponent(id="Call_StoreLogPrimary_142")
	public Action terminate() throws Exception {
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
		case "testFatalCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 0, 0, 0, 0, 1, 1, 1);
			break;

		case "testErrorCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 0, 0, 0, 1, 0, 1, 1);
			break;

		case "testWarnCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 0, 0, 1, 0, 0, 1, 1);
			break;

		case "testInfoCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 0, 1, 0, 0, 0, 1, 1);
			break;

		case "testDebugCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, fatalLogCount, totalLogCount, fileLogCount, 1, 0, 0, 0, 0, 1, 1);
			break;

		default:
			break;
		}

		return new StandardAction(Action.Type.terminate);
	}
}
