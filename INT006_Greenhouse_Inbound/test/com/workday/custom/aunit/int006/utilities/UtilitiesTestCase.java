package com.workday.custom.aunit.int006.utilities;

import java.util.ArrayList;
import java.util.List;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AtComponent;
import com.workday.custom.aunit.int006.CommonTestCase;

public abstract class UtilitiesTestCase extends CommonTestCase {

	public static final String PROP_PARAMETER_IN_API_VERSION = "inApiVersion";
	public static final String PROP_PARAMETER_IN_RETURN_RESULTS = "inReturnResults";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_VALIDATE_ONLY = "inValidateOnly";
	public static final String PROP_PARAMETER_IN_ABORT_ON_ERROR = "inIsAbortOnError";
	public static final String PROP_PARAMETER_IN_LOG_TARGET = "inLogTarget";

	private boolean isEventDocumentsInitialized = false;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		List<String> componentsToTrack = new ArrayList<String>();
		componentsToTrack.add("CallSoap_103");
		componentsToTrack.add("CallSoapParallel_104");
		componentsToTrack.add("CallSoapSerial_104");
		componentsToTrack.add("PIM_Tag_106");
		componentsToTrack.add("GetEventDocuments_110");
		componentsToTrack.add("CallSoap_113");
		componentsToTrack.add("GetEventDocuments_114");
		componentsToTrack.add("GetEventDocuments_115");
		componentsToTrack.add("GetEventDocuments_118");
		componentsToTrack.add("CallCustomObjectAPI_120");
		componentsToTrack.add("CallQueue_122");
		componentsToTrack.add("CreateQueue_122");
		componentsToTrack.add("CallQueue_123");
		componentsToTrack.add("CallQueue_124");
		componentsToTrack.add("CallQueueRead_125");
		componentsToTrack.add("CallQueueDelete_125");
		componentsToTrack.add("CallQueue_126");
		componentsToTrack.add("CallQueueDelete_127");
		componentsToTrack.add("CallQueue_128");
		componentsToTrack.add("CallQueue_Read_128");
		componentsToTrack.add("CallQueue_Delete_128");

		mockTracker.addComponentTracking(componentsToTrack);
		
		setupGlobalInitialization();
		setupDebugValidations();
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	protected void initializeFileBlobitory() throws Exception {  }
	
	protected void initializeEventDocuments() throws Exception {  }

