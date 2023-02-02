import java.util.*;
import java.io.*;
class Players implements Serializable{
	private static final long serialVersionUID = 12L;
	String name;
	ArrayList<Tokens> tokensList = new ArrayList<Tokens>();
	String tokenColor;
	
	Players(String tokenColor,Tokens token){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Player Name: ");
		this.name = sc.nextLine();
		this.tokenColor = tokenColor;
		populateTokens(token);
		System.out.println("Player "+name+" created with the color "+tokenColor);
	}
	
	private void populateTokens(Tokens t){
		for(int i=1;i<=4;i++){
			Tokens token = (Tokens)t.clone();token.pieceName+=i;
			tokensList.add(token);}
	}
	
	public String getValidPiece(){
		String str = "";
		for(Tokens token:tokensList)
			if(!token.isPocketed)str+=token.toString()+" ";
		return str;
	}
	public String getValidPiece(int moves){
		String str = "";
		for(Tokens token:tokensList){
			if(moves==1 && token.i==-1 &&token.j==-1){str+=token.toString()+" ";continue;}
			if(!token.isPocketed && token.isRepositionEligible(moves) && token.i!=-1 && token.j!=-1)str+=token.toString()+" ";
		}
		return str.trim();
	}
	public Tokens getToken(String piece){
		char numb = piece.charAt(0);
		switch(numb){
			case '1':return tokensList.get(0);
			case '2':return tokensList.get(1);
			case '3':return tokensList.get(2);
			case '4':return tokensList.get(3);
		}
		return null;
	}
	
	private int pocketCount(){
		int count = 0;
		for(Tokens token:tokensList)
			if(token.isPocketed)count++;
		return count;
	}
	
	public String[] getNameBoard(){
		String[] str=new String[4];
		str[0]= this.tokenColor.trim();
		str[1]=(this.name+" : "+pocketCount()).trim();
		String line_1 = "";
		String line_2 = "";
		boolean isHalved = false;
		int i=0;
		for(Tokens tokens:tokensList){
			if(isHalved && tokens.i==-1 && tokens.j==-1)
				line_2 += tokens.toString()+" ";
			else if(tokens.i==-1 && tokens.j==-1){
				i++;
				if(tokensList.size()/2==i)
					isHalved = true;
				line_1 += tokens.toString()+" ";
			}
		}
		str[2] =line_1.trim();str[3]=line_2.trim();
		return str;
	}
	@Override
	public String toString(){
		return this.name+" ( "+tokenColor+" )";
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj==null)return false;
		Players p = (Players)obj;
		return this.tokenColor.equals(p.tokenColor);
	}
	
}