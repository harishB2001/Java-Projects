import java.io.*;
class FileHandling{
	static File f;
	static FileOutputStream writer;
	static FileHandling fileHandler = new FileHandling();
	
	static FileHandling getObject(){
		try{
		f = new File("Snake and Ladder.txt");
		writer = new FileOutputStream(f,true);
		f.createNewFile();}
		catch(Exception e){}
		return fileHandler;
	}
	
	public void writeIntoFile(String s){	
	s = s+"\n";
	try{
	writer.write(s.getBytes());}
	catch(Exception e ){}
	}
	public void closeFile(){
		try{
			writer.close();
		}
		catch(Exception e){}
		
	}
}