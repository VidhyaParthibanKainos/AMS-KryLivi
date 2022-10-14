package com.workday.custom.aunit.int006.utilities.ssk120;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;
import com.workday.custom.int006.SSKUtils;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent120TestCase extends UtilitiesTestCase {
	
	public static final String VALUE_ALIAS_MULTI_INSTANCE_SERVICE = "shoesService";
	public static final String VALUE_ALIAS_SINGLE_INSTANCE_SERVICE = "spouseService";
	public static final String VALUE_HTTP_METHOD_PUT = "PUT";
	public static final String VALUE_HTTP_METHOD_POST = "POST";
	
	public static final String VAR_RECORD_COUNT = "localCountResult120";
	public static final String VAR_TRANSFORM = "localTransform120";
	public static final String VAR_DATA_INPUT = "aunitVariableInputSource";
	public static final String VAR_COMPONENT_OUTPUT = "theRequestedVariableToUse";
	
	public static final String PROP_PARAMETER_IN_SERVICE_ALIAS = "inCustomObjectServiceAlias";
	public static final String PROP_PARAMETER_IN_HTTP_METHOD = "inHttpMethod";
	public static final String PROP_PARAMETER_IN_UPDATE_IF_EXISTS = "inUpdateIfExists";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inParameterDataLocation";
	public static final String PROP_PARAMETER_IN_XSLT_FILE = "inPathToXsltFile";
	public static final String PROP_PARAMETER_IN_XSD_FILE = "inPathToXsdFile";
	public static final String PROP_PARAMETER_IN_VALIDATE_ONLY = "inValidateOnly";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	
	public static final String PROP_LOCAL_USE_BATCH = "localIsUseBatch";
	public static final String PROP_LOCAL_URL = "localRestUrl";
	public static final String PROP_LOCAL_RECORD_COUNT = "localRecordCount";
	
	private static final String VALUE_SINGLE_INSTANCE_API = "api/v1/testTenant/customObjects/spouse";
	private static final String VALUE_MULTI_INSTANCE_API = "api/v1/testTenant/customObjects/shoes";
	
	public static final String MOCK_SSK120_XML_MULTI_INSTANCE_DATA = "test/ssk/ssk120/SSK120_XML_MultiInstanceData.xml";
	public static final String MOCK_SSK120_XML_MULTI_INSTANCE_BULK_DATA = "test/ssk/ssk120/SSK120_XML_MultiInstanceData_Bulk.xml";
	public static final String MOCK_SSK120_XML_SINGLE_INSTANCE_DATA = "test/ssk/ssk120/SSK120_XML_SingleInstanceData.xml";
	public static final String MOCK_SSK120_XML_SINGLE_INSTANCE_BULK_DATA = "test/ssk/ssk120/SSK120_XML_SingleInstanceData_Bulk.xml";
	public static final String MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA = "test/ssk/ssk120/SSK120_JSON_MultiInstanceData.json";
	public static final String MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_BULK_DATA = "test/ssk/ssk120/SSK120_JSON_MultiInstanceData_Bulk.json";
	public static final String MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_DATA = "test/ssk/ssk120/SSK120_JSON_SingleInstanceData.json";
	public static final String MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_BULK_DATA = "test/ssk/ssk120/SSK120_JSON_SingleInstanceData_Bulk.json";
	public static final String MOCK_SSK120_JSON_MULTI_INSTANCE_XSLT_RESULT = "test/ssk/ssk120/SSK120_XML_MultiInstanceData_TransformResult.json";
	
	public static final String MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA = "test/ssk/ssk120/SSK120_Test_XmlToJson_MultiInstanceService.xsd";
	public static final String MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM = "test/ssk/ssk120/SSK120_Test_Xslt_MultiInstanceService.xsl";
	public static final String MOCK_SSK120_XML_SINGLE_INSTANCE_SCHEMA = "test/ssk/ssk120/SSK120_Test_XmlToJson_SingleInstanceService.xsd";
	public static final String MOCK_SSK120_XML_SINGLE_INSTANCE_TRANSFORM = "test/ssk/ssk120/SSK120_Test_Xslt_SingleInstanceService.xsl";
	
	public static final String MOCK_SSK120_JSON_RESPONSE_SUCCESS_BULK = "test/ssk/ssk120/SSK120_JSON_ResponseSuccess_Bulk.json";
	public static final String MOCK_SSK120_JSON_RESPONSE_SUCCESS_SINGLE = "test/ssk/ssk120/SSK120_JSON_ResponseSuccess_Single.json";
	public static final String MOCK_SSK120_JSON_RESPONSE_ERROR_SUMMARY = "test/ssk/ssk120/SSK120_JSON_ResponseError_SummaryOnly.json";
	public static final String MOCK_SSK120_JSON_RESPONSE_ERROR_DETAILS = "test/ssk/ssk120/SSK120_JSON_ResponseError_IncludesDetails.json";

	private Integer expectedRecordCount = new Integer(0);
	private Integer expectedTransformCount = new Integer(0);

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mockTracker.addComponentTracking("BuildRequestFromXmlAndXslt_120");
		mockTracker.addComponentTracking("BuildRequestFromXmlAndXsd_120");
		
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, Boolean.TRUE);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiMessageJsonDataToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_COMPONENT_OUTPUT);
		
		setMessagePart(0, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiVariableJsonDataToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_COMPONENT_OUTPUT);
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	/*
	 * tests for multi-instance objects
	 */

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiMessageJsonDataToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setMessagePart(0, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiMessageJsonBulkDataToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setMessagePart(0, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_BULK_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiVariableJsonDataToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiVariableJsonBulkDataToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_BULK_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

//	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
//	public void testMultiMessageXmlDataXsdTransformToMessage() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
//		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
//		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA);
//		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
//		
//		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
//
//		expectedRecordCount = new Integer(5);
//	}
//
//	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
//	public void testMultiMessageXmlBulkDataXsdTransformToMessage() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
//		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
//		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA);
//		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
//		
//		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);
//	}
//
//	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
//	public void testMultiVariableXmlDataXsdTransformToMessage() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
//		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
//		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA);
//		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
//		
//		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
//	
//		expectedRecordCount = new Integer(5);
//	}
//
//	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
//	public void testMultiVariableXmlBulkDataXsdTransformToMessage() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
//		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
//		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA);
//		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
//		
//		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_MULTI_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);
//	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiMessageXmlDataXsltTransformToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);

		expectedRecordCount = new Integer(5);
		expectedTransformCount = new Integer(3);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiMessageXmlBulkDataXsltTransformToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);

		expectedRecordCount = new Integer(5);
		expectedTransformCount = new Integer(3);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiVariableXmlDataXsltTransformToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);

		expectedRecordCount = new Integer(5);
		expectedTransformCount = new Integer(3);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testMultiVariableXmlBulkDataXsltTransformToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_MULTI_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);

		expectedRecordCount = new Integer(5);
		expectedTransformCount = new Integer(3);
	}
	
	/*
	 * tests for single-instance objects
	 */

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testSingleMessageJsonDataToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setMessagePart(0, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testSingleMessageJsonBulkDataToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setMessagePart(0, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_BULK_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testSingleVariableJsonDataToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testSingleVariableJsonBulkDataToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_BULK_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

//	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
//	public void testSingleMessageXmlDataXsdTransformToMessage() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
//		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
//		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA);
//		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
//		
//		setMessagePart(0, MOCK_SSK120_XML_SINGLE_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
//	}
//
//	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
//	public void testSingleMessageXmlBulkDataXsdTransformToMessage() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
//		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
//		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_SINGLE_INSTANCE_SCHEMA);
//		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
//		
//		setMessagePart(0, MOCK_SSK120_XML_SINGLE_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);
//	}
//
//	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
//	public void testSingleVariableXmlDataXsdTransformToMessage() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
//		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
//		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_SINGLE_INSTANCE_SCHEMA);
//		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
//		
//		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_SINGLE_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
//	}
//
//	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
//	public void testSingleVariableXmlBulkDataXsdTransformToMessage() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
//		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
//		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_SINGLE_INSTANCE_SCHEMA);
//		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
//		
//		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_SINGLE_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);
//	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testSingleMessageXmlDataXsltTransformToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_SINGLE_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setMessagePart(0, MOCK_SSK120_XML_SINGLE_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);

		expectedRecordCount = new Integer(1);
		expectedTransformCount = new Integer(1);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testSingleMessageXmlBulkDataXsltTransformToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_SINGLE_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setMessagePart(0, MOCK_SSK120_XML_SINGLE_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);

		expectedRecordCount = new Integer(4);
		expectedTransformCount = new Integer(2);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testSingleVariableXmlDataXsltTransformToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_SINGLE_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_SINGLE_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);

		expectedRecordCount = new Integer(1);
		expectedTransformCount = new Integer(1);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testSingleVariableXmlBulkDataXsltTransformToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_SINGLE_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_SINGLE_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_SINGLE_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);

		expectedRecordCount = new Integer(4);
		expectedTransformCount = new Integer(2);
	}
	
	/*
	 * local-in validation exceptions
	 */
	
	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPutMessageXmlDataDoubleTransform() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_PUT);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA));
		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		
		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPutVariableXmlDataDoubleTransform() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_PUT);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA));
		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPutMessageJsonDataXsdTransform() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_PUT);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA));
		
		setMessagePart(0, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPutMessageJsonDataXsltTransform() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_PUT);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		
		setMessagePart(0, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPutMessageXmlDataNoTransform() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_PUT);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		
		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPutVariableJsonDataXsdTransform() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_PUT);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA));
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPutVariableJsonDataXsltTransform() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_PUT);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPutVariableXmlDataNoTransform() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_PUT);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_INPUT);
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPostNoDataNoTransformPassiveMessageXmlData() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		
		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPostNoDataNoTransformPassiveMessageJsonData() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		
		setMessagePart(0, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPostNoDataNoTransformPassiveVariableXmlData() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPostNoDataNoTransformPassiveVariableJsonData() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		
		setVariable(VAR_DATA_INPUT, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WriteNonEffectiveDatedCustomObject")
	public void testPutWithUpdateIfExists() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_ALIAS, VALUE_ALIAS_MULTI_INSTANCE_SERVICE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_PUT);
		ctx.setProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA));
		
		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
	}

	/*
	 * Tests focused precisely on the fitness of transformations to the data
	 */
	
