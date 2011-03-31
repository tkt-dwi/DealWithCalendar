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

        testCalendar.addEvent(first);
        testCalendar.addEvent(second);
        testCalendar.addEvent(third);
        testCalendar.addEvent(fourth);

        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        startDate.set(2011, 1, 2, 0, 0, 0);
        endDate.set(2011, 1, 6, 0, 0, 0);

        ArrayList<Event> eventArray = testCalendar.getEvents(startDate, endDate);

        // The getEvents() should return second and third events.
        assertFalse(eventArray.isEmpty());
        assertEquals(2, eventArray.size());
        assertEquals(second, eventArray.get(0));
        assertEquals(third, eventArray.get(1));

    }

    private Event createDummyEvent(int year, int startday, int endday){
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(year, 1, startday, 0, 0, 0);
        end.set(year, 1, endday, 0, 0, 0);
        return new Event(start, end, "Gurula", "DummyCourse");
    }

}