import java.io.*;
class FileHandling{

	static private FileHandling handler = new FileHandling();

	static public FileHandling getObject(){
		return handler;
	}
	private FileHandling(){}

	public void writeObject(String fileName, Object obj,boolean flag){
		try{
			File file = new File(fileName);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file,flag);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
		}
		catch(Exception e){/*System.out.println(e);*/}
	}

	public Object readObject(String filePath){
		try{
			File file = new File(filePath);
			file.createNewFile();
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			return ois.readObject();	
		}
		catch(Exception e){}
		return null;
	}
}