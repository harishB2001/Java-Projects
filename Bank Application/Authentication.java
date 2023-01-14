import java.util.*;
class Authentication{
	
	static boolean getAuthentication(ArrayList<Customer>list,int custId, String password){
		for(Customer c:list){
			if(c.custId==custId && c.encryptedPwd.equals(password)){
				return true;
			}
		}
		return false;
	}
	
	
}


