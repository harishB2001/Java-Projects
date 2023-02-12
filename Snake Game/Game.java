import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
	
class Game{
	String direction = "d";
	char board[][] = new char[30][75];
	LinkedList<int[]> snakePath = new LinkedList<>();
	ArrayList<Character>snakeBody= new ArrayList<>();
	int fruitPosition[] = new int [2];
	boolean isFruitAvailable = false;
	int sleepTimer = 0;
	HashMap<int[],Character> name = new HashMap<>();
	
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
	setName();
	}
	
	private void setFruit(){
		if(isFruitAvailable){board[fruitPosition[0]][fruitPosition[1]] = '@';return;}
		ArrayList<String>fruitPos = new ArrayList<>();
		for(int i  = 2;i<board.length-2;i++)
			for(int j = 2;j<board[i].length-2;j++)
				fruitPos.add(""+i+" "+j);
		for(int arr[]:snakePath)
			fruitPos.remove(""+arr[0]+" "+arr[1]);
		for(int arr[]:name.keySet())
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
		for(int arr[]:name.keySet())
			if(arr[0]==i && arr[1]==j)throw new Exception();
		if(i==0 || i == board.length-1 || j ==0 || j==board[0].length-1)throw new Exception();
		
	}
	
	private void fillSnake(){
		for(int i = 0;i<board.length;i++)
			Arrays.fill(board[i],' ');
		for(int j =0;j<board[0].length;j++){
			board[0][j] = '-';
			board[board.length-1][j] = '-';
		}
		for(int i =0;i<board.length;i++){
			board[i][0] = '|';
			board[i][board[0].length-1] ='|';
		}
		int i = 0;
		for(int a[]:snakePath){
			board[a[0]][a[1]] = snakeBody.get(i++);
		}
		for(int arr[]:name.keySet()){
			board[arr[0]][arr[1]] = name.get(arr) ;
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
					//sleepTimer = 200;
				}else{FileHandling.writeOutput("w");}
			}break;
			case "a":{
				if(!dir.equals("d")){
					direction = dir;
					int a[] = snakePath.get(0);
					int nextArray[] = new int[]{a[0],a[1]-1 };
					isHit(nextArray[0],nextArray[1]);
					snakePath.add(0,nextArray);
					snakeBody.set(0,'<');
					snakePath.pollLast();
					isFruit(nextArray[0],nextArray[1]);
					//sleepTimer = 200;
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
					// sleepTimer = 200;
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
					// sleepTimer = 200;
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
		fillSnake();
		display();
		Scanner sc = new Scanner(System.in);
		while(true){
			//String dir  = FileHandling.readInput();
			
			String dir = sc.nextLine(); 
			for(int i = 0;i<dir.length();i++)
			crawl(""+dir.charAt(i));
		}
	}
	
	private void display(){
		
		setFruit();
		
		try{
		//	Thread.sleep(sleepTimer);
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
		try{
				try{new ProcessBuilder("cmd","/c","color 07").inheritIO().start().waitFor();}catch(Exception eee){}
			}
			catch(Exception e){}
		long start = System.currentTimeMillis();
		Game game = new Game();
		try{
			game.play();
		}
		catch(Exception e){
			try{new ProcessBuilder("cmd","/c","color 4").inheritIO().start().waitFor();}catch(Exception eee){}
			game.display();
			System.out.println("\t\tGame Over");
		}
		finally{
			long end = System.currentTimeMillis();
			System.out.println("\n Total Time: "+((end-start)/1000)+" Second");
		}
	}
	
	private void setName(){
		//Z
		name.put(new int[]{11,10},'-');
		name.put(new int[]{11,11},'-');
		name.put(new int[]{11,12},'-');
		name.put(new int[]{11,13},'-');
		name.put(new int[]{11,14},'-');
		name.put(new int[]{11,15},'-');
		name.put(new int[]{11,16},'+');
		
		name.put(new int[]{12,15},'/');
		name.put(new int[]{13,14},'/');
		name.put(new int[]{14,13},'/');
		name.put(new int[]{15,12},'/');
		name.put(new int[]{16,11},'/');
		name.put(new int[]{17,10},'/');
		
		name.put(new int[]{18,9},'+');
		name.put(new int[]{18,10},'-');
		name.put(new int[]{18,11},'-');
		name.put(new int[]{18,12},'-');
		name.put(new int[]{18,13},'-');
		name.put(new int[]{18,14},'-');
		name.put(new int[]{18,15},'-');
		
		//O
		name.put(new int[]{11,23},'-');
		name.put(new int[]{11,24},'-');
		name.put(new int[]{11,25},'-');
		
		
		name.put(new int[]{11,28},'-');
		name.put(new int[]{11,29},'-');
		name.put(new int[]{11,30},'-');
		
		name.put(new int[]{18,23},'-');
		name.put(new int[]{18,24},'-');
		name.put(new int[]{18,25},'-');
		
		name.put(new int[]{18,28},'-');
		name.put(new int[]{18,29},'-');
		name.put(new int[]{18,30},'-');
		
		name.put(new int[]{11,22},'+');
		name.put(new int[]{12,22},'|');
		name.put(new int[]{13,22},'|');
		name.put(new int[]{14,22},'|');
		name.put(new int[]{16,22},'|');
		name.put(new int[]{17,22},'|');
		name.put(new int[]{18,22},'+');
		
		name.put(new int[]{11,31},'+');
		name.put(new int[]{12,31},'|');
		name.put(new int[]{13,31},'|');
		name.put(new int[]{15,31},'|');
		name.put(new int[]{16,31},'|');
		name.put(new int[]{17,31},'|');
		name.put(new int[]{18,31},'+');
		
		
		//H
		name.put(new int[]{11,38},'|');
		name.put(new int[]{12,38},'|');
		name.put(new int[]{13,38},'|');
		name.put(new int[]{14,38},'+');
		name.put(new int[]{15,38},'|');
		name.put(new int[]{16,38},'|');
		name.put(new int[]{17,38},'|');
		name.put(new int[]{18,38},'|');
		
		name.put(new int[]{11,46},'|');
		name.put(new int[]{12,46},'|');
		name.put(new int[]{13,46},'|');
		name.put(new int[]{14,46},'+');
		name.put(new int[]{15,46},'|');
		name.put(new int[]{16,46},'|');
		name.put(new int[]{17,46},'|');
		name.put(new int[]{18,46},'|');
		
		name.put(new int[]{14,39},'-');
		name.put(new int[]{14,40},'-');
		name.put(new int[]{14,41},'-');
		name.put(new int[]{14,42},'-');
		name.put(new int[]{14,43},'-');
		name.put(new int[]{14,44},'-');
		name.put(new int[]{14,45},'-');
		
		//O
		name.put(new int[]{11,54},'-');
		name.put(new int[]{11,55},'-');
		name.put(new int[]{11,56},'-');
		
		
		name.put(new int[]{11,59},'-');
		name.put(new int[]{11,60},'-');
		name.put(new int[]{11,61},'-');
		
		name.put(new int[]{18,54},'-');
		name.put(new int[]{18,55},'-');
		name.put(new int[]{18,56},'-');
		
		name.put(new int[]{18,59},'-');
		name.put(new int[]{18,60},'-');
		name.put(new int[]{18,61},'-');
		
		name.put(new int[]{11,53},'+');
		name.put(new int[]{12,53},'|');
		name.put(new int[]{13,53},'|');
		name.put(new int[]{14,53},'|');
		name.put(new int[]{16,53},'|');
		name.put(new int[]{17,53},'|');
		name.put(new int[]{18,53},'+');
		
		name.put(new int[]{11,62},'+');
		name.put(new int[]{12,62},'|');
		name.put(new int[]{13,62},'|');
		name.put(new int[]{15,62},'|');
		name.put(new int[]{16,62},'|');
		name.put(new int[]{17,62},'|');
		name.put(new int[]{18,62},'+');
		
	}

}
