//I watched a youtube tutorial on how to create JFrames and use JButtons inside them. https://www.youtube.com/watch?v=RcvABhflOkI
//Liam Dowling T00199360
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game {

    JFrame DisplayWindow;
    Container con;
    JPanel TitlePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, secondaryTextPanel;     //JPanels which define the area for a JLabel
    JLabel TitleName, HPLabel, HPLabelNumber, WeaponLabel, WeaponLabelName;                                        //JLabels which are essentially containers for the text
                                //font name,        font style   size
    Font TitleFont = new Font("Garamond", Font.PLAIN,50);
                                //font name,        font style   size
    Font normalFont = new Font("Garamond", Font.PLAIN, 30);
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea, secondaryTextArea;


    String weapon="fists";

    TitleScreenHandler TSHandler = new TitleScreenHandler();//task handler for the title screen
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
    int distance=10;                                                                            //I plan on having the game stop after ten enemies with a "you found the treasure" message
    int lostDistance=9;

    //enemy variable
    int enemyHealth = rand.nextInt(maxEnemyHealth);             //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this
    String enemy = enemies[rand.nextInt(enemies.length)];       //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this


    //Player Variables
    int numHealthPotions = 3;           //number of health potions the player will start out with
    int attackDamage = 30;              //max attack damage the player can inflict
    int healthPotionHealNum = 30;       //amount by which the health potion heals
    int healthPotionDropChance = 25;      //percentage chance of a potion dropped when an enemy is killed
    int playerHealthValue = 100;


    public static void main(String[] args)
    {
        new Game();
    }       //calls the game method

    public Game()
    {
        DisplayWindow=new JFrame();             //initialises the JFrame
        DisplayWindow.setSize(1200,800);        //sets the size of the JFrame                                   learning of creating JFrames and having buttons in them referenced from https://www.youtube.com/watch?v=RcvABhflOkI
        DisplayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //sets the close operation
        DisplayWindow.getContentPane().setBackground(Color.LIGHT_GRAY);      //sets the background color of the frame
        DisplayWindow.setLayout(null);      //disable the layout for a custom layout
        con = DisplayWindow.getContentPane();

        TitlePanel = new JPanel();              //initialises the JPanel
        TitlePanel.setBounds(200,200,800,350);  //x-axis:100,y-axis:100,width and height of "text box"
        TitlePanel.setBackground(Color.LIGHT_GRAY);   //choosing the color for the panel
        TitleName = new JLabel("Dungeons Of Fortuna");  //Label that will display the title of the game
        TitleName.setForeground(Color.BLUE);            //sets the color of the text
        TitleName.setFont(TitleFont);                   //sets the font of the text

        startButtonPanel = new JPanel();        //creating a new panel
        startButtonPanel.setBounds(510,650,200,100);        //see line 62's comment
        startButtonPanel.setBackground(Color.LIGHT_GRAY);        //setting color of panel

        startButton = new JButton("START");     //creates a button
        startButton.setBackground(Color.BLUE);     //setting color of button                                                    learning of creating JFrames and having buttons in them referenced from https://www.youtube.com/watch?v=RcvABhflOkI
        startButton.setForeground(Color.LIGHT_GRAY);     //setting color of button
        startButton.setFont(normalFont);            //setting font of button
        startButton.addActionListener(TSHandler);    //adding an action listener to the button
        startButton.setFocusPainted(false);     //hides the focus decoration

        TitlePanel.add(TitleName);      //adds the text box to the panel
        startButtonPanel.add(startButton);  //adds the button to the panel
        con.add(TitlePanel);    //adding the panel to the Frame
        con.add(startButtonPanel);  //adds the panel to the frame
        DisplayWindow.setVisible(true);     //sets the window to visible/appear
    }
    public void createGameScreen()
    {
        TitlePanel.setVisible(false);               //hides the title panel
        startButtonPanel.setVisible(false);         //hides the start button panel


        mainTextPanel = new JPanel();               //creates a new panel
        mainTextPanel.setBounds(200,200,800,350);       //see comment on line 62
        mainTextPanel.setBackground(Color.LIGHT_GRAY);      //sets the background color of the panel
        con.add(mainTextPanel);             //adds the panel to the container

        mainTextArea = new JTextArea("");       //text that will be displayed in the panel
        mainTextArea.setBounds(100,100,600,250);    //sets the area for the the panel, see the previous bounds command for explanation
        mainTextArea.setBackground(Color.LIGHT_GRAY);        //text color
        mainTextArea.setForeground(Color.BLUE);        //text color
        mainTextArea.setFont(normalFont);           //sets font
        mainTextArea.setLineWrap(true);     //sets the text to go to the next line if the container is too small
        mainTextPanel.add(mainTextArea);        //adds the text area to the panel

        secondaryTextPanel = new JPanel();          //initialises the panel
        secondaryTextPanel.setBounds(325,375,600,100);      //see comment line 62
        secondaryTextPanel.setBackground(Color.LIGHT_GRAY);         //sets the background color of the panel
        con.add(secondaryTextPanel);        //adds the panel to the container
                                                                                                            //learning of creating JFrames and having buttons in them referenced from https://www.youtube.com/watch?v=RcvABhflOkI
        secondaryTextArea = new JTextArea();            //Defines the JTextArea
        secondaryTextArea.setBounds(350,350,300,150);       //see comment line 62
        secondaryTextArea.setBackground(Color.LIGHT_GRAY);        //text color
        secondaryTextArea.setForeground(Color.BLUE);        //text color
        secondaryTextArea.setFont(normalFont);           //sets font
        secondaryTextArea.setLineWrap(true);     //sets the text to go to the next line if the container is too small
        secondaryTextPanel.add(secondaryTextArea);        //adds the text area to the panel

        //this panel is the container for the possible actions the player can take in the game
        choiceButtonPanel = new JPanel();       //creates a new JPanel
        choiceButtonPanel.setBounds(400,550,300,150);       //see comment line 62
        choiceButtonPanel.setBackground(Color.LIGHT_GRAY);          //sets the background color
        choiceButtonPanel.setLayout(new GridLayout(4,1));   //means one column and four rows of added components
        con.add(choiceButtonPanel);         //adds the panel to the container

        choice1 = new JButton("Option 1");      //creates the button
        choice1.setBackground(Color.LIGHT_GRAY);         //sets the color of the button
        choice1.setForeground(Color.blue);         //sets the color of the button
        choice1.setFont(normalFont);                //sets the font of the text displayed inside the button
        choiceButtonPanel.add(choice1);             //adds the button to the container
        choice1.setFocusPainted(false);             //hides the focus decoration

        choice2 = new JButton("Option 2");      //creates the button
        choice2.setBackground(Color.LIGHT_GRAY);         //sets the background color
        choice2.setForeground(Color.BLUE);          //sets the text color
        choice2.setFont(normalFont);                //sets the font
        choiceButtonPanel.add(choice2);             //adds the button to the panel
        choice2.setFocusPainted(false);             //hides the focus decoration


        choice3 = new JButton("Option 3");      //Creates the button
        choice3.setBackground(Color.LIGHT_GRAY);       //sets the background color
        choice3.setForeground(Color.BLUE);         //sets the background color
        choice3.setFont(normalFont);                //sets the font of the button text
        choiceButtonPanel.add(choice3);             //adds the button to the panel
        choice3.setFocusPainted(false);             //hides the focus decoration


        choice4 = new JButton("Option 4");      //creates the button
        choice4.setBackground(Color.LIGHT_GRAY);         //sets the background color
        choice4.setForeground(Color.BLUE);                  //sets the font color
        choice4.setFont(normalFont);                        //sets the font
        choiceButtonPanel.add(choice4);                     //adds the button to the panel
        choice4.setFocusPainted(false);                         //hides the focus decoration


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
        dungeonStart();
    }



    public void dungeonStart(){

        firstDirectionChoice();
       /* try {
            Thread.sleep(5000);     //trying to make the code wait before it overwrites the mainTextAreas content
        }catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }*/
    }

    public void West(){
        //GAME:       referenced @ https://stackoverflow.com/questions/19836549/java-label-usage


        mainTextArea.setText("After proceeding west you encounter a(n) " + "\n" + enemy +
                ". What would you like to do?");

            secondaryTextArea.setText("Your HP is: " + playerHealthValue + "\n "
                    + enemy + "'s HP: " + enemyHealth);

            newAreaOptions();           //presents the new button text and handlers are removed and new ones are added to the buttons

            if (enemyHealth<=0)
            {
                directionChoice();
            }

    }

    private void newAreaOptions() {
        choice1.removeActionListener(WestHandler);
        choice1.addActionListener(attackHandler);
        choice1.setText("Attack the enemy");

        choice2.removeActionListener(EastHandler);
        choice2.addActionListener(drinkPotHandler);
        choice2.setText("Drink a health potion");

        choice3.removeActionListener(NorthHandler);
        choice3.addActionListener(sneakHandler);
        choice3.setText("Try and sneak past it");


        choice4.removeActionListener(SouthHandler);
        choice4.addActionListener(runAwayHandler);
        choice4.setText("Head back the way you came");

        if (choice1.getModel().isPressed()) {
            attack();
        } else if (choice2.getModel().isPressed()) {
            heal();
        } else if (choice3.getModel().isPressed()) {
            runAway();
            //continue GAME;   <-- referenced @ https://stackoverflow.com/questions/19836549/java-label-usage
        }
    }

    public void East(){ //GAME:       referenced @ https://stackoverflow.com/questions/19836549/java-label-usage


            mainTextArea.setText("After proceeding East you encounter a(n) " + "\n" + enemy +
                    ". What would you like to do?");

            secondaryTextArea.setText("Your HP is: " + playerHealthValue + "\n "
                    + enemy + "'s HP: " + enemyHealth);

            newAreaOptions();           //presents the new button text and handlers are removed and new ones are added to the buttons

            if (enemyHealth<=0)
            {
                directionChoice();
            }

    }

    public void North(){
        int enemyHealth = rand.nextInt(maxEnemyHealth);             //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this
        String enemy = enemies[rand.nextInt(enemies.length)];       //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this
        //GAME:       referenced @ https://stackoverflow.com/questions/19836549/java-label-usage


            mainTextArea.setText("After proceeding North you encounter a(n) " + "\n" + enemy +
                    ". What would you like to do?");

                secondaryTextArea.setText("Your HP is: " + playerHealthValue + "\n "
                        + enemy + "'s HP: " + enemyHealth);

            newAreaOptions();

            if (enemyHealth<=0)
            {
                directionChoice();
            }
    }

    public void South(){
        int enemyHealth = rand.nextInt(maxEnemyHealth);             //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this
        String enemy = enemies[rand.nextInt(enemies.length)];       //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this
        //GAME:       referenced @ https://stackoverflow.com/questions/19836549/java-label-usage


            mainTextArea.setText("After proceeding South you encounter a(n) " + "\n" + enemy +
                    ". What would you like to do?");

            secondaryTextArea.setText("Your HP is: " + playerHealthValue + "\n "
                    + enemy + "'s HP: " + enemyHealth);

            newAreaOptions();

            if (enemyHealth<=0)
            {
                directionChoice();
            }

    }

    private void firstDirectionChoice()
    {
        mainTextArea.setText("You are about to enter the fabled Dungeons Of   Fortuna. \nMany men have fallen while attempting this" +
                "\narduous task. Proceed at your own risk. \nThere are four possible ways to proceed:");
        choice1.setText("Head West");
        choice1.addActionListener(WestHandler);
        choice2.setText("Head East");
        choice2.addActionListener(EastHandler);             //put this in a victory method
        choice3.setText("Head North");
        choice3.addActionListener(NorthHandler);
        choice4.setText("South");
        choice4.addActionListener(SouthHandler);
    }
    private void directionChoice() {

        distance++;
        mainTextArea.setText("Which way do you go now brave fighter?");
        choice1.setText("Head West");
        choice1.addActionListener(WestHandler);
        choice2.setText("Head East");
        choice2.addActionListener(EastHandler);             //put this in a victory method
        choice3.setText("Head North");
        choice3.addActionListener(NorthHandler);
        choice4.setText("South");
        choice4.addActionListener(SouthHandler);
        /*try {
            Thread.sleep(5000);
        }catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }*/
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
            East();
        }
    }
    public class SouthHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            South();
        }
    }
    public class NorthHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            North();
        }
    }
    public class attackHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {attack();}
    }
    public class drinkPotHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {heal();}
    }
    public class sneakHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {sneak();}
    }
    public class runAwayHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event) {
            runAway();}
    }
    private void attack() {
        int damageDealt = rand.nextInt(attackDamage);           //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this

        int damageTaken = rand.nextInt(enemyAttackDamage);      //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this

        enemyHealth -= damageDealt;
        playerHealthValue -=  damageTaken;

            //THIS IS LOOPING until you die

        secondaryTextArea.setText("You strike the " + enemy + " for " + damageDealt + " damage.");
        secondaryTextArea.setText("You receive " + damageTaken  + " in retaliation!");
        playerStatsSetup();
        if (playerHealthValue<=0)
        {
            gameOver();
        }

    }
    private void sneak() {
        distance++;
        directionChoice();
    }
    private void heal() {
        if (numHealthPotions > 0) {
            playerHealthValue += healthPotionHealNum;
            numHealthPotions--;
            mainTextArea.setText("You drink a health potion healing yourself for " + healthPotionHealNum + ". "
                    + "\nYou now have " + playerHealthValue + " HP."
                    + "\nYou have " + numHealthPotions + " health potions left. \n");
        } else {
            secondaryTextArea.setText(" You have no health potions left! Defeat enemies to earn more!");
        }
        playerStatsSetup();
    }
    private void runAway() {
        distance=rand.nextInt(lostDistance);            //<--this is a random number generator referenced from https://codereview.stackexchange.com/questions/164540/simple-text-based-rpg-in-java  the dice roll in this example is what i used from this
        secondaryTextArea.setText(" You run away from the " + enemy + " in a state of fear! \n delirious from this you have lost your way!");
        playerStatsSetup();
    }
    private void playerStatsSetup() {
        WeaponLabel.setText(weapon);
        HPLabelNumber.setText("" + playerHealthValue);
    }

    private void gameOver() {

        mainTextArea.setText("You limp from the dungeon, exhausted and covered in blood." +
                "\nThe bards will tell stories of your adventures!" +
                "\nJust not very good ones. GAME OVER");
        exitSystem();
    }
    private void exitSystem()
    {
        System.exit(0);
    }
}