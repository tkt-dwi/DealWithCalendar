/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dealwithcalendar;

import java.util.HashMap;

/**
 *
 * @author Deal With It Productions
 */
public class Main {
    dwiCalendar currentCalendar;
    HashMap<Integer, Course> courses;
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

        

        GUI gui = new GUI();
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

}

/*
 * TODO: GUI wants atleast these dummies
 *
 getWeek(int weekN, int yearN)
return ArrayList<Event>

getWeek(Calendar date) // palauttaa viikon jolle pvm sijoittuu
return ArrayList<Event>

changeEvent(Event e, eventproperty ep) // to all event "properties"
return void / changer Event e

add Event(all event properties in that order they appear in Event-class)
return void/ Event

changeCourse(courseID/Course?, courseproperty) // to all course"properties"
return void/ changed Course

addCourse(all course properties in that order they appear in Course-class)
return Course

addCourseEvent(all courseEvent properties...)
return void / CourseEvent

getCourse()
return …

getCourseList/HashMap()
return....
 */