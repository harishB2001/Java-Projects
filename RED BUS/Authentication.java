enum UserType{
	BUS_OPERATOR,PASSENGER;
}
class Authentication{
	
	public static boolean getAuthentication(UserType type,String phoneNo, String password){
		
		if(type == UserType.BUS_OPERATOR){
			BusOperator user = UserHandler.getBusOperator(phoneNo);	
			if(user!= null && user.getPassword().equals(password))return true;
			else return false;
		}
		else if(type == UserType.PASSENGER){
			Passenger user = UserHandler.getPassenger(phoneNo);	
			if(user!= null && user.getPassword().equals(password))return true;
			else return false;
		}
		else return false;
	}	
}