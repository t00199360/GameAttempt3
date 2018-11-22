public class Direction {
    static String direction;

    public static void GO(String direction){
        System.out.println("You have reached the direction " + direction);      //debug
                                                     //sets main text

        setDirection(direction);                //calls the setter

        AudioFilePlayer.playAudio("src//Resources//Fight.wav");                                        //referenced from John Brosnan's X:/lab/Structured Programming 2 2018/AudioPlayerStuff
    }

    public static String getDirection() {
        return direction;
    }

    public static void setDirection(String direction) {
        Direction.direction = direction;
    }
}
