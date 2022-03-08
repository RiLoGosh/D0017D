import java.util.Random;
import java.util.Scanner;
import java.util.Date;

/**
 * This program will implement a version of a functioning "Kassa-system" where the user will be able to issue it commands
 * for how they wish to edit the inventory in various ways.
 *
 * 1. Initialise variables.
 * 2. Set up a Switch statement that makes up the backbone of the program as it will decide what operation happens between the 6 commands.
 * 3. Institute a while loop around the switch statement that will repeatedly call the menu method which shall display the
 *    options and ask for input from the user.
 * 4. The input from the user shall select which case in the switch statement that will be called.
 * 5. Each method will receive the relevant matrices and variables that it needs to conduct its functions.
 *      5.1. The insertArticle method will initially use another function called checkFull to see if the size of the matrix needs
 *           resizing. Then it will continuously add articles to the inventory until the entered amount of articles amount has been reached.
 *      5.2. The removeArticle method shall search through the matrix for the corresponding article and then reset all of its values.
 *      5.3. The printArticles method will allow us to loop through the matrix and print the information of each article in the system.
 *      5.4. The sellArticle method permits us to enter an article number and amount to sell and will then search through
 *           the matrix and reduce the stock of that article appropriately, while also logging that information in another matrix
 *           for order history.
 *      5.5. The printSales method shows us the order history information of all sales made in the system by printing out values from
 *           both the Date/Time array and the sales matrix.
 *      5.6. The "Quit" option will simply break the endless while loop that this program runs and therefore will finish the process.
 *
 * @author Simon Hocker, simhoc-1
 */

public class Uppgift6 {

    private static Scanner myscan = new Scanner(System.in);

    public static void main(String[] args)
    {
        final int MAX_PRICE = 1000;
        final int MIN_PRICE = 100;
        final int MAX_AMOUNT = 10;
        int[][] articles;
        int[][] sales;
        Date[] dateTime;
        int userInput;
        int noOfArticles;

        // The base size shall be for 10 articles.
        articles = new int[10][3];

        // A method for expanding the sales matrix size when needed could be added but for practical purposes we put it at 100.
        sales = new int[100][3];

        dateTime = new Date[100];

        System.out.println("Welcome to the Weyland-Yutani Corporation Store Hub!");
        System.out.println("Building Better Worlds.");


        /**
        * The main framework for the program. It is an endless while loop that repeatedly calls the menu on every iteration
        * as each one will run one command from the user. It checks the input from the menu with that of its switch statement,
        * checking if any of the 6 commands have been issued and then running them. After every entry, it repeats and waits
        * for the next command, even if the user input wasn't a valid option of 1-6.
        */
        outerloop:
        while(true)
        {
            System.out.println();
            userInput = menu();

            switch (userInput)
            {
                case 1:
                    System.out.println("Add Articles selected.");
                    System.out.println("How many different articles shall be added?");
                    noOfArticles = input();

                    while(noOfArticles < 0)
                    {
                        System.out.println("Invalid input. Please enter an integer number that is atleast 0.");
                        noOfArticles = input();
                    }

                    articles = checkFull(articles, noOfArticles);

                    // If matrix is empty
                    if (checkLatestArticle(articles) == 0)
                    {
                        insertArticles(articles, 999, noOfArticles, MAX_PRICE, MIN_PRICE, MAX_AMOUNT);
                        System.out.println(noOfArticles + " articles were added to an empty inventory.");
                    }

                    else
                    {
                        insertArticles(articles, checkLatestArticle(articles), noOfArticles, MAX_PRICE, MIN_PRICE, MAX_AMOUNT);
                        System.out.println(noOfArticles + " articles were added to the inventory.");
                    }
                    break;

                case 2:
                    System.out.println("Remove an Article selected.");

                    if (checkLatestArticle(articles) == 0)
                    {
                        System.out.println("The inventory is empty. There is nothing to remove.");
                        break;
                    }

                    else
                    {
                        removeArticle(articles);
                    }
                    break;

                case 3:
                    System.out.println("Print Articles selected.");

                    printArticles(articles);
                    break;

                case 4:
                    System.out.println("Sell Articles selected.");
                    sellArticle(sales, dateTime, articles);
                    break;

                case 5:
                    System.out.println("Order History selected.");
                    printSales(sales, dateTime);
                    break;

                case 6:
                    System.out.println("Quit selected.");
                    System.out.println("Shutting Down.");
                    break outerloop;

            }
        }

    }

