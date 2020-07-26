import java.util.*;
import java.io.*;
import java.text.*;

public class PlayerList
{
    //Bunch of vars needed for the methods.
    private String name;
    private int wins, losses, rounds, guess;
    private double percent;
    private PrintWriter write;
    private Scanner input;
    private File outfile;
    private Player[] arrPlayers;
    private int totalPlayers = 0;
    private Player player1 = new Player();



    //Constructor that takes player.txt as input (String)
    public PlayerList (String filename) throws IOException
    {
        outfile = new File(filename);
        input = new Scanner(outfile);

        //We read in the data needed for a Player obj, then assign it to a PlayerList array
        if (true)
        {
            arrPlayers = new Player[3];
            while (input.hasNext())
            {
                if (arrPlayers.length == totalPlayers)
                {
                    Player[] temp = new Player[arrPlayers.length * 2];
                    for (int i = 0; i < arrPlayers.length; i++)
                    {
                        temp[i] = arrPlayers[i];
                    }
                    arrPlayers = temp;
                }
                arrPlayers[totalPlayers] = new Player(input.next(), input.nextInt(), input.nextInt(), input.nextInt(),input.nextInt());

                totalPlayers++;
                if (input.hasNextLine())
                input.nextLine();
            }
        }
        write = new PrintWriter(outfile);
    }

    //Returns the physical size of the array.
    public int size()
    {
        return totalPlayers;
    }


    //Returns the logical size of array.
    public int capacity()
    {
        return arrPlayers.length;
    }


    //iterate through array to find an index with matching name until all items are read or we find the match.
    public Player getPlayer(String yo)
    {
        int i = 0;
        boolean found = false;
        while (i < totalPlayers && found == false)
        {
            if (arrPlayers[i].name().equals(yo))
            {
                found = true;
                return arrPlayers[i];
            }
            else
            i++;
        }
        return null;
    }

    //double array size if physical size = logical size. Add player obj to end of array.
    public void addPlayer(Player x)
    {
        if (arrPlayers.length == totalPlayers)
        {
            Player[] temp = new Player[arrPlayers.length * 2];
            for (int i = 0; i < arrPlayers.length; i++)
            {
                temp[i] = arrPlayers[i];
            }
            arrPlayers = temp;
        }
        arrPlayers[totalPlayers] = x;
        totalPlayers++;
    }

    public void updateWins(int g)
    {
        guess += g;
        wins++;
        rounds++;
    }

    //Iterate through array to find the matching obj. When found, decrease the according ints and remove
    //obj from array, by setting it to n+1 until all objs are iterated through.
    public Player removePlayer(String yo)
    {
        int i = 0;
        boolean found = false;
        while (i < totalPlayers && found == false)
        {
            if (arrPlayers[i].name().equals(yo))
            {
                found = true;
                wins -= arrPlayers[i].getWins();
                losses -= arrPlayers[i].getLosses();
                rounds -= arrPlayers[i].getRounds();
                guess -= arrPlayers[i].getGuess();
                player1 = arrPlayers[i];
                for (int q = i; q < totalPlayers; q++)
                {
                    arrPlayers[q] = arrPlayers[q+1];
                }
                totalPlayers--;
                return player1;
            }
            else
            i++;
        }
        return null;
    }
    public void updateLosses(int g)
    {
        losses++;
        guess +=g;
        rounds++;
    }

    //Works with Player.toStringFile(). Basically writes name, rounds, wins, etc for each Player obj to output file.
    public void saveList() throws IOException
    {
		for (int i = 0; i < totalPlayers; i++)
		{
			write.print(arrPlayers[i].toStringFile());
		}
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

    //Prints out the results. Because test file only called Player.update(), I couldn't update the
    //vars. by calling methods in PlayerList. Thus in this method, if there is a different between the ints here and the total
    //when each variable is called and added together, the ints here are changed accordning, since the numbers
    //in the Array are the correct numbers.
    public String toString()
	{
        int r = 0;              //initializing the ints for addition.
        int w = 0;
        int l = 0;
        int g = 0;
        for (int i = 0; i < totalPlayers; i++)
        {
            r += arrPlayers[i].getRounds();
            w += arrPlayers[i].getWins();
            l += arrPlayers[i].getLosses();
            g += arrPlayers[i].getGuess();
        }
            if(rounds != r)
            {
                rounds = r;
                wins = w;
                losses = l;
                guess = g;
            }
        //Calculation of the percent.
        if (rounds == 0)
        {
            percent = 0.0;
        }
        else
            percent = ((double)wins) / rounds;
            percent *= 100;

        StringBuilder allDaInfo = new StringBuilder();
        //From stackoverflow on how to format demical numbers to 2 decimal points: NumberFormat
        NumberFormat form = new DecimalFormat("#0.0");
        NumberFormat form1 = new DecimalFormat("#0.00");
        allDaInfo.append("Total Players: " + totalPlayers);
        allDaInfo.append("\n\tTotal Rounds Played: " + rounds);
        allDaInfo.append("\n\tTotal Rounds Won: " + wins);
        allDaInfo.append("\n\tTotal Rounds Lost: " + losses);
        allDaInfo.append("\n\tTotal Guesses: " + guess);
        allDaInfo.append("\n\tPct of Rounds Won: " + form.format(percent));
        allDaInfo.append("\n\tAve Guesses per Round " + form1.format(aveGuesses()) +"\n");
		return allDaInfo.toString();
	}

    public String toStringAdmin()
    {
        StringBuilder allDaInfo2 =  new StringBuilder();
        allDaInfo2.append(toString());
        for (int i = 0; i < totalPlayers; i++)
        {
            allDaInfo2.append("\n" + arrPlayers[i].toString());
        }
        return allDaInfo2.toString();
    }
}
