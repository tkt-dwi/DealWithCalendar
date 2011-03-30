package dealwithcalendar;

import java.util.*;

/**
 * Course object, contains information on the course such as dates and credit, as well as name and course ID
 *
 * @author Deal With It Productions
 * @version 0.1
 */
public class Course implements Comparable<Course> {

    private Calendar start, end; //start and end dates for the course
    private String name; //course name
    private int id, cr; //course ID and the credits it's worth
    private ArrayList<Event> cEvents;

    /**
     * Constructor to init a new course. The params dictate the starting and ending date, earned credits and courseID
     * @param _start The date when course starts
     * @param _end The date when course ends
     * @param _name The date when course ends
     * @param _id The id identifying the course
     * @param _cr Course credits gained from the course
     */
    public Course(Calendar _start, Calendar _end, String _name, int _id, int _cr) {
        initializeFields(_start, _end, _name);
        id = _id;
        cr = _cr;
    }

    /**
     * Constructor to init a new course. No credits or courseID used
     * @param _start The date when course starts
     * @param _end The date when course ends
     * @param _name
     */

    public Course(Calendar _start, Calendar _end, String _name) {
        initializeFields(_start, _end, _name);
    }

    //Private method for initializing the fields, just avoiding rewriting this for both constructors

    private void initializeFields(Calendar _start, Calendar _end, String _name) {
        this.start = _start;
        this.end = _end;
        this.name = _name;
    }

    /**
     * Method for adding an event to this course
     * @param _starttime
     * @param _endtime
     * @param _location
     * @param eventName
     */
    public void addCourseEvent(Calendar _starttime, Calendar _endtime, String _location, String eventName) {
        Event cEvent = new Event (_starttime, _endtime, _location, eventName, id);
        cEvents.add(cEvent);
    }

    /**
     * Comparable required method for comparing objects
     *
     * @param _c The Course being compared to
     * @return Negative if this.id is smaller, positive if not, 0 if they're equal
     */
    public int compareTo(Course _c) {
        return this.id - _c.id;
    }

    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }
}

