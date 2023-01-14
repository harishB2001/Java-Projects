import java.util.*;
import java.io.*;
class User implements Serializable{
	String emailId;
	String password;
	String address;
	ArrayList<Ticket> ticketList;
	
	User(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Email-ID: ");
		this.emailId = sc.nextLine();
		System.out.println("Enter Password: ");
		this.password = sc.nextLine();
		System.out.println("Enter Address: ");
		this.address = sc.nextLine();
		ticketList = new ArrayList<>();
	}
	
	public void ticketBooking(Journey j){
		j.bookTicket(this);
	}
	
	public void cancelBooking(){
		
	}
	
	public void addTicket(Ticket ticket){
		ticketList.add(ticket);
		System.out.println("Ticket Generated Successfully..");
		System.out.println(ticket);
		Handler.writeObject();
		
	}
	
}