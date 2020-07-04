[["java:package:com.query.ice"]]
module info
{
	struct CityInformation{
		string name;
		string location;
		string information;
		string services;
	};
	struct EnvirInformation{
		string location;
		int temperature;
		double AQI;
	};
	struct PreferenceIn
	{
		int type;
		string service;
		double temthreshold;
	};
	interface QueryCityInfo
	{
		string querycity(string value, int type);
		void shutdown();
	};
	interface QueryEnvirInfo
	{
		EnvirInformation queryenvir(string username, int time);
		void shutdown();
	};
	interface QueryWeaInfo
	{
		int queryweather(int time);
		void shutdown();
	};
	interface QueryPreferInfo
	{
		PreferenceIn queryprefer(string username, string information);
		void shutdown();
	};
};