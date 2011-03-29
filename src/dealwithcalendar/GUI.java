
package dealwithcalendar;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.io.*;

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

public class GUI extends JFrame
                            implements ActionListener {

     int weekdays = 7;
     int hours = 24;
     int months = 12;
     JButton[][] calendarButtons; // not private because of tests
     int[][] calendarEvents;
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
     private JMenuItem quit = new JMenuItem("Poistu");
        // game mode is a submenu in game menu
     private JMenu courses = new JMenu("Kurssit");
     private JMenuItem sCoursesView = new JMenuItem("Valitut kurssit");
     private JMenuItem aCoursesView = new JMenuItem("Kaikki kurssit");
     private JMenuItem addCourseView = new JMenuItem("Lisää kurssi");

     // weekNumber indicator
     JTextField weekNumber = new JTextField("VIIKKO 11");

     // dummy week for prototype
     String[][] dummyWeek = {{"1", "8", "10", "Ohtu", "CK111", ""},
                             {"2", "10", "12", "Ohtu, laskarit", "B119", "Joel Spolsky: Painless functional \nspecifications osa 1 ja Painless functional \nspecifications osa 2"}};

     // event infos
     private String eventEmpty ="Kurssi:  \t \n" +
                                "Päivämäärä:   \t \n" +
                                "Kello: - \n" +
                                "Paikka: \n" +
                                "Omat merkinnät: \n";

     // for calendar view
     private String[] dayNames = {  "Maanantai",
                                    "Tiistai",
                                    "Keskiviikko",
                                    "Torstai",
                                    "Perjantai",
                                    "Lauantai",
                                    "Sunnuntai"};

     // text area and panel for showing event info details
     private JTextArea eventProperties = new JTextArea(eventEmpty, 30, 30);
     private JPanel eventInfo = new JPanel(new GridLayout(1,2));
     
     // different panels for constructing UI
     JScrollPane calendarScrollPane;
     private JPanel weekScroll = new JPanel(new BorderLayout(1,3));
     private JPanel upperLeftUI = new JPanel(new BorderLayout());
     private JPanel mainLeft = new JPanel(new BorderLayout());
     private JPanel rightUIPanel = new JPanel(new BorderLayout());
     private JPanel menuPanel = new JPanel(new BorderLayout());
     private JPanel mainUI = new JPanel(new FlowLayout());
     private JPanel mainRight = new JPanel(new BorderLayout());
     private JPanel wholeGUI = new JPanel(new BorderLayout());
    
    /**
     * Constructor to create GUI for calendar
     */
    public GUI() {
        //Look and feel
        /*
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
         * 
         */


        UIManager.put("Button.disabledText", Color.WHITE);

        // GENERATE weekNumber marker and calendarGrid for standard week view
        weekNumber.setFont(new Font("sansserif", Font.BOLD, 25));
        weekNumber.setBackground(new Color(100,125,150, 200));
        weekNumber.setHorizontalAlignment(0);
        weekNumber.setEditable(false);
        bPrev.setFont(new Font("sansserif", Font.BOLD, 25));
        bPrev.setBackground(new Color(100,125,150, 200));
        bNext.setFont(new Font("sansserif", Font.BOLD, 25));
        bNext.setBackground(new Color(100,125,150, 200));
        
        calendarWhole = new JPanel(new GridLayout(hours,weekdays));
        calendarButtons = new JButton[hours][weekdays];
        calendarEvents = new int[hours][weekdays];
        calendarScrollPane = new JScrollPane(calendarWhole);
        calendarScrollPane.setPreferredSize(new Dimension(650, 400));

        // create prototype week view
        createWeekView(dummyWeek, 12);

        // make calendar view scrollable (not necessary?)
       
        



        weekScroll.add("West", bPrev);
        weekScroll.add(weekNumber);
        weekScroll.add("East", bNext);
        upperLeftUI.add("North", weekScroll);

        mainLeft.add("North", upperLeftUI);
        mainLeft.add("South", calendarScrollPane);

        eventProperties.setFont(new Font("sansserif", Font.BOLD, 12));
        eventProperties.setBackground(new Color(100,125,150, 100));
        eventProperties.setForeground(new Color(0,0,0));
        eventProperties.setEditable(false);

        eventInfo.add(eventProperties);
      
        rightUIPanel.add("North", eventInfo);

        mainRight.add("North", rightUIPanel);

        mainUI.add(mainLeft);
        mainUI.add(mainRight);

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

            sCoursesView.setBackground(new Color(100,125,150));
            sCoursesView.setForeground(Color.DARK_GRAY);
            sCoursesView.addActionListener(this);
            courses.add(sCoursesView);

            aCoursesView.setBackground(new Color(100,125,150));
            aCoursesView.setForeground(Color.DARK_GRAY);
            aCoursesView.addActionListener(this);
            courses.add(aCoursesView);

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

        if (source == quit) System.exit(0);
        if (source == weekView) createWeekView(dummyWeek, 12);
        if (source == monthView) openSaveFileDialog();
        if (source == sCoursesView) openLoadFileDialog();
        if (source == aCoursesView) createShowAllCoursesView();
        if (source == bPrev) ;
        if (source == bNext) ;
        
        for (byte i = 0; i < hours ; i++) {
            for (byte j = 0; j < weekdays; j++) {
                if (source == calendarButtons[i][j]) {
                    if (calendarEvents[i][j] >= 0)
                        updateEventInfo(dummyWeek[calendarEvents[i][j]]);
                    else { openInfoWindow(); }
                }
            }
        }
    }

    public void createWeekView(String[][] weekEvents, int wNumber) {

        weekNumber.setText("Viikko " +Integer.toString(wNumber));
        int eventDay = 0;
        int eventStart = 0;
        int eventEnd = 0;
        calendarButtons = new JButton[hours][weekdays];
        calendarEvents = new int[hours][weekdays];
        calendarWhole.removeAll();

        for (int i = 0; i < hours; i++) {
            for (int j = 0; j < weekdays; j++) {
               b = new JButton("   ");
               b.setBackground(new Color(100,125,150, 0));
               b.setFont(new Font("sansserif", Font.PLAIN, 10));
               b.setMargin(margins);
               b.setPreferredSize(new Dimension(80,15));
               b.addActionListener(this);

               calendarEvents[i][j] = -1;
               calendarButtons[i][j] = b;
               calendarWhole.add(b);
            }
        }
        for (int i = 0; i < weekEvents.length; i++) {
            eventDay = Integer.valueOf(weekEvents[i][0]);
            eventStart = Integer.valueOf(weekEvents[i][1]);
            eventEnd = Integer.valueOf(weekEvents[i][2]);
            
            for (int j = eventStart; j < eventEnd; j++) {
                calendarButtons[j][eventDay].setText(weekEvents[i][3] + " : " + weekEvents[i][4]);
                setButtonBgBlue(j, eventDay);
                calendarEvents[j][eventDay] = i;
            }
        }
       
        repaint();
    }

    public void createShowAllCoursesView() {
        mainLeft.removeAll();

        repaint();
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
         for (int i = 0; i < hours ; i++) {
           for (int j = 0; j < weekdays; j++) {
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
     * @param XO true = updates X, false = updates O
     */
    public void updateEventInfo(String[] event) {
        eventProperties.setText("Tapahtuma:  \t"+ event[3] +"\n" +
                                "Päivämäärä:   \t" + "\n" +
                                "Kello: "+ event[1] +" - " + event[2] + "\n" +
                                "Paikka: "+ event[4] +" \n" +
                                "Omat merkinnät: " + event[5] + "\n");
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


            
            

        

    

