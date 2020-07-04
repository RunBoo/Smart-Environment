package smart.enviro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.query.ice.info.EnvirInformation;
import com.query.ice.info._QueryEnvirInfoDisp;

import Ice.Current;

public class AllSensors extends _QueryEnvirInfoDisp{

	@Override
	public EnvirInformation queryenvir(String username, int time, Current __current) throws IOException {
		// TODO Auto-generated method stub
		String L_pathname = "filedata/"+username+"Location.txt";    //Read the user's location file.
		File L_filename = new File(L_pathname);
		InputStreamReader L_reader = new InputStreamReader(new FileInputStream(L_filename));
		BufferedReader L_bReader = new BufferedReader(L_reader);
		L_bReader.mark((int)L_filename.length()+1);   //Mark the initial location of the file.
		String A_pathname = "filedata/"+username+"AQI.txt";    //Read the user's AQI file.
		File A_filename = new File(A_pathname);
		InputStreamReader A_reader = new InputStreamReader(new FileInputStream(A_filename));
		BufferedReader A_bReader = new BufferedReader(A_reader);
		A_bReader.mark((int)L_filename.length()+1);   //Mark the initial location of the file.
		String T_pathname = "filedata/"+username+"Temperature.txt";    //Read the user's temperature file.
		File T_filename = new File(T_pathname);
		InputStreamReader T_reader = new InputStreamReader(new FileInputStream(T_filename));
		BufferedReader T_bReader = new BufferedReader(T_reader);
		T_bReader.mark((int)L_filename.length()+1);   //Mark the initial location of the file.
		int L_time = time, A_time = time, T_time = time;
		
		EnvirInformation eInformation = new EnvirInformation();
		try {
			//Find the user's current location.
			String L_text = "";
			do {
				if((L_text = L_bReader.readLine())!=null) {
					String[] bStrings = L_text.split(",");
					if((L_time=L_time-Integer.parseInt(bStrings[1])) > 0) {
						continue;
					}else {
						eInformation.location = bStrings[0];
					}
				}else {
					L_bReader.reset(); //If reach the end of the file, return to the initial position.
				}
				
			}while(L_time>0);
			//Find the user's current AQI.
			String A_text = "";
			do {
				if((A_text = A_bReader.readLine())!=null) {
					String[] bStrings = A_text.split(",");
					if((A_time=A_time-Integer.parseInt(bStrings[1])) > 0) {
						continue;
					}else {
						eInformation.AQI = Double.valueOf(bStrings[0]);
					}
				}else {
					A_bReader.reset();    //If reach the end of the file, return to the initial position.
				}
				
			}while(A_time>0);
			//Find the user's current temperature.
			String T_text = "";
			do {
				if((T_text = T_bReader.readLine())!=null) {
					String[] bStrings = T_text.split(",");
					if((T_time=T_time-Integer.parseInt(bStrings[1])) > 0) {
						continue;
					}else {
						eInformation.temperature = Integer.parseInt(bStrings[0]);
					}
				}else {
					T_bReader.reset();   //If reach the end of the file, return to the initial position.
				}		
			}while(T_time>0);
				
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			L_reader.close();
			L_bReader.close();
			A_reader.close();
			A_bReader.close();
			T_reader.close();
			T_bReader.close();
		}
		return eInformation;
	}

	@Override
	public void shutdown(Current __current) {
		// TODO Auto-generated method stub
		__current.adapter.getCommunicator().shutdown();
	}

}
