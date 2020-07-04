package smart.enviro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.query.ice.info.EnvirInformation;
import com.query.ice.info.PreferenceIn;
import com.query.ice.info.QueryCityInfoPrx;
import com.query.ice.info.QueryCityInfoPrxHelper;
import com.query.ice.info.QueryEnvirInfoPrx;
import com.query.ice.info.QueryEnvirInfoPrxHelper;
import com.query.ice.info.QueryPreferInfoPrx;
import com.query.ice.info.QueryPreferInfoPrxHelper;
import com.query.ice.info.QueryWeaInfoPrx;
import com.query.ice.info.QueryWeaInfoPrxHelper;

public class EnviroAppUI {
	private static Scanner input = new Scanner(System.in);   //Define input method,record the signal input by the user.
	//Used to determine whether the user presses the Enter key.
	private static long StartTime = 0;    //Record application start time.
	private static String username = "";  

	public static void main(String[] args) throws Exception {				
		System.out.println("Context-aware Enviro Smart Application");
		System.out.println("Please enter your user name:");	
		username = input.nextLine();    //User input his/her username,Application stores it.
		StartTime = System.currentTimeMillis(); 
		
		Ice.Communicator communicator = null;
		communicator = Ice.Util.initialize(args);    //Get user's Medical Condition Type and Temperature Threshold.
		Ice.ObjectPrx oPpre= communicator.stringToProxy("QueryPreInfo:tcp -h localhost -p 10000");
		QueryPreferInfoPrx qpre = QueryPreferInfoPrxHelper.uncheckedCast(oPpre);
		PreferenceIn pIn = new PreferenceIn();
		pIn = qpre.queryprefer(username, null);
		int Medi_Type = pIn.type;    //user's Medical Condition Type
		double Tem_Thteshold = pIn.temthreshold;   //user's Temperature Threshold.
		
		//Create a thread that requests environment parameters every 60 seconds.
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				long NowTime = System.currentTimeMillis();   //Record the current time of the application.
				int pastTime = (int)(NowTime - StartTime)/1000;  //Record the length of time that has passed.
				String Warning = "", Suggestion = "", items = "";
				String Now_location = "";
				int APO = 0;
				Ice.Communicator communicator = null;
				communicator = Ice.Util.initialize(args);   //Get environment information.
				Ice.ObjectPrx oPrxEn= communicator.stringToProxy("QueryEnviroInfo:tcp -h localhost -p 10000");
				QueryEnvirInfoPrx qpEn = QueryEnvirInfoPrxHelper.uncheckedCast(oPrxEn);
				
				//request weather alarm information.
				communicator = Ice.Util.initialize(args);
				Ice.ObjectPrx oPwea = communicator.stringToProxy("QueryWeaInfo:tcp -h localhost -p 10000");
				QueryWeaInfoPrx qpwea = QueryWeaInfoPrxHelper.uncheckedCast(oPwea);
				int Wea_alarm = qpwea.queryweather(pastTime);   //get the weather alarm.
				communicator = Ice.Util.initialize(args);    //Get city information.
				Ice.ObjectPrx oPrx= communicator.stringToProxy("QueryCityInfo:tcp -h localhost -p 10000");
				QueryCityInfoPrx qp = QueryCityInfoPrxHelper.uncheckedCast(oPrx);
				
				EnvirInformation eInformation = new EnvirInformation();
				eInformation = qpEn.queryenvir(username, pastTime);   //Get the user's current environment
				LocationServer locationServer = new LocationServer();   //Determine if the user is outdoors.
				try {
					Now_location = locationServer.LocationJudge(eInformation.location);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//If the user is outdoors, proceed to the next step, otherwise do nothing.
				if (Now_location.equals("outdoor")) {     
					if(Wea_alarm>0) {      //if extreme weather is detected.
						PreferenceIn pIn = new PreferenceIn();
						pIn = qpre.queryprefer(username, "weather");    //get services
						//get items of interest for suggestion.
						items = qp.querycity(pIn.service, 3);   //if type=3,query by service.And must be indoor.
						if(Wea_alarm==1) {
							Warning = "extreme weather is detected, the current weather event is heavy rain.";
						}else if (Wea_alarm==2) {
							Warning = "extreme weather is detected, the current weather event is hail storm.";
						}else if (Wea_alarm==3) {
							Warning = "extreme weather is detected, the current weather event is strong wind.";
						}
						Suggestion = "please go to " + items.substring(0,items.length()-1) + ".";
						ShowWarning(Warning,Suggestion);
					}
					//Calculate APO
					if(eInformation.AQI>=0 && eInformation.AQI<=50) {
						APO = 30 * Medi_Type; 
					}else if (eInformation.AQI>50 && eInformation.AQI<=100) {
						APO = 15 * Medi_Type; 
					}else if (eInformation.AQI>100 && eInformation.AQI<=150) {
						APO = 10 * Medi_Type; 
					}else if (eInformation.AQI>150 && eInformation.AQI<=200) {
						APO = 5 * Medi_Type; 
					}
					if(APO<50) {   //if APO is detected.
						PreferenceIn pIn = new PreferenceIn();
						pIn = qpre.queryprefer(username, "APO");    //get services
						//get items of interest for suggestion.
						items = qp.querycity(pIn.service, 3);   //if type=3,query by service.And must be indoor.
						Suggestion = "please go to " + items.substring(0,items.length()-1) + ".";
						Warning = "significant air pollution level detected, the current AQI is "+String.valueOf(eInformation.AQI)+".";
						ShowWarning(Warning,Suggestion);
					}
					if (eInformation.temperature>=Tem_Thteshold) {   //if Temperatur thteshold is detected.
						PreferenceIn pIn = new PreferenceIn();
						pIn = qpre.queryprefer(username, "temperature");    //get services
						String[] aStrings = pIn.service.split(",");
						for(int i=0;i<aStrings.length;i++) {
							String info = qp.querycity(aStrings[i], 4);    //if type=3,query by service.No need to be indoor.
							items = items + info;
						}
						//Remove duplicates from the results.
						String ite = "";
						items = items.substring(0,items.length()-1);
						String[] zStrings = items.split(",");
						List list = Arrays.asList(zStrings);
						Set set = new HashSet(list);
						zStrings=(String [])set.toArray(new String[0]);
						for(int i=0;i<zStrings.length;i++) {
							ite = ite + zStrings[i]+",";
						}
						Warning = "Temperature is now "+String.valueOf(eInformation.temperature)+".";
						Suggestion = "please go to " + ite.substring(0,ite.length()-1) + ".";
						ShowWarning(Warning,Suggestion);
					}				
				}					
				
			}
		};
		//The first request is started 5 seconds after the application is started, and is requested every 10 seconds thereafter.
		ScheduledExecutorService Schservice = Executors.newSingleThreadScheduledExecutor();
		Schservice.scheduleAtFixedRate(runnable, 5, 10, TimeUnit.SECONDS);
		BacktoMainMenu(args);  //Go to the main menu without warning and suggestion for the first time.

	}
	//Define a function that displays the main menu, including displaying warnings and suggestions
	private static void BacktoMainMenu(String[] args) throws Exception {
		System.out.println("Context-aware Enviro Smart Application Main Menu");
		System.out.println("Please select an option:");
		System.out.println("1. Search for information on a specific item of interset");
		System.out.println("2. Search for items of interest in current location");
		System.out.println("E. Exit");
		
		Ice.Communicator communicator = null;
		communicator = Ice.Util.initialize(args);    //Get city information.
		Ice.ObjectPrx oPrx= communicator.stringToProxy("QueryCityInfo:tcp -h localhost -p 10000");
		QueryCityInfoPrx qp = QueryCityInfoPrxHelper.uncheckedCast(oPrx);
		if(qp==null) {
			throw new Exception("qp == null");
		}
		communicator = Ice.Util.initialize(args);   //Get environment information.
		Ice.ObjectPrx oPrxEn= communicator.stringToProxy("QueryEnviroInfo:tcp -h localhost -p 10000");
		QueryEnvirInfoPrx qpEn = QueryEnvirInfoPrxHelper.uncheckedCast(oPrxEn);
		if(qpEn==null) {
			throw new Exception("qpEn == null");
		}
		//!!!Enter the main menu loop!!!
		try {	
			input = new Scanner(System.in);
			String signal = input.next();   //Record the signal input by the user.
			//Keep logged in as long as the user does not quit.
			do {			
				if(signal.equals("1")) {     //User enters "1".
					String Itemname = "";
					System.out.println("Please enter name of item of interest:");		
					input = new Scanner(System.in);
					Itemname = input.nextLine();	 //User enter the name of item.				
					
					int type = 1;  //if type=1,query by name;if type=2,query by location.
					String result = qp.querycity(Itemname,type);
					if(result == "") {
						System.out.println("No match found for item of interest");
					}else {
						System.out.println("Information about "+Itemname+":");
						System.out.println(result);
					}
					BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
					String str = bf.readLine();   //Press the Enter key and return to the Main Menu.
					do {
						if(str.length() == 0) {
							BacktoMainMenu(args);
							signal = input.next();
							break;
						}else {
							System.out.println("Please press the Enter Key:");
							str = bf.readLine();
						}
					}while(true);	
				}else if (signal.equals("2")) {    //User enters "2".
					long NowTime = System.currentTimeMillis();   //Record the current time of the application.
					int pastTime = (int)(NowTime - StartTime)/1000;  //Record the length of time that has passed.
					//Get the user's current environment.
					EnvirInformation eInformation = new EnvirInformation();
					eInformation = qpEn.queryenvir(username, pastTime);   //Get the user's current environment				
					int type = 2;  //if type=1,query by name;if type=2,query by location.
					String result = qp.querycity(eInformation.location,type);
					if(result == "") {
						System.out.println("No match found for item of interest");
					}else {
						System.out.println("The following items of interest are in your location:");
						System.out.println(result);
					}
					BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
					String str = bf.readLine();   //Press the Enter key and return to the Main Menu.
					do {
						if(str.length() == 0) {
							BacktoMainMenu(args);
							signal = input.next();
							break;
						}else {
							System.out.println("Please press the Enter Key:");
							str = bf.readLine();
						}
					}while(true);
				}else if (signal.equals("E")) {    //User enters "E".
					qpEn.shutdown();
					System.out.println("Shut down...");
					System.out.println("Goodbye!");
					break;
				}else {
					System.out.println("Please enter the correct option:");
					signal = input.next();    //Continue to cycle.
				}
			}while(true);
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			input.close();  //Close the input stream.
			System.exit(1);
		}
		
	}
	
	private static void ShowWarning(String warning, String suggestion) {
		//If a warning occurs, send a warning and suggestion to the user and display the main menu.
		System.out.println("Context-aware Enviro Smart Application Main Menu");
		System.out.println("Warning, " + warning);
		System.out.println("Suggestion - " + suggestion);
		System.out.println("Please select an option:");
		System.out.println("1. Search for information on a specific item of interset");
		System.out.println("2. Search for items of interest in current location");
		System.out.println("E. Exit");
	}

}
