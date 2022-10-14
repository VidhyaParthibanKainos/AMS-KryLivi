package com.workday.custom.aunit.int006.utilities.ssk129;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent129TestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_INPUT_DATA = "globalInputData";
	public static final String VAR_GLOBAL_OUTPUT_DATA = "globalOutputData";
	
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inDataLocation";
	public static final String PROP_PARAMETER_IN_XSLT = "inPathToXsltFile";
	public static final String PROP_PARAMETER_IN_RETURN_LOCATION = "inReturnOutputLocation";
	public static final String PROP_PARAMETER_IN_MIN_LEVEL = "inMinLogLevel";
	public static final String PROP_PARAMETER_IN_LOG_TARGET = "inLogTarget";
	public static final String PROP_PARAMETER_IN_ABORT_ON_ERROR = "inIsAbortOnError";
	
	public static final String MOCK_XML_DATA_7 = "test/ssk/ssk129/SSK129_XML_7Records.xml";
	public static final String MOCK_XML_DATA_1 = "test/ssk/ssk129/SSK129_XML_1Record.xml";
	public static final String MOCK_XML_BAD_DATA = "test/ssk/ssk129/SSK129_XML_ResultWithBadData.xml";
	public static final String MOCK_XML_BAD_DATA_RESULT = "test/ssk/ssk129/SSK129_XML_BadDataTransformResult.xml";
	
	public static final String MOCK_XSLT_GOOD_NOLOG = "test/ssk/ssk129/SSK129_StreamingCopyTemplate.xsl";
	public static final String MOCK_XSLT_GOOD_LOG = "test/ssk/ssk129/SSK129_CopyTemplateWithLog.xsl";
	public static final String MOCK_XSLT_BAD = "test/ssk/ssk129/SSK129_UnitTest_MalformedTransform.xsl";
	public static final String MOCK_XSLT_TRY_CATCH_JAVA = "test/ssk/ssk129/SSK129_TransformWithJavaMessages.xsl";
	public static final String MOCK_XSLT_TRY_CATCH_XSLT = "test/ssk/ssk129/SSK129_TransformWithXsltMessages.xsl";

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "primary");
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformNoLogMessagesFromMessageToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_NOLOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformNoLogMessagesFromMessageToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_NOLOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, VAR_GLOBAL_OUTPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformNoLogMessagesFromVariableToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_GLOBAL_INPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_NOLOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, VAR_GLOBAL_OUTPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformNoLogMessagesFromVariableToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_GLOBAL_INPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_NOLOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, VAR_GLOBAL_OUTPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformWithMessagesFromMessageToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_LOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_DATA_1, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformHardErrorFromMessageToMessage() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_BAD));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_DATA_1, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformWithErrorTryCatchFromMessageToMessageJavaLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_TRY_CATCH_JAVA));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_BAD_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformWithErrorTryCatchFromMessageToMessageXsltLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_TRY_CATCH_XSLT));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_BAD_DATA, CONTENT_TYPE_TEXT_XML);
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
			if ("testTransformWithMessagesFromMessageToMessage".equalsIgnoreCase(getName())) {
				assertPrimaryCloudLogEntryHTML("info", "Summary Message", "Message details", null, null, null, null, null);
			} else if ("testTransformWithMessagesFromMessageToMessage".equalsIgnoreCase(getName())) {
				assertPrimaryCloudLogEntryHTML("warn", "Error occurred during date calculation", "Invalid date \"`1959-05-22-07:00\" (Non-numeric year component)", "21002", "mctx:vars/localInMemoryXslt", "err:FORG0001", "", "`1959-05-22-07:00");
			}
		}
	}

	@Override
	protected void handledExceptionVerification(Throwable t) throws Exception {
		super.handledExceptionVerification(t);
		
		if ("testTransformHardErrorFromMessageToMessage".equalsIgnoreCase(getName())) {
			
		}
	}
	
	@AssertAfter(id="ApplyTransform_129")
	public void verifyTransform() throws Exception {
		if ("testTransformNoLogMessagesFromMessageToMessage".equalsIgnoreCase(getName()) ||
				"testTransformNoLogMessagesFromVariableToMessage".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_DATA_7),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML));
		} else if ("testTransformNoLogMessagesFromMessageToVariable".equalsIgnoreCase(getName()) ||
				"testTransformNoLogMessagesFromVariableToVariable".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_DATA_7),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML));
		} else if ("testTransformWithMessagesFromMessageToMessage".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_DATA_1),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_1, CONTENT_TYPE_TEXT_XML));
		} else if ("testTransformWithErrorTryCatchFromMessageToMessageJavaLog".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_BAD_DATA_RESULT),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_BAD_DATA_RESULT, CONTENT_TYPE_TEXT_XML));
		} else if ("testTransformWithErrorTryCatchFromMessageToMessageXsltLog".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_BAD_DATA_RESULT),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_BAD_DATA_RESULT, CONTENT_TYPE_TEXT_XML));
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
