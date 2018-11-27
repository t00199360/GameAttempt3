//Liam Dowling T00199360
import javafx.embed.swing.JFXPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;                                  //imports
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;


/**
 * <h1>Dungeons Of Fortuna</h1>
 * <p>
 * The Game.java program is a text based game that the player must fight or sneak past a group of enemies to win
 * <br>
 * This program was written as part of my OOP project in 2nd Year<br>
 * <b>Note:</b> This code may contain methods that do not work as intended.
 *
 * @author Liam Dowling
 * @since 2018-11-24
 * @version 1.3
 */
public class Game {

    /**
     * This method is used to declare global variables and
     * define variables relating to the enemies and player
     */
    JFrame DisplayWindow;                                                                                        //initialising the JFrame
    Container con;                                                                                               //just the container
    JPanel TitlePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, secondaryTextPanel;     //JPanels which define the area for a JLabel
    JLabel TitleName, HPLabel, HPLabelNumber, WeaponLabel, WeaponLabelName;                                        //JLabels which are essentially containers for the text
    //font name,        font style   size
    Font TitleFont = new Font("Garamond", Font.PLAIN, 50);                                           //sets font
    //font name,        font style   size
    Font normalFont = new Font("Garamond", Font.PLAIN, 30);                                          //sets font
    static JButton startButton,leaderBoard, exit;
    static JButton choice1;
    static JButton choice2;
    static JButton choice3;
    static JButton choice4;                                                    //JButton initialisation
    JTextArea mainTextArea, secondaryTextArea;                                                                  //textArea initialisation

    int x= 0;
    String weapon = "Sword";

    //BufferedImage background;

    TitleScreenHandler TSHandler = new TitleScreenHandler();//task handler for the title screen
    leaderboardHandler leaderboardHandler = new leaderboardHandler();
    exitHandler exitHandler = new exitHandler();
    WestHandler WestHandler = new WestHandler();            //task handler for heading west
    EastHandler EastHandler = new EastHandler();            //task handler for heading east
    NorthHandler NorthHandler = new NorthHandler();         //task handler for heading north
    SouthHandler SouthHandler = new SouthHandler();         //task handler for heading south
    attackHandler attackHandler = new attackHandler();      //task handler for attacking an enemy
    drinkPotHandler drinkPotHandler = new drinkPotHandler();//task handler for drinking a health potion
    sneakHandler sneakHandler = new sneakHandler();         //task handler for sneaking past an enemy
    runAwayHandler runAwayHandler = new runAwayHandler();   //task handler for retreating from an enemy


    Random rand = new Random();     //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this

    //Game Variables
    String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin", "Gremlin", "Dragon"};      //enemies will be picked at random from this array
    int maxEnemyHealth = 75;                                                                    //although enemy health will be picked at random, it must be lower or equal to this value
    int enemyAttackDamage = 25;                                                                 //although enemy damage will be generated at random, it cannot exceed this value
    int distance = 0;                                                                            //I plan on having the game stop after ten enemies with a "you found the treasure" message
    int lostDistance = 9;                                                                       //initialises the lost distance variable that gets called when you run away from

    //enemy variables
    String enemy = enemies[rand.nextInt(enemies.length)];       //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this
    int enemyHealth = rand.nextInt(maxEnemyHealth);             //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this

    //Player Variables
    int numHealthPotions = 3;           //number of health potions the player will start out with
    int attackDamage = 30;              //max attack damage the player can inflict
    int healthPotionHealNum = 30;       //amount by which the health potion heals
    int healthPotionDropChance = 25;      //percentage chance of a potion dropped when an enemy is killed
    int playerHealthValue = 100;
    Thread mainThread;

    private Direction DirectionObject = new Direction();

    /**
     * Just the main method to run the game and take in the users details
     * @param args - does nothing
     */
    public static void main(String[] args) {

        createPlayer x = new createPlayer();
        x.openFile();
        JFXPanel fxPanel = new JFXPanel();          //referenced from John Brosnan's X:/lab/Structured Programming 2 2018/AudioPlayerStuff
        new Game();             //calls the game method


    }

