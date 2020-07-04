package smart.enviro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.query.ice.info.CityInformation;
import com.query.ice.info._QueryCityInfoDisp;

import Ice.Current;

public class QueryCityInfoI extends _QueryCityInfoDisp{
	@Override
	public String querycity(String value, int type, Current __current) throws IOException {
		String pathname = "filedata/city_information.txt";    //Read the city information file.
		File filename = new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader bReader = new BufferedReader(reader);
		CityInformation[] cInformations = new CityInformation[5];   //Record 5 city information.
		LocationServer lServer = new LocationServer();   //Determine whether the location is indoor or outdoor.
		try {
			String line = "";
			for(int i=0;i<5;i++) {
				cInformations[i] = new CityInformation();
				line = bReader.readLine();
				cInformations[i].name = line.substring(5);
				line = bReader.readLine();
				cInformations[i].location = line.substring(9);
				line = bReader.readLine();
				cInformations[i].information = line.substring(12);
				line = bReader.readLine();
				cInformations[i].services = line.substring(9);
				line = bReader.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			reader.close();
			bReader.close();
		}
		String result = "";
		if(type == 1) {
			for(int i=0;i<5;i++) {
				if(cInformations[i].name.equals(value)) {
					result = cInformations[i].information;
				}
			}
		}else if (type == 2) {
			for(int i=0;i<5;i++) {
				if(cInformations[i].location.equals(value)) {
					result = result+cInformations[i].name +"\n";
				}
			}
		}else if (type == 3) {
			for(int i=0;i<5;i++) {
				if((lServer.LocationJudge(cInformations[i].location)).equals("indoor")) {
					String[] bStrings = cInformations[i].services.split(",");
					for(int j=0;j<bStrings.length;j++) {
						if(bStrings[j].equals(value)) {
							result = result + cInformations[i].name + ",";
						}
					}
				}
			}
		}else if (type == 4) {
			for(int i=0;i<5;i++) {
				String[] bStrings = cInformations[i].services.split(",");
				for(int j=0;j<bStrings.length;j++) {
					if(bStrings[j].equals(value)) {
						result = result + cInformations[i].name + ",";
					}
				}
			}
		}
		return result;
	}

	@Override
	public void shutdown(Current __current) {
		// TODO Auto-generated method stub
		__current.adapter.getCommunicator().shutdown();
	}

}
