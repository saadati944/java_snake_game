package simple.snake;

public class Main
{

    public static void main(String[] args)
    {
        System.out.println("before reading file :");
        System.out.print("width  :   ");
        System.out.println(Settings.worldWidth);
        System.out.print("height :   ");
        System.out.println(Settings.worldHeight);
        System.out.print("fps    :   ");
        System.out.println(Settings.frameRate);
        System.out.print("delay  :   ");
        System.out.println(Settings.foodDelay);

        Settings.readFromFile();

        System.out.println("after reading file :");
        System.out.print("width  :   ");
        System.out.println(Settings.worldWidth);
        System.out.print("height :   ");
        System.out.println(Settings.worldHeight);
        System.out.print("fps    :   ");
        System.out.println(Settings.frameRate);
        System.out.print("delay  :   ");
        System.out.println(Settings.foodDelay);

    }
}
