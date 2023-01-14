import java.util.*;
import java.io.*;
class Ticket implements Serializable{
	public static final long serialVersionUID = 635110l;

	String ticketId;
	Journey journey;
	String passengerList;
	String startFrom;
	String destination;
	String seatNo;
	int totalFare;
	String ticketStatus = "Booked";
	
	Ticket(Journey journey, String startFrom, String destination, String seatNo,int totalFare){
		this.ticketId = Utility.getNextTicketId();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Space Seperated Passenger list");
		this.passengerList = sc.nextLine();
		this.journey = journey;
		this.startFrom = startFrom;
		this.destination = destination;
		this.seatNo = seatNo;
		this.totalFare = totalFare;
	}
	Ticket(String ticketId){
		this.ticketId = ticketId;
	}
	@Override
	public String toString(){
		String str = "";
		str+="\t Ticket-Id  : "+this.ticketId+"\n";
		str+="\t Journey-Id : "+this.journey.journeyId+"\n";
		str+="\t Passengers : "+this.passengerList+"\n";
		str+="\t "+startFrom+" ---> "+destination+"\n";
		str+="\t Seat - No  : "+ this.seatNo+"\n";
		str+="\t Total Fare : "+this.totalFare+"\n";
		str+="\t Status     : "+this.ticketStatus+"\n";
		return str+"\n";
	}
	
	@Override
	public boolean equals(Object obj){
		Ticket t = (Ticket)obj;
		return this.ticketId.equals(t.ticketId);
	}
}