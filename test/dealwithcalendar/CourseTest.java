/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dealwithcalendar;

import java.util.ArrayList;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author koheikki
 */
public class CourseTest {

    public CourseTest() {
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

    @Test
    public void basicFunctionalityTest(){

        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.DATE, 20);

        Course firstCourse = new Course(startTime, endTime, "Ohpe", 0, 4);

        startTime = (Calendar)endTime.clone();
        endTime = (Calendar)startTime.clone();
        endTime.add(Calendar.DATE, 20);

        Course secondCourse = new Course(startTime, endTime, "Ohja", 1, 4);

        assertTrue(firstCourse.compareTo(secondCourse) < 0);
        assertEquals(secondCourse.getId(), 1);
        assertEquals(secondCourse.getName(), "Ohja");
        assertEquals(secondCourse.getCr(), 4);
        assertEquals(secondCourse.getStart(), startTime);
        assertEquals(secondCourse.getEnd(), endTime);

    }

    @Test
    public void eventGenerationTest(){
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        Calendar eventTime;
        startTime.set(2011, 0, 1, 0, 0, 0);
        endTime.set(2011, 0, 31, 0, 0, 0);

        Course testCourse = new Course(startTime, endTime, "Ohpe", 0, 4);

        eventTime = createTime(12, 0);
        addLectureStudygroup(testCourse, courseEvent.STUDYGROUP, eventTime, Calendar.MONDAY);
        eventTime = createTime(14, 0);
        addLectureStudygroup(testCourse, courseEvent.LECTURE, eventTime, Calendar.TUESDAY);

        ArrayList<Event> events = testCourse.generateEvents();

        assertFalse(events.isEmpty());
        assertEquals(9, events.size());




    }

    private Calendar createTime(int hour, int minute) {
        Calendar eventTime = Calendar.getInstance();
        eventTime.set(Calendar.HOUR_OF_DAY, hour);
        eventTime.set(Calendar.MINUTE, minute);
        eventTime.set(Calendar.SECOND, 0);
        return eventTime;
    }

    private void addLectureStudygroup(Course a, int _type, Calendar _time, int _weekday){
        a.addCourseEvent(_type, _time, _weekday, 120, "A111");
    }

}