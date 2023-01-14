import java.util.*;
import java.time.*;

class BusOperator extends User{
	public static final long serialVersionUID = 635110l;
	ArrayList<Bus>busList;
	int totalRevenue;
	ArrayList<Journey> journey;	

	BusOperator(){
		super();
		busList = new ArrayList<>();
		journey = new ArrayList<>();
		totalRevenue = 0;
	}
	
	public void addBus(){
		busList.add(new Bus());
		UserHandler.writeUser();
	}
	
	public void createJourney(){
		if(busList.size()==0)System.out.println("Add a Bus and then create a Journey for the Bus");
		else{
			System.out.println(busList);
			System.out.println("Enter the Bus Reg no. from above list to create a journey: ");
			Scanner sc = new Scanner(System.in);
			Bus bus = new Bus(sc.nextLine());
			if(!busList.contains(bus)){
				System.out.println("Please Enter the Correct Bus ID: ");
				createJourney();
			}
			else{
				int i = 0;
				for(Bus b:busList){if(bus.equals(b)){break;}i++;}
				Journey j = new Journey(busList.get(i));
				journey.add(j);
				System.out.println("Journey Created Successfully: "+j.journeyId);
				UserHandler.writeUser();
			}
		}
	}
	
	public String boardingAvailability(String source,String destination, LocalDate date){
		String str = "\t";
		boolean flag = false;
		for(Journey j:journey){
			if(j.isBoardingPointAvailable(source,destination,date) && j.isSeatAvailable(source,destination)){
				str+=this.name+" ";
				int s = j.boardingPointList.indexOf(source);
				int d = j.boardingPointList.indexOf(destination);
				LocalTime sT= j.arrivalTime.get(s).toLocalTime();
				LocalTime dT= j.arrivalTime.get(d).toLocalTime();
				Bus bus = j.bus;//index of source and destination of j and its current fares
				str += bus.busId+" "+bus.busType.name()+" "+"Start time: "+sT+" "+"Destination time: "+dT+" Journey ID: "+j.journeyId;
				flag = true;
			}
		}
		if(flag)
		return str+"\n";
		return "";
		
	}
		
	public void reuseJourney(){}
	
	public void busListing(){
		System.out.println(busList);
	}
	public void listJourney(){
		System.out.println(journey);
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}