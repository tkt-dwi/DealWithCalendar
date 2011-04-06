package dealwithcalendar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;


/**
 * Functions to read and write the calendar in use to a file
 *
 */
public class FileOperations{

    static OutputStream file, buffer;
    static ObjectOutput output;
    static InputStream fileIn, bufferIn;
    static ObjectInput input;


    /**
     * Initializes writing operations to save the information needed by the
     * software to work properly
     * @param calendar dwiCalendar to be written to file
     * @param courses HashMap of courses to be written to file
     * @param name Name of the file to write to
     * @throws IOException
     */
//    public static void startWrite(dwiCalendar calendar, HashMap<Integer, Course> courses, String name) throws IOException{
//         try {
//            file = new FileOutputStream(name);
//            buffer = new BufferedOutputStream(file);
//            output = new ObjectOutputStream(buffer);
//
//        } catch (IOException ex) {
//            System.out.println("Could not save the calendar and events to file");
//        }
//         writeCalendar(calendar);
//         writeCourseEvents(courses);
//
//    }

    /**
     * Closes the file that the writing operations used
     * @param output
     * @throws IOException
     */
    public static void closeOutputFile() throws IOException{
        output.close();
    }

    /**
     * Writes HashMap containing Courses to file
     * @param courses
     * @throws IOException
     */
    public static void writeCourseEvents(HashMap<Integer, Course> courses, String name) throws IOException{
        try {
            file = new FileOutputStream(name);
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);

        } catch (IOException ex) {
            System.out.println("Could not save the calendar and events to file");
        }
        try {
            output.writeObject(courses);
            output.close();
        }catch (IOException ex) {
            System.out.println("Could not save the calendar and events to file");
        }
    }

    /**
     * Writes the calendar given as parameter to a file name 'mycalendar.cld'
     * @param calendar dwiCalendar that will be written
     */
    public static void writeCalendar(dwiCalendar calendar, String name) {
        try {
            file = new FileOutputStream(name);
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);

        } catch (IOException ex) {
            System.out.println("Could not save the calendar and events to file");
        }
        try {
            output.writeObject(calendar);
            output.close();

        } catch (IOException ex) {
            System.out.println("Could not save the calendar and events to file");
        }

    }

    /**
     * Initializes the start to read a file
     * @param name Name of the file to be read
     */

//    public static void startRead(String name){
//        try{
//      //use buffering
//            fileIn = new FileInputStream(name);
//            bufferIn = new BufferedInputStream( fileIn );
//            input = new ObjectInputStream ( bufferIn );
//        }catch(IOException ex){
//        System.out.println("Error. Could not open file");
//        }
//
//    }
    /**
     * Closes the file thats been read
     * @throws IOException
     */
    public static void closeInputFile() throws IOException{
        input.close();
    }

    /**
     * Reads save information of the courses from HashMap<Integer, Course>
     * @return the saved HashMap
     * @throws IOException
     */
    public static HashMap<Integer, Course> readCourses(String name) throws IOException{
        try{
      //use buffering
            fileIn = new FileInputStream(name);
            bufferIn = new BufferedInputStream( fileIn );
            input = new ObjectInputStream ( bufferIn );
        }catch(IOException ex){
        System.out.println("Error. Could not open file");
        }
        try{
          //HashMap<Integer, Course> courses = null;
          HashMap<Integer, Course> savedCourses = null;

          if((savedCourses = (HashMap<Integer, Course>) input.readObject()) != null) {
              HashMap<Integer, Course> courses = savedCourses;
              return courses;
           }

        }catch(ClassNotFoundException ex){
            System.out.println("Error. Could not find class");
        }
        return null;
    }

    /**
     * Reads saved information and events of dwiCalendar from file
     * @return dwiCalendar object read from file.
     */
    public static dwiCalendar readDWICalendar(String name) throws IOException{
        try{
      //use buffering
            fileIn = new FileInputStream(name);
            bufferIn = new BufferedInputStream( fileIn );
            input = new ObjectInputStream ( bufferIn );
        }catch(IOException ex){
        System.out.println("Error. Could not open file");
        }
      try{
          dwiCalendar calendar = null;

          if((calendar = (dwiCalendar) input.readObject()) != null) {
              dwiCalendar dwi = calendar;
              return dwi;
           }

      }

    catch(ClassNotFoundException ex){
        System.out.println("Error. Could not find class");
    }
        return null;
  }




}