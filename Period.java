/**
 * Represents a period of time. It consists of two
 * times: a start time and an end time.
 * 
 * @author Sebastian Olsson 
 * @version 2010.12.04
 */
public class Period{
    private Time startTime;
    private Time endTime;

    /**
     * Creates a Period.
     * 
     * @param period The period that this period is based on.
     */
    public Period(Period period){
        this(period.getStartTime(),period.getEndTime());
    }
    
    /**
     * Creates a Period.
     * 
     * @param startTime The input start time.
     * @param endTime The input end time.
     */
    public Period(Time startTime,Time endTime){
        this(startTime.getHour(),startTime.getMinute(),endTime.getHour(),endTime.getMinute());
    }
    
    /**
     * Creates a Period.
     * 
     * @param startHour The input hour of the start time.
     * @param startHour The input minute of the start time.
     * @param startHour The input hour of the end time.
     * @param startHour The input minute of the end time.
     */
    public Period(int startHour,int startMinute,int endHour,int endMinute){
        this.startTime = new Time(startHour,startMinute);
        this.endTime = new Time(endHour,endMinute);
        update();
    }

    /**
     * Ensures that the end time is longer than the start time.
     * If not, the times are swapped.
     */
    private void update(){
        if(endTime.getTimeInMinutes() < startTime.getTimeInMinutes()){
            swap(startTime,endTime);
        }
    }

    /**
     * Swaps time A and time B.
     * 
     * @param Time A.
     * @param Time B.
     */
    private void swap(Time timeA,Time timeB){
        Time temp = startTime;
        startTime = endTime;
        endTime = temp;
    }

    /**
     * @return A String of the period written in the form HH:MM-HH:MM.
     */
    public String getPeriodString(){
        return startTime.getTimeString()+"-"+endTime.getTimeString();
    }
    
    /**
     * @return The time at which the period starts.
     */
    public Time getStartTime(){
        return startTime;
    }
    
    /**
     * @return The time at which the period ends.
     */
    public Time getEndTime(){
        return endTime;
    }
    
    /**
     * @return True if the time is within the period, else false.
     * @param time The investigated time.
     */
    public boolean within(Time time){
        return (startTime.getTimeInMinutes() <= time.getTimeInMinutes() &&
                time.getTimeInMinutes() < endTime.getTimeInMinutes());
    }
}