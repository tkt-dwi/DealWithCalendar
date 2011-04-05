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
public class dwiCalendarTest {

    public dwiCalendarTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    /**
     * Test for seeing if year comparison works. Simple business
     */
    @Test
    public void yearTest(){
        dwiCalendar first = new dwiCalendar(2010);
        dwiCalendar second = new dwiCalendar(2011);
        assertTrue(first.compareTo(second) < 0);
        assertTrue(second.compareTo(first) > 0);
    }
    /**
     * Test to see if a single event ends up in the calendar and can be pulled from there
     */
    @Test
    public void singleEventTest(){
        dwiCalendar testCalendar = new dwiCalendar(2011);
        Event testEvent = new Event(Calendar.getInstance(), Calendar.getInstance(), "A111", "OhPeLuento");
        testCalendar.addEvent(testEvent);
        ArrayList<Event> eventArray = testCalendar.getEvents();
        assertFalse(eventArray.isEmpty());
        assertTrue(eventArray.contains(testEvent));
    }

    @Test
    public void addingEventsTest(){
        dwiCalendar testCalendar = new dwiCalendar(2011);
        Event first = createDummyEvent(2011, 1, 3);
        testCalendar.addEvent(first);

        assertEquals(1, testCalendar.size());

        Event second = createDummyEvent(2011, 2, 4);

        testCalendar.addEvent(second);

        assertEquals(1, testCalendar.size());

        Event third = createDummyEvent(2011, 3, 4);

        testCalendar.addEvent(third);

        assertEquals(2, testCalendar.size());
    }
    /**
     * Test to see if many events can be added to the list in arbitary order
     * and then pulled from there ordered by date
     */
    @Test
    public void manyEventsTest(){
        dwiCalendar testCalendar = new dwiCalendar(2011);
        Event first = createDummyEvent(2011,1, 2);
        Event second = createDummyEvent(2011, 3, 4);
        Event third = createDummyEvent(2011, 5, 6);

        // We'll add the Event objects in "wrong" order
        testCalendar.addEvent(first);
        testCalendar.addEvent(third);
        testCalendar.addEvent(second);
        ArrayList<Event> eventArray = testCalendar.getEvents();

        // We'll see if the events are in order
        assertEquals(first, eventArray.get(0));
        assertEquals(second, eventArray.get(1));
        assertEquals(third, eventArray.get(2));

    }
    /**
     * Test to see if many events can be pulled from the calendar according
     * to the given Calendar parameters
     */
    @Test
    public void partialEventsTest(){

        dwiCalendar testCalendar = new dwiCalendar(2011);

        Event first = createDummyEvent(2011, 1, 2);
        Event second = createDummyEvent(2011, 3, 4);
        Event third = createDummyEvent(2011, 5, 6);
        Event fourth = createDummyEvent(2011, 7, 8);
        Event fifth = createDummyEvent(2011, 9, 10);

        testCalendar.addEvent(first);
        testCalendar.addEvent(second);
        testCalendar.addEvent(third);
        testCalendar.addEvent(fourth);
        testCalendar.addEvent(fifth);

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        startDate.set(2011, 0, 2, 0, 0, 0);
        endDate.set(2011, 0, 8, 0, 0, 0);

        ArrayList<Event> eventArray = testCalendar.getEvents(startDate, endDate);

        // The getEvents() should return second and third events.
        assertFalse(eventArray.isEmpty());
        assertEquals(3, eventArray.size());
        assertEquals(second, eventArray.get(0));
        assertEquals(third, eventArray.get(1));
        assertEquals(fourth, eventArray.get(2));

        startDate.set(2011, 0, 7, 0, 0, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        endDate.set(2011, 0, 20, 0, 0, 0);
        endDate.set(Calendar.MILLISECOND, 0);

        System.out.println("-----Problem-----");
        eventArray = testCalendar.getEvents(startDate, endDate);
        assertFalse(eventArray.isEmpty());
        assertEquals(2, eventArray.size());
        assertEquals(fourth, eventArray.get(0));
        assertEquals(fifth, eventArray.get(1));
        System.out.println("-----EndProblem -----");

    }

    @Test
    public void weeklyEventTest(){
        System.out.println("-----StartOfWeeklyTests-----");
        dwiCalendar testCalendar = new dwiCalendar(2011);

        Event first = createDummyEvent(2011, 1, 2);
        Event second = createDummyEvent(2011, 4, 5);
        Event third = createDummyEvent(2011, 6, 7);
        Event fourth = createDummyEvent(2011, 10, 11);

        testCalendar.addEvent(first);
        testCalendar.addEvent(second);
        testCalendar.addEvent(third);
        testCalendar.addEvent(fourth);


        ArrayList <Event> Week1List = testCalendar.getEventsOfWeek(2011, 1);

        assertFalse(Week1List.isEmpty());
        assertEquals(2, Week1List.size());
        assertEquals(second, Week1List.get(0));
        assertEquals(third, Week1List.get(1));

        Calendar dateFromWeek1 = Calendar.getInstance();
        dateFromWeek1.set(2011, 0, 8, 0, 0);

        Week1List = testCalendar.getEventsOfWeek(dateFromWeek1);

        assertFalse(Week1List.isEmpty());
        assertEquals(2, Week1List.size());
        assertEquals(second, Week1List.get(0));
        assertEquals(third, Week1List.get(1));
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

}