import java.util.*;
import java.io.*;
class Friend implements Serializable{
	private static final long serialVersionUID= 121L;
	HashSet<User> friendList;
	HashSet<User> requestList;
	HashSet<User> requestSentList;
	
	Friend(){
		friendList = new HashSet<>();
		requestList = new HashSet<>();
		requestSentList = new HashSet<>();
	}
	
	Set<User>getMutualFriends(User aUser){
		Set<User> fL = new HashSet<>(friendList);
		Set<User> fFL = new HashSet<>(aUser.friend.friendList);
		fL.retainAll(fFL);
		return fL;
	}
	//1 Friends
	//2 Accept Friend
	//3 Friend Request Sent
	//4 add friend
	int getFollowingStatus(User u){
		if(friendList.contains(u))return 1;
		if(requestList.contains(u))return 2;
		if(requestSentList.contains(u))return 3;
		return 4;
	}
	
	//send a friend request
	public void addFriend(User user,User aUser){
		aUser.friend.requestList.add(user);
		user.friend.requestSentList.add(aUser);
		addToFile();
	}
	
	//accept a friend frquest
	public void acceptFriend(User user,User aUser){
		user.friend.requestList.remove(aUser);
		user.friend.friendList.add(aUser);
		aUser.friend.requestSentList.remove(user);
		aUser.friend.friendList.add(user);
		addToFile();
	}
	
	//reject a friend request
	public void rejectRequest(User user,User aUser){
		user.friend.requestList.remove(aUser);
		aUser.friend.requestSentList.remove(user);
		addToFile();
	}
	
	//revoke friend request
	public void revokeRequest(User user,User aUser){
		user.friend.requestSentList.remove(aUser);
		aUser.friend.requestList.remove(user);
		addToFile();
	}
	
	public void removeAsFriend(User user,User aUser){
		user.friend.friendList.remove(aUser);
		aUser.friend.friendList.remove(user);
		addToFile();
	}
	
	public void viewRequests(User user){
		Scanner sc = new Scanner(System.in);
		Utility.cls();
		if(this.requestList.size()==0){
			System.out.println("\t\t\t-------------------\n\t\t\tNo Pending Requests\n\t\t\t-------------------");
			System.out.println("Press Enter to Continue");
			sc.nextLine();
		}
		else{
		System.out.println("\t\t\t-------------------\n\t\t\tPending Requests\n\t\t\t-------------------");
		int i = 1;
		ArrayList<User>requestList = new ArrayList<>(this.requestList);
		for(User u:requestList){
			System.out.println("\n\t"+i+++" )"+u);
		}
		System.out.println("\n\t\t\t 0) Exit or Enter Appropriate Number ");
		int choice = sc.nextInt();
		if(choice==0)return;
		else{
			user.viewProfile(requestList.get(choice-1));
		}
	}
	}
	
	public void viewMutualFriend(User user,User aUser){
		Scanner sc = new Scanner(System.in);
		ArrayList<User> mutualList =  new ArrayList<>(getMutualFriends(aUser));
		Utility.cls();
		if(mutualList.size()==0){
			System.out.println("\t\t\t-------------------\n\t\t\tNo Mutual Friends\n\t\t\t-------------------");
			System.out.println("Press Enter to Continue");
			sc.nextLine();
		}
		else{
		System.out.println("\t\t\t-------------------\n\t\t\tMutual Friends\n\t\t\t-------------------");
		int i = 1;
		for(User u:mutualList){
			System.out.println("\n\t"+i+++" )"+u);
		}
		System.out.println("\n\t\t\t 0) Exit or Enter Appropriate Number ");
		int choice = sc.nextInt();
		if(choice==0)return;
		else{
			user.viewProfile(mutualList.get(choice-1));
		}
	}
		
	}
	
	
	public void addToFile(){
		UserHandler.writeUser();
	}
}