    /**
     * Prints out all of the options to the user and returns the user's input regardless of what it is.
     * @return user input from input().
     */
    public static int menu()
    {
        int userInput;
        System.out.println("Select an option please.");
        System.out.println("1. Add articles.");
        System.out.println("2. Remove an article.");
        System.out.println("3. Show articles.");
        System.out.println("4. Sell Articles.");
        System.out.println("5. Order history.");
        System.out.println("6. Quit.");
        userInput = input();

        return userInput;
    }

    /**
     * The main input method. This will keep repeating until the user enters an integer and will then return that integer.
     * It stays in a constant while loop in order to constantly check if the input is an integer.
     * @return an integer determined to be a valid integer.
     */
    public static int input ()
    {
        String input;
        int number;

        while (true)
        {
            input = myscan.next();

            try
            {
                number = Integer.parseInt(input);
                return number;
            }

            catch (Exception e)
            {
                System.out.println("Not an integer. Try again.");
            }

        }

    }

    /**
     * Loops through the matrix and uses a counter to keep track of all the empty indexes, then if that number is larger
     * than the noOfArticles (amount of articles to be added) then it will return the original matrix. However, if the
     * number is smaller then it will use Syste.arraycopy to make a new array that is twice the size and return that instead.
     * @param articles holds the complete matrix that is the inventory.
     * @param noOfArticles is an integer stating how many articles are going to be added.
     * @return either the array or a new array made to be twice as large as the previous one.
     */
    public static int[][] checkFull ( int[][] articles, int noOfArticles)
    {
        int openslots;
        openslots = 0;
        for (int i = 0; i < articles.length; i++)
        {
            if (articles[i][0] == 0)
            {
                openslots++;
            }
        }

        if (noOfArticles <= openslots)
        {
            return articles;
        }

        int copyArray[][] = new int[articles.length * 2][3];

        System.arraycopy(articles, 0, copyArray, 0, articles.length);
        return copyArray;
    }

    /**
     * This method will search through the matrix for empty spots and then place articles there using the random method
     * and starting article number, and continues to do so until the noOfArticles had been decremented to 0 (meaning that
     * there are no articles left to add). We shouldn't have to worry about the matrix not being big enough here as
     * that has already been checked and handled before it was called.
     * @param articles holds the complete matrix that is the inventory.
     * @param articleNumber states the article number of the most recently added item and increments it for each added article.
     * @param MAX_PRICE  is a constant stating what the maximum price of an article is going to be.
     * @param MIN_PRICE is a constant stating what the minimum price of each article shall be.
     * @param MAX_AMOUNT is a constant stating what the maximum amount of each added article can be.
     */
    public static void insertArticles ( int[][] articles, int articleNumber, int noOfArticles, int MAX_PRICE, int MIN_PRICE, int MAX_AMOUNT)
    {
        articleNumber++;
        for (int i = 0; i < articles.length; i++)
        {
            // This segment will only run if the index has an open spot and if there are articles left to add
            if (articles[i][0] == 0 && noOfArticles != 0)
            {

                articles[i][0] = articleNumber;
                articles[i][1] = random(MAX_AMOUNT);
                articles[i][2] = random(MAX_PRICE - MIN_PRICE) + MIN_PRICE;
                articleNumber++;
                noOfArticles--;
            }
        }
    }

    /**
     * This method loops through all of the articles until it finds the one that has a matching article number. It
     * then edits all elements of the sub-array (article number, amount, price) to have the value 0.
     * @param articles holds the complete matrix that is the inventory.
     */
    public static void removeArticle ( int[][] articles)
    {
        int articleNr;
        System.out.println("What is the article number of the article that you would like removed?");
        articleNr = input();

        for (int i = 0; i < articles.length; i++)
        {
            if (articleNr == articles[i][0])
            {
                articles[i][0] = 0;
                articles[i][1] = 0;
                articles[i][2] = 0;
                System.out.println("Article " + articleNr + " removed from the inventory.");
                break;
            }
            else if (i+1 == articles.length)
            {
                System.out.println("Article " + articleNr + " could not be found.");
            }
        }
    }

