import java.util.Scanner;
import java.io.*;

class Passenger implements Serializable{
	String name;
	int age;
	char seatPreference;
	String seatNo;
	
	Passenger(){
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Name: ");
		this.name = sc.nextLine();
		System.out.println("Enter Age: ");
		this.age = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Seat Preference: ");
		this.seatPreference = sc.nextLine().charAt(0);
	}
	
	@Override
	public String toString(){
		String str = "";
		str += this.name+"-"+this.seatNo+" ";
		return str;
	}
}