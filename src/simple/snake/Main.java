package simple.snake;


public class Main
{
    public static void main(String[] args)
    {
        Settings.readFromFile();
        Controller controller = new Controller();
        controller.start();
    }
}
