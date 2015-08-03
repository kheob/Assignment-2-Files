/**
 * This class is used to create objects that represent prizes in the
 * Lucky Vending Machine. It is used by the PrizeList and Player classes.
 * 
 * @author Bai Chan Kheo 22262407
 * @version 1.3 27 May 2015
 */
public class Prize
{
    private String name;
    private int worth;
    private int cost;
    
    /**
     * Constructor that takes no arguments.
     */
    public Prize()
    {
        name = "";
        worth = 0;
        cost = 0;
    }
    
    /**
     * Constructor that takes arguments for all attributes.
     * 
     * @param newName   The name of the prize.
     * @param newWorth  The prize's worth.
     * @param newCost   The prize's cost.
     */
    public Prize(String newName, int newWorth, int newCost)
    {
        name = newName;
        worth = newWorth;
        cost = newCost;
    }
    
    /**
     * Method that displays the details of the Prize object.
     */
    public void displayPrize()
    {
        System.out.println(name + ", Worth: $" + worth + 
                           ", Cost: $" + cost);
    }
    
    /**
     * Accessor method for cost attribute.
     * 
     * @return  The cost of the Prize.
     */
    public int getCost()
    {
        return cost;
    }
    
    /**
     * Accessor method for name attribute.
     * 
     * @return  The name of the Prize.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Accessor method for worth attribute.
     * 
     * @return  The prize's worth.
     */
    public int getWorth()
    {
        return worth;
    }
    
    /**
     * Mutator method for cost attribute.
     * 
     * @param newCost   The cost of the prize.
     * @return          True if the cost is valid.
     */
    public boolean setCost(int newCost)
    {
        boolean valid = false;
        if (newCost > 0)
        {
            cost = newCost;
            valid = true;
        }
        return valid;
    }
    
    /**
     * Mutator method for name attribute.
     * 
     * @param newName   The name of the prize.
     * @return          True if the name is valid.
     */
    public boolean setName(String newName)
    {
        boolean valid = false;
        if (newName.trim().length() > 0 && Character.isLetter(newName.charAt(0)))
        {
            name = newName;
            valid = true;
        }
        return valid;
    }
    
    /**
     * Mutator method for worth attribute.
     * 
     * @param newWorth  The prize's worth.
     * @return          True if the worth is valid.
     */
    public boolean setWorth(int newWorth)
    {
        boolean valid = false;
        if (newWorth > 0)
        {
            worth = newWorth;
            valid = true;
        }
        return valid;
    }
}