package temp;

public class Taxi {
	String taxiId;
	int earning;
	int freeTime;
	char location;

	public Taxi(String taxiId) {
		this.taxiId = taxiId;
		this.earning = 0;
		this.freeTime = 0;
		this.location = 'A';
	}

	void setLocation(Raid raid) {
		setFreeTime(raid);
		setEarning(raid);
		location = raid.dropPoint;
	}
	
	private void setFreeTime(Raid raid) {
		this.freeTime = raid.pickUpTime + Math.abs(raid.pickUpPoint-raid.dropPoint);
	}

	private void setEarning(Raid raid) {
		int min = 100;
		int kms = (Math.abs(raid.pickUpPoint - raid.dropPoint) * 15) - 5;
		int totalCost = min + (kms * 10);
		earning += totalCost;
	}

	@Override
	public boolean equals(Object taxi) {
		Taxi t = (Taxi) taxi;
		return t.taxiId == this.taxiId;
	}
	
	@Override
	public String toString() {
		return this.taxiId;
	}
}
