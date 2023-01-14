class Ticket{
	
	String ticketId;
	Vehicle vehicle;
	int floor;
	int slot;
	
	Ticket(String lotID,int floorNo,int slotNo,Vehicle vehicle){
		this.floor = floorNo;
		this.slot = slotNo;
		this.ticketId = lotID+"_"+floorNo+"_"+slotNo;
		this.vehicle = vehicle;
	}
	
	@Override
	public String toString(){
		return "Ticket Id: "+this.ticketId;
	}
	
}