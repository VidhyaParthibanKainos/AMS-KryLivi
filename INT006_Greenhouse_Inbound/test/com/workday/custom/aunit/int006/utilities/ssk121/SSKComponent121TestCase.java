package com.workday.custom.aunit.int006.utilities.ssk121;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent121TestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_EXEMPTION_LIST = "inStaticCodeAnalysisExemptionList";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";

	public static final String PROP_LOCAL_ASSEMBLY = "localAssemblyXml";
	public static final String PROP_LOCAL_SUBASSEMBLY_LIST = "localSubAssemblyList";
	public static final String PROP_LOCAL_CORE_LIST = "localCoreList";
	public static final String PROP_LOCAL_EXEMPT_LIST = "localExemptionList";
	public static final String PROP_LOCAL_ASSEMBLY_MAP = "localSubAssemblyDetailMap";

	public static final String MOCK_SSK121_ASSEMBLY1 = "test/ssk/ssk121/SSK121_Assembly1.xml";
	public static final String MOCK_SSK121_ASSEMBLY1_DEAD_CODE = "test/ssk/ssk121/SSK121_Assembly1_DeadCode.xml";
	public static final String MOCK_SSK121_ASSEMBLY1_MISSING_CODE = "test/ssk/ssk121/SSK121_Assembly1_MissingCode.xml";
	public static final String MOCK_SSK121_ASSEMBLY1_MESSAGE_MISSING_CODE_PRE_ANALYSIS = "test/ssk/ssk121/SSK121_Assembly1_MissingCodePreAnalysis.xml";
	public static final String MOCK_SSK121_ASSEMBLY2 = "test/ssk/ssk121/SSK121_Assembly2.xml";
	public static final String MOCK_SSK121_ASSEMBLY2_DEAD_CODE = "test/ssk/ssk121/SSK121_Assembly2_DeadCode.xml";
	public static final String MOCK_SSK121_ASSEMBLY2_MISSING_CODE = "test/ssk/ssk121/SSK121_Assembly2_MissingCode.xml";
	public static final String MOCK_SSK121_ASSEMBLY2_MESSAGE_MISSING_CODE_PRE_ANALYSIS = "test/ssk/ssk121/SSK121_Assembly2_MissingCodePreAnalysis.xml";
	public static final String MOCK_SSK121_MESSAGE_MISSING_CODE_PRE_ANALYSIS = "test/ssk/ssk121/SSK121_Message_MissingCodePreAnalysis.xml";
	public static final String MOCK_SSK121_UNEXEMPTED_DEAD_CODE = "test/ssk/ssk121/SSK121_Message_Unexempted_DeadCode.xml";
	public static final String MOCK_SSK121_UNEXEMPTED_MISSING_CODE = "test/ssk/ssk121/SSK121_Message_MissingCode.xml";
	public static final String MOCK_SSK121_EXEMPTED_DEAD_CODE = "test/ssk/ssk121/SSK121_Message_Exempted_DeadCode.xml";
	public static final String MOCK_SSK121_EXEMPTED_MISSING_CODE = "test/ssk/ssk121/SSK121_Message_Exempted_MissingCode.xml";
	
	protected String assemblyOverride;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, Boolean.TRUE);
		
		assemblyOverride = null;
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="StaticCodeAnalysis")
	public void testNullExemptionListSskAssembly() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_EXEMPTION_LIST, null);
	}
	
	@UnitTest(startComponent="StaticCodeAnalysis")
	public void testEmptyExemptionListSskAssembly() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_EXEMPTION_LIST, new ArrayList<String>());
	}
	
	@UnitTest(startComponent="StaticCodeAnalysis")
	public void testExemptionListSskAssembly() throws Exception {
		List<String> exemptList = new ArrayList<String>();
		exemptList.add("Main");
		
		ctx.setProperty(PROP_PARAMETER_IN_EXEMPTION_LIST, exemptList);
	}
	
	@UnitTest(startComponent="StaticCodeAnalysis")
	public void testNullExemptionListAssemblyOverride1() throws Exception {
		assemblyOverride = MOCK_SSK121_ASSEMBLY1;

		ctx.setProperty(PROP_PARAMETER_IN_EXEMPTION_LIST, null);
	}
	
	@UnitTest(startComponent="StaticCodeAnalysis")
	public void testNullExemptionListAssemblyOverride2() throws Exception {
		assemblyOverride = MOCK_SSK121_ASSEMBLY2;
		
		ctx.setProperty(PROP_PARAMETER_IN_EXEMPTION_LIST, null);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@AssertAfter(id="InitializeAndFinalize_121", step="InitValues")
	public void mockAssembly() throws Exception {
		if (StringUtils.isNotBlank(assemblyOverride)) {
			ctx.setProperty(PROP_LOCAL_ASSEMBLY, assemblyOverride);
		}
	}

	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {
		// By this point in the component flow, Finally_121 will have been run, and the map we need for verifications will have been torn down.
		// Recreate so unit test code can use...
		Map<String, String> mockSubAssemblyDetailMap = new HashMap<String, String>();
		mockSubAssemblyDetailMap.put("Main","int006 Greenhouse Inbound > Integration Code > Begin Integration Work");
		mockSubAssemblyDetailMap.put("InitializeFrameworkThenRunMain","Studio StarterKit Library > CORE COMPONENTS > 109 - Initialization");
		mockSubAssemblyDetailMap.put("HandleError","Studio StarterKit Library > CORE COMPONENTS > 141 - Error Handling");
		mockSubAssemblyDetailMap.put("CreateLogEntry","Studio StarterKit Library > CORE COMPONENTS > 142 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("CreateSecondaryLogEntry","Studio StarterKit Library > CORE COMPONENTS > 142 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("StoreLogPrimary","Studio StarterKit Library > CORE COMPONENTS > 142 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("StoreLogSecondary","Studio StarterKit Library > CORE COMPONENTS > 142 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("LogStatsPrimary","Studio StarterKit Library > CORE COMPONENTS > 142 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("LogStatsSecondary","Studio StarterKit Library > CORE COMPONENTS > 142 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("StartThreadLog","Studio StarterKit Library > CORE COMPONENTS > 143 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("CreateLogEntryOnThread","Studio StarterKit Library > CORE COMPONENTS > 143 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("WriteThreadLogsToCloudLog","Studio StarterKit Library > CORE COMPONENTS > 143 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("Debug","Studio StarterKit Library > CORE COMPONENTS > 144 - General Cloud Logging");
		mockSubAssemblyDetailMap.put("CloudLogXSLTMessages","Studio StarterKit Library > CORE COMPONENTS > 112 - XSLT / ETV / XTT Cloud Logging");
		mockSubAssemblyDetailMap.put("CloudLogETVXTTMessages","Studio StarterKit Library > CORE COMPONENTS > 112 - XSLT / ETV / XTT Cloud Logging");
		mockSubAssemblyDetailMap.put("IntegrationEventProgress","Studio StarterKit Library > CORE COMPONENTS > 108 - Integration Event Progress Update");
		mockSubAssemblyDetailMap.put("StaticCodeAnalysis","Studio StarterKit Library > CORE COMPONENTS > 121 - Static Code Analysis");
		mockSubAssemblyDetailMap.put("AddReportPromptFromProperty","Studio StarterKit Library > REPORTS AS A SERVICE > 101 - Build Reports-as-a-Service Report Prompts");
		mockSubAssemblyDetailMap.put("AddReportPromptFromXml","Studio StarterKit Library > REPORTS AS A SERVICE > 101 - Build Reports-as-a-Service Report Prompts");
		mockSubAssemblyDetailMap.put("CallRaaS","Studio StarterKit Library > REPORTS AS A SERVICE > 102 - Reports-as-a-Service Execution");
		mockSubAssemblyDetailMap.put("CallSoap","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 103 - Workday Web Service (SOAP) Execution");
		mockSubAssemblyDetailMap.put("CallSoapPaged","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 104 - Workday Web Service (SOAP) Paged Execution");
		mockSubAssemblyDetailMap.put("CallSoapImport","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 113 - Workday Web Service (SOAP) Import APIs (Process)");
		mockSubAssemblyDetailMap.put("FinalizeCallSoapImport","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 113 - Workday Web Service (SOAP) Import APIs (Process)");
		mockSubAssemblyDetailMap.put("ReportResultsOfCallSoapImport","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 118 - Workday Web Service (SOAP) Import APIs (Post-Process)");
		mockSubAssemblyDetailMap.put("CreateQueue","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 122 - Workday Message Queue - Create Queue");
		mockSubAssemblyDetailMap.put("GetQueues","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 123 - Workday Message Queue - Get Queues");
		mockSubAssemblyDetailMap.put("PollQueue","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 124 - Workday Message Queue - Poll Queue");
		mockSubAssemblyDetailMap.put("ReadMessageFromQueue","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 125 - Workday Message Queue - Read Message");
		mockSubAssemblyDetailMap.put("AddMessageToQueue","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 126 - Workday Message Queue - Write Message");
		mockSubAssemblyDetailMap.put("DeleteMessageFromQueue","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 127 - Workday Message Queue - Delete Message");
		mockSubAssemblyDetailMap.put("PurgeQueue","Studio StarterKit Library > WORKDAY WEB SERVICES (WWS) API > 128 - Workday Message Queue - Purge Queue");
		mockSubAssemblyDetailMap.put("GenerateOutput","Studio StarterKit Library > FILE UTILITIES > 106 - Output Generation");
		mockSubAssemblyDetailMap.put("GetDocumentList","Studio StarterKit Library > FILE UTILITIES > 110 - Get Document List");
		mockSubAssemblyDetailMap.put("LoadFile","Studio StarterKit Library > FILE UTILITIES > 111 - Load File");
		mockSubAssemblyDetailMap.put("GetDISResults","Studio StarterKit Library > FILE UTILITIES > 115 - Data Initialization Service");
		mockSubAssemblyDetailMap.put("PopulateJavaMap","Studio StarterKit Library > XSLT 3.0 > 105 - Populate Java Map");
		mockSubAssemblyDetailMap.put("StreamDataMerge","Studio StarterKit Library > XSLT 3.0 > 107 - Streaming Data Merge");
		mockSubAssemblyDetailMap.put("BlockSplitter","Studio StarterKit Library > XSLT 3.0 > 116 - XML Block Splitting");
		mockSubAssemblyDetailMap.put("XsltPlus","Studio StarterKit Library > XSLT 3.0 > 129 - XSLT Transform");
		
		if ("testNullExemptionListSskAssembly".equalsIgnoreCase(getName()) || "testEmptyExemptionListSskAssembly".equalsIgnoreCase(getName())) {
			verifyLoggedMessagesUnexemptedAssembly(mockSubAssemblyDetailMap);
		} else if ("testExemptionListSskAssembly".equalsIgnoreCase(getName())) {
			verifyLoggedMessagesExemptedAssembly(mockSubAssemblyDetailMap);
		} else if ("testNullExemptionListAssemblyOverride1".equalsIgnoreCase(getName())) {
			verifyLoggedMessagesAssembly1(mockSubAssemblyDetailMap);
		} else if ("testNullExemptionListAssemblyOverride2".equalsIgnoreCase(getName())) {
			verifyLoggedMessagesAssembly2(mockSubAssemblyDetailMap);
		}
	}
	
	@AssertAfter(id="DeadCode_121")
	public void verifyUnusedLocalIns() throws Exception {
		if ("testNullExemptionListSskAssembly".equalsIgnoreCase(getName()) || "testEmptyExemptionListSskAssembly".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_UNEXEMPTED_DEAD_CODE, CONTENT_TYPE_TEXT_XML);
		} else if ("testExemptionListSskAssembly".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_EXEMPTED_DEAD_CODE, CONTENT_TYPE_TEXT_XML);
		} else if ("testNullExemptionListAssemblyOverride1".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_ASSEMBLY1_DEAD_CODE, CONTENT_TYPE_TEXT_XML);
		} else if ("testNullExemptionListAssemblyOverride2".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_ASSEMBLY2_DEAD_CODE, CONTENT_TYPE_TEXT_XML);
		}
	}
	
	@AssertAfter(id="MissingCode_121")
	public void verifyDeletedCoreComponents() throws Exception {
		if ("testNullExemptionListSskAssembly".equalsIgnoreCase(getName()) || "testEmptyExemptionListSskAssembly".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_UNEXEMPTED_MISSING_CODE, CONTENT_TYPE_TEXT_XML);
		} else if ("testExemptionListSskAssembly".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_EXEMPTED_MISSING_CODE, CONTENT_TYPE_TEXT_XML);
		} else if ("testNullExemptionListAssemblyOverride1".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_ASSEMBLY1_MISSING_CODE, CONTENT_TYPE_TEXT_XML);
		} else if ("testNullExemptionListAssemblyOverride2".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_ASSEMBLY2_MISSING_CODE, CONTENT_TYPE_TEXT_XML);
		}
	}
	
	@AssertAfter(id="Call_Aggregate_Metadata_121")
	public void verifyMissingCodePreAnalysis() throws Exception {
		if ("testNullExemptionListAssemblyOverride1".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_ASSEMBLY1_MESSAGE_MISSING_CODE_PRE_ANALYSIS, CONTENT_TYPE_TEXT_XML);
		} else if ("testNullExemptionListAssemblyOverride2".equalsIgnoreCase(getName())) {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_ASSEMBLY2_MESSAGE_MISSING_CODE_PRE_ANALYSIS, CONTENT_TYPE_TEXT_XML);
		} else {
			compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_SSK121_MESSAGE_MISSING_CODE_PRE_ANALYSIS, CONTENT_TYPE_TEXT_XML);
		}
	}
	
	private void verifyLoggedMessagesUnexemptedAssembly(Map<String, String> mockSubAssemblyDetailMap) throws Exception {
		List<String> unreferencedLocalInsThatShouldBeLogged = new ArrayList<String>();
		unreferencedLocalInsThatShouldBeLogged.add("Main");
		unreferencedLocalInsThatShouldBeLogged.add("CloudLogETVXTTMessages");
		unreferencedLocalInsThatShouldBeLogged.add("AddReportPromptFromProperty");
		unreferencedLocalInsThatShouldBeLogged.add("AddReportPromptFromXml");
		unreferencedLocalInsThatShouldBeLogged.add("CallRaaS");
		unreferencedLocalInsThatShouldBeLogged.add("CallSoap");
		unreferencedLocalInsThatShouldBeLogged.add("CallSoapPaged");
		unreferencedLocalInsThatShouldBeLogged.add("CallSoapImport");
		unreferencedLocalInsThatShouldBeLogged.add("FinalizeCallSoapImport");
		unreferencedLocalInsThatShouldBeLogged.add("ReportResultsOfCallSoapImport");
		unreferencedLocalInsThatShouldBeLogged.add("GetQueues");
		unreferencedLocalInsThatShouldBeLogged.add("PollQueue");
		unreferencedLocalInsThatShouldBeLogged.add("ReadMessageFromQueue");
		unreferencedLocalInsThatShouldBeLogged.add("AddMessageToQueue");
		unreferencedLocalInsThatShouldBeLogged.add("DeleteMessageFromQueue");
		unreferencedLocalInsThatShouldBeLogged.add("PurgeQueue");
		unreferencedLocalInsThatShouldBeLogged.add("GenerateOutput");
		unreferencedLocalInsThatShouldBeLogged.add("GetDocumentList");
		unreferencedLocalInsThatShouldBeLogged.add("LoadFile");
		unreferencedLocalInsThatShouldBeLogged.add("GetDISResults");
		unreferencedLocalInsThatShouldBeLogged.add("PopulateJavaMap");
		unreferencedLocalInsThatShouldBeLogged.add("StreamDataMerge");
		unreferencedLocalInsThatShouldBeLogged.add("BlockSplitter");
		unreferencedLocalInsThatShouldBeLogged.add("XsltPlus");
		
		String level = "WARN";
		String message = "Potential Dead Code Detected";
		String reference = "";
		String localIn = "";
		String errorCode = "12100";
		String recordNumber = "";
		
		String messageDetail;
		String supportData;
		
		for (String sa : unreferencedLocalInsThatShouldBeLogged) {
			messageDetail = "Static code analysis suggests that the SSK Component defined by local-in with id="+ sa +" may not be used in the integration.  See Support Data for more details.";
			supportData = "Static code analysis does not read dynamic endpoints, so if you have referenced this SSK Component dynamically, this may be a false-positive and you should make no code changes.  In this case, add your local-in id to the sskStaticCodeAnalysisExemptionList property in StaticAnalysisExemptions@Initialize_109, which can be found in the InitializeFrameworkThenRunMain SSK Component.  Otherwise, consider removing the SSK Component by deleting the " + mockSubAssemblyDetailMap.get(sa) + " swimlane, or removing the entire parent swimlane if no other SSK Components in that group are being used.";
			assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
		}
	}
	
	private void verifyLoggedMessagesExemptedAssembly(Map<String, String> mockSubAssemblyDetailMap) throws Exception {
		List<String> unreferencedLocalInsThatShouldBeLogged = new ArrayList<String>();
		unreferencedLocalInsThatShouldBeLogged.add("CloudLogETVXTTMessages");
		unreferencedLocalInsThatShouldBeLogged.add("AddReportPromptFromProperty");
		unreferencedLocalInsThatShouldBeLogged.add("AddReportPromptFromXml");
		unreferencedLocalInsThatShouldBeLogged.add("CallRaaS");
		unreferencedLocalInsThatShouldBeLogged.add("CallSoap");
		unreferencedLocalInsThatShouldBeLogged.add("CallSoapPaged");
		unreferencedLocalInsThatShouldBeLogged.add("CallSoapImport");
		unreferencedLocalInsThatShouldBeLogged.add("FinalizeCallSoapImport");
		unreferencedLocalInsThatShouldBeLogged.add("ReportResultsOfCallSoapImport");
		unreferencedLocalInsThatShouldBeLogged.add("GetQueues");
		unreferencedLocalInsThatShouldBeLogged.add("PollQueue");
		unreferencedLocalInsThatShouldBeLogged.add("ReadMessageFromQueue");
		unreferencedLocalInsThatShouldBeLogged.add("AddMessageToQueue");
		unreferencedLocalInsThatShouldBeLogged.add("DeleteMessageFromQueue");
		unreferencedLocalInsThatShouldBeLogged.add("PurgeQueue");
		unreferencedLocalInsThatShouldBeLogged.add("GenerateOutput");
		unreferencedLocalInsThatShouldBeLogged.add("GetDocumentList");
		unreferencedLocalInsThatShouldBeLogged.add("LoadFile");
		unreferencedLocalInsThatShouldBeLogged.add("GetDISResults");
		unreferencedLocalInsThatShouldBeLogged.add("PopulateJavaMap");
		unreferencedLocalInsThatShouldBeLogged.add("StreamDataMerge");
		unreferencedLocalInsThatShouldBeLogged.add("BlockSplitter");
		unreferencedLocalInsThatShouldBeLogged.add("XsltPlus");

		String level = "WARN";
		String message = "Potential Dead Code Detected";
		String reference = "";
		String localIn = "";
		String errorCode = "12100";
		String recordNumber = "";

		String supportData;
		String messageDetail;
		
		for (String sa : unreferencedLocalInsThatShouldBeLogged) {
			messageDetail = "Static code analysis suggests that the SSK Component defined by local-in with id="+ sa +" may not be used in the integration.  See Support Data for more details.";
			supportData = "Static code analysis does not read dynamic endpoints, so if you have referenced this SSK Component dynamically, this may be a false-positive and you should make no code changes.  In this case, add your local-in id to the sskStaticCodeAnalysisExemptionList property in StaticAnalysisExemptions@Initialize_109, which can be found in the InitializeFrameworkThenRunMain SSK Component.  Otherwise, consider removing the SSK Component by deleting the "+ mockSubAssemblyDetailMap.get(sa) +" swimlane, or removing the entire parent swimlane if no other SSK Components in that group are being used.";

			assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
		}
	}
	
	private void verifyLoggedMessagesAssembly1(Map<String, String> mockSubAssemblyDetailMap) throws Exception {
		String level = "WARN";
		String message = "SSK Core Component Missing";
		String reference = "";
		String localIn = "";
		String errorCode = "12101";
		String recordNumber = "";
		
		String messageDetail = "Static code analysis was unable to locate the SSK Core Component HandleError.  This may have been deleted from the integration.  See Support Data for more details.";
		String supportData = "SSK Core Components should not be removed, even if identified as potential dead code in static analysis.  All other SSK components assume the Core Components as dependencies, and you may inadvertently break another SSK component in-use by removing a Core Component.  If a Core Component is being flagged in static analysis, then add the local-in id to the sskStaticCodeAnalysisExemptionList property in StaticAnalysisExemptions@Initialize_109, which can be found in the InitializeFrameworkThenRunMain SSK Component.  Otherwise, restore the deleted code for HandleError in the " + mockSubAssemblyDetailMap.get("HandleError") + " swimlane from a clean copy of SSK.";
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);

		messageDetail = "Static code analysis was unable to locate the SSK Core Component StaticCodeAnalysis.  This may have been deleted from the integration.  See Support Data for more details.";
		supportData = "SSK Core Components should not be removed, even if identified as potential dead code in static analysis.  All other SSK components assume the Core Components as dependencies, and you may inadvertently break another SSK component in-use by removing a Core Component.  If a Core Component is being flagged in static analysis, then add the local-in id to the sskStaticCodeAnalysisExemptionList property in StaticAnalysisExemptions@Initialize_109, which can be found in the InitializeFrameworkThenRunMain SSK Component.  Otherwise, restore the deleted code for StaticCodeAnalysis in the " + mockSubAssemblyDetailMap.get("StaticCodeAnalysis") + " swimlane from a clean copy of SSK.";
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);

		messageDetail = "Static code analysis was unable to locate the SSK Core Component InitializeFrameworkThenRunMain.  This may have been deleted from the integration.  See Support Data for more details.";
		supportData = "SSK Core Components should not be removed, even if identified as potential dead code in static analysis.  All other SSK components assume the Core Components as dependencies, and you may inadvertently break another SSK component in-use by removing a Core Component.  If a Core Component is being flagged in static analysis, then add the local-in id to the sskStaticCodeAnalysisExemptionList property in StaticAnalysisExemptions@Initialize_109, which can be found in the InitializeFrameworkThenRunMain SSK Component.  Otherwise, restore the deleted code for InitializeFrameworkThenRunMain in the " + mockSubAssemblyDetailMap.get("InitializeFrameworkThenRunMain") + " swimlane from a clean copy of SSK.";
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);

		messageDetail = "Static code analysis was unable to locate the SSK Core Component CloudLogXSLTMessages.  This may have been deleted from the integration.  See Support Data for more details.";
		supportData = "SSK Core Components should not be removed, even if identified as potential dead code in static analysis.  All other SSK components assume the Core Components as dependencies, and you may inadvertently break another SSK component in-use by removing a Core Component.  If a Core Component is being flagged in static analysis, then add the local-in id to the sskStaticCodeAnalysisExemptionList property in StaticAnalysisExemptions@Initialize_109, which can be found in the InitializeFrameworkThenRunMain SSK Component.  Otherwise, restore the deleted code for CloudLogXSLTMessages in the " + mockSubAssemblyDetailMap.get("CloudLogXSLTMessages") + " swimlane from a clean copy of SSK.";
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);

		messageDetail = "Static code analysis was unable to locate the SSK Core Component CloudLogETVXTTMessages.  This may have been deleted from the integration.  See Support Data for more details.";
		supportData = "SSK Core Components should not be removed, even if identified as potential dead code in static analysis.  All other SSK components assume the Core Components as dependencies, and you may inadvertently break another SSK component in-use by removing a Core Component.  If a Core Component is being flagged in static analysis, then add the local-in id to the sskStaticCodeAnalysisExemptionList property in StaticAnalysisExemptions@Initialize_109, which can be found in the InitializeFrameworkThenRunMain SSK Component.  Otherwise, restore the deleted code for CloudLogETVXTTMessages in the " + mockSubAssemblyDetailMap.get("CloudLogETVXTTMessages") + " swimlane from a clean copy of SSK.";
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}

	private void verifyLoggedMessagesAssembly2(Map<String, String> mockSubAssemblyDetailMap) throws Exception {
		List<String> unreferencedLocalInsThatShouldBeLogged = new ArrayList<String>();
		unreferencedLocalInsThatShouldBeLogged.add("Main");
		unreferencedLocalInsThatShouldBeLogged.add("PopulateJavaMap");
		unreferencedLocalInsThatShouldBeLogged.add("CloudLogXSLTMessages");
		unreferencedLocalInsThatShouldBeLogged.add("AddReportPromptFromProperty");
		unreferencedLocalInsThatShouldBeLogged.add("AddReportPromptFromXml");
		unreferencedLocalInsThatShouldBeLogged.add("CallSoapPaged");
		unreferencedLocalInsThatShouldBeLogged.add("StreamDataMerge");

		String level = "WARN";
		String message = "Potential Dead Code Detected";
		String reference = "";
		String localIn = "";
		String errorCode = "12100";
		String recordNumber = "";

		String messageDetail;
		String supportData;

		for (String sa : unreferencedLocalInsThatShouldBeLogged) {
			messageDetail = "Static code analysis suggests that the SSK Component defined by local-in with id="+ sa +" may not be used in the integration.  See Support Data for more details.";
			supportData = "Static code analysis does not read dynamic endpoints, so if you have referenced this SSK Component dynamically, this may be a false-positive and you should make no code changes.  In this case, add your local-in id to the sskStaticCodeAnalysisExemptionList property in StaticAnalysisExemptions@Initialize_109, which can be found in the InitializeFrameworkThenRunMain SSK Component.  Otherwise, consider removing the SSK Component by deleting the "+ mockSubAssemblyDetailMap.get(sa) +" swimlane, or removing the entire parent swimlane if no other SSK Components in that group are being used.";
			assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
		}
				
		message = "SSK Core Component Missing";
		errorCode = "12101";
		messageDetail = "Static code analysis was unable to locate the SSK Core Component StaticCodeAnalysis.  This may have been deleted from the integration.  See Support Data for more details.";
		supportData = "SSK Core Components should not be removed, even if identified as potential dead code in static analysis.  All other SSK components assume the Core Components as dependencies, and you may inadvertently break another SSK component in-use by removing a Core Component.  If a Core Component is being flagged in static analysis, then add the local-in id to the sskStaticCodeAnalysisExemptionList property in StaticAnalysisExemptions@Initialize_109, which can be found in the InitializeFrameworkThenRunMain SSK Component.  Otherwise, restore the deleted code for StaticCodeAnalysis in the " + mockSubAssemblyDetailMap.get("StaticCodeAnalysis") + " swimlane from a clean copy of SSK.";
		assertPrimaryCloudLogEntryHTML(level, message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
}
