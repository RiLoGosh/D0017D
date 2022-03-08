import java.util.Scanner;
import java.util.Random;

/**
 * This program will simulate the game known as "12".
 * The objective of the game is to roll 3 dice where the sum of them shall result in 12.
 *
 * 1. Initialise all the variables.
 * 2. Form one big while loop so game can repeat itself until that loop is broken.
 * 3. Create 3 nested while loops, each one to represent one die result.
 * 4. Each while loop will contain if statements to determine if result is valid (is numbers 1-3 and hasn't been selected previously).
 *    This will also include a check for "q" which would then break the outer loop, ending the game.
 * 5. Create separate roll function that will conduct the randomisation of numbers. This function will be called inside the nested while loops.
 * 6. The 3rd nested while loop will also check if the game has ended (since game can be won or lost after a minimum of 2 rolls) and
 *    may not run as a result. If game has already been won after the second roll (2 rolls of 6) then the third while loop will be skipped.
 *
 * @author Simon Hocker, simhoc-1
 */

public class Uppgift3 {

    public static void main(String[] args)
        {


        // Scanner
        Scanner myscan = new Scanner(System.in);

        // Initialise variables
        String input1 = null;
        String input2 = null;
        String input3 = null;

        //Game Manager Variables
        int wins;
        int losses;
        boolean gamedone;

        //Dice Values
        int d1;
        int d2;
        int d3;

        wins = 0;
        losses = 0;

        System.out.println("Välkommen till spelet 12. Du ska slå 1-3 tärningar och försöka få summan 12...");

        //Main Game loop - Will repeat until broken out of
        outerloop:
        while(true){

            // Reset round-dependent variables
            gamedone = false;
            d1 = 0;
            d2 = 0;
            d3 = 0;

            //Dice Roll 1 - Will only break out once a 1/2/3 has been entered or will end outer loop if "q" is entered.
            System.out.print("Ange vilken tärning du vill slå [1,2,3](avsluta med q): ");
            while(true) {
                input1 = myscan.nextLine();
                if (input1.equals("1")) {
                    d1 = roll();
                    System.out.println();
                    break;
                } else if (input1.equals("2")) {
                    d2 = roll();
                    System.out.println();
                    break;
                } else if (input1.equals("3")) {
                    d3 = roll();
                    System.out.println();
                    break;
                } else if (input1.equals("q")) {
                    break outerloop;
                } else {
                    // Will repeat loop if invalid input
                    System.out.println("Inkorrekt input, försök igen [1, 2, 3, q].");
                }
            }
            // Result is reported and asks for next input
            System.out.print(d1 + " " + d2 + " " + d3 + " sum: " + (d1+d2+d3) + " #vinst: " + wins + " #förlust: " + losses + "\n");
            System.out.print("Ange vilken tärning du vill slå [1,2,3](avsluta med q): ");

            //Dice Roll 2
            while(true)
            {
                input2 = myscan.nextLine();
                if (input2.equals("1") && !input2.equals(input1))
                {
                    d1 = roll();
                    System.out.println();
                    break;
                }
                else if (input2.equals("2")  && !input2.equals(input1))
                {
                    d2 = roll();
                    System.out.println();
                    break;
                }
                else if (input2.equals("3")  && !input2.equals(input1))
                {
                    d3 = roll();
                    System.out.println();
                    break;
                }
                else if (input2.equals("q"))
                {
                    break outerloop;
                }
                else
                {
                    // Will repeat this loop if invalid input
                    System.out.println("Inkorrekt input, försök igen [1, 2, 3, q].");
                }
            }

            // Checks if game has already been won with 2 dice rolls. Max value here is 6 and 6 so no need to check if game is lost.
            if (d1+d2+d3 == 12)
            {
                gamedone = true;
            }
            else
            {
                System.out.print(d1 + " " + d2 + " " + d3 + " sum: " + (d1+d2+d3) + " #vinst: " + wins + " #förlust: " + losses + "\n");

            }

            //Dice Roll 3
            System.out.print("Ange vilken tärning du vill slå [1,2,3](avsluta med q): ");
            while(!gamedone)
            {  // Will not run if game is done due to rolling 6 and 6 previously.
                input3 = myscan.nextLine();
                if (input3.equals("1") && !input3.equals(input1) && !input3.equals(input2))
                {   // If input is 1 and die 1 has not been rolled.
                    d1 = roll();
                    System.out.println();
                    break;
                }
                else if (input3.equals("2")  && !input3.equals(input1) && !input3.equals(input2))
                {   // If input is 2 and die 2 has not been rolled.
                    d2 = roll();
                    System.out.println();
                    break;
                }
                else if (input3.equals("3")  && !input3.equals(input1) && !input3.equals(input2))
                {   // If input is 3 and die 3 has not been rolled.
                    d3 = roll();
                    System.out.println();
                    break;
                }
                else if (input3.equals("q"))
                {    // Exits game by breaking outer loop.
                    break outerloop;
                }
                else
                {
                    System.out.println("Inkorrekt input, försök igen [1, 2, 3, q].");    // Will repeat loop if input is invalid.
                }
            }

            // Counts the sum and increments wins or losses counter.
            if (d1+d2+d3 == 12){
                wins++;
            }
            else if (d1+d2+d3 > 12){
                losses++;
            }
            System.out.print(d1 + " " + d2 + " " + d3 + " sum: " + (d1+d2+d3) + " #vinst: " + wins + " #förlust: " + losses + "\n");
            System.out.println("Nästa Omgång"); // End of loop and so new round begins!

        }
        System.out.println("Avslutar Spel!");
    }

    // This function creates a random int ranging rom 0-5 and will increment it by 1 to make it a valid dice roll.
    public static int roll(){
        Random random = new Random();
        return (random.nextInt(6)) + 1;
    }
}
