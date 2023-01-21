package temp;

import java.util.ArrayList;

public class RaidBook {

	ArrayList<Raid> raidList = new ArrayList<>();
	static ArrayList<Taxi> taxiList = new ArrayList<>();

	private void addToRaidList(Raid raid) {
		raidList.add(raid);
	}

	public void addTaxi(Taxi taxi) {
		taxiList.add(taxi);
	}

	public String bookTaxi(Raid raid) {
		Taxi t = taxiList.get(0);
		ArrayList<Taxi> tempTaxi = new ArrayList<>();

		// chooses taxi
		for (Taxi temp : taxiList) {
			if (temp.freeTime + (Math.abs(temp.location - raid.pickUpPoint)) > raid.pickUpTime)
				continue;
			else
				tempTaxi.add(temp);
		}
		if(tempTaxi.size()!=0) 
		t = tempTaxi.get(0);
		for (Taxi temp : tempTaxi) {
			if (Math.abs(t.location - raid.pickUpPoint) > Math.abs(temp.location - raid.pickUpPoint)
					|| (Math.abs(t.location - raid.pickUpPoint) == Math.abs(temp.location - raid.pickUpPoint)
							&& t.earning > temp.earning))
				t = temp;
		}

		// taxi choosing ends
		if(tempTaxi.size()!=0)
		if (t.equals(tempTaxi.get(0)) && t.freeTime + Math.abs(t.location - raid.pickUpPoint) > raid.pickUpTime)
			t = null;
		if(tempTaxi.size()==0) t = null;
		raid.setTaxi(t);

		addToRaidList(raid);
		if (t != null) {
			t.setLocation(raid);
		}
		return raid.statusMessage;

	}

}
