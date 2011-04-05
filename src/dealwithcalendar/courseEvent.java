/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dealwithcalendar;



import java.io.Serializable;
import java.util.Calendar;

/**
 * Container class for course event templates, from which actual Calendar events
 * are generated. This is still full of fail, I might scrap it, so do not pay much notice.
 * @author koheikki
 */
public class courseEvent implements Serializable{
    public static final int LECTURE = 0;
    public static final int STUDYGROUP = 1;
    public static final int TEST = 3;

    private int weekday;
    private Calendar time;
    private int duration;
    private int type;
    private String location;

    public courseEvent(int _type, Calendar _time, int _weekday, int _duration, String _location){
        this.type = _type;
        this.time = _time;
        this.weekday = _weekday;
        this.duration = _duration;
        this.location = _location;

    }

    public int getType(){
        return type;
    }
    public int getDuration(){
        return duration;
    }
    public int getWeekday(){
        return weekday;
    }
    public Calendar getTime(){
        return time;
    }
    public String getLocation(){
        return location;
    }


}
