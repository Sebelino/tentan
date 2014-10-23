/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author Michael Kolling
 * @author David J. Barnes
 * @author Sebastian Olsson
 * @version 2010.12.04
 */
public enum CommandWord{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    ENTER("enter"),
    CHARGE("charge"),
    FIRE("fire"),
    YES("yes"),
    NO("no"),
    UNKNOWN("?");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command word.
     * @param commandWord The command string.
     */
    CommandWord(String commandString){
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString(){
        return commandString;
    }
}