	@AtComponent(id="CallSoap_103")
	public Action handleCallSoap() throws Exception {
		Throwable mockError = loadSoapResponse();
		
		mockTracker.incrementCallCount("CallSoap_103");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadSoapResponse() throws Exception {
		return null;
	}
	
	@AtComponent(id="CallSoapParallel_104")
	public Action handleCallSoapPagedParallel104() throws Exception {
		Throwable mockError = loadSoapPagedResponse();
		
		mockTracker.incrementCallCount("CallSoap_104");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}
	
	@AtComponent(id="CallSoapSerial_104")
	public Action handleCallSoapPagedSerial104() throws Exception {
		Throwable mockError = loadSoapPagedResponse();
		
		mockTracker.incrementCallCount("CallSoap_104");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadSoapPagedResponse() throws Exception {
		return null;
	}
	
	protected void loadCSPAggregateFinalVariable() throws Exception {  }

	@AtComponent(id="PIM_Tag_106")
	public Action mockGenerateOutputTagDeliverable() throws Exception {
		mockTracker.incrementCallCount("PIM_Tag_106");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="GetEventDocuments_110")
	public Action mockGetEventDocuments110() throws Exception {
		if (!isEventDocumentsInitialized) {
			initializeFileBlobitory();
		}
		initializeEventDocuments();
		
		isEventDocumentsInitialized = true;
		mockTracker.incrementCallCount("GetEventDocuments_110");
		return new StandardAction(Action.Type.mock);
	}

	@AtComponent(id="CallSoap_113")
	public Action handleCallSoapImport() throws Exception {
		Throwable mockError = loadSoapResponse();
		
		mockTracker.incrementCallCount("CallSoap_113");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}
	
	@AtComponent(id="GetEventDocuments_114")
	public Action mockGetEventDocuments114() throws Exception {
		if (!isEventDocumentsInitialized) {
			initializeFileBlobitory();
		}
		initializeEventDocuments();
		
		isEventDocumentsInitialized = true;
		mockTracker.incrementCallCount("GetEventDocuments_114");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="GetEventDocuments_115")
	public Action mockGetEventDocuments115() throws Exception {
		if (!isEventDocumentsInitialized) {
			initializeFileBlobitory();
		}
		initializeEventDocuments();
		
		isEventDocumentsInitialized = true;
		mockTracker.incrementCallCount("GetEventDocuments_115");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="GetEventDocuments_118")
	public Action mockGetEventDocuments118() throws Exception {
		if (!isEventDocumentsInitialized) {
			initializeFileBlobitory();
		}
		initializeEventDocuments();
		
		isEventDocumentsInitialized = true;
		mockTracker.incrementCallCount("GetEventDocuments_118");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="CallSoapPagedGet_118")
	public Action handleCallSoapPaged118() throws Exception {
		Throwable mockError = loadSoapPagedResponse();
		
		mockTracker.incrementCallCount("CallSoapPagedGet_118");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}
	
	@AtComponent(id="CallCustomObjectAPI_120")
	public Action handleCallCustomObjectAPI120() throws Exception {
		Throwable mockError = loadWriteCustomObjectResponse();
		
		mockTracker.incrementCallCount("CallCustomObjectAPI_120");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadWriteCustomObjectResponse() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueue_122")
	public Action mockCallQueue122() throws Exception {
		Throwable mockError = loadRestCallQueue122();
		
		mockTracker.incrementCallCount("CallQueue_122");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueue122() throws Exception {
		return null;
	}

	@AtComponent(id="CreateQueue_122")
	public Action mockCreateQueue122() throws Exception {
		Throwable mockError = loadRestCreateQueue122();
		
		mockTracker.incrementCallCount("CreateQueue_122");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCreateQueue122() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueue_123")
	public Action mockCallQueue123() throws Exception {
		Throwable mockError = loadRestCallQueue123();
		
		mockTracker.incrementCallCount("CallQueue_123");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueue123() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueue_124")
	public Action mockCallQueue124() throws Exception {
		Throwable mockError = loadRestCallQueue124();
		
		mockTracker.incrementCallCount("CallQueue_124");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueue124() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueueRead_125")
	public Action mockCallQueueRead125() throws Exception {
		Throwable mockError = loadRestCallQueueRead125();
		
		mockTracker.incrementCallCount("CallQueueRead_125");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueueRead125() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueueDelete_125")
	public Action mockCallQueueDelete125() throws Exception {
		Throwable mockError = loadRestCallQueueDelete125();
		
		mockTracker.incrementCallCount("CallQueueDelete_125");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueueDelete125() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueue_126")
	public Action mockCallQueue126() throws Exception {
		Throwable mockError = loadRestCallQueue126();
		
		mockTracker.incrementCallCount("CallQueue_126");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueue126() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueueDelete_127")
	public Action mockCallQueueDelete127() throws Exception {
		Throwable mockError = loadRestCallQueueDelete127();
		
		mockTracker.incrementCallCount("CallQueueDelete_127");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueueDelete127() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueue_128")
	public Action mockCallQueue128() throws Exception {
		Throwable mockError = loadRestCallQueue128();
		
		mockTracker.incrementCallCount("CallQueue_128");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueue128() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueue_Read_128")
	public Action mockCallQueue_Read128() throws Exception {
		Throwable mockError = loadRestCallQueueRead128();
		
		mockTracker.incrementCallCount("CallQueue_Read_128");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueueRead128() throws Exception {
		return null;
	}

	@AtComponent(id="CallQueue_Delete_128")
	public Action mockCallQueue_Delete128() throws Exception {
		Throwable mockError = loadRestCallQueueDelete128();
		
		mockTracker.incrementCallCount("CallQueue_Delete_128");
		if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadRestCallQueueDelete128() throws Exception {
		return null;
	}
}
