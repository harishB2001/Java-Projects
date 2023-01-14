import java.io.*;
import java.util.*;
class FileHandling{
	static File f;
	static FileOutputStream writer;
	static FileInputStream reader;
	static FileHandling fileHandler = new FileHandling();
	
	static FileHandling getObject(){
		try{
		f = new File("bank_db.txt");
		writer = new FileOutputStream(f,true);
		reader = new FileInputStream(f);
		f.createNewFile();}
		catch(Exception e){}
		return fileHandler;
	}
	
	public void writeAsNewFile(ArrayList<Customer>list){	
	FileOutputStream newWriter = null;
	String s = "";
	try{
	 newWriter = new FileOutputStream(f);
	 for(Customer c:list){
		s = c.toString()+"\n";
			newWriter.write(s.getBytes());
	}
	 }
	catch(Exception e){}
	
	}
	
	public void writeIntoFile(String s){	
	s = s+"\n";
	try{
	writer.write(s.getBytes());}
	catch(Exception e ){}
	}
	
	public void createFile(Customer c){
		String fileName =  ""+c.custId+".txt";
		try{
		File f = new File(fileName);
		if(f.createNewFile()){
			String s = "Customer Id: "+c.custId+"\n"+"Customer Name: "+c.name+"\n"+"Account Number: "+c.accountNo;
			writeIntoFile(fileName,s);
		}
		}
		catch(Exception e){}
	}
	
	public static void writeIntoFile(String fileName,String s){
		try{
			s = s+"\n";
			File f = new File(fileName);
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f,true);
			fos.write(s.getBytes());
		}
		catch(Exception e){}
	}
	
	public static void writeIntoFile(String fileName,String s,boolean flag){
		try{
			s = s+"\n";
			File f = new File(fileName);
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f,flag);
			fos.write(s.getBytes());
		}
		catch(Exception e){}
	}
	
	public String readFile(){
		String s = "";
		int i = 0;
		try{
		while((i=reader.read())!=-1){
			s += (char)i;
		}}
		catch(Exception e){}
		return s;
	}
	public void closeFile(){
		try{
			writer.close();
		}
		catch(Exception e){}
		
	}
	
	public String readFile(String fileName){
		String s = "";
		int i = 0;
		try{
			File f = new File(fileName);
			f.createNewFile();//this must work
			FileInputStream fis = new FileInputStream(f);
			while((i=fis.read())!=-1){
				s+=(char)i;
			}
		}
		catch(Exception e){
			
		}
		return s;
	}
	
	public void writeTransactionFile(HashMap<Integer,Integer> map){
		try{
		File f = new File("transaction.txt");
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(f,false);
		for(Integer c:map.keySet()){
		String s = c+" "+map.get(c)+"\n";
		fos.write(s.getBytes());
		}}
		catch(Exception e){}
	}
}