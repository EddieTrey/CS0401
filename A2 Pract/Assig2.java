/* Edward Yeh  CS0401: Intermediate Programming Using java
               Section: M W 12:30PM - 2:15PM
               Lab: Th 12:00PM - 1:50PM
The purpose of this program is to make a scrambling game that reads a list of words from the text and
let the user try to guess the word after it is scrambled. */

import java.util.*;
import java.io.*;

public class Assig2

{

    public static void main (String [] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        String username;                                // the user's name
        String word, userword, scramword;               // word is the word from the list, userword is the guess, scramword is scrambled word.
        Scramble scram = new Scramble("words.txt");
        Results res = new Results("results.txt");
        boolean play = true;                            // used as sentinel for the main while loop. if false, it means the player doesn't
                                                        // want to play anymore.
        String yes_no;                                  // string for yes/no to continue playing
        int count = 0;                                  // number of guesses

        System.out.println("Hello! Welcome to the Scrambler!!\nAre you ready to play?");
        System.out.print("Please enter your name: ");
        username = input.nextLine();
        System.out.println(username + ", you have 5 guesses to make the Scramble.\nGOOD LUCK!\n");
        word = scram.getRealWord().toLowerCase();
        scramword = scram.getScrambledWord().toLowerCase();

        while (play)
        {
            System.out.print("Scramble: " + scramword + "\nYour Guess: ");
            userword = input.next().toLowerCase();

            //If the entered word is not the actual word and the number of guesses is less than 5.
            while (!userword.equals(word) && count < 4)  //4 because 0-4 is 5 numbers
            {
                System.out.println("Sorry, " + username + " that is not correct");
                System.out.println("Here are the letters you got correct: ");
                for (int i = 0; i < word.length(); i++)
                {
                    //.equals didn't work for char. ***** exception if userword is shorter than the scrambled word.
                    //tried i < userword.length() so that if eg: word = food, userword =foo. When i = 3, the exception doesn't occur.
                    if (i < userword.length() && userword.charAt(i) == (word.charAt(i)))
                    {
                        System.out.print(word.charAt(i));
                    }
                    else
                    {
                        System.out.print('?');
                    }
                }
                count++;
                System.out.println("\nYou have " + (5 - count) + " more guesses.\n");
                System.out.print("Scramble: " + scramword + "\nYour Guess: ");
                userword = input.next().toLowerCase();
            }

            //If loop to update losses and rounds when number of guesses reaches 5.
            if (count == 4)
            {
                count++;      //because when count = 4, the while loop doesn't iterate. So we need to ++ here.
                System.out.println("Sorry! You're out of guesses!");
                System.out.println("The correct answer is: " + word) ;
                res.update(false, count);
            }

            //If user guesses correct word.
            if (userword.equals(word))
            {
                System.out.println("You got it!\n");
                count++;
                res.update(true, count);
            }

            //Loop for user to continue playing or not.
            int condition = 0;
            do
            {
                System.out.println("Would you like to keep playing? (yes/no)");
                yes_no = input.next();
                System.out.println("");
                count = 0;
                if (yes_no.equals("yes") || yes_no.equals("no"))
                {
                    condition = 1;
                }
            } while(condition == 0);

            //Loop for continue to play and if there are still words left in the txt.
            if (yes_no.equals("yes"))
            {
                word = scram.getRealWord();
                if (word != null)                   //Incase out of words and null pointer excep occurs.
                {
                    word = word.toLowerCase();
                    scramword = scram.getScrambledWord().toLowerCase();
                    System.out.println("You have 5 guesses to make the Scramble.\nGOOD LUCK!\n");

                }
                else
                {
                    System.out.println("No words left!!");
                    play = false;
                    System.out.println("Thanks for playing "+ username +"!\n"+ res.toString());
    				res.save();
                }
            }

            else
            {
                play = false;
                System.out.println("Thanks for playing "+ username +"!\n"+ res.toString());
				res.save();

            }


        }

    }

}



/*  Note To Self: This didn't work because scram.getRealWord is null so we can't call methods with it. Even with the if (word == null),
    because we need to make it to lower case so capitalization doesn't matter. MAKE A NEW LOOP


 if (yes_no.equals("yes"))     //Note1 to self: need something here for null pointer exceptoin if out of wordssss
{
    word = scram.getRealWord();  //Note2 to self: can't used .toLowerCase here cuz scram.RealWord = null. can't call methods with null
    scramword = scram.getScrambledWord().toLowerCase();
    if (word == null)           //Note3 to self: Took off .toLowerCase for word and added this to exit game if out of words
    {
        System.out.println("No words left");
        play = false;
    }
}
*/
