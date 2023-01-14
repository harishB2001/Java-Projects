class BookHandler{
	
	String userId;
	String dueDate;
	BookCopy bookCopy;
	int rackNo;
	
	BookHandler(String s){
		String str[] = s.split(" ");
		System.out.println(str.length);
		if(str.length==6){
			bookCopy = new BookCopy(str[4],str[0],str[1],str[2],str[3]);
			this.rackNo = Integer.parseInt(str[5]);
			userId = "";
			dueDate = "";
		}
		else //if(str.length==8)
		{
			bookCopy = new BookCopy(str[4],str[0],str[1],str[2],str[3]);
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
		if(rackNo==-1)User.updateUserMap(this.userId,false);
		else User.updateUserMap(this.userId,true);
		this.userId = userId;
		this.dueDate = dueDate;
		this.rackNo = rackNo;
	}
}