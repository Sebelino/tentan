/**
 * Represents the player. The player can move between rooms,
 * has a number of attributes knows as "stats", has a
 * circadian rhythm that gradually reduces the player's
 * spryness and the player wields a "beamer" which works
 * as a teleporting device.
 * 
 * @author Sebastian Olsson
 * @version 2010.12.04
 */
public class Player{
    private Room currentRoom;
    private Stats stats;
    //Is set to true if the player just moved to another room, otherwise false. Initially set to false.
    private boolean justMoved;
    private CircadianRhythm circadianRhythm;
    private Beamer beamer;

    /**
     * Creates a player.
     * 
     * @param startTime The phase shift of the player's
     * circadian rhythm.
     */
    public Player(Time startTime){
        currentRoom = new Room(RoomDescription.THEATER_D1);
        stats = new Stats();
        justMoved = false;
        circadianRhythm = new CircadianRhythm(startTime,1,new Time(1,0));
        beamer = new Beamer();
    }

    /**
     * Charges the beamer.
     */
    public void charge(){
        System.out.println("The teleportation device memorizes the room.");
        beamer.charge(currentRoom);
    }

    /**
     * Fires the beamer.
     */
    public void fire(){
        if(beamer.fire() != null){
            currentRoom = beamer.fire();
            System.out.println("Teleportation successful. You are now "+currentRoom.getDescription()+".");
        }else{
            System.out.println("Your teleporting device isn't charged.");
        }
    }
    
    /**
     * @return The sum of all knowledge about one variable analysis.
     */
    public int getMerits(){
        return (stats.getKnowledge().getLimitCalculus().getValue()+
                stats.getKnowledge().getDifferentialCalculus().getValue()+
                stats.getKnowledge().getIntegralCalculus().getValue()+
                stats.getKnowledge().getDifferentialEquations().getValue()+
                stats.getKnowledge().getTaylorSeries().getValue());
    }
    
    /**
     * If the player attends a lecture when the lecture
     * is held, knowledge is learned.
     * 
     * @param time The time at which the player attends
     * or does not attend the lecture.
     */
    public void learn(Time time){
        if(currentRoom.getFirstLecture().getPeriod().within(time)){
            stats.increaseKnowledge(currentRoom.getFirstLecture().getKnowledge());
        }else{
            stats.increaseKnowledge(currentRoom.getSecondLecture().getKnowledge());
        }
    }

    /**
     * Sets the player's current room to the input room.
     * 
     * @param The input room.
     */
    public void setCurrentRoom(Room room){
        currentRoom = room;
    }

    /**
     * Returns the room that the player is located in.
     * @return The current room.
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * @return The description of the room that the player is located in.
     */
    public RoomDescription getRoomDescription(){
        return currentRoom.getDescription();
    }
    
    /**
     * @return The long decription of the room that the player is located in.
     */
    public String getCurrentRoomLongDescription(){
        return currentRoom.getLongDescription();
    }
    
    /**
     * Sets whether the player carries lunch or not.
     * 
     * @param If true, the player will carry food,
     * else not.
     */
    public void setCarriesLunch(boolean value){
        stats.setCarriesLunch(value);
    }

    /**
     * Sets the player's level of spryness to the input level.
     * 
     * @param The input level of spryness.
     */
    public void setSpryness(Degree value){
        stats.setSpryness(value);
    }
    
    /**
     * Sets the player's level of insight to the input level.
     * 
     * @param The input level of insight.
     */
    public void setInsight(Degree value){
        stats.setInsight(value);
    }
    
    /**
     * Decreases the player's insight by value degrees.
     * 
     * @param value The number of decreased degrees.
     */
    public void decreaseInsight(Degree value){
        stats.decreaseInsight(value);
    }

    /**
     * @return True if the player carries lunch, else false.
     */
    public boolean getCarriesLunch(){
        return stats.getCarriesLunch();
    }
    
    /**
     * @return The player's level of spryness.
     */
    public Degree getSpryness(){
        return stats.getSpryness();
    }
    
    /**
     * @return The player's level of insight.
     */
    public Degree getInsight(){
        return stats.getInsight();
    }
    
    /**
     * @return The player's knowledge in one-variable analysis.
     */
    public Knowledge getKnowledge(){
        return stats.getKnowledge();
    }
    
    /**
     * Returns true if the player just moved to another room, else false.
     */
    public boolean getJustMoved(){
        return justMoved;
    }
    
    /**
     * Sets whether the player just moved or not.
     * 
     * @param If true, the player just moved,
     * else not.
     */
    public void setJustMoved(boolean value){
        justMoved = value;
    }
    
    /**
     * Might make the player less spry. If a certain
     * amount of time has elapsed, the level of
     * spryness will decrease.
     * 
     * @param time The current time.
     */
    public void updateCircadianRhythm(Time time){
        setSpryness(new Degree(getSpryness().getValue()-circadianRhythm.getReduction(time)));
    }
}