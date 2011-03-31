/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dealwithcalendar;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Container class for course event templates, from which actual Calendar events
 * are generated.
 * @author koheikki
 */
public class courseEvent{
    public static final int LECTURE = 0;
    public static final int STUDYGROUP = 1;
    public static final int TEST = 1;
    private ArrayList<Calendar> starttimes;
    private ArrayList<Calendar> endtimes;
    private int type;
    private String location;
}
