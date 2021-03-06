package simple.snake;

import java.io.File;
import java.util.Scanner;

public class Settings
{
    //settings file path
    private static final String settingsPath = "./settings.txt";


    // size of the world
    public static int worldWidth = 60;
    public static int worldHeight= 19;

    // game speed
    public static int frameRate = 5;

    // how many frames should wait after
    // eating a food to appear a new one
    public static int foodDelay = 2;

    // character to show snake head
    public static char snakeHead = '●';

    // character to show snake body
    public static char[] snakeBody = new char[]{'o', 'O'};

    // character to show empty space
    public static char emptyChar = ' ';

    // snake minimum length
    public static int snakeMinLength = 10;

    // character to show borders
    public static char bordeChar = '#';

    // character to show food
    public static char foodChar = 'X';

    // snake start point
    public static Point snakeStartPoint = new Point(1, 1, snakeHead);

    // snake start direction
    public static Controller.snakeDirection snakeStartDirection = Controller.snakeDirection.Paused;


    // don't touch these ↓↓↓
    public static int frameDelay = 200;
    public static boolean isLinux = false;

    /**
     * read settings from settings file if exists
     */
    public static void readFromFile()
    {
        isLinux = System.getProperty("os.name").equals("Linux");

        File settingsFile;
        Scanner scanner;

        frameDelay = (int)(1000.0 / frameRate);

        try
        {
            settingsFile = new File(settingsPath);
            scanner = new Scanner(settingsFile);
        }
        catch(Exception e)
        {
            return;
        }
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            if(line.startsWith("#") || line.isBlank())
                continue;
            else if (line.startsWith("width "))
                try
                {
                    worldWidth = Integer.parseInt(line.substring(6).strip());
                }catch (Exception e){continue;}
            else if (line.startsWith("height "))
                try
                {
                    worldHeight = Integer.parseInt(line.substring(7).strip());
                }catch (Exception e){continue;}
            else if (line.startsWith("fps "))
                try
                {
                    frameRate = Integer.parseInt(line.substring(4).strip());
                }catch (Exception e){continue;}
            else if (line.startsWith("delay "))
                try
                {
                    foodDelay = Integer.parseInt(line.substring(6).strip());
                }catch (Exception e){continue;}
            else if (line.startsWith("head ") && line.length()==6)
                try
                {
                    snakeHead = line.charAt(5);
                }catch (Exception e){continue;}
            //todo : complete this part
//            else if (line.startsWith("body ") && line.length()==6)
//                try
//                {
//                    snakeBody = line.charAt(5);
//                }catch (Exception e){continue;}
            else if (line.startsWith("food ") && line.length()==6)
                try
                {
                    foodChar = line.charAt(5);
                }catch (Exception e){continue;}
            else if (line.startsWith("empty ") && line.length()==7)
                try
                {
                    emptyChar = line.charAt(6);
                }catch (Exception e){continue;}

        }
    }
}
