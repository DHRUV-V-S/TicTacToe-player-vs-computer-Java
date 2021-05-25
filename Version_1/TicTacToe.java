package Version_1;

import java.util.Scanner;

public class TicTacToe 
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
        String[] board = new String[9];
        String turn = "X";
        String winner = null;
  
        for (int i = 0; i < 9; i++) 
        {
            board[i] = String.valueOf(i);//Creating board
        }
  
        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        PrintBoard(board);
  
        System.out.println("Press 1 to play first, press any other number to continue");
        if(in.nextInt()!=1)
        {
        	turn="O";
        }
  
        while (winner == null) 
        {
            int numInput;
            
            if (turn.equals("X")) //Player move
            {
            	System.out.println("Enter a slot number to place X in the board");
            	numInput=in.nextInt();
            }
            else
            {
            	System.out.println("Computer is thinking hard....\n"
            			+ "\nAfter computer's move");
            	numInput=FindBestMove(board);//Computer move
            }
            
            if (board[numInput].equals(
                    String.valueOf(numInput))) 
            {
                board[numInput] = turn;//Updating board
                turn=UpdateTurn(turn);//Updating turn
                
  
                PrintBoard(board);
                winner = CheckWinner(board);//Checking winner

                
            }
            else 
            {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }
        in.close();
        // If no one win or lose from both player x and O.
        // then here is the logic to print "draw".
        if (winner=="draw") 
        {
            System.out.println("It's a draw! Thanks for playing.");
        }
        
        // For winner -to display Congratulations! message.
        else 
        {
            System.out.println("Congratulations! " + winner
                + "'s have won! Thanks for playing.");
        }

	}
	
	private static String CheckWinner(String[] board) 
	{
		for (int a = 0; a < 8; a++) 
		{
            String line = null;
  
            switch (a) {
            case 0:
                line = board[0] + board[1] + board[2];
                break;
            case 1:
                line = board[3] + board[4] + board[5];
                break;
            case 2:
                line = board[6] + board[7] + board[8];
                break;
            case 3:
                line = board[0] + board[3] + board[6];
                break;
            case 4:
                line = board[1] + board[4] + board[7];
                break;
            case 5:
                line = board[2] + board[5] + board[8];
                break;
            case 6:
                line = board[0] + board[4] + board[8];
                break;
            case 7:
                line = board[2] + board[4] + board[6];
                break;
            }
            //For X winner
            if (line.equals("XXX")) 
            {
                return "X";
            }
              
            // For O winner
            else if (line.equals("OOO")) 
            {
                return "O";
            }
        }
        String [] temp=GetFreeElements(board);
        int j=0;
        for (int i=0;i<9;i++)
        {
        	if (temp[i]!=null)
        	{
        		j++;
        	}
        }
        if(j==0)
        	return "draw";
  
       
        return null;
	
	}

	private static String[] GetFreeElements(String[] board) 
	{
		String [] temp= new String[9];
        int i=0;
        String j="";
        for (int a = 0; a < 9; a++) 
        {
            j=board[a];
            if(isNumeric(j))
            {
            	if (Integer.parseInt(j)<10) 
            	{
         
            		temp[i]=j;
            		i++;
            		
            	}
            }
        	
            
        }
        return temp;
	}

	private static boolean isNumeric(String string) 
	{
		int intValue;
		
        //System.out.println(String.format("Parsing string: \"%s\"", string));
    		
        if(string == null || string.equals("")) 
        {
            //System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }
        
        try 
        {
            intValue = Integer.parseInt(string);
            return true;
        } 
        catch (NumberFormatException e) 
        {
            //System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
	}

	private static String UpdateTurn(String turn) 
	{
		if (turn.equals("X")) 
        {
            turn = "O";
        }
        else 
        {
            turn = "X";
        }
		return turn;
	}

	private static int FindBestMove(String[] board) //refer:https://www.youtube.com/watch?v=trKjYdBASyQ&list=WL&index=2&t=1152s
	{
		String turn="O";
		int bestScore=-2;
		int bestMove=4;
		String [] temp=GetFreeElements(board);
        for (int i=0;i<9;i++)
        {
        	if (temp[i]!=null)
        	{
        		String temp1=board[Integer.parseInt(temp[i])];
        		board[Integer.parseInt(temp[i])]=turn;
        		int score=MiniMax(board,UpdateTurn(turn));
        		board[Integer.parseInt(temp[i])]=temp1;
        		if(score>bestScore)
        		{
        			bestScore=score;
        			bestMove=Integer.parseInt(temp[i]);
        		}
        	}
        }
        return bestMove;
	}

	private static int MiniMax(String[] board, String turn) 
	{
		String result=CheckWinner(board);
		if(result!=null)
		{
			if(result=="X")
				return -1;
			if(result=="O")
				return 1;
			if(result=="draw")
				return 0;
		}
		
		String temp[]=GetFreeElements(board);
		int score;
		if(turn=="O")
		{
			int bestScore=-2;
			for (int i=0;i<9;i++)
            {
            	if (temp[i]!=null)
            	{
            		String temp1=board[Integer.parseInt(temp[i])];
            		board[Integer.parseInt(temp[i])]=turn;
            		score=MiniMax(board,UpdateTurn(turn));
            		board[Integer.parseInt(temp[i])]=temp1;
            		if(score>bestScore)
            		{
            			bestScore=score;
            		}
            	}
            }
			return bestScore;
			
		}
		else
		{
			int bestScore=2;
			for (int i=0;i<9;i++)
            {
            	if (temp[i]!=null)
            	{
            		String temp1=board[Integer.parseInt(temp[i])];
            		board[Integer.parseInt(temp[i])]=turn;
            		score=MiniMax(board,UpdateTurn(turn));
            		board[Integer.parseInt(temp[i])]=temp1;
            		if(score<bestScore)
            		{
            			bestScore=score;
            		}
            	}
            }
			return bestScore;
		}
	}

	private static void PrintBoard(String[] board) 
	{
		System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | "
                           + board[1] + " | " + board[2]
                           + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | "
                           + board[4] + " | " + board[5]
                           + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | "
                           + board[7] + " | " + board[8]
                           + " |");
        System.out.println("|---|---|---|");
		
	}

}
