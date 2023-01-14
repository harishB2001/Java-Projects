import java.util.*;
class MainDriver{
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		Library lib = new Library("L-1",10);
		outer: while(true){
			System.out.println("Enter input: ");
			String s = sc.next();
			switch(s){
				case "create_library":lib = new Library(sc.next(),Integer.parseInt(sc.nextLine().trim()));break;
				case "create_user": User.addUser(sc.nextLine().trim());break;
				case "add_book":lib.addBook(sc.nextLine().trim());break;
				case "borrow_book":lib.borrowBook(sc.nextLine().trim());break;
				case "borrow_book_copy":lib.borrowBookCopy(sc.nextLine().trim());break;
				case "return_book_copy":lib.returnBook(sc.nextLine().trim());break;
				case "search":lib.search(sc.nextLine().trim());break;
				case "print_borrowed":lib.printBorrowed(sc.nextLine().trim());break;
				case "exit":break outer;
			}
		}
	}
}