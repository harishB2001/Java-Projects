import java.util.*;
class User{
	private static HashMap<String,Integer> map;
	static{
		map = new HashMap<>();
		readFile();
		
	}
	public static boolean isEligible(String s){
			if(map.containsKey(s) && map.get(s)!=0)return true;
			else return false;
	}
	
	public static void writeFile(){
		String s = "";
		for(String key:map.keySet()){
			s+=key+" "+map.get(key)+"\n";
		}
		s = s.substring(0,s.length()-1);
		FileHandling handler = FileHandling.getObject();
		handler.writeFile(s,"UserBase.txt",false);
	}
	
	public static void  readFile(){
		FileHandling handler = FileHandling.getObject();
		String str[] = handler.readFile("UserBase.txt").split("\n");
		for(String s:str){
			if(s!=""){
				String temp[] = s.split(" ");
				map.put(temp[0],Integer.parseInt(temp[1]));
			}
		}
		
	}
	 
	 //true == (+1)............false == (-1)
	public static void updateUserMap(String userId,boolean isReturn){
		if(isReturn)map.put(userId,map.get(userId)+1);
		else map.put(userId,map.get(userId)-1);
		writeFile();
	}
	
	public static void addUser(String userId){
		if(!(map.containsKey(userId)))
		{map.put(userId,5);
			System.out.println("Created User: "+userId);
			writeFile();
		}
	}
}