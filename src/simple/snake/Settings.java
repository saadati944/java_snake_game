package simple.snake;

public class Settings
{
    //settings file path
    private static final String settingsPath = "./settings.txt";

    // size of the world
    public static int worldWidth = 80, worldHeight =20;

    //game speed
    public static int frameRate = 30;

    // how many frames should wait after
    // eating a food to appear a new one
    public static int foodDelay = 2;


}
