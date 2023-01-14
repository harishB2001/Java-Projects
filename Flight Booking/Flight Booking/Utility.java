import java.io.*;
class Utility{
	static{
		reader();
	}
	private static int ticketNumber;
	private static int journeyNumber;
	
	static private void reader(){
		String s = "";
		try{
			File f = new File("Utility.txt");
			f.createNewFile();
			FileInputStream fis = new FileInputStream(f);
			int i = 0;
			while((i=fis.read())!=-1)
				s+=(char)i;
			ticketNumber = Integer.parseInt(s.split(" ")[0].trim());
			journeyNumber = Integer.parseInt(s.split(" ")[1].trim());
		}catch(Exception e){
			ticketNumber = 1;
			journeyNumber =1;
		}
	}
	
	static private void writer(){
		String s = ""+ticketNumber+" "+journeyNumber;
		try{
			File f = new File("Utility.txt");
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(s.getBytes());
		}catch(Exception e){}
	}
	
	static public String getNextTicketId(){
		String ticket = "T-"+ticketNumber;
		ticketNumber++;
		writer();
		return ticket;
	}
	static public String getNextJourneyId(){
		String journey = "JR-"+journeyNumber;
		journeyNumber++;
		writer();
		return journey; 		
	}
}