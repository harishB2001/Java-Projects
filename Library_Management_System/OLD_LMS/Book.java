class Book{
	int bookId;
	String title;
	String authors[];
	String publishers[];
	
	Book(String bookId,String title,String authors,String publishers){
		this.bookId = Integer.parseInt(bookId);
		this.title = title;
		this.authors = authors.split(",");
		this.publishers = publishers.split(",");
	}
	
	@Override 
	public String toString(){
		String authors = "";
		String publishers = "";
		for(String a:this.authors)
			authors+=a+",";
		for(String p:this.publishers)
			publishers+=p+",";
		authors = authors.substring(0,authors.length()-1);
		publishers = publishers.substring(0,publishers.length()-1);
		return this.bookId+" "+this.title+" "+authors+" "+publishers;
	}
}