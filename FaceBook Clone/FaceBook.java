import java.util.Scanner;
class FaceBook{
	public static void landingPage(){
		Scanner sc = new Scanner(System.in);
		outer: while(true){
			Utility.cls(); 
			System.out.println("\t\t\t-------------------\n\t\t\tLogin/Signup\n\t\t\t-------------------");
			System.out.println("\n\n\t\t\t1) Sign-Up\n\t\t\t2) Log-In\n\t\t\t3) Exit\n\t\t\tEnter your Choice");
			int choice = sc.nextInt();
			switch(choice){
				case 1:signUp();break;
				case 2:login();break;
				case 3:break outer;
			}
		}
	}
	private static void signUp(){
		Scanner sc = new Scanner(System.in);
		Utility.cls();
		User u = null;
		try{
			u = new User();
			UserHandler.addUser(u);
			System.out.println("\n\t\t\tFaceBook Account Created Successfully\n\t\t\tPress Enter to Continue");
			sc.nextLine();
			Utility.cls();
			login(u);
		}
		catch(UserAlreadyAvailableException e){
			Utility.cls();
			System.out.println("\n\t\t\tUser Name Not Available\n\t\t\tPress Enter to Continue");
			sc.nextLine();
		}
		catch(AgeNotEligibleException e){
			Utility.cls();
			System.out.println("\n\t\t\tAge must be greater than 13\n\t\t\tPress Enter to Continue");
			sc.nextLine();
		}
		catch(FaceBookException e){
			Utility.cls();
			System.out.println("\n\t\t\t UnKnown Error\n\t\t\tPress Enter to Continue");
			sc.nextLine();
		}
	}
	private static void login(){
		Utility.cls();
		System.out.println("\t\t\t-------------------\n\t\t\tLogin Page\n\t\t\t-------------------");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Phone No or Email ID: ");
		String credentialId = sc.nextLine();
		System.out.println("Enter Password: ");
		String credentialPassword = sc.nextLine();
		User user = UserHandler.getAuthentication(credentialId,credentialPassword);
		if(user==null){
			System.out.println("\n\t\t\tLogin Failed Successfully\n\t\t\tPress Enter to Continue");
			sc.nextLine();
		}
		else{
			login(user);
		}
	}
	
	private static void login(User user){
		Scanner sc = new Scanner(System.in);
		outer: while(true){
			Utility.cls();
			System.out.println("\t\t\t-------------------\n\t\t\tFaceBook Home Page\n\t\t\t-------------------");
			System.out.println("\n\t\t\t1) View My Profile");
			System.out.println("\n\t\t\t2) Search FB Peer");
			System.out.println("\n\t\t\t3) View Pending Friend Request");
			System.out.println("\n\t\t\t4) Logout");
			System.out.println("\n\t\t\tEnter Your Choice:");
			int choice = sc.nextInt();
			switch(choice){
				case 1:user.viewProfile();break;
				case 2:UserHandler.searchUser(user);break;
				case 3:user.friend.viewRequests(user);break;
				case 4:break outer;
			}
		}
	}
}