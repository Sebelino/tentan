/**
 * Gradually decreases a person's spryness. The spryness
 * decreases periodically and resembles a step function.
 * 
 * @author Sebastian Olsson
 * @version 2010.12.04
 */
public class CircadianRhythm{
    private Time lastTime;
    private int amount;
    private Time interval;

    /**
     * Creates a circadian rhythm.
     * 
     * @param startTime The phase shift of the time.
     * @param amount The quantity of the reduction of
     * the person's spryness.
     * @param interval The time between every reduction.
     */
    public CircadianRhythm(Time startTime,int amount,Time interval){
        this.lastTime = new Time(startTime);
        this.amount = amount;
        this.interval = new Time(interval);
    }

    /**
     * @return The quantity of the reduction of
     * the person's spryness.
     * @param startTime The phase shift of the time.
     */
    public int getReduction(Time currentTime){
        int counter = (currentTime.getTimeInMinutes()-lastTime.getTimeInMinutes())/interval.getTimeInMinutes();
        if(counter == 0){
            return 0;
        }
        lastTime.setMinute(counter*interval.getTimeInMinutes());
        return counter*amount;
    }
}
