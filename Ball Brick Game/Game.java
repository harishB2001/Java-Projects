import java.util.*;
import java.io.*;
class Game{
	String brick[][];
	int ballCount;
	private int size;
	int ballPosition;
	int startPos;
	int endPos;
	String nextBase = "Right";
	boolean isBaseEligible = false;
	boolean gameStarted = true;
	int rBrickStrength  = 1;
	Scanner sc = null;
	{
	try{sc = new Scanner(new File("input.txt"));}
	catch(Exception e){}
	}
	Game(){
		System.out.println("Enter the size of NxN matrix: ");
		size = Integer.parseInt(sc.nextLine());
		brick = new String[size][size];
		for(int i = 0;i<size;i++)for(int j = 0;j<size;j++)brick[i][j]="";
		for(int i =0;i<size;i++)
		{	
			brick[size-1][i] = "G";
			brick[0][i] = "W";
			brick[i][0] = "W";
			brick[i][size-1] = "W";
			
		}
		setBallPosition(size/2);
		addBrick();
		System.out.println("Enter the ball count: ");
		int bc = sc.nextInt();
 		setBallCount(bc);
		startPos = size/2;
		endPos = size/2;
	}
	
	private void setBallPosition(int position){
		this.ballPosition = position;
		brick[size-1][ballPosition] = "o";
	}
	
	public void display(){
		for(int i = 0;i<size-1;i++){
			for(int j = 0;j<size;j++){
				if(brick[i][j].length()==0)
					System.out.print("   ");
				else if(brick[i][j].length()==1)
				System.out.print(brick[i][j]+"  ");
				else System.out.print(brick[i][j]+" ");
			}
			System.out.println();
		}
		for(int i = 0;i<size;i++){
			String s = brick[size-1][i];
			if(i==ballPosition)System.out.print("O  ");
			else if(i>=startPos && i<=endPos && isBaseEligible)System.out.print("_  ");
			else System.out.print("G  ");
		}
		System.out.println("\nBall Count is "+this.ballCount);
	}
	
	public void addBrick(){
		while(true){
			System.out.println("Enter the brick's position and the brick type: ");
			int i = sc.nextInt();
			int j = sc.nextInt();
			String type = sc.next();
			brick[i][j] = type;
			System.out.println("Do you want to Continue(Y or N)? ");
			if(sc.next().equalsIgnoreCase("N"))break;
		}
	}
	
	public void setBallCount(int count){
		this.ballCount = count;
	}
	
	public void play(){
		Scanner pc = new Scanner(System.in);
		while(ballCount!=0){
			if(isWin())break;
			if(!(gameStarted) &&!(shiftDown()))break;
			gameStarted = false;
			display();
			System.out.println("Enter the direction in which the ball need to traverse: ");
			String s = pc.next();
			switch(s){
				case "ST":straight();break;
				case "LD":leftDiagonal();break;
				case "RD":rightDiagonal();break;
			}
		}
		display();
		if(isWin())System.out.println("Hurray you win the game");
		else{
			System.out.println("Game Over");
		}
		
		
	}
	private void straight(){
		for(int i = size-2;i>=0;i--){
			if(!(brick[i][ballPosition].equals(""))){
			breakBrick(i,ballPosition);
			break;
			}
		}	
	}
	
