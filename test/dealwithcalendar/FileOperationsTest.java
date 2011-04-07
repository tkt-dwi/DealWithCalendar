/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dealwithcalendar;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jjnyman
 */
public class FileOperationsTest {




    public FileOperationsTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    private Event createDummyEvent(int year, int startday, int endday){
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(year, 0, startday, 0, 0, 0);
        start.set(Calendar.MILLISECOND, 0);
        end.set(year, 0, endday, 0, 0, 0);
        end.set(Calendar.MILLISECOND, 0);
        return new Event(start, end, "Gurula", "DummyCourse");
    }


    /**
     * Test of all FileOperations methods.
     */
    @Test
    public void testWriteNReadCalendar() throws IOException {
        dwiCalendar testCalendar = new dwiCalendar(2011);
        HashMap<Integer, Course> courses = new HashMap<Integer, Course>();

        Event first = createDummyEvent(2011,1, 2);
        Event second = createDummyEvent(2011, 3, 4);
        Event third = createDummyEvent(2011, 5, 6);

        // We'll add the Event objects in "wrong" order
        testCalendar.addEvent(first);
        testCalendar.addEvent(third);
        testCalendar.addEvent(second);

        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();

        Course c1 = new Course(startTime, endTime, "test1", 1, 4);
        Course c2 = new Course(startTime, endTime, "test2", 3, 5);
        Course c3 = new Course(startTime, endTime, "test3", 2, 6);

        courses.put(1, c1);
        courses.put(2, c2);
        courses.put(3, c3);

        FileOperations.startWrite(testCalendar, courses, "testCalendar.test");
        FileOperations.closeOutputFile();

        //writes the testcalendar to file
//        FileOperations.writeCalendar(testCalendar);
//        FileOperations.closeFile(FileOperations.output);

        //reads the just created testfile and compares few elements

        FileOperations.startRead("testCalendar.test");
        dwiCalendar readDWI = FileOperations.readDWICalendar();
        HashMap<Integer, Course> readCourses = FileOperations.readCourses();

        FileOperations.closeInputFile();

        int expResult = testCalendar.getYear();
        int result = readDWI.getYear();
        assertEquals(expResult, result);

        String expResult2 = testCalendar.getEvents().get(1).getName();
        String result2 = readDWI.getEvents().get(1).getName();
        assertEquals(expResult2, result2);

        Calendar expResult3 = testCalendar.getEvents().get(testCalendar.getEvents().size()-1).getStarttime();
        Calendar result3 = readDWI.getEvents().get(testCalendar.getEvents().size()-1).getStarttime();
        assertEquals(expResult3, result3);


        int expResult4 = courses.get(1).getCr();
        int result4 = readCourses.get(1).getCr();
        assertEquals(expResult4, result4);


        File file = new File( "testCalendar.test" );
        file.delete();
    }

}