import java.util.*;
class Balloon{
	
	char matrix[][];
	int rows;
	int columns;
	
	Balloon(){
		System.out.println("Enter the matrix size(m*n): ");
		Scanner sc =  new Scanner(System.in);
		this.rows = sc.nextInt();
		this.columns = sc.nextInt();
		matrix = new char[rows][columns];
		for(int i = 0;i<rows;i++){
			char temp[] = new char[columns];
			Arrays.fill(temp,'_');
			matrix[i] = temp;
		}
		
	}
	
	private void burstBalloon(){
		outer : for(int c = 0;c<columns;c++){
			char d = matrix[0][c];
			for(int r = 0;r<rows;r++){
				if(matrix[r][c]!=d)continue outer;
			}
			for(int r = 0;r<rows;r++)
				matrix[r][c] = '_';
		}
	}
	
	private boolean terminateGamePlay(){
		for(int j = 0;j<columns;j++){
			if(matrix[0][j]!='_')return true;
		}
		return false;
	}
	
	
	private void display(){
		System.out.println("Contents of the matrix: ");
		for(int i=0;i<rows;i++){
			for(int j =0;j<columns;j++){
				System.out.print(" "+matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	private void balloonDrop(int col,char color){
		for(int i = rows-1;i>=0;i--){
			for(int c = 0;c<columns;c++){
				if(matrix[i][col]=='_'){matrix[i][col] = color;return;}
				if(matrix[i][c]=='_'){matrix[i][c] = color;return;}
				}
			}
	}
	
	public void play(){
		Scanner sc = new Scanner(System.in);
		while(true){
			display();
			System.out.println("Enter the Column Number: ");
			int c = sc.nextInt()-1;
				burstBalloon();
				System.out.println("Enter the Color of the balloon: ");
				char color = sc.next().charAt(0);
				balloonDrop(c,color);
			System.out.println("Do you wish to continue(Y/N)");
			if(sc.next().equals("Y")){
			}
			else{
				System.out.println("Program Stopped");
				return;
			}
			
		}
	}
	public static void main(String args[]){
		Balloon balloon = new Balloon();
		balloon.play();
	}
}