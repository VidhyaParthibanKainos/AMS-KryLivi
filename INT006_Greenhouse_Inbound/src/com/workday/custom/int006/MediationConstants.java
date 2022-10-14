package com.workday.custom.int006;

public class MediationConstants {
	
	public static final String ASSEMBLY_VERSION = "2020.36";
	public static final String API_VERSION = "v35.0";
	public static final String SSK_VERSION = "Studio StarterKit 2020r2.03";

	// Names of Workday Studio built-in properties
	public static final String STUDIO_PROPERTY_TOKEN_USERNAME = "wss.usernametoken.username";
	public static final String STUDIO_PROPERTY_TOKEN_PASSWORD = "wss.usernametoken.password";
	public static final String STUDIO_PROPERTY_TENANT_ID = "cc.customer.id";
	public static final String STUDIO_PROPERTY_UNIT_TEST = "atf.aunit.test";

	// Names of SSK-provided Integration Services
	public static final String ATTRIBUTE_SERVICE_GENERAL = "int006 Greenhouse Inbound Attribute Map Service - General";
	public static final String ATTRIBUTE_SERVICE_FUNCTIONAL = "int006 Greenhouse Inbound Attribute Map Service - Functional";
	public static final String ATTRIBUTE_SERVICE_MESSAGE_QUEUE = "int006 Greenhouse Inbound Attribute Map Service - Message Queue";
	public static final String ATTRIBUTE_SERVICE_PRIMARY_LOG = "int006 Greenhouse Inbound Attribute Map Service - Primary Logging";
	public static final String ATTRIBUTE_SERVICE_SECONDARY_LOG = "int006 Greenhouse Inbound Attribute Map Service - Secondary Logging";
	
	// Names of SSK-provided Integration Attributes
	public static final String ATTRIBUTE_DOCUMENT_RETENTION = "Document Retention in Days";
	public static final String ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG = "Retrieval Document Tag(s)";
	public static final String ATTRIBUTE_DOCUMENT_RETRIEVAL_TAG_TYPE = "Retrieval Document Tag Match Strategy";
	public static final String ATTRIBUTE_DOCUMENT_OUTPUT_FILENAME = "Output File Name (Include File Extension)";
	public static final String ATTRIBUTE_DOCUMENT_DELIVERY_TAG = "Delivery Document Tag(s)";
	public static final String ATTRIBUTE_LOG_FILENAME = "Log File Name (Exclude File Extension)";
	public static final String ATTRIBUTE_LOG_RETENTION = "Log Retention in Days";
	public static final String ATTRIBUTE_LOG_MAX_SIZE = "Max Entries per Log File";
	public static final String ATTRIBUTE_LOG_FORMAT = "Cloud Log Output File Type";
	public static final String ATTRIBUTE_QUEUE_ENDPOINT = "Message Queue Endpoint";
	public static final String ATTRIBUTE_QUEUE_USERNAME = "Message Queue Endpoint Login Username";
	public static final String ATTRIBUTE_QUEUE_PASSWORD = "Message Queue Endpoint Login Password";
	public static final String ATTRIBUTE_QUEUE_NAME = "Message Queue Name";

	// Names of SSK-provided Launch Parameters
	public static final String LAUNCH_PARAMETER_VALIDATION_MODE = "Run in Validation Mode";
	public static final String LAUNCH_PARAMETER_DEBUG_MODE = "Run with Debug Logging";
	public static final String LAUNCH_PARAMETER_SOURCE_EVENT_WID = "Source Integration Event WID (Build and Debug Only)";

	// SSK Properties that have meta-level influence on framework behavior
	public static final String PROPERTY_SSK_VERSION = "sskVersion";
	public static final String PROPERTY_SSK_DEDUPLICATOR = "sskStringDeduplicator";
	public static final String PROPERTY_SSK_DEBUG_TARGET_TYPE_VALIDATION = "sskDebugTargetTypeValidation";
	public static final String PROPERTY_SSK_DEBUG_TARGET_NAME_VALIDATION = "sskDebugTargetNameValidation";
	public static final String PROPERTY_SSK_STATIC_ANALYSIS_EXEMPTIONS = "sskStaticCodeAnalysisExemptionList";
	public static final String PROPERTY_SSK_DEBUG_LIST = "sskDebugPropertyList";
	public static final String PROPERTY_SSK_DEBUG_ARCHIVE = "sskDebugArchiveFilename";
	public static final String PROPERTY_SSK_XSLT_FUNCTION_LIBRARY_MESSAGES = "sskXsltLibMessage";

	// SSK Properties populated from Launch Parameters
	public static final String PROPERTY_SSK_IS_DEBUG_MODE = "sskIsDebugMode";
	public static final String PROPERTY_SSK_IS_VALIDATION_MODE = "sskIsValidationMode";
	public static final String PROPERTY_SSK_DEBUG_EVENT_WID = "sskDebugWID";
	public static final String PROPERTY_SSK_EVENT_WID = "sskEventWID";
	
	// SSK Properties populated from Integration Attributes
	public static final String PROPERTY_SSK_DOCUMENT_RETENTION_PERIOD = "sskDocumentRetentionPeriod";
	public static final String PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG = "sskRetrievalDocTag";
	public static final String PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG_TYPE = "sskRetrievalDocTagType";
	public static final String PROPERTY_SSK_OUTPUT_FILENAME = "sskOutputFilename";
	public static final String PROPERTY_SSK_DELIVERY_DOCUMENT_TAG = "sskDeliveryDocTag";
	public static final String PROPERTY_SSK_QUEUE_ENDPOINT = "sskQueueEndpoint";
	public static final String PROPERTY_SSK_QUEUE_USERNAME = "sskQueueUsername";
	public static final String PROPERTY_SSK_QUEUE_PASSWORD = "sskQueuePassword";
	public static final String PROPERTY_SSK_QUEUE_NAME = "sskQueueName";
	
