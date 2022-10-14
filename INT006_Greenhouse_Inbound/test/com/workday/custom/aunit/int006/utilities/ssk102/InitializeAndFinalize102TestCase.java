package com.workday.custom.aunit.int006.utilities.ssk102;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;
import com.workday.custom.int006.ssk102.ParameterMapHelper;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class InitializeAndFinalize102TestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_SAVE_RESULTS = "inSaveResultsToIntegrationEvent";
	public static final String PROP_PARAMETER_IN_REPORT_ALIAS = "inReportServiceAlias";
	public static final String PROP_PARAMETER_IN_PROMPT_MAP = "inPropertyNameReportPromptMap";
	public static final String PROP_PARAMETER_IN_RETURN_RESULTS = "inReturnResults";
	public static final String PROP_PARAMETER_IN_USE_JAVA_URL_ENCODER = "inIsUseJavaUrlEncoder";
	public static final String PROP_PARAMETER_IN_COMPRESS_RESULTS = "inCompressSavedResults";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";

	public static final String PROP_LOCAL_PROMPT_MAP = "localMapReference";
	public static final String PROP_LOCAL_REST_URL = "localRestUrl";

	public static final String VALUE_ALL_CHARACTERS_URL_ENCODE_BEFORE = "0123456789`~!@#$%^&*()_+-= abcdefghijklmnopqrstuvwxyz[]\\{}|;':\",./<>?ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String VALUE_ALL_CHARACTERS_URL_ENCODE_AFTER = "0123456789%60~%21@%23$%25%5E%26*()_%2B-=%20abcdefghijklmnopqrstuvwxyz[]%5C%7B%7D%7C%3B%27:\",./<>?ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String VALUE_ALL_CHARACTERS_DEFAULT_URL_ENCODE_BEFORE = "0123456789`~!@#$%^&*()_+-= abcdefghijklmnopqrstuvwxyz[]\\{}|;':\",./<>?ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String VALUE_ALL_CHARACTERS_DEFAULT_URL_ENCODE_AFTER = "0123456789%60%7E%21%40%23%24%25%5E%26*%28%29_%2B-%3D+abcdefghijklmnopqrstuvwxyz%5B%5D%5C%7B%7D%7C%3B%27%3A%22%2C.%2F%3C%3E%3FABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String VALUE_CHARACTERS_URL_ENCODE_BEFORE_1 = "0123456789`~!@#$%^&*()_+-= abcdefghijklmnopqrstuvwxyz";
	public static final String VALUE_CHARACTERS_URL_ENCODE_AFTER_1 = "0123456789%60~%21@%23$%25%5E%26*()_%2B-=%20abcdefghijklmnopqrstuvwxyz";
	public static final String VALUE_CHARACTERS_URL_ENCODE_BEFORE_2 = "[]\\{}|;':\",./<>?ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String VALUE_CHARACTERS_URL_ENCODE_AFTER_2 = "[]%5C%7B%7D%7C%3B%27:\",./<>?ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String VALUE_CHARACTERS_URL_ENCODE_BEFORE_3 = "0123456789[]\\{}|;':\",./<>?ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String VALUE_CHARACTERS_URL_ENCODE_AFTER_3 = "0123456789[]%5C%7B%7D%7C%3B%27:\",./<>?ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String VALUE_CHARACTERS_URL_ENCODE_BEFORE_4 = "@#[]\\";
	public static final String VALUE_CHARACTERS_URL_ENCODE_AFTER_4 = "@%23[]%5C";
	public static final String VALUE_CHARACTERS_URL_ENCODE_BEFORE_5 = "0 %;";
	public static final String VALUE_CHARACTERS_URL_ENCODE_AFTER_5 = "0%20%25%3B";
	public static final String VALUE_REPORT_PROMPT_WORKER = "Worker!WID";
	public static final String VALUE_REPORT_SERVICE_ALIAS = "Worker Data";
	public static final String VALUE_SSK102_REPORT_PROMPT1 = "SA102_PRV1";
	public static final String VALUE_SSK102_REPORT_PROMPT2 = "SA102_PRV2";
	public static final String VALUE_REPORT_PROMPT_VALUE1 = "0e44c92412d34b01ace61e80a47aaf6d";
	public static final String VALUE_REPORT_PROMPT_VALUE2 = "3aa5550b7fe348b98d7b5741afc65534";
	public static final String VALUE_REPORT_REST_ENDPOINT = "customreport2/testTenant/ISU_INT/CR_INT_Worker_Data";

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setupGlobalInitialization();
		
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, true);
		ctx.setProperty(PROP_PARAMETER_IN_COMPRESS_RESULTS, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);

		ParameterMapHelper.initializeParameterMap(this.ctx, PROP_LOCAL_PROMPT_MAP);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	
	@UnitTest(startComponent="InitializeAndFinalize_102")
	public void testBuildURLNoEncoding() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_USE_JAVA_URL_ENCODER, false);

		Map<String,Object> prompts = new HashMap<String,Object>();
		List<String> workers = new ArrayList<String>();
		workers.add(VALUE_REPORT_PROMPT_VALUE1);
		workers.add(VALUE_REPORT_PROMPT_VALUE2);
		prompts.put(VALUE_REPORT_PROMPT_WORKER, workers);
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
	}

	@UnitTest(startComponent="InitializeAndFinalize_102")
	public void testBuildURLOneScalarPromptEncoding() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_USE_JAVA_URL_ENCODER, false);

		Map<String,Object> prompts = new HashMap<String,Object>();
		prompts.put(VALUE_SSK102_REPORT_PROMPT1, VALUE_ALL_CHARACTERS_URL_ENCODE_BEFORE);
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
	}

	@UnitTest(startComponent="InitializeAndFinalize_102")
	public void testBuildURLTwoScalarPromptsEncoding() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_USE_JAVA_URL_ENCODER, false);

		Map<String,Object> prompts = new HashMap<String,Object>();
		prompts.put(VALUE_SSK102_REPORT_PROMPT1, VALUE_ALL_CHARACTERS_URL_ENCODE_BEFORE);
		prompts.put(VALUE_SSK102_REPORT_PROMPT2, VALUE_CHARACTERS_URL_ENCODE_BEFORE_5);
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
	}

	@UnitTest(startComponent="InitializeAndFinalize_102")
	public void testBuildURLOneListPromptEncoding() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_USE_JAVA_URL_ENCODER, false);

		Map<String,Object> prompts = new HashMap<String,Object>();
		List<String> values = new ArrayList<String>();
		values.add(VALUE_CHARACTERS_URL_ENCODE_BEFORE_1);
		values.add(VALUE_CHARACTERS_URL_ENCODE_BEFORE_2);
		values.add(VALUE_CHARACTERS_URL_ENCODE_BEFORE_3);
		values.add(VALUE_CHARACTERS_URL_ENCODE_BEFORE_4);
		prompts.put(VALUE_SSK102_REPORT_PROMPT1, values);
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
	}

	@UnitTest(startComponent="InitializeAndFinalize_102")
	public void testBuildURLOneScalarAndOneListPromptEncoding() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_USE_JAVA_URL_ENCODER, false);

		Map<String,Object> prompts = new HashMap<String,Object>();
		List<String> values = new ArrayList<String>();
		values.add(VALUE_CHARACTERS_URL_ENCODE_BEFORE_1);
		values.add(VALUE_CHARACTERS_URL_ENCODE_BEFORE_2);
		values.add(VALUE_REPORT_PROMPT_VALUE1);
		values.add(VALUE_ALL_CHARACTERS_URL_ENCODE_BEFORE);
		prompts.put(VALUE_SSK102_REPORT_PROMPT1, values);
		prompts.put(VALUE_SSK102_REPORT_PROMPT2, VALUE_CHARACTERS_URL_ENCODE_BEFORE_5);
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
	}

	@UnitTest(startComponent="InitializeAndFinalize_102")
	public void testBuildURLOneScalarPromptDefaultEncoding() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_USE_JAVA_URL_ENCODER, true);
		
		Map<String,Object> prompts = new HashMap<String,Object>();
		prompts.put(VALUE_SSK102_REPORT_PROMPT1, VALUE_ALL_CHARACTERS_DEFAULT_URL_ENCODE_BEFORE);
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
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
	
	@AssertAfter(id="InitializeAndFinalize_102", step="BuildURL")
	public void verifyRaaSEndpoint() throws Exception {
		switch(getName()) {
			case "testBuildURLNoEncoding" :
				validateTestBuildURLNoEncoding();
				break;
		
			case "testBuildURLOneScalarPromptEncoding" :
				validateTestBuildURLOneScalarPromptEncoding();
				break;
				
			case "testBuildURLTwoScalarPromptsEncoding" :
				validateTestBuildURLTwoScalarPromptsEncoding();
				break;
				
			case "testBuildURLOneListPromptEncoding" :
				validateTestBuildURLOneListPromptEncoding();
				break;
				
			case "testBuildURLOneScalarAndOneListPromptEncoding" :
				validateTestBuildURLOneScalarAndOneListPromptEncoding();
				break;
				
			case "testBuildURLOneScalarPromptDefaultEncoding" :
				validateTestBuildURLOneScalarPromptDefaultEncoding();
				break;
				
			default :
				break;
		}
	}
	
	private void validateTestBuildURLNoEncoding() {
		String expectedURL = VALUE_REPORT_REST_ENDPOINT + "?" + VALUE_REPORT_PROMPT_WORKER + "=" + VALUE_REPORT_PROMPT_VALUE1 + "!" + VALUE_REPORT_PROMPT_VALUE2;
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_REST_URL), expectedURL, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
	}
	
	private void validateTestBuildURLOneScalarPromptEncoding() {
		String expectedURL = VALUE_REPORT_REST_ENDPOINT + "?" + VALUE_SSK102_REPORT_PROMPT1 + "=" + VALUE_ALL_CHARACTERS_URL_ENCODE_AFTER;
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_REST_URL), expectedURL, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
	}
	
	private void validateTestBuildURLTwoScalarPromptsEncoding() {
		String expectedURL = VALUE_REPORT_REST_ENDPOINT + "?" + VALUE_SSK102_REPORT_PROMPT1 + "=" + VALUE_ALL_CHARACTERS_URL_ENCODE_AFTER + "&" + VALUE_SSK102_REPORT_PROMPT2 + "=" + VALUE_CHARACTERS_URL_ENCODE_AFTER_5;
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_REST_URL), expectedURL, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
	}
	
	private void validateTestBuildURLOneListPromptEncoding() {
		String expectedURL = VALUE_REPORT_REST_ENDPOINT + "?" + VALUE_SSK102_REPORT_PROMPT1 + "=" + VALUE_CHARACTERS_URL_ENCODE_AFTER_1 + "!" + VALUE_CHARACTERS_URL_ENCODE_AFTER_2 + "!" + VALUE_CHARACTERS_URL_ENCODE_AFTER_3 + "!" + VALUE_CHARACTERS_URL_ENCODE_AFTER_4;
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_REST_URL), expectedURL, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
	}
	
	private void validateTestBuildURLOneScalarAndOneListPromptEncoding() {
		String expectedURL = VALUE_REPORT_REST_ENDPOINT + "?" + VALUE_SSK102_REPORT_PROMPT1 + "=" + VALUE_CHARACTERS_URL_ENCODE_AFTER_1 + "!" + VALUE_CHARACTERS_URL_ENCODE_AFTER_2 + "!" + VALUE_REPORT_PROMPT_VALUE1 + "!" + VALUE_ALL_CHARACTERS_URL_ENCODE_AFTER + "&" + VALUE_SSK102_REPORT_PROMPT2 + "=" + VALUE_CHARACTERS_URL_ENCODE_AFTER_5;
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_REST_URL), expectedURL, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
	}
	
	private void validateTestBuildURLOneScalarPromptDefaultEncoding() {
		String expectedURL = VALUE_REPORT_REST_ENDPOINT + "?" + VALUE_SSK102_REPORT_PROMPT1 + "=" + VALUE_ALL_CHARACTERS_DEFAULT_URL_ENCODE_AFTER;
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_REST_URL), expectedURL, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	
	@AtComponent(id="ChooseProtocol_102")
	public Action terminate() throws Exception {
		return new StandardAction(Action.Type.terminate); 
	}

}
