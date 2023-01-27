class Main{
public static void main(String args[]) throws Exception{
	String query[] = {"color 01","color 02","color 03"};
	for(String a:query){
		cls(a);
		System.out.println(a);
		Thread.sleep(1000);
	}
}

static void cls(String query){
		try{
			new ProcessBuilder("cmd","/c",query).inheritIO().start().waitFor();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	}