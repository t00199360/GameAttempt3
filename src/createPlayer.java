//missed the class where we covered this. learned with help from https://www.youtube.com/watch?v=Bws9aQuAcdg
import java.util.*;

public class createPlayer {
    private Formatter x;

    public void openFile(){
        try{
            x = new Formatter("playerName.txt");
        }
        catch (Exception e)
        {
            System.out.println("You have an error");
        }
    }

    public void addRecords(){
        x.format("%s%s","Liam","Yeet");
    }
public void closeFile(){
        x.close();
}
}
