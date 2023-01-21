package temp;

public class Main {
public static void main(String[] args) {
	RaidBook rb = new RaidBook();
	
	Taxi t1 = new Taxi("T-1");
	Taxi t2 = new Taxi("T-2");
	Taxi t3 = new Taxi("T-3");
	Taxi t4 = new Taxi("T-4");
	
	rb.addTaxi(t1);
	rb.addTaxi(t2);
	rb.addTaxi(t3);
	rb.addTaxi(t4);
	
//	Raid d1 = new Raid(1,'B','D',"B-1",4);
//	Raid d2 = new Raid(2,'B','C',"B-2",5);
//	Raid d3 = new Raid(3,'B','E',"B-3",7);
//	Raid d4 = new Raid(4,'D','F',"B-4",7);
//	Raid d5 = new Raid(5,'B','E',"B-5",6);
	
	Raid d1 = new Raid(1,'A','E',"B-1",10);
	Raid d2 = new Raid(2,'A','E',"B-2",10);
	Raid d3 = new Raid(3,'A','E',"B-3",10);
	Raid d4 = new Raid(4,'A','E',"B-4",10);
	Raid d5 = new Raid(5,'E','A',"B-5",14);

	System.out.println(rb.bookTaxi(d1));
	System.out.println(rb.bookTaxi(d2));
	System.out.println(rb.bookTaxi(d3));
	System.out.println(rb.bookTaxi(d4));
	System.out.println(rb.bookTaxi(d5));
	//System.out.println(rb.bookTaxi(d6));
	
	
}
}
