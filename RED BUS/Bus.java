import java.util.*;
import java.io.*;
enum BUSAMENITIES{
BED_SHEET, BLANKETS, CHARGING_POINT, MOVIE, TOILET, TRACK_MY_BUS, WIFI, WATER_BOTTLE;
}

enum BUSTYPE{
	L,S;
}

class Bus implements Serializable{
	public static final long serialVersionUID = 635110l;
	String busId;
	BUSTYPE busType;
	Set<BUSAMENITIES> busAmenitiesSet;
	int totalSeats;
	
	Bus(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Bus No: ");
		this.busId = sc.nextLine();
		System.out.println("Enter the bus type L or S -----> (L - Seater , S - Sleeper) ");
		String type = sc.nextLine();
		if(type.equals("L"))busType = BUSTYPE.L;
		else busType = BUSTYPE.S;
		System.out.println("Enter all Amenities that apply for this Bus (Space Seperated Numbers): ");
		System.out.println(" 1) Bed Sheet\n 2) BLANKETS\n 3) CHARGING POINT\n 4) MOVIE\n 5) TOILET\n 6) TRACK MY BUS\n 7) WIFI\n 8) WATER BOTTLE\n");
		String amenities[] = sc.nextLine().split(" ");
		busAmenitiesSet = new HashSet<>();
		for(String s:amenities){
			switch(s){
				case "1":busAmenitiesSet.add(BUSAMENITIES.BED_SHEET);break;
				case "2":busAmenitiesSet.add(BUSAMENITIES.BLANKETS);break;
				case "3":busAmenitiesSet.add(BUSAMENITIES.CHARGING_POINT);break;
				case "4":busAmenitiesSet.add(BUSAMENITIES.MOVIE);break;
				case "5":busAmenitiesSet.add(BUSAMENITIES.TOILET);break;
				case "6":busAmenitiesSet.add(BUSAMENITIES.TRACK_MY_BUS);break;
				case "7":busAmenitiesSet.add(BUSAMENITIES.WIFI);break;
				case "8":busAmenitiesSet.add(BUSAMENITIES.WATER_BOTTLE);break;
			}
		}
	System.out.println("Enter total Number of Seats: ");
	totalSeats = sc.nextInt();
	System.out.println("Bus Created Successfully: "+this.busId);
	}
	Bus(String busId){this.busId = busId;}
	@Override
	public String toString(){
		return this.busId+" "+"Bus Type: "+this.busType.name()+"\n";
	}
	
	@Override
	public boolean equals(Object bus){
	Bus b = (Bus) bus;
	return this.busId.equals(b.busId);
	}
}
