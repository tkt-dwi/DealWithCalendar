package dealwithcalendar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of FileOperations methods.
 * 
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
     * Creates some dummy Events and Courses. Writes them to a file and then
     * reads them. After that compares some info of the read information to the
     * original versions. Returns success if assertEquals match.
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

        FileOperations.writeCalendar(testCalendar, "testcalendar.test");
        FileOperations.writeCourseEvents(courses, "testevents.test");
        FileOperations.closeOutputFile();

        
        dwiCalendar readDWI = FileOperations.readDWICalendar("testcalendar.test");
        HashMap<Integer, Course> readCourses = FileOperations.readCourses("testevents.test");

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

    @Test
    public void writeWeekTest(){
        ArrayList <Event> oneWeek = new ArrayList<Event>();
        
        oneWeek.add(createDummyEventWithTime(2011, 3, 12, 13));
        oneWeek.add(createDummyEventWithTime(2011, 3, 13, 14));
        oneWeek.add(createDummyEventWithTime(2011, 5, 12, 13));
        oneWeek.add(createDummyEventWithTime(2011, 6, 9, 8));
        
        FileOperations.writeWeek(oneWeek, "testWeek.txt");
        
        File writtenFile = new File("testweek.txt");
        
        assertTrue(writtenFile.exists());
    }

    private Event createDummyEventWithTime(int year, int day, int starthour, int endhour){
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(year, 0, day, starthour, 0, 0);
        start.set(Calendar.MILLISECOND, 0);
        end.set(year, 0, day, endhour, 0, 0);
        end.set(Calendar.MILLISECOND, 0);
        return new Event(start, end, "Gurula", "DummyCourse");
    }

}