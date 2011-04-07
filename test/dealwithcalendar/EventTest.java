/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dealwithcalendar;

import java.util.Calendar;
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
public class EventTest {

    Event testEvent = createDummyEvent(2012, 2, 3, "nowhere" , "test", 100);

    public EventTest() {
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

    private Event createDummyEvent(int year, int startday, int endday, String location, String name, int id ){
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(year, 0, startday, 0, 0, 0);
        start.set(Calendar.MILLISECOND, 0);
        end.set(year, 0, endday, 0, 0, 0);
        end.set(Calendar.MILLISECOND, 0);
        return new Event(start, end, location, name, id);
    }
    /**
     * Test of setType method, of class Event.
     */
//    @Test
//    public void testSetType() {
//
//        Event first = createDummyEvent(2011, 1, 2, "a" , "one", 10);
//        Event second = createDummyEvent(2011, 3, 4, "b", "two", 20);
//        Event third = createDummyEvent(2011, 5, 6, "c", "three", 30);
//        Event fourth = createDummyEvent(2011, 7, 8, "d", "four", 40);
//        Event fifth = createDummyEvent(2011, 9, 10, "e", "five", 50);
//
//
//        System.out.println("setType");
//        int t = 0;
//        Event instance = null;
//        instance.setType(t);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getType method, of class Event.
     */
    @Test
    public void testGetType() {
        Event instance = testEvent;
        int expResult = 4;
        int result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOwnMarkings method and getOwnMarkings method, of class Event.
     */
    @Test
    public void testSetNGetOwnMarkings() {
        String om = "testmarking";
        Event instance = testEvent;
        instance.setOwnMarkings(om);
        String expResult = om;
        String result = instance.getOwnMarkings();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocation method and setLocation method, of class Event.
     */
    @Test
    public void testSetNGetLocation() {
        String location = "testland";
        Event instance = testEvent;
        instance.setLocation(location);
        String expResult = location;
        String result = instance.getLocation();
        assertEquals(expResult, result);
    }


    /**
     * Test of setStarttime and getStarttime methods, of class Event.
     */
    @Test
    public void testSetNGetStarttime() {
        Calendar starttime = Calendar.getInstance();
        Event instance = testEvent;
        instance.setStarttime(starttime);
        Calendar expResult = starttime;
        Calendar result = instance.getStarttime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndtime and getEndtime methods, of class Event.
     */
    @Test
    public void testSetNGetEndtime() {
        Calendar endtime = Calendar.getInstance();
        Event instance = testEvent;
        instance.setEndtime(endtime);
        Calendar expResult = endtime;
        Calendar result = instance.getEndtime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAttending and isAttending methods, of class Event.
     */
    @Test
    public void testIsAttending() {
        boolean attending = false;
        Event instance = testEvent;
        instance.setAttending(attending);
        boolean expResult = false;
        boolean result = instance.isAttending();
        assertEquals(expResult, result);
    }


    /**
     * Test of setName and getName methods, of class Event.
     */
    @Test
    public void testSetName() {
        String n = "easteregg!";
        Event instance = testEvent;
        instance.setName(n);
        String expResult = n;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCourseID and getCourseID methods, of class Event.
     */
    @Test
    public void testGetCourseID() {
        int courseID = 665;
        Event instance = testEvent;
        instance.setCourseID(courseID);
        int expResult = courseID;
        int result = instance.getCourseID();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Event.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Event o = testEvent;
        Event instance = testEvent;
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Event.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Event e = testEvent;
        Event instance = testEvent;
        boolean expResult = true;
        boolean result = instance.equals(e);
        assertEquals(expResult, result);
    }

}