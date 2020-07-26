/* What's different? In this version, we choose a RANDOM word from the list instead of in order. */
import java.util.*;
import java.io.*;

public class Scramble2

{
    private File infile;
    private Scanner input;

    // These two booleans are so that getScrambledWord can't be called without real word already being called.
    private boolean realwordboo = false;
    private boolean scramwordboo = true;

    private StringBuilder stringbuilder;
    private String word;
    private Random rand = new Random();  // Random number generator.
    private ArrayList<String> wordsList; //ArrayList for words in player.txt file. !!!TRY THE NEW NAMING METHOD YOU SAW.
    private ArrayList<String> noRepeatCheck; //This ArrayList is the same as wordList but it is for changing the word
                                            //to "useddddd" so that I can check if the word has been used or not.


    private int listPhys;
    private int usedWordsArray;
    private int go;

    //Constructor
    public Scramble2(String filename) throws IOException
    {
        infile = new File(filename);
        input = new Scanner(infile);
        wordsList = new ArrayList<String>();     //ArrayList auto resizes-YAY
        while (input.hasNext())
        {
            wordsList.add(input.nextLine());
            listPhys++;
        }
        noRepeatCheck = new ArrayList<String>(wordsList);
        usedWordsArray = 0;
    }

    //Gets a word from the list and returns it. Also if it's called already called and scrambled word isn't called yet, return same word.
    public String getRealWord()
    {
        for (String x : noRepeatCheck)
        {
            if (!x.toLowerCase().contains("useddddd"))
            {
                go = 1;
                break;
            }
            else
            go = 0;
        }


        if (go == 1 && scramwordboo == true && usedWordsArray < listPhys)
        {
            int randoNum;
            do
            {
                randoNum = rand.nextInt(listPhys);
            } while (noRepeatCheck.get(randoNum).equals("useddddd"));

            //Note to Self!! COMP ERROR: ARRAY REQUIRED BUT ARRAYLIST FOUND
            //while (noRepeatCheck[randoNum].equals("useddddd"));   Array[i] if it's an actual normal array not ArrayList
            //while (noRepeatCheck.indexOf(randoNum).equals("useddddd"));  INDEXOF GIVES REFERENCE OF OBJECT SO WILL RETURN INT

            noRepeatCheck.set(randoNum, "useddddd");
            stringbuilder = new StringBuilder(wordsList.get(randoNum));
            word = stringbuilder.toString();
            realwordboo = true; // Sets boolean to true to that getScrambledWord can work.
            scramwordboo = false;
            usedWordsArray++;
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
    //Resets the number of used words and the booleans and also makes a new array for checking.
    public void reset()
    {
        usedWordsArray = 0;
        noRepeatCheck = new ArrayList<String>(wordsList);
        realwordboo = false;
        scramwordboo = true;
    }
}
