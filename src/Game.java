//I watched a youtube tutorial on how to create JFrames and use JButtons inside them. https://www.youtube.com/watch?v=RcvABhflOkI

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game {

    JFrame DisplayWindow;
    Container con;
    JPanel TitlePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, secondaryTextPanel;
    JLabel TitleName, HPLabel, HPLabelNumber, WeaponLabel, WeaponLabelName;
                                //font name,        font style   size
    Font TitleFont = new Font("Garamond", Font.PLAIN,50);
    Font normalFont = new Font("Garamond", Font.PLAIN, 30);
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea, secondaryTextArea;

    int playerHealthValue;
    String weapon;

    TitleScreenHandler TSHandler = new TitleScreenHandler();
    WestHandler WestHandler = new WestHandler();
    EastHandler EastHandler = new EastHandler();
    NorthHandler NorthHandler = new NorthHandler();
    SouthHandler SouthHandler = new SouthHandler();


    Random rand = new Random();     //<--this is a random number generator

    //Game Variables
    String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin", "Gremlin", "Dragon"};      //enemies will be picked at random from this array
    int maxEnemyHealth = 75;                                                                    //although enemy health will be picked at random, it must be lower or equal to this value
    int enemyAttackDamage = 25;                                                                 //although enemy damage will be generated at random, it cannot exceed this value
    int distance=10;                                                                            //I plan on having the game stop after ten enemies wiht a "you found the treasure" message

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
        DisplayWindow.setSize(1200,800);
        DisplayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //sets the close operation
        DisplayWindow.getContentPane().setBackground(Color.LIGHT_GRAY);      //sets the background color of the frame
        DisplayWindow.setLayout(null);      //disable the layout for a custom layout
        con = DisplayWindow.getContentPane();

        TitlePanel = new JPanel();
        TitlePanel.setBounds(200,200,800,350);  //x-axis:100,y-axis:100,width and height of "text box"
        TitlePanel.setBackground(Color.LIGHT_GRAY);   //choosing the color for the panel
        TitleName = new JLabel("Dungeons Of Fortuna");  //Label that will display the title of the game
        TitleName.setForeground(Color.BLUE);
        TitleName.setFont(TitleFont);

        startButtonPanel = new JPanel();        //creating a new panel
        startButtonPanel.setBounds(510,650,200,100);
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
        mainTextPanel.setBounds(200,200,800,350);
        mainTextPanel.setBackground(Color.LIGHT_GRAY);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea("");       //text that will be displayed in the panel
        mainTextArea.setBounds(100,100,600,250);    //sets the area for the the panel, see the previous bounds command for explanation
        mainTextArea.setBackground(Color.LIGHT_GRAY);        //text color
        mainTextArea.setForeground(Color.BLUE);        //text color
        mainTextArea.setFont(normalFont);           //sets font
        mainTextArea.setLineWrap(true);     //sets the text to go to the next line if the container is too small
        mainTextPanel.add(mainTextArea);        //adds the text area to the panel

        secondaryTextPanel = new JPanel();
        secondaryTextPanel.setBounds(325,375,600,100);
        secondaryTextPanel.setBackground(Color.LIGHT_GRAY);
        con.add(secondaryTextPanel);

        secondaryTextArea = new JTextArea();
        secondaryTextArea.setBounds(350,350,300,150);
        secondaryTextArea.setBackground(Color.LIGHT_GRAY);        //text color
        secondaryTextArea.setForeground(Color.BLUE);        //text color
        secondaryTextArea.setFont(normalFont);           //sets font
        secondaryTextArea.setLineWrap(true);     //sets the text to go to the next line if the container is too small
        secondaryTextPanel.add(secondaryTextArea);        //adds the text area to the panel

        //this panel is the container for the possible actions the player can take in the game
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(400,550,300,150);
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
        playerPanel.setBounds(300,15,600,50);
        playerPanel.setBackground(Color.LIGHT_GRAY);
        playerPanel.setLayout(new GridLayout(1,4));
        con.add(playerPanel);

        //this will display the "HP:" section of the text
        HPLabel = new JLabel("HP: ");
        HPLabel.setFont(normalFont);
        HPLabel.setForeground(Color.GREEN);     //ideally id like to change this to a darker green.
        playerPanel.add(HPLabel);

        //this will display the variable for health
        HPLabelNumber = new JLabel();
        HPLabelNumber.setFont(normalFont);
        HPLabelNumber.setForeground(Color.GREEN);        //ideally id like to change this to a darker green.
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
                "\narduous task. Proceed at your own risk. \nThere are four possible ways to proceed:");

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
        GAME:       //If using labels is a problem its John Brosnan's fault, I was going to change them but he said to leave them in.
        for (int i=0;i<distance;i++) {

        secondaryTextArea.setText("After proceeding west you encounter a(n) " + "\n" + enemy +
                ". What would you like to do?");



            secondaryTextArea.setText("Your HP is: " + playerHealthValue + "\n "
                    + enemy + "'s HP: " + enemyHealth);

            choice1.setText("Attack the " + enemy);
            choice2.setText("Try and sneak past it");
            choice3.setText("Head back the way you came");
            choice4.setText("Drink a health potion");

            if (choice1.getModel().isPressed()) {
                int damageDealt = rand.nextInt(attackDamage);

                //int damageTaken = rand.nextInt(enemyAttackDamage);

                enemyHealth -= damageDealt;
                //playerHealth -= damageTaken;

                secondaryTextArea.setText("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                secondaryTextArea.setText("\t> You receive " /*+ damageTaken */ + " in retaliation!");

                if (playerHealthValue < 1) {
                    secondaryTextArea.setText("\t> You have taken too much damage and have been slain!");
                    System.exit(0);
                }
            } else if (choice2.getModel().isPressed()) {
                if (numHealthPotions > 0) {
                    playerHealthValue += healthPotionHealNum;
                    numHealthPotions--;
                    mainTextArea.setText("\t> You drink a health potion healing yourself for " + healthPotionHealNum + ". "
                            + "\n\t You now have " + playerHealthValue + " HP."
                            + "\n\t> You have " + numHealthPotions + " health potions left. \n");
                } else {
                    secondaryTextArea.setText("\t You have no health potions left! Defeat enemies to earn more!");
                }
            } else if (choice3.getModel().isPressed()) {
                secondaryTextArea.setText("\t You run away from the " + enemy + "!");
                continue GAME;
            }

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
