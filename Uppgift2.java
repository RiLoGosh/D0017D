import java.util.Scanner;

/**
 * This program will calculate the production and cost of solar cell panels.
 *
 * 1. Assign the many constants of the setup (panel sizes, solinstrålning, verkningsgrad, elpriset, antal) as well as initialise variables.
 * 2. Create input scanner and save the user's inputs as 3 strings.
 * 3. Run each string through a new iteration of the scanner and delimiter for the respective data formats (delimit "-" for the date for example).
 *    The times of sunrise and sunset will be saved as doubles for ease of calculation later.
 * 4. Conduct a calculation of total hours of sunlight and save it as soltimmar.
 * 5. Use an if statement to check that both the month is 6th or 7th and that the total soltimmar are positive (if
 *    negative then that means that the entered sundown time was before the sunrise time which is incorrect input).
 * 6.
 *      6.1 If previous if statement was true, then conduct calculation for energy production of the solar panels and
 *          price and then print those values.
 *      6.2 If the statement was false, then that means that some part of the input was either incorrect or at least not relevant
 *          for our purposes and so we go to our "else" statement that prints an error to the console.
 *
 * @author Simon Hocker, simhoc-1
 */

public class Uppgift2
{

    public static void main(String[] args)
    {
        //Constants
        final double PANEL_BREDD = 1.7;
        final double PANEL_HOJD = 1;
        final double SOL_INSTRÅLNING = 166;
        final double VERKNINGS_GRAD = 0.2;
        final double EL_PRISET = 0.9;
        final double ANTAL = 26;

        //Variables
        String date;
        String sunrise;
        String sundown;
        double risehour;
        double risemin;
        double downhour;
        double downmin;
        int month;
        int day;
        double soltimmar;
        double produktion;
        double försäljning;

        //Scanner
        Scanner myscan = new Scanner(System.in);

        // Acquires all of the user's inputs
        System.out.print("Skriv dagens datum [mm-dd]> ");
        date = myscan.next();
        System.out.print("Skriv in tidpunkt soluppgång [hh:mm]> ");
        sunrise = myscan.next();
        System.out.print("Skriv in tidpunkt solnedgång [hh:mm]> ");
        sundown = myscan.next();
        myscan.close();

        // Process Date
        myscan = new Scanner(date).useDelimiter("-");
        month = myscan.nextInt();
        day = myscan.nextInt();
        myscan.close();

        // Process Sunrise
        myscan = new Scanner(sunrise).useDelimiter(":");
        risehour = myscan.nextDouble();
        risemin = myscan.nextDouble();
        myscan.close();

        // Process Sundown
        myscan = new Scanner(sundown).useDelimiter(":");
        downhour = myscan.nextDouble();
        downmin = myscan.nextDouble();
        myscan.close();

        System.out.print("============================================================\n");

        // Converts the time into minutes and then finds difference before returning it to hours
        soltimmar = (((downhour * 60) + downmin) - ((risehour * 60) + risemin)) / 60;

        // Checks that the months are either 06 or 07 and that sundown is after sunset
        if ((month == 6 || month == 7) && (soltimmar > 0))
        {
            // Conducts the calculation for the production and also converts it to kWh
            produktion = SOL_INSTRÅLNING * soltimmar * (PANEL_BREDD * PANEL_HOJD) * VERKNINGS_GRAD * ANTAL / 1000;
            // Sales Calculation
            försäljning = EL_PRISET * produktion;
            // Result Printing
            System.out.print("Soltimmar : ");
            System.out.printf("%.2f", soltimmar);
            System.out.println(" timmar");
            System.out.print("Produktionen " + day + "/" + month + " är: ");
            System.out.printf("%.2f", produktion);
            System.out.print(" kWh till ett värde av: ");
            System.out.printf("%.2f", försäljning);
            System.out.print(" kr");
        }
        else
        {
            // Will do this if any of the input doesn't fit our requirements
            System.out.println("Felaktiga Värden.");
        }
        System.out.println();
    }
}
