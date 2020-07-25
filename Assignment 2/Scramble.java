import java.util.*;
import java.io.*;

public class Scramble

{
    private File infile;
    private Scanner input;

    // These two booleans are so that getScrambledWord can't be called without real word already being called.
    private boolean realwordboo = false;
    private boolean scramwordboo = true;

    private StringBuilder stringbuilder; // I'm using a stringbuilder because we can't use arrays in this assignment.
    private String word;                 // To store the word read from the file.
    private Random rand = new Random();  // Random number generator.


    //Constructor
    public Scramble(String filename) throws IOException
    {
        infile = new File(filename);
        input = new Scanner(infile);
    }

    //Gets a word from the list and returns it. Also if it's called already called and scrambled word isn't called yet, return same word.
    public String getRealWord() throws IOException
    {
        if (input.hasNext() && scramwordboo == true)
        {
            stringbuilder = new StringBuilder(input.nextLine());
            word = stringbuilder.toString();
            realwordboo = true; // Sets boolean to true to that getScrambledWord can work.
            scramwordboo = false;
            return word;
        }
        else if (scramwordboo == false)
        {
            return word;
        }
        else
        {
            realwordboo = false; //So getScrambledWord can't be called if there are no words left.
            return null;
        }
    }

    public String getScrambledWord()
    {
        if (realwordboo == true)
        {
            if (scramwordboo == false)
            {
                //stringbuilder = new StringBuilder(word);
                scramwordboo = true;
                while (word.equals(stringbuilder.toString()))        // Personal Comment: ignore if grader!
                                                                     // check previous lab to brush up on how to use random generator.
                {
                    //Modified fisher-yates shuffle
                    int i = stringbuilder.length();                  //Note to self!! .length(), Lab6 used .length cuz arrays are special.
                    int o;
                    char p;
                    while ((--i) > 0)
                    {
                        o = rand.nextInt(i);
                        p = stringbuilder.charAt(o);
                        stringbuilder.setCharAt(o, stringbuilder.charAt(i));
                        stringbuilder.setCharAt(i, p);
                    }
                }
                return stringbuilder.toString();
            }
            else
            {
                return stringbuilder.toString();
            }
        }
        return null;
    }
}
