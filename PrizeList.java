import java.io.*;
import java.util.*;
/**
 * This class manages a list of all the Prizes for the Lucky Vending
 * Machine.
 * 
 * @author Bai Chan Kheo 22262407 
 * @version 1.3 27 May 2015
 */
public class PrizeList
{
    private ArrayList<Prize> prizes;
    
    /**
     * Constructor for PrizeList. Reads the list of prizes from a 
     * text file.
     */
    public PrizeList()
    {
        prizes = new ArrayList<Prize>();
        readPrizes("prizes.txt");
    }
    
    /**
     * Constructor for PrizeList that takes in arguments.
     */
    public PrizeList(ArrayList<Prize> newPrizes)
    {
        prizes = newPrizes;
    }
    
    /**
     * Adds a Prize to the list of Prizes.
     * 
     * @param name      The name of the prize.
     * @param worth     The prize's worth.
     * @param cost      The prize's cost.
     * @return          True if the Prize had been added.
     */
    private boolean addPrize(String name, int worth, int cost)
    {
        boolean prizeAdded = false;
        Prize prize = new Prize();
        if (prize.setName(name) && prize.setWorth(worth) && prize.setCost(cost))
        {
            prizes.add(prize);
            prizeAdded = true;
        }
        else
            System.out.println("Invalid prize. Please add another prize.\n");
        return prizeAdded;
    }
    
