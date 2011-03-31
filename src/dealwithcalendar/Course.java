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
    private ArrayList<Calendar> lectures;
    private ArrayList<Calendar> studygroups;
    private ArrayList<Calendar> tests;
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
        this.lectures = new ArrayList<Calendar>();
        this.tests = new ArrayList<Calendar>();
        this.studygroups = new ArrayList<Calendar>();
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
    public void addLectureTime(Calendar a){
        lectures.add(a);
    }
    public void addStudygroupTime(Calendar a){
        studygroups.add(a);
    }
    public void addTestTime(Calendar a){
        tests.add(a);
    }
    public ArrayList<Calendar> getLectureTimes(){
        return lectures;
    }
    public ArrayList<Calendar> getStudygroupTimes(){
        return studygroups;
    }
    public ArrayList<Calendar> getTestTimes(){
        return studygroups;
    }
}

