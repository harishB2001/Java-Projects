import java.util.*;
class MainDriver{
	public static void createUser(){
		User user = new User();
		if(Handler.isUserAvailable(user.emailId))
			System.out.println("Passenger already Available try Login");
		else{
			Handler.addUser(user);
			System.out.println("User Created Successfully");
		}
	}
	public static void loginUser(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Email-Id: ");
		String emailId = sc.nextLine();
		System.out.println("Enter your password: ");
		String password = sc.nextLine();
		boolean auth  = Authentication.getAuthentication(emailId,password);
		if(auth){
			User user = Handler.getUser(emailId);
			System.out.println(" Welcome to Flight Booking: ");
			outer: while(true){
				System.out.println(" 1) Ticket Booking\n 2) Cancel Booking\n 3) Logout");
				System.out.println("Enter your choice: ");
				int choice = sc.nextInt();
				switch(choice){
					case 1:user.ticketBooking(Handler.getJourney());break;
					case 2:user.cancelBooking();break;
					case 3:System.out.println("Logout Successful");break outer;
					default: sc.nextLine();System.out.println("Try Input number of 1 2 3 ");break;
				}
			}
		}
		else{
			System.out.println(" Invalid Email-Id or Password");
		}
	}
	
	
}