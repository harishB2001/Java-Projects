import java.io.*;
abstract class Tokens implements Cloneable,Serializable{
	private static final long serialVersionUID = 12L;
	int i;
	int j;
	int choice = 1;
	boolean isStart = true;
	int count = 0;
	String pieceName;
	boolean isPocketed = false;
	Tokens(int i,int j,String pieceName){
		this.i = i;
		this.j = j;
		this.pieceName=pieceName;
	}
	abstract public void rePosition(int moves);
	
	abstract public boolean isRepositionEligible(int moves);
	
	public void resetToken(){
		i = -1;
		j = -1;
		isStart = true;
		choice = 1;
		count = 0;
		isPocketed = false;
	}
	
	@Override
	public Object clone(){
		try{
			return super.clone();
		}
		catch(Exception e){}
		return null;
	}
	
	@Override
	public String toString(){
		return this.pieceName;
	}
}