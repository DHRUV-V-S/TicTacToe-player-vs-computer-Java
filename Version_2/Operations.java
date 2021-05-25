package Version_2;

import java.util.Scanner;

public class Operations 
{
	Scanner in = new Scanner(System.in);
	String[] board;		//Global variables since we only update them when
	String turn;		//game progresses
	
	Operations()
	{
		System.out.println("Welcome to 3x3 Tic Tac Toe.");
		board = new String[9];
		for (int i = 0; i < 9; i++) 
		{
            board[i] = String.valueOf(i);
        }
		PrintBoard();
		turn="X";
		System.out.println("Press 1 to play first, press any other number to continue");
        if(in.nextInt()!=1)
        {
        	turn="O";
        }
	}
	void ComputerMove()
	{
		System.out.println("Computer is thinking hard....\n"
    			+ "\nAfter computer's move");
		ComputerOperations c1=new ComputerOperations();
		int move=c1.FindBestMove(board);
		board[move]=turn;
		UpdateTurn();
		PrintBoard();
		
	}
	void PlayerMove()
	{
		int i=0;
		int move = 0;
		while(i==0)
			{
				System.out.println("Enter a slot number to place X in the board");
				move=in.nextInt();
				if (board[move].equals(String.valueOf(move)))
				{
					i=1;
				}
				else 
				{
					System.out.println("Slot already taken; re-enter slot number:");
				}
			}
		board[move]=turn;
		UpdateTurn();
		PrintBoard();
	}
	String CheckWinner() 
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
        String [] temp=GetFreeElements();
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
	String[] GetFreeElements() 
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
	boolean isNumeric(String string) 
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
	void UpdateTurn() 
	{
		if (turn.equals("X")) 
        {
            turn = "O";
        }
        else 
        {
            turn = "X";
        }

	}
	
	void PrintBoard() 
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
