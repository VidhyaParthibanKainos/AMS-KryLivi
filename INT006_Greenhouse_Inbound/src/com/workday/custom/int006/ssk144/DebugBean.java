package com.workday.custom.int006.ssk144;

import static com.capeclear.assembly.annotation.Component.Type.mediation;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.capeclear.assembly.annotation.Component;
import com.capeclear.assembly.annotation.ComponentMethod;
import com.capeclear.mediation.MediationContext;
import com.workday.custom.int006.MediationConstants;
import com.workday.custom.int006.SSKUtils;


/**
 * Custom mediation
 *
 * TODO Modify the Component annotation. Also add Property annotations to any
 * bean pattern methods you add and want to appear in the Assembly Editor.
 */
@Component(
        name = "DebugBean",
        type = mediation,
        toolTip = "",
        scope = "prototype",
        smallIconPath = "icons/DebugBean_16.png",
        largeIconPath = "icons/DebugBean_24.png"
        )
public class DebugBean {

	private int numberOfDebugKBs;
	
    public DebugBean() {
		super();

		numberOfDebugKBs = MediationConstants.DEBUG_FRAGMENT_SIZE_KB;
	}

	/**
     * This method is called by the Assembly framework.    
     */
    @ComponentMethod
    public void process(MediationContext mc) {
    	try {
    		String dataLocation = ("message".equalsIgnoreCase((String)mc.getProperty(MediationConstants.PROP_PARAMETER_IN_TARGET_TYPE))) ?
    				"message" :
    					(String)mc.getProperty(MediationConstants.PROP_PARAMETER_IN_TARGET_NAME);
	    	
	    	mc.setProperty(MediationConstants.PROPERTY_SSK_DEBUG_MESSAGE_FRAGMENT, getDataFragment(mc, dataLocation, numberOfDebugKBs));
    	} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
    }

    public static String getDataFragmentOfDefaultSize(MediationContext mc, String dataLocation) throws Exception {
    	return getDataFragment(mc, dataLocation, MediationConstants.DEBUG_FRAGMENT_SIZE_KB);
    }
    
    private static String getDataFragment(MediationContext mc, String dataLocation, int kbIterations) throws Exception {
    	try {
	    	InputStream in;
	    	
	    	if ("message".equalsIgnoreCase(dataLocation)) {
	    		in = SSKUtils.getMessageInputStream(mc);
	    	} else {
	    		in = SSKUtils.getVariableInputStream(mc, dataLocation);
	    	}
	    	
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	byte[] buffer = new byte[1024];
	
	    	int counter = 0;
	    	int length;
			while ((counter++ < kbIterations) && (length = in.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}

			if (baos.size() == (1024 * kbIterations)) {
				baos.write("...".getBytes());
			}
			
	    	return baos.toString("UTF-8");
    	} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
    }
    
    public int getNumberOfDebugKBs() {
		return numberOfDebugKBs;
	}

	public void setNumberOfDebugKBs(int numberOfDebugKBs) {
		if (numberOfDebugKBs < 1) {
			throw new RuntimeException("The numberOfDebugKBs property of SSK144DebugBean accepts only positive integers greater than or equal to 1.");
		}
		this.numberOfDebugKBs = numberOfDebugKBs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getIterableList(MediationContext mc, String collectionPropertyName) throws Exception {
    	List<Object> returnValue = new ArrayList<Object>();

    	try {
    		Object collection = mc.getProperty(collectionPropertyName);
    		
    		if (collection != null && (collection instanceof Map || collection instanceof Set)) {
    			if (collection instanceof Map) {
    				collection = ((Map)collection).keySet();
    			}
    			
	    		Consumer<Object> addToList = new Consumer<Object>() {
					@Override
					public void accept(Object t) {
						returnValue.add(t);
					}
				};
				((Set)collection).forEach(addToList);
    		}
    	} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
    	
    	return returnValue;
    }
    
    @SuppressWarnings("rawtypes")
    public static String getPropertiesTargetKey(List l, int index) throws Exception {
    	String returnValue = "";

    	try {
    		if (CollectionUtils.isNotEmpty(l)) {
    			returnValue = String.valueOf(l.get(index));
    		}
    	} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
    	
    	return returnValue;
    }

    @SuppressWarnings("rawtypes")
    public static String getPropertiesTargetKeyValue(MediationContext mc, List l, int index) throws Exception {
    	String returnValue = "";

    	try {
    		if (CollectionUtils.isNotEmpty(l)) {
    			String key = String.valueOf(l.get(index));
    			
    			Object item = mc.getProperty(key);
    			if (item != null) {
    				returnValue = String.valueOf(item);
    			}
    		}
    	} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
    	
    	return returnValue;
    }

    public static String getPropertyTargetValue(MediationContext mc, String key) throws Exception {
    	String returnValue = "";

    	try {
    		if (StringUtils.isNotEmpty(key)) {
    			Object item = mc.getProperty(key);
    			if (item != null) {
    				returnValue = String.valueOf(item);
    			}
    		}
    	} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
    	
    	return returnValue;
    }

    @SuppressWarnings("rawtypes")
    public static String getMapTargetValue(MediationContext mc, Map mapInstance, String mapKey) throws Exception {
    	String returnValue = "";

    	try {
			Object value = mapInstance.get(mapKey);
			if (value != null) {
				returnValue = String.valueOf(value);
			}
    	} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
    	
    	return returnValue;
    }
    
    public static String getItemValue(Object item) throws Exception {
    	String returnValue = "";

    	try {
    		if (item != null) {
    			returnValue = String.valueOf(item);
    		}
    	} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
    	
    	return returnValue;
    }
}