    /**
     * Simply declares a lot of the values relating to the frames and threads etc
     */

    public Game()  {

        mainThread = Thread.currentThread();            //initialises the thread
        DisplayWindow = new JFrame();             //initialises the JFrame
        DisplayWindow.setSize(1200, 800);        //sets the size of the JFrame                                   learning of creating JFrames and having buttons in them referenced from https://www.youtube.com/watch?v=RcvABhflOkI
        DisplayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //sets the close operation//                        50% of DisplayWindow is not mine, reference: https://www.youtube.com/watch?v=RcvABhflOkI
        DisplayWindow.setLayout(new FlowLayout());          //sets a flow layout
        DisplayWindow.setResizable(false);

        try {

            BufferedImage image = ImageIO.read(new File("src//Resources//archydungeon.jpg"));            //put all resources in a resource folder
            DisplayWindow.setContentPane(new JLabel(new ImageIcon(image)));                                 //referenced from stackoverflow

        }catch(FileNotFoundException e)
        {
            System.out.println("File does not exist");
            System.exit(0);
        }                                                               //error catching relating to the image
        catch(IOException e)
        {
            System.out.println("IO Exception");
            System.exit(0);
        }

        //DisplayWindow.setLayout(null);        //disable the layout for a custom layout


        con = DisplayWindow.getContentPane();



        TitlePanel = new JPanel();                                              //initialises the JPanel
        TitlePanel.setBounds(200, 200, 800, 350);               //x-axis:100,y-axis:100,width and height of "text box"          This line is also taken from: https://www.youtube.com/watch?v=RcvABhflOkI
        TitlePanel.setBackground(Color.LIGHT_GRAY);                             //choosing the color for the panel
        TitleName = new JLabel("Dungeons Of Fortuna");                     //Label that will display the title of the game
        TitleName.setForeground(Color.BLUE);                                    //sets the color of the text
        TitleName.setFont(TitleFont);                                           //sets the font of the text

        startButtonPanel = new JPanel();                                        //creating a new panel
        startButtonPanel.setBounds(510, 450, 250, 200);             //This line is also taken from: https://www.youtube.com/watch?v=RcvABhflOkI
        startButtonPanel.setBackground(Color.LIGHT_GRAY);                       //setting color of panel

        startButton = new JButton("START");                                 //creates a button
        leaderBoard = new JButton("PLAYER");
        exit = new JButton("EXIT");
        startButton.setBackground(Color.BLUE);                                   //setting color of button                                                    learning of creating JFrames and having buttons in them referenced from https://www.youtube.com/watch?v=RcvABhflOkI
        leaderBoard.setBackground(Color.BLUE);
        exit.setBackground(Color.BLUE);
        startButton.setForeground(Color.LIGHT_GRAY);                             //setting color of button
        leaderBoard.setForeground(Color.LIGHT_GRAY);
        exit.setForeground(Color.LIGHT_GRAY);
        leaderBoard.setFont(normalFont);
        exit.setFont(normalFont);
        startButton.setFont(normalFont);                                         //setting font of button
        leaderBoard.setFocusPainted(false);
        exit.setFocusPainted(false);
        leaderBoard.addActionListener(leaderboardHandler);
        exit.addActionListener(exitHandler);
        startButton.addActionListener(TSHandler);                                //adding an action listener to the button
        startButton.setFocusPainted(false);                                      //hides the focus decoration

        TitlePanel.add(TitleName);                                              //adds the text box to the panel
        startButtonPanel.add(startButton);                                      //adds the button to the panel
        startButtonPanel.add(leaderBoard);
        startButtonPanel.add(exit);
        con.add(TitlePanel);                                                    //adding the panel to the Frame
        con.add(startButtonPanel);                                              //adds the panel to the frame

        DisplayWindow.setVisible(true);                                         //sets the window to visible/appear

    }