	// SSK Properties used globally, but not derived from Attributes or Launch Parameters
	public static final String PROPERTY_GLOBAL_API_VERSION = "globalApiVersion";

	// SSK Properties related to Primary Log
	public static final String PROPERTY_SSK_PRIMARY_LOG_FILENAME = "sskPrimaryLogFilename";
	public static final String PROPERTY_SSK_PRIMARY_LOG_RETENTION = "sskPrimaryLogExpires";
	public static final String PROPERTY_SSK_PRIMARY_LOG_MAX_SIZE = "sskPrimaryLogMaxCountPerFile";
	public static final String PROPERTY_SSK_PRIMARY_LOG_FORMAT = "sskPrimaryLogFileFormat";
	public static final String PROPERTY_SSK_PRIMARY_LOG_COUNT_BY_FILE = "sskPrimaryLogCountByLogFile";
	public static final String PROPERTY_SSK_PRIMARY_LOG_COUNT_DEBUG = "sskPrimaryLogCountDebug";
	public static final String PROPERTY_SSK_PRIMARY_LOG_COUNT_INFO = "sskPrimaryLogCountInfo";
	public static final String PROPERTY_SSK_PRIMARY_LOG_COUNT_WARN = "sskPrimaryLogCountWarn";
	public static final String PROPERTY_SSK_PRIMARY_LOG_COUNT_ERROR = "sskPrimaryLogCountError";
	public static final String PROPERTY_SSK_PRIMARY_LOG_COUNT_FATAL = "sskPrimaryLogCountFatal";
	public static final String PROPERTY_SSK_PRIMARY_LOG_COUNT_TOTAL = "sskPrimaryLogCountTotal";
	public static final String PROPERTY_SSK_PRIMARY_LOG_FILES_STORED = "sskPrimaryLogFilesStored";

	// SSK Variables related to Primary Log
	public static final String VARIABLE_SSK_CLOUD_LOG_PRIMARY = "cloud-log-primary";

	// SSK Properties related to Secondary Log
	public static final String PROPERTY_SSK_SECONDARY_LOG_IS_ENABLED = "sskIsSecondaryLogEnabled";
	
	public static final String PROPERTY_SSK_SECONDARY_LOG_FILENAME = "sskSecondaryLogFilename";
	public static final String PROPERTY_SSK_SECONDARY_LOG_RETENTION = "sskSecondaryLogExpires";
	public static final String PROPERTY_SSK_SECONDARY_LOG_MAX_SIZE = "sskSecondaryLogMaxCountPerFile";
	public static final String PROPERTY_SSK_SECONDARY_LOG_FORMAT = "sskSecondaryLogFileFormat";
	public static final String PROPERTY_SSK_SECONDARY_LOG_COUNT_BY_FILE = "sskSecondaryLogCountByLogFile";
	public static final String PROPERTY_SSK_SECONDARY_LOG_COUNT_DEBUG = "sskSecondaryLogCountDebug";
	public static final String PROPERTY_SSK_SECONDARY_LOG_COUNT_INFO = "sskSecondaryLogCountInfo";
	public static final String PROPERTY_SSK_SECONDARY_LOG_COUNT_WARN = "sskSecondaryLogCountWarn";
	public static final String PROPERTY_SSK_SECONDARY_LOG_COUNT_ERROR = "sskSecondaryLogCountError";
	public static final String PROPERTY_SSK_SECONDARY_LOG_COUNT_FATAL = "sskSecondaryLogCountFatal";
	public static final String PROPERTY_SSK_SECONDARY_LOG_COUNT_TOTAL = "sskSecondaryLogCountTotal";
	public static final String PROPERTY_SSK_SECONDARY_LOG_FILES_STORED = "sskSecondaryLogFilesStored";
	
	// SSK Variables related to Secondary Log
	public static final String VARIABLE_SSK_CLOUD_LOG_SECONDARY = "cloud-log-secondary";

	// Cloud Log format enumeration values
	public static final String PROPERTY_SSK_CLOUD_LOG_FORMAT_HTML = "HTML";
	public static final String PROPERTY_SSK_CLOUD_LOG_FORMAT_CSV = "CSV";
	public static final String PROPERTY_SSK_CLOUD_LOG_FORMAT_XLSX = "XLSX";
	
	// SSK Input Parameters related to General Cloud Logging
	public static final String PARAMETER_IN_SSK_CLOUD_LOG_LEVEL = "inLogLevel";
	public static final String PARAMETER_IN_SSK_CLOUD_LOG_FINALIZE = "inLogFinalize";

	// Document Retrieval Tag Match Strategy enumeration values
	public static final String PROPERTY_SSK_RETRIEVAL_MATCH_ANY = "Any";
	public static final String PROPERTY_SSK_RETRIEVAL_MATCH_ALL = "All";

	// SSK Variables related to Debug
	public static final String PROP_PARAMETER_IN_TARGET_TYPE = "inTargetType";
	public static final String PROP_PARAMETER_IN_TARGET_NAME = "inTargetName";
	public static final String PROPERTY_SSK_DEBUG_MESSAGE_FRAGMENT = "sskFileBackedManagedDataDebugFragment";
	public static final int DEBUG_FRAGMENT_SIZE_KB = 5;

	// SSK102 Input Parameters used in custom Java
	public static final String PROP_PARAMETER_IN_PROMPT_MAP = "inPropertyNameReportPromptMap";
	
	// SSK112 XSLT Function Libraries
	public static final String VALUE_XSLT_LIB_MESSAGE = "xslt/ssk112/SSK112_FunctionLib_Messages.xsl";

}
