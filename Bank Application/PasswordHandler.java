import java.util.*;
class PasswordHandler{
	
	static ArrayList<Password> passwordList = new ArrayList<>();
	static{
		readFromFile();
	}
	static void readFromFile(){
		passwordList = new ArrayList<>();
		FileHandling handler = FileHandling.getObject();
		String s =  handler.readFile("passwords_db.txt");
		String temp[] = s.split("\n");
		for(String t:temp){
			if(t!=""){
				String str[] = t.split(" ");
				LinkedList<String> list = new LinkedList<>();
				for(int i =1;i<str.length;i++){
					list.add(str[i]);
				}
				Password p = new Password(Integer.parseInt(str[0]),list);
				passwordList.add(p);
			}
		}
	}
	
	static boolean isValidPassword(int custId,String password){
		for(Password p:passwordList){
			if(p.customerId == custId){
				if(p.list.contains(password))
					return false;
				else return true;
			}
		}
		return true;
	}
	
	static void changePassword(int custId,String password){
		boolean f = true;
		for(Password p:passwordList){
			if(p.customerId == custId){
				f = false;
				if(p.list.size()>=3)
				p.list.pollLast();
				p.list.add(0,password);
			}
		}
		if(f){
			LinkedList<String> list = new LinkedList<>();
			list.add(password);
			Password p = new Password(custId,list);
			passwordList.add(p);
		}
		writeFile();
	}
	
	private static void writeFile(){
		String s = "";
		for(Password p:passwordList){
			s +=p.customerId+" ";
			for(String k:p.list){
				s+=k+" ";
			}
			s+="\n";
		}
		FileHandling handler = FileHandling.getObject();
		handler.writeIntoFile("passwords_db.txt",s,false);
		
	}
	
	
	
}
