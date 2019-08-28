package flow;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;

import com.avaya.sce.runtimecommon.IVariableField;

/**
 * A basic servlet which allows a user to define their code, generate
 * any output, and to select where to transition to next.
 * Last generated by Orchestration Designer at: 2019-JUL-06  10:29:29 AM
 */
public class GoogleTTS extends com.avaya.sce.runtime.BasicServlet {

	//{{START:CLASS:FIELDS
	//}}END:CLASS:FIELDS

	/**
	 * Default constructor
	 * Last generated by Orchestration Designer at: 2019-JUL-06  10:29:29 AM
	 */
	public GoogleTTS() {
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
	 * Last generated by Orchestration Designer at: 2019-JUL-06  10:29:29 AM
	 */
	
	
	public int count=0;
	public static String MobileNumber ="";
	public static String Ucid=""; 
	
	public void servletImplementation(com.avaya.sce.runtimecommon.SCESession mySession) {

		
		Date date = new Date();//yyddMMhhmmss
		SimpleDateFormat sdate = new SimpleDateFormat("dd-MM-yy");
		SimpleDateFormat stime = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = sdate.format(date);
		String formattedTime = stime.format(date);
		String TimeStamp=formattedDate + "\t"+ formattedTime;
		
		

		MobileNumber =  mySession.getVariableField(IProjectVariables.SESSION,IProjectVariables.SESSION_FIELD_ANI).getStringValue();
		Ucid =  mySession.getVariableField(IProjectVariables.SESSION,IProjectVariables.SESSION_FIELD_UCID).getStringValue();
		String LanguageCode =  mySession.getVariableField(IProjectVariables.LANGUAGE_CODE_TO_TTS).toString();
		String LanguageName =  mySession.getVariableField(IProjectVariables.LANGUAGE_NAME_TO_TTS).toString();
		try
        {
			
		 System.out.println("------------------------------------------------------------------------------------------------------------");
		//SSL Certificate
	        TrustManager[] trustAllCerts = new TrustManager[]
	                {
	                     new X509TrustManager()
	                     {
	                          public java.security.cert.X509Certificate[] getAcceptedIssuers()
	                          {
	                               return null;
	                          }

	                          public void checkClientTrusted(X509Certificate[] certs, String authType)
	                          {
	                          }

	                          public void checkServerTrusted(X509Certificate[] certs, String authType)
	                          {
	                          }
	                     }
	                };

	                // Install the all-trusting trust manager
	                SSLContext sc = SSLContext.getInstance("SSL");
	                sc.init(null, trustAllCerts, new java.security.SecureRandom());
	                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	                // Create all-trusting host name verifier
	                HostnameVerifier allHostsValid = new HostnameVerifier()
	                {
	                     public boolean verify(String hostname, SSLSession session)
	                     {
	                          return true;
	                     }
	                };

	                // Install the all-trusting host verifier-----29/9
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            String weburl = "https://texttospeech.googleapis.com/v1beta1/text:synthesize?key=AIzaSyD8ZrvoSGt_ZdysosQrLwvWdcBIBpmevQw";
            String inputText =  mySession.getVariableField(IProjectVariables.OUT_GOOGLE_ASR).toString();
           

            String ssml = "<speak><say-as interpret-as=\"characters\">"+inputText+"</say-as></speak>";
            LogFileIn.stringLogBuilder.append("\r\n" + TimeStamp +"\t"+ "[Input Text Request In Google TTS]     -->");
            LogFileIn.stringLogBuilder.append(ssml);
//            String json = "{\"audioConfig\":{\"audioEncoding\":\"LINEAR16\",\"pitch\":0.00,\"speakingRate\":1.00},\"input\":{\"text\":\"\"},\"voice\":{\"languageCode\":\"en-US\",\"name\":\"en-US-Wavenet-D\"}}";
//            String json = "{\"audioConfig\": {\"audioEncoding\": \"LINEAR16\",\"sampleRateHertz\":8000,\"effectsProfileId\":[\"telephony-class-application\"],\"pitch\": \"-7.4\",\"speakingRate\": \"0.81\"},\"input\": {\"ssml\": \"\"},\"voice\": {\"languageCode\": \"en-US\",\"name\": \"en-US-Wavenet-D\"}}";
            String json = "{\"audioConfig\": {\"audioEncoding\": \"LINEAR16\",\"sampleRateHertz\":8000,\"effectsProfileId\":[\"telephony-class-application\"],\"pitch\": \"0.00\",\"speakingRate\": \"0.81\"},\"input\": {\"ssml\": \"\"},\"voice\": {\"languageCode\": \""+LanguageCode+"\",\"name\": \""+LanguageName+"\"}}";
            JSONObject jsonObj = new JSONObject(json);
            JSONObject chldJson = jsonObj.getJSONObject("input");
           // chldJson.put("text", inputText);
            chldJson.put("ssml", ssml);

            java.net.URL url = new java.net.URL(weburl);
            LogFileIn.stringLogBuilder.append("\r\n" + TimeStamp +"\t" +"[Request To Google TTS]     -->");
            LogFileIn.stringLogBuilder.append(weburl);
            java.net.HttpURLConnection connjava = (java.net.HttpURLConnection) url.openConnection();
            connjava.setRequestMethod("POST");

            connjava.setRequestProperty("Content-Type", "application/json");
            connjava.setDoInput(true);
            connjava.setDoOutput(true);
            connjava.setUseCaches(false);
            
	        connjava.setAllowUserInteraction(true);

            java.io.DataOutputStream dataOutputStream = new java.io.DataOutputStream(connjava.getOutputStream());
            dataOutputStream.writeBytes(jsonObj.toString());
            LogFileIn.stringLogBuilder.append("\r\n" + TimeStamp +"\t"+ "[Request Google TTS Data]     -->");
            LogFileIn.stringLogBuilder.append(jsonObj.toString());
            LogFileIn.stringLogBuilder.append("\n");
            dataOutputStream.flush();
            dataOutputStream.close();
            int respcode = connjava.getResponseCode();
            System.out.println(respcode);
            if (connjava.getResponseCode() != java.net.HttpURLConnection.HTTP_OK)
            {
                System.out.println("********* Unable to connect to the URL *********\n");
                System.out.println(connjava.getResponseMessage());
                IVariableField serverStatus = mySession.getVariableField(IProjectVariables.SERVER_STATUS_ASR); 
	        	serverStatus.setValue("1");		
            }
            else
            {

            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(connjava.getInputStream()));
            StringBuilder sbout = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null)
            {
                sbout.append(line);
            }
            in.close();

            System.out.println(sbout.toString());
            String decodeStr = sbout.substring(sbout.indexOf(":") + 3, sbout.lastIndexOf("}") - 1);
            
            String ucid=mySession.getVariableField(IProjectVariables.SESSION,IProjectVariables.SESSION_FIELD_UCID).toString();
            count++;
 
        //    String ttsOutFilePath="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5_Tomcat8_5\\webapps\\decode\\"+ucid+"TTS"+count+".wav";
            String ttsOutFilePath="D:\\officedocument\\personal\\Task By Suvijoy Sir\\Server\\AAOD-7.2.1\\apache-tomcat-8.5.23\\webapps\\decode\\"+ucid+"TTS"+count+".wav";
            IVariableField FileName = mySession.getVariableField(IProjectVariables.FILE_NAME); 
            FileName.setValue(ucid+"TTS"+count+".wav");

            System.out.println(ttsOutFilePath);
            byte[] data = Base64.getDecoder().decode(decodeStr);
           // byte[] data = Base64.decodeBase64(decodeStr);
            try (OutputStream stream = new FileOutputStream(ttsOutFilePath))
            {
                stream.write(data);
                System.out.println(stream);
                IVariableField varOutGoogleTTS = mySession.getVariableField(IProjectVariables.OUT_GOOGLE_TTS); 
               // varOutGoogleTTS.setValue("http://172.16.0.97:6080/decode/"+ucid+"TTS"+count+".wav");
                varOutGoogleTTS.setValue("http://localhost:8080/decode/"+ucid+"TTS"+count+".wav");
	            System.out.println(varOutGoogleTTS);
	            LogFileIn.stringLogBuilder.append("\r\n"+TimeStamp+ "\t"+"[Response From Google TTS]     -->");
	            LogFileIn.stringLogBuilder.append(String.valueOf( respcode)+"."+"\t"+String.valueOf( stream));
	            LogFileIn.stringLogBuilder.append("\n");
            }
            catch (Exception ex)
            {
            	System.out.println("DECODE catch" +ex);
            	 ex.printStackTrace();
            	 IVariableField serverStatus = mySession.getVariableField(IProjectVariables.SERVER_STATUS_TTS); 
              	serverStatus.setValue("1");
              	LogFileIn.stringLogBuilder.append("\r\n"+TimeStamp+ "\t"+"[DECODE Exception]     -->");
              	LogFileIn.stringLogBuilder.append("\t"+ex.toString());
              	LogFileIn.stringLogBuilder.append("\n");
              	LogFileIn.count = 0;
            }
            }
        }
        catch (Exception ex)
        {
        	System.out.println(" catch" +ex);
         	IVariableField serverStatus = mySession.getVariableField(IProjectVariables.SERVER_STATUS_TTS); 
         	serverStatus.setValue("1");
            ex.printStackTrace();
            LogFileIn.stringLogBuilder.append("\r\n"+TimeStamp+ "\t"+"[Exception]");
            LogFileIn.stringLogBuilder.append("\t"+ex.toString());
            LogFileIn.count = 0;
            
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
	 * Last generated by Orchestration Designer at: 2019-AUG-28  10:11:11 AM
	 */
	public java.util.Collection getBranches(com.avaya.sce.runtimecommon.SCESession mySession) {
		java.util.List list = null;
		com.avaya.sce.runtime.Goto aGoto = null;
		list = new java.util.ArrayList(1);

		aGoto = new com.avaya.sce.runtime.Goto("ChkErrorTTS", 0, true, "Default");
		list.add(aGoto);

		return list;
	}
}
