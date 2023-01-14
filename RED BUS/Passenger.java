import java.util.*;
import java.time.*;
class Passenger extends User{
	int age;
	String gender;
	ArrayList<Ticket> ticketList;
	
	Passenger(){
		super();	
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter age: ");
		this.age = sc.nextInt();
		System.out.println("Enter gender: ");
		gender = sc.next();
		ticketList = new ArrayList<>();
	}
	
	public void ticketBooking(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Boarding Point: ");
		String source = sc.nextLine();
		System.out.println("Enter Destination: ");
		String destination = sc.nextLine();
		System.out.println("Enter Date(yyyy mm dd) : " );
		LocalDate date = LocalDate.of(sc.nextInt(),sc.nextInt(),sc.nextInt());
		String str = "";
		for(String phone:UserHandler.busOperatorList.keySet()){
			BusOperator b = UserHandler.busOperatorList.get(phone);
			str+=b.boardingAvailability(source,destination,date);
		}
		if(str.equals("")){System.out.println("No Source or Destination Found for this available date");return;};
		Journey tempJourney = null;
		BusOperator b =  null;
		outer: while(true){
			System.out.println(str);
			System.out.println("Enter the valid Journey Id: ");
			tempJourney = new Journey(sc.next());
			for(String phone:UserHandler.busOperatorList.keySet()){
				 b = UserHandler.busOperatorList.get(phone);
				if(b.journey.contains(tempJourney)){
					int temp = b.journey.indexOf(tempJourney);
					tempJourney = b.journey.get(temp);
					break outer;
				}
			}
		}
		sc.nextLine();
		String seatList = "";
		while(true){
			tempJourney.display(source,destination);
			System.out.println("\nSelect space seperated seats ");
			seatList = sc.nextLine();
			if(tempJourney.isValidSeat(seatList,source,destination)){
				break;
			}
			System.out.println("Enter Available Seats");
		}
		int totalFare = tempJourney.calculateFare(source,destination,seatList.split(" ").length);
		Ticket t = new Ticket(tempJourney,source,destination,seatList,totalFare);
		ticketList.add(t);
		tempJourney.setSeatAvailibility(seatList,source,destination,false);
		System.out.println("Ticket Successfully Created \nYour Ticket Details are as Follows ");
		System.out.println(t);
		UserHandler.writeUser();
	}
	public void cancelBooking(){
		Scanner sc = new Scanner(System.in);
		Ticket tempTicket = null;
		boolean isAvailableForCancellation = true;
		outer: while(true){
			for(Ticket ticket:ticketList){
				if(ticket.ticketStatus.equals("Booked"))
				{
					System.out.println(ticket);
					isAvailableForCancellation = false;
				}
			}
			if(isAvailableForCancellation){
				System.out.println("No tickets Availeble for Cancellation. ");
				return;
			}
			System.out.println("Enter the Valid Ticket Id from the list ");
			tempTicket = new Ticket(sc.next());
			if(ticketList.contains(tempTicket)){
				System.out.println("Tickets Found..");
				int index = ticketList.indexOf(tempTicket);
				tempTicket= ticketList.get(index);
				break outer;
			}
		}
		Journey jt = tempTicket.journey;
		jt.setSeatAvailibility(tempTicket.seatNo,tempTicket.startFrom,tempTicket.destination,true);
		tempTicket.ticketStatus = "Cancelled";
		System.out.println("Ticket Cancelled Successfully");
		UserHandler.writeUser();
		
	}
	public void getBookingHistory(){
		System.out.println(ticketList);
	}	
}
