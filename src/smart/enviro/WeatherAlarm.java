package smart.enviro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.query.ice.info._QueryWeaInfoDisp;

import Ice.Current;

public class WeatherAlarm extends _QueryWeaInfoDisp{

	@Override
	public int queryweather(int time, Current __current) throws IOException {
		String pathname = "filedata/weather.txt";    //Read the weather alarm file.
		File filename = new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader bReader = new BufferedReader(reader);
		bReader.mark((int)filename.length()+1);   //Mark the initial location of the file.
		int My_time = time + 60, we_alarm = 0;  //Record the next 60 seconds of weather.
		try {
			
			String text = "";
			do {
				if((text = bReader.readLine())!=null) {
					String[] bStrings = text.split(",");
					if((My_time=My_time-Integer.parseInt(bStrings[1])) > 0) {
						continue;
					}else {
						we_alarm = Integer.parseInt(bStrings[0]);
					}
				}else {
					bReader.reset(); //If reach the end of the file, return to the initial position.
				}
				
			}while(My_time>0);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally {
			reader.close();
			bReader.close();
		}
		return we_alarm;
	}

	@Override
	public void shutdown(Current __current) {
		// TODO Auto-generated method stub
		__current.adapter.getCommunicator().shutdown();
	}

}
