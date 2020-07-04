package smart.enviro;

public class ContextManager {
	public static void main(String[] args) {
		Ice.Communicator communicator = null;
		try {
			communicator = Ice.Util.initialize(args);
			Ice.ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Query", "tcp -h localhost -p 10000");
			QueryCityInfoI servantCity = new QueryCityInfoI();     //Query city information.
			adapter.add(servantCity, communicator.stringToIdentity("QueryCityInfo"));
			AllSensors servantEnviro = new AllSensors();     //Query city information.
			adapter.add(servantEnviro, communicator.stringToIdentity("QueryEnviroInfo"));
			PreferenceRepository servantPre = new PreferenceRepository();   //Query Preference information.
			adapter.add(servantPre, communicator.stringToIdentity("QueryPreInfo"));
			WeatherAlarm servantWea = new WeatherAlarm();
			adapter.add(servantWea, communicator.stringToIdentity("QueryWeaInfo"));
			adapter.activate();
			communicator.waitForShutdown();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally {
			if(communicator != null) {
				communicator.destroy();
			}
		}
		
	}
}
