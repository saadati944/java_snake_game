package simple.snake;

import java.io.File;
import java.util.Scanner;

public class Settings
{
    //settings file path
    private static final String settingsPath = "./settings.txt";


    // size of the world
    public static int worldWidth = 80;
    public static int worldHeight =20;

    //game speed
    public static int frameRate = 30;

    // how many frames should wait after
    // eating a food to appear a new one
    public static int foodDelay = 2;


    // character to show snake head
    public static char snakeHead = '@';

    // character to show snake body
    public static char snakeBody = 'O';


    /**
     * read settings from settings file if exists
     */
    public static void readFromFile()
    {
        File settingsFile;
        Scanner scanner;
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
            else if (line.startsWith("body ") && line.length()==6)
                try
                {
                    snakeBody = line.charAt(5);
                }catch (Exception e){continue;}

        }
    }
}
