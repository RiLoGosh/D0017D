import java.util.Scanner;
import java.util.Random;

/**
 * This program will generate random integers based on user input and then sort those numbers into an array of ascending order
 * as well as separating and counting even and odd numbers.
 *
 * 1. intitialise all variables and arrays.
 * 2. Get input from user about how many random numbers.
 * 3. Create an int array of that size and then assign all of its random numbers using the randomNumber function.
 * 4. Prints array.
 * 5. Sort the array in ascending order before separating evens and odds.
 *    5.1. Use the sort function for this which will work by repeatedly comparing each element to array's previous elements and
 *         switching when appropriate.
 * 6. Loop through array and assign all even numbers into the a new array as you loop through (will be in ascending order since alredy sorted).
 * 7. Loop through array again and assign all odd numbers into the end of the array in a reverse order so that it will read as descending.
 * 8. Print the new array with a "-" symbol after the even numbers to show the separation.
 * 9. Print out how many even and odd numbers there are of the user's input.
 *
 * @author Simon Hocker, simhoc-1
 */


public class Uppgift4
{

    public static void main(String[] args)
    {
        // Scanner
        Scanner myscan = new Scanner(System.in);

        // Initialise all variables
        int input;
        int array[];
        int newarray[];
        int evencounter;
        int oddcounter;

        System.out.print("Hur många slumptal i intervallet 0 - 999 önskas? ");
        input = myscan.nextInt();

        // Assign the variables
        array = new int[input];
        newarray = new int[input];
        evencounter = 0;
        oddcounter = 0;

        // Inserts a random integer into each index of the array
        for (int i = 0; i < array.length; i++)
        {
            array[i] = randomNumber();
        }

        // Prints out unsorted array
        System.out.println();
        System.out.println("Här är de slumpade talen: ");
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }

        // Sorts the entire array in ascending order
        array = sort(array);

        // Puts each even number into the new array in ascending order
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] % 2 == 0)
            {
                newarray[evencounter] = array[i];
                evencounter++;
            }
        }

        // Puts each odd number into the end of the new array in a reverse ascending order (Descending).
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] % 2 == 1)
            {
                newarray[newarray.length - 1 - oddcounter] = array[i];
                oddcounter++;
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("Här är de slumpade talen ordnade: ");

        // Prints the array and when all even numbers are printed, it will print a "-" symbol to differentiate between odd and even.
        for (int i = 0; i < newarray.length; i++)
        {
            if (i == evencounter)
            {
                System.out.print("- ");
            }
            System.out.print(newarray[i] + " ");
        }

        // Prints out the input of the user along with how many of the elements were even and how many were odd.
        System.out.println();
        System.out.println();
        System.out.println("Av ovanstående " + input + " tal var " + evencounter + " jämna och " + oddcounter + " udda.");


    }

    // This function will generate a random number between 0 - 999.
    public static int randomNumber()
    {
        Random random = new Random();
        return random.nextInt(999);
    }

    // This function will sort an int array by repeatedly comparing each element to all of its previous elements and
    // switches if there if earlier element is larger.
    public static int[] sort (int array[])
    {
        for (int i = 1; i < array.length; i++)
        {
            int temp = array[i];
            int j;

            for ( j = i; j > 0 && array[j - 1] > temp; j--)
            {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
        return array;
    }
}
