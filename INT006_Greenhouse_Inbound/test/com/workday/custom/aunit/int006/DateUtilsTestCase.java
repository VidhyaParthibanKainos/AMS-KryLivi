package com.workday.custom.aunit.int006;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.int006.DateUtils;

@AssemblyTest(project="INT006_Greenhouse_Inbound")
public class DateUtilsTestCase extends CommonTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setupPrimaryLogInitialization();
		setupGlobalInitialization();
		setupDebugValidations();
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="Main")
	public void testNow() throws Exception {
		Date expected = null;
		Date actual = null;

		try {
			expected = Calendar.getInstance().getTime();
			actual = DateUtils.now();
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		long delta = Math.abs(actual.getTime() - expected.getTime());
		assertTrue("Too long of a gap ["+ String.valueOf(delta) +"ns] encountered between expected [" + expected.toString() + "] and actual [" + actual.toString() + "] results to be attributable to execution time only.  Tolerance threshold is 100ms.", delta <= 100);
	}
	
	@UnitTest(startComponent="Main")
	public void testCreateDateShortNoTz() throws Exception {
		Date expected = null;
		Date actual = null;

		try {
			expected = new SimpleDateFormat("yyyy-MM-ddXXX").parse("2020-02-29-08:00");
			actual = DateUtils.createDate(2020, 2, 29);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testCreateDateShortWithTz() throws Exception {
		Date expected = null;
		Date actual = null;

		try {
			expected = new SimpleDateFormat("yyyy-MM-ddXXX").parse("2020-02-29-05:00");
			actual = DateUtils.createDate(2020, 2, 29, "EST");
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testCreateDateLongNoTz() throws Exception {
		Date expected = null;
		Date actual = null;

		try {
			expected = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-02-29T01:02:03-08:00");
			actual = DateUtils.createDate(2020, 2, 29, 1, 2, 3, 0);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testCreateDateLongWithTz() throws Exception {
		Date expected = null;
		Date actual = null;

		try {
			expected = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-02-29T01:02:03-05:00");
			actual = DateUtils.createDate(2020, 2, 29, 1, 2, 3, 0, "EST");
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testStringToDate00() throws Exception {
		Class<ParseException> expected = ParseException.class;
		Date actual = null;
		
		String argument = "2020-02-29T01:02:03-08:00";
		boolean isFail = true;

		try {
			actual = DateUtils.convertXmlStringToDate(argument);
		} catch (Throwable t) {
			isFail = false;
			assertTrue("Exception thrown was expected to be an instance of " + expected.getName(), t.getClass().equals(expected));
		}
		if (isFail) {
			fail("Parsing ["+ argument +"] against Formatter should have resulted in a " + expected.getName() + ", not ["+ actual.toString() +"].");
		}
	}
	
	@UnitTest(startComponent="Main")
	public void testStringToDate01() throws Exception {
		Date expected = null;
		Date actual = null;
		
		String argument = "2020-02-29-05:00";

		try {
			expected = new SimpleDateFormat("yyyy-MM-ddXXX").parse("2020-02-29-05:00");
			actual = DateUtils.convertXmlStringToDate(argument);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testStringToDate02() throws Exception {
		Class<ParseException> expected = ParseException.class;
		Date actual = null;
		
		String argument = "2020-02-29";
		boolean isFail = true;

		try {
			actual = DateUtils.convertXmlStringToDate(argument);
		} catch (Throwable t) {
			isFail = false;
			assertTrue("Exception thrown was expected to be an instance of " + expected.getName(), t.getClass().equals(expected));
		}
		if (isFail) {
			fail("Parsing ["+ argument +"] against Formatter should have resulted in a " + expected.getName() + ", not ["+ actual.toString() +"].");
		}
	}
	
	@UnitTest(startComponent="Main")
	public void testDateToString00() throws Exception {
		String expected = null;
		String actual = null;
		
		Date argument = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-02-29T01:02:03-08:00");

		try {
			expected = "2020-02-29-08:00";
			actual = DateUtils.convertDateToXmlString(argument);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testDateToString01() throws Exception {
		String expected = null;
		String actual = null;
		
		Date argument = new SimpleDateFormat("yyyy-MM-ddXXX").parse("2020-02-29-08:00");

		try {
			expected = "2020-02-29-08:00";
			actual = DateUtils.convertDateToXmlString(argument);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testStringToDateTime00() throws Exception {
		Class<ParseException> expected = ParseException.class;
		Date actual = null;
		
		String argument = "2020-02-29-05:00";
		boolean isFail = true;

		try {
			actual = DateUtils.convertXmlStringToDateTime(argument);
		} catch (Throwable t) {
			isFail = false;
			assertTrue("Exception thrown was expected to be an instance of " + expected.getName(), t.getClass().equals(expected));
		}
		if (isFail) {
			fail("Parsing ["+ argument +"] against Formatter should have resulted in a " + expected.getName() + ", not ["+ actual.toString() +"].");
		}
	}
	
	@UnitTest(startComponent="Main")
	public void testStringToDateTime01() throws Exception {
		Date expected = null;
		Date actual = null;
		
		String argument = "2020-02-29T01:02:03.000-05:00";

		try {
			expected = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-02-28T22:02:03-08:00");
			actual = DateUtils.convertXmlStringToDateTime(argument);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testStringToDateTime02() throws Exception {
		Class<ParseException> expected = ParseException.class;
		Date actual = null;
		
		String argument = "2020-02-29";
		boolean isFail = true;

		try {
			actual = DateUtils.convertXmlStringToDateTime(argument);
		} catch (Throwable t) {
			isFail = false;
			assertTrue("Exception thrown was expected to be an instance of " + expected.getName(), t.getClass().equals(expected));
		}
		if (isFail) {
			fail("Parsing ["+ argument +"] against Formatter should have resulted in a " + expected.getName() + ", not ["+ actual.toString() +"].");
		}
	}
	
	@UnitTest(startComponent="Main")
	public void testDateTimeToString00() throws Exception {
		String expected = null;
		String actual = null;
		
		Date argument = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse("2020-02-29T01:02:03.000-08:00");

		try {
			expected = "2020-02-29T01:02:03.000-08:00";
			actual = DateUtils.convertDateTimeToXmlString(argument);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testDateTimeToString01() throws Exception {
		String expected = null;
		String actual = null;
		
		Date argument = new SimpleDateFormat("yyyy-MM-ddXXX").parse("2020-02-29-08:00");

		try {
			expected = "2020-02-29T00:00:00.000-08:00";
			actual = DateUtils.convertDateTimeToXmlString(argument);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testAddDays00() throws Exception {
		Date expected = null;
		Date actual = null;

		try {
			expected = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-03-01T01:02:03-08:00");
			actual = DateUtils.addDaysToDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-02-28T01:02:03-08:00"), 2);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testAddMonths00() throws Exception {
		Date expected = null;
		Date actual = null;

		try {
			expected = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-03-01T01:02:03-08:00");
			actual = DateUtils.addMonthsToDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-02-01T01:02:03-08:00"), 1);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
	}
	
	@UnitTest(startComponent="Main")
	public void testAddSeconds00() throws Exception {
		Date expected = null;
		Date actual = null;

		try {
			expected = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-02-01T01:02:07-08:00");
			actual = DateUtils.addSecondsToDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2020-02-01T01:02:03-08:00"), 4);
		} catch (Throwable t) {
			fail(t.getMessage());
		}
		
		assertEquals("Unexpected Date was returned.", expected, actual);
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
	@AtComponent(id="InitializeAttributesAndLaunchParameters")
	public Action terminate() throws Exception {
		return new StandardAction(Action.Type.terminate);
	}
}