	private void travelHorizontally(int i,int j){
		if(j==0){
			for(j = j+1;j<size;j++){
				if(!(brick[i][j].equals(""))){
					breakBrick(i,j);
					if(j<startPos || j>endPos)
					ballCount--;
					break;
				}
			}
		}
		else{
			for(j = j-1;j>=0;j--){
				if(!(brick[i][j].equals(""))){
					breakBrick(i,j);
					if(j<startPos || j>endPos)
						ballCount--;
					break;
				}
			}
		}
	}
	private void leftDiagonal(){
		int position = ballPosition;
		for(int i = size-2;i>=0;i--){
			position--;
			if(position==0){travelHorizontally(i,position);break;}
			else if(!(brick[i][position].equals(""))){
			breakBrick(i,position);
			break;
			}
		}
	}
	private void rightDiagonal(){
		int position = ballPosition;
		for(int i = size-2;i>=0;i--){
			position++;
			if(position==size-1){travelHorizontally(i,position);break;}
			else if(!(brick[i][position].equals(""))){
			breakBrick(i,position);
			break;
			}
		}
	}
	private void breakBrick(int i,int j){
		String s = brick[i][j].trim();
		if(s.equals("DE")){
			for(int k = 1;k<size-1;k++){
				breakBrick(i+1,j+1);
			brick[i][k] = "";
			}
			brick[size-1][ballPosition] = "G";
			brick[size-1][j] = "o";
			ballPosition = j;
			
		}
		
		else if(s.equals("DS")){
			
			if(!(brick[i][j-1].equals("W"))){
			breakBrick(i,j-1);
			brick[i][j-1] = "";}
			
			if(!(brick[i][j+1].equals("W"))){
				breakBrick(i,j+1);
			brick[i][j+1] = "";}
			
			if(!(brick[i-1][j].equals("W"))){
				breakBrick(i-1,j);
			brick[i-1][j] = "";}
		
			if(!(brick[i-1][j-1].equals("W"))){
				breakBrick(i-1,j-1);
			brick[i-1][j-1] = "";}
		
			if(!(brick[i-1][j+1].equals("W"))){
				breakBrick(i-1,j+1);
			brick[i-1][j+1] = "";}
			
			/*if(!(brick[i+1][j].equals("W")) && !(brick[i+1][j].equals("G")) && !(brick[i+1][j].equals("o"))){
				breakBrick(i+1,j);
			brick[i+1][j] = "";}
		
			if(!(brick[i+1][j-1].equals("W")) && !(brick[i+1][j-1].equals("G")) && !(brick[i+1][j-1].equals("o"))){
				breakBrick(i+1,j-1);
			brick[i+1][j-1] = "";}
		
			if(!(brick[i+1][j+1].equals("W")) && !(brick[i+1][j+1].equals("G")) && !(brick[i+1][j+1].equals("o"))){
				breakBrick(i+1,j+1);
			brick[i+1][j+1] = "";}*/
			
			brick[i][j] = "";
			ballPosition = j;
		
		}else if(s.equals("B")){
			brick[i][j] = "";
			isBaseEligible = true;
			if(nextBase.equals("Right")){
				nextBase = "Left";
				endPos++;
				ballPosition = j;
			}
			else if(nextBase.equals("Left")){
				nextBase = "Right";
				startPos--;
				ballPosition = j;
			}
			
		}
		else if(s.equals("N")){
			
			if(!(brick[i-1][j].equals("W"))){
				breakBrick(i-1,j);
				brick[i-1][j] = "";
			}
			brick[i][j] = "";
		}
		else if(s.equals("S")){
			
			if(!(brick[i+1][j].equals("G"))){
				breakBrick(i+1,j);
				brick[i+1][j] = "";
			}
			brick[i][j] = "";
		}
		else if(s.equals("E")){
			
			if(!(brick[i][j+1]).equals("W")){
				breakBrick(i,j+1);
				brick[i][j+1] = "";
			}
			brick[i][j] = "";
		}
		else if(s.equals("WE")){
			
			if(!(brick[i][j-1]).equals("W")){
				breakBrick(i,j-1);
				brick[i][j-1] = "";
			}
			brick[i][j] = "";
		}
		else if(s.equals("D")){
			ballCount++;
			brick[i][j] = "";
			ballPosition = j;
		}
		else if(s.equals("R")){
			rBrickStrength++;
			ballPosition = j;
		}
		
		else{
		try{
			int k =  Integer.parseInt(s);
			k--;
			if(k == 0)brick[i][j] = "";
				else brick[i][j] = ""+k;
				ballPosition = j;
		}
		catch(Exception e){
			
		}
		}
	}
		
	private boolean isWin(){
		for(int i = 1;i<size-1;i++){
			for(int j = 1;j<size-1;j++){
				if(!(brick[i][j].equals("")))
					return false;
			}
		}
		return true;
	}
	
	private boolean shiftDown(){
		for(int i = 1;i<size-1;i++)if(!(brick[size-2][i].equals("")))return false;
		for(int i = size-3;i>0;i--){
			for(int j = 1;j<size-1;j++){
				brick[i+1][j] = brick[i][j]; 
			}
		}
		for(int i = 1;i<size-1;i++)brick[1][i]="";
		return true;
	}
	public static void main(String args[]){
		Game game = new Game();
		game.play();
	}
}