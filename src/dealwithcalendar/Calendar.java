package dealwithcalendar;

import java.util.*;

/**
 * Calendar class (NOT java.util.Calendar) representing one schoolyear, containing the courses for it
 * 
 * @author Deal With It Productions
 * @version 0.1
 */
public class Calendar implements Comparable<Calendar>
{
	private TreeSet<Comparable<Course>> courses; //List of courses in this calendar
	private int year; //The year this Calendar represents
	
	/**
	 * Constructor, sets the year of the course
	 * 
	 * @param _y The current year
	 */
	public Calendar(int _y)
	{
		year = _y;
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
	 * Compares two Calendars. Required by Comparable<Calendar>
	 * 
	 * @param _c The Calendar being compared to
	 * @return Negative if this Calendar is earlier, positive if later, 0 if they're the same year
	 */
	public int compareTo(Calendar _c)
	{
		return this.year - _c.year;
	}
}
