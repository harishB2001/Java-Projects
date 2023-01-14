import java.util.Scanner;
class MainDriver{
	public static void main(String args[]){
		Operations op = null;
		Scanner sc = new Scanner(System.in);
		outer: while(true){
		System.out.println("  1) Create Account\n  2) ATM withdrawl\n  3) Deposit\n  4) Transfer\n  5) Top N\n  6) change password\n  7) Show Transaction\n  8) exit");
		switch(sc.nextInt()){
			
			case 1:CustomerHandler.createCustomer();break;
			
			case 2: //Atm  withdrawl
			System.out.println("Enter id, password, amount");
			 op = new Operations(sc.nextInt(),sc.next());
			try{
				op.atmWithdrawl(sc.nextFloat());
			}catch(Exception e){
				System.out.println(e);
			}
			break;
			
			case 3: //deposit
			System.out.println("Enter id, password, amount");
			 op = new Operations(sc.nextInt(),sc.next());
			op.deposit(sc.nextFloat());
			break;
			
			case 4://transfer
			System.out.println("Enter id, password, amount,beneficiary account number");
			 op = new Operations(sc.nextInt(),sc.next());
			 try{
			 op.transfer(sc.nextInt(),sc.nextLong());}
		catch(Exception e){
			System.out.println(e);
		}
			break;
			case 5: 
			System.out.println("Enter the top N");
			CustomerHandler.topNCustomer(sc.nextInt());break;
			case 6:
			System.out.println("Enter id, password");
			CustomerHandler.passwordChange(sc.nextInt(),sc.next());break;
			case 7:
			System.out.println("Enter id, password");
			CustomerHandler.getTransactionHistory(sc.nextInt(),sc.next());break;
			case 8:
			break outer;
			}
		}
	}
}