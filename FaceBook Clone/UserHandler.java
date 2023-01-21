import java.util.*;
class UserHandler{
	
	static HashMap<String,User> userList = new HashMap<>();
	static FileHandling fileHandler  = FileHandling.getObject();
	static{readUser();}
	
	@SuppressWarnings("unchecked")
	static void readUser(){
		userList = (HashMap) fileHandler.readObject("UserBase.txt");
		if(userList == null)userList =new HashMap<>();
		}
	
	static void writeUser(){
			fileHandler.writeObject("UserBase.txt",userList,false);
		}
	
	static void addUser(User u){userList.put(u.phoneNo,u);userList.put(u.emailId,u);writeUser();}
	
	static boolean isUserAvailable(String credentialId){
		if(userList.containsKey(credentialId))return true;
		else return false;
	}
	
	static User getAuthentication(String credentialId,String credentialPassword){
		if(userList.containsKey(credentialId) && userList.get(credentialId).password.equals(credentialPassword))
			return userList.get(credentialId);
		else return null;
	}
	
	static void searchUser(User u){
		Scanner sc = new Scanner(System.in);
		Utility.cls();
		System.out.println("\t\t\t-------------------\n\t\t\tSearch User\n\t\t\t-------------------");
		System.out.println("\t\t\tEnter User name: ");
		String searchUser = sc.nextLine();
		Set<User> tempUser = new HashSet<>();
		for(String key:userList.keySet()){
			User user = userList.get(key);
			if(user.name.startsWith(searchUser) && !(u.equals(user)))
			tempUser.add(user);
		}
		
		if(tempUser.size()==0){System.out.println("No FB Peer Found\nPress Enter to Continue");sc.nextLine();}
		else{
			int i = 1;
			ArrayList<User>list = new ArrayList<>(tempUser);
			for(User user:list){
				System.out.println(i+++" )"+user.name);
			}
		System.out.println("\n\t\t\tEnter 0 for Exit or Press Appropriate Number to View Profile: ");
			int choice = sc.nextInt();
			switch(choice){
				case 0:break;
				default: u.viewProfile(list.get(choice-1));
			}
		}
		
	}
	
}