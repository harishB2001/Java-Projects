import java.time.*;
import java.util.*;
import java.io.*;
class User implements Serializable{
	private static final long serialVersionUID= 121L;
	String name;
	String emailId;
	String phoneNo;
	LocalDate dob;
	Friend friend = new Friend();
	String password;
	String fbId = "";
	String gender = "";
	
	User()throws FaceBookException{
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t-------------------\n\t\t\twelcome to FaceBook\n\t\t\t-------------------");
		System.out.print("\n\n\t\t\tEnter Email-Id:\n\t\t");
		this.emailId = sc.nextLine();
		if(UserHandler.isUserAvailable(this.emailId))throw new UserAlreadyAvailableException();
		System.out.println("\n\t\t\tEnter Phone-No:");
		this.phoneNo = sc.nextLine();
		if(UserHandler.isUserAvailable(this.phoneNo))throw new UserAlreadyAvailableException();
		System.out.println("\n\t\t\tEnter Password:");
		this.password = sc.nextLine();
		System.out.println("\n\t\t\tEnter Display Name:");
		this.name = sc.nextLine();
		System.out.println("\n\t\t\tEnter DOB in yyyy mm dd :");
		this.dob = LocalDate.of(sc.nextInt(),sc.nextInt(),sc.nextInt());
		if(dob.isBefore(LocalDate.of(2010,01,01))) throw new AgeNotEligibleException();
		sc.nextLine();
		System.out.println("\n\t\t\tEnter Gender: ");
		this.gender = sc.nextLine();
		fbId = Utility.getNextFBID();
	}
	
	@Override
	public boolean equals(Object obj){
		User user = (User)obj;
		return this.emailId.equals(user.emailId) && this.phoneNo.equals(user.phoneNo);
	}
	
	void viewProfile(){
		Scanner sc = new Scanner(System.in);
		outer: while(true){
		Utility.cls();
		System.out.println("\t\t\t-------------------\n\t\t\tMy Profile\n\t\t\t-------------------");
		System.out.println("\n\t\t\t Name         : "+this.name);
		System.out.println("\n\t\t\t DOB          : "+this.dob);
		System.out.println("\n\t\t\t EmailId      : "+this.emailId);
		System.out.println("\n\t\t\t Phone        : "+this.phoneNo);
		System.out.println("\n\t\t\t Gender       : "+this.gender);
		System.out.println("\n\t\t\t Total Friends: "+this.friend.friendList.size());
		System.out.println("1) Edit Account\n2) Exit\nEnter Your choice ");
		switch(sc.nextInt()){
			case 1:editAccount();break;
			case 2:break outer;
		}
		
		}
		
	}

	void editAccount(){
		System.out.println("Accout Edit is yet to be implemented");
		try{Thread.sleep(5000);}catch(Exception e){}
	}
	
	void viewProfile(User user){
		Scanner sc = new Scanner(System.in);
		boolean flag  = true;
		outer: while(flag){
			Utility.cls();
			System.out.println("\t\t\t-------------------\n\t\t\t"+user.name+" Profile\n\t\t\t-------------------");
			System.out.println("\n\t\t\t Name          : "+user.name);
			System.out.println("\n\t\t\t DOB           : "+user.dob);
			System.out.println("\n\t\t\t Gender        : "+user.gender);
			System.out.println("\n\t\t\t Total Friends : "+user.friend.friendList.size());
			System.out.println("\n\t\t\t Mutual Friends: "+this.friend.getMutualFriends(user).size());
			int statusBoard = this.friend.getFollowingStatus(user);
			switch(statusBoard){
				case 1:System.out.println("\n\t\t\t Friends");flag = friendFunc(user);break;
				case 2:System.out.println("\n\t\t\t Accept/Decline");flag = acceptFriend(user);break;
				case 3:System.out.println("\n\t\t\t Friend Request Sent");flag = friendRequestSent(user);break;
				case 4:System.out.println("\n\t\t\t Add Friend");flag = addFriend(user);break;
			}
		}
	}
	
	private boolean friendFunc(User user){
		Scanner sc = new Scanner(System.in);
		System.out.println("1) Remove Friend\n2) See Mutual Friend List\n3)Go Back");
		int choice = sc.nextInt();
		switch(choice){
			case 1:this.friend.removeAsFriend(this,user);return true;
			case 2:this.friend.viewMutualFriend(this,user);return true;
			case 3:break;
		}
		return false;
	}
	
	private boolean acceptFriend(User user){
		Scanner sc = new Scanner(System.in);
		System.out.println("1) Accept Friend Request\n2) Reject Friend Request\n3) Go Back");
		int choice = sc.nextInt();
		switch(choice){
			case 1:this.friend.acceptFriend(this,user);return true;
			case 2:this.friend.rejectRequest(this,user);return true;
			case 3:break;
		}
		return false;
	}
	
	private boolean friendRequestSent(User user){
		Scanner sc = new Scanner(System.in);
		System.out.println("1) Revoke the Friend Request\n2) Go Back");
		int choice = sc.nextInt();
		switch(choice){
			case 1:this.friend.revokeRequest(this,user);return true;
			case 2:break;
		}
		return false;
	}
	
	private boolean addFriend(User user){
		Scanner sc = new Scanner(System.in);
		System.out.println("1) Add Friend\n2) Go Back");
		int choice = sc.nextInt();
		switch(choice){
			case 1:this.friend.addFriend(this,user);return true;
			case 2:break;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}