    /**
     * This method is what makes the "game" display on the JFrame.<br>
     * It sets panels and labels to be visible.<br>
     * It also assigns the colors and fonts etc to the backgrounds and text.
     */
    private void createGameScreen() {
        TitlePanel.setVisible(false);                                       //hides the title panel
        startButtonPanel.setVisible(false);                                 //hides the start button panel


        mainTextPanel = new JPanel();                                       //creates a new panel
        mainTextPanel.setBounds(200, 200, 800, 350);    //see comment on line 62
        mainTextPanel.setBackground(Color.LIGHT_GRAY);                      //sets the background color of the panel
        con.add(mainTextPanel);                                             //adds the panel to the container

        mainTextArea = new JTextArea("");                                  //text that will be displayed in the panel
        mainTextArea.setBounds(100, 100, 600, 250);    //sets the area for the the panel, see the previous bounds command for explanation
        mainTextArea.setBackground(Color.LIGHT_GRAY);                      //text color
        mainTextArea.setForeground(Color.BLUE);                            //text color
        mainTextArea.setFont(normalFont);                                  //sets font
        mainTextArea.setLineWrap(true);                                    //sets the text to go to the next line if the container is too small
        mainTextPanel.add(mainTextArea);                                   //adds the text area to the panel
        mainTextArea.setEditable(false);

        secondaryTextPanel = new JPanel();                                          //initialises the panel
        secondaryTextPanel.setBounds(325, 375, 600, 100);       //see comment line 62
        secondaryTextPanel.setBackground(Color.LIGHT_GRAY);                         //sets the background color of the panel
        con.add(secondaryTextPanel);                                                //adds the panel to the container
                                                                                    //learning of creating JFrames and having buttons in them referenced from https://www.youtube.com/watch?v=RcvABhflOkI
        secondaryTextArea = new JTextArea();                                        //Defines the JTextArea
        secondaryTextArea.setBounds(250, 350, 500, 200);        //see comment line 62
        secondaryTextArea.setBackground(Color.LIGHT_GRAY);                          //text color
        secondaryTextArea.setForeground(Color.BLUE);                                //text color
        secondaryTextArea.setFont(normalFont);                                      //sets font
        secondaryTextArea.setLineWrap(true);                                        //sets the text to go to the next line if the container is too small
        secondaryTextPanel.add(secondaryTextArea);                                  //adds the text area to the panel
        secondaryTextArea.setEditable(false);



        //this panel is the container for the possible actions the player can take in the game
        choiceButtonPanel = new JPanel();                                           //creates a new JPanel
        choiceButtonPanel.setBounds(400, 550, 300, 150);        //see comment line 62
        choiceButtonPanel.setBackground(Color.LIGHT_GRAY);                          //sets the background color
        choiceButtonPanel.setLayout(new GridLayout(4, 1));               //means one column and four rows of added components
        con.add(choiceButtonPanel);                                                 //adds the panel to the container

        choice1 = new JButton("Option 1");      //creates the button
        choice1.setBackground(Color.LIGHT_GRAY);     //sets the color of the button
        choice1.setForeground(Color.blue);           //sets the color of the button
        choice1.setFont(normalFont);                 //sets the font of the text displayed inside the button
        choiceButtonPanel.add(choice1);              //adds the button to the container
        choice1.setFocusPainted(false);              //hides the focus decoration

        choice2 = new JButton("Option 2");      //creates the button
        choice2.setBackground(Color.LIGHT_GRAY);     //sets the background color
        choice2.setForeground(Color.BLUE);           //sets the text color
        choice2.setFont(normalFont);                 //sets the font
        choiceButtonPanel.add(choice2);              //adds the button to the panel
        choice2.setFocusPainted(false);              //hides the focus decoration


        choice3 = new JButton("Option 3");      //Creates the button
        choice3.setBackground(Color.LIGHT_GRAY);     //sets the background color
        choice3.setForeground(Color.BLUE);           //sets the background color
        choice3.setFont(normalFont);                 //sets the font of the button text
        choiceButtonPanel.add(choice3);              //adds the button to the panel
        choice3.setFocusPainted(false);              //hides the focus decoration


        choice4 = new JButton("Option 4");             //creates the button
        choice4.setBackground(Color.LIGHT_GRAY);            //sets the background color
        choice4.setForeground(Color.BLUE);                  //sets the font color
        choice4.setFont(normalFont);                        //sets the font
        choiceButtonPanel.add(choice4);                     //adds the button to the panel
        choice4.setFocusPainted(false);                     //hides the focus decoration


        //creates and sets the values for the Players stats
        playerPanel = new JPanel();                                         //creates the Jpanel to display the players stats at the top of the screen
        playerPanel.setBounds(300, 50, 600, 50);        //sets the boundaries of the panel
        playerPanel.setBackground(Color.LIGHT_GRAY);                        //sets the background color of the panel
        playerPanel.setLayout(new GridLayout(1, 4));             //sets the layout of the playerpanel
        con.add(playerPanel);                                               //adds the player panel to the container

        //this will display the "HP:" section of the text
        HPLabel = new JLabel("HP: ");                                  //creates the HP label that will be displayed in playerpanel
        HPLabel.setFont(normalFont);                                        //sets the font of the label
        HPLabel.setForeground(Color.decode("#006600"));                     //color.decode was referenced from https://stackoverflow.com/questions/25837449/setbackgroundcolor-with-hex-color-codes-androidstudio
        playerPanel.add(HPLabel);                                           //adds the label to the player panel

        //this will display the variable for health
        HPLabelNumber = new JLabel();                                       //creates a new label that will hold the actual updating health value
        HPLabelNumber.setFont(normalFont);                                  //sets the font of the JLabel
        HPLabelNumber.setForeground(Color.decode("#006600"));               //color.decode was referenced from https://stackoverflow.com/questions/25837449/setbackgroundcolor-with-hex-color-codes-androidstudio
        playerPanel.add(HPLabelNumber);                                     //adds the label to the player panel

        //this displays the "weapon:" text
        WeaponLabel = new JLabel();                                         //creates the JLabel
        WeaponLabel.setFont(normalFont);                                    //sets the font of the Jlabel
        WeaponLabel.setForeground(Color.RED);                               //sets the foreground color to red
        playerPanel.add(WeaponLabel);                                       //adds the weapon label to the panel

        //this will display the name of the weapon you are using
        WeaponLabelName = new JLabel("Weapon: ");                           //creates and sets the text of the JLabel
        WeaponLabelName.setFont(normalFont);                                     //sets the font
        WeaponLabelName.setForeground(Color.RED);                                //sets the foreground color
        playerPanel.add(WeaponLabelName);                                        //adds the weaponlabelname to the panel
        playerPanel.add(WeaponLabel);                                            //adds the weapon label to the panel

        System.out.println("You have reached the end of the game setup P1");                    //debug code
        playerStatsSetup();                                                                     //calls the player stats setup method
        dungeonStart();                                                                        //calls the dungeon start method
    }