    /**
     * This will loop through all the articles and print their article number, amount of the item and its price.
     * @param articles holds the complete matrix that is the inventory.
     */
    public static void printArticles ( int[][] articles)
    {
        System.out.println("Article Number   Amount   Price");
        for (int i = 0; i < articles.length; i++)
        {
            if (articles[i][0] != 0)
            {
                System.out.println(articles[i][0] + "             " + articles[i][1] + "        " + articles[i][2]);
            }
        }
    }


    /**
     * This method will first ask the user for the article number and then the amount being sold, then it starts looping
     * through the articles to find the corresponding number, then it checks that there is enough stock to make the sale,
     * and then finally edits the amount in the articles inventory while making an entry of it in the sales and date.
     * @param sales holds the matrix containing the information about all of the sales that have been made.
     * @param salesDate is a Date array containing the date and time information of all the sales that have been made.
     * @param articles holds the complete matrix that is the inventory.
     */
    public static void sellArticle ( int[][] sales, Date[] salesDate,int[][] articles)
    {
        int articleNr;
        int sellAmount;
        Date thisDate = new Date();


        System.out.println("What is the article number of the item being sold?");
        articleNr = input();

        System.out.println("How many of the selected item are being sold?");
        sellAmount = input();
        while(sellAmount < 1)
        {
            System.out.println("Invalid input. Please enter an integer number that is above 0.");
            sellAmount = input();
        }

        outerloop:
        for (int i = 0; i < articles.length; i++)
        {
            if (articleNr == articles[i][0])
            {
                if (articles[i][1] >= sellAmount)
                {
                    articles[i][1] = articles[i][1] - sellAmount;
                    System.out.println("Sale made!. " + sellAmount + " copies were sold of article " + articleNr + ".");
                    for (int k = 0; k < sales.length; k++)
                    {
                        if (sales[k][0] == 0)
                        {
                            sales[k][0] = articleNr;
                            sales[k][1] = sellAmount;
                            sales[k][2] = articles[i][2] * sellAmount;

                            salesDate[k] = thisDate;
                            break outerloop;
                        }
                    }

                }

                else
                {
                    System.out.println("Inventory does not have enough of the specified item so no sale was made.");
                }
            }
        }
    }

    /**
     * Will loop through the sales and salesDate arrays and print out every entry that has been made (ignores the empty entries)
     * @param sales holds the matrix containing the information about all of the sales that have been made.
     * @param salesDate is a Date array containing the date and time information of all the sales that have been made.
     */
    public static void printSales ( int[][] sales, Date[] salesDate)
    {
        System.out.println("Date/Time                           Article Number   Amount Sold   Sum");
        for (int i = 0; i < sales.length; i++)
        {

            if (sales[i][0] != 0)
            {
                System.out.println(salesDate[i].toString() + "       " + sales[i][0] + "             " + sales[i][1] + "             " + sales[i][2]);
            }
        }
    }

    /**
     * Generates a random integer with a specified upperbound limit and is guaranteed to be 1 or greater.
     * @param upper is the upperbound limit of the random integer being produced.
     * @return either the array or a new array made to be twice as large as the previous one.
     */
    public static int random ( int upper)
    {
        Random random = new Random();
        return (random.nextInt(upper)) + 1;
    }

    /**
     * Will loop through the list to find the latest added article and return that article number.
     * @param articles holds the complete matrix that is the inventory.
     * @return the highest article number in the inventory as it will then have been the latest one added.
     */
    public static int checkLatestArticle ( int[][] articles)
    {
        int highest;
        highest = 0;

        for (int i = 0; i < articles.length; i++)
        {
            if (articles[i][0] > highest)
            {
                highest = articles[i][0];
            }
        }
        return highest;
    }
}






