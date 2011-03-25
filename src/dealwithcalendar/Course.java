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
	 * Comparable required method for comparing objects 
	 * 
	 * @param _c The Course being compared to
	 * @return Negative if this.id is smaller, positive if not, 0 if they're equal
	 */
	public int compareTo(Course _c)
	{
		return this.id - _c.id;
	}
}
