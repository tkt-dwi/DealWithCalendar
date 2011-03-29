package dealwithcalendar;

import java.util.*;

/**
* Course object, contains information on the course such as dates and credit, as well as name and course ID
*
* @author Deal With It Productions
* @version 0.1
*/
public class Course implements Comparable<Course>
{


private Date start, end; //start and end dates for the course
private String name; //course name
private int id, cr; //course ID and the credits it's worth
private ArrayList<CourseEvent> cEvents;


/**
* Constructor to init a new course
*
* @param _start The starting date for the course
* @param _end The ending date for the course
* @param _name The course name
* @param _id The course ID
* @param _cr The course's amount of credits
*/
public Course(Date _start, Date _end, String _name, int _id, int _cr)
{
start = _start;
end = _end;
name = _name;
id = _id;
cr = _cr;
}

/**
* Constructor to init a new course
* No course id or creditpoints used.
*
* @param _start The starting date for the course
* @param _end The ending date for the course
* @param _name The course name
* @param _cr The course's amount of credits
*/
public Course(Date _start, Date _end, String _name)
{
start = _start;
end = _end;
name = _name;
}

/** Adds a new course event to the course.
 *
 * @param _day  Number representation of the weekday (type int is just a workin
 *              version so far)
 * @param _starttime Time in intform i.e. 1415
 * @param _location
 */

public void addCourseEvent(int _day, int _starttime, String _location){
    CourseEvent cEvent = new CourseEvent (_day, _starttime, _location);
    cEvents.add(cEvent);
}

/**
* Comparable required method for comparing objects
*
* @param _c The Course being compared to
* @return Negative if this.id is smaller, positive if not, 0 if they're equal
*/
public int compareTo(Course _c)
{
return this.id - _c.id;
}


    public int getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}

