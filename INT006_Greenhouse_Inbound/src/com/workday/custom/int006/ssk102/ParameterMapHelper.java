package com.workday.custom.int006.ssk102;

import java.net.URLEncoder;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.impl.mediators.MVELUtilHelper;

public class ParameterMapHelper {
	
	private static Map<Character, String> URL_ENCODING_MAP;

	/*
	 * NOTE:
	 * 
	 * General Workday Studio development guidance is to avoid use of static instances in Java.  The reason for which is
	 * that the integration server may not be torn down after the event completes, and in a data center optimization, may
	 * instead be recycled to serve the next event sooner.  If data is stored in static references, then this can result
	 * in two consequences: reduced memory availability to the second integration, and potential leakage of data between
	 * events.
	 * 
	 * The static reference below is used because:
	 * a) It is a small memory footprint, and as the use of SSK becomes broader, the existing instance will save the
	 * second integration the overhead of reinstantiating it, and...
	 * b) It contains no customer data and has no opportunity to leak information across events. 
	 */

	static {
		URL_ENCODING_MAP = new HashMap<Character,String>();
		
		URL_ENCODING_MAP.put(Character.valueOf('`'), "%60");
		URL_ENCODING_MAP.put(Character.valueOf('!'), "%21");
		URL_ENCODING_MAP.put(Character.valueOf('#'), "%23");
		URL_ENCODING_MAP.put(Character.valueOf('%'), "%25");
		URL_ENCODING_MAP.put(Character.valueOf('^'), "%5E");
		URL_ENCODING_MAP.put(Character.valueOf('&'), "%26");
		URL_ENCODING_MAP.put(Character.valueOf('+'), "%2B");
		URL_ENCODING_MAP.put(Character.valueOf('\\'), "%5C");
		URL_ENCODING_MAP.put(Character.valueOf('{'), "%7B");
		URL_ENCODING_MAP.put(Character.valueOf('}'), "%7D");
		URL_ENCODING_MAP.put(Character.valueOf('|'), "%7C");
		URL_ENCODING_MAP.put(Character.valueOf(';'), "%3B");
		URL_ENCODING_MAP.put(Character.valueOf('\''), "%27");
		URL_ENCODING_MAP.put(Character.valueOf(' '), "%20");
	}
	
	private ParameterMapHelper() {
		super();
	}

	public static void initializeParameterMap(MediationContext mediationContext, String propertyNameOfMap) throws Exception {
		Map<String, Object> theMap = new HashMap<String, Object>();
		mediationContext.setProperty(propertyNameOfMap, theMap);
	}
	
	@SuppressWarnings("unchecked")
	public static void addValue(MediationContext mediationContext, String propertyNameOfMap, String k, Object v) throws Exception {
		Map<String, Object> theMap = (Map<String, Object>)mediationContext.getProperty(propertyNameOfMap);

		if (theMap.containsKey(k)) {
			Object incumbentValue = theMap.get(k); 

			if (incumbentValue instanceof List<?>) {
				((List<Object>) incumbentValue).add(v);
			} else {
				List<Object> newList = new ArrayList<Object>();
				newList.add(incumbentValue);
				newList.add(v);
				theMap.put(k, newList);
			}
		} else {
			theMap.put(k, v);
		}
	}
	
	public static String urlEscapeValue(String v, boolean isUseJavaUrlEncoder) throws Exception {
		if (isUseJavaUrlEncoder) {
			return URLEncoder.encode(v, "UTF-8");
		} else {
			char[] charArray = v.toCharArray();
			StringBuilder sb = new StringBuilder();
			
			for (int x = 0; x < charArray.length; x++) {
				if (URL_ENCODING_MAP.containsKey(charArray[x])) {
					sb.append(URL_ENCODING_MAP.get(charArray[x]));
				} else {
					sb.append(charArray[x]);
				}
			}
			
			return sb.toString();
		}
	}
	
	public static String xmlEscapeValue(MVELUtilHelper util, String v) throws Exception {
		
		return util.cleanString(v);
	}

	public static String transformCollectionParameterToQueryString(AbstractCollection<Object> theMapValue, boolean isUseJavaUrlEncoder) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		for (Object item : theMapValue) {
			if (sb.length() > 0) {
				sb.append("!");
			}
			sb.append(urlEscapeValue(String.valueOf(item), isUseJavaUrlEncoder));
		}
		
		return sb.toString(); 
	}
}
