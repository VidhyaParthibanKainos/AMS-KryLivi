package com.workday.custom.int006.ssk142;

import org.apache.commons.lang3.StringUtils;

import com.capeclear.mediation.MediationContext;
import com.workday.custom.int006.MediationConstants;

public class GeneralCloudLogHelper {

	public static void updateLogCounts(MediationContext mc, String logTarget) throws Throwable {
		StringBuilder sb = new StringBuilder();

		sb.append("ssk").
			append(
					StringUtils.capitalize(logTarget.toLowerCase())).
			append("LogCount").
			append(
					StringUtils.capitalize(
							((String)mc.getProperty(MediationConstants.PARAMETER_IN_SSK_CLOUD_LOG_LEVEL)).toLowerCase()));

		incrementProperty(mc, sb.toString());
		
		if ("secondary".equalsIgnoreCase(logTarget)) {
			incrementProperty(mc, MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_TOTAL);
			incrementProperty(mc, MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_BY_FILE);
		} else {
			incrementProperty(mc, MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_TOTAL);
			incrementProperty(mc, MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_BY_FILE);
		}
	}
	
	private static void incrementProperty(MediationContext mc, String propertyName) throws Throwable {
		mc.setProperty(propertyName, ((Integer)mc.getProperty(propertyName) + 1));
	}
	
	public static boolean isLogReadyForOutput(MediationContext mc, String logTarget) throws Throwable {
		boolean returnValue = false;
		
		if ("secondary".equalsIgnoreCase(logTarget)) {
			returnValue = mc.getVariables().isVariable(MediationConstants.VARIABLE_SSK_CLOUD_LOG_SECONDARY) && 
					mc.getVariables().getVariable(MediationConstants.VARIABLE_SSK_CLOUD_LOG_SECONDARY) != null &&
					((Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_BY_FILE) > 0) &&
					((Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_TOTAL) > 0) &&
					((Boolean)mc.getProperty(MediationConstants.PARAMETER_IN_SSK_CLOUD_LOG_FINALIZE) ||
							(((Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_MAX_SIZE) > 0) &&
									((Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_BY_FILE) == (Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_SECONDARY_LOG_MAX_SIZE))));
		} else {
			returnValue = mc.getVariables().isVariable(MediationConstants.VARIABLE_SSK_CLOUD_LOG_PRIMARY) && 
					mc.getVariables().getVariable(MediationConstants.VARIABLE_SSK_CLOUD_LOG_PRIMARY) != null &&
					((Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_BY_FILE) > 0) &&
					((Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_TOTAL) > 0) &&
					((Boolean)mc.getProperty(MediationConstants.PARAMETER_IN_SSK_CLOUD_LOG_FINALIZE) ||
							(((Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_MAX_SIZE) > 0) &&
									((Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_BY_FILE) == (Integer)mc.getProperty(MediationConstants.PROPERTY_SSK_PRIMARY_LOG_MAX_SIZE))));
		}

		return returnValue;
	}
}
