import java.util.Scanner;
class MainDriver{
	static String s = "";
	static FileHandling fileHandler = FileHandling.getObject();
	public static void main(String args[]){
		Match m = new Match();
		Scanner sc = new Scanner(System.in);
		
		s = sc.nextLine();
		fileHandler.writeIntoFile(s);
		int snakes = Integer.parseInt(s);
		for(int i=0;i<snakes;i++){
			
			int j = sc.nextInt();
			int k = Integer.parseInt(sc.nextLine().trim());
			s = ""+j+" "+k;
			fileHandler.writeIntoFile(s);
			m.setSnake(j,k);
		}
		
		s = sc.nextLine();
		fileHandler.writeIntoFile(s);
		int ladder = Integer.parseInt(s);
		for(int i=0;i<ladder;i++){
			int j = sc.nextInt();
			int k = Integer.parseInt(sc.nextLine().trim()); 
			s = ""+j+" "+k;
			fileHandler.writeIntoFile(s);
			
			m.setLadder(j,k);
		}
		
		s = sc.nextLine();
		fileHandler.writeIntoFile(s);
		int pc = Integer.parseInt(s);
		m.player = new Player[pc];
		
		for(int i= 0 ;i<pc;i++){
			s = sc.nextLine();
			fileHandler.writeIntoFile(s);
				m.player[i]  = new Player(s,0,0);
		}
		System.out.println("1 for automation \n2 for Manual Mode and other number for exit");
		switch(Integer.parseInt(sc.nextLine())){
			case 1:	m.play();break;
			case 2: m.userInputPlay();break;
			default:System.out.println("Exiting");
				
		}
		
	}
}