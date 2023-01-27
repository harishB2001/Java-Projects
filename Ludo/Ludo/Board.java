import java.io.IOException;
import java.util.*;
import java.io.*;
class Board implements Serializable{
	private static final long serialVersionUID = 12L;
	private String board[][] =  new String[31][];
	private HashMap<String,String>safeAreaMap = new HashMap<>();
	private HashMap<String,String>safeAreaTokenMap = new HashMap<>();
	private ArrayList<Players> playersList=new ArrayList<>();
	private HashMap<String,String>tokenTeams = new HashMap<>();
	private int currentPlayer = 0;
	private boolean isMatchOver = false;
	private Players prePlayers =  null;
	Board(){
		for(int i = 0;i<=10;i+=2)board[i] = new String[]{"                          ","-------------","                            "};
		for(int i = 20;i<=30;i+=2)board[i]= new String[]{"                          ","-------------","                            "};
		for(int i = 1;i<=11;i+=2)board[i] = new String[]{"                          ","| ","  ","| ","  ","| ","  ","| ","                          "};
		for(int i = 19;i<=29;i+=2)board[i]= new String[]{"                          ","| ","  ","| ","  ","| ","  ","| ","                          "};
		for(int i = 13;i<=17;i+=2)board[i]= new String[]{"  | ","  ","| ","  ","| ","  ","| ","  ","| ","  ","| ","  ","| ","          ","| ","  ","| ","  ","| ","  ","| ","  ","| ","  ","| ","  ","| "};
		for(int i = 12;i<=18;i+=6)board[i]= new String[]{"  -------------------------------------------------------------"};
		for(int i = 14;i<=16;i+=2)board[i]= new String[]{"  -------------------------","           ","-------------------------"};
		safeAreaMap.put("S1","3,6");safeAreaTokenMap.put("S1","");
		safeAreaMap.put("S2","13,21");safeAreaTokenMap.put("S2","");
		safeAreaMap.put("S3","17,23");safeAreaTokenMap.put("S3","");
		safeAreaMap.put("S4","25,6");safeAreaTokenMap.put("S4","");
		safeAreaMap.put("S5","27,2");safeAreaTokenMap.put("S5","");
		safeAreaMap.put("S6","17,5");safeAreaTokenMap.put("S6","");
		safeAreaMap.put("S7","13,3");safeAreaTokenMap.put("S7","");
		safeAreaMap.put("S8","5,2");safeAreaTokenMap.put("S8","");
		Tokens token[] = new Tokens[]{new RedTokens("R"),new GreenTokens("G"),new YellowTokens("Y"),new BlueTokens("B")};
		String[] colorName = {"Red","Green","Yellow","Blue"};
		for(int i = 1;i<=4;i++)
			playersList.add(new Players(colorName[i-1],token[i-1]));
	}
	
