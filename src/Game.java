import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game {

    JFrame DisplayWindow;
    Container con;
    JPanel TitlePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel TitleName, HPLabel, HPLabelNumber, WeaponLabel, WeaponLabelName;           //font name,        font style   size
    Font TitleFont = new Font("Calibri", Font.PLAIN,50);
    Font normalFont = new Font("Calibri", Font.PLAIN, 30);
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;

    int playerHealthValue;
    String weapon;

    TitleScreenHandler TSHandler = new TitleScreenHandler();
    WestHandler WestHandler = new WestHandler();
    EastHandler EastHandler = new EastHandler();
    NorthHandler NorthHandler = new NorthHandler();
    SouthHandler SouthHandler = new SouthHandler();


    Random rand = new Random();     //<--this is a random number generator

    //Game Variables
    String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin", "Gremlin", "Dragon"};
    int maxEnemyHealth = 75;
    int enemyAttackDamage = 25;
    int enemyWave = 1;

    //Player Variables
    int numHealthPotions = 3;
    int attackDamage = 30;
    int healthPotionHealNum = 30;
    int healthPotionDropChance = 25;      //percentage chance of a potion dropped when an enemy is killed


    public static void main(String[] args)
    {
        new Game();
    }

    public Game()
    {
        DisplayWindow=new JFrame();
        DisplayWindow.setSize(800,600);
        DisplayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //sets the close operation
        DisplayWindow.getContentPane().setBackground(Color.LIGHT_GRAY);      //sets the background color of the frame
        DisplayWindow.setLayout(null);      //disable the layout for a custom layout
        con = DisplayWindow.getContentPane();

        TitlePanel = new JPanel();
        TitlePanel.setBounds(100,100,600,150);  //x-axis:100,y-axis:100,width and height of "text box"
        TitlePanel.setBackground(Color.LIGHT_GRAY);   //choosing the color for the panel
        TitleName = new JLabel("Dungeons Of Fortuna");  //Label that will display the title of the game
        TitleName.setForeground(Color.BLUE);
        TitleName.setFont(TitleFont);

        startButtonPanel = new JPanel();        //creating a new panel
        startButtonPanel.setBounds(300,400,200,100);
        startButtonPanel.setBackground(Color.LIGHT_GRAY);        //setting color of panel

        startButton = new JButton("START");     //creates a button
        startButton.setBackground(Color.BLUE);     //setting color of button
        startButton.setForeground(Color.LIGHT_GRAY);     //setting color of button
        startButton.setFont(normalFont);            //setting font of button
        startButton.addActionListener(TSHandler);    //adding an action listener to the button
        startButton.setFocusPainted(false);

        TitlePanel.add(TitleName);      //adds the text box to the panel
        startButtonPanel.add(startButton);  //adds the button to the panel
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
        mainTextPanel.setBackground(Color.LIGHT_GRAY);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea("This is the main text area. This text will not overlap once it runs out of space");       //text that will be displayed in the panel
        mainTextArea.setBounds(100,100,600,250);    //sets the area for the the panel, see the previous bounds command for explanation
        mainTextArea.setBackground(Color.LIGHT_GRAY);        //text color
        mainTextArea.setForeground(Color.BLUE);        //text color
        mainTextArea.setFont(normalFont);           //sets font
        mainTextArea.setLineWrap(true);     //sets the text to go to the next line if the container is too small
        mainTextPanel.add(mainTextArea);        //adds the text area to the panel

        //this panel is the container for the possible actions the player can take in the game
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250,350,300,150);
        choiceButtonPanel.setBackground(Color.LIGHT_GRAY);
        choiceButtonPanel.setLayout(new GridLayout(4,1));   //means one column and four rows of added components
        con.add(choiceButtonPanel);

        choice1 = new JButton("Option 1");      //creates the button
        choice1.setBackground(Color.LIGHT_GRAY);         //sets the color of the button
        choice1.setForeground(Color.blue);         //sets the color of the button
        choice1.setFont(normalFont);                //sets the font of the text displayed inside the button
        choiceButtonPanel.add(choice1);             //adds the button to the container
        choice1.setFocusPainted(false);

        choice2 = new JButton("Option 2");
        choice2.setBackground(Color.LIGHT_GRAY);         //see previous comments for description of the code
        choice2.setForeground(Color.BLUE);
        choice2.setFont(normalFont);
        choiceButtonPanel.add(choice2);
        choice2.setFocusPainted(false);


        choice3 = new JButton("Option 3");
        choice3.setBackground(Color.LIGHT_GRAY);
        choice3.setForeground(Color.BLUE);         //see previous comments for description of the code
        choice3.setFont(normalFont);
        choiceButtonPanel.add(choice3);
        choice3.setFocusPainted(false);


        choice4 = new JButton("Option 4");
        choice4.setBackground(Color.LIGHT_GRAY);         //see previous comments for description of the code
        choice4.setForeground(Color.BLUE);
        choice4.setFont(normalFont);
        choiceButtonPanel.add(choice4);
        choice4.setFocusPainted(false);


        //creates and sets the values for the Players stats
        playerPanel = new JPanel();
        playerPanel.setBounds(100,15,600,50);
        playerPanel.setBackground(Color.LIGHT_GRAY);
        playerPanel.setLayout(new GridLayout(1,4));
        con.add(playerPanel);

        //this will display the "HP:" section of the text
        HPLabel = new JLabel("HP: ");
        HPLabel.setFont(normalFont);
        HPLabel.setForeground(Color.GREEN);
        playerPanel.add(HPLabel);

        //this will display the variable for health
        HPLabelNumber = new JLabel();
        HPLabelNumber.setFont(normalFont);
        HPLabelNumber.setForeground(Color.GREEN);
        playerPanel.add(HPLabelNumber);

        //this displays the "weapon:" text
        WeaponLabel = new JLabel();
        WeaponLabel.setFont(normalFont);
        WeaponLabel.setForeground(Color.RED);
        playerPanel.add(WeaponLabel);

        //this will display the name of the weapon you are using
        WeaponLabelName = new JLabel("Weapon: ");
        WeaponLabelName.setFont(normalFont);
        WeaponLabelName.setForeground(Color.RED);
        playerPanel.add(WeaponLabelName);
        playerPanel.add(WeaponLabel);

        playerStatsSetup();
    }

    private void playerStatsSetup() {
        playerHealthValue = 100;
        weapon = "Fists";
        WeaponLabel.setText(weapon);
        HPLabelNumber.setText("" + playerHealthValue);

        dungeonStart();
    }

    public void dungeonStart(){
        mainTextArea.setText("You are about to enter the fabled Dungeons Of   Fortuna. \nMany men have fallen while attempting this" +
                "        arduous task. Proceed at your own risk. \nThere are four possible ways to proceed:");

        choice1.setText("Head West");
        choice1.addActionListener(WestHandler);
        choice2.setText("Head East");
        choice2.addActionListener(EastHandler);
        choice3.setText("Head North");
        choice3.addActionListener(NorthHandler);
        choice4.setText("South");
        choice4.addActionListener(SouthHandler);

    }

    public void West(){

        int enemyHealth = rand.nextInt(maxEnemyHealth);
        String enemy = enemies[rand.nextInt(enemies.length)];
        GAME:           //<-- idea for using labels to loop combat came from codecourse.com     <--label for while loop

        mainTextArea.setText("After proceeding west you encounter a(n) " + "\n" + enemy +
                ". What would you like to do?");

        choice1.setText("Attack the " + enemy);
        choice2.setText("Try and sneak past it");
        choice3.setText("Head back the way you came");
        choice4.setText("Drink a health potion");

        if (choice1.getModel().isPressed()) {
            int damageDealt = rand.nextInt(attackDamage);

            //int damageTaken = rand.nextInt(enemyAttackDamage);

            enemyHealth -= damageDealt;
            //playerHealth -= damageTaken;

            JOptionPane.showMessageDialog(null, "\t> You strike the " + enemy + " for " + damageDealt + " damage.");
            JOptionPane.showMessageDialog(null, "\t> You receive " /*+ damageTaken */+ " in retaliation!");

            if (playerHealthValue < 1) {
                JOptionPane.showMessageDialog(null, "\t> You have taken too much damage and have been slain!");
                System.exit(0);
            }
        } else if (choice2.getModel().isPressed()) {
            if (numHealthPotions > 0) {
                playerHealthValue += healthPotionHealNum;
                numHealthPotions--;
                JOptionPane.showMessageDialog(null, "\t> You drink a health potion healing yourself for " + healthPotionHealNum + ". "
                        + "\n\t You now have " + playerHealthValue + " HP."
                        + "\n\t> You have " + numHealthPotions + " health potions left. \n");
            } else {
                JOptionPane.showMessageDialog(null, "\t You have no health potions left! Defeat enemies to earn more!");
            }
        } else if (choice3.getModel().isPressed()) {
            JOptionPane.showMessageDialog(null, "\t You run away from the " + enemy + "!");
            //continue GAME;
        }


    }

    public class TitleScreenHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            createGameScreen();
        }
    }
    public class WestHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            West();
        }
    }
    public class EastHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            West();
        }
    }
    public class SouthHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            West();
        }
    }
    public class NorthHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            West();
        }
    }
}