    /**
     * Prompts the administrator for details relating to the Prize they wish
     * to add. Has validation to ensure that a Prize is added correctly.
     */
    private void addPrizePrompt()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("What is the name of the prize you wish to add?");
        String prizeName = console.nextLine().trim();
        if (uniquePrize(prizeName))
        {
            if (prizeName.length() > 0 && Character.isLetter(prizeName.charAt(0)))
            {
                int prizeWorth = 0;
                System.out.println("\nWhat is it worth?");
                try
                {
                    prizeWorth = console.nextInt();
                    console.nextLine(); // Clears buffer.
                }
                catch (Exception e)
                {
                    // Error message will be displayed in the 'if-else' below.
                }
                if (prizeWorth > 0)
                {
                    int prizeCost = 0;
                    System.out.println("\nWhat does it cost?");
                    try
                    {
                        prizeCost = console.nextInt();
                        if (uniqueCost(prizeCost))
                        {
                            if (prizeCost > 0)
                            {
                                addPrize(prizeName, prizeWorth, prizeCost);
                                System.out.println(prizeName + " successfully added.\n");
                            }
                            else
                                System.out.println("Invalid prize cost.\n");
                        }
                        else
                        System.out.println("Prize cost is not unique.\n");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid prize cost.\n");
                    }
                }
                else
                    System.out.println("Invalid prize worth.\n");
            }
            else
                System.out.println("Invalid prize name.\n");
        }
        else
            System.out.println("Prize is already in the system.\n");
    }
    
    /**
     * Finds the Prize object with a given name and deleted it.
     * 
     * @param prizeName     The name of the Prize that needs to be found.
     * @return              True if the prize has been deleted.
     */
    public boolean deletePrizeName(String prizeName)
    {
        Prize currentPrize = new Prize();
        Iterator<Prize> iterator = prizes.iterator();
        boolean deleted = false;
        int index = 0;
        while (!deleted && iterator.hasNext())
        {
            currentPrize = iterator.next();
            if (currentPrize.getName().equalsIgnoreCase(prizeName))
            {
                removePrize(index);
                deleted = true;
            }
            index++;
        }
        return deleted;
    }
    
    /**
     * This method displays the Prize Menu for the admin to configure
     * the prizes in the machine.
     */
    public void displayMenu()
    {
        String input = "";
        String exitValue = "4";
        Scanner console = new Scanner(System.in);
        while (!input.equals(exitValue))
        {
            System.out.println("----- [Prize Menu] -----\n" + 
                               "[1] Add a Prize\n" +
                               "[2] Remove a Prize\n" +
                               "[3] List all the Prizes\n" +
                               "[4] Exit Prize Menu\n" + 
                               "Please make a selection:");
            System.out.print("> ");
            input = console.nextLine();
            System.out.println();
            switch (input)
            {
                case "1":   addPrizePrompt(); break;
                case "2":   removePrizePrompt(); break;
                case "3":   System.out.println("----- [Prizes] -----");
                            if (getSize() > 0)
                                displayPrizes();
                            else
                                System.out.println("[No prizes currently in the system.]");
                            pressEnter(); break;
                case "4":   System.out.println("Exiting prize menu. Bye!\n"); break;
                default:    System.out.println("Invalid option. Please choose another.\n");
                            break;
            }
        }
    }
    
    /**
     * Method that display all the Prizes in the list.
     */
    public void displayPrizes()
    {
        int index = 1;
        for (Prize prize : prizes)
        {
            System.out.print("[" + index + "] ");
            prize.displayPrize();
            index++;
        }
    }
    
    /**
     * Returns the number of Prizes in the list.
     * 
     * @return  The size of the ArrayList.
     */
    public int getSize()
    {
        return prizes.size();
    }
    
    /**
     * Method to return the Prize object at any given index.
     * 
     * @param index     The index of the Prize.
     * @return          The Prize object at the given index.
     */
    public Prize getPrize(int index)
    {
        return prizes.get(index);
    }
    
    /**
     * This method prompts the user to press the [ENTER] key to continue.
     */
    private void pressEnter()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("\nPress [ENTER] to continue...");
        console.nextLine();
    }
    
    /**
     * Reads prizes from the given filename and populates the ArrayList.
     * 
     * @param fileName  The name of the file to be read.
     * @return          True if the prizes have been read correctly.
     */
    private boolean readPrizes(String fileName)
    {
        boolean prizesRead = false;
        try
        {
            FileReader inputFile = new FileReader(fileName);
            Scanner parser = new Scanner(inputFile);
            String[] values = new String[3]; // Array to hold the contents of each line.
            while (parser.hasNextLine())
            {
                String line = parser.nextLine();
                values = line.split(",");
                prizes.add(new Prize(values[0], Integer.parseInt(values[1]), 
                                     Integer.parseInt(values[2])));
            }
            prizesRead = true;
        }
        catch (Exception e)
        {
            System.out.println("Error reading Prizes:\n" + 
                               "File not found. Please check that prizes.txt exists and " +
                               "contains prize data.");
        }
        return prizesRead;
    }
    
    /**
     * Removes a prize from the list of prizes. Checks that the index given
     * is available first.
     * 
     * @param index     The index of the prize object that is to be removed.
     * @return          True if the prize has been successfully removed.
     */
    private boolean removePrize(int index)
    {
        boolean prizeRemoved = false;
        if (prizes.size() > index && index >= 0)
        {
            prizes.remove(index);
            prizeRemoved = true;
        }
        return prizeRemoved;
    }
    
    /**
     * Asks the player for the name of the prize they wish to remove.
     * If it is found, the prize is removed from the list.
     */
    private void removePrizePrompt()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("What is the name of the prize you wish to remove?");
        String prizeName = console.nextLine().trim();
        if (deletePrizeName(prizeName))
            System.out.println(prizeName + " has been successfully removed.\n");
        else
            System.out.println("Prize not found.\n");
    }
    
    /**
     * Checks if a given cost is unique.
     * 
     * @param prizeCost     The cost of the prize to be checked.
     * @return              True if the prize name is unique.
     */
    private boolean uniqueCost(int prizeCost)
    {
        boolean isUnique = true;
        for (Prize prize : prizes)
        {
            if (prize.getCost() == prizeCost)
                isUnique = false;
        }
        return isUnique;
    }
    
    /**
     * Checks if a given prize name is unique.
     * 
     * @param prizeName     The name of the prize to be checked.
     * @return              True if the prize name is unique.
     */
    private boolean uniquePrize(String prizeName)
    {
        boolean isUnique = true;
        for (Prize prize : prizes)
        {
            if (prize.getName().equalsIgnoreCase(prizeName))
                isUnique = false;
        }
        return isUnique;
    }
    
    /**
     * Writes the prizes added to the text file.
     * 
     * @param fileName  The name of the text file to be written to.
     * @return          True if the write process finishes successfully.
     */
    public boolean writePrizes(String fileName)
    {
        boolean written = false;
        try
        {
            PrintWriter outputFile = new PrintWriter(fileName);
            for (Prize prize : prizes)
            {
                outputFile.println(prize.getName() + "," + prize.getWorth() + "," +
                                   prize.getCost());
            }
            outputFile.close();
            written = true;
        }
        catch (Exception e)
        {
            System.out.println("Error in I/O process.");
        }
        return written;
    }
}