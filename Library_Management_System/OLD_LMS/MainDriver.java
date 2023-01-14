import java.util.*;
class MainDriver{
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		Library lib = new Library("L-1",10);
		outer: while(true){
			System.out.println("Enter input: ");
			String s = sc.next();
			switch(s){
				case "add_book":lib.addBook(sc.nextLine().trim());break;
				case "borrow_book":lib.borrowBook(sc.nextLine().trim());break;
				case "borrow_book_copy":lib.borrowBookCopy(sc.nextLine().trim());break;
				case "return_book_copy":lib.returnBook(sc.nextLine().trim());break;
				case "exit":break outer;
			}
		}
	}
}