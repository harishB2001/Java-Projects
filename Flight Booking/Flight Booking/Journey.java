import java.util.*;
import java.io.*;
class Journey implements Serializable{
	
	String source;
	String destinaton;
	boolean seatInformation[];
	LinkedList<Ticket> queueTicketList;
	
	Journey(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Source: ");
		this.source = sc.nextLine();
		System.out.println("Enter Destination: ");
		this.destinaton = sc.nextLine();
		seatInformation = new boolean[60];
		Arrays.fill(seatInformation,true);
		queueTicketList = new LinkedList<>();
	}
	
	public void bookTicket(User u){
		Scanner sc = new Scanner(System.in);
		if(queueTicketList.size()<=19){
			System.out.println("Enter the number of passengers");
			int count = sc.nextInt();sc.nextLine();
			int seatCount = getAvailableSeatCount();
				if(seatCount+(20-queueTicketList.size())<count)
					System.out.println("Seats are Full Try again after Sometime");
				else{
					ArrayList<Passenger> passenger = new ArrayList<>();
					for(int i = 1;i<=count;i++){
						Passenger p = new Passenger();
						passenger.add(p);
						if(seatCount<count)
							p.seatNo ="WL";
					}
					if(seatCount<count){
						int totalFare = calculateTotalFare(passenger);
						Ticket ticket = new Ticket(this,passenger,totalFare,"Waiting List");
						u.addTicket(ticket);
						queueTicketList.add(ticket);
					}
					else{
					allocateSeat(passenger);//method to be implemented
					setSeatStatus(passenger,false);
					int totalFare = calculateTotalFare(passenger);
					Ticket ticket = new Ticket(this,passenger,totalFare,"Booked");
					u.addTicket(ticket);
				}
				}
			}
		else{
			System.out.println("Seats are Full Try again after Sometime");
			System.out.println("Waiting List is Full");
		}
	}
	
	public int calculateTotalFare(ArrayList<Passenger> passenger){
		int totalFare = 0;
		for(Passenger p:passenger){
			if(p.age<=10)totalFare += 600;
			else totalFare += 1300;
			int seatNo = getSeatNo(p);
			if(p.seatPreference=='W' && (seatNo%6==0 || seatNo%6==5))
				totalFare+=100;
		}
		return totalFare;
		
	}
	
	public int getSeatNo(Passenger p){
		return(p.seatNo.length()==3)
		?Integer.parseInt(""+p.seatNo.charAt(2))
		:Integer.parseInt(""+p.seatNo.substring(2,4));
	}
	
	public void setSeatStatus(ArrayList<Passenger>passenger,boolean status){
		for(Passenger p:passenger){
			if(p.seatNo.equals("WL")){}//Must Resolve the seatStatus for cancelling or allocation
			else seatInformation[getSeatNo(p)-1] = status;
		}
	}
	
	public int getAvailableSeatCount(){
		int i = 0;
		for(boolean bool:seatInformation)
			if(bool)
				i++;
		return i;
	}
	
	public void allocateSeat(ArrayList<Passenger> passenger){
		for(Passenger p:passenger){
			int seatNo =getNextAvailableSeatForAdult(p);
			if(seatNo!=-1)p.seatNo = "S-"+seatNo;
			//else {p.seatNo = "WL";queueTicketList.add(p);}this is a bug
		}
	}
	public int getNextAvailableSeatForAdult(Passenger p){
		char seatP = p.seatPreference;
		int seatNo = -1;
		int searchCount = 0;
		while(true){
			switch(seatP){
				case 'N':
				case 'A':{seatNo = getNextSeat(2,3);if(seatNo!=-1)return seatNo;}
				case 'M':{seatNo = getNextSeat(1,4);if(seatNo!=-1)return seatNo;}
				case 'W':{seatNo = getNextSeat(0,5);if(seatNo!=-1)return seatNo;}	
			}
			if(seatP!='N')return -1;
			else seatP = 'N';
		}
	}
	public int getNextSeat(int lowerBound,int upperBound){
		for(int i = lowerBound;i<seatInformation.length;i++){
			if(seatInformation[i]==true && (i%6==lowerBound || i%6==upperBound))return i+1;
		}
		return -1;
		
	}
	
	public static void main(String args[]){
		Journey journey = new Journey();
		FileHandling fileHandler  = FileHandling.getObject();
		fileHandler.writeObject("journey.txt",journey,false);
		
	}
}