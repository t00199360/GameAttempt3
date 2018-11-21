public class SavePlayer {
    public static void main(String[] args) {
        createPlayer g = new createPlayer();
        g.openFile();           //opens file
        g.addRecords();         //uses the addRecords method
        g.closeFile();          //closes the file to prevent memory wastage
    }
}
