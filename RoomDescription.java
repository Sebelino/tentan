/**
 * Representations of the descriptions of all rooms.
 * 
 * @author Sebastian Olsson
 * @version 2010.12.04
 */
public enum RoomDescription{
    THEATER_D1("outside theatre D1"),
    THEATER_E1("outside theatre E1"),
    THEATER_F1("outside theatre F1"),
    THEATER_F2("outside theatre F2"),
    THEATER_K1("outside theatre K1"),
    THEATER_Q1("outside theatre Q1"),
    HALL_V22("outside hall V22"),
    HALL_EXAM("outside the examination hall"),
    HALL_KTH("outside KTH-hallen"),
    LIBRARY("outside the KTH library"),
    INFOCENTER("outside KTH Infocenter"),
    NYMBLE("outside Nymble"),
    ESCAPEN("outside ESCapen"),
    ALLEY("in an alley of some kind"),
    LANTMATERI("outside Lantmäteriet"),
    OSQUARS_BACKE("on the street called Osquars Backe"),
    TEKNIKRINGEN("on the street called Teknikringen"),
    BRINELLVAGEN("on the street called Brinellvägen");
    
    private String description;
    
    /**
     * Creates a room description.
     * 
     * @param description The input description.
     */
    RoomDescription(String description){
        this.description = description;
    }
    
    /**
     * @return The description, converted to a String.
     */
    public String toString(){
        return description;
    }
}
