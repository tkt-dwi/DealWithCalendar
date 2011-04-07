package dealwithcalendar;

/**
 * Course object can contain several course events that are created from this
 * class. Mainly focused on lectures and studygroups so far.
 *
 */


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Event implements Comparable<Event>, Serializable{

    private Calendar starttime;
    private Calendar endtime;
    private int courseID = -1;
    private int type = 4;
    private String location;
    private String name;
    private String ownMarkings = "";
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

    public void setType(int t) {
        if (t < 0 || t > 4)
            this.type = 4;

        else this.type = t;
    }

    public int getType() {
        return this.type;
    }

    public void setOwnMarkings(String om) {
        this.ownMarkings = om;
    }

    public String getOwnMarkings() {
        return this.ownMarkings;
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

    public void setStarttime(Calendar starttime) {
        this.starttime = starttime;
    }

    public Calendar getEndtime(){
        return endtime;
    }

    public void setEndtime(Calendar endtime) {
        this.endtime = endtime;
    }

    public boolean isAttending() {
        return attending;
    }

    public void setAttending(boolean attending) {
        this.attending = attending;
    }

    public void setName(String n){
        this.name = n;
    }

    public String getName(){
        return name;
    }

    public int getCourseID(){
        return courseID;
    }

    public void setCourseID(int courseID){
        this.courseID = courseID;
    }

    public int compareTo(Event o) {
        int x = this.starttime.compareTo(o.starttime);
        if(x == 0)
            x = this.name.compareTo(o.name);
        return x;
    }

    public boolean equals(Event e){

        return (this.starttime.equals(e.starttime) && this.endtime.equals(e.endtime)
                && this.location.equalsIgnoreCase(e.location) && this.name.equalsIgnoreCase(e.name)
                && (this.courseID == e.courseID));

    }

    @Override
    public String toString(){
        String startString = starttime.get(Calendar.HOUR_OF_DAY) + ":" + starttime.get(Calendar.MINUTE);
        String endString = endtime.get(Calendar.HOUR_OF_DAY) + ":" + endtime.get(Calendar.MINUTE);
        String typeString = "";

        return name + " " + getEventType() + "\nPaikka: " + location + "\nAlku: " + startString + "\nLoppu: " + endString + "\nMerkinnät: " + ownMarkings;
    }

    private String getEventType(){
        switch (type){
            case(courseEvent.LECTURE):
                return "Luento";
            case(courseEvent.GUIDGROUP):
                return "Ohjausryhmä";
            case(courseEvent.STUDYGROUP):
                return "Opintopiiri";
            case(courseEvent.TEST):
                return "Tentti";
            default:
                return "";
        }
    }


    

}
