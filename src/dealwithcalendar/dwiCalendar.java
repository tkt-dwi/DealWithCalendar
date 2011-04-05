package dealwithcalendar;

import java.io.Serializable;
import java.util.*;

/**
 * dwiCalendar class (NOT java.util.Calendar) representing one schoolyear, containing the courses for it
 * 
 * @author Deal With It Productions
 * @version 0.1
 */
public class dwiCalendar implements Comparable<dwiCalendar>, Serializable {

    private TreeSet<Event> events;
    private int year; //The year this dwiCalendar represents

    /**
     * Constructor, sets the year of the course
     *
     * @param _y The current year
     */
    public dwiCalendar(int _y) {
        year = _y;
        events = new TreeSet<Event>();
    }

    public void addEvent(Event a) {
        events.add(a);
    }

    /**
     * Method for getting Events between a certain timeframe from the calendar.
     * @param start The starting time
     * @param end The ending time
     * @return A set containing the events.
     */
    public ArrayList<Event> getEvents(Calendar start, Calendar end) {
        Event startEvent = null;
        Event endEvent = null;
        if (start.compareTo(end) > 0) {
            return null;
        }
        Iterator<Event> eventIterator = events.iterator();
        while (eventIterator.hasNext()) {
            Event current = eventIterator.next();
            if (current.getStarttime().compareTo(start) >= 0 && current.getStarttime().compareTo(end) < 0) {
                /*Debug information, yeah!
                System.out.println("FOUND STARTEVENT FUCK YEAH\nTime:");
                DebugPrintForCalendar(current.getStarttime());*/
                startEvent = current;
                break;
            }
        }
        if (startEvent == null) {
            return null;
        }
        while (eventIterator.hasNext()) {
            Event current = eventIterator.next();
            if (current.getEndtime().compareTo(end) > 0) {
                /*Debug information, YEAH!
                System.out.println("FOUND ENDEVENT FUCK YEAH\nTime:");
                DebugPrintForCalendar(current.getEndtime());*/

                endEvent = current;
                break;
            }
        }
        SortedSet<Event> selected = null;
        if (endEvent != null) {
            selected = events.subSet(startEvent, endEvent);
        } else {
            selected = events.tailSet(startEvent);
        }

        ArrayList<Event> returnArray = new ArrayList<Event>();
        returnArray.addAll(selected);
        return returnArray;

    }

    /**
     * Getter for events according to a given year and week
     *
     * @param year The year in question
     * @param week the week in question
     * @return ArrayList<Event> object containing the events associated with the selected week.
     */
    public ArrayList<Event> getEventsOfWeek(int year, int week) {
        Calendar startOfWeek = Calendar.getInstance();
        Calendar endOfWeek = Calendar.getInstance();

        startOfWeek.set(Calendar.YEAR, year);
        startOfWeek.set(Calendar.WEEK_OF_YEAR, week);
        startOfWeek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        startOfWeek.set(Calendar.HOUR_OF_DAY, 0);
        startOfWeek.set(Calendar.MINUTE, 1);
        startOfWeek.set(Calendar.SECOND, 0);

        //Debugging info, yay
        //System.out.println("StartOfWeek:");
        //DebugPrintForCalendar(startOfWeek);

        endOfWeek.set(Calendar.YEAR, year);
        endOfWeek.set(Calendar.WEEK_OF_YEAR, week);
        endOfWeek.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        endOfWeek.set(Calendar.HOUR_OF_DAY, 23);
        endOfWeek.set(Calendar.MINUTE, 59);
        endOfWeek.set(Calendar.SECOND, 59);

        //System.out.println("endOfWeek");
        //DebugPrintForCalendar(endOfWeek);

        return getEvents(startOfWeek, endOfWeek);
    }

    private void DebugPrintForCalendar(Calendar startOfWeek) {
        System.out.println("YEAR: " + startOfWeek.get(Calendar.YEAR));
        System.out.println("WEEK: " + startOfWeek.get(Calendar.WEEK_OF_YEAR));
        System.out.println("MONTH: " + startOfWeek.get(Calendar.MONTH));
        System.out.println("DAY: " + startOfWeek.get(Calendar.DATE));
        System.out.println("HOUR: " + startOfWeek.get(Calendar.HOUR_OF_DAY));
        System.out.println("MINUTE: " + startOfWeek.get(Calendar.MINUTE));
    }

    public ArrayList<Event> getEventsOfWeek(Calendar dayInWeek) {
        return getEventsOfWeek(dayInWeek.get(Calendar.YEAR), dayInWeek.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     * getter for the whole list of events
     * @return ArrayList containing all events
     */
    public ArrayList<Event> getEvents() {
        ArrayList<Event> returnArray = new ArrayList<Event>();
        returnArray.addAll(events);
        return returnArray;
    }

    public ArrayList<Event> getEvents(int courseID){
        ArrayList<Event> returnArray = new ArrayList<Event>();
        Iterator<Event> eventIterator = events.iterator();
        while(eventIterator.hasNext()){
            Event current = eventIterator.next();
            if (current.getCourseID() == courseID){
                returnArray.add(current);
            }
        }
        return returnArray;
    }

    public void removeEvents(Event e){
        events.remove(e);
    }

    public void removeEvents(int courseID){
        ArrayList<Event> eventsToBeRemoved = getEvents(courseID);
        Iterator<Event> eventIterator = eventsToBeRemoved.iterator();
        while(eventIterator.hasNext()){
            Event current = eventIterator.next();
            events.remove(current);
        }
    }

    /**
     * Getter for the year this calendar represents
     * @return The year in question
     */
    public int getYear() {
        return year;
    }

    /**
     * Compares two Calendars. Required by Comparable<dwiCalendar>
     *
     * @param _c The dwiCalendar being compared to
     * @return Negative if this dwiCalendar is earlier, positive if later, 0 if they're the same year
     */
    public int compareTo(dwiCalendar _c) {
        return this.year - _c.year;
    }
}
