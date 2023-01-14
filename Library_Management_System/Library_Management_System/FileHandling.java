import java.io.*;
class FileHandling{
	
	static private FileHandling handler = new FileHandling();
	
	static public FileHandling getObject(){
		return handler;
	}
	private FileHandling(){}
	
	public void writeFile(String s,String fileName,boolean flag){
		try{
			File file = new File(fileName);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file,flag);
			fos.write(s.getBytes());
		}
		catch(Exception e){
			
		}
	}
	
	public String readFile(String fileName){
		String s = "";
		try{
			File file = new File(fileName);
			file.createNewFile();
			FileInputStream fis = new FileInputStream(file);
			int i;
			
			while((i=fis.read())!=-1){
				s+=(char)i;
			}
		}
		catch(Exception e){}
		return s;
	}
}