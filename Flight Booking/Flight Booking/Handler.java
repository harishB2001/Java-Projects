import java.util.*;
import java.io.*;
class Handler{
		
	static HashMap<String,User> userList = new HashMap<>();
	
	static Journey journey;
	
	static FileHandling fileHandler  = FileHandling.getObject();
	
	static{readObject();}
	
	static void readObject(){
		userList = (HashMap) fileHandler.readObject("userBase.txt");
		journey =(Journey)fileHandler.readObject("journey.txt");
		if(userList == null)userList =new HashMap<>();
		//if(journey == null)journey = new Journey();
		}
	
	static void writeObject(){
			fileHandler.writeObject("userBase.txt",userList,false);
			fileHandler.writeObject("journey.txt",journey,false);
		}
	
	static void addUser(User u){userList.put(u.emailId,u);writeObject();}
	
	
	static User getUser(String emailId){
		User u = userList.get(emailId);
		return u;
	}
	
	static Journey getJourney(){
		return journey;
	}
	
	static boolean isUserAvailable(String emailId){return userList.containsKey(emailId);}
}