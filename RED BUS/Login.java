import java.util.*;
class Login{
	public static void main(String args[]){
		outer: while(true){
			Scanner sc = new Scanner(System.in);
			System.out.println(" 1) Create User\n 2) User Login\n 3) Create Bus Operator\n 4) Bus Operator Login\n 5) Exit");
			int choice = sc.nextInt();
			switch(choice){
				case 1: MainDriver.createUser();break;
				case 2: MainDriver.loginUser();break;
				case 3: MainDriver.createBusOperator();break;
				case 4: MainDriver.loginBusOperator();break;
				case 5: break outer;
				default: sc.nextLine();System.out.println("Try Input number of 1 2 3 4 ");break;
			}
		}
	}
	
}