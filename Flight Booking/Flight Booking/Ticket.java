import java.util.*;
import java.io.*;
class Ticket implements Serializable{
	
	String ticketId;
	ArrayList<Passenger>passenger;
	Journey journey;
	int totalFare;
	String ticketStatus;
	
	Ticket(Journey j,ArrayList<Passenger>passenger,int totalFare,String ticketStatus){
		this.ticketId = Utility.getNextTicketId();
		this.passenger = passenger;
		this.journey = journey;
		this.totalFare = totalFare;
		this.ticketStatus = ticketStatus;
	}
	
	@Override
	public String toString(){
		String str = "";
		str += "Ticket Id    : "+this.ticketId+"\n";
		str += "Passenger    : "+this.passenger+"\n";
		str += "Total Fare   : "+this.totalFare+"\n";
		str += "ticketStatus : "+this.ticketStatus+"\n";
		return str;
	}
}