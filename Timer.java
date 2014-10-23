/**
 * Keeps the time. Consists of a current time and an end time.
 * 
 * @author Sebastian Olsson 
 * @version 2010.12.04
 */
public class Timer{
    private Time currentTime;
    private Time endTime;

    /**
     * Creates a Timer.
     */
    public Timer(){
        currentTime = new Time(8,0);
        endTime = new Time(8,0);
    }

    /**
     * @returns The current time.
     */
    public Time getCurrentTime(){
        return currentTime;
    }

    /**
     * @return The current minute.
     */
    public int getCurrentMinute(){
        return currentTime.getMinute();
    }
    
    /**
     * @return The current hour.
     */
    public int getCurrentHour(){
        return currentTime.getHour();
    }
    
    /**
     * Displays the time.
     */
    public void displayCurrentTime(){
        System.out.print("Time: ");
        currentTime.displayTime();
    }
    
    /**
     * Sets the current time to a input time.
     * 
     * @param time The increased time.
     */
    public void setCurrentTime(Time time){
        currentTime = new Time(time);
    }

    /**
     * Increases current time by minutes minutes.
     * 
     * @param hours The increased minutes.
     */
    public void increaseMinute(int minutes){
        currentTime.increaseMinute(minutes);
    }

    /**
     * Increases current time by hours hours.
     * 
     * @param hours The increased hours.
     */
    public void increaseHour(int hours){
        currentTime.increaseHour(hours);
    }
}
