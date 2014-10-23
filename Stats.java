/**
 * Represents all the attributes of the player. Those are:
 * The player's level of spryness, insight and knowledge
 * in one-variable analysis as well as whether or not
 * the player carries lunch.
 * 
 * @author Sebastian Olsson
 * @version 2010.12.04
 */
public class Stats{
    private Degree spryness;
    private Degree insight;
    private boolean carriesLunch;
    private Knowledge knowledge;

    /**
     * Creates the player's Stats.
     */
    public Stats(){
        spryness = new Degree(8);
        insight = new Degree(10);
        carriesLunch = false;
        knowledge = new Knowledge(2,3,1,1,0);
    }
    
    /**
     * Sets whether the player will carry food or not.
     * 
     * @param value If true, the player will carry food,
     * otherwise not.
     */
    public void setCarriesLunch(boolean value){
        carriesLunch = value;
    }

    /**
     * @param value The player's new level of insight.
     */
    public void setInsight(Degree value){
        insight = new Degree(value);
    }
    
    /**
     * Decreases the player's level of insight.
     * 
     * @param value The decreased level of insight.
     */
    public void decreaseInsight(Degree value){
        insight.decreaseValue(value.getValue());
    }
    
    /**
     * Sets the player's spryness.
     * 
     * @param value The player's new level of spryness.
     */
    public void setSpryness(Degree value){
        spryness = new Degree(value);
    }

    /**
     * Increases the player's knowledge.
     * 
     * @param knowledge The player's gained knowledge.
     */
    public void increaseKnowledge(Knowledge knowledge){
        this.knowledge.increaseKnowledge(knowledge);
    }
    
    /**
     * @return True if the player carries lunch, else false.
     */
    public boolean getCarriesLunch(){
        return carriesLunch;
    }
    
    /**
     * @return The player's level of spryness.
     */
    public Degree getSpryness(){
        return spryness;
    }
    
    /**
     * @return The player's level of insight.
     */
    public Degree getInsight(){
        return insight;
    }
    
    /**
     * @return The player's knowledge.
     */
    public Knowledge getKnowledge(){
        return knowledge;
    }
}