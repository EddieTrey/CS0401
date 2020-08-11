import java.io.*;
import java.util.*;

public class War
{
    public static void main(String [] args)
    {
        int decksize = 52;
        boolean toP1 = true;
        MultiDS<Card> deck = new MultiDS<Card>(decksize);
        MultiDS<Card> player1 = new MultiDS<Card>(26);
        MultiDS<Card> player2 = new MultiDS<Card>(26);
        MultiDS<Card> player1Discard = new MultiDS<Card>(1);
        MultiDS<Card> player2Discard = new MultiDS<Card>(1);
        MultiDS<Card> inPlay = new MultiDS<Card>(2);


        //HOLY SHIT I FIGURED IT OUT, TOOK ME A WHOLE HOYUR KILL ME PLEASE!!!
		for (Card.Suits s: Card.Suits.values())
		{
            for (Card.Ranks r: Card.Ranks.values())
            {
                Card temp = new Card(s,r);
                deck.addItem(temp);
            }
        }
        deck.shuffle();
        deck.reverse();
        for (int i = 0; i < decksize; i++)
        {
            if (toP1)
            {
                player1.addItem(deck.removeItem());
                toP1 = false;
            }
            else
            {
                player2.addItem(deck.removeItem());
                toP1 = true;
            }
        }
        System.out.println("Welcome to the War game!");
        System.out.println("Now dealing the cards....\n");
        System.out.println("Here is Player1's hand:");
        System.out.println(player1.toString());
        System.out.println("Here is Player2's hand:");
        System.out.println(player2.toString());
        System.out.println();

        //Commented out but should be left in. Prof's output file has a logic error.
        //If players play cards from the top of their hand, players' hands should be
        //reversed since the required implementation of remove() removes the "oldest" card
        //aka. the card at the beginning of the array.

        //player1.reverse();
        //player2.reverse();
        int totalRounds = Integer.parseInt(args[0]);
        int currRounds = 0;
        System.out.println("Starting the WAR!\n");

        while (currRounds < totalRounds)
        {
            if (player1.size() == 0)
            player1.fill(player1Discard);

            if (player2.size() == 0)
            player2.fill2(player2Discard);
            inPlay.addItem(player1.removeItem());
            inPlay.addItem(player2.removeItem());
            Card p1Card = inPlay.card();
            Card p2Card = inPlay.card1();
            //int result = inPlay.removeItem().compareTo(inPlay.removeItem());
            int result = p1Card.compareTo(p2Card);
            if (result > 0)
            {
    			System.out.println("Player1 wins round " + currRounds + ": " + p1Card + " beats " + p2Card + " : " + inPlay.size());
                currRounds++;
                while (!inPlay.empty())
                {
                    player1Discard.addItem(inPlay.removeItem());
                }
            }
    		else if (result < 0)
            {
    			System.out.println("Player2 wins round " + currRounds + ": " + p1Card + " loses to " + p2Card + " : " + inPlay.size());
                currRounds++;
                while (!inPlay.empty())
                {
                    player2Discard.addItem(inPlay.removeItem());
                }
            }
    		else
            {
                while (result == 0)
                {
        			System.out.println("\tWAR!!!!!" + p1Card + " ties " + p2Card);
                    if (player1.size() == 0)
                    player1.fill(player1Discard);

                    if (player2.size() == 0)
                    player2.fill2(player2Discard);
                    Card temp1 = player1.removeItem();
                    Card temp2 = player2.removeItem();
                    System.out.println("\tPlayer1: " + temp1 + " and Player2: " + temp2 + " are at risk!");
                    inPlay.addItem(temp1);
                    inPlay.addItem(temp2);
                    if (player1.size() == 0)
                    player1.fill(player1Discard);

                    if (player2.size() == 0)
                    player2.fill2(player2Discard);
                    inPlay.addItem(player2.removeItem());
                    inPlay.addItem(player1.removeItem());
                    inPlay.reverse();
                    p1Card = inPlay.card();
                    p2Card = inPlay.card1();
                    result = p1Card.compareTo(p2Card);
                    if (result > 0)
                    {
            			System.out.println("Player1 wins round " + currRounds + ": " + p1Card + " beats " + p2Card + " : " + inPlay.size());
                        currRounds++;
                        while (!inPlay.empty())
                        {
                            player1Discard.addItem(inPlay.removeItem());
                        }
                    }
            		else if (result < 0)
                    {
            			System.out.println("Player2 wins round " + currRounds + ": " + p1Card + " loses to " + p2Card + " : " + inPlay.size());
                        currRounds++;
                        while (!inPlay.empty())
                        {
                            player2Discard.addItem(inPlay.removeItem());
                        }
                    }
                }
            }
        }
        System.out.println("\nAfter " + currRounds + " rounds, here are the stats:");
        //System.out.println(""+ currRounds + 1);
        //Note To Self!! If curr is 100. The above statement will print: 1001 instead of 101. Cool
        //If (currRounds + 1);     It will print 101 tho.
        int p1Total = player1.size() + player1Discard.size();
        int p2Total = player2.size() + player2Discard.size();
        System.out.println("\tPlayer1 has " + p1Total + " cards\n" + "\tPlayer2 has " + p2Total + " cards");
        if (p1Total > p2Total)
        System.out.println("Player1 is the Winner!!!");
        else if (p1Total < p2Total)
        System.out.println("Player2 is the Winner!!!");
        else
        System.out.println("ISSA TIE!!!");

    }
}
