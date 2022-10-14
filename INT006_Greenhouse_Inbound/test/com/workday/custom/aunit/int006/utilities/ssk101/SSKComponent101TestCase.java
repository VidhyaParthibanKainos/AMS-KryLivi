package com.workday.custom.aunit.int006.utilities.ssk101;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int006.utilities.UtilitiesTestCase;
import com.workday.custom.int006.ssk102.ParameterMapHelper;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class SSKComponent101TestCase extends UtilitiesTestCase {
	public static final String VAR_GLOBAL_DATA = "globalData";

	public static final String PROP_PARAMETER_IN_REPORT_PROMPT_NAME = "inReportPromptName";
	public static final String PROP_PARAMETER_IN_REFERENCE_ID_TYPE = "inReferenceIdType";
	public static final String PROP_PARAMETER_IN_VALUE = "inValue";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inParameterDataLocation";	
	public static final String PROP_PARAMETER_IN_PROMPT_MAP = "inPropertyNameReportPromptMap";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_QUERY_VALUE = "inQueryToValue";
	public static final String PROP_PARAMETER_IN_ENTRY_FILTER = "inEntryFilter";

	public static final String PROP_LOCAL_PROMPT_MAP = "localMapReference";
	public static final String PROP_LOCAL_IS_PROMPTS_PROVIDED = "localIsPromptsProvided";
	public static final String PROP_LOCAL_IS_PROMPTS_MAP = "localIsPromptsMap";
	public static final String PROP_LOCAL_IS_PROMPTS_VALID = "localIsPromptsValid";
	public static final String PROP_LOCAL_KEY = "localKey";
	public static final String PROP_LOCAL_VALUE = "localValue";

	public static final String VALUE_GETWORKERS_WORKER_REFERENCE_XPATH = "/env:Envelope/env:Body/wd:Get_Workers_Response/wd:Response_Data/wd:Worker/wd:Worker_Reference/wd:ID";
	public static final String VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER = "[@wd:type='WID']";
	public static final String VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER_TO_RECORD = "[@wd:type='WID'][./text() = '" + VALUE_WID_1 + "']";
//	public static final String VALUE_GETWORKERS_WORKER_REFERENCE_XPATH = "/env:Envelope/env:Body/wd:Get_Workers_Response/wd:Response_Data/wd:Worker/wd:Worker_Reference/wd:ID[@wd:type='WID']";
//	public static final String VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER = ". = '" + VALUE_WID_1 + "'";
	public static final String VALUE_REPORT_PROMPT_LABEL = "Label";
	public static final String VALUE_REPORT_PROMPT_TYPE = "Label_Reference_ID";	
	
	public static final String MOCK_RESPONSE_SSK101_SOAP_GETWORKERS = "test/ssk/ssk101/SSK101_SOAP_GetWorkers_Response.xml";
	public static final String MOCK_RESPONSE_SSK101_SOAP_GETWORKERS_NO_RESULTS = "test/ssk/ssk101/SSK101_SOAP_GetWorkers_Response_NoResults.xml";
	public static final String MOCK_RESPONSE_SSK101_SOAP_GETWORKERS_ONE_RESULT = "test/ssk/ssk101/SSK101_SOAP_GetWorkers_Response_OneResult.xml";
	public static final String MOCK_MESSAGE_SSK101_NON_XML = "test/ssk/ssk101/SSK101_NonXml.txt";

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setupGlobalInitialization();
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, true);
		
		ParameterMapHelper.initializeParameterMap(this.ctx, PROP_LOCAL_PROMPT_MAP);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyPrimitiveValueDouble() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, VALUE_PRIMITIVE_DOUBLE);
	}
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyPrimitiveValueInteger() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, VALUE_PRIMITIVE_INT);
	}
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyObjectValueEmptyString() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, VALUE_EMPTY_STRING);
	}
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyObjectValueInteger() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, VALUE_OBJECT_INTEGER);
	}
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyObjectValueDouble() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, VALUE_OBJECT_DOUBLE);
	}
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyObjectValueString() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, VALUE_WID_1);
	}
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyObjectValueList() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);

		List<String> theCollection= new ArrayList<String>();
		theCollection.add(VALUE_WID_1);
		theCollection.add(VALUE_WID_2);
		theCollection.add(VALUE_WID_3);
		
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, theCollection);
	}	
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyObjectValueMap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);

		Map<String, String> theCollection = new HashMap<String, String>();
		theCollection.put(VALUE_WID_1, VALUE_WID_10);
		theCollection.put(VALUE_WID_2, VALUE_WID_11);
		theCollection.put(VALUE_WID_3, VALUE_WID_12);
		
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, theCollection);
	}	
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyObjectValueSet() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);

		Set<String> theCollection = new HashSet<String>();
		theCollection.add(VALUE_WID_1);
		theCollection.add(VALUE_WID_2);
		theCollection.add(VALUE_WID_3);
		
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, theCollection);
	}
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyDuplicateKeyError() throws Exception {
		expectHandledException();
		
		@SuppressWarnings("unchecked")
		Map<String, Object> theMap = (Map<String, Object>)ctx.getProperty(PROP_LOCAL_PROMPT_MAP);
		theMap.put(VALUE_REPORT_PROMPT_LABEL, VALUE_PRIMITIVE_DOUBLE);
		
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, VALUE_OBJECT_DOUBLE);
	}
	
	@UnitTest(startComponent="AddReportPromptFromProperty")
	public void testPropertyNotMapError() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, new HashSet<String>());
		
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);
		ctx.setProperty(PROP_PARAMETER_IN_VALUE, VALUE_PRIMITIVE_DOUBLE);
	}
	
	@UnitTest(startComponent="AddReportPromptFromXml")
	public void testXmlNotMapError() throws Exception {
		expectHandledException();
		
		setMessagePart(0, MOCK_RESPONSE_SSK101_SOAP_GETWORKERS_ONE_RESULT, CONTENT_TYPE_TEXT_XML);
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, new HashSet<String>());
		
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_VALUE, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER);
	}
	
	@UnitTest(startComponent="AddReportPromptFromXml")
	public void testXmlDuplicateKeyError() throws Exception {
		expectHandledException();
		
		setMessagePart(0, MOCK_RESPONSE_SSK101_SOAP_GETWORKERS_ONE_RESULT, CONTENT_TYPE_TEXT_XML);

		@SuppressWarnings("unchecked")
		Map<String, Object> theMap = (Map<String, Object>)ctx.getProperty(PROP_LOCAL_PROMPT_MAP);
		theMap.put(VALUE_REPORT_PROMPT_LABEL, VALUE_PRIMITIVE_DOUBLE);

		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_VALUE, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER);
	}
	
	@UnitTest(startComponent="AddReportPromptFromXml")
	public void testXmlEmptyMessageError() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_VALUE, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER);
	}
	
	@UnitTest(startComponent="AddReportPromptFromXml")
	public void testXmlNoSuchVariableError() throws Exception {
		expectHandledException();
		
		setMessagePart(0, MOCK_RESPONSE_SSK101_SOAP_GETWORKERS_ONE_RESULT, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_GLOBAL_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_VALUE, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER);
	}
	
	@UnitTest(startComponent="AddReportPromptFromXml")
	public void testXmlVariableNotXmlError() throws Exception {
		expectHandledException();
		
		setVariable(VAR_GLOBAL_DATA, MOCK_MESSAGE_SSK101_NON_XML, CONTENT_TYPE_TEXT_PLAIN);

		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_GLOBAL_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_VALUE, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER);
	}
	
	@UnitTest(startComponent="AddReportPromptFromXml")
	public void testXmlNoMatchValue() throws Exception {
		setMessagePart(0, MOCK_RESPONSE_SSK101_SOAP_GETWORKERS_NO_RESULTS, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_VALUE, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER);
	}
	
	@UnitTest(startComponent="AddReportPromptFromXml")
	public void testXmlOneMatchValue() throws Exception {
		setMessagePart(0, MOCK_RESPONSE_SSK101_SOAP_GETWORKERS_ONE_RESULT, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_VALUE, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER);
	}
	
	@UnitTest(startComponent="AddReportPromptFromXml")
	public void testXmlMultipleMatchValue() throws Exception {
		setMessagePart(0, MOCK_RESPONSE_SSK101_SOAP_GETWORKERS, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_VALUE, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER);
	}
	
	@UnitTest(startComponent="AddReportPromptFromXml")
	public void testXmlFilterMatchValue() throws Exception {
		setMessagePart(0, MOCK_RESPONSE_SSK101_SOAP_GETWORKERS, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_PROMPT_NAME, VALUE_REPORT_PROMPT_LABEL);
		ctx.setProperty(PROP_PARAMETER_IN_REFERENCE_ID_TYPE, VALUE_REPORT_PROMPT_TYPE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_VALUE, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH);
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, VALUE_GETWORKERS_WORKER_REFERENCE_XPATH_FILTER_TO_RECORD);
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
		Iterator<?> propertyNameIterator = ctx.getPropertyNames();

		while (propertyNameIterator.hasNext()) {
			String propertyName = String.valueOf(propertyNameIterator.next());
			
			assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_IS_PROMPTS_MAP), PROP_LOCAL_IS_PROMPTS_MAP.equalsIgnoreCase(propertyName));
			assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_KEY), PROP_LOCAL_IS_PROMPTS_PROVIDED.equalsIgnoreCase(propertyName));
			assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_VALUE), PROP_LOCAL_IS_PROMPTS_VALID.equalsIgnoreCase(propertyName));

			verifyGlobalPropertyNotModified(propertyName, null);
		}
	}
	
	@Override
	protected void handledExceptionVerification(Throwable t) throws Exception {
		if (!testsExpectingHandledExceptions.contains(getName())) {
			super.handledExceptionVerification(t);
		} else if ("testPropertyObjectValueEmptyString".equalsIgnoreCase(getName())) {
        	assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, "Exception Message"), StringUtils.contains(t.getMessage(), ""));
        } else if ("testPropertyDuplicateKeyError".equalsIgnoreCase(getName())) {
        	assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, "Exception Message"), StringUtils.contains(t.getMessage(), "10102"));
        } else if ("testPropertyNotMapError".equalsIgnoreCase(getName())) {
        	assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, "Exception Message"), StringUtils.contains(t.getMessage(), "10100"));
        } else if ("testXmlNotMapError".equalsIgnoreCase(getName())) {
        	assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, "Exception Message"), StringUtils.contains(t.getMessage(), "10101"));
        } else if ("testXmlDuplicateKeyError".equalsIgnoreCase(getName())) {
        	assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, "Exception Message"), StringUtils.contains(t.getMessage(), "10104"));
        } else if ("testXmlEmptyMessageError".equalsIgnoreCase(getName())) {
        	assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, "Exception Message"), StringUtils.contains(t.getMessage(), "10105"));
        } else if ("testXmlVariableNotXmlError".equalsIgnoreCase(getName())) {
        	assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, "Exception Message"), StringUtils.contains(t.getMessage(), "10105"));
		}
	}
	
	@Override
	protected void unhandledExceptionVerification(Throwable t) throws Exception {
		if ("testXmlNoSuchVariableError".equalsIgnoreCase(getName())) {
        	assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, "Exception Message"), StringUtils.contains(t.getMessage(), "declares property name=inParameterDataLocation, which has failed validation using expression = 'props['inParameterDataLocation'] == 'message' || vars.isVariable(props.inParameterDataLocation)'"));
        } else {
        	super.unhandledExceptionVerification(t);
        }
	}

	@AssertAfter(id="UpdateMap_Property_101", step="UpdateValues")
	public void verifyPropertyMap() throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> theMap = (Map<String, Object>)ctx.getProperty(PROP_LOCAL_PROMPT_MAP);

		if ("testPropertyPrimitiveValueDouble".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL), VALUE_PRIMITIVE_DOUBLE, theMap.get(VALUE_REPORT_PROMPT_LABEL));
		} else if ("testPropertyPrimitiveValueInteger".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL), VALUE_PRIMITIVE_INT, theMap.get(VALUE_REPORT_PROMPT_LABEL));
		} else if ("testPropertyObjectValueEmptyString".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL), VALUE_EMPTY_STRING, theMap.get(VALUE_REPORT_PROMPT_LABEL));
		} else if ("testPropertyObjectValueInteger".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL), VALUE_OBJECT_INTEGER, theMap.get(VALUE_REPORT_PROMPT_LABEL));
		} else if ("testPropertyObjectValueDouble".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL), VALUE_OBJECT_DOUBLE, theMap.get(VALUE_REPORT_PROMPT_LABEL));
		} else if ("testPropertyObjectValueString".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL), VALUE_WID_1, theMap.get(VALUE_REPORT_PROMPT_LABEL));
		} else if ("testPropertyObjectValueList".equalsIgnoreCase(getName())) {
			Object theValue = theMap.get(VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE);
			
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PROMPT_MAP), theValue instanceof List<?>);
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), ((List<?>)theValue).contains(VALUE_WID_1));
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), ((List<?>)theValue).contains(VALUE_WID_2));
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), ((List<?>)theValue).contains(VALUE_WID_3));
		} else if ("testPropertyObjectValueMap".equalsIgnoreCase(getName())) {
			Object theValue = theMap.get(VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE);
			
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PROMPT_MAP), theValue instanceof Map<?,?>);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + ".keySet().size()"), 3, ((Map<?,?>)theValue).keySet().size());
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), VALUE_WID_10,  ((Map<?,?>)theValue).get(VALUE_WID_1));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), VALUE_WID_11,  ((Map<?,?>)theValue).get(VALUE_WID_2));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), VALUE_WID_12,  ((Map<?,?>)theValue).get(VALUE_WID_3));
		} else if ("testPropertyObjectValueSet".equalsIgnoreCase(getName())) {
			Object theValue = theMap.get(VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE);
			
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PROMPT_MAP), theValue instanceof Set<?>);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + ".size()"), 3, ((Set<?>)theValue).size());
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), ((Set<?>)theValue).contains(VALUE_WID_1));
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), ((Set<?>)theValue).contains(VALUE_WID_2));
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), ((Set<?>)theValue).contains(VALUE_WID_3));
		} 
	}

	@AssertAfter(id="UpdateMap_Xml_101", step="UpdateValues")
	public void verifyXmlMap() throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> theMap = (Map<String, Object>)ctx.getProperty(PROP_LOCAL_PROMPT_MAP);
		Object theValue = theMap.get(VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PROMPT_MAP), theValue instanceof List<?>);

		if ("testXmlNoMatchValue".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), 0, ((List<?>)theValue).size());
		} else if ("testXmlOneMatchValue".equalsIgnoreCase(getName()) ||
				"testXmlFilterMatchValue".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), 1, ((List<?>)theValue).size());
		    
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), VALUE_WID_1, ((List<?>)theValue).get(0));
		} else if ("testXmlMultipleMatchValue".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), 3, ((List<?>)theValue).size());
		    
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), VALUE_WID_1, ((List<?>)theValue).get(0));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), VALUE_WID_2, ((List<?>)theValue).get(1));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE, PROP_LOCAL_PROMPT_MAP + "/" + VALUE_REPORT_PROMPT_LABEL + "!" + VALUE_REPORT_PROMPT_TYPE), VALUE_WID_3, ((List<?>)theValue).get(2));
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
