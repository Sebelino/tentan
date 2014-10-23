/**
 * Represents a time. Consists of an hour and a minute.
 * 
 * @author Sebastian Olsson 
 * @version 2010.12.04
 */
public class Time{
    private int hour;
    private int minute;

    /**
     * Creates a Time.
     * 
     * @param hour The input hour.
     * @param hour The input minute.
     */
    public Time(int hour,int minute){
        setHour(hour);
        setMinute(minute);
    }
    
    /**
     * Creates a Time.
     * 
     * @param time The input time that this time is identical to.
     */
    public Time(Time time){
        setHour(time.getHour());
        setMinute(time.getMinute());
    }

    /**
     * @return The hour.
     */
    public int getHour(){
        return hour;
    }

    /**
     * @return The minute.
     */
    public int getMinute(){
        return minute;
    }
    
    /**
     * @return The time in minutes.
     */
    public int getTimeInMinutes(){
        return minute+60*hour;
    }    

    /**
     * Sets the minute.
     * 
     * @param value The number of minutes.
     */
    public void setMinute(int value){
        minute = value;
        updateTime();
    }
    
    /**
     * Sets the hour.
     * 
     * @param value The number of hours.
     */
    public void setHour(int value){
        hour = value;
    }
    
    /**
     * Increases the minute.
     * 
     * @param minutes The number of increased minutes.
     */
    public void increaseMinute(int minutes){
        setMinute(minute+minutes%60);
        setHour(hour+minutes/60);
    }

    /**
     * Increases the minute.
     * 
     * @param hours The number of increased hours.
     */
    public void increaseHour(int hours){
        setHour(hour+hours);
    }
    
    /**
     * Ensures that the time is formatted correctly,
     * i.e. the number of minutes is between 0 and 59.
     * If not, this method adjusts the time correctly.
     */
    private void updateTime(){
        while(minute < 0){
            hour--;
            minute += 60;
        }
        hour += minute/60;
        minute = minute%60;
    }
    
    /**
     * Displays the time.
     */
    public void displayTime(){
        System.out.print(getTimeString());
    }
    
    /**
     * @return The time displayed in a String in the form HH:MM.
     */
    public String getTimeString(){
        return ""+getHourString()+getSeparatorString()+getMinuteString();
    }
    
    /**
     * @return The hour, converted to a String.
     * If the hour consists of one digit, a prefix zero
     * will be added.
     */
    private String getHourString(){
        String text = "";
        if(hour < 10){
            text += "0";
        }
        text += hour;
        return text;
    }
    
    /**
     * @return The minute, converted to a String.
     * If the minute consists of one digit, a prefix zero
     * will be added.
     */
    private String getMinuteString(){
        String text = "";
        if(minute < 10){
            text += "0";
        }
        text += minute;
        return text;
    }

    /**
     * @return The symbol that separates the hour and
     * minute when displayed.
     */
    private String getSeparatorString(){
        return ":";
    }
}