//	@UnitTest(startComponent="TransformXML_120")
//	public void testMultiInstanceXsd() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA);
//		
//		setVariable(VAR_TRANSFORM, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA, CONTENT_TYPE_TEXT_XML);
//		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
//	}
//
//	@UnitTest(startComponent="TransformXML_120")
//	public void testMultiInstanceBulkXsd() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA);
//		
//		setVariable(VAR_TRANSFORM, MOCK_SSK120_XML_MULTI_INSTANCE_SCHEMA, CONTENT_TYPE_TEXT_XML);
//		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);
//	}
//
//	@UnitTest(startComponent="TransformXML_120")
//	public void testSingleInstanceXsd() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_SINGLE_INSTANCE_SCHEMA);
//		
//		setVariable(VAR_TRANSFORM, MOCK_SSK120_XML_SINGLE_INSTANCE_SCHEMA, CONTENT_TYPE_TEXT_XML);
//		setMessagePart(0, MOCK_SSK120_XML_SINGLE_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
//	}
//
//	@UnitTest(startComponent="TransformXML_120")
//	public void testSingleInstanceBulkXsd() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_XSD_FILE, MOCK_SSK120_XML_SINGLE_INSTANCE_SCHEMA);
//		
//		setVariable(VAR_TRANSFORM, MOCK_SSK120_XML_SINGLE_INSTANCE_SCHEMA, CONTENT_TYPE_TEXT_XML);
//		setMessagePart(0, MOCK_SSK120_XML_SINGLE_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);
//	}
	
	@UnitTest(startComponent="TransformXML_120")
	public void testMultiInstanceXslt() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_LOCAL_RECORD_COUNT, 5);
		
		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
		
		expectedTransformCount = new Integer(1);
	}

	@UnitTest(startComponent="TransformXML_120")
	public void testMultiInstanceBulkXslt() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_MULTI_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_LOCAL_RECORD_COUNT, 5);
		
		setMessagePart(0, MOCK_SSK120_XML_MULTI_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);
		
		expectedTransformCount = new Integer(1);
	}

	@UnitTest(startComponent="TransformXML_120")
	public void testSingleInstanceXslt() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_SINGLE_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_LOCAL_RECORD_COUNT, 1);
		
		setMessagePart(0, MOCK_SSK120_XML_SINGLE_INSTANCE_DATA, CONTENT_TYPE_TEXT_XML);
		
		expectedTransformCount = new Integer(1);
	}

	@UnitTest(startComponent="TransformXML_120")
	public void testSingleInstanceBulkXslt() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_XSLT_FILE, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_SSK120_XML_SINGLE_INSTANCE_TRANSFORM));
		ctx.setProperty(PROP_LOCAL_RECORD_COUNT, 4);
		
		setMessagePart(0, MOCK_SSK120_XML_SINGLE_INSTANCE_BULK_DATA, CONTENT_TYPE_TEXT_XML);
		
		expectedTransformCount = new Integer(1);
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
		String currentTestName = getName();

		if ("testMultiMessageJsonDataToVariable".equalsIgnoreCase(currentTestName) ||
				"testMultiVariableJsonDataToVariable".equalsIgnoreCase(currentTestName)) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_COMPONENT_OUTPUT),
					compareAgainstVariable(VAR_COMPONENT_OUTPUT, CONTENT_TYPE_APPLICATION_JSON, MOCK_SSK120_JSON_RESPONSE_SUCCESS_BULK, CONTENT_TYPE_APPLICATION_JSON));
		} else if (getName().contains("Xslt") && !getName().contains("Error")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, "number of calls to transformation mediation"), expectedTransformCount.intValue(), mockTracker.getCallCount("BuildRequestFromXmlAndXslt_120"));
		}

	}
	
	@AssertAfter(id="InitializeAndFinalize_120")
	public void verifyURL() throws Exception {
		String httpMethod = null;
		boolean isUpdate = false;
		String composedURL = String.valueOf(ctx.getProperty(PROP_LOCAL_URL));
		
		if (getName().contains("Multi")) {
			httpMethod = String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_HTTP_METHOD));
			isUpdate = (boolean)ctx.getProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS);
			
			if (httpMethod.equalsIgnoreCase("post") && isUpdate) {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_URL), VALUE_MULTI_INSTANCE_API + "?updateIfExists=true", composedURL);
			} else {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_URL), VALUE_MULTI_INSTANCE_API, composedURL);
			}
		} else if (getName().contains("Single")) {
			httpMethod = String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_HTTP_METHOD));
			isUpdate = (boolean)ctx.getProperty(PROP_PARAMETER_IN_UPDATE_IF_EXISTS);
			
			if (httpMethod.equalsIgnoreCase("post") && isUpdate) {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_URL), VALUE_SINGLE_INSTANCE_API + "?updateIfExists=true", composedURL);
			} else {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_URL), VALUE_SINGLE_INSTANCE_API, composedURL);
			}
		}
	}
	
	@AssertAfter(id="InitializeXml_120", step="CountRecords")
	public void verifyRecordCount() throws Exception {
		Integer actualCount = Integer.valueOf(SSKUtils.getValueFromVariableXml(ctx, VAR_RECORD_COUNT, "/*"));
		
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_RECORD_COUNT), expectedRecordCount, actualCount);
	}
	
	@AssertAfter(id="BuildRequestFromXmlAndXslt_120")
	public void verifyXsltTransformation() throws Exception {
		mockTracker.incrementCallCount("BuildRequestFromXmlAndXslt_120");

		switch (getName()) {
			case "testMultiInstanceXslt" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA), 
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON));
				break;
	
			case "testMultiInstanceBulkXslt" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_BULK_DATA), 
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_BULK_DATA, CONTENT_TYPE_APPLICATION_JSON));
				break;
	
			case "testSingleInstanceXslt" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_DATA), 
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON));
				break;
	
			case "testSingleInstanceBulkXslt" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_BULK_DATA), 
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_BULK_DATA, CONTENT_TYPE_APPLICATION_JSON));
				break;

			default :
				break;
		}
	}
	
	@AssertAfter(id="BuildRequestFromXmlAndXsd_120")
	public void verifyXsdTransformation() throws Exception {
		mockTracker.incrementCallCount("BuildRequestFromXmlAndXsd_120");

		switch (getName()) {
			case "testMultiInstanceXsd" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA), 
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON));
				break;
	
			case "testMultiInstanceBulkXsd" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_BULK_DATA), 
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_SSK120_JSON_MULTI_INSTANCE_SOURCE_BULK_DATA, CONTENT_TYPE_APPLICATION_JSON));
				break;
	
			case "testSingleInstanceXsd" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_DATA), 
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_DATA, CONTENT_TYPE_APPLICATION_JSON));
				break;
	
			case "testSingleInstanceBulkXsd" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_BULK_DATA), 
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_SSK120_JSON_SINGLE_INSTANCE_SOURCE_BULK_DATA, CONTENT_TYPE_APPLICATION_JSON));
				break;
	
			default :
				break;
		}
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	@Override
	protected Throwable loadWriteCustomObjectResponse() throws Exception {
		switch (getName()) {
			case "" :
				return null;

			case "tbd" :
				setMessagePart(0, MOCK_SSK120_JSON_RESPONSE_ERROR_SUMMARY, CONTENT_TYPE_APPLICATION_JSON);
				return new Exception();

			default :
				setMessagePart(0, MOCK_SSK120_JSON_RESPONSE_SUCCESS_BULK, CONTENT_TYPE_APPLICATION_JSON);
				return null;
		}
	}
	
}
