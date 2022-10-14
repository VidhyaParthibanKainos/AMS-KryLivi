package com.workday.custom.aunit.int006.utilities.ssk112;

import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;
import com.workday.custom.int006.ssk112.CloudLogEntry;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent112SerializationTestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_MESSAGES = "inMessages";
	public static final String PROP_PARAMETER_IN_IS_CLEAR_MESSAGES = "inIsClearMessages";
	public static final String PROP_PARAMETER_IN_MIN_LOG_LEVEL = "inMinLogLevel";

	Logger log = LogControl.getLogger(getClass());

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		super.setupGlobalInitialization();

		ctx.setProperty(PROP_PARAMETER_IN_IS_CLEAR_MESSAGES, false);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="InitializeAndFinalize_XSLT_112")
	public void testSerializeMessage00() throws Exception {
		String expectedValueLevel = "<l>info</l>";
		String expectedValueSummary = "<m>This message contains XML characters to be encoded { &amp; &lt; &gt; \" ' }</m>";
		
		String serialized = CloudLogEntry.createMessage("This message contains XML characters to be encoded { & < > \" ' }");
		assertTrue("The expected message string was not returned - the level was different.", serialized.contains(expectedValueLevel));
		assertTrue("The expected message string was not returned - the summary was different.", serialized.contains(expectedValueSummary));
	}
	
	@UnitTest(startComponent="InitializeAndFinalize_XSLT_112")
	public void testSerializeMessage01() throws Exception {
		String expectedValueLevel = "<l>debug</l>";
		String expectedValueSummary = "<m>This is a debug message</m>";
		
		String serialized = CloudLogEntry.createMessage("DEBUG", "This is a debug message");
		assertTrue("The expected message string was not returned - the level was different.", serialized.contains(expectedValueLevel));
		assertTrue("The expected message string was not returned - the summary was different.", serialized.contains(expectedValueSummary));
	}
	
	@UnitTest(startComponent="InitializeAndFinalize_XSLT_112")
	public void testSerializeMessage02() throws Exception {
		String expectedValueLevel = "<l>warn</l>";
		String expectedValueSummary = "<m>This is a warning message</m>";
		String expectedValueDetail = "<d>This message detail contains XML characters to be encoded { &amp; &lt; &gt; \" ' }</d>";
		
		String serialized = CloudLogEntry.createMessage("WARN", "This is a warning message", "This message detail contains XML characters to be encoded { & < > \" ' }");
		assertTrue("The expected message string was not returned - the level was different.", serialized.contains(expectedValueLevel));
		assertTrue("The expected message string was not returned - the summary was different.", serialized.contains(expectedValueSummary));
		assertTrue("The expected message string was not returned - the detail was different.", serialized.contains(expectedValueDetail));
	}
	
	@UnitTest(startComponent="InitializeAndFinalize_XSLT_112")
	public void testSerializeMessage03() throws Exception {
		String expectedValueLevel = "<l>error</l>";
		String expectedValueSummary = "<m>Aww, SNAP!</m>";
		String expectedValueDetail = "<d>This message contains XML characters to be encoded { &amp; &lt; &gt; \" ' }</d>";
		String expectedValueRefId = "<r>123456789</r>";
		
		String serialized = CloudLogEntry.createMessage("error", "Aww, SNAP!", "This message contains XML characters to be encoded { & < > \" ' }", "123456789");
		assertTrue("The expected message string was not returned - the level was different.", serialized.contains(expectedValueLevel));
		assertTrue("The expected message string was not returned - the summary was different.", serialized.contains(expectedValueSummary));
		assertTrue("The expected message string was not returned - the detail was different.", serialized.contains(expectedValueDetail));
		assertTrue("The expected message string was not returned - the referenceId was different.", serialized.contains(expectedValueRefId));
	}
	
	@UnitTest(startComponent="InitializeAndFinalize_XSLT_112")
	public void testSerializeMessage04() throws Exception {
		String expectedValueLevel = "<l>error</l>";
		String expectedValueSummary = "<m>Aww, SNAP!</m>";
		String expectedValueDetail = "<d>This message contains XML characters to be encoded { &amp; &lt; &gt; \" ' }</d>";
		String expectedValueRefId = "<r>123456789</r>";
		String expectedValueRecord = "<n>7</n>";
		String expectedValueLocalIn = "<t>CloudLogXSLTMessages</t>";
		String expectedValueCode = "<c>11200</c>";
		String expectedValueSupport = "<a>&lt;wd:Report_Entry&gt;&lt;wd:Worker&gt;&lt;wd:ID wd:type='Employee_ID'&gt;123456789&lt;/wd:ID&gt;&lt;/wd:Worker&gt;&lt;/wd:Report_Entry&gt;</a>";
		
		String serialized = CloudLogEntry.createMessage("error", "Aww, SNAP!", "This message contains XML characters to be encoded { & < > \" ' }", "123456789", "7", "CloudLogXSLTMessages", "11200", "<wd:Report_Entry><wd:Worker><wd:ID wd:type='Employee_ID'>123456789</wd:ID></wd:Worker></wd:Report_Entry>");
		assertTrue("The expected message string was not returned - the level was different.", serialized.contains(expectedValueLevel));
		assertTrue("The expected message string was not returned - the summary was different.", serialized.contains(expectedValueSummary));
		assertTrue("The expected message string was not returned - the detail was different.", serialized.contains(expectedValueDetail));
		assertTrue("The expected message string was not returned - the referenceId was different.", serialized.contains(expectedValueRefId));
		assertTrue("The expected message string was not returned - the record number was different.", serialized.contains(expectedValueRecord));
		assertTrue("The expected message string was not returned - the local-in was different.", serialized.contains(expectedValueLocalIn));
		assertTrue("The expected message string was not returned - the error code was different.", serialized.contains(expectedValueCode));
		assertTrue("The expected message string was not returned - the support data was different.", serialized.contains(expectedValueSupport));
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
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@AtComponent(id="Call_CloudLogMessages_XSLT_112")
	public Action mockCloudLogXsltMessages() throws Exception {
		return new StandardAction(Action.Type.terminate);
	}

}
