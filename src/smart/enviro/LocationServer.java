package smart.enviro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocationServer {
	public String LocationJudge(String location) throws IOException {
		String pathname = "filedata/configuration.txt";    //Read the city information file.
		File filename = new File(pathname);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
		BufferedReader bReader = new BufferedReader(reader);
		String text = "", indoor = "", outdoor = "";
		try {
			text = bReader.readLine();
			indoor = bReader.readLine().substring(7);   //Record locations indoors.
			outdoor = bReader.readLine().substring(8);   //Record locations outdoors.
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			bReader.close();
		}
		if(location.equals("indoor")) {
			return indoor;
		}else if (location.equals("outdoor")) {
			return outdoor;
		}else {
			String[] Indoors = indoor.split(",");
			String[] Outdoors = outdoor.split(",");
			for(int i=0;i<Indoors.length;i++) {
				if(location.equals(Indoors[i])) {
					return "indoor";
				}
			}
			for(int i=0;i<Outdoors.length;i++) {
				if(location.equals(Outdoors[i])) {
					return "outdoor";
				}
			}
		}
		return null;
	}

}
