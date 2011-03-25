package dealwithcalendar;

import java.util.*;

/**
 * A class containing general information of the student
 * 
 * TODO: Serialization?
 * 
 * @author Deal With It Productions
 * @version 0.1
 */
public class Student
{
	private TreeSet<Comparable<Calendar>> calendars; //list of Calendars specific per year
	private String name; //Student name
	private int credits, startingYear, currentYear; //amount of credits, year started studies, current year
	
	/**
	 * Init a new Student object using just the student name
	 * @param _name The name of the student
	 */
	public Student(String _name)
	{
		name = _name;
		credits = 0; //no credits earned when starting school
		startingYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); //get the starting year
		currentYear = startingYear; //set current year
		
		calendars = new TreeSet<Comparable<Calendar>>(); // init the calendar list
		calendars.add(new Calendar(startingYear)); //add this year's calendar
	}
	
	/**
	 * Run at login events
	 */
	public void onLogin()
	{
		int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
		if(currentYear != year)
		{
			currentYear = year; //update year
			calendars.add(new Calendar(year)); //add the new calendar
		}
	}
}
