import java.util.Scanner;
import java.io.*;
class FileHandling{
	static{
		writeOutput(" ");
	}
	public static String readInput(){
		String s = null;
		try{
			File file = new File("snakeInput.txt");
			FileInputStream fis = new FileInputStream(file);
			s = ""+(char)fis.read();
		}
		catch(Exception e){}
		finally{
			return s;
		}
	}
	public static void writeOutput(String input){
		try{
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
			File file = new File("snakeInput.txt");
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file,false);
			fos.write(input.getBytes());
		}catch(Exception e){
		}
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				writeOutput(""+sc.nextLine().charAt(0));
			}catch(Exception e){}
		}
	}
	
}