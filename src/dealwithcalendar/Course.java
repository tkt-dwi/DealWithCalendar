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
     * @param _startYear
     * @param _startMonth
     * @param _startDay
     * @param _endYear
     * @param _endMonth
     * @param _endDay
     * @param _name
     * @param _id
     * @param _cr
     */
    public Course(int _startYear, int _startMonth, int _startDay, int _endYear, int _endMonth, int _endDay, String _name, int _id, int _cr) {
        initializeFields(_startYear, _startMonth, _startDay, _endYear, _endMonth, _endDay, _name);
        id = _id;
        cr = _cr;
    }

    /**
     * Constructor to init a new course. No credits or courseID used
     * @param _startYear
     * @param _startMonth
     * @param _startDay
     * @param _endYear
     * @param _endMonth
     * @param _endDay
     * @param _name
     */

    public Course(int _startYear, int _startMonth, int _startDay, int _endYear, int _endMonth, int _endDay, String _name) {
        initializeFields(_startYear, _startMonth, _startDay, _endYear, _endMonth, _endDay, _name);
    }

    private void initializeFields(int _startYear, int _startMonth, int _startDay, int _endYear, int _endMonth, int _endDay, String _name) {
        start = Calendar.getInstance();
        end = Calendar.getInstance();
        start.set(_startYear, _startMonth, _startDay);
        end.set(_endYear, _endMonth, _endDay);
        name = _name;
    }

    /** Adds a new course event to the course.
     *
     * @param _day  Number representation of the weekday (type int is just a workin
     *              version so far)
     * @param _starttime Time in intform i.e. 1415
     * @param _location
     */
    public void addCourseEvent(int _day, int _starttime, String _location) {
        //Event cEvent = new Event (_day, _starttime, _location);
        // cEvents.add(cEvent);
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

