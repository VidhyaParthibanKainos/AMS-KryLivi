package com.workday.custom.int006.ssk144;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Class copied/imported as-is from ws_ArchiveMessages sub-assembly.
 *   
 */
public class StringDeduplicator {
	private final ArrayList<String> stringNames;
	
	public StringDeduplicator() {
		stringNames =  new ArrayList<String>();
	}
	
	public void clearStrings() throws Exception {
		stringNames.clear();
	}
	
	public String dedupeString (String strStringName) throws Exception {
		int intOccurrences, intIndexOfDot;
		try {
			StringBuilder sb = new StringBuilder(256);
			strStringName = strStringName.replaceAll(" ", "_");
			stringNames.add(strStringName);
			intOccurrences = Collections.frequency(stringNames, strStringName);
			if (intOccurrences > 1) {
				intIndexOfDot = strStringName.lastIndexOf('.');

				if (intIndexOfDot != -1 ) {
					sb.append(strStringName.substring(0, intIndexOfDot));
					sb.append('_');
					sb.append(String.format("%05d", intOccurrences));
					sb.append(strStringName.substring(intIndexOfDot));
				} else {
					sb.append(strStringName);
					sb.append('_');
					sb.append(String.format("%05d", intOccurrences));
				}
			strStringName = sb.toString();	
			}
		}
		catch (Exception e) {
			throw (new RuntimeException("Error detecting duplicate file", e));	
		}
		return strStringName;
	}
	
	public String dedupeStringDefaultNumber (String strStringName) throws Exception {
		int intOccurrences, intIndexOfDot;

		try {
			strStringName = strStringName.replaceAll(" ", "_");
			StringBuilder sb = new StringBuilder(256);
			stringNames.add(strStringName);
			intOccurrences = Collections.frequency(stringNames, strStringName);
			
			if (intOccurrences > 1) {
				intIndexOfDot = strStringName.lastIndexOf('.');
				if (intIndexOfDot != -1 ) {
					sb.append(strStringName.substring(0, intIndexOfDot));
					sb.append('_');
					sb.append(String.format("%05d", intOccurrences-1));
					sb.append(strStringName.substring(intIndexOfDot));
				} else {
					sb.append(strStringName);
					sb.append('_');
					sb.append(String.format("%05d", intOccurrences));
				}
			} else {
				intIndexOfDot = strStringName.lastIndexOf('.');
				if (intIndexOfDot != -1 ) {
					sb.append(strStringName.substring(0, intIndexOfDot));
					sb.append('_');
					sb.append(String.format("%05d", intOccurrences));
					sb.append(strStringName.substring(intIndexOfDot));
				} else {
					sb.append(strStringName);
					sb.append('_');
					sb.append(String.format("%05d", intOccurrences));
				}
			}
			strStringName = sb.toString();
		}
		catch (Exception e) {
			throw (new RuntimeException("Error detecting duplicate file", e));
		}
		return strStringName;
	}
}
