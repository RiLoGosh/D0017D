import java.util.Scanner;

/**
 * This program will contain a number of methods for conducting various mathematical operations based on the user's input.
 * The following mathematical calculations are supported in the program:
 * - Area & Volume of a cone calculation
 * - The handling of fractions
 * -
 *
 * 1. Initialise Variables
 * 2. Create 2 while loops for handling the testing of area/volume and for fractions
 *    2.1. Check if input is -1 (my declared integer for a 'q' input and break the loop in that case
 *    2.2  Save the input to the related variable if input is confirmed as a valid integer
 *    2.3  Attempt to use those inputs in all of the mathematical methods
 * 3. Methods for Calculating:
 *    3.1 Area with just the radius conducts the math using Math.PI
 *    3.2 Area with radius and height conducts math using pythagoran theorem function along with Math.PI
 *    3.3 Pythagoran theorem function uses 2 integers and returns float for use with previous area function
 *    3.4 Volume function conducts that math with supplied ints and Math.PI
 *    3.5 Fraction begins by verifying that neither nominator or denominator is 0 and then does math to format the fraction and then save it in array
 *    3.6 sgd begins by validating that a is bigger than b and then goes into its while loop and breaks down the numbers until sgd is found
 *    3.7 printFraction first checks certain components of the fraction array and then prints out appropriate parts of it.
 *    3.8 input will read one item at a time from user and then use a try catch feature to attempt to turn it into an int and if not it returns an "invalid" error
 *    while also saving the 'q' entry as a -1 for later recognition.
 *
 *
 * @author Simon Hocker, simhoc-1
 */



public class Uppgift5 {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args)
    {

        int input;
        // Area & volume
        int radius;
        int height;


        // Fraction
        int nominator;
        int denominator;

        Scanner myscan = new Scanner(System.in);

        radius = 0;
        height = 0;

        nominator = 0;
        denominator = 0;

        System.out.println("----------------------------------\n" + "# Test av area och volymmetoderna\n" + "----------------------------------");

        // Area & Volume loop
        while(true)
        {
            // Get first number
            input = input();

            // If input was q
            if (input == -1)
            {
                break;
            }

            // Add integer as radius
            if (input != -2)
            {
                radius = input;
            }

            // Get second number
            input = input();

            // If input was q
            if (input == -1)
            {
                break;
            }

            // Add integer as height
            if (input != -2)
            {
                height = input;
            }

            System.out.println("r = " + radius + " h = " + height);
            System.out.println("Basytans area:    " + area(radius));
            System.out.println("Mantelytans area: " + area(radius, height));
            System.out.println("Volym:            " + volume(radius, height));
            System.out.println();
        }

        System.out.println("----------------------------------\n" + "# Test av bråktalsmetoderna\n" + "----------------------------------");

        // Fractions
        while(true)
        {
            // Get first number
            input = input();

            // If input was q
            if (input == -1)
            {
                break;
            }

            // Add integer as denominator
            if (input != -2)
            {
                nominator = input;
            }

            // Get second number
            input = input();

            // If input was q
            if (input == -1)
            {
                break;
            }

            // Add integer as denominator
            if (input != -2)
            {
                denominator = input;
            }

            System.out.print(nominator + "/" + denominator + " = ");
            printFraction(fraction(nominator, denominator));

        }

    }

    /**
     * Calculates the area of a circle using radius.
     * @param radius holds the entered value of circle radius
     * @return area of the circle
     */
    public static float area(int radius)
    {
        return (float)Math.round(((Math.PI * (radius * radius))) * 100) / 100;
    }

    /**
     * Calculates the area of a cone using radius and height.
     * @param radius holds the entered value of circle radius
     * @param height holds the entered value of cone height
     * @return area of the cone
     */
    public static float area(int radius, int height)
    {
        return (float)Math.round(((Math.PI * radius * (pythagoras(radius, height)))) * 100) / 100;
    }

    /**
     * Calculates the length of a hypotenuse of a triangle using Pythagoras
     * @param sideA holds the entered value of the first side
     * @param sideB holds the entered value of the second side
     * @return the length of the hypotenuse calculated using Pythagoras
     */
    public static float pythagoras(int sideA, int sideB)
    {
        return ((float)(Math.sqrt((sideA * sideA) + (sideB * sideB))));
    }

    /**
     * Calculates the volume of the cone.
     * @param radius holds the entered value the radius of the cone.
     * @param height holds the entered value of cone height.
     * @return the volume of the cone.
     */
    public static float volume(int radius, int height)
    {
        return (float)Math.round((((Math.PI * (radius*radius) * height) / 3)) * 100) / 100;
    }

    /**
     * Takes in 2 numbers and presents them as a fraction.
     * @param numerator holds the entered value the numerator
     * @param denominator holds the entered value of the denominator of the fraction.
     * @return a 3 element array that describes a fraction using the entered numerator and denominator.
     */
    public static int[] fraction(int numerator, int denominator)
    {
        int sgd;
        int[] array = new int[3];
        if (denominator == 0)
        {
            return null;
        }
        else if(numerator == 0)
        {
            return new int[]{0, 0, 0};
        }


        sgd = sgd(numerator, denominator);

        array[0] = numerator / denominator;
        array[1] = (numerator % denominator) / sgd;
        array[2] = denominator / sgd;

        return array;
    }

    /**
     * Finds the lowest common multiple between two numbers.
     * @param a holds the first number.
     * @param b holds the second number.
     * @return an integer that is determined to be the lowest common multiple.
     */
    public static int sgd(int a, int b)
    {
        int c;

        // If a is smaller than b then we switch them.
        if (!(a > b))
        {
            c = a;
            a = b;
            b = c;
        }

        // While b isn't 0, we continue breaking the numbers into their smaller components until we find the GCD
        while(b != 0)
        {
            c = a % b;
            a = b;
            b = c;
        }

        return a;
    }

    /**
     * Takes in a fraction described as an array and prints out the components in proper fashion.
     * @param parts is a 3-element array describing a fraction
     */
    public static void printFraction(int[] parts)
    {
        if (parts == null)
        {
            System.out.println("Error");
        }
        else if(parts[0] == 0)
        {
            if (parts[1] == 0)
            {
                System.out.println(0);
            }
            else
            {
                System.out.println(parts[1] + "/" + parts[2]);
            }
        }
        else if(parts[1] == 0)
        {
            System.out.println(parts[0]);
        }
        else
        {
            System.out.println(parts[0] + " " + parts[1] + "/" + parts[2]);
        }
    }

    /**
     * Each time the function is called, it will return the next integer in the input.
     * It does so by recursively checking through each item in the input and only returning it when
     * it is an integer and if not then it will call recursively call itself until an integer or a "q" is found.
     * @return an integer that is determined to be appropriate input or a -1 if "q" was entered.
     */
    public static int input()
    {
        String input = userInput.next();
        int number;

        if (input.equals("q"))
        {
            return (-1);

        }

        try
        {
            number = Math.abs(Integer.parseInt(input));
            return number;
        }
        catch (Exception e)
        {
            System.out.println("Invalid input.");
            return input();
        }

    }
}
