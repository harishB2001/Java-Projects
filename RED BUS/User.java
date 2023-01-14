import java.util.*;
import java.io.*;
abstract class User implements Serializable{
	public static final long serialVersionUID = 635110l;
	String phoneNo;
	protected String password;
	String name;
	public void setPassword(String password){this.password = password;}
	public String getPassword(){return this.password;}	

	User(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Phone Number: ");
		this.phoneNo = sc.nextLine();
		System.out.println("Enter Password");
		this.password  = sc.nextLine();
		System.out.println("Enter Name: ");
		this.name = sc.nextLine();
	}
	
	@Override
	
	public boolean equals(Object obj){
		User user = (User)obj;
		return this.phoneNo.equals(user.phoneNo) && this.password.equals(user.password);
	}
}