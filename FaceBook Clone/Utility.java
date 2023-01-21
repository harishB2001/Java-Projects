import java.io.*;
class Utility{
	static{
		reader();
	}
	private static int fbId;
	
	
	static void reader(){
		String s = "";
		try{
			File f = new File("Utility.txt");
			f.createNewFile();
			FileInputStream fis = new FileInputStream(f);
			int i = 0;
			while((i=fis.read())!=-1)
				s+=(char)i;
			fbId = Integer.parseInt(s.split(" ")[0].trim());
		}catch(Exception e){
			fbId = 1;
		}
	}
	
	static void writer(){
		String s = ""+fbId;
		try{
			File f = new File("Utility.txt");
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(s.getBytes());
		}catch(Exception e){}
	}
	
	static public String getNextFBID(){
		String fbid = "FB-"+fbId;
		fbId++;
		writer();
		return fbid;
	}
	
	static void cls(){
		try{
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}
		catch(Exception e){}
	}
}