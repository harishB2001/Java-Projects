class BlueTokens extends Tokens{
	BlueTokens(String pieceName){
		super(-1,-1,pieceName);
	}
	public void rePosition(int moves){
		if(isRepositionEligible(moves)){
			outer: for(int k = 1;k<=moves;k++){
			count++;
			switch(choice){
				case 1 : if(isStart){isStart = false;i=27;j=02;}else{i-=2;}if(i==19){choice = 2;isStart=true;}break;
				case 2 : if(isStart){isStart = false;i=17;j=11;}else{j-=2;}if(j==01){choice = 3;isStart=true;}break;
				case 3 : if(isStart){isStart = false;i=15;j=01;}else{i-=2;}if(i==13){choice = 4;isStart=true;}break;
				case 4 : if(isStart){isStart = false;i=13;j=03;}else{j+=2;}if(j==11){choice = 5;isStart=true;}break;
				case 5 : if(isStart){isStart = false;i=11;j=02;}else{i-=2;}if(i==01){choice = 6;isStart=true;}break;
				case 6 : if(isStart){isStart = false;i=01;j=04;}else{j+=2;}if(j==06){choice = 7;isStart=false;}break;
				case 7 : if(isStart){isStart = false;i=05;j=06;}else{i+=2;}if(i==11){choice = 8;isStart=true;}break;
				case 8 : if(isStart){isStart = false;i=13;j=15;}else{j+=2;}if(j==25){choice = 9;isStart=true;}break;         
				case 9 : if(isStart){isStart = false;i=15;j=25;}else{i+=2;}if(i==17){choice = 10;isStart=true;}break;
				case 10: if(isStart){isStart = false;i=17;j=23;}else{j-=2;}if(j==15){choice = 11;isStart=true;}break;
				case 11: if(isStart){isStart = false;i=19;j=06;}else{i+=2;}if(i==29){choice = 12;isStart=true;}break;
				case 12: if(isStart){isStart = false;i=29;j=04;}else{i-=2;}if(i==19){choice = 13;isStart=true;}break;
				case 13: this.isPocketed = true;i=100;j=100;break outer;
			}
		}
		}
	}
	
	public boolean isRepositionEligible(int moves){
		return this.count+moves<=57 && (!isPocketed);
	}
}