    /**
     * This method simply calls the firstDirectionChoice() method
     */
    private void dungeonStart() {


        System.out.println("You have reached the dungeon start P3");        //debug code

        firstDirectionChoice();                                             //calls the first direction choice method
    }

    /**
     * This method removes and adds the relevant eventHandlers to the buttons.
     * @see WestHandler
     * @see attackHandler
     * @see EastHandler
     * @see drinkPotHandler
     * @see NorthHandler
     * @see sneakHandler
     * @see SouthHandler
     * @see runAwayHandler
     */
    protected void newAreaOptions() {
        System.out.println("You've reached the newAreaOptions");            //debug code


        choice1.removeActionListener(WestHandler);                          //each section here removes the previous action listeners and adds the new relevant ones
        choice1.addActionListener(attackHandler);
        choice1.setText("Attack enemy");

        choice2.removeActionListener(EastHandler);                           //each section here removes the previous action listeners and adds the new relevant ones
        choice2.addActionListener(drinkPotHandler);
        choice2.setText("Drink health potion");

        choice3.removeActionListener(NorthHandler);                           //each section here removes the previous action listeners and adds the new relevant ones
        choice3.addActionListener(sneakHandler);
        choice3.setText("Sneak past");


        choice4.removeActionListener(SouthHandler);
        choice4.addActionListener(runAwayHandler);                             //each section here removes the previous action listeners and adds the new relevant ones
        choice4.setText("Run Away");
    }

