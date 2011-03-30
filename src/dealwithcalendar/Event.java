package dealwithcalendar;

/**
 * Course object can contain several course events that are created from this
 * class. Mainly focused on lectures and studygroups so far.
 *
 */


import java.util.Calendar;



public class Event implements Comparable<Event>{

    private Calendar starttime;
    private Calendar endtime;
    private int courseID = -1;
    private String location;
    private String name;
    boolean attending = false;

    /**
     * Creates a new event with given parameters
     *
     * @param _starttime The starting time of the event
     * @param _endtime The ending time of the event
     * @param _location Location of the event (For example, a classroom)
     * @param _name Name of the event
     */
    public Event(Calendar _starttime, Calendar _endtime, String _location, String _name){
        initializeFields(_starttime, _endtime, _location, _name);
    }
   /**
    * Same as before, but tied to a given courseID which identifies the course used.
    *
    * @param _starttime The starting time of the event
    * @param _endtime The ending time of the event
    * @param _location Location of the event (For example, a classroom)
    * @param _name Name of the event
    * @param _courseID courseID of the event
    */
    public Event(Calendar _starttime, Calendar _endtime, String _location, String _name, int _courseID){
        initializeFields(_starttime, _endtime, _location, _name);
        this.courseID = _courseID;
    }

    private void initializeFields(Calendar _starttime, Calendar _endtime, String _location, String _name) {
        this.starttime = _starttime;
        this.endtime = _endtime;
        this.location = _location;
        this.name = _name;
    }




    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Calendar getStarttime() {
        return starttime;
    }

    public void setStarttime(int _year, int _day, int _month, int _starthour) {
        starttime.set(_year, _month, _day, _starthour, 0);
    }

    public Calendar getEndtime(){
        return endtime;
    }

    public void setEndtime(int _year, int _day, int _month, int _endhour){
        endtime.set(_year, _month, _day, _endhour, 0);
    }

    public boolean isAttending() {
        return attending;
    }

    public void setAttending(boolean attending) {
        this.attending = attending;
    }

    public int compareTo(Event o) {
        int x = this.starttime.compareTo(o.starttime);
        if(x == 0)
            x = this.name.compareTo(o.name);
        return x;
    }


    

}
