/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dealwithcalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author Deal With It Productions
 */
public class Main {
    private dwiCalendar currentCalendar;
    private HashMap<Integer, Course> courses;
    /**
     * @param args the command line arguments
     */

    public Main (){

        //This constructor is bullshit, this data would really come from file
        // 
        currentCalendar = new dwiCalendar(2011);
        courses = new HashMap<Integer, Course>();
    }


    public static void main(String[] args) {

        Main main = new Main();
        GUI gui = new GUI(main);
        /*
        while (true)
        {}
         * 
         */
    }

    /*public void addCourse(Calendar startTime, Calendar endTime, ){

    }*/

    public Course getCourse(int courseID){
        return courses.get(courseID);
    }
    private boolean createEvents(Course source){

        return true;
    }

    /**
     * Tools to handle single events and to create one
     */
     public void addEvent(Calendar starttime, Calendar endtime, int courseID,
                          String location, String name, boolean attending){
        Event e = new Event(starttime, endtime, location, name, courseID);
        currentCalendar.addEvent(e);
    }

    public void changeEventStarttime(Event e, Calendar starttime){
        e.setStarttime(starttime);
    }


    public void changeEventEndtime(Event e, Calendar endtime){
        e.setEndtime(endtime);
    }

    public void changeEventLocation(Event e, String location){
        e.setLocation(location);
    }

    public void changeEventName(Event e, String name){
        e.setLocation(name);
    }

    public void changeEventAttending(Event e, boolean attending){
        e.setAttending(attending);
    }


    /**
     * Tools to handle single course or create one
     */
    public void addCourse(Calendar starttime, Calendar endtime, String name,
                          int credits){
        int ID = 0;
        while(true){
            if(!courses.containsKey(ID)){
                break;
            }
            ID++;
        }
        Course c = new Course(starttime, endtime, name, ID, credits);
        courses.put(ID, c);
    }

    public void addCourseEvent(Course id, int type, Calendar time, int week, int duration,
                                String location){
        id.addCourseEvent(type, time, week, duration, location);

    }

    public void changeCourseStart(int id, Calendar starttime){
        courses.get(id).setStart(starttime);
    }

    public void changeCourseEnd(int id, Calendar endtime){
        courses.get(id).setStart(endtime);
    }

    public void changeCourseName(int id, String name){
        courses.get(id).setName(name);
    }

    public void changeCourseCreditpoints(int id, int cr){
        courses.get(id).setCr(cr);
    }

    public HashMap getCourses(){
        return courses;
    }

    public Course getACourse(int id){
        return courses.get(id);
    }
//
//     Likely useless, thus commented out ATM
//
//     public void changeCourseId(int id, int ident){
//         courses.get(id).setCr(ident);
//     }


}



/*
 * TODO: GUI wants atleast these dummies
 *
 getWeek(int weekN, int yearN)
return ArrayList<Event>

getWeek(Calendar date) // palauttaa viikon jolle pvm sijoittuu
return ArrayList<Event>

 */