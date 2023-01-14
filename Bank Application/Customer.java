import java.util.*;
class Customer {
	int custId;
	long accountNo;
	String name;
	float balance;
	String encryptedPwd;
	
	Customer(int custId, long accountNo,String name,float balance,String encryptedPwd){
			this.custId = custId;
			this.accountNo = accountNo;
			this.name = name;
			this.balance = balance;
			this.encryptedPwd = encryptedPwd;
	}
	
	Customer(String s){
		String c[] = s.split(" ");
		this.custId = Integer.parseInt(c[0]);
		this.accountNo = Long.parseLong(c[1]);
		this.name = c[2];
		this.balance = Float.parseFloat(c[3]);
		this.encryptedPwd = decryptPassword(c[4]);
	}
	
	 static String   decryptPassword(String s){
		String password = "";
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='a')
			{password +='z';continue;}
			if(s.charAt(i)=='A')
			{password +='Z';continue;}
			if(s.charAt(i)=='0')
			{password+='9';continue;}
		password+=(char)(s.charAt(i)-1);
		}
		return password;
	}
	
	static String  encryptPassword(String s){
		String password = "";
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='z')
			{password +='a';continue;}
			if(s.charAt(i)=='Z')
			{password +='A';continue;}
			if(s.charAt(i)=='9')
			{password+='0';continue;}
		password+=(char)(s.charAt(i)+1);
		}
		return password;
	}
	
	@Override
	public String toString(){
		return custId+" "+accountNo+" "+name+" "+balance+" "+encryptPassword(encryptedPwd);
	}
	
	@Override
	public boolean equals(Object c){
		Customer d = (Customer)c;
		return this.custId == d.custId;
	}
	
}

class CustomerSortByBalance implements Comparator<Customer>{
	@Override 
	public int compare(Customer a, Customer b){
		return (int)(b.balance-a.balance);
	}
}

class CustomerSortById implements Comparator<Customer>{
	@Override 
	public int compare(Customer a, Customer b){
		return (int)(a.custId-b.custId);
	}
}