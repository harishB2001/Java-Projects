import java.util.*;
class Library{
	String libraryId;
	LinkedList<BookHandler>bookHandlerList = new LinkedList<>();
	private int MAX_COUNT = 0;
	Library(String libraryId,int maxCount){
		this.libraryId = libraryId;
		this.MAX_COUNT = maxCount;
		this.readFile();
	}
	
	Library(String libraryId){
		this.libraryId = libraryId;
		this.readFile();
	}
	
	public void addBook(String s){
		int nextNum = 1;
		String racks = "";
		String str[]=s.split(" ");
		String copies[] =str[4].split(",");
		if(bookHandlerList.size()+copies.length<MAX_COUNT){
			for(int i = 0;i<copies.length;i++){
				for(BookHandler bh:bookHandlerList){
					if(bh.rackNo == nextNum){nextNum++;continue;}
				}
				BookHandler bh = new BookHandler(str[0]+" "+str[1]+" "+str[2]+" "+str[3]+" "+copies[i]+" "+nextNum);
				bookHandlerList.add(bh);
				racks+=nextNum;
				nextNum = 1;
			}
			writeFile();
			System.out.println("Added Books to racks: "+racks);
		}
		else{
			System.out.println("Rack Not Available");
		}
	}
	private void borrowBook(int i,String dueDate,String userId){
			BookHandler bh = bookHandlerList.get(i);
			System.out.println("Book borrowed from rack: "+bh.rackNo);
			bh.setBookState(userId,dueDate,-1);
			writeFile();
	}
	public void borrowBook(String s){
		String str[] = s.split(" ");
		int bookId = Integer.parseInt(str[0]);
		String userId = str[1];
		String dueDate = str[2];
		//check if user is allowed to borrow books//
		if(User.isEligible(userId)){
		int i = 0;
		boolean isBookAvailable = false;
		for(BookHandler bh:bookHandlerList){
			if(bh.rackNo!=-1 &&bh.bookCopy.bookId==bookId){
				isBookAvailable = true;
				break;
			}
			i++;
		}
		if(isBookAvailable)
			borrowBook(i,dueDate,userId);
		else System.out.println("Book is Not Available");}
		else
			System.out.println("Book Borrow is OverLimited");		
	}
	
	public void borrowBookCopy(String s){
		
		String str[] = s.split(" ");
		String bookCopyId = str[0];
		String userId = str[1];
		String dueDate = str[2];
		if(User.isEligible(userId)){
		int i = 0;
		boolean isBookAvailable = false;
		for(BookHandler bh:bookHandlerList){
			if(bh.rackNo!=-1 &&bh.bookCopy.bookCopyId.equals(bookCopyId)){
				isBookAvailable = true;
				break;
			}
			i++;
		}
		if(isBookAvailable)
			borrowBook(i,dueDate,userId);
			else System.out.println("Book is Not Available");}
		else
			System.out.println("Book Borrow is OverLimited");
	}
	
	public void returnBook(String s){
		boolean isValid = false;
		int index = 0;
		for(BookHandler bh:bookHandlerList){
			if(bh.bookCopy.bookCopyId.equals(s) && bh.rackNo==-1){
				isValid = true;
				break;
			}
			index++;
		}
		if(isValid){
			int nextNum = 1;
		for(BookHandler bh:bookHandlerList){
				if(bh.rackNo == nextNum){nextNum++;continue;}
			}
		BookHandler bh = bookHandlerList.get(index);
		bh.setBookState("","",nextNum);
		writeFile();
		}
		else 
		System.out.println("Invalid Book ID");
	}
	
	public void writeFile(){
		String s = "";
		for(BookHandler bh:bookHandlerList)
			s+=bh.toString()+"\n";
		s = s.substring(0,s.length()-1);
		FileHandling handler = FileHandling.getObject();
		handler.writeFile(s,""+libraryId+".txt",false);
	}
	
	public void readFile(){
		FileHandling handler = FileHandling.getObject();
		String s = handler.readFile(""+this.libraryId+".txt");	
		String str[] = s.split("\n");
		if(MAX_COUNT==0)MAX_COUNT = str.length;
		for(int i = 0;i<str.length;i++)
			if(str[i]!=""){
				BookHandler bh = new BookHandler(str[i]);
				bookHandlerList.add(bh);
			}
	}
}