// CS 0401 Summer 2020
// Shell of MultiDS<T> class to be used with Assignment 4.
// You must complete this class so that:
// 1) It works with test program Assig4A.java
// 2) You can use it in your War program

import java.io.*;
import java.util.*;

// Note that your class implements the two interfaces below.  This means
// that it must implement all of the methods in both interfaces.
public class MultiDS<T> implements PrimQ<T>, Reorder
{
	private T [] theData;  // Note that the data is an array of T
	// You will also need one or more state variables to keep track of your
	// logical data.  The variables you need depend on how you will manage
	// your queue.
    private int numObj;

	private Random R;  // This is needed so that you can shuffle your data

	public MultiDS(int sz)
	{
        //what happens if I change object to T??????
		theData = (T []) new Object[sz];  // Note how this is created
		R = new Random();
        numObj = 0;
		// You will also need to initialize your other state variable(s).
		// (i.e. setting your logical size to 0).
		// You will need to create a new array in a similar way in your
		// resize() method.
	}

    public T card()
    {
        return theData[0];
    }
    public T card1()
    {
        return theData[1];
    }
    //why MultDS<Card> didn't work
    public void fill(MultiDS<T> x)
    {
        System.out.println("\tGetting and shuffling the pile for Player1");
        if (!x.empty())
        {
            while (!x.empty())
            {
                this.addItem(x.removeItem());
            }
            this.shuffle();
        }
        else
        {
            System.out.println("\nPlayer1 is out of cards!\nPlayer2 wins!!");
            System.exit(0);
        }

    }
    public void fill2(MultiDS<T> x)
    {
        System.out.println("\tGetting and shuffling the pile for Player2");
        if (!x.empty())
        {
            while (!x.empty())
            {
                this.addItem(x.removeItem());
            }
            this.shuffle();
        }
        else
        {
            System.out.println("\nPlayer2 is out of cards!\nPlayer1 wins!!");
            System.exit(0);
        }

    }

    public String toString()
    {
        StringBuilder info = new StringBuilder("Contents: \n");
        for (T x : theData)
        {
            if (x == null)
            {
                break;
            }
            info.append(x);
            info.append(" ");
        }
        return info.toString();
    }
    public void resize()
    {
        T[] temp = (T []) new Object[theData.length * 2];
        for (int i = 0; i < numObj; i++)
        {
            temp[i] = theData[i];
        }
        theData = temp;
    }
    // Add a new Object to the PrimQ<T> in the next available location.  If
	// all goes well, return true.  The iterface allows for this method to
	// return false if the add does not succeed.  [However, in your implementation
	// it should always succeed (i.e you should resize your array if necessary)]

    public boolean addItem(T item)
    {
        if (theData.length == numObj)
        {
            resize();
            theData[numObj] = item;
            numObj++;
            if (theData.length == numObj)
            {
                resize();
            }
            return true;
        }
        else
        {
            theData[numObj] = item;
            numObj++;
            if (theData.length == numObj)
            {
                resize();
            }
            return true;
        }
    }

    //Removes item in first index and shifts everything to the left.
    //Returns the item removed.
	public T removeItem()
    {
        if (numObj == 0)
        {
            return null;
        }
        else
        {
            if (theData.length == numObj)
            {
                resize();
            }
            T temp = theData[0];
            for (int i = 0; i < numObj; i++)
            {
                theData[i] = theData[i+1];
            }
            theData[numObj-1] = null;
            numObj--;
            return temp;
        }
    }

    //Useless by itself unless used with accompanying source file. For eg: while (MultiDS.full()).
	public boolean full()
    {
        if (theData.length == numObj)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Useless by itself unless used with accompanying source file. For eg: while (MultiDS.empty()).
	public boolean empty()
    {
        if (numObj == 0)
        {
            return true;
        }
        else
        return false;
    }

	// Return the number of items currently in the PrimQ
	public int size()
    {
        return numObj;
    }

    //Not finished
	public void clear()
    {
        theData = (T []) new Object[10];
        numObj = 0;
    }

    //Makes a new T[] array and puts first item in original array into T[]'s last index.
    //Puts second item into T[]'s second to last index.......and so on.
    //Set original array to T[].
	public void reverse()
    {
        T[] temp = (T []) new Object[theData.length];
        int max = numObj;
        for (int i = 0; i < numObj; i++)
        {
            temp[i] = theData[max - 1];
            max--;
        }
        theData = temp;
    }

	// Remove the logical last item of the DS and put it at the
	// front.  As with reverse(), this can be done physically in
	// different ways depending on the underlying implementation.
	public void shiftRight()
    {
        T temp = theData[numObj - 1];
        for (int i = numObj - 1; i > 0; i--)
        {
            theData[i] = theData[i - 1];
        }
        theData[0] = temp;
    }

	// Remove the logical first item of the DS and put it at the
	// end.  As above, this can be done in different ways.
	public void shiftLeft()
    {
        T temp = theData[0];
        for (int i = 0; i < numObj - 1; i++)
        {
            theData[i] = theData[i + 1];
        }
        theData[numObj - 1] = temp;
    }

	public void shuffle()
    {
        int i = numObj;
        int roll;
        T temp;

        //YAY it's the fitcher-yates shuffle again. ty youtube.
        while ((--i) > 0)
        {
            roll = R.nextInt(i);
            temp = theData[roll];
            theData[roll] = theData[i];
            theData[i] = temp;
        }
    }
	// Reorganize the items in the object in a pseudo-random way.  The exact
	// way is up to you but it should utilize a Random object (see Random in
	// the Java API).  Thus, after this operation the "oldest" item in the
	// DS could be arbitrary. Note that you should already be familiar with
	// this idea since you have done it in previous assignments.



	// Below you should implement all of the methods in the PrimQ<T> and Reorder
	// interfaces.  See the details for these methods in files PrimQ.java and
	// Reorder.java.  See how these are actually used in the test program Assig4A.java.
	// Once you have completed this class the program Assig4A.java should compile and
	// run and give output identical to that shown in file A4Out.txt (except for the
	// order of the data after shuffling, since that should be random).

    //T temp = theData[numObj - 1];
    //for (int i = numObj - 1; i > 0; i--;)
    //{
    //    theData[i] = theData[i - 1];
    //}
    //theData[0] = temp;
}
