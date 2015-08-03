import java.util.*;
/**
 * The Player class creates a new player and stores their name, 
 * prizes they have won, total value of their prizes won, and how 
 * much money they have spent.
 * 
 * It is used by the PlayerList class to store information relevant 
 * to the current player as well as a list of Player objects.
 * 
 * @author Bai Chan Kheo 22262407
 * @version 1.6 27 May 2015
 */
public class Player
{
    private String name;
    private ArrayList<Prize> prizesWon;
    private int prizesWorth;
    private int totalSpent;
    
    /**
     * Constructor with no parameters.
     */
    public Player()
    {
        name = "";
        prizesWon = new ArrayList<Prize>();
        prizesWorth = 0;
        totalSpent = 0;
    }
    
    /**
     * Constructor which takes all fields as parameters.
     * 
     * @param newName           The player's name.
     * @param newPrizesWorth    The worth of all the player's prizes.
     * @param newTotalSpent     The total the player has spent.
     */
    public Player(String newName, int newPrizesWorth, int newTotalSpent)
    {
        if (!setName(newName)) // If name is invalid.
            name = "";
        prizesWon = new ArrayList<Prize>();
        if (!setPrizesWorth(newPrizesWorth)) // If prizesWorth is invalid.
            prizesWorth = 0;
        if (!setTotalSpent(newTotalSpent)) // If totalSpent is invalid.
            totalSpent = 0;
    }
    
    /**
     * This method adds a prize to the player's current list of prizes.
     * 
     * @param newPrize  A Prize object.
     * @return          True if the prize had been added successfully.
     */
    public boolean addPrize(Prize newPrize)
    {
        boolean prizeAdded = false;
        try
        {
            prizesWon.add(newPrize);
            prizeAdded = true;
        }
        catch (Exception e)
        {
            System.out.println("Prize not added.");
        }
        return prizeAdded;
    }

    /**
     * This method adds the cost of the player's guess to the total amount 
     * that the player has spent.
     * 
     * @param newSpent  The cost of the Prize object guessed.
     * @return          True if spent amount is added successfully.
     */
    public boolean addSpent(int newSpent)
    {
        boolean spentAdded = false;
        if (newSpent > 0)
        {
            totalSpent += newSpent;
            spentAdded = true;
        }
        return spentAdded;
    }    
    
    /**
     * This method adds a prize's worth to the total worth off all the 
     * player's prizes.
     * 
     * @param newWorth  The worth of the Prize object won.
     * @return          True if the prize's worth had been added successfully.
     */
    public boolean addWorth(int newWorth)
    {
        boolean worthAdded = false;
        if (newWorth > 0)
        {
            prizesWorth += newWorth;
            worthAdded = true;
        }
        return worthAdded;
    }    
    
    /**
     * Method to display all relevant information about the player. 
     * Includes all fields.
     */
    public void displayPlayer()
    {
        System.out.println("Name: " + name);
        System.out.println("Prizes won:");
        displayPrizes();
        System.out.println("\nTotal value of prizes won: $" + prizesWorth);
        System.out.println("Total spent: $" + totalSpent + "\n");
    }    
    
    /**
     * Method to display information about the player in a more compact
     * form.
     */
    public void displayPlayerCompact()
    {
        System.out.println("Name: " + name + ", No. of prizes: " + prizesWon.size() +
                         ", Value of prizes: $" + prizesWorth + ", Total spent: $" + 
                         totalSpent);
    }
    
    /**
     * This method displays all the prizes the player has won.
     */
    public void displayPrizes()
    {
        for (Prize prize : prizesWon)
            System.out.print("[" + prize.getName() + "] ");
    }
    
    /**
     * Accessor method for name variable.
     * 
     * @return  The name of the current player.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Accessor method for prizesWon variable.
     * 
     * @return  The list of Prizes the player has won as an ArrayList.
     */
    public ArrayList<Prize> getPrizesWon()
    {
        return prizesWon;
    }
    
    /**
     * Accessor method for prizesWorth variable.
     * 
     * @return  The total value of all the prizes the player has won.
     */
    public int getPrizesWorth()
    {
        return prizesWorth;
    }
    
    /**
     * Accessor method for totalSpent variable.
     * 
     * @return  The total amount the player has spent.
     */
    public int getTotalSpent()
    {
        return totalSpent;
    }
    
    /**
     * Mutator method for name variable.
     * 
     * @param newName   A new name for the player.
     * @return          True if the name is valid.
     */
    public boolean setName(String newName)
    {
        if (validName(newName))
            name = newName;
        return validName(newName);
    }
    
    /**
     * Mutator method for prizesWorth variable.
     * 
     * @param newPrizesWorth    The new value to be stored as the total
     *                          worth of all the prizes.
     * @return                  True if the attribute was set correctly.
     */
    public boolean setPrizesWorth(int newPrizesWorth)
    {
        boolean prizesWorthSet = false;
        if (newPrizesWorth >= 0)
        {
            prizesWorth = newPrizesWorth;
            prizesWorthSet = true;
        }
        return prizesWorthSet;
    }
    
    /**
     * Mutator method for totalSpent variable.
     * 
     * @param newTotalSpent     the new value to be stored as the
     *                          total amount the Player has spent.
     * @return                  True if the attribute was set correctly.
     */
    public boolean setTotalSpent(int newTotalSpent)
    {
        boolean totalSpentSet = false;
        if (newTotalSpent >= 0)
        {
            totalSpent = newTotalSpent;
            totalSpentSet = true;
        }
        return totalSpentSet;
    }
    
    /**
     * Validation method for the name attribute. Checks if a
     * name isn't an empty string and begins with a letter.
     * 
     * @param newName   The name that needs to be validated.
     * @return          True if the name is valid.
     */
    private boolean validName(String newName)
    {
        boolean valid = false;
        if (newName.trim().length() > 0 && Character.isLetter(newName.trim().charAt(0)))
            valid = true;
        return valid;
    }
}