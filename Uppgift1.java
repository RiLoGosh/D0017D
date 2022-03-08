/**
 * This program will calculate the charge efficiency of an electric car.
 *
 * 1. Assign the ström (10, 16, 32) and spänning (230, 400) constants.
 * 2. Prepare initial print formatting.
 * 3. Create 3 functions for calculating and handling of results.
 *    3.1. BeraknaLadd is a method for calculating the Ladd Effekt with 2 parameters(ström and spänning).
 *    3.2. BeraknaLaddtid is a method that divides the battery limit of the car (35.8 kWh) by the result of BeraknaLadd.
 *    3.3. myRound is a method that takes in any number and rounds it off to 2 decimal points.
 *
 * @author Simon Hocker, simhoc-1
 */
import java.util.*;

public class Uppgift1
{

    public static void main(String[] args)
    {

        //Save all of the mathematical values as constants
        final double AMPS_1 = 10;
        final double AMPS_2 = 16;
        final double AMPS_3 = 32;
        final double VOLTS_1 = 230;
        final double VOLTS_2 = 400;

        //Printing format and calls to relative math functions
        System.out.print("\n");
        System.out.print("Batteri 35.8(kWh)\n");
        System.out.print("Ström(A) \t Spänning(V) \t Laddeffekt(kW) \t Laddningstid(h)\n");
        System.out.print("============================================================\n");
        System.out.print((int) AMPS_1 + " \t\t\t " + (int) VOLTS_1 + " \t\t\t " + beraknaLadd(AMPS_1, VOLTS_1) + " \t\t " + beraknaLaddtid(beraknaLadd(AMPS_1, VOLTS_1)) + "\n");
        System.out.print((int) AMPS_2 + " \t\t\t " + (int) VOLTS_1 + " \t\t\t " + beraknaLadd(AMPS_2, VOLTS_1) + " \t\t " + beraknaLaddtid(beraknaLadd(AMPS_2, VOLTS_1)) + "\n");
        System.out.print((int) AMPS_1 + " \t\t\t " + (int) VOLTS_2 + " \t\t\t " + beraknaLadd(AMPS_1, VOLTS_2) + " \t\t " + beraknaLaddtid(beraknaLadd(AMPS_1, VOLTS_2)) + "\n");
        System.out.print((int) AMPS_2 + " \t\t\t " + (int) VOLTS_2 + " \t\t\t " + beraknaLadd(AMPS_2, VOLTS_2) + " \t\t " + beraknaLaddtid(beraknaLadd(AMPS_2, VOLTS_2)) + "\n");
        System.out.print((int) AMPS_3 + " \t\t\t " + (int) VOLTS_2 + " \t\t\t " + beraknaLadd(AMPS_3, VOLTS_2) + " \t\t " + beraknaLaddtid(beraknaLadd(AMPS_3, VOLTS_2)) + "\n");

    }

    /*
    This function calculates the "Laddeffekt" of each combination of amps and voltages.
    It does this by conducting the following steps:
        1. Uses an if statement to determine which voltage we are using (spanning).
        2. Conducts a specific mathematical calculation depending on result of the prior if statement.
        3. Divides the number by 1000 (because we want answer in kiloWatts) and then passes that number to the rounding function.
        4. Returns the result of the rounding function.
     */
    public static double beraknaLadd(double strom, double spanning)
    {
        if (spanning == 230)
        {
            return (avrunda((spanning * strom) / 1000));
        }
        else if (spanning == 400)
        {
            return (avrunda((spanning * strom * (java.lang.Math.sqrt(3))) / 1000));
        }
        return 0;
    }

    /*
    This function calculates the "Laddtid" of a certain "Laddeffekt.
    It does this by conducting the following steps:
        1. Conducts a mathematical equation --> total capacity of the battery (35.8 kWh) divided by the "Laddeffekt"
        2. Returns that result.
     */
    public static double beraknaLaddtid(double ladd)
    {
        return (avrunda(35.8 / ladd));
    }

    /*
    This function rounds off a number to the 2 least significant decimals.
    It does this by conducting the following steps:
        1. Multiplies the number by 1000, so that the last non decimal digit is the one that tells us if we need to round up or down.
        2. Converts (casts) the number to an "int" data type which drops the decimals.
        3. Uses modulus to find remainder and the deciding value of whether number will be rounded up or down.
        4. Checks that remainder in an if statement to see if it is more or less than 5.
        5. Result of the prior if statement tells us whether to subtract that remainder or add the difference between it and 10.
        6. The result of the previous step is saved as a double.
        7. Double is now divided by 1000 to revert to its initial form but with properly rounded off values.
        8. Returns the new number.
     */
    public static double avrunda(double number)
    {
        number = number * 1000;
        int tempnum = (int) number;
        int remainder = tempnum % 10;

        if (remainder >= 5)
        {
            tempnum = tempnum + (10 - remainder);
        }
        else
        {
            tempnum = tempnum - remainder;
        }

        double newnumber = tempnum;
        newnumber = newnumber / 1000;

        return newnumber;
    }
}
