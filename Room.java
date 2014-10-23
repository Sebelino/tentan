import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author Michael Kolling
 * @author David J. Barnes
 * @author Sebastian Olsson
 * @version 2010.12.04
 */

public class Room{
    private RoomDescription description;
    private HashMap<String,Room> exits;        // stores exits of this room.
    private Lecture firstLecture;
    private Lecture secondLecture;

    /**
     * Create a room identical to another room. Initially, it has
     * no exits.
     * @param room The room that this room is identical to.
     */
    public Room(Room room){
        this.description = room.getDescription();
        exits = new HashMap<String,Room>();
    }
    
    /**
     * Create a room described "description". Initially, it has
     * no exits.
     * @param description The room's description.
     */
    public Room(RoomDescription description){
        this.description = description;
        exits = new HashMap<String,Room>();
    }

    /**
     * If the room contains a lecture locale, information
     * about the two lectures held today is printed.
     */
    public void printLectureInfo(){
        if(firstLecture != null && secondLecture != null){
            System.out.println(firstLecture.getLocale()+" - "+firstLecture.getPeriodString()+" - "+firstLecture.getDescription());
            System.out.println(secondLecture.getLocale()+" - "+secondLecture.getPeriodString()+" - "+secondLecture.getDescription());
        }
    }

    /**
     * @return The first lecture held today.
     */
    public Lecture getFirstLecture(){
        return firstLecture;
    }
    
    /**
     * @return The second lecture held today.
     */
    public Lecture getSecondLecture(){
        return firstLecture;
    }

    /**
     * @return The lecture that is held at the time time.
     * If no lecture is held, return null.
     * @param time The checked time.
     */
    public Lecture getOngoingLecture(Time time){
        if(firstLecture.getPeriod().within(time)){
            return firstLecture;
        }else if(secondLecture.getPeriod().within(time)){
            return secondLecture;
        }
        return null;
    }

    /**
     * Organizes the first lecture.
     * 
     * @param period The time period the lecture is held.
     * @param knowledge The knowledge that the lecture gives.
     * @param description A description of the lecture.
     * @param locale The locale in which the lecture is held.
     */
    public void setFirstLecture(Period period,Knowledge knowledge,String description,String locale){
        firstLecture = new Lecture(period,knowledge,description,locale);
    }

    /**
     * Organizes the first lecture.
     * 
     * @param startTime When the lecture starts.
     * @param endTime When the lecture ends.
     * @param knowledge The knowledge that the lecture gives.
     * @param description A description of the lecture.
     * @param locale The locale in which the lecture is held.
     */
    public void setFirstLecture(Time startTime,Time endTime,Knowledge knowledge,String description,String locale){
        setFirstLecture(new Period(startTime,endTime),knowledge,description,locale);
    }

    /**
     * Organizes the second lecture.
     * 
     * @param period The time period the lecture is held.
     * @param knowledge The knowledge that the lecture gives.
     * @param description A description of the lecture.
     * @param locale The locale in which the lecture is held.
     */
    public void setSecondLecture(Period period,Knowledge knowledge,String description,String locale){
        secondLecture = new Lecture(period,knowledge,description,locale);
    }

    /**
     * Organizes the second lecture.
     * 
     * @param startTime When the lecture starts.
     * @param endTime When the lecture ends.
     * @param knowledge The knowledge that the lecture gives.
     * @param description A description of the lecture.
     * @param locale The locale in which the lecture is held.
     */
    public void setSecondLecture(Time startTime,Time endTime,Knowledge knowledge,String description,String locale){
        setSecondLecture(new Period(startTime,endTime),knowledge,description,locale);
    }
    
    /**
     * Defines an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction,Room neighbor){
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room
     * (the one that was defined in the constructor).
     */
    public RoomDescription getDescription(){
        return description;
    }
    
    /**
     * @return A String of the short description of the room
     */
    public String getShortDescription(){
        return description.toString();
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription(){
        return "You are " + getShortDescription() + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString(){
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * direction. If there is no room in that direction, return null.
     * 
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction){
        return exits.get(direction);
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * direction. If there is no room in that direction, return null.
     * 
     * @param time The checked time.
     * @return True if an lecture is held at the time time,
     * false otherwise.
     */
    public boolean hasLectureNow(Time time){
        if(firstLecture == null || secondLecture == null){
            return false;
        }
        return (firstLecture.getPeriod().within(time) ||
                secondLecture.getPeriod().within(time));
    }
}