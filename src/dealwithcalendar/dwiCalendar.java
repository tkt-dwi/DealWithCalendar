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
        private TreeSet<Event> events;
	private int year; //The year this dwiCalendar represents
	
	/**
	 * Constructor, sets the year of the course
	 * 
	 * @param _y The current year
	 */
	public dwiCalendar(int _y)
	{
		year = _y;
                events = new TreeSet<Event>();
	}
	public void addEvent(Event a){
            events.add(a);
        }

        /**
         * Method for getting Events between a certain timeframe from the calendar.
         * @param start The starting time
         * @param end The ending time
         * @return A set containing the events.
         */
        public ArrayList<Event> getEvents(Calendar start, Calendar end){
            Event startEvent = null;
            Event endEvent = null;
            if(start.compareTo(end) > 0){
                return null;
            }
            Iterator<Event> eventIterator = events.iterator();
            while(eventIterator.hasNext()){
                Event current = eventIterator.next();
                if(current.getStarttime().compareTo(start) >= 0){
                    startEvent = current;
                    break;
                }
            }
            if(startEvent == null){
                return null;
            }
            while(eventIterator.hasNext()){
                Event current = eventIterator.next();
                if(current.getEndtime().compareTo(end) > 0){
                    endEvent = current;
                    break;
                }
            }
            SortedSet<Event> selected = null;
            if(endEvent != null)
                selected = events.subSet(startEvent, endEvent);
            else
                selected = events.tailSet(startEvent);

           ArrayList<Event> returnArray = new ArrayList<Event>();
           returnArray.addAll(selected);
           return returnArray;

        }

        public ArrayList<Event> getEvents(){
            ArrayList<Event> returnArray = new ArrayList<Event>();
            returnArray.addAll(events);
            return returnArray;
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
