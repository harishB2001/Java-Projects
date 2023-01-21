package temp;

public class Raid {
	
	int customerId;
	char pickUpPoint;
	char dropPoint;
	String bookingId;
	int pickUpTime;
	int dropTime;
	private Taxi t;
	String statusMessage;
	int cost = 0;
	
	public Raid(int customerId, char pickUpPoint, char dropPoint, String bookingId, int pickUpTime) {
		super();
		this.customerId = customerId;
		this.pickUpPoint = pickUpPoint;
		this.dropPoint = dropPoint;
		this.bookingId = "B-"+customerId;
		this.pickUpTime = pickUpTime;
		this.dropTime = Math.abs(pickUpTime-dropPoint);
		t = null;
	}
	
	void setTaxi(Taxi t){
		this.t = t;
		setStatusMessage();
	}
	
	private void setStatusMessage() {
		if(this.t == null)
			this.statusMessage = "Trip Cancelled Taxi Not available";
		else { 
			this.statusMessage = "Allocated Taxi "+t.taxiId;
			calculateCost();
			}
	}
	
	private void calculateCost() {
		
		int min = 100;
		int  kms = (Math.abs(pickUpPoint - dropPoint) * 15) - 5;
		int totalCost = min + (kms * 10);
		if(t!=null) {
			cost = totalCost;
		}
		
	}

	
}
