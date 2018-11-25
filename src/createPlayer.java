//missed the class where we covered this. learned with help from https://www.youtube.com/watch?v=Bws9aQuAcdg
import javax.swing.*;
import java.io.*;


public class createPlayer {


    public static void openFile(){
        try{
            FileWriter in = new FileWriter("player.txt",true);
            PrintWriter pw = new PrintWriter(in);


            if (JOptionPane.showConfirmDialog(null,"Are you a new player?", "Question",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                String age = JOptionPane.showInputDialog("Please enter your age: ");
                String firstName = JOptionPane.showInputDialog("Please enter your first name: ");
                String lastName = JOptionPane.showInputDialog("Please enter your second name: ");
                pw.println(age);
                pw.println(firstName);
                pw.println(lastName);

                pw.close();
            }


        }catch(IOException e)
        {
            System.out.println("Error");
        }
    }

    public static void readFile()
    {
                try{
                    Runtime.getRuntime().exec("notepad player.txt");
                }
                catch(IOException ex){
                    ex.printStackTrace();
            }
                JOptionPane.showMessageDialog(null,"Player list has been opened!");
    }
}
