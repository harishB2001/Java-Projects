class BookCopy extends Book{
	
	String bookCopyId;
	BookCopy(String bookCopyId,String bookId,String title,String authors,String publishers){
		super(bookId,title,authors,publishers);
		this.bookCopyId = bookCopyId;
	}
	
	@Override
	public String toString(){
		String s = super.toString();
		return this.bookCopyId+" "+s;
	}
	
}