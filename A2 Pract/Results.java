import java.util.*;
import java.io.*;

public class Results
{
    //Bunch of vars needed for the methods.
    private int wins, losses, rounds, guess;
    private PrintWriter write;
    private Scanner input;
    private File outfile;

    //Constructor that takes results.txt as input (String)
    public Results (String filename) throws IOException
    {
        outfile = new File(filename);
        input = new Scanner(outfile);

        //If the files already has numbers, we read in the values, if not, we set them all to 0s.
        if (true)
        {
            if (input.hasNext())
            {
                wins = input.nextInt();
                losses = input.nextInt();
                rounds = input.nextInt();
                guess = input.nextInt();
            }
            else
            {
                wins = 0;
                losses = 0;
                rounds = 0;
                guess = 0;
            }
        }
        write = new PrintWriter(outfile);
    }

    //Takes input from main, to increment losses or wins based on arguement. Guesses and rounds is updated everytime.
    public void update(boolean won, int guesses)
    {
        if (won == true)
        {
            wins++;
            rounds++;
            guess += guesses;
        }
        else
        {
            losses++;
            rounds++;
            guess += guesses;
        }
    }

    //Print out the data to the txt file.
    public void save() throws IOException
    {
        write.println(wins);
		write.println(losses);
		write.println(rounds);
        write.print(guess);
		write.close();
    }

    //Returns the average.
    public double aveGuesses()
    {
        double ans = (double)guess / rounds;
        if (rounds == 0)
        {
            return 0.0;
        }
        else return ans;
    }

    //Prints out the results.
    public String toString()
    {
        return ("Here are the results: \n        Rounds tried: " + rounds + "\n        Rounds won: " + wins +
                                "\n        Rounds lost: " + losses + "\n        Total guesses: " + guess + "\n        Ave guesses: " + aveGuesses());
    }

}
