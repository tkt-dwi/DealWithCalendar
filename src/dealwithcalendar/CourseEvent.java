package dealwithcalendar;

/**
 * Course object can contain several course events that are created from this
 * class. Mainly focused on lectures and studygroups so far.
 *
 */


import java.util.ArrayList;



public class CourseEvent {

    private int day;
    private int starttime;
    private String location;
    boolean attending = false;

    /**
     * Creates a new unspecified event tied that is tied to a specific course
     * through the Course object
     *
     * @param _day
     * @param _starttime
     * @param _location
     */
    public CourseEvent(int _day, int _starttime, String _location){
        this.day = _day;
        this.starttime = _starttime;
        this.location = _location;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public boolean isAttending() {
        return attending;
    }

    public void setAttending(boolean attending) {
        this.attending = attending;
    }

    

}
