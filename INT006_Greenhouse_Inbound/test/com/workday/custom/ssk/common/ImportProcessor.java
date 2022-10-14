package com.workday.custom.ssk.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class ImportProcessor {
	private static final String INTEGRATION_NAME_SEPARATOR = "_";
	
	private static final String PACKAGE_MYINT = "int006";
	private static final String PROJECT_NAME_WITH_UNDERSCORES = "int006_Greenhouse_Inbound";
	private static final String PROJECT_NAME_WITH_SPACES = "int006 Greenhouse Inbound";	
	
	private static final String[] FILE_EXTENSIONS = {"java", "xml", "xsl", "xsd", "json"};

	Logger log = Logger.getLogger(ImportProcessor.class.getName());
	
	private boolean isDebugMode = true;
	private String newPackageName;
	private Map<String, String> fileContentReplacements;
	
	public ImportProcessor(boolean debugMode, File rootDirectory) throws IOException {
		isDebugMode = debugMode;
		
		String integrationName = StringUtils.substringAfterLast(rootDirectory.getCanonicalPath(), File.separator);
		String integrationId = StringUtils.substringBefore(integrationName, INTEGRATION_NAME_SEPARATOR);
		String newProjectNameWithSpaces = integrationName.replace('_', ' ');

		newPackageName = integrationId.toLowerCase();

		fileContentReplacements = new HashMap<String, String>();
		fileContentReplacements.put(PACKAGE_MYINT, newPackageName);
		fileContentReplacements.put(PROJECT_NAME_WITH_UNDERSCORES, integrationName);
		fileContentReplacements.put(PROJECT_NAME_WITH_SPACES, newProjectNameWithSpaces);

		if (isDebugMode) {
			System.out.println("The integration id is: " + integrationId);
			System.out.println("The integration name is: " + integrationName);
			System.out.println("The new package name is: " + newPackageName);
			System.out.println("The new integration name with spaces is: " + newProjectNameWithSpaces);
		}
	}
	
	public void executeRenamingTasks(File rootDirectory) throws Exception {
		processDirectory(rootDirectory);
	}
	
	private void processDirectory(File directory) throws Exception {
		for (File f : directory.listFiles()) {
			if (f.isDirectory()) {
				System.out.println("The directory name is: " + f.getCanonicalPath());
				
				if (!"build".equalsIgnoreCase(f.getName())) {
					processDirectory(f);
				}
				
				if (PACKAGE_MYINT.equalsIgnoreCase(f.getName())) {
					renameDirectory(f, newPackageName);
				}
			} else {
				processFile(f.toPath());
			}
		}
	}
	
	private void renameDirectory(File directory, String newPackageName) throws Exception {
		System.out.println("Renaming directory from " + directory.getName() + " to " + newPackageName);
		String newPackage = directory.getAbsolutePath().replace(PACKAGE_MYINT, newPackageName);
		if (!isDebugMode) {
			directory.renameTo(new File(newPackage));
		}		
	}
	
	private void processFile(Path path) throws Exception {
		String filename = path.toFile().getName(); 
		for (String ext : FILE_EXTENSIONS) {
			if (StringUtils.endsWith(filename, ext)) {
				modifyFileOnMatch(path);
				break;
			}
		}
	}
	
	private void modifyFileOnMatch(Path path) throws Exception {
		System.out.println("Processing file " + path.getFileName() + " for text replacements");
		if (!isDebugMode) {
			try {
				Stream<String> lines = Files.lines(path);
				List<String> replacedLine = lines.map(line -> doReplacements(line)).collect(Collectors.toList());
				Files.write(path, replacedLine);
				lines.close();
			} catch (Throwable t) {
				log.log(Level.ALL, t.getMessage(), t);
			}
		}
	}
	
	private String doReplacements(String line) {
		for (Map.Entry<String, String> entry : fileContentReplacements.entrySet()) {

			if (line.contains(entry.getKey())) {
				line = line.replace(entry.getKey(), entry.getValue());
			}
		}
		return line;
	}
}
