package com.workday.custom.ssk.cleanup;

import java.io.File;

import com.workday.custom.ssk.common.CleanupProcessor;

public class CleanupAssistant {
	
	private static final boolean isDebugModeOn = false;
	
	public static void main(String[] args) {
		try {
			
			File rootDirectory = new File(".");
			
			String assemblyXml = "./ws/WSAR-INF/assembly.xml";
			String diagramXml = "./ws/WSAR-INF/assembly-diagram.xml";
			
//			String assemblyXml = "C:\\Users\\john.smail\\workspace\\xslt\\SSK\\assembly-v2.xml";
//			String diagramXml = "C:\\Users\\john.smail\\workspace\\xslt\\SSK\\assembly-diagram-v2.xml";

			CleanupProcessor cp = new CleanupProcessor(isDebugModeOn, assemblyXml, diagramXml);
			cp.executeCleanupTasks(rootDirectory);

			if (isDebugModeOn) {
				System.out.println("The process has completed.  Please review the output in the Console view.");
			} else {
				System.out.println("The assembly cleanup steps have been completed.  Please refresh your Workday Studio Assembly Project in the Project Explorer.  If the Assembly Editor is currently open, you will need to close and reopen it before it will recognize the changes made.  If there are SSK Components that can be removed from the assembly, the CleanupAssistant will highlight those component swimlanes in red font for you to manually delete (the graphical nature of Studio prevents automated deletes for those swimlane contents).");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}
}