    /**
     * This method is the basic text for after choosing what direction you want to go<br>
     * The Direction.java passes in the "north", "south", "east" or "west" text.
     */

    private void directionBase() {

        System.out.println("You have reached the direction P5.1");                              //debug code
        //GAME:                                                                                 referenced @ https://stackoverflow.com/questions/19836549/java-label-usage
        String enemy = enemies[rand.nextInt(enemies.length)];
        mainTextArea.setText("After proceeding" + DirectionObject.getDirection() + " you encounter a(n) " + "\n" + enemy +
                ". What would you like to do?");
        secondaryTextArea.setText("Your HP is: " + playerHealthValue + "\n "
                + "enemies HP: " + enemyHealth);
    }

    /**
     * This method is the initial direction choice once the player runs the game.<br>
     * It also adds relevant event handlers to the buttons
     * @see WestHandler
     * @see EastHandler
     * @see NorthHandler
     * @see SouthHandler
     */
    private void firstDirectionChoice() {
        System.out.println("You have reached the first direction P4");          //debug code

        mainTextArea.setText("You are about to enter the fabled Dungeons Of   Fortuna. \nMany men have fallen while attempting this" +
                "\narduous task. Proceed at your own risk. \nThere are four possible ways to proceed:");        //sets main text
        choice1.setText("Head West");
        choice1.addActionListener(WestHandler);
        choice2.setText("Head East");
        choice2.addActionListener(EastHandler);             //adds relevant action listeners
        choice3.setText("Head North");
        choice3.addActionListener(NorthHandler);
        choice4.setText("Head South");
        choice4.addActionListener(SouthHandler);

    }

    /**
     * This method simply presents a JOptionPane that has the "Game Win" text.
     */
    public void setWinText()
    {
        JOptionPane.showMessageDialog(null,"After battling long and hard with skeletons, dragons and the like" +
                "\nyou reach the end of the dungeon."+
                "\nFull of excitement you open the dusty treasure chest"+       //displays the victory message in a JOP as i want it to visually be different so the user doesnt keep clicking without realising
                "\nIt's filled with a wealth of jewels and gold coins."+
                "\nGAME OVER");
    }

    /**
     * This method is the main direction choice method<br>
     * It also adds the relevant event handlers
     * @see attackHandler
     * @see sneakHandler
     * @see runAwayHandler
     * @see drinkPotHandler
     */
    public void directionChoice() {
System.out.println("You have reached DirectionChoice");


        if (distance == 10) {
            setWinText();
            mainTextArea.setText("After battling long and hard with skeletons, dragons and the like\n" +
                    "you reach the end of the dungeon. \n" +
                    "Full of excitement you open the dusty treasure chest \n" +
                    "It's filled with a wealth of jewels and gold coins." +
                    "\nGAME OVER");
            win();
        }
        mainTextArea.setText("Which way do you go now brave fighter?");
        choice1.setText("Head West");
        choice1.removeActionListener(attackHandler);
        choice1.addActionListener(WestHandler);
        choice2.setText("Head East");
        choice2.removeActionListener(drinkPotHandler);
        choice2.addActionListener(EastHandler);             //adds and removes action listeners
        choice3.setText("Head North");
        choice3.removeActionListener(sneakHandler);
        choice3.addActionListener(NorthHandler);
        choice4.setText("Head South");
        choice4.removeActionListener(runAwayHandler);
        choice4.addActionListener(SouthHandler);
        secondaryTextArea.setText(" ");
    }

