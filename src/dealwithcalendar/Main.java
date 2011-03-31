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
