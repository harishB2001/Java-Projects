import java.util.*;
class Operations{
	int custId;
	String password;
	
	Operations(int custId,String password){
		this.custId = custId;
		this.password = password;
	}
	
	static void getMaintain(Customer c){
		if(Transaction.getCount(c)%10 ==0 && CustomerHandler.isEligibleforMaintainenceFee(c.custId)){
					c.balance-=100;
					CustomerHandler.writeAsNewFile();
				Transaction.updateTransactionCount(c);
				int i = Transaction.getCount(c);
				Transaction.updateTransaction(c," "+i+" Maintainence Fee  100  "+c.balance);
				}
	}
	void atmWithdrawl(float amount)throws InsufficientBalanceException{
		if(getAuthentication()){
			Customer c = CustomerHandler.getCustomer(custId,password);
			if(c.balance>=amount+1000){
				c.balance-=amount;
				System.out.println("Successfully Withdrawn the amount from ATM");
				CustomerHandler.writeAsNewFile();
				Transaction.updateTransactionCount(c);
				int i = Transaction.getCount(c);
				Transaction.updateTransaction(c," "+i+" ATM withdrawl  "+amount+" "+c.balance);
				
				getMaintain(c);
			}
			else{
				throw new InsufficientBalanceException();
			}
		}else{
			System.out.println("Authentication Error");
		}
	}
	void deposit(float amount){
		if(getAuthentication()){
			Customer c = CustomerHandler.getCustomer(custId,password);
				c.balance+=amount;
				System.out.println("Successfully Deposited");
				CustomerHandler.writeAsNewFile();
				Transaction.updateTransactionCount(c);
				int i = Transaction.getCount(c);
				Transaction.updateTransaction(c," "+i+" Deposit  "+amount+" "+c.balance);
				getMaintain(c);
		}else{
			System.out.println("Authentication Error");
		}
	}
	void transfer(int amount,long beneficiaryAccountNumber)throws InsufficientBalanceException{
		if(getAuthentication()){
			Customer c = CustomerHandler.getCustomer(custId,password);
			Customer beneficiary = CustomerHandler.getCustomer(beneficiaryAccountNumber);
			if(beneficiary==null){
				System.out.println("Beneficiary not found");
			}else{
				if(c.balance>=amount+1000){
				c.balance-=amount;
				beneficiary.balance+=amount;
				System.out.println("Successfully Amount Transferred");
				CustomerHandler.writeAsNewFile();
				Transaction.updateTransactionCount(c);
				Transaction.updateTransactionCount(beneficiary);
				int i = Transaction.getCount(c);
				Transaction.updateTransaction(c," "+i+" Transfer to "+beneficiary.accountNo+" "+amount+" "+c.balance);
				i = Transaction.getCount(beneficiary);
				Transaction.updateTransaction(beneficiary," "+i+" Transfer from "+c.accountNo+" "+amount+" "+beneficiary.balance);
				if(amount>=5000){
					c.balance-=10;
					CustomerHandler.writeAsNewFile();
					Transaction.updateTransactionCount(c);
					i = Transaction.getCount(c);
					Transaction.updateTransaction(c," "+i+" Operational Fee 10 "+c.balance);
				}
				getMaintain(c);
				getMaintain(beneficiary);
				
			}
			else{
				throw new InsufficientBalanceException();
			}
			}
		}else{
			System.out.println("Authentication Error");
		}
	}
	
	private boolean getAuthentication(){
		return Authentication.getAuthentication(CustomerHandler.getCustomerList(),this.custId,this.password);
	}
	
}