import java.util.*;
class Transaction{
	
	static HashMap<Integer,Integer> map = new HashMap<>();
	
	static{
		readfromFile();
	}
	
	static public void readfromFile(){
		FileHandling handler = FileHandling.getObject();
		String s = handler.readFile("transaction.txt");
		String str[] =s.split("\n");
		for(String temp:str){
			if(temp!=""){
			String t[] = temp.split(" ");
			map.put(Integer.parseInt(t[0]),Integer.parseInt(t[1]));
			}
		}
	}
	
	static public void writeNewFile(){
		FileHandling handler  = FileHandling.getObject();
		handler.writeTransactionFile(map);
	}
	
	static void updateTransactionCount(Customer d){
		int c = d.custId;
		if(map.containsKey(c)){
			map.put(c,map.get(c)+1);
		}
		else{
			map.put(c,1);
		}
		
		writeNewFile();
	}
	
	static void updateTransaction(Customer c, String s){
		FileHandling handler  = FileHandling.getObject();
		handler.writeIntoFile(""+c.custId+".txt",s);
	}
	
	static int getCount(Customer d){
		int c = d.custId;
		if(map.containsKey(c)){
			return map.get(c);
		}
		else
			return 0;
	}
	
}