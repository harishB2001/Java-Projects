class Book{
	int bookId;
	String title;
	String authors;
	String publishers;
	
	Book(String bookId,String title,String authors,String publishers){
		this.bookId = Integer.parseInt(bookId);
		this.title = title;
		this.authors = authors.trim();
		this.publishers = publishers.trim();
	}
	
	@Override 
	public String toString(){
		return this.bookId+" "+this.title+" "+this.authors+" "+this.publishers;
	}
}