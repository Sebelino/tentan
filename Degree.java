/**
 * Represents a degree of some kind. Can only assume integer values from MIN to MAX.
 * If the value is tried to be increased or decreased higher or lower than these limits,
 * the value will be set to the highest or lowest value, respectively.
 * 
 * @author Sebastian Olsson
 * @version 2010.12.04
 */

public class Degree{
    private int value;
    public static final int MIN = 0;
    public static final int MAX = 10;

    /**
     * Creates a Degree.
     * 
     * @param value The degree value.
     */
    public Degree(int value){
        setValue(value);
    }
    
    /**
     * Creates a Degree.
     * 
     * @param value The degree that this degree is identical to.
     */
    public Degree(Degree degree){
        setValue(degree.getValue());
    }

    /**
     * @return The degree value.
     */
    public int getValue(){
        return value;
    }

    /**
     * Sets the degree value. If the value is
     * higher than the upper or lower limit,
     * the value is set to the upper or lower
     * limit, respectively.
     * 
     * @param value The new value.
     */
    public void setValue(int value){
        if(value <= MIN){
            modifyValue(MIN);
        }else if(value >= MAX){
            modifyValue(MAX);
        }else{
            modifyValue(value);
        }
    }

    /**
     * Increases the degree value.
     * 
     * @param value The increased value.
     */
    public void increaseValue(int value){
        setValue(this.value+value);
    }

    /**
     * Decreases the degree value.
     * 
     * @param value The decreased value.
     */
    public void decreaseValue(int value){
        setValue(this.value-value);
    }

    /**
     * Sets the degree value, with no restrictions.
     * 
     * @param value The new value.
     */
    private void modifyValue(int value){
        this.value = value;
    }

    /**
     * Increases the value by 1.
     */
    public void increment(){
        setValue(getValue()+1);
    }

    /**
     * Decreases the value by 1.
     */
    public void decrement(){
        setValue(getValue()-1);
    }

    /**
     * Sets the value to the lowest possible value.
     */
    public void setValueMin(){
        setValue(MIN);
    }

    /**
     * Sets the value to the highest possible value.
     */
    public void setValueMax(){
        setValue(MAX);
    }
}