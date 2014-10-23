/**
 * Device that can be charged and fired. When you charge the beamer,
 * it memorizes the current room. When you fire the beamer, it
 * transports you immediately back to the room it was charged in.
 * 
 * @author Sebastian Olsson
 * @version 2010.12.04
 */
public class Beamer{
    private Room charge;

    /**
     * Creates a Beamer.
     */
    public Beamer(){}

    /**
     * @return The charged room.
     */
    public Room fire(){
        return charge;
    }

    /**
     * Charges the device by memorizing a room.
     * 
     * @param The room that is memorized.
     */
    public void charge(Room room){
        charge = room;
    }
}