	void setNameBoard(){
		int[] position = {7,0,8,0,9,0,10,0,
		                  3,8,4,2,5,8,6,2,
						  19,8,20,2,21,8,22,2,
						  23,0,24,0,25,0,26,0,27,0};
		int i = -1;
		for(Players p:playersList){
			String[] str = p.getNameBoard();
			for(String s:str){
				int width = 26;
				int padSize = width-s.length();
				int padStart =s.length()+padSize/2;
				s = String.format("%"+padStart+"s",s);
				s = String.format("%-"+width+"s",s);
				board[position[i+1]][position[i+2]] = s;
				i+=2;
			}
		}
	}
	void displayBoard(){
		setSafeArea();
		setNameBoard();
		cls();
		System.out.println("\n");
		for(int i = 0;i<board.length;i++){
			for(int j = 0;j<board[i].length;j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		for(String key:safeAreaTokenMap.keySet()) if(!safeAreaTokenMap.get(key).equals(""))System.out.println(key+" : "+safeAreaTokenMap.get(key));
		for(String key:tokenTeams.keySet())System.out.println(key+" : "+tokenTeams.get(key));
	}
	private void cls(){
		try{
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	private void clearToken(Tokens token){
		int i=token.i,j=token.j;
		if(i!=-1 && j!=-1)
		if(board[i][j].charAt(0)=='S'){
			String safeArea = board[i][j];
			String value = safeAreaTokenMap.get(safeArea);
			value = value.replace(token.toString(),"").trim();
			safeAreaTokenMap.put(safeArea,value);
			return;
		}
		else if(board[i][j].charAt(0)=='T'){
			String existingTeamName = board[i][j];
			String existingToken = tokenTeams.get(existingTeamName);
			existingToken = existingToken.replace(token.toString(),"").trim();
			if(existingToken.length()==2){board[i][j] = existingToken;tokenTeams.remove(existingTeamName);return;}
			else{ tokenTeams.put(existingTeamName,existingToken.trim());return;}
		}
		if(i!=-1 && j!=-1)
		board[i][j] = "  ";
	}
	private void setToken(Tokens token){
		grouping(token);
		int i=token.i,j=token.j;
		if(i!=-1 && j!=-1 && i!=100 && j!=100 && board[i][j].charAt(0)!='T')
			board[i][j]=token.toString();
	}
	private void setSafeArea(){
		for(String key:safeAreaMap.keySet()){
			String value = safeAreaMap.get(key);
			int i = Integer.parseInt(value.split(",")[0]);
			int j = Integer.parseInt(value.split(",")[1]);
			board[i][j] = key;
		}
	}
	private int rollDice(){
		return (int) Math.round(Math.random()*5+1);
	}
	
	private void changeNextPlayer(){
		currentPlayer = (currentPlayer+1)%playersList.size();
		Players p = playersList.get(currentPlayer);
		if(p.equals(prePlayers))isMatchOver = true;
		FileHandling handler = FileHandling.getObject();
		handler.writeObject("ludo.txt",this,false);
	}
	private void grouping(Tokens token){
		int i=token.i,j=token.j;
		if((i==100&&j==100)||(i==-1 &&j==-1)||board[i][j].equals("  "))return;
		if(board[i][j].charAt(0)=='S'){
			String key = board[i][j];
			String value = safeAreaTokenMap.get(key);
			value = value+" "+token.toString();
			safeAreaTokenMap.put(key,value.trim());
			return;
			}
			if(board[i][j].charAt(0)=='T'){handleTeaming(token);return;}
			if(board[i][j].charAt(0)!=token.pieceName.charAt(0)){
				killToken(board[i][j]);return;}
			if(board[i][j].charAt(0)==token.pieceName.charAt(0)){formTeam(token);return;}
		
	}
	
	private void formTeam(Tokens t){
		int nextTeamNumber  = 1;
		while(true){
			if(!tokenTeams.containsKey("T"+nextTeamNumber)){break;}
			nextTeamNumber++;
		}
		String teamName = "T"+nextTeamNumber;
		String teamTokens = board[t.i][t.j]+" "+t.pieceName;
		tokenTeams.put(teamName,teamTokens);
		board[t.i][t.j] = teamName;
	}
	private void handleTeaming(Tokens token){
		String existingTeamName  = board[token.i][token.j];
		String existingTokens = tokenTeams.get(existingTeamName);
		char p = token.pieceName.charAt(0);
		if(existingTokens.contains(""+p)){
			existingTokens = existingTokens+" "+token.pieceName;
			tokenTeams.put(existingTeamName,existingTokens.trim());
		}else{
			String str[] = existingTokens.split(" ");
			for(String s:str)killToken(s);
			tokenTeams.remove(existingTeamName);
			board[token.i][token.j] = token.pieceName;
		}
	}
	private void killToken(String pieceName){
		char p = pieceName.charAt(0);
		int n = Integer.parseInt(""+pieceName.charAt(1));
		switch(p){
			case 'R':playersList.get(0).tokensList.get(n-1).resetToken();break;
			case 'G':playersList.get(1).tokensList.get(n-1).resetToken();break;
			case 'Y':playersList.get(2).tokensList.get(n-1).resetToken();break;
			case 'B':playersList.get(3).tokensList.get(n-1).resetToken();break;
		}
	}
	public void play(){
		Scanner sc = new Scanner(System.in);
		while(!isMatchOver){
			displayBoard();
			Players players = playersList.get(currentPlayer);
			String validPiece = players.getValidPiece();
			if(validPiece.equals("")){changeNextPlayer();continue;}
			else{
				System.out.println(players+" Press Enter to roll the Dice");
				sc.nextLine();
				int dice = rollDice();
				System.out.println(players.toString()+" rolled a "+dice);
				validPiece = players.getValidPiece(dice);
				if(validPiece.equals("")){prePlayers = players;System.out.println("No Valid Moves Available press Enter to continue");sc.nextLine();changeNextPlayer();continue;}
				while(true){
					System.out.println("Enter Your Piece from "+validPiece);
					String piece = sc.nextLine().trim();
					if(validPiece.contains(piece) && !piece.equals("")){
						Tokens token = players.getToken(piece);
						clearToken(token);
						token.rePosition(dice);
						setToken(token);
						prePlayers = players;
						changeNextPlayer();
						break;
					}
				}
			}
			
		}
		System.out.println("Match Over");
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Reload Game If available Y/N: ");
		String s = sc.nextLine();
		Board board = null;
		if(s.equalsIgnoreCase("Y")){
			FileHandling handler = FileHandling.getObject();
			if(handler.readObject("ludo.txt")==null){
				board = new Board();
				board.play();
			}else{
				board = (Board)handler.readObject("ludo.txt");
				board.play();
			}
		}else{
			board = new Board();
			board.play();
		}
	}
}