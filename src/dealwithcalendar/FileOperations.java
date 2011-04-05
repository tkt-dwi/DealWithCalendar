package dealwithcalendar;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class FileOperations{


    public void writeCalendar(dwiCalendar calendar) {
        try {
            OutputStream file = new FileOutputStream("mycalendar.cld");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try {
                output.writeObject(calendar);
            } finally {
                output.close();
            }
        } catch (IOException ex) {
            System.out.println("Could not save the calendar and events to file");
        }

    }
}


