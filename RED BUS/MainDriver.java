import java.util.*;
class MainDriver{
	public static void createUser(){
		Passenger user = new Passenger();
		if(UserHandler.isPassengerAvailable(user.phoneNo))
			System.out.println("Passenger already Available try Login");
		else{
			UserHandler.addPassenger(user);
			System.out.println("User Created Successfully");
		}
	}
	public static void loginUser(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Phone number: ");
		String phoneNo = sc.nextLine();
		System.out.println("Enter your password: ");
		String password = sc.nextLine();
		boolean auth  = Authentication.getAuthentication(UserType.PASSENGER,phoneNo,password);
		if(auth){
			Passenger passenger = UserHandler.getPassenger(phoneNo);
			System.out.println(" Welcome to Red Bus: ");
			outer: while(true){
				System.out.println(" 1) Ticket Booking\n 2) Cancel Booking\n 3) Booking History\n 4) Logout");
				System.out.println("Enter your choice: ");
				int choice = sc.nextInt();
				switch(choice){
					case 1:passenger.ticketBooking();break;
					case 2:passenger.cancelBooking();break;
					case 3:passenger.getBookingHistory();break;
					case 4:System.out.println("Logout Successful");break outer;
					default: sc.nextLine();System.out.println("Try Input number of 1 2 3 4 ");break;
				}
			}
		}
		else{
			System.out.println(" Invalid Phone Number or Password");
		}
	}
	
	public static void createBusOperator(){
		BusOperator bo = new BusOperator();
		if(UserHandler.isBusOperatorAvailable(bo.phoneNo)){
			System.out.println("Bus Operator already Available");
		}
		else{
			UserHandler.addBusOperator(bo);
			System.out.println("Bus Operator is Created Successfully");
		}
	}
	public static void loginBusOperator(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Phone number: ");
		String phoneNo = sc.nextLine();
		System.out.println("Enter your password: ");
		String password = sc.nextLine();
		boolean auth  = Authentication.getAuthentication(UserType.BUS_OPERATOR,phoneNo,password);
		if(auth){
			BusOperator busOperator = UserHandler.getBusOperator(phoneNo);
			outer: while(true){
				System.out.println(" 1) Add Bus\n 2) create Journey\n 3) List Bus\n 4) Reuse Journey\n 5) List Journey\n 6) Logout");
				System.out.println("Enter your choice: ");
				int choice = sc.nextInt();
				switch(choice){
					case 1:busOperator.addBus();break;
					case 2:busOperator.createJourney();break;
					case 3:busOperator.busListing();break;
					case 4:busOperator.reuseJourney();break;
					case 5:busOperator.listJourney();break;
					case 6:System.out.println("Logout Successful");break outer;
					default: sc.nextLine();System.out.println("Try Input number of 1 2 3 4 ");break;
				}
			}
		}
		else{
			System.out.println(" Invalid Phone Number or Password");
		}
	}
}