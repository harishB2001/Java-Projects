class BookHandler {
	
	String userId;
	String dueDate;
	BookCopy bookCopy;
	int rackNo;
	
	BookHandler(String s){
		String str[] = s.split(" ");
		bookCopy = new BookCopy(str[0],str[1],str[2],str[3],str[4]);
		if(str.length==6){
			this.rackNo = Integer.parseInt(str[5]);
			userId = "";
			dueDate = "";
		}
		else //if(str.length==8)
		{
			this.rackNo = Integer.parseInt(str[5]);
			userId = str[6];
			dueDate = str[7];
		}
	}
	
	@Override
	public String toString(){
		String s = bookCopy.toString();
		return s+" "+rackNo+" "+userId+" "+dueDate;
	}
	
	public void setBookState(String userId,String dueDate,int rackNo){
		if(rackNo==-1){
			User.updateUserMap(userId.trim(),false);
			this.userId = userId;
			this.rackNo = rackNo;
			this.dueDate = dueDate;
			}
		else{
			User.updateUserMap(userId.trim(),true);
			this.userId = "";
			this.dueDate = "";
			this.rackNo = rackNo;
		}
	}
}