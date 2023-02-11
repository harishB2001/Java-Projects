import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
	
class Game{
	String direction = "d";
	char board[][] = new char[30][150];
	LinkedList<int[]> snakePath = new LinkedList<>();
	ArrayList<Character>snakeBody= new ArrayList<>();
	int fruitPosition[] = new int [2];
	boolean isFruitAvailable = false;
	int sleepTimer = 0;
	
	Game(){
	snakePath.add(new int[]{5,10});
	snakePath.add(new int[]{5,9});
	snakePath.add(new int[]{5,8});
	snakePath.add(new int[]{4,8});
	snakePath.add(new int[]{4,7});
	snakeBody.add('>');
	snakeBody.add('0');
	snakeBody.add('0');
	snakeBody.add('0');
	snakeBody.add('0');
	}
	
	private void setFruit(){
		if(isFruitAvailable){board[fruitPosition[0]][fruitPosition[1]] = '@';return;}
		ArrayList<String>fruitPos = new ArrayList<>();
		for(int i  = 2;i<board.length-2;i++)
			for(int j = 2;j<board[i].length-2;j++)
				fruitPos.add(""+i+" "+j);
		for(int arr[]:snakePath)
			fruitPos.remove(""+arr[0]+" "+arr[1]);
		int d = (int)(System.currentTimeMillis()%fruitPos.size());
		String arr[] = fruitPos.get(d).split(" ");
		fruitPosition = new int[]{Integer.parseInt(arr[0].trim()),Integer.parseInt(arr[1].trim())};
		board[fruitPosition[0]][fruitPosition[1]] = '@';
		isFruitAvailable = true;
	}
	
	private void increaseSnakeSize(){
		int lastPath[] = snakePath.peekLast();
		int newTail[] = new int[]{lastPath[0],lastPath[1]};
		try{
			try{//top
				int arr[] = new int[]{newTail[0]-1,newTail[1]};
				char g = board[arr[0]][arr[1]];
				snakeBody.add('0');
				snakePath.add(arr);
				return;
			}catch(Exception e){}
			try{//bottom
				int arr[] = new int[]{newTail[0]+1,newTail[1]};
				char g = board[arr[0]][arr[1]];
				snakeBody.add('0');
				snakePath.add(arr);
				return;
			}catch(Exception e){}
			try{//left
				int arr[] = new int[]{newTail[0],newTail[1]-1};
				char g = board[arr[0]][arr[1]];
				snakeBody.add('0');
				snakePath.add(arr);
				return;
			}catch(Exception e){}
			try{//right
				int arr[] = new int[]{newTail[0],newTail[1]+1};
				char g = board[arr[0]][arr[1]];
				snakeBody.add('0');
				snakePath.add(arr);
				return;
			}catch(Exception e){}
			
			}
		catch(Exception e){
			
		}
	}
	private void isFruit(int i,int j)throws Exception{
		if(i==fruitPosition[0] && j ==fruitPosition[1]){increaseSnakeSize();isFruitAvailable = false;}
	}
	private void isHit(int i,int j)throws Exception{
		if(board[i][j]=='0')throw new Exception();
	}
	
	private void fillSnake(){
		for(int i = 0;i<board.length;i++)
			Arrays.fill(board[i],' ');
		for(int j =0;j<board[0].length;j+=3){
			board[0][j] = '-';
			board[board.length-1][j] = '-';
		}
		for(int i =0;i<board.length;i+=3){
			board[i][0] = '|';
			board[i][board[0].length-1] ='|';
		}
		int i = 0;
		for(int a[]:snakePath){
			board[a[0]][a[1]] = snakeBody.get(i++);
		}
		display();
	}
	
	private void crawl(String dir)throws Exception{
		switch(dir){
			case "w":{
				if(!dir.equals("s")){
					direction = dir;
					int a[] = snakePath.get(0);
					int nextArray[] = new int[]{a[0]-1,a[1]};
					isHit(nextArray[0],nextArray[1]);
					snakePath.add(0,nextArray);
					int d[] = snakePath.pollLast();
					board[d[0]][d[1]] = ' ';
					snakeBody.set(0,'A');
					isFruit(nextArray[0],nextArray[1]);
					sleepTimer = 200;
				}else{FileHandling.writeOutput("w");}
			}break;
			case "a":{
				if(!dir.equals("d")){
					direction = dir;
					int a[] = snakePath.get(0);
					int nextArray[] = new int[]{a[0],a[1]-1};
					isHit(nextArray[0],nextArray[1]);
					snakePath.add(0,nextArray);
					snakeBody.set(0,'<');
					snakePath.pollLast();
					isFruit(nextArray[0],nextArray[1]);
					sleepTimer = 0;
				}else{FileHandling.writeOutput("a");}
			}
			break;
			case "s":{
				if(!dir.equals("w")){
					direction = dir;
					int a[] = snakePath.get(0);
					int nextArray[] = new int[]{a[0]+1,a[1]};
					snakePath.add(0,nextArray);
					isHit(nextArray[0],nextArray[1]);
					int d[] = snakePath.pollLast();
					board[d[0]][d[1]] = ' ';
					snakeBody.set(0,'v');
					isFruit(nextArray[0],nextArray[1]);
					sleepTimer = 200;
				}else{FileHandling.writeOutput("s");}
			}
			break;
			case "d":{
				if(!dir.equals("a")){
					direction = dir;
					int a[] = snakePath.get(0);
					int nextArray[] = new int[]{a[0],a[1]+1};
					snakePath.add(0,nextArray);
					isHit(nextArray[0],nextArray[1]);
					snakePath.pollLast();
					snakeBody.set(0,'>');
					isFruit(nextArray[0],nextArray[1]);
					sleepTimer = 0;
				}else{FileHandling.writeOutput("d");}
			}
			break;
			default:{
				boolean b = false;
				b = b || dir.equals("w");
				b = b || dir.equals("a");
				b = b || dir.equals("s");
				b = b || dir.equals("d");
				if(b)direction = dir;
			}
		}
		fillSnake();
	}
	
	public void play()throws Exception{
		Scanner sc = new Scanner(System.in);
		while(true){
			String dir  = FileHandling.readInput();
			//String dir = sc.nextLine(); 
			crawl(dir);
		}
	}
	
	private void display(){
		
		setFruit();
		
		try{
			Thread.sleep(sleepTimer);
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
			System.out.println("\n\n\t\tScore: "+(snakeBody.size()-5));
			System.out.println("\t\tFruit Position: "+fruitPosition[0]+" "+fruitPosition[1]);
			System.out.println("\t\tHead Position: "+snakePath.get(0)[0]+" "+snakePath.get(0)[1]);
		}
		catch(Exception e){
			System.out.println(e);
		}
		System.out.println("\n\n");
		for(int i = 0;i<board.length;i++){
			System.out.print("\t\t");
			for(int j = 0;j<board[i].length;j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String args[])throws Exception{
		long start = System.currentTimeMillis();
		Game game = new Game();
		try{
			game.play();
		}
		catch(Exception e){
			game.display();
			System.out.println("\t\tGame Over");
		}
		finally{
			long end = System.currentTimeMillis();
			System.out.println("\n Total Time: "+((end-start)/1000)+" Second");
		}
	}

}
