import java.util.*;
import java.util.regex.*;
class CustomerHandler{
	static ArrayList<Customer> customerList = new ArrayList<Customer>();
		
	static{
		readCustomerFromFile();
	}
	
	static ArrayList<Customer> getCustomerList(){
			return customerList;
	}
	
	static void writeAsNewFile(){
		FileHandling handler = FileHandling.getObject();
		handler.writeAsNewFile(customerList);
	}
	
	static Customer getCustomer(int custId,String password){
		for(Customer c:customerList){
			if(c.custId==custId && c.encryptedPwd.equals(password)){
				return c;
			}
		}
		return null;
	}
	
	static Customer getCustomer(long accountNo){
		for(Customer c:customerList){
			if(c.accountNo==accountNo){
				return c;
			}
		}
		return null;
	}
	
	static void readCustomerFromFile(){
		customerList = new ArrayList<Customer>();
		FileHandling handler = FileHandling.getObject();
		String temp = handler.readFile();
		if(temp!=""){
		String customer[] = temp.split("\n");
		for(String c:customer){
			Customer cust = new Customer(c);
			handler.createFile(cust);
			customerList.add(cust);
		}
		}
	}
	
	static void writeIntoFile(Customer c){
		FileHandling handler = FileHandling.getObject();
		handler.writeIntoFile(c.toString());
		handler.createFile(c);
		customerList.add(c);
	}
	
	static void createCustomer(){
		readCustomerFromFile();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name: ");
		String name = sc.nextLine();
		int custId = generateCustomerId();
		String password = passwordHandler(custId);
		long acc = generateAccountNo();
		Customer c = new Customer(custId,acc,name,10000.0f,password);
		System.out.println("Customer Id: "+c.custId);
		System.out.println("Accout No  : "+c.accountNo);
		writeIntoFile(c);
		Transaction.updateTransactionCount(c);
		int i = Transaction.getCount(c);
		Transaction.updateTransaction(c," "+i+" Opeaning "+10000+" "+c.balance);
	}
	
	private static int generateCustomerId(){
		if(customerList.size()==0)return 11;
		else return customerList.get(customerList.size()-1).custId+1;
	}
	
	private static long generateAccountNo(){
		if(customerList.size()==0)return 11011;
		else return customerList.get(customerList.size()-1).accountNo+1;
	}

	 static void topNCustomer(int n){
		Collections.sort(customerList,new CustomerSortByBalance());
		String s = "";
		for(int i = 0;i<n && i<customerList.size();i++){
			s += customerList.get(i)+"\n";
		}
		FileHandling handler = FileHandling.getObject();
		s = s+"**********************\n\n";
		handler.writeIntoFile("TOP_N.txt",s);
		Collections.sort(customerList,new CustomerSortById());
	}
	
	static boolean isEligibleforMaintainenceFee(int custId){
		Collections.sort(customerList,new CustomerSortByBalance());
		int i = 0;
		if(customerList.size()<=3){Collections.sort(customerList,new CustomerSortById());return false;}
		while(i<3){
			if(customerList.get(i).custId == custId){Collections.sort(customerList,new CustomerSortById());return false;}                        
			i++;
		}
		Collections.sort(customerList,new CustomerSortById());
		return true;
	}

	static void passwordChange(int custId,String password){
		
		if(Authentication.getAuthentication(customerList,custId,password)){
			String p = passwordHandler(custId);
			for(Customer c:customerList){
				if(custId == c.custId){
					c.encryptedPwd = p;
					writeAsNewFile();
				}
			}
			
		}
		else{
			System.out.println("Authentication error");
		}
	}


	private static String passwordHandler(int custId){
		Scanner sc = new Scanner(System.in);
		String password = "";
		while(true){
			System.out.println("Enter password of (2 numbers) (2 lower case) (2 upper case) and minimum six characters : ");
			password = sc.nextLine();
			if(!PasswordHandler.isValidPassword(custId,Customer.encryptPassword(password))){
				System.out.println("Your password matches with recently used password, please try a different password");
				continue;
			}
			String pattern[] = {"[0-9]","[a-z]","[A-Z]"};
			boolean flag = true;
			for(String s:pattern){
				Matcher matcher  = Pattern.compile(s).matcher(password);
				int temp = 0;
				while(matcher.find()){
					temp++;
				}
				if(temp>=2){flag = flag & true;}
				else flag = false;

			}
			if(flag)break;
		}
		
		while(true){
			System.out.println("Please Retype the correct password: ");
			String re = sc.nextLine();
			if(re.equals(password))
				break;
		}
		PasswordHandler.changePassword(custId,Customer.encryptPassword(password));
		return password;
	}
	static void getTransactionHistory(int custId,String password){
		if(Authentication.getAuthentication(customerList,custId,password)){
		FileHandling handler = FileHandling.getObject();
		String s = handler.readFile(""+custId+".txt");
		System.out.println("\n"+s+"\n");
		}
		else{
			System.out.println("Authentication error");
		}
	}
}