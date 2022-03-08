import java.util.Random;
import java.util.Scanner;

/**
 * Detta program implementerar ett system för att hantera användare samt projekten de jobbar i.
 *
 * 1. Initialisera variabler.
 * 2. Switch statement som hanterar de 7 olika kommandon.
 * 3. En while loop runt om Switch statement som refreshar meny metoden så användaren kan få se menyn mellan varje kommando.
 * 4. Användarens input bestämmer vilken case som görs och därmed vilka metoder som blir anropade
 * 5. Varje metod får de relevanta matriserna och variablarna som parametrar. Se varje metod för specifika detaljer.
 *
 * @author Simon Hocker, simhoc-1
 */

public class Tentamen_VT21_simhoc1 {

    private static Scanner myscan = new Scanner(System.in);

    public static void main(String[] args)
    {
        final int MAX_ANSTALLD_NR = 10000;
        String userInput;
        String[][] anställda;
        String[][][] projekt;

        anställda = new String[10001][2];

        /**
         * Här har jag gjort ett antagande att vi inte behöver hantera fler än 1000 olika projekt
         * samt att inget projekt kommer att ha fler än 10 anställda.
         */
        projekt = new String[1000][10][3];


        outerloop:
        while(true)
        {
            System.out.println();
            userInput = menu();

            switch (userInput)
            {
                case "1":
                    System.out.println();
                    System.out.println("Lägg till anställd har valts.");
                    laggTillAnstalld(anställda, MAX_ANSTALLD_NR);
                    break;

                case "2":
                    System.out.println();
                    System.out.println("Skriver ut lista på anställda.");
                    printAnställda(anställda);
                    break;

                case "3":
                    System.out.println();
                    System.out.println("Starta ett projekt har valts.");
                    startaProjekt(projekt, anställda);
                    break;

                case "4":
                    System.out.println();
                    System.out.println("Tidsrapportering har valts.");
                    tidsRapportering(projekt);
                    break;

                case "5":
                    System.out.println();
                    System.out.println("Avsluta Projekt har valts.");
                    avslutaProjekt(projekt);
                    break;

                case "6":
                    System.out.println();
                    System.out.println("Skriver ut lista på projekt.");
                    printProjekt(projekt, anställda);
                    break;

                case "q":
                    System.out.println();
                    System.out.println("Avslutar programmet.");
                    break outerloop;

            }
        }
    }

