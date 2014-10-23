/**
 * Represents a lecture. A lecture consists of a time period
 * when the lecture is held, a piece of knowledge that the
 * lecture gives, the name of the lecture locale and a
 * decription of the lecture.
 * 
 * @author Sebastian Olsson
 * @version 2010.12.04
 */
public class Lecture{
    private Period period;
    private Knowledge knowledge;
    private String description;
    private String locale;

    /**
     * Creates a Lecture.
     * 
     * @param period The time period when the lecture is held.
     * @param knowledge The piece of knowledge given.
     * @param description A description of the lecture.
     * @param description The name of the lecture locale.
     */
    public Lecture(Period period,Knowledge knowledge,String description,String locale){
        this.period = new Period(period);
        this.knowledge = new Knowledge(knowledge);
        this.description = description;
        this.locale = locale;
    }
    
    /**
     * Creates a Lecture.
     * 
     * @param startTime When the lecture starts.
     * @param endTime When the lecture ends.
     * @param knowledge The piece of knowledge given.
     * @param description A description of the lecture.
     * @param description The name of the lecture locale.
     */
    public Lecture(Time startTime,Time endTime,Knowledge knowledge,String description,String locale){
        this(new Period(startTime,endTime),knowledge,description,locale);
    }

    /**
     * @return The time period when the lecture is held.
     */
    public Period getPeriod(){
        return period;
    }
    
    /**
     * @return The piece of knowledge that the lecture gives. 
     */
    public Knowledge getKnowledge(){
        return knowledge;
    }
    
    /**
     * The period written in the form HH:MM-HH:MM
     */
    public String getPeriodString(){
        return period.getPeriodString();
    }
    
    /**
     * @return When the lecture begins.
     */
    public Time getStartTime(){
        return period.getStartTime();
    }
    
    /**
     * @return When the lecture ends.
     */
    public Time getEndTime(){
        return period.getEndTime();
    }

    /**
     * @return The name of the locale in which the lecture is held.
     */
    public String getLocale(){
        return locale;
    }
    
    /**
     * @return A description of the lecture.
     */
    public String getDescription(){
        return description;
    }
}