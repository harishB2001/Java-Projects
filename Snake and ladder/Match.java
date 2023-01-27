import java.util.HashMap;
import java.util.Scanner;
class Match{
	
	Player[] player;
	char board[][] = new char[10][10];
	HashMap<Integer,Integer> snake = new HashMap<>();
	HashMap<Integer,Integer> ladder = new HashMap<>();
	int nextPlayer = 0;
	int prevPlayer = -1;
	int prevDice = 0;
	static FileHandling fileHandler = FileHandling.getObject();
	static String s = "";
	
	Match(){
		for(int i=0;i<10;i++){
			for(int j =0;j<10;j++){
				board[i][j] = 'o';
			}
		}
	}
	
	void setSnake(Integer head,Integer tail){
		snake.put(head,tail);
	}

	void setLadder(Integer start,Integer end){
		ladder.put(start,end);
	}
	
	//positive inieger which return tail of snake, -1 if no snake
	private int checkSnake(int position){
		if(snake.containsKey(position))
			return snake.get(position);
		return -1;
	}
	
	private int checkLadder(int position){
		if(ladder.containsKey(position))
			return ladder.get(position);
		return -1;
	}
	
	private int rollDice(){
		return (int) Math.round(Math.random()*5+1);
	}
	
	private int nextPlayerPlay(boolean isAutomated,int num){
		boolean isSnake = true;boolean isLadder = true;
		Player p = player[nextPlayer];
		if(isAutomated)prevDice =  rollDice();
		else{prevDice = num;}
		int position = p.finalPos+prevDice;
		while(isSnake || isLadder){
			isLadder =  true;
			isSnake = true;
			if(checkLadder(position)==-1){isLadder = false;}else{position = checkLadder(position);}
			if(checkSnake(position)==-1){isSnake = false;}else{position = checkSnake(position);};
		}
		prevPlayer = nextPlayer;
		nextPlayer = (nextPlayer+1)%player.length;
		return position;
	}
	
	public void play(){
		while(true){
			int pos = nextPlayerPlay(true,-1);
			if(pos == 100)
			{
				Player p = player[prevPlayer];
				p.changePosition(pos);
				
				s = p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos;
				fileHandler.writeIntoFile(s);
				System.out.println(p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos);
				
				s = p.name+" won the match ";
				fileHandler.writeIntoFile(s);
				System.out.println(p.name+" won the match ");
				break;
			}
			else if(pos<=99){
				Player p = player[prevPlayer];
				p.changePosition(pos);
				
				s = p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos;
				fileHandler.writeIntoFile(s);
				System.out.println(p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos);
			}
		}
	}
	
	public void userInputPlay(){
		Scanner sc = new Scanner(System.in);
		int time = 0;
		while(true){
		Player p = player[nextPlayer];
		
		
		s = "chance for: "+p.name;
		fileHandler.writeIntoFile(s);
		System.out.println("chance for: "+p.name);
		
		s = sc.nextLine();
		fileHandler.writeIntoFile(s);
		int dice = Integer.parseInt(s);
		if(dice>=7 || dice<=0){
			
			s = "Try number within 1 to 6";
			fileHandler.writeIntoFile(s);
			System.out.println("Try number within 1 to 6");continue;
			}
		else if(dice==6 && time<2){
			time++;
			int pos = nextPlayerPlay(false,dice);
			
			if(pos == 100)
			{
				p = player[prevPlayer];
				p.changePosition(pos);
				
				s = p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos;
				fileHandler.writeIntoFile(s);
				System.out.println(p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos);
				
				s = p.name+" won the match ";
				fileHandler.writeIntoFile(s);
				System.out.println(p.name+" won the match ");
				break;
			}
			else if(pos>=101){
				if(time<2)
				nextPlayer = prevPlayer;
			continue;}
			p.changePosition(pos);
			
			s = p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos;
			fileHandler.writeIntoFile(s);
			System.out.println(p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos);
			
			if(time<2)
			nextPlayer = prevPlayer;
		}
		else{
			time = 0;
			int pos = nextPlayerPlay(false,dice);
			if(pos == 100)
			{
				p = player[prevPlayer];
				p.changePosition(pos);
				
				s = p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos;
				fileHandler.writeIntoFile(s);
				System.out.println(p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos);
				
				s = p.name+" won the match ";
				fileHandler.writeIntoFile(s);
				System.out.println(p.name+" won the match ");
				break;
			}
			else if(pos>=101)continue;
			p.changePosition(pos);
			
			s = p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos;
			fileHandler.writeIntoFile(s);
			System.out.println(p.name+" rolled a "+prevDice+" and moved from "+p.initPos+" to "+p.finalPos);
		}
		
		}
	
	}
	
	void display(){
		for(int key:snake.keySet()){
			int start = key;
			int end  =  snake.get(key);
			for()
		}
	}
}