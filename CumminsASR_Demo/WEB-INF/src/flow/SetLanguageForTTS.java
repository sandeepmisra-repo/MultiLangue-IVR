package flow;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.avaya.sce.runtimecommon.IVariableField;

/**
 * A basic servlet which allows a user to define their code, generate
 * any output, and to select where to transition to next.
 * Last generated by Orchestration Designer at: 2019-JUL-06  12:30:46 PM
 */
public class SetLanguageForTTS extends com.avaya.sce.runtime.BasicServlet {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2019-JUL-06  12:30:46 PM
	 */
	public SetLanguageForTTS() {
		//{{START:CLASS:CONSTRUCTOR
		super();
		//}}END:CLASS:CONSTRUCTOR
	}

	/**
	 * This method allows for custom integration with other Java components.
	 * You may use Java for sophisticated logic or to integrate with custom
	 * connectors (i.e. JMS, custom web services, sockets, XML, JAXB, etc.)
	 *
	 * Any custom code added here should work as efficiently as possible to prevent delays.
	 * It's important to design your callflow so that the voice browser (Experienve Portal/IR)
	 * is not waiting too long for a response as this can lead to a poor caller experience.
	 * Additionally, if the response to the client voice browser exceeds the configured
	 * timeout, the platform may throw an "error.badfetch". 
	 *
	 * Using this method, you have access to all session variables through the 
	 * SCESession object.
	 *
	 * The code generator will *** NOT *** overwrite this method in the future.
	 * Last generated by Orchestration Designer at: 2019-JUL-06  12:30:46 PM
	 */
	public void servletImplementation(com.avaya.sce.runtimecommon.SCESession mySession) {
		Date date = new Date();//yyddMMhhmmss
		SimpleDateFormat sdate = new SimpleDateFormat("dd-MM-yy");
		SimpleDateFormat stime = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = sdate.format(date);
		String formattedTime = stime.format(date);
		String TimeStamp=formattedDate + "\t"+ formattedTime;
		
		String Language = mySession.getVariableField(IProjectVariables.SELECTED_LANGUAGE).toString();
		IVariableField SetLanguageCode = mySession.getVariableField(IProjectVariables.LANGUAGE_CODE_TO_TTS);
		IVariableField SetLanguageName = mySession.getVariableField(IProjectVariables.LANGUAGE_NAME_TO_TTS);
		
		if(Language == "2")
		{
			SetLanguageCode.setValue("en-US");
			SetLanguageName.setValue("en-US-Wavenet-E");
			 LogFileIn.stringLogBuilder.append("\r\n"+TimeStamp+ "\t"+"Language Selected For Google TTS: [English]");
 	         LogFileIn.stringLogBuilder.append("\n");
		}
		else if (Language == "3")
		{
			SetLanguageCode.setValue("fr-CA");
			SetLanguageName.setValue("fr-CA-Standard-A");
			 LogFileIn.stringLogBuilder.append("\r\n"+TimeStamp+ "\t"+"Language Selected For Google TTS: [French]");
 	         LogFileIn.stringLogBuilder.append("\n");
		}
		else if (Language == "4")
		{
			SetLanguageCode.setValue("de-DE");
			SetLanguageName.setValue("de-DE-Standard-A");
			 LogFileIn.stringLogBuilder.append("\r\n"+TimeStamp+ "\t"+"Language Selected For Google TTS: [German]");
 	         LogFileIn.stringLogBuilder.append("\n");
		}
		else if (Language == "5")
		{
			SetLanguageCode.setValue("es-ES");
			SetLanguageName.setValue("es-ES-Standard-A");
			 LogFileIn.stringLogBuilder.append("\r\n"+TimeStamp+ "\t"+"Language Selected For Google ASR: [Spanish]");
 	         LogFileIn.stringLogBuilder.append("\n");
		}
		// TODO: Add your code here!

	}
	/**
	 * Builds the list of branches that are defined for this servlet object.
	 * This list is built automatically by defining Goto nodes in the call flow editor.
	 * It is the programmer's responsibilty to provide at least one enabled Goto.<BR>
	 *
	 * The user should override updateBranches() to determine which Goto that the
	 * framework will activate.  If there is not at least one enabled Goto item, 
	 * the framework will throw a runtime exception.<BR>
	 *
	 * This method is generated automatically and changes to it may
	 * be overwritten next time code is generated.  To modify the list
	 * of branches for the flow item, override:
	 *     <code>updateBranches(Collection branches, SCESession mySession)</code>
	 *
	 * @return a Collection of <code>com.avaya.sce.runtime.Goto</code>
	 * objects that will be evaluated at runtime.  If there are no gotos
	 * defined in the Servlet node, then this returns null.
	 * Last generated by Orchestration Designer at: 2019-AUG-28  10:11:10 AM
	 */
	public java.util.Collection getBranches(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.List list = null;
		com.avaya.sce.runtime.Goto aGoto = null;
		list = new java.util.ArrayList(1);

		aGoto = new com.avaya.sce.runtime.Goto("GoogleTTS", 0, true, "Default");
		list.add(aGoto);

		return list;
	}
}
