package smart.enviro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.query.ice.info.CityInformation;
import com.query.ice.info.PreferenceIn;
import com.query.ice.info._QueryPreferInfoDisp;

import Ice.Current;

public class PreferenceRepository extends _QueryPreferInfoDisp{

	@Override
	public PreferenceIn queryprefer(String username, String information, Current __current) throws IOException {
		// TODO Auto-generated method stub
		String pathname = "filedata/preference.txt";    //Read the preference file.
		File filename = new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader bReader = new BufferedReader(reader);
		PreferenceIn pIn = new PreferenceIn();
		try {
			//Record the personal preferences of two users
			String username1 = bReader.readLine().substring(5);
			String type1 = bReader.readLine().substring(23);
			String firpre1 = bReader.readLine().substring(12);
			String sepre1 = bReader.readLine().substring(12);
			String thpre1 = bReader.readLine().substring(12);
			bReader.readLine();
			String username2 = bReader.readLine().substring(5);
			String type2 = bReader.readLine().substring(23);
			String firpre2 = bReader.readLine().substring(12);
			String sepre2 = bReader.readLine().substring(12);
			String thpre2 = bReader.readLine().substring(12);
			
			if(information==null||information.equals("")) {   //request user's Medical Condition Type and Temperature Threshold.
				if(username.equals(username1)) {
					pIn.type = Integer.parseInt(type1);
					pIn.temthreshold = Double.valueOf((firpre1.split(" "))[0]);
				}else if (username.equals(username2)) {
					pIn.type = Integer.parseInt(type2);
					pIn.temthreshold = Double.valueOf((firpre2.split(" "))[0]);
				}
			}else if (information.equals("weather")) {    //Request service list when warning occurs.
				if(username.equals(username1)) {
					pIn.service = thpre1.split(" ")[2];
				}else if (username.equals(username2)) {
					pIn.service = thpre2.split(" ")[2];
				}
			}
			else if (information.equals("APO")) {
				if(username.equals(username1)) {
					pIn.service = sepre1.split(" ")[2];
				}else if (username.equals(username2)) {
					pIn.service = sepre2.split(" ")[2];
				}
			}else if (information.equals("temperature")) {
				if(username.equals(username1)) {
					pIn.service = firpre1.split(" ")[2];
				}else if (username.equals(username2)) {
					pIn.service = firpre2.split(" ")[2];
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			reader.close();
			bReader.close();
		}
		return pIn;
	}

	@Override
	public void shutdown(Current __current) {
		// TODO Auto-generated method stub
		__current.adapter.getCommunicator().shutdown();
	}

}
