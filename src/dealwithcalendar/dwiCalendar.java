package dealwithcalendar;

import java.util.*;

/**
 * dwiCalendar class (NOT java.util.dwiCalendar) representing one schoolyear, containing the courses for it
 * 
 * @author Deal With It Productions
 * @version 0.1
 */
public class dwiCalendar implements Comparable<dwiCalendar>
{
	private TreeSet<Comparable<Course>> courses; //List of courses in this calendar
        private ArrayList<CourseEvent> events;
	private int year; //The year this dwiCalendar represents
	
	/**
	 * Constructor, sets the year of the course
	 * 
	 * @param _y The current year
	 */
	public dwiCalendar(int _y)
	{
		year = _y;
                courses = new TreeSet<Comparable<Course>>();
                events = new ArrayList<CourseEvent>();
	}
	
	/**
	 * Add a course to the calendar
	 * @param _c The course to be added
	 */
	public void addCourse(Course _c)
	{
		courses.add(_c);
	}
	
	/**
	 * Remove a course from the calendar
	 * @param _c The course to be removed
	 */
	public void removeCourse(Course _c)
	{
		courses.remove(_c);
	}
	
	/**
	 * Compares two Calendars. Required by Comparable<dwiCalendar>
	 * 
	 * @param _c The dwiCalendar being compared to
	 * @return Negative if this dwiCalendar is earlier, positive if later, 0 if they're the same year
	 */
	public int compareTo(dwiCalendar _c)
	{
		return this.year - _c.year;
	}
}
