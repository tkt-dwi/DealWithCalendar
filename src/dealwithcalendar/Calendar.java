package dealwithcalendar;

/**
 * Calendar class (NOT java.util.Calendar) representing one schoolyear, containing the courses for it
 * 
 * @author Deal With It Productions
 * @version 0.1
 */
public class Calendar implements Comparable<Calendar>
{
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
	 * 
	 * @param _c The Calendar being compared to
	 * @return Negative if this Calendar is earlier, positive if later, 0 if they're the same year
	 */
	public int compareTo(Calendar _c)
	{
		return this.year - _c.year;
	}
}
