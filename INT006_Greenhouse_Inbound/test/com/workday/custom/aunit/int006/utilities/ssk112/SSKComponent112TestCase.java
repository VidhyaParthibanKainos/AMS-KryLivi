package com.workday.custom.aunit.int006.utilities.ssk112;

import java.util.ArrayList;
import java.util.List;

import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;
import com.workday.mediation.impl.mediators.etv.ETVInfo;
import com.workday.mediation.impl.mediators.etv.ETVInfoCollection;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent112TestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_MESSAGES = "inMessages";
	public static final String PROP_PARAMETER_IN_IS_CLEAR_MESSAGES = "inIsClearMessages";
	public static final String PROP_PARAMETER_IN_MIN_LOG_LEVEL = "inMinLogLevel";

	Logger log = LogControl.getLogger(getClass());

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		super.setupGlobalInitialization();
	}

	void setupLegacyXSLTTest(String minSeverity) {
		List<String> messages = new ArrayList<String>();
		
		messages.add("<lm><l>debug</l><m>This is a debug message</m></lm>");
		messages.add("<lm><m>This is a default severity message</m></lm>");
		messages.add("<lm><l>info</l><m>This is an info message</m></lm>");
		messages.add("<lm><l>warn</l><m>This is a warning message</m></lm>");
		messages.add("<lm><l>error</l><m>This is an error message</m></lm>");
		messages.add("<lm><l>fatal</l><m>This is a fatal message</m></lm>");
		
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGES, messages);
		if (minSeverity != null) {
			ctx.setProperty(PROP_PARAMETER_IN_MIN_LOG_LEVEL, minSeverity);
			log.info("Testing with minLevel = " + minSeverity);
		}
	}
	
	void setupEnhancedXSLTTest(String minSeverity) {
		List<String> messages = new ArrayList<String>();
		
		messages.add("<lm><m>This is a default severity message</m></lm>");
		messages.add("<lm><l>info</l><m>This is an info message</m></lm>");
		messages.add("<lm><l>warn</l><m>This is a warning message</m><d>There are details that go with it</d></lm>");
		messages.add("<lm><l>error</l><m>This is an error message</m><d>There are important details that go with it</d><r>andReferenceId</r></lm>");
		
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGES, messages);
		if (minSeverity != null) {
			ctx.setProperty(PROP_PARAMETER_IN_MIN_LOG_LEVEL, minSeverity);
			log.info("Testing with minLevel = " + minSeverity);
		}
	}
	
	void setupETVXTTTest(String minSeverity) {
		ETVInfoCollection etvInfo = new ETVInfoCollection();
		
		etvInfo.add(new ETVInfo("This is a message", ETVInfo.MessageSeverity.INFO));
		etvInfo.add(new ETVInfo("This is a warning message", ETVInfo.MessageSeverity.WARNING));
		etvInfo.add(new ETVInfo("This is an error message", ETVInfo.MessageSeverity.ERROR));
		etvInfo.add(new ETVInfo("This is a critical message", ETVInfo.MessageSeverity.CRITICAL));
		
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGES, etvInfo);
		if (minSeverity != null) {
			ctx.setProperty(PROP_PARAMETER_IN_MIN_LOG_LEVEL, minSeverity);
			log.info("Testing with minSeverity = " + minSeverity);
		}
	}
	
	void setupEmptyXSLTTest() {
		List<String> messages = new ArrayList<String>();
		
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGES, messages);
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LOG_LEVEL, "debug");
	}
	
	void setupEmptyETVXTTTest() {
		ETVInfoCollection etvInfo = new ETVInfoCollection();
		
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGES, etvInfo);
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LOG_LEVEL, "INFO");
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTNoMessagesHTML() throws Exception {
		setupEmptyXSLTTest();
	}

	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTNoMessagesHTML() throws Exception {
		setupEmptyETVXTTTest();
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTDefaultMinLevelHTML() throws Exception {
		setupLegacyXSLTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTInfoMinLevelHTML() throws Exception {
		setupLegacyXSLTTest("info");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTWarnMinLevelHTML() throws Exception {
		setupLegacyXSLTTest("WARN");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTErrorMinLevelHTML() throws Exception {
		setupLegacyXSLTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTCriticalMinLevelHTML() throws Exception {
		setupLegacyXSLTTest("FATAL");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTDebugMinLevelHTML() throws Exception {
		setupLegacyXSLTTest("debug");
	}

	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTDefaultMinLevelHTML() throws Exception {
		setupETVXTTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTWarnMinLevelHTML() throws Exception {
		setupETVXTTTest("WARNING");
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTErrorMinLevelHTML() throws Exception {
		setupETVXTTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTCriticalMinLevelHTML() throws Exception {
		setupETVXTTTest("CRITICAL");
	}

	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTDefaultMinLevelHTML() throws Exception {
		setupEnhancedXSLTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTInfoMinLevelHTML() throws Exception {
		setupEnhancedXSLTTest("info");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTWarnMinLevelHTML() throws Exception {
		setupEnhancedXSLTTest("WARN");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTErrorMinLevelHTML() throws Exception {
		setupEnhancedXSLTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTDebugMinLevelHTML() throws Exception {
		setupEnhancedXSLTTest("debug");
	}

	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTDefaultMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTInfoMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("info");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTWarnMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("WARN");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTErrorMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTCriticalMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("FATAL");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTDebugMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("debug");
	}

	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTDefaultMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupETVXTTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTWarnMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupETVXTTTest("WARNING");
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTErrorMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupETVXTTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTCriticalMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupETVXTTTest("CRITICAL");
	}

	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTDefaultMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTInfoMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest("info");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTWarnMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest("WARN");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTErrorMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTDebugMinLevelCSV() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest("debug");
	}


	
	
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTDefaultMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupLegacyXSLTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTInfoMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupLegacyXSLTTest("info");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTWarnMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupLegacyXSLTTest("WARN");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTErrorMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupLegacyXSLTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTCriticalMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupLegacyXSLTTest("FATAL");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTDebugMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupLegacyXSLTTest("debug");
	}

	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTDefaultMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupETVXTTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTWarnMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupETVXTTTest("WARNING");
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTErrorMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupETVXTTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTCriticalMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupETVXTTTest("CRITICAL");
	}

	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTDefaultMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupEnhancedXSLTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTInfoMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupEnhancedXSLTTest("info");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTWarnMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupEnhancedXSLTTest("WARN");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTErrorMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupEnhancedXSLTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTDebugMinLevelHTMLSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setupEnhancedXSLTTest("debug");
	}

	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTDefaultMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTInfoMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("info");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTWarnMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("WARN");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTErrorMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTCriticalMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("FATAL");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testXSLTDebugMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupLegacyXSLTTest("debug");
	}

	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTDefaultMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupETVXTTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTWarnMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupETVXTTTest("WARNING");
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTErrorMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupETVXTTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogETVXTTMessages")
	public void testETVXTTCriticalMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupETVXTTTest("CRITICAL");
	}

	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTDefaultMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest(null);
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTInfoMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest("info");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTWarnMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest("WARN");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTErrorMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest("ERROR");
	}
	
	@UnitTest(startComponent="CloudLogXSLTMessages")
	public void testEnhancedXSLTDebugMinLevelCSVSecondaryLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		setupEnhancedXSLTTest("debug");
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

	}

	@AssertAfter(id="InitializeAndFinalize_112", step="CleanUp")
	public void verifyLoggedMessages() throws Exception {
		if ("testXSLTNoMessagesHTML".equalsIgnoreCase(getName())) {
			fail(getName() + " should not have reached this point due to earlier mediation condition step.");
		} else if ("testETVXTTNoMessagesHTML".equalsIgnoreCase(getName())) {
			fail(getName() + " should not have reached this point due to earlier mediation condition step.");
		} else if ("testXSLTDefaultMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("DEBUG", "This is a debug message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTInfoMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTWarnMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTErrorMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTCriticalMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTDebugMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("DEBUG", "This is a debug message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testETVXTTDefaultMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("INFO", "This is a message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTWarnMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTErrorMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTCriticalMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testEnhancedXSLTDefaultMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTInfoMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTWarnMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTErrorMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTDebugMinLevelHTML".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertPrimaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testXSLTDefaultMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("DEBUG", "This is a debug message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTInfoMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTWarnMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTErrorMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTCriticalMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTDebugMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("DEBUG", "This is a debug message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testETVXTTDefaultMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("INFO", "This is a message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTWarnMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTErrorMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTCriticalMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testEnhancedXSLTDefaultMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTInfoMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTWarnMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTErrorMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTDebugMinLevelCSV".equalsIgnoreCase(getName())) {
			assertPrimaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertPrimaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testXSLTDefaultMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("DEBUG", "This is a debug message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTInfoMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTWarnMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTErrorMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTCriticalMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTDebugMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("DEBUG", "This is a debug message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testETVXTTDefaultMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("INFO", "This is a message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTWarnMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTErrorMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTCriticalMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testEnhancedXSLTDefaultMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTInfoMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTWarnMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTErrorMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTDebugMinLevelHTMLSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertSecondaryCloudLogEntryHTML("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testXSLTDefaultMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("DEBUG", "This is a debug message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTInfoMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTWarnMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTErrorMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTCriticalMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testXSLTDebugMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("DEBUG", "This is a debug message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a fatal message", null, null, null, null, null, null);
		} else if ("testETVXTTDefaultMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("INFO", "This is a message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTWarnMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTErrorMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testETVXTTCriticalMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("FATAL", "This is a critical message", null, null, null, null, null, null);
		} else if ("testEnhancedXSLTDefaultMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTInfoMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTWarnMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTErrorMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
		} else if ("testEnhancedXSLTDebugMinLevelCSVSecondaryLog".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("INFO", "This is a default severity message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("INFO", "This is an info message", null, null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("WARN", "This is a warning message", "There are details that go with it", null, null, null, null, null);
			assertSecondaryCloudLogEntryCSV("ERROR", "This is an error message", "There are important details that go with it", "andReferenceId", null, null, null, null);
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
