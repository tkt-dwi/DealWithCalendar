package dealwithcalendar;

import java.util.*;

/**
 * Course object, contains information on the course such as dates and credit, as well as name and course ID
 *
 * @author Deal With It Productions
 * @version 0.1
 */
public class Course implements Comparable<Course> {

    /**
     * TODO: We need information about course's lecture,
     * test and studygroup times in here, most probably with
     * information if they are already added in calendar or not
     * (booleans for each lecture, studygroup and test times separately).
     *
     * This helps in getting GUI done in the most well working manner where
     * one can easily change courses one is attending to afterwards.
     * In this way if one wants to plan his/her calendar more and changes
     * attending courses all the time he/she won't have to type the lecture
     * times every time separately. (and GUI won't give permission to add
     * lecture times to calendar which are already in there)
     *
     */

    private Calendar start, end; //start and end dates for the course
    private ArrayList<courseEvent> coursetimes;
    private String name; //course name
    private int id, cr; //course ID and the credits it's worth

    /**
     * Constructor to init a new course. The params dictate the starting and ending date, earned credits and courseID
     * @param _start The date when course starts
     * @param _end The date when course ends
     * @param _name The date when course ends
     * @param _id The id identifying the course
     * @param _cr Course credits gained from the course
     */
    public Course(Calendar _start, Calendar _end, String _name, int _id, int _cr) {
        this.start = _start;
        this.end = _end;
        this.name = _name;
        this.id = _id;
        this.cr = _cr;
        this.coursetimes = new ArrayList<courseEvent>();
    }


    /**
     * Constructor to init a new course. No credits or courseID used.
     * @param _start The date when course starts
     * @param _end The date when course ends
     * @param _name
     */

    //Questionable. I'd say that we want to ID all courses, so we can place them on hashmap.
    //I'll call this DEPRECATED for now.
    @Deprecated
    public Course(Calendar _start, Calendar _end, String _name) {
        this.start = _start;
        this.end = _end;
        this.name = _name;
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
    /**
     * Method for adding course events such as lectures and studygroups. The type parameter
     * is either LECTURE or STUDYGROUP, which are defined as static constants in courseEvent
     * class. The weekday must be one of the weekdays defined in Calendar class. If these
     * requirements are not fullfilled, the courseEvent will not be added. These courseEvents are used to
     * generate actual Event objects for dwiCalendar class.
     * 
     * @param _type The type of the event, either lecture or a studygroup.
     * @param _time The time when the event starts.
     * @param _weekday The weekday when event occurs
     * @param _duration The duration of the event in minutes
     * @param _location The location of the event.
     */
    public void addCourseEvent(int _type, Calendar _time, int _weekday, int _duration, String _location){
        if(_type != courseEvent.LECTURE && _type != courseEvent.STUDYGROUP)
            return;
        if(_weekday < 1 || _weekday > 7)
            return;

        coursetimes.add( new courseEvent(_type, _time, _weekday, _duration, _location));
    }

    /**
     * Method for adding exams to the course.
     * @param _testDate The time and date of the exam
     * @param _location The location of the exam
     * @param duration The duration of the exam in minutes
     */
    public void addTest(Calendar _testDate, String _location, int duration){
        coursetimes.add(new courseEvent(courseEvent.TEST, _testDate, 0, duration, _location));
    }
    /**
     * Method for generating an ArrayList<Event> filled with Event objects
     * generated from the data given in the course. If no courseEvents are given,
     * the method returns null
     *
     * TODO: This is still under developement.
     * 
     * @return null if no courseEvents are given for this course, otherwise an ArrayList containing the events.
     */
    public ArrayList<Event> generateEvents(){
        if(coursetimes.isEmpty())
            return null;
        ArrayList<Event> generatedEvents = new ArrayList<Event>();
        Iterator<courseEvent> courseEventIterator = coursetimes.iterator();
        while(courseEventIterator.hasNext()){
            courseEvent current = courseEventIterator.next();
            Calendar starttime;
            Calendar endtime;
            if(current.getType() == courseEvent.TEST){
                starttime = current.getTime();
            }
        }
        return generatedEvents;
    }

    private Calendar getEndtime(int minutes, Calendar original){
        Calendar endtime = Calendar.getInstance();

        copyCalendarFields(original, endtime);

        endtime.add(Calendar.MINUTE, minutes);

        return endtime;
    }

    private void copyCalendarFields(Calendar original, Calendar endtime) {
        int originalSecond, originalYear, originalMonth, originalDay, originalHour, originalMinute;
        originalYear = original.get(Calendar.YEAR);
        originalMonth = original.get(Calendar.MONTH);
        originalDay = original.get(Calendar.DATE);
        originalHour = original.get(Calendar.HOUR_OF_DAY);
        originalMinute = original.get(Calendar.MINUTE);
        originalSecond = original.get(Calendar.SECOND);
        endtime.set(originalYear, originalMonth, originalDay, originalHour, originalMinute, originalSecond);
    }
}

