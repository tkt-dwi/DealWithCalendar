/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dealwithcalendar;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Deal With It Productions
 */
public class Main {
    dwiCalendar currentCalendar;
    TreeSet<Course> courses;
    /**
     * @param args the command line arguments
     */

    public Main (){

        //This constructor is bullshit, this data would really come from file
        // 
        currentCalendar = new dwiCalendar(2011);
        courses = new TreeSet<Course>();
    }


    public static void main(String[] args) {

        

        GUI gui = new GUI();
        /*
        while (true)
        {}
         * 
         */
    }

}
