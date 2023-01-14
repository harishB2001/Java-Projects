class Authentication{
	
	public static boolean getAuthentication(String emailId, String password){
			User user = Handler.getUser(emailId);	
			if(user!= null && user.password.equals(password))return true;
			else return false;
	}	
}