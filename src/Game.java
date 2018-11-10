import javax.swing.*;
import java.awt.*;

public class Game {

    JFrame DisplayWindow;
    Container con;
    JPanel TitlePanel, startButtonPanel, mainTextPanel;
    JLabel TitleName;           //font name,        font style   size
    Font TitleFont = new Font("Times New Roman", Font.PLAIN,50);
    Font ButtonFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton startButton;

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
        startButton.setFont(ButtonFont);            //setting font of button

        TitlePanel.add(TitleName);      //adds the text box to the panel
        startButtonPanel.add(startButton);  //adds teh button to the panel
        con.add(TitlePanel);    //adding the panel to the Frame
        con.add(startButtonPanel);  //adds the panel to the frame
        DisplayWindow.setVisible(true);     //sets the window to visible/appear
    }
    public void createGameScreen()
    {

    }
}
