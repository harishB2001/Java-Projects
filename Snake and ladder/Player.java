class Player{
	String name;
	int initPos;
	int finalPos;
	
	Player(String name,int initPos,int finalPos){
		this.name = name;
		this.initPos = initPos;
		this.finalPos = finalPos;
	}
	
	void changePosition(int finalPos){
		this.initPos = this.finalPos;
		this.finalPos = finalPos;
	}
}