import java.util.*;
class ParkingDriver{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		ParkingLot lot = null;
		outer: while(true){
			System.out.println("Enter the command..");
			String input  = sc.nextLine();
			String inputSplit[] = input.split(" ");
			
			switch(inputSplit[0]){
				case "create_parking_lot": 
				{
					lot = new ParkingLot(inputSplit[1],Integer.parseInt(inputSplit[2]),Integer.parseInt(inputSplit[3]));
					break;
				}
				case "display_free_count":
				{
					lot.displayFreeCount(inputSplit[1]);
					break;
				}
				case "display_free_slots":{
					lot.displayFreeSlots(inputSplit[1]);
					break;
				}
				case "display_occupied_slots":{
					lot.displayOccupiedSlots(inputSplit[1]);
					break;
				}
				case "park_vehicle":{
					Vehicle vehicle  = new Vehicle(inputSplit[1],inputSplit[2],inputSplit[3]);
					lot.parkVehicle(vehicle);
					break;
				}
				case "unpark_vehicle":{
					lot.unParkVehicle(inputSplit[1]);
					break;
				}
				case "exit": break outer;
			}
			
		}
	}
}