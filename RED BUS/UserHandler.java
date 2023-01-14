import java.util.*;
import java.io.*;
class UserHandler{
		
	static HashMap<String,Passenger> passengerList = new HashMap<>();
	
	static HashMap<String,BusOperator> busOperatorList = new HashMap<>();
	
	static FileHandling fileHandler  = FileHandling.getObject();
	
	static{readUser();}
	
	static void readUser(){
		passengerList = (HashMap) fileHandler.readObject("PassengerBase.txt");
		busOperatorList =(HashMap)fileHandler.readObject("BusOperatorBase.txt");
		if(passengerList == null)passengerList =new HashMap<>();
		if(busOperatorList == null)busOperatorList = new HashMap<>();
		}
	
	static void writeUser(){
			fileHandler.writeObject("PassengerBase.txt",passengerList,false);
			fileHandler.writeObject("BusOperatorBase.txt",busOperatorList,false);
		}
	
	static void addPassenger(Passenger p){passengerList.put(p.phoneNo,p);writeUser();}
	
	static void addBusOperator(BusOperator b){busOperatorList.put(b.phoneNo,b);writeUser();}
	
	static Passenger getPassenger(String phoneNo){
		Passenger p = passengerList.get(phoneNo);
		return p;
	}
	
	static BusOperator getBusOperator(String phoneNo){
		BusOperator bs = busOperatorList.get(phoneNo);
		return bs;
	}
	static boolean isPassengerAvailable(String phone){return passengerList.containsKey(phone);}
	static boolean isBusOperatorAvailable(String phone){return busOperatorList.containsKey(phone);}
}