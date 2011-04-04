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
        Calendar examTime = Calendar.getInstance();
        Calendar eventTime;
        startTime.set(2011, 0, 1, 0, 0, 0);
        endTime.set(2011, 0, 31, 0, 0, 0);

        examTime.set(2011, 1, 1, 12, 0, 0);
        examTime.set(Calendar.MILLISECOND, 0);

        Course testCourse = new Course(startTime, endTime, "Ohpe", 0, 4);

        eventTime = createTime(12, 0);
        addLectureStudygroup(testCourse, courseEvent.STUDYGROUP, eventTime, Calendar.MONDAY);
        eventTime = createTime(14, 0);
        addLectureStudygroup(testCourse, courseEvent.LECTURE, eventTime, Calendar.TUESDAY);

        testCourse.addExam(examTime, "A111", 180);

        ArrayList<Event> events = testCourse.generateEvents();

        assertFalse(events.isEmpty());
        assertEquals(10, events.size());

        ArrayList<Event> mondayEvents = new ArrayList<Event>();
        ArrayList<Event> tuesdayEvents = new ArrayList<Event>();
        ArrayList<Event> exams = new ArrayList<Event>();
        for (int i = 0; i < events.size(); i++){
            String eventName = events.get(i).getName();
            if(eventName.equals("Ohpe lecture"))
                tuesdayEvents.add(events.get(i));
            else if (eventName.equals("Ohpe studygroup"))
                mondayEvents.add(events.get(i));
            else if (eventName.equals("Ohpe exam"))
                exams.add(events.get(i));
        }

        assertFalse(mondayEvents.isEmpty());
        assertFalse(tuesdayEvents.isEmpty());
        assertFalse(exams.isEmpty());

        assertEquals(mondayEvents.size(), 5);
        assertEquals(tuesdayEvents.size(), 4);
        assertEquals(exams.size(), 1);

        for(int i = 0; i < mondayEvents.size(); i++){
            assertEquals(Calendar.MONDAY, mondayEvents.get(i).getStarttime().get(Calendar.DAY_OF_WEEK));
        }
        for(int i = 0; i < tuesdayEvents.size(); i++){
            assertEquals(Calendar.TUESDAY, tuesdayEvents.get(i).getStarttime().get(Calendar.DAY_OF_WEEK));
        }

        Event firstStudygroup = mondayEvents.get(0);


        Calendar studyGroupStartTime = Calendar.getInstance();

        studyGroupStartTime.set(2011, 0, 3, 12, 0, 0);
        studyGroupStartTime.set(Calendar.MILLISECOND, 0);

        Calendar studyGroupEndTime = Calendar.getInstance();
        studyGroupEndTime.set(2011, 0, 3, 14, 0, 0);
        studyGroupEndTime.set(Calendar.MILLISECOND, 0);

        assertEquals(studyGroupStartTime, firstStudygroup.getStarttime());
        assertEquals(studyGroupEndTime, firstStudygroup.getEndtime());

        Event secondStudygroup = mondayEvents.get(1);

        studyGroupStartTime.add(Calendar.DATE, 7);
        studyGroupEndTime.add(Calendar.DATE, 7);

        assertEquals(studyGroupStartTime, secondStudygroup.getStarttime());
        assertEquals(studyGroupEndTime, secondStudygroup.getEndtime());


    }

    private Calendar createTime(int hour, int minute) {
        Calendar eventTime = Calendar.getInstance();
        eventTime.set(Calendar.HOUR_OF_DAY, hour);
        eventTime.set(Calendar.MINUTE, minute);
        eventTime.set(Calendar.SECOND, 0);
        eventTime.set(Calendar.MILLISECOND, 0);
        return eventTime;
    }

    private void addLectureStudygroup(Course a, int _type, Calendar _time, int _weekday){
        a.addCourseEvent(_type, _time, _weekday, 120, "A111");
    }

}