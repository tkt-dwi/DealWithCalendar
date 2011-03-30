package dealwithcalendar;

import java.util.*;

/**
 * dwiCalendar class (NOT java.util.Calendar) representing one schoolyear, containing the courses for it
 * 
 * @author Deal With It Productions
 * @version 0.1
 */
public class dwiCalendar implements Comparable<dwiCalendar>
{
        private TreeSet<Comparable<Event>> events;
	private int year; //The year this dwiCalendar represents
	
	/**
	 * Constructor, sets the year of the course
	 * 
	 * @param _y The current year
	 */
	public dwiCalendar(int _y)
	{
		year = _y;
                events = new TreeSet<Comparable<Event>>();
	}
	public void addEvent(Event a){

        }
        /**
         * Getter for the year this calendar represents
         * @return The year in question
         */
	public int getYear(){
            return year;
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
