import java.util.*;
/**
 * This class manages a list of all the players currently in the
 * Lucky Vending Machine game.
 * 
 * @author Bai Chan Kheo 22262407
 * @version 1.2 27 May 2015
 */
public class PlayerList
{
    private ArrayList<Player> players;
    
    /**
     * Constructor for PlayerList class.
     */
    public PlayerList()
    {
        players = new ArrayList<Player>();
    }
    
    /**
     * Constructor that takes the fields as arguments.
     * 
     * @param newPlayers    An ArrayList of type Player.
     */
    public PlayerList(ArrayList<Player> newPlayers)
    {
        players = newPlayers;
    }
    
    /**
     * Adds a player to the list of all players.
     * 
     * @param name          The name of the player.
     * @param prizeWorth    The total worth of the player's prizes.
     * @param totalSpent    The total the player has spent.
     * @return              True if the player has been added.
     */
    public boolean addPlayer(String name, int prizeWorth, int totalSpent)
    {
        boolean playerAdded = false;
        Player player = new Player();
        if (player.setName(name) && player.setPrizesWorth(prizeWorth) &&
            player.setTotalSpent(totalSpent))
        {
            players.add(player);
            playerAdded = true;
        }
        else
            System.out.println("Invalid player. Please try again.\n");
        return playerAdded;
    }
    
    /**
     * Displays information for all the Players in the ArrayList in a
     * compact form. Checks if there are Player objects first.
     */
    public void displayPlayers()
    {
        if (getSize() > 0)
        {
            int index = 1;
            for (Player player : players)
            {
                System.out.println("[Player " + index + "]");
                player.displayPlayerCompact();
                index++;
            }
        }
        else
            System.out.println("No players yet.");
    }
    
    /**
     * Looks through the list of all Players and adds the top three with
     * the highest prize worth to an Array, then prints the Players in the
     * Array.
     * 
     * If there is a tie, the older player gets pushed down in the list
     * for the newer player.
     */
    public void displayTop()
    {
        Player[] topPlayers = new Player[3]; // Array to store the top 3 players.
        for (int index = 0; index < 3; index++)
            topPlayers[index] = new Player(); // Populates array with dummy players.
        for (Player player : players)
        {
            if (player.getPrizesWorth() >= topPlayers[0].getPrizesWorth())
            {
                topPlayers[2] = topPlayers[1]; // Moves 2nd place to 3rd place.
                topPlayers[1] = topPlayers[0]; // Moves 1st place to 2nd place.
                topPlayers[0] = player; // Adds the player to 1st place.
            }
            else if (player.getPrizesWorth() >= topPlayers[1].getPrizesWorth())
            {
                topPlayers[2] = topPlayers[1]; // Moves 2nd place to 3rd place.
                topPlayers[1] = player; // Adds the player to 2nd place.
            }
            else if (player.getPrizesWorth() >= topPlayers[2].getPrizesWorth())
                topPlayers[2] = player; // Adds the player to 3rd place.
        }
        System.out.println("----- [Leaderboard] -----");
        if (topPlayers[0].getName().length() > 0) // Checks if there are any players.
        {
            int position = 1; // The player's position on the leaderboard.
            for (Player player : topPlayers)
            {
                if (player.getName().length() > 0) // Checks that it is not a dummy player.
                {
                    System.out.println("[Position " + position + "]");
                    player.displayPlayerCompact();
                    position++;
                }
            }
        }
        else
            System.out.println("No players yet.");
    }
    
    /**
     * Returns the Player object at the given index.
     * 
     * @param index     The index of the Player object.
     * @return          The player object at the given index.
     */
    public Player getPlayer(int index)
    {
        return players.get(index);
    }
    
    /**
     * Returns the ArrayList of Players.
     * 
     * @return  The ArrayList object with all the Players.
     */
    public ArrayList<Player> getPlayers()
    {
        return players;
    }
    
    /**
     * Returns the number of Players in the ArrayList.
     * 
     * @return The size of the ArrayList.
     */
    public int getSize()
    {
        return players.size();
    }
    
    /**
     * Removes a Player from the ArrayList.
     * 
     * @param index     The index of the Player object to be removed.
     * @return          True if the player has been removed successfully.
     */
    public boolean removePlayer(int index)
    {
        boolean playerRemoved = false;
        try
        {
            players.remove(index);
            playerRemoved = true;
        }
        catch (Exception e)
        {
            System.out.println("Invalid player index.");
        }
        return playerRemoved;
    }
    
    /**
     * Checks if a name is unique within the list of players.
     * 
     * @param name  The name to be checked.
     * @return      True if the name is unique.
     */
    public boolean uniqueName(String name)
    {
        boolean isUnique = true;
        Iterator<Player> iterator = players.iterator();
        while (iterator.hasNext())
        {
            if (name.equalsIgnoreCase(iterator.next().getName()))
                isUnique = false;
        }
        return isUnique;
    }
}