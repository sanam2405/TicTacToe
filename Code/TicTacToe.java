import java.util.*;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	
	public static void main(String args[])
	{
		char gameBoard[][] = {
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '}
							  };
		
		char gameBoardLegend[][] = {
										{'1', '|', '2', '|', '3'},
										{'-', '+', '-', '+', '-'},
										{'4', '|', '5', '|', '6'},
										{'-', '+', '-', '+', '-'},
										{'7', '|', '8', '|', '9'}
			  						};

		printGameBoard(gameBoard);
		System.out.println();
		printLine();
		System.out.println();
		printGameBoard(gameBoardLegend);
			
		while(true)
		{
			Scanner sc = new Scanner(System.in);
			String result;
			
			System.out.println();
			System.out.println("Where do you want to place ?");
			int playerPos = sc.nextInt();
			
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos) || invalidPos(playerPos))
			{
				if(invalidPos(playerPos))
					System.out.println("The position is invalid!");
				else
					System.out.println("Oops! The position is already taken ");
				System.out.println("Enter an valid possition : ");
				playerPos = sc.nextInt();
			}
			
			System.out.println();
			System.out.println("Your position : "+playerPos);
			
			placeMove(gameBoard, playerPos, "player");
			placeMove(gameBoardLegend, playerPos, "player");
			
			result = checkWinner();
			if(result.length()>0)
			{
				printGameBoard(gameBoard);
				System.out.println(result);
				break; 
			}
			
			Random rn = new Random();
			int cpuPos = rn.nextInt(9)+1;
			
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)|| invalidPos(cpuPos))
			{
				cpuPos = rn.nextInt(9)+1;
			}
			
			System.out.println("CPU position : "+cpuPos);
			
			placeMove(gameBoard,cpuPos,"cpu");
			placeMove(gameBoardLegend, cpuPos, "cpu");
			
			result = checkWinner();
			if(result.length()>0)
			{
				printGameBoard(gameBoard);
				System.out.println(result);
				break;
			}
			
			System.out.println();
			printGameBoard(gameBoard);
			System.out.println();
			printLine();
			System.out.println();
			printGameBoard(gameBoardLegend);
			
		}
		
	}
	
	public static void printLine()
	{
		System.out.println("*****");
	}
	
	public static boolean invalidPos(int pos)
	{
		if(pos> 0 && pos < 10)
			return false;
		else
			return true;
	}
	
	public static void printGameBoard(char gameBoard[][])
	{
		int rowLength = gameBoard.length;
		int colLength = gameBoard[0].length;
		
		for(int row = 0; row < rowLength; row++)
		{
			for(int col = 0; col < colLength; col++)
			{
				System.out.print(gameBoard[row][col]);
			}
			
			System.out.println();
		}
	}
	
	public static void placeMove(char gameBoard[][], int pos, String user)
	{
		char symbol;
		
		if(user.equals("player"))
		{
			symbol = 'x';
			playerPositions.add(pos);
		}
			
		else
		{
			symbol = 'o';
			cpuPositions.add(pos);
		}
			
		switch(pos)
		{
			case 1:
				gameBoard[0][0] = symbol;
				break;
				
			case 2:
				gameBoard[0][2] = symbol;
				break;
				
			case 3:
				gameBoard[0][4] = symbol;
				break;
				
			case 4:
				gameBoard[2][0] = symbol;
				break;
				
			case 5:
				gameBoard[2][2] = symbol;
				break;
				
			case 6:
				gameBoard[2][4] = symbol;
				break;
				
			case 7:
				gameBoard[4][0] = symbol;
				break;
				
			case 8:
				gameBoard[4][2] = symbol;
				break;
				
			case 9:
				gameBoard[4][4] = symbol;
				break;
		}	
	}
	
	public static String checkWinner()
	{
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		List dexDiag = Arrays.asList(1,5,9);
		List sinDiag = Arrays.asList(3,5,7);
		
		List<List> winning = new ArrayList<List>();
		
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(dexDiag);
		winning.add(sinDiag);
		
		for(List l : winning)
		{
			if(playerPositions.containsAll(l))
				return "Congratulations you won!";
			if(cpuPositions.containsAll(l))
				return "CPU Wins!";
		}
		
		if(playerPositions.size()+cpuPositions.size() == 9)
		return "The Game is a Draw!";
		
		return "";
	}
}