    /**
     * This method is ran when the objective distance is hit.<br>
     * It hides the unnecessary game panels and runs a timer to automatically close the game after 5 seconds.
     */
    private void win() {
        mainTextPanel.setVisible(false);
        secondaryTextArea.setVisible(false);                    //removes these panels as there is no need for them after the player "wins"
        choiceButtonPanel.setVisible(false);
        playerPanel.setVisible(false);
        MyWinTimerListener m = new MyWinTimerListener();        //timer makes the game wait before quitting
        Timer win = new Timer(5000, m);                   //<-- code supplied by John Brosnan to replace the ineffective thread.Sleep
        win.start();                                            //starts the timer
    }


    /**
     * This method is what runs the attacking section of the game.<br>
     * It takes away health from both the player and the enemy when necessary
     */
    private void hit()
    {
        System.out.println("you are in the hit method");        //debug code
    x=0;                                                        //sets the loop variable
        while (x == 0)                                          //while loop
        {
            System.out.println("You have reached the hit method PAfterAttack");     //debug code
            playerStatsSetup();                                                     //calls the player stats setup method
            int damageDealt = rand.nextInt(attackDamage);           //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this

            int damageTaken = rand.nextInt(enemyAttackDamage);      //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this

            secondaryTextArea.setText("You strike the " + enemy + " for " + damageDealt + " damage. \nYou receive " + damageTaken + " in retaliation!");
            enemyHealth -= damageDealt;                             //updates the text when you get hit and damages the enemy
            x++;

            if (enemyHealth > 0) {                                  //this should make it so if you kill the enemy it cant hit you in retaliation. Dead things should'nt be able to hit you
                playerHealthValue -= damageTaken;                   //adjusts the playerhealthvalue

            }
        if (enemyHealth <= 0) {
            enemyDefeated();                        //if the enemy dies it increments x to exit the loop and calls the enemy defeated method
            x++;
        }
            if (playerHealthValue <= 0) {
                gameOver();                         //if the player dies then the game over method is called
                playerPanel.setVisible(false);      //makes the panels disappear
                choiceButtonPanel.setVisible(false);

            }
            playerStatsSetup();                     //calls the playerstatssetup

        }
    }

    /**
     * This method is called when the enemy's health hits zero. <br>
     * It contains a random generator that should randomly generate which enemy you face. <br>
     * It should also call the next direction pick method<br>
     * It also contains the code to have a random chance to get a health potion from the enemy you kill
     */

    private void enemyDefeated() {
        distance++;                                                             //increments the distance
        System.out.println("You have reached the enemydefeated method P7");     //debug code

        enemyHealth = rand.nextInt(maxEnemyHealth);                             //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this
        JOptionPane.showMessageDialog(null, enemy + " has been defeated!");
        /*String enemy = enemies[rand.nextInt(enemies.length)];*/                   //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this

        directionChoice();

        if (rand.nextInt(40) > healthPotionDropChance) {
            numHealthPotions++;                                                          //code to have a chance at an enemy dropping a health potion
            mainTextArea.setText(" #  The " + enemy + " dropped a health potion!  # ");
            secondaryTextArea.setText(" #  You now have " + numHealthPotions + " health potion(s).  # ");
        }
    }

    /**
     * This method is called when the player selects to sneak past.<br>
     * It increments the distance of the player also.
     */
    private void sneak() {
        System.out.println("You have reached the sneak method P6.2");                   //debug code

        distance++;                                                                     //progresses you past the enemy
        directionChoice();                                                              //calls the direction choice method
    }

    /**
     * This method is called when the player selects to heal <br>
     * It is conditional. The player must have a health potion to actually heal.
     */
    private void heal() {
        System.out.println("You have reached the heal method P6.3");                    //debug code

        if (numHealthPotions > 0) {                                                     //if statement that checks if you have health potions to use
            playerHealthValue += healthPotionHealNum;                                   //heals the player if they drink a health pot
            numHealthPotions--;                                                         //reduces the number of health potions they have
            mainTextArea.setText("You drink a health potion healing yourself for " + healthPotionHealNum + ". "
                    + "\nYou now have " + playerHealthValue + " HP."
                    + "\nYou have " + numHealthPotions + " health potions left. \n");
        } else {
            secondaryTextArea.setText(" You have no health potions left! Defeat enemies to earn more!");
        }
        playerStatsSetup();
    }