    /**
     * Skriver ut alla användarens val som en meny.
     * @return user input från input().
     */
    public static String menu()
    {
        String userInput;
        System.out.println("Välj ett kommando.");
        System.out.println("1. Lägg till anställd.");
        System.out.println("2. Skriv ut lista på anställda.");
        System.out.println("3. Starta ett projekt.");
        System.out.println("4. Rapportera tid i ett projekt.");
        System.out.println("5. Avsluta ett projekt.");
        System.out.println("6. Skriv ut projektlista.");
        System.out.println("q. Avsluta programmet.");
        userInput = input();

        while(!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4") && !userInput.equals("5") && !userInput.equals("6") && !userInput.equals("q"))
        {
            System.out.println("Input not valid. Välj ett kommando genom att ange den relevanta tecknet ovan.");
            userInput = input();
        }

        return userInput;
    }

    /**
     * Tar emot input från användaren, oavsett dess form
     * @return vad som användaren än skrev
     */
    public static String input ()
    {
        String input;
        input = myscan.next();
        return input;
    }

    /**
     * Tar in heltals input från användaren
     * @return input i formen av ett heltal och kommer skicka tillbaka "-1" som representation av "q"
     */
    public static int intInput ()
    {
        String input;
        int number;

        while (true)
        {
            input = myscan.next();

            // Jag gör en antagelse att vi aldrig kommer behöva skriva in negativa värden (som tex -1) i detta system och kan därför
            // använda det som ett kännetecken för "q".
            if (input.equals("q"))
            {
                return -1;
            }
            else
            {
                try
                {
                    number = Integer.parseInt(input);
                    return number;
                }

                catch (Exception e)
                {
                    System.out.println("Måste vara heltal, ange igen.");
                }
            }
        }
    }

    /**
     * Lägger till en anställd i systemet samt dens omfattning
     * @param anstallda håller all info om de anställda
     * @param MAX_NUMMER är en konstant som håller värdet för den övre gränsen på siffrorna som slumpas
     */
    public static void laggTillAnstalld(String[][] anstallda, int MAX_NUMMER)
    {
        String namn;
        int omfattning;
        int nummer;

        System.out.println();
        System.out.println("Ange namn på anställda.");
        namn = input();
        while(hasName(anstallda, namn))
        {
            /**
             * Här gör jag ett antagande att det inte får finnas fler än en anställd med samma namn.
             */
            System.out.println("Denna person är redan anställd. Ange ett nytt namn.");
            namn = input();
        }

        System.out.println("Ange omfattning.");
        omfattning = intInput();
        while (omfattning < 1 || omfattning > 100)
        {
            System.out.println("Den angedda siffran representerar % och måste vara mellan 0-100. Försök igen.");
            omfattning = intInput();
        }

        nummer = random(MAX_NUMMER);
        while(hasNumber(anstallda, nummer))
        {
            System.out.println("Anställningsnumret existerade redan, slumpas ett nytt.");
            nummer = random(MAX_NUMMER);
        }

        anstallda[nummer][0] = namn;
        anstallda[nummer][1] = Integer.toString(omfattning);

    }

    /**
     * Skriver ut information om alla anställda
     * @param anställda håller all info om de anställda
     */
    public static void printAnställda(String[][] anställda)
    {
        System.out.println();
        System.out.println("Nr     Namn        Omfattning");
        for(int i = 0; i < anställda.length; i++)
        {
            if (anställda[i][0] != null)
            {
                System.out.printf("%-7d", i);
                System.out.printf("%-12s", anställda[i][0]);
                System.out.printf("%-2s", anställda[i][1]);
                System.out.print("%");
                System.out.println();
            }
        }
    }

    /**
     * Startar ett projekt och loggar det i matrisen samtidigt som man lägger till arbetare till projektet
     * @param projekt håller info om alla projekt
     * @param anställda håller all info om de anställda
     */
    public static void startaProjekt(String[][][] projekt, String[][] anställda)
    {
        String namn;
        int anställningsnummer;
        int omfattning;
        int i = 0;
        int latestWorker = 1;

        System.out.println("Ange ett namn för projektet.");
        namn = input();
        while(hasProjektNamn(projekt, namn))
        {
            System.out.println("Det projektnamnet är redan taget, ange ett nytt.");
            namn = input();
        }

        // hittar första tomma platsen i matrisen.
        while(projekt[i][0][0] != null){
            i++;
        }

        projekt[i][0][0] = namn;
        projekt[i][0][1] = "Pågående";

        /**
         * Här gör jag ett antagande att det varje projekt måste ha minst en anställd som jobbar på det.
         * Därför kör jag en run av att lägga in en anställd utan att man kan avsluta först så att vi får minst en.
         */
        System.out.print("Anställnings ID: ");
        anställningsnummer = intInput();
        while(!(hasNumber(anställda, anställningsnummer)))
        {
            System.out.println("Det angivna ID numret är antingen ogiltigt eller finns ej med i vår anställningslista. Försök igen.");
            anställningsnummer = intInput();
        }

        System.out.println();
        System.out.print("Omfattning: ");
        omfattning = intInput();
        while(omfattning < 1 || omfattning > 100)
        {
            System.out.println("Den angedda siffran representerar % och måste vara mellan 0-100. Försök igen.");
            omfattning = intInput();
        }

        // Lägger till infon av senaste arbetaren.
        projekt[i][latestWorker][0] = Integer.toString(anställningsnummer);
        projekt[i][latestWorker][1] = Integer.toString(omfattning);
        projekt[i][latestWorker][2] = Integer.toString(0);
        latestWorker++;


        outerloop:
        while(true)
        {
            System.out.print("Anställnings ID: ");
            anställningsnummer = intInput();

            if (anställningsnummer == -1)
            {
                break;
            }

            while(!(hasNumber(anställda, anställningsnummer)))
            {

                System.out.println("Det angivna ID numret är antingen ogiltigt eller finns ej med i vår anställningslista. Försök igen.");
                anställningsnummer = intInput();
                if (anställningsnummer == -1)
                {
                    break outerloop;
                }
            }

            System.out.println();
            System.out.print("Omfattning: ");
            omfattning = intInput();
            while(omfattning < 1 || omfattning > 100)
            {
                System.out.println("Den angedda siffran representerar % och måste vara mellan 0-100. Försök igen.");
                omfattning = intInput();
            }

            // Lägger till infon av senaste arbetaren.
            projekt[i][latestWorker][0] = Integer.toString(anställningsnummer);
            projekt[i][latestWorker][1] = Integer.toString(omfattning);
            projekt[i][latestWorker][2] = Integer.toString(0);
            latestWorker++;
        }
    }

    /**
     * Tillåter en användare att rapportera in arbetstimmar som de lagt ned på projektet
     * @param projekt håller info om alla projekt
     */
    public static void tidsRapportering(String[][][] projekt)
    {
        String projektnamn;
        int anställningsnummer;
        int projektindex = -1;
        int omfattningstimmar;
        int totalaTimmar;
        int j = 1;

        // Denna loop existerar endast för att ge funktionen att avsluta denna metod om det visar sig att det angivna projektet är avslutet.
        thisloop:
        while(true)
        {
            System.out.print("Projektnamn: ");
            projektnamn = input();
            while (!(hasProjektNamn(projekt, projektnamn))) {
                System.out.println("Projekt med det namnet kunde ej hittas. Försök igen.");
                projektnamn = input();
            }

            // sparar index av projektet i vår matris
            for (int i = 0; i < projekt.length; i++) {
                if (projektnamn.equals(projekt[i][0][0])) {
                    projektindex = i;
                }
            }

            if (projekt[projektindex][0][1].equals("Avslutad")) {
                System.out.println("Det projektet är avslutet. Du kan inte rapportera timmar där.");
                break thisloop;
            }

            System.out.println();
            System.out.print("Anställnings ID: ");
            anställningsnummer = intInput();

            while (!(projektHasWorker(projekt, Integer.toString(anställningsnummer), projektindex))) {
                System.out.println("Det anställningsnumret kunde inte hittas i den gruppen. Ange igen.");
                anställningsnummer = intInput();
            }

            System.out.println();
            System.out.print("Omfattning (timmar): ");
            omfattningstimmar = intInput();

            while (omfattningstimmar < 0) {
                System.out.println("Ett positivt heltal måste anges för timmarna. Försök igen.");
                omfattningstimmar = intInput();
            }

            totalaTimmar = omfattningstimmar + hämtaTimmar(projekt, projektindex, Integer.toString(anställningsnummer));


            while (j < 10) {
                if (Integer.toString(anställningsnummer).equals(projekt[projektindex][j][0])) {
                    projekt[projektindex][j][2] = Integer.toString(totalaTimmar);
                }
                j++;
            }

            System.out.println(omfattningstimmar + " timmar har lagts till.");
            break;
        }

    }

    /**
     * Markerar ett projekt som avslutat
     * @param projekt håller info om alla projekt
     */
    public static void avslutaProjekt(String[][][] projekt)
    {
        String projektnamn;
        System.out.print("Projektnamn: ");
        projektnamn = input();

        while(!(hasProjektNamn(projekt, projektnamn)))
        {
            System.out.println("Ett projekt med det namnet kunde ej hittas, ange igen.");
            projektnamn = input();
        }

        for (int i = 0; i < projekt.length; i++)
        {
            if (projektnamn.equals(projekt[i][0][0]))
            {
                if (projekt[i][0][1].equals("Pågående"))
                {
                    projekt[i][0][1] = "Avslutad";
                    System.out.println("Projekt " + projektnamn + " avslutat.");
                }
                else if (projekt[i][0][1].equals("Avslutad"))
                {
                    System.out.println("Det projektet är redan avslutat.");
                }
            }
        }
    }

    /**
     * Skriver ut information om både pågående och avslutade projekt
     * @param projekt håller info om alla projekt
     * @param anställda håller all info om de anställda
     */
    public static void printProjekt(String[][][] projekt, String[][] anställda)
    {
        System.out.println();
        int anställningsnummer = -1;
        int omfattning;
        int budgeteradeTimmar;
        double omfattningDouble;
        double budgeteradeDouble;
        int j = 1;
        String pågående = "Pågående";
        String Avslutad = "Avslutad";

        System.out.println("Pågående projekt: ");
        for (int i = 0; i < projekt.length; i++)
        {
            if (pågående.equals(projekt[i][0][1]))
            {
                System.out.println("Projektnamn: " + projekt[i][0][0]);
                while (j < 10)
                {
                    if (projekt[i][j][0] != null)
                    {
                        anställningsnummer = Integer.parseInt(projekt[i][j][0]);
                        omfattning = Integer.parseInt(anställda[anställningsnummer][1]);
                        omfattningDouble = Double.valueOf(omfattning);
                        budgeteradeDouble = 1720 * (omfattningDouble/100);
                        budgeteradeTimmar = (int) budgeteradeDouble;
                        System.out.printf("%-12s", anställda[anställningsnummer][0]);
                        System.out.printf("%-6d", budgeteradeTimmar);
                        System.out.printf("%-6s", projekt[i][j][2]);
                        System.out.println();
                    }
                    j++;
                }
            }
        }

        System.out.println();
        System.out.println("Avslutade Projekt: ");
        for (int i = 0; i < projekt.length; i++)
        {
            if (Avslutad.equals(projekt[i][0][1]))
            {
                System.out.println("Projektnamn: " + projekt[i][0][0]);
                while (j < 10)
                {
                    if (projekt[i][j][0] != null)
                    {
                        anställningsnummer = Integer.parseInt(projekt[i][j][0]);
                        omfattning = Integer.parseInt(anställda[anställningsnummer][1]);
                        omfattningDouble = Double.valueOf(omfattning);
                        budgeteradeDouble = 1720 * (omfattningDouble/100);
                        budgeteradeTimmar = (int) budgeteradeDouble;
                        System.out.printf("%-12s", anställda[anställningsnummer][0]);
                        System.out.printf("%-6d", budgeteradeTimmar);
                        System.out.printf("%-6s", projekt[i][j][2]);
                        System.out.println();
                    }
                    j++;
                }
            }
        }

    }

    /**
     * Kollar om ett viss anställd redan har det namnet och returnerar en boolean
     * @param anställda håller all info om de anställda
     * @param name håller namnet av nya arbetaren som ska läggas till
     * @return vare sig det redan finns någon med det namnet eller inte
     */
    public static boolean hasName(String[][] anställda, String name)
    {
        for (int k = 0; k < anställda.length; k++)
        {
            if (name.equals(anställda[k][0]))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Kollar om ett visst projekt redan har det angivna namnet och returnerar en boolean
     * @param projekt håller infon av alla projekt
     * @param name håller namnet av projektet
     * @return vare sig det redan finns ett projekt med det namnet eller inte
     */
    public static boolean hasProjektNamn(String[][][] projekt, String name)
    {
        for (int i = 0; i < projekt.length; i++)
        {
            if (name.equals(projekt[i][0][0]))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Kollar om ett visst projekt redan har en viss arbetare på sig och returnerar en boolean
     * @param projekt är matrisen som håller i all projekt info
     * @param anställningsnummer är numret av arbetaren
     * @param projektindex är indexet av projektet i matrisen
     * @return vare sig projektet har den arbetaren eller inte
     */
    public static boolean projektHasWorker(String[][][] projekt, String anställningsnummer, int projektindex)
    {
        int j = 1;

        thisloop:
        while(true)
        {
            if (anställningsnummer.equals(projekt[projektindex][j][0]))
            {
                return true;
            }

            // Vi kör 10 här efterom vi antagit att max antal arbetare inte överskrider 10
            else if(j == 10)
            {
                break thisloop;
            }
            j++;
        }

        return false;
    }

    /**
     * Letar efter ett anställningsnummer och returnerar en boolean beroende på om den finnns eller inte
     * @param anställda är matrisen som håller i all anställd info
     * @param nummer är själva antsällningsnumret
     * @return vare sig matrisen har det anställningsnumret i sig eller inte
     */
    public static boolean hasNumber(String[][] anställda, int nummer)
    {
        if (anställda[nummer][0] == null)
        {
            return false;
        }
        return true;
    }

    /**
     * Hämtar ett värde från matrisen, specifikt så hämtar den antalet timmar som en viss arbetare har samlat på sig.
     * @param projekt är matrisen som håller i all projektinfo.
     * @param projektindex är en int som berättar index av projektet i matrisen.
     * @param anställningsnummer är anställningsnumret på arbetaren.
     * @return antalet timmar den arbetaren har loggade.
     */
    public static int hämtaTimmar(String[][][] projekt, int projektindex, String anställningsnummer)
    {
        int j = 1;
        while(j < 10)
        {
            if (anställningsnummer.equals(projekt[projektindex][j][0]))
            {
                return Integer.parseInt(projekt[projektindex][j][2]);
            }
            j++;
        }
        System.out.println("Nåt gick fel. Kunde ej ta fram timmarna.");
        return -1;
    }

    /**
     * Genererar slumpmässig integer med bestämd upperlimit.
     * @param upper är upperlimit, dvs högsta siffran som kan slumpas.
     * @return en slumpmässig siffra mellan 0 och upper.
     */
    public static int random ( int upper)
    {
        Random random = new Random();
        return (random.nextInt(upper));
    }


}
