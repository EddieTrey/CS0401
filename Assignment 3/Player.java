import java.util.*;
import java.io.*;
import java.text.*;

public class Player
{
    //Vars for names, wins, losses, etc.
    private String name;
    private int wins, losses, rounds, guess;

    //Default constructor for fun.
    public Player()
    {
        name = "IDK LOL";
        wins = 0;
        losses = 0;
        rounds = 0;
        guess = 0;
    }
    //Constructor for when only name is passed.
    public Player(String x)
    {
        name = x;
        wins = 0;
        losses = 0;
        rounds = 0;
        guess = 0;
    }

    public Player(String x, int r, int w, int l, int g)
    {
        name = x;
        wins = w;
        losses = l;
        rounds = r;
        guess = g;
    }

    //Functions to return info of player.
    public String name()
    {
        return name;
    }
    public int getWins()
    {
        return wins;
    }
    public int getLosses()
    {
        return losses;
    }
    public int getRounds()
    {
        return rounds;
    }
    public int getGuess()
    {
        return guess;
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
        //Formatting from stackoverflow: NumberFormat
        NumberFormat form = new DecimalFormat("#0.00");
        return ("        Name: " + name + "\n        Rounds tried: " + rounds + "\n        Rounds won: " + wins +
                                "\n        Rounds lost: " + losses + "\n        Total guesses: " + guess + "\n        Ave guesses: " + form.format(aveGuesses()) + "\n");
    }

    //Works together with PlayerList.saveList(). This returns formatted Player info so saveList() can write to file.
    public String toStringFile()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(name + " ");
		stringBuilder.append(rounds + " ");
		stringBuilder.append(wins + " ");
		stringBuilder.append(losses + " ");
        stringBuilder.append(guess + "\n");
		return stringBuilder.toString();
	}

}
