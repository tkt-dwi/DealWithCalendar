/*
 * TODO: Courseview, addcourseview, fix weekView and
 * week scrolling add save-method and add-event method
 * 
 *
 */
package dealwithcalendar;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.io.*;
import java.util.*;
import java.text.*;


import java.awt.event.*;
import java.awt.Component.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Font;
import java.awt.FileDialog;
import java.awt.Dimension;
import java.awt.Container;

public class GUI extends JFrame
                            implements ActionListener {

     Main m;
     HashMap<Integer, Course> crs;
     ArrayList<Event> weekEvents;

     private Event curEvent = null;
     private int curYear = Calendar.getInstance().get(Calendar.YEAR);
     private int curWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
     private static final int WEEKDAYS = 7;
     private static final int HOURS = 24;
     private static final int MONTHS = 12;
     JButton[][] calendarButtons; // not private because of tests
     int[][] calendarEvents; // array to store week events
     JPanel calendarWhole; // not private because of tests
     private Insets margins = new Insets(0,0,0,0); // insets for calendarButtons

     // general button for constructing calendarButtons
     private JButton b;

     // buttons for scrolling weeks
     private JButton bPrev = new JButton("<<");
     private JButton bNext = new JButton(">>"); 

     // General menubar
     private JMenuBar menuBar = new JMenuBar();

     // calendar menu and it's items
     private JMenu Menu = new JMenu("Valikko");
     private JMenuItem weekView = new JMenuItem("Viikkonäkymään");
     private JMenuItem monthView = new JMenuItem("Kuukausinäkymään");
     private JMenuItem eventView = new JMenuItem("Lisää tapahtuma");
     private JMenuItem saveWeek = new JMenuItem("Tallenna viikko");
     private JMenuItem quit = new JMenuItem("Poistu");
        // courses is submenu in main menu
     private JMenu courses = new JMenu("Kurssit");
     private JMenuItem CoursesView = new JMenuItem("Selaa kursseja");
     private JMenuItem addCourseView = new JMenuItem("Lisää kurssi");

     // weekNumber indicator
     JTextField weekNumber = new JTextField(curYear + "VIIKKO " + curWeek);
     
     String[] dc; // Course names for pickCourse combobox
     private int[] comboToCourseID; // maps pickCourse combobox's indexes to courseID's;

     // event infos
     private String eventEmpty ="\t \n" +
                                "Päivämäärä:   \t \n" +
                                "Kello: - \n" +
                                "Paikka: \n" +
                                "Omat merkinnät:";

     // 
     private String[] dayNames = {  "Sunnuntai",
                                    "Maanantai",
                                    "Tiistai",
                                    "Keskiviikko",
                                    "Torstai",
                                    "Perjantai",
                                    "Lauantai"};

     private String[] hrs = {   "0:00", "1:00", "2:00", "3:00", "4:00", "5:00",
                                "6:00", "7:00", "8:00", "9:00", "10:00", "11:00",
                                "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
                                "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};

     private String[] crsEvents = {"Luento", "Laskuharjoitus", "Ohjausryhmä", "Muu"};

     private String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
     private String[] monthdays = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                                   "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                                   "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
     private String[] years = new String[2];


     // text area and panel for showing event info details
     private JButton saveEventMarkings = new JButton("Tallenna merkinnät");
     private JTextArea eventProperties = new JTextArea(eventEmpty, 5, 30);
     private JTextArea eventOwnMarkings = new JTextArea("", 7, 30);
     private JPanel eventInfo = new JPanel(new BorderLayout());
     
     // different panels, boxes, etc. for constructing UI

     // week Calendar
     JScrollPane calendarScrollPane;
     private JPanel weekScroll = new JPanel(new BorderLayout(1,3));

     // main UI contianers
     private JPanel upperLeftUI = new JPanel(new BorderLayout());     
     private JPanel mainLeft = new JPanel(new BorderLayout());
     private JPanel rightUIPanel = new JPanel(new BorderLayout());
     private JPanel menuPanel = new JPanel(new BorderLayout());
     private JPanel mainUI = new JPanel(new FlowLayout());
     private JPanel mainRight = new JPanel(new BorderLayout());
     private JPanel wholeGUI = new JPanel(new BorderLayout());

     // courses view
     private JComboBox pickDay = new JComboBox(dayNames);
     private JComboBox pickHour = new JComboBox(hrs);
     private JComboBox pickCourseEvent = new JComboBox(crsEvents);
     private JTextField insertPlace = new JTextField("lisää paikka");
     private JCheckBox insertToCalendar;
     private JComboBox pickTestDay = new JComboBox(monthdays);
     private JComboBox pickTestMonth = new JComboBox(months);
     private JComboBox pickTestYear = new JComboBox(years);
     private JComboBox pickCourse = new JComboBox();
     private JButton inspectCourse = new JButton("Tarkastele kurssia");;
     private JButton addSelectedEvents = new JButton ("Lisää valitut tapahtumat kalenteriin");
     private JPanel courseViewMain = new JPanel(new BorderLayout());
     private JPanel courseViewUpper = new JPanel(new FlowLayout());
     private JPanel courseInfo = new JPanel(new BorderLayout());
     private JTextField courseNickname = new JTextField("");
     private JTextField cn = new JTextField("Anna kurssille nikki: ");
     private JTextField selectedCourseInfo = new JTextField("");
     private JPanel courseEventGrid = new JPanel(new GridLayout(8,1));
     private JPanel courseEvent = new JPanel(new FlowLayout());
     private JPanel courseViewLower = new JPanel(new BorderLayout());
     private JPanel courseNick = new JPanel(new FlowLayout());
     private JComboBox[] courseEventType = new JComboBox[8];
     private JComboBox[] courseEventDays = new JComboBox[6];
     private JComboBox[] courseEventTestDays = new JComboBox[2];
     private JComboBox[] courseEventTestMonths = new JComboBox[2];
     private JComboBox[] courseEventTestYears = new JComboBox[2];
     private JComboBox[] courseEventSTime = new JComboBox[8];
     private JComboBox[] courseEventETime = new JComboBox[8];
     private JTextField[] courseEventLoc = new JTextField[8];
     private JCheckBox[] courseEventSel = new JCheckBox[8];




    
    /**
     * Constructor to create GUI for calendar
     */
    public GUI(Main main) {

        for (int i = 0; i < 2; i++) {
            years[i] = String.valueOf(curYear +i);
        }
        
        m = main;
        crs = m.getCourses();
        mapCourses();

        weekEvents = m.getWeek(curYear, curWeek);
        
        UIManager.put("Button.disabledText", Color.WHITE);

        // CONSTRUCT STANDARD WEEK VIEW

        // construct weekNumber marker and calendarButtons for standard week view
        weekNumber.setFont(new Font("sansserif", Font.BOLD, 25));
        weekNumber.setBackground(new Color(100,125,150, 200));
        weekNumber.setHorizontalAlignment(0);
        weekNumber.setEditable(false);
        bPrev.setFont(new Font("sansserif", Font.BOLD, 25));
        bPrev.setBackground(new Color(100,125,150, 200));
        bPrev.addActionListener(this);
        bNext.setFont(new Font("sansserif", Font.BOLD, 25));
        bNext.setBackground(new Color(100,125,150, 200));
        bNext.addActionListener(this);
        
        calendarWhole = new JPanel(new GridLayout(HOURS,WEEKDAYS));
        calendarButtons = new JButton[HOURS][WEEKDAYS];
        calendarEvents = new int[HOURS][WEEKDAYS];
        // make calendar view scrollable (not necessary?)
        calendarScrollPane = new JScrollPane(calendarWhole);
        calendarScrollPane.setPreferredSize(new Dimension(650, 400));

        weekScroll.add("West", bPrev);
        weekScroll.add(weekNumber);
        weekScroll.add("East", bNext);
        upperLeftUI.add("North", weekScroll);

        // create standard week view, map JButtons and events into arrays
        for (int i = 0; i < HOURS; i++) {
            for (int j = 0; j < WEEKDAYS; j++) {
               b = new JButton("   ");
               b.setBackground(new Color(100,125,150, 0));
               b.setFont(new Font("sansserif", Font.PLAIN, 10));
               b.setMargin(margins);
               b.setPreferredSize(new Dimension(80,15));
               // Testing a fix for button highlighting!
               b.setRolloverEnabled(false);
               b.addActionListener(this);

               calendarEvents[i][j] = -1;
               calendarButtons[i][j] = b;
               calendarWhole.add(b);
            }
        }
        // FIXME: get current week's events in here
        createWeekView(curYear, curWeek);

        // add week view as default view into GUI.mainLeft
        mainLeft.add("North", upperLeftUI);
        mainLeft.add("South", calendarScrollPane);

        // construct container for event info's
        eventProperties.setFont(new Font("sansserif", Font.BOLD, 12));
        eventProperties.setBackground(new Color(100,125,150, 100));
        eventProperties.setForeground(new Color(0,0,0));
        eventProperties.setEditable(false);
        eventProperties.setLineWrap(true);
        eventProperties.setWrapStyleWord(true);

        saveEventMarkings.setFont(new Font("sansserif", Font.BOLD, 12));
        saveEventMarkings.setBackground(new Color(100,125,150));
        saveEventMarkings.setForeground(new Color(0,0,0));
        saveEventMarkings.addActionListener(this);

        eventInfo.add("North", eventProperties);
        eventInfo.add(eventOwnMarkings);
        eventInfo.add("South", saveEventMarkings);

        // construct default view of right side of UI
        rightUIPanel.add("North", eventInfo);
        mainRight.add("North", rightUIPanel);

        mainUI.add(mainLeft);
        mainUI.add(mainRight);

        // CONSTRUCT COURSES VIEW

        // FIXME: get all courses in here map them to some array
        pickCourse = new JComboBox(dc);
        pickCourse.setFont(new Font("sansserif", Font.BOLD, 12));
        pickCourse.setBackground(new Color(100,125,150));
        pickCourse.setForeground(new Color(0,0,0));

        inspectCourse.setBackground(new Color(100,125,150, 0));
        inspectCourse.setFont(new Font("sansserif", Font.PLAIN, 15));
        inspectCourse.setMargin(margins);
        inspectCourse.addActionListener(this);

        // generate general course info into courseinfo JPanel here
        
        selectedCourseInfo.setText("");
        selectedCourseInfo.setEditable(false);
        selectedCourseInfo.setPreferredSize(new Dimension(200, 60));
        selectedCourseInfo.setFont(new Font("sansserif", Font.BOLD, 12));
        selectedCourseInfo.setBackground(new Color(100,125,150));
        selectedCourseInfo.setForeground(new Color(0,0,0));
        
        courseNickname.setText("");
        courseNickname.setEditable(true);
        courseNickname.setPreferredSize(new Dimension(200, 20));
        cn.setFont(new Font("sansserif", Font.BOLD, 12));
        cn.setBackground(new Color(100,125,150, 0));
        cn.setEditable(false);
        courseNick.add(cn);
        courseNick.add(courseNickname);

        courseInfo.add("North", selectedCourseInfo);
        courseInfo.add("South", courseNick);

        // generate course event properties' adding 
        for (int i=0; i < 6; i++) {
            courseEvent = new JPanel(new FlowLayout());
            courseEvent.setFont(new Font("sansserif", Font.BOLD, 12));
            courseEvent.setBackground(new Color(100,125,150, 100));
            courseEvent.setForeground(new Color(0,0,0));

            pickCourseEvent = new JComboBox(crsEvents);
            pickCourseEvent.setPreferredSize(new Dimension(120,20));
            pickCourseEvent.setFont(new Font("sansserif", Font.BOLD, 12));
            pickCourseEvent.setBackground(new Color(100,125,150));
            pickCourseEvent.setForeground(new Color(0,0,0));
            courseEventType[i] = pickCourseEvent;
            courseEvent.add(pickCourseEvent);

            pickDay = new JComboBox(dayNames);
            pickDay.setPreferredSize(new Dimension(110,20));
            pickDay.setFont(new Font("sansserif", Font.BOLD, 12));
            pickDay.setBackground(new Color(100,125,150));
            pickDay.setForeground(new Color(0,0,0));
            pickDay.setSelectedIndex(1);
            courseEventDays[i] = pickDay;
            courseEvent.add(pickDay);

            pickHour = new JComboBox(hrs);
            pickHour.setPreferredSize(new Dimension(70,20));
            pickHour.setFont(new Font("sansserif", Font.BOLD, 12));
            pickHour.setBackground(new Color(100,125,150));
            pickHour.setForeground(new Color(0,0,0));
            pickHour.setSelectedIndex(12);
            courseEventSTime[i] = pickHour;
            courseEvent.add(pickHour);

            pickHour = new JComboBox(hrs);
            pickHour.setPreferredSize(new Dimension(70,20));
            pickHour.setFont(new Font("sansserif", Font.BOLD, 12));
            pickHour.setBackground(new Color(100,125,150));
            pickHour.setForeground(new Color(0,0,0));
            pickHour.setSelectedIndex(12);
            courseEventETime[i] = pickHour;
            courseEvent.add(pickHour);

            insertPlace = new JTextField("lisää paikka");
            insertPlace.setPreferredSize(new Dimension(120,20));
            insertPlace.setFont(new Font("sansserif", Font.PLAIN, 12));
            insertPlace.setForeground(new Color(0,0,0));
            insertPlace.setEditable(true);
            courseEventLoc[i] = insertPlace;
            courseEvent.add(insertPlace);

            insertToCalendar = new JCheckBox("kalenteriin");
            insertToCalendar.setPreferredSize(new Dimension(130,20));
            insertToCalendar.setFont(new Font("sansserif", Font.PLAIN, 12));
            insertToCalendar.setBackground(new Color(100,125,150,0));
            insertToCalendar.setOpaque(true);
            insertToCalendar.setForeground(new Color(0,0,0));
            courseEventSel[i] = insertToCalendar;
            courseEvent.add(insertToCalendar);

            courseEventGrid.add(courseEvent);
        }

        for (int i=0; i < 2; i++) {
            courseEvent = new JPanel(new FlowLayout());
            courseEvent.setFont(new Font("sansserif", Font.BOLD, 12));
            courseEvent.setBackground(new Color(100,125,150, 100));
            courseEvent.setForeground(new Color(0,0,0));

            insertPlace = new JTextField("Tentti");
            insertPlace.setFont(new Font("sansserif", Font.BOLD, 12));
            insertPlace.setBackground(new Color(100,125,150, 0));
            insertPlace.setEditable(false);
            courseEvent.add(insertPlace);

            pickTestDay = new JComboBox(monthdays);
            pickTestDay.setPreferredSize(new Dimension(50,20));
            pickTestDay.setFont(new Font("sansserif", Font.BOLD, 12));
            pickTestDay.setBackground(new Color(100,125,150));
            pickTestDay.setForeground(new Color(0,0,0));
            courseEventTestDays[i] = pickTestDay;
            courseEvent.add(pickTestDay);

            pickTestMonth = new JComboBox(months);
            pickTestMonth.setPreferredSize(new Dimension(50,20));
            pickTestMonth.setFont(new Font("sansserif", Font.BOLD, 12));
            pickTestMonth.setBackground(new Color(100,125,150));
            pickTestMonth.setForeground(new Color(0,0,0));
            courseEventTestMonths[i] = pickTestMonth;
            courseEvent.add(pickTestMonth);

            pickTestYear = new JComboBox(years);
            pickTestYear.setPreferredSize(new Dimension(70,20));
            pickTestYear.setFont(new Font("sansserif", Font.BOLD, 12));
            pickTestYear.setBackground(new Color(100,125,150));
            pickTestYear.setForeground(new Color(0,0,0));
            courseEventTestYears[i] = pickTestYear;
            courseEvent.add(pickTestYear);

            pickHour = new JComboBox(hrs);
            pickHour.setPreferredSize(new Dimension(70,20));
            pickHour.setFont(new Font("sansserif", Font.BOLD, 12));
            pickHour.setBackground(new Color(100,125,150));
            pickHour.setForeground(new Color(0,0,0));
            pickHour.setSelectedIndex(12);
            courseEventSTime[i+6] = pickHour;
            courseEvent.add(pickHour);

            pickHour = new JComboBox(hrs);
            pickHour.setPreferredSize(new Dimension(70,20));
            pickHour.setFont(new Font("sansserif", Font.BOLD, 12));
            pickHour.setBackground(new Color(100,125,150));
            pickHour.setForeground(new Color(0,0,0));
            pickHour.setSelectedIndex(12);
            courseEventETime[i+6] = pickHour;
            courseEvent.add(pickHour);

            insertPlace = new JTextField("lisää paikka");
            insertPlace.setPreferredSize(new Dimension(120,20));
            insertPlace.setFont(new Font("sansserif", Font.PLAIN, 12));
            insertPlace.setBackground(new Color(255,255,255));
            insertPlace.setForeground(new Color(0,0,0));
            insertPlace.setEditable(true);
            courseEventLoc[i+6] = insertPlace;
            courseEvent.add(insertPlace);

            insertToCalendar = new JCheckBox("kalenteriin");
            insertToCalendar.setPreferredSize(new Dimension(125,20));
            insertToCalendar.setFont(new Font("sansserif", Font.PLAIN, 12));
            insertToCalendar.setBackground(new Color(100,125,150,0));
            insertToCalendar.setOpaque(true);
            insertToCalendar.setForeground(new Color(0,0,0));
            courseEventSel[i+6] = insertToCalendar;
            courseEvent.add(insertToCalendar);

            courseEventGrid.add(courseEvent);
        }
      
        addSelectedEvents.setFont(new Font("sansserif", Font.PLAIN, 20));
        addSelectedEvents.setBackground(new Color(100,125,150));
        addSelectedEvents.setOpaque(true);
        addSelectedEvents.setForeground(new Color(0,0,0));
        addSelectedEvents.addActionListener(this);

        courseViewUpper.add("West", pickCourse);
        courseViewUpper.add("East", inspectCourse);
        courseViewLower.add("North", courseInfo);
        courseViewLower.add(courseEventGrid);
        courseViewLower.add("South", addSelectedEvents);
        courseViewMain.add(courseViewLower);
        courseViewMain.add("North", courseViewUpper);
        //courseViewMain.setPreferredSize(new Dimension(650, 400));
        courseViewMain.setBackground(new Color(100,125,150, 0));
        courseViewMain.setFont(new Font("sansserif", Font.PLAIN, 13));

       
        // GENERATE JMENUBAR AND IT'S MENUS        
        
        menuBar.setBackground(new Color(100,125,150, 100));
        menuBar.add(Menu);
        
        // game option menu

        Menu.setFont(new Font("sansserif", Font.BOLD, 13));
        Menu.setMnemonic(KeyEvent.VK_V);
        Menu.setForeground(new Color(100,125,150,250));

        courses.setBackground(new Color(100,125,150));
        courses.setForeground(new Color(100,125,150,255));
        courses.setMnemonic(KeyEvent.VK_K);
        Menu.add(courses);

            // courses submenu

            CoursesView.setBackground(new Color(100,125,150));
            CoursesView.setForeground(Color.DARK_GRAY);
            CoursesView.addActionListener(this);
            courses.add(CoursesView);

            addCourseView.setBackground(new Color(100,125,150));
            addCourseView.setForeground(Color.DARK_GRAY);
            addCourseView.addActionListener(this);
            courses.add(addCourseView);

        weekView.setBackground(new Color(100,125,150));
        weekView.setForeground(Color.DARK_GRAY);
        weekView.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        weekView.addActionListener(this);
        Menu.add(weekView);

        monthView.setBackground(new Color(100,125,150));
        monthView.setForeground(Color.DARK_GRAY);
        monthView.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_K, ActionEvent.CTRL_MASK));
        monthView.addActionListener(this);
        Menu.add(monthView);

        eventView.setBackground(new Color(100,125,150));
        eventView.setForeground(Color.DARK_GRAY);
        eventView.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        eventView.addActionListener(this);
        Menu.add(eventView);

        saveWeek.setBackground(new Color(100,125,150));
        saveWeek.setForeground(Color.DARK_GRAY);
        saveWeek.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveWeek.addActionListener(this);
        Menu.add(saveWeek);

        quit.setBackground(new Color(100,125,150));
        quit.setForeground(Color.DARK_GRAY);
        quit.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        quit.addActionListener(this);
        Menu.add(quit);

        // GENERATE WHOLE GUI

        menuPanel.add("North", menuBar);

        add(wholeGUI);
        wholeGUI.add("North", menuPanel);
        wholeGUI.add("South", mainUI);

        setTitle("OpKuppa");
        setResizable(false);
        //setPreferredSize(new Dimension(900, 500));
        pack();
        this.setLocationRelativeTo(null); //Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try
		{	//set the logo as icon
			this.setIconImage(ImageIO.read(new File("icon.jpg")));
		} catch (IOException ex)
		{	//advanced error handling
			System.err.println("No icon found, deal with it");
		}

        setVisible(true);

    }

   /**
    * Action listener for GUI. Handles all actions.
    *
    * @param act ActionEvent to which reaction is needed
    */ 
   public void actionPerformed(ActionEvent act) {

        Object source = act.getSource();

        // menu and main functions
        if (source == quit) System.exit(0);
        if (source == weekView) createWeekView(curYear, curWeek);
        if (source == monthView) openSaveFileDialog();
        if (source == CoursesView) createCoursesView();
        if (source == addCourseView) ;
        if (source == saveWeek) ;

        // week view
        if (source == bPrev) createWeekView(curYear, curWeek -1) ;
        if (source == bNext) createWeekView(curYear, curWeek +1);
        if (source == saveEventMarkings) saveOwnMarkings();
        
        for (byte i = 0; i < HOURS ; i++) {
            for (byte j = 0; j < WEEKDAYS; j++) {
                if (source == calendarButtons[i][j]) {
                    if (calendarEvents[i][j] >= 0)
                        updateEventInfo(weekEvents.get(calendarEvents[i][j]));
                    else { openInfoWindow(); }
                }
            }
        }

        // course view
        if (source == inspectCourse) updateCourseInformation();
        if (source == addSelectedEvents) addCourseEvents();

    }

    public void saveOwnMarkings() {
        m.changeEventOwnMarkings(curEvent, eventOwnMarkings.getText());


    }

    public void mapCourses() {
        dc = new String[crs.size()];
        comboToCourseID = new int[crs.size()];
        int i = 0;

        Iterator<Course> ci = crs.values().iterator();

        while (ci.hasNext()) {
            Course c = ci.next();
            dc[i] = c.getName();
            comboToCourseID[i] = c.getId();
            i++;
        }
    }

    public void createWeekView(int cy, int cw) {
        if (cw == 0) {
            cw = 53;
            cy--;
        }
        if (cw == 54) {
            cw = 1;
            cy++;
        }
        curYear = cy;
        curWeek = cw;
        weekEvents = m.getWeek(cy,cw);

        mainLeft.removeAll();
        mainRight.removeAll();
        weekNumber.setText(cy + " VIIKKO " + cw);
        int eventDay = 0;
        int eventStart = 0;
        int eventEnd = 0;

        for (int i = 0; i < HOURS; i++) {
            for (int j = 0; j < WEEKDAYS; j++) {
               calendarButtons[i][j].setText("    ");
               calendarButtons[i][j].setBackground(new Color(100,125,150, 0));
               calendarEvents[i][j] = -1;
            }
        }

        if (weekEvents != null) {
            System.out.println(weekEvents.size());

            for (int i = 0; i < weekEvents.size(); i++) {
                Event e = weekEvents.get(i);
                eventDay = e.getStarttime().get(Calendar.DAY_OF_WEEK);
                if (eventDay == 1) eventDay = 6;
                else eventDay = eventDay-2;
                eventStart = e.getStarttime().get(Calendar.HOUR_OF_DAY);
                eventEnd = e.getEndtime().get(Calendar.HOUR_OF_DAY);

                for (int j = eventStart; j < eventEnd; j++) {
                    calendarButtons[j][eventDay].setText(e.getName() + " : " + e.getLocation());
                    setButtonBgBlue(j, eventDay);
                    calendarEvents[j][eventDay] = i;
                }
            }
        }
        upperLeftUI.removeAll();
        upperLeftUI.add("North", weekScroll);
        mainRight.add("North", rightUIPanel);
        mainLeft.add("North", upperLeftUI);
        mainLeft.add("South", calendarScrollPane);
        repaint();
    }

    /* Changes mainLeft container's content to basic course view where one
     * can change course's lecture, studygroup and test times.
     */
    public void createCoursesView() {
        mainRight.removeAll();
        mainLeft.removeAll();
        mainLeft.add(courseViewMain);

        mainRight.validate();
        mainLeft.validate();
        repaint();
    }

    public void setCourseEventGridToDefault() {

        for (int i = 0; i < courseEventType.length-2; i++) {
            ((JComboBox)courseEventType[i]).setSelectedIndex(0);
            ((JComboBox)courseEventDays[i]).setSelectedIndex(0);
            ((JComboBox)courseEventSTime[i]).setSelectedIndex(12);
            ((JComboBox)courseEventETime[i]).setSelectedIndex(12);
            ((JTextField)courseEventLoc[i]).setText("lisää paikka");
            ((JCheckBox)courseEventSel[i]).setSelected(false);
        }
        for (int i = 0; i < 2; i++) {
            ((JComboBox)courseEventTestDays[i]).setSelectedIndex(0);
            ((JComboBox)courseEventTestMonths[i]).setSelectedIndex(0);
            ((JComboBox)courseEventTestYears[i]).setSelectedIndex(0);
            ((JComboBox)courseEventSTime[i]).setSelectedIndex(12);
            ((JComboBox)courseEventETime[i]).setSelectedIndex(12);
            ((JTextField)courseEventLoc[i]).setText("lisää paikka");
            ((JCheckBox)courseEventSel[i]).setSelected(false);
        }
    }

    public void updateCourseInformation() {
        int courseid = comboToCourseID[pickCourse.getSelectedIndex()];
        updateCourseGeneralInfo(courseid);
        updateCourseEvents(courseid);
    }

    public String parseDate(Calendar c) {
        String d = c.get(Calendar.DAY_OF_MONTH) + "." +
                   (c.get(Calendar.MONTH)+1) + "." +
                   c.get(Calendar.YEAR);

        return d;
    }

    public void updateCourseGeneralInfo(int courseid) {
        Course c = m.getACourse(courseid);
        String info = c.getName() + "\n " +
                      parseDate(c.getStart()) + " - " +
                      parseDate(c.getEnd()) + "\n";

        selectedCourseInfo.setText(info);
        selectedCourseInfo.validate();
        courseNickname.setText(c.getNickname());
        courseInfo.validate();
        courseInfo.repaint();
    }

    public void updateCourseEvents(int courseid) {
        // display all course events of selected course
        // in courseEventGrid

        setCourseEventGridToDefault();

        ArrayList<courseEvent> ce = m.getACourse(courseid).getCourseEvents();
        int tn = 0;

        for (int i = 0; i < ce.size(); i++) {
            courseEvent e = ce.get(i);
            if (e.getType() != 3) {
                courseEventType[i].setSelectedIndex(e.getType());
                courseEventDays[i].setSelectedIndex(e.getWeekday());
                courseEventSTime[i].setSelectedIndex(e.getTime().get(Calendar.HOUR_OF_DAY));
                courseEventETime[i].setSelectedIndex(e.getTime().get(Calendar.HOUR_OF_DAY) + (ce.get(i).getDuration()/60));
                courseEventLoc[i].setText(e.getLocation());
                courseEventSel[i].setSelected(true);
            }
            else {
                courseEventTestDays[tn].setSelectedIndex(e.getTime().get(Calendar.DAY_OF_MONTH) -1);
                courseEventTestMonths[tn].setSelectedIndex(e.getTime().get(Calendar.MONTH));
                courseEventTestYears[tn].setSelectedIndex(e.getTime().get(Calendar.YEAR)- Calendar.getInstance().get(Calendar.YEAR));
                courseEventSTime[6+tn].setSelectedIndex(e.getTime().get(Calendar.HOUR_OF_DAY));
                courseEventETime[6+tn].setSelectedIndex(e.getTime().get(Calendar.HOUR_OF_DAY) + (ce.get(i).getDuration()/60));
                courseEventLoc[6+tn].setText(e.getLocation());
                courseEventSel[6+tn].setSelected(true);
                tn++;
            }
        }
        courseEventGrid.validate();
        courseEventGrid.repaint();
    }

    public void addCourseEvents() {
        int courseid = comboToCourseID[pickCourse.getSelectedIndex()];
        if (courseid < 0) return;

        m.deleteCourseEvents(courseid);
        // remove all previous course events from this course
        // remove all events from this course from calendar

        m.getACourse(courseid).setNickname(courseNickname.getText());

        for (int i = 0; i < courseEventType.length-2; i++) {
            
            if (courseEventSel[i].isSelected()) {

                // create course event properties
                int st = courseEventSTime[i].getSelectedIndex();
                int et = courseEventETime[i].getSelectedIndex();
                if (st < et) { // add only those with starttime < endtime
                    int type = courseEventType[i].getSelectedIndex();
                    if (type == 2) type = 1;
                    if (type == 3) type = 4;
                    int d = ((JComboBox)courseEventDays[i]).getSelectedIndex() +1;
                    String loc = courseEventLoc[i].getText();
                    if (loc.equals("lisää paikka")) loc = "";
                    int dur = (et-st)*60;
                    Calendar c = Calendar.getInstance();
                    c.set(0, 0, 0, st, 0, 0);
                    m.addCourseEvent(m.getACourse(courseid), type, c, d, dur, loc);
                }
            }
        }
        for (int i = 0; i < 2; i++) {

            if (courseEventSel[i+6].isSelected()) {

                // create course event properties
                int st = courseEventSTime[i+6].getSelectedIndex();
                int et = courseEventETime[i+6].getSelectedIndex();
                if (st < et) { // add only those with starttime < endtime
                    int td = courseEventTestDays[i].getSelectedIndex() +1;
                    int tm = courseEventTestMonths[i].getSelectedIndex();
                    int ty = Calendar.getInstance().get(Calendar.YEAR) + courseEventTestYears[i].getSelectedIndex();
                    int type = 3;
                    String loc = courseEventLoc[i+6].getText();
                    if (loc.equals("lisää paikka")) loc = "";
                    int dur = (et-st)*60;
                    Calendar c = Calendar.getInstance();
                    c.set(ty, tm, td, st, 0, 0);
                    m.addCourseExam(m.getACourse(courseid), c, loc, dur);
                    System.out.println(td + "." + tm + "." + ty);
                }
            }
        }
        m.createEventsToCalendar(courseid);
        if (m.getCalendar() != null) System.out.println(m.getCalendar().size());
    }

    public void deleteCourseEvents() {
        // courseID

        // deleteCourseEvents(Course id);
    }

    public void updateWeekView(int year, int week) {
        curYear = year;
        curWeek = week;
        
        weekEvents = m.getWeek(year, week);


    
    }


    /**
     * Checks if given slot {x, y} isEnabled
     * in calendarGrid.
     *
     * @param x row of slot to be checked
     * @param y column of slot to be checked
     */
    public boolean isSlotEnabled(int x, int y) {
        return calendarButtons[x][y].isEnabled();
    }
    
    /**
     * Sets given slot {x, y} background color
     * to Color. in calendarGrid. Used for AI testing.
     *
     * @param x row of slot to be altered
     * @param y column of slot to be altered
     */
    public void setButtonBgBlue(int x, int y) {
        calendarButtons[x][y].setBackground(new Color(100,125,150,100));
    }


    /**
     * Disables given slot {x, y} in calendarGrid, sets it's text
     * to 'mover' (ie. X or O) and background color depending on 'mover'.
     *
     * @param mover char to be set as text in button
     * @param x row of slot to be altered
     * @param y column of slot to be altered
     */
    public void setBoardSquareLabel(char mover, int x, int y) {
    /*
        calendarGrid[x][y].setText(String.valueOf(mover));
        calendarGrid[x][y].setEnabled(false);
            if (mover == 'X') {
                calendarGrid[x][y].setBackground(Color.DARK_GRAY);
            }
            else {
                calendarGrid[x][y].setBackground(Color.LIGHT_GRAY);
            }
     *
     */
    }

   /**
     * Used for loading and saving problems and setThreatInformer().
     *
     * @param message String to be set in 'errorMessages' text area.
     */
    public void setErrorMessage(String message) {
      
    }
    
    /**
     * Opens FileDialog for loading a game. Game
     * is unplayable while FileDialog is open.
     */
    public void openLoadFileDialog() {
        FileDialog loadWindow = new FileDialog(this, "Choose a file to load ", FileDialog.LOAD);
        loadWindow.setFile("*.XOS");
        loadWindow.setDirectory("Saves");
        loadWindow.setLocation(getLocationOnScreen());
        loadWindow.setSize(400, 500);
        loadWindow.show();
        String filePath = loadWindow.getDirectory() +
           System.getProperty("file.separator") + loadWindow.getFile();
        /*
        if (loadWindow.getFile() != null) game.loadGame(filePath);
         *
         */
    }

    /**
     * Opens FileDialog for saving the game. Game
     * is unplayable while FileDialog is open.
     */
    public void openSaveFileDialog() {
        FileDialog saveWindow = new FileDialog(this, "Choose a file name to save", FileDialog.SAVE);
        saveWindow.setFile("*.XOS");
        saveWindow.setDirectory("Saves");
        saveWindow.setLocation(getLocationOnScreen());
        saveWindow.setSize(400, 500);
        saveWindow.show();
        String filePath = saveWindow.getDirectory() +
           System.getProperty("file.separator") + saveWindow.getFile();
        /*
        if (saveWindow.getFile() != null) game.saveGame(filePath);
         *
         */
    }

    /**
     * Clears calendarGrid, ie. sets all buttons label to "   ",
     * enables them and sets background color back to Color.WHITE.
     */
    private void clearGUIgrid() {
         for (int i = 0; i < HOURS ; i++) {
           for (int j = 0; j < WEEKDAYS; j++) {
               b = calendarButtons[i][j];
               b.setText("   ");
               b.setEnabled(true);
               b.setBackground(Color.WHITE);
           }
         }
        
    }

  
    public void createStudent() {

        FileDialog saveWindow = new FileDialog(this, "Choose a name for student", FileDialog.SAVE);
        saveWindow.setFile("*.stu");
        saveWindow.setDirectory("Students");
        saveWindow.setSize(400, 500);
        saveWindow.setLocation(getLocationOnScreen());
        saveWindow.show();
        String filePath = saveWindow.getDirectory() +
           System.getProperty("file.separator") + saveWindow.getFile();

        /*
        if (saveWindow.getFile() != null) game.createPlayer(XO, filePath);
         *
         */

    }

    /**
     * Updates playerSummaries in GUI to represent current game's player and
     * that players current statistics.
     *
     */
    public void updateEventInfo(Event e) {
        curEvent = e;

        eventProperties.setText(e.getName() +"\n" +
                                "Päivämäärä: " + parseDate(e.getStarttime()) + "\n" +
                                "Kello: "+ e.getStarttime().get(Calendar.HOUR_OF_DAY) +" - "
                                         + e.getEndtime().get(Calendar.HOUR_OF_DAY) + "\n" +
                                "Paikka: "+ e.getLocation() +" \n" +
                                "Omat merkinnät:");
        eventOwnMarkings.setText(e.getOwnMarkings());
        repaint();
    }

    /**
     * Opens new JFrame for general info about the game,
     * it's developer and version number. Game is playable
     * while JFrame is open.
     */
    public void openInfoWindow() {
        JFrame infoWindow = new JFrame("Lisää uusi tapahtuma");

        String project = "Voit lisätä tapahtuman tästä";
        JTextArea aboutProject = new JTextArea(project);
        aboutProject.setAlignmentX(CENTER_ALIGNMENT);
        aboutProject.setAlignmentY(CENTER_ALIGNMENT);
        aboutProject.setFont(new Font("sansserif", Font.PLAIN, 15));
        aboutProject.setBackground(Color.WHITE);
        aboutProject.setForeground(Color.DARK_GRAY);
        aboutProject.setEditable(false);

        JTextField developer = new JTextField("", 5);
        developer.setFont(new Font("sansserif", Font.BOLD, 12));
        developer.setBackground(Color.WHITE);
        developer.setForeground(Color.DARK_GRAY);
        developer.setEditable(false);

        JTextField version = new JTextField("");
        version.setFont(new Font("sansserif", Font.BOLD, 12));
        version.setBackground(Color.WHITE);
        version.setForeground(Color.DARK_GRAY);
        version.setEditable(false);

        JPanel layout = new JPanel(new BorderLayout());
        JPanel lower = new JPanel(new BorderLayout());

        lower.add("North", developer);
        lower.add("South", version);
        layout.add("North", aboutProject);
        layout.add("South", lower);

        infoWindow.add(layout);
        infoWindow.setPreferredSize(new Dimension (250, 150));
        infoWindow.pack();
        infoWindow.setResizable(false);
        infoWindow.setLocation(getLocationOnScreen());
        infoWindow.show();
        infoWindow.toFront();
        infoWindow.setVisible(true);
    }
    
}


            
            

        

    


