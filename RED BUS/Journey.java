import java.util.*;
import java.time.*;
import java.io.*;

enum TimeFilter{
	Q1,Q2,Q3,Q4
}

class Journey implements Serializable{
	public static final long serialVersionUID = 635110l;
	String journeyId;
	ArrayList<LocalDateTime> arrivalTime;//boarding point - arrival date time
	ArrayList<Integer> priceList;
	LinkedHashMap<String,boolean[]> seatInformation; //seatNo,seat information for all stops in boolean
	ArrayList<String> boardingPointList;
	Bus bus;
	
	Journey(Bus bus){
		this.journeyId = Utility.getNextJourneyId();
		Scanner sc = new Scanner(System.in);
		String route[];
		boardingPointList = new ArrayList<String>();
		arrivalTime = new ArrayList<>();
		priceList = new ArrayList<>();
		while(true){
			System.out.println("Enter Space Seperated Boarding Points : ");
			route = sc.nextLine().split(" ");
			if(route.length>=2){
				break;
			}
			System.out.println("Source and Destination Cannot be the same");
		}	
		for(int i = 0;i<route.length;i++){
			boardingPointList.add(route[i]);
			System.out.println("Enter Arrival Date(yyyy mm dd) for "+route[i]+" : " );
			LocalDate date = LocalDate.of(sc.nextInt(),sc.nextInt(),sc.nextInt());
			System.out.println("Enter Arrival Time(hh mm) for "+route[i]+" :" );
			LocalTime time = LocalTime.of(sc.nextInt(),sc.nextInt());
			LocalDateTime dateTime = LocalDateTime.of(date,time);
			arrivalTime.add(dateTime);
		}
		for(int  i=0;i<route.length-1;i++){
			System.out.println("Enter price for "+route[i]+" --> "+route[i+1]+" : ");
			priceList.add(sc.nextInt());
		}
		this.bus  = bus;
		
		createEmptySeats();
	}
	
	Journey(String journeyId){this.journeyId = journeyId;}
	@Override
	public boolean equals(Object obj){
		Journey j = (Journey)obj;
		return j.journeyId.equals(journeyId);
	}
	@Override
	public String toString(){
		return "Journey ID: "+this.journeyId+" Source: "+boardingPointList.get(0)
		+"  Destination: "+boardingPointList.get(boardingPointList.size()-1)+" Date: "+arrivalTime.get(0).toLocalDate()+"\n";
	}
	
	private void createEmptySeats(){
		seatInformation = new LinkedHashMap<String,boolean[]>();
		for(int i =0;i<bus.totalSeats;i++){
			boolean b[] = new boolean[boardingPointList.size()];
			Arrays.fill(b,true);
			seatInformation.put(""+bus.busType.name()+(i+1),b);
		}
	}
	
	 boolean isBoardingPointAvailable(String boardingPoint, String destination,LocalDate date){
		if(boardingPointList.contains(boardingPoint) && boardingPointList.contains(destination)){
			int i = boardingPoint.indexOf(boardingPoint);
			//return true;
			LocalDate arrivalDate = arrivalTime.get(i).toLocalDate();
			return arrivalDate.equals(date);
		}return false;
	}
	
	private boolean isSeatAvailable(String seatNo,String source,String destination){
		int s = boardingPointList.indexOf(source);
		int d = boardingPointList.indexOf(destination);
		boolean b[] = seatInformation.get(seatNo);
		for(int i=s;i<d;i++){
			if(b[i]==false)return false;
		}
		return true;
	}
	
	public boolean isSeatAvailable(String source,String destination){
		int s = boardingPointList.indexOf(source);
		int d = boardingPointList.indexOf(destination);
		for(String seatNo:seatInformation.keySet()){
			boolean b[] = seatInformation.get(seatNo);
			for(int i = s;i<d;i++){
			if(b[i]==true){
				System.out.println("Seat Available");
				return true;
				}
			}
		}
		System.out.println("Seat Not Available");
		return false;
		
	}
	
	boolean isValidSeat(String str,String source,String destination){
		boolean f = true;
		String arr[] = str.split(" ");
		for(String a:arr){
			f &= isSeatAvailable(a,source,destination);
		}
		return f;
	}
	
	void setSeatAvailibility(String seat,String source,String destination,boolean availableStatus){
		String arr[] =seat.split(" ");
		int s = boardingPointList.indexOf(source);
		int d = boardingPointList.indexOf(destination);
		for(String seatNo:arr){
			System.out.print(seatNo+" ");
		boolean b[] = seatInformation.get(seatNo.trim());
			for(int i = s;i<d;i++){
				b[i] = availableStatus;
		}
		}
	}
	
	int calculateFare(String source,String destination, int count){
		int s = boardingPointList.indexOf(source);
		int d = boardingPointList.indexOf(destination);
		int singleCost = 0;
		for(int i = s;i<d;i++)
			singleCost += priceList.get(i);
		return count*singleCost;
	}
	
	public void display(String source,String destination){
		int i =0;
		int j =0;
		for(String seatNo:seatInformation.keySet()){
		if(i%4==0)System.out.print("\n\t");
		else if(j%2==0)System.out.print("   ");
			if(isSeatAvailable(seatNo,source,destination)){
				if(seatNo.length()==2)System.out.print(seatNo+"  ");
				else System.out.print(seatNo+" ");
			}
			else{
				System.out.print("__  ");
			}
			i++;
			j++;
		}
	}
}