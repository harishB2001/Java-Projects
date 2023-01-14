import java.util.*;
class Library{
	String libraryId;
	ArrayList<BookHandler>bookHandlerList = new ArrayList<>();
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
		if(bookHandlerList.size()+copies.length<=MAX_COUNT){
			for(int i = 0;i<copies.length;i++){
				for(BookHandler bh:bookHandlerList){
					if(bh.rackNo == nextNum){nextNum++;continue;}
				}
				BookHandler bh = new BookHandler(copies[i]+" "+str[0]+" "+str[1]+" "+str[2]+" "+str[3]+" "+nextNum);
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
		int i = MAX_COUNT;
		int tempI = 0;
		int index = i;
		boolean isBookAvailable = false;
		for(BookHandler bh:bookHandlerList){			
			if(bh.rackNo!=-1 &&bh.bookCopy.bookId==bookId){
				isBookAvailable = true;
				if(index>bh.rackNo)index = tempI;
			}
			tempI++;
		}
		if(isBookAvailable)
			borrowBook(index,dueDate,userId);
		else System.out.println("Book is Not Available");}
		else
			System.out.println("Book Borrow is OverLimited or User Id not Found");		
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
		for(int i = 0;i<bookHandlerList.size();i++)
		for(BookHandler bh:bookHandlerList){
				if(bh.rackNo == nextNum){nextNum++;continue;}
			}
		BookHandler bh = bookHandlerList.get(index);
		bh.setBookState(bh.userId,bh.dueDate,nextNum);
		System.out.println("Returened Book Copy "+bh.bookCopy.bookCopyId+" and added to rack: "+nextNum);
		writeFile();
		}
		else 
		System.out.println("Invalid Book ID");
	}
	
	public void writeFile(){
		String s = ""+MAX_COUNT+"\n";
		for(BookHandler bh:bookHandlerList)
			s+=bh.toString()+"\n";
		s = s.substring(0,s.length()-1);
		FileHandling handler = FileHandling.getObject();
		handler.writeFile(s,""+libraryId+".txt",false);
	}
	
	public void readFile(){
		bookHandlerList = new LinkedList<>();
		FileHandling handler = FileHandling.getObject();
		String s = handler.readFile(""+this.libraryId+".txt");	
		String str[] = s.split("\n");
		MAX_COUNT = Integer.parseInt(str[0].trim());
		for(int i = 1;i<str.length;i++)
			if(str[i]!=""){
				BookHandler bh = new BookHandler(str[i]);
				bookHandlerList.add(bh);
			}
	}
	
	public void search(String s){
		String str[] = s.split(" ");
		switch(str[0]){
			case "book_id":
			for(BookHandler bh:bookHandlerList)
				if(bh.bookCopy.bookId == Integer.parseInt(str[1]))
					System.out.println("Book Copy: "+bh);
			break;
			case "author":
			for(BookHandler bh:bookHandlerList)
				if(bh.bookCopy.authors.contains(str[1]))
					System.out.println("Book Copy: "+bh);
			break;
			case "publisher":
			for(BookHandler bh:bookHandlerList)
				if(bh.bookCopy.publishers.contains(str[1]))
					System.out.println("Book Copy: "+bh);
			break;
		}
	}
	
	public void printBorrowed(String userId){
		for(BookHandler bh : bookHandlerList){
			if(bh.userId.equals(userId))
				System.out.println(bh);
		}
	}

}