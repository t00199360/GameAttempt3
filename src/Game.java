import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    JFrame DisplayWindow;
    Container con;
    JPanel TitlePanel, startButtonPanel, mainTextPanel, choiceButtonPanel;
    JLabel TitleName;           //font name,        font style   size
    Font TitleFont = new Font("Times New Roman", Font.PLAIN,50);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;

    TitleScreenHandler TSHandler = new TitleScreenHandler();

    public static void main(String[] args)
    {
        new Game();
    }

    public Game()
    {
        DisplayWindow=new JFrame();
        DisplayWindow.setSize(800,600);
        DisplayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //sets the close operation
        DisplayWindow.getContentPane().setBackground(Color.black);      //sets the background color of the frame
        DisplayWindow.setLayout(null);      //disable the layout for a custom layout
        con = DisplayWindow.getContentPane();

        TitlePanel = new JPanel();
        TitlePanel.setBounds(100,100,600,150);  //x-axis:100,y-axis:100,width and height of "text box"
        TitlePanel.setBackground(Color.black);   //choosing the color for the panel
        TitleName = new JLabel("Dungeons Of Fortuna");  //Label that will display the title of the game
        TitleName.setForeground(Color.white);
        TitleName.setFont(TitleFont);

        startButtonPanel = new JPanel();        //creating a new panel
        startButtonPanel.setBounds(300,400,200,100);
        startButtonPanel.setBackground(Color.BLACK);        //setting color of panel

        startButton = new JButton("START");     //creates a button
        startButton.setBackground(Color.BLACK);     //setting color of button
        startButton.setForeground(Color.WHITE);     //setting color of button
        startButton.setFont(normalFont);            //setting font of button
        startButton.addActionListener(TSHandler);    //adding an action listener to the button

        TitlePanel.add(TitleName);      //adds the text box to the panel
        startButtonPanel.add(startButton);  //adds teh button to the panel
        con.add(TitlePanel);    //adding the panel to the Frame
        con.add(startButtonPanel);  //adds the panel to the frame
        DisplayWindow.setVisible(true);     //sets the window to visible/appear
    }
    public void createGameScreen()
    {
        TitlePanel.setVisible(false);
        startButtonPanel.setVisible(false);


        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100,100,600,250);
        mainTextPanel.setBackground(Color.BLACK);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea("This is the main text area. This text will not overlap once it runs out of space");
        mainTextArea.setBounds(100,100,600,250);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.white);        //text color
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);     //sets the text to go to the next line if the container is too small
        mainTextPanel.add(mainTextArea);        //adds the text area to the panel

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250,350,300,150);
        choiceButtonPanel.setBackground(Color.BLACK);
        choiceButtonPanel.setLayout(new GridLayout(4,1));   //means one column and four rows of added components
        con.add(choiceButtonPanel);

        choice1 = new JButton("Option 1");
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choiceButtonPanel.add(choice1);

        choice2 = new JButton("Option 2");
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choiceButtonPanel.add(choice2);

        choice3 = new JButton("Option 3");
        choice3.setBackground(Color.BLACK);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choiceButtonPanel.add(choice3);

        choice4 = new JButton("Option 4");
        choice4.setBackground(Color.BLACK);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choiceButtonPanel.add(choice4);
    }

    public class TitleScreenHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            createGameScreen();
        }
    }
}