    /**
     * This method is called when the player chooses to run away.<br>
     * It contains a random number generator to randomly generate the player distance if they run away
     */
    private void runAway() {
        System.out.println("You have reached the run away method P6.4");            //debug code

        distance = rand.nextInt(lostDistance);                                      //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this
        secondaryTextArea.setText(" You run away from the " + enemy + " in a state of fear! delirious from this you have lost \nyour way!");
        playerStatsSetup();
    }

    /**
     * populates and repopulates the player stats that appear at the top of the screen
     */
    private void playerStatsSetup() {
        System.out.println("You have reached the player stats setup P2");
        WeaponLabel.setText(weapon);
        HPLabelNumber.setText("" + playerHealthValue);
    }

    //game over screen

    /**
     * Displays the gameover text if the players HP depletes to zero
     */
    private void gameOver() {
        System.out.println("You have reached the gameover method P.END");                   //debug code

        MyTimerListener m = new MyTimerListener();                                          //creates the timer
        Timer t = new Timer(5000, m);                                                 //<-- code supplied by John Brosnan to replace the ineffective thread.Sleep
        t.start();                                                                          //starts the timer

        mainTextArea.setText("You limp from the dungeon, exhausted and \ncovered in blood." +
                "\nThe bards will tell stories of your adventures!" +
                "\nJust not very good ones. GAME OVER");
        secondaryTextArea.setVisible(false);                                        //hides the panel as it's no longer needed
    }

    //method to specifically exit the system

    /**
     * This method is used to specifically exit the system
     */
    private void exitSystem() {
        System.out.println("You have reached the exit system method P.END");        //debug code
                                                                                    //this code specifically exits the system. That's it.
        System.exit(0);
    }

    //ActionListener section:

    private class MyWinTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            exitSystem();
        }
    }
    private class MyTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            exitSystem();
        }
    }

    public class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createGameScreen();
        }
    }

    public class exitHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            exitSystem();
        }
    }

    public class leaderboardHandler implements ActionListener {
        public void actionPerformed(ActionEvent event){
            createPlayer.readFile();
        }
    }

    public class WestHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Direction.GO(" West");
            directionBase();
            newAreaOptions();

        }
    }

    public class EastHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Direction.GO(" East");
            directionBase();
            newAreaOptions();
        }
    }

    public class SouthHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Direction.GO(" South");
            directionBase();
            newAreaOptions();

        }
    }

    public class NorthHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Direction.GO(" North");
            directionBase();
            newAreaOptions();

        }
    }

    public class attackHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("you are in the attackHandler");

            if (enemy.equals("Skeleton"))
            {
                AudioFilePlayer.playAudio("src/Resources/SpookyScarySkeletons.wav");              //plays sounds when that kind of enemy is encountered. A lot of these are placeholders and need to be replaced
            }
            if (enemy.equals("Warrior"))
            {
                AudioFilePlayer.playAudio("src/Resources/Warrior.wav");                 //audioFilePlayer is referenced from John Brosnan
            }
            if (enemy.equals("Zombie"))
            {
                AudioFilePlayer.playAudio("src/Resources/Mummy Zombie-SoundBible.com-1966938763.wav");      //referenced from soundbible.com
            }
            if (enemy.equals("Assassin"))
            {
                AudioFilePlayer.playAudio("src/Resources/swordfight.wav");                              //referenced from soundbible.com
            }
            if (enemy.equals("Dragon"))
            {
                AudioFilePlayer.playAudio("src/Resources/dragon.wav");                                  //referenced from soundbible.com
            }
            if (enemy.equals("Gremlin"))
            {
                AudioFilePlayer.playAudio("src/Resources/GremlinNoise.mp3");                            //referenced from soundbible.com
            }
            hit();
        }
    }

    public class drinkPotHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            heal();
        }
    }

    public class sneakHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            sneak();
        }
    }

    public class runAwayHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            runAway();
        }
    }
}