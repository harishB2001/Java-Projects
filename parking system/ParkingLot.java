class ParkingLot{
	
	Ticket[] ticketList;
	private int ticketListCount = -1;
	String parkingLotNo;
	
	//0 means available 1 means Not available 
	int floorsAndSlots[][];
	
	ParkingLot(String parkingLotNo,int floors,int slots){
		
		this.floorsAndSlots = new int[floors][slots];
		this.parkingLotNo = parkingLotNo;
		this.ticketList = new Ticket[floors*slots];
		System.out.println("Created Parking Lot with "+floors+" floors"+" and "+slots+" slots per floor");
		
	}
	
	//space seperated Int which gives floorNo and slotNo and -1 if no slots available
	//returns 0 indexed
	//0 available 1 not available
	private String findSlot(VehicleType type ){
		
		int minJ = 0;
		int maxJ = 0;
		
		if(type == VehicleType.bike){minJ = 1;maxJ = 2;}
		else if(type == VehicleType.car){minJ = 3;maxJ = floorsAndSlots[0].length-1;}
		
		for(int i = 0;i<=floorsAndSlots.length-1;i++){
			for(int j = minJ;j<=maxJ;j++){
				if(floorsAndSlots[i][j] == 0)
					return ""+i+" "+j;
			}
		}
		return "-1";
	}
	
	public void parkVehicle(Vehicle vehicle){
		String slot = findSlot(vehicle.vehicleType);
		if(slot.equals("-1")){
				System.out.println("Parking Lot Full");
		}
		else{
			String nums[] = slot.split(" ");
			generateTicket( Integer.parseInt(nums[0]),Integer.parseInt(nums[1]),vehicle);
		}
	}
	
	private void generateTicket(int floor,int slotNo,Vehicle vehicle){
		floorsAndSlots[floor][slotNo] = 1;
		ticketList[++ticketListCount] = new Ticket(parkingLotNo,floor,slotNo,vehicle);
		System.out.println("Parked Vehicle: "+ticketList[ticketListCount]);
	}
	
	public void unParkVehicle(String ticketId){
		boolean isAvailable = false;
		int i = 0;
		Ticket t = null;
		for(;i<=ticketListCount;i++){
			t = ticketList[i];
			if(t.ticketId.equals(ticketId)){
				isAvailable = true;
				break;
			}
			
		}
		if(isAvailable){
			floorsAndSlots[t.floor][t.slot] = 0;
			System.out.println("Unparked vehicle with "+t.vehicle);
			for(;i<ticketListCount;i++)
				ticketList[i] = ticketList[i+1];
			ticketListCount--;
		}
		else{
			System.out.println("Invalid Ticket");
		}
		
	}
	
	void displayFreeSlots(String type){
		int minJ = 0;
		int maxJ = 0;
		
		if(type.equals(VehicleType.bike.name())){minJ = 1;maxJ = 2;}
		else if(type.equals(VehicleType.car.name())){minJ = 3;maxJ = floorsAndSlots[0].length-1;}
		
		String freeSlots = "";
		for(int i = 0;i<=floorsAndSlots.length-1;i++){
			freeSlots = "Free slots for "+ type + " on floor "+(i+1)+" :";	
			for(int j = minJ;j<=maxJ;j++){
				if(floorsAndSlots[i][j] == 0)
				freeSlots += (j+1) +",";	
			}
			System.out.println(freeSlots);
		}
	}
	void displayFreeCount(String type){
		int minJ = 0;
		int maxJ = 0;
		
		if(type.equals(VehicleType.bike.name())){minJ = 1;maxJ = 2;}
		else if(type.equals(VehicleType.car.name())){minJ = 3;maxJ = floorsAndSlots[0].length-1;}
		
		String freeSlots = "";
		for(int i = 0;i<=floorsAndSlots.length-1;i++){
			int freeCount = 0;
			freeSlots = "No. of free slots for "+ type + " on floor "+(i+1)+" :";	
			for(int j = minJ;j<=maxJ;j++){
				if(floorsAndSlots[i][j] == 0)
					freeCount++;
			}
			System.out.println(freeSlots+freeCount);
		}
	}
	void displayOccupiedSlots(String type){
		int minJ = 0;
		int maxJ = 0;
		
		if(type.equals(VehicleType.bike.name())){minJ = 1;maxJ = 2;}
		else if(type.equals(VehicleType.car.name())){minJ = 3;maxJ = floorsAndSlots[0].length-1;}
		
		String occupiedSlots = "";
		for(int i = 0;i<=floorsAndSlots.length-1;i++){
			occupiedSlots = "Occupied slots for "+ type + " on floor "+(i+1)+" :";	
			for(int j = minJ;j<=maxJ;j++){
				if(floorsAndSlots[i][j] == 1)
				occupiedSlots += (j+1) +",";	
			}
			System.out.println(occupiedSlots);
		}
	}
	
}