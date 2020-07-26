/* Edward Yeh  CS0401: Intermediate Programming Using java
               Section: M W 12:30PM - 2:15PM
               Lab: Th 12:00PM - 1:50PM
*/

import java.util.*;
import java.io.*;

public class Assig3

{

    public static void main (String [] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        String username;                                // the user's name
        String word, userword, scramword;               // word is the word from the list, userword is the guess, scramword is scrambled word.
        Scramble2 scram = new Scramble2("words.txt");
        PlayerList playlist = new PlayerList("players.txt");
        Player player = new Player();
        boolean play = true;                            // used as sentinel for the main while loop. if false, it means the player doesn't
                                                        // want to play anymore.
        boolean playf = true;                           // boolean for most outer loop.
        String yes_no;                                  // string for yes/no to continue playing
        String addname;                                  // string for adding name or not.
        int count = 0;                                  // number of guesses

        System.out.println("Hello! Welcome to the Scrambler!!\nAre you ready to play?");
        System.out.println("Please enter your name(Spelling Counts) or press enter to quit");
        username = input.nextLine();

        //Loop for if more than <Enter> is pressed.
        while (username.length() > 0 && playf == true)
        {
            player = playlist.getPlayer(username);
            if (player != null)
            {
                System.out.println("Welcome back " + username);
                System.out.println(player.toString());
                System.out.println(username + ", you have 5 guesses to make the Scramble.\nGOOD LUCK!\n");
                play = true;
            }
            else
            {
                System.out.println(username + ", you aren't on the player list.");
                System.out.println("Do you wish to add your name to our player list? (yes/no): ");
                addname = input.nextLine();
                if (addname.equals("yes"))
                {
                    player = new Player(username);
                    playlist.addPlayer(player);
                }
                else
                {
                    play = false;
                    System.out.println("Please enter your name(Spelling Counts) or press enter to quit");
                    username = input.nextLine();
                }
            }
            while (play)
            {
                word = scram.getRealWord().toLowerCase();
                scramword = scram.getScrambledWord().toLowerCase();

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
                    player.update(false, count);
                }

                //If user guesses correct word.
                if (userword.equals(word))
                {
                    System.out.println("You got it!\n");
                    count++;
                    player.update(true, count);
                }

                //Loop for user to continue playing or not.
                int condition = 0;
                do
                {
                    System.out.println("Would you like to keep playing? (yes/no)");
                    yes_no = input.next();
                    System.out.println();
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
                        System.out.println("Thanks for playing "+ username +"!\n"+ player.toString());
                        player.toStringFile();
                        System.out.println("Please enter your name(Spelling Counts) or press enter to quit");
                        scram.reset();
                        input.nextLine();
                        username = input.nextLine();
                    }
                }
                else
                {
                    play = false;
                    System.out.println("Thanks for playing "+ username +"!\n"+ player.toString());
                    player.toStringFile();
                    System.out.println("Please enter your name(Spelling Counts) or press enter to quit");
                    scram.reset();
                    input.nextLine();
                    username = input.nextLine();
                }
            }
        }
        System.out.println("Game Over!!\nHere are the player stats: ");
        System.out.println(playlist.toString());
        playlist.saveList();
    }
}
