package Version_2;

public class Driver 
{

	public static void main(String[] args) 
	{
		Operations o1=new Operations();
		String winner = null;
		while(winner==null)
		{
			if(o1.turn=="X")
			{
				o1.PlayerMove();
			}
			else
			{
				o1.ComputerMove();
			}
			winner = o1.CheckWinner();
		}
		PrintResult(winner);
		
	}

	static void PrintResult(String winner) 
	{
		if (winner=="draw") 
        {
            System.out.println("It's a draw! Thanks for playing.");
        }
        
        
        else if(winner=="O")
        {
            System.out.println("Computer has won! Thanks for playing.");
        }
        else
        {
        	System.out.println("Congratulations! You, beat the computer");
        }
		
	}

}
