package simple.snake;

import java.util.Scanner;

public class Controller
{
    // variables
    private LinkedList head;
    private LinkedList tail;

    private World world;

    public static inputType input = inputType.Empty;
    private snakeDirection direction = snakeDirection.Paused;
    boolean isLive = true;

    private Scanner scanner;

    public enum inputType
    {
        Right,
        Left,
        Up,
        Down,
        Menu,
        Exit,
        Empty
    }

    enum snakeDirection
    {
        Right,
        Left,
        Up,
        Paused
    }

    // constructor (initialization)
    public Controller()
    {
        this.scanner = new Scanner(System.in);
        this.world = new World();
        InputController inputController = new InputController();
        inputController.start();
    }


    public void start()
    {
        System.out.println("start called ... ");
        while (isLive)
        {
            try
            {
                Thread.sleep(10);
            }catch (Exception e){}
            if(input == inputType.Exit)
            {
                System.out.println("exiting ...");
                return;
            }
        }
    }

    private void loop()
    {
        addSnakeToWorld();
        world.show();
        world.reset();
    }

    private void addSnakeToWorld()
    {
        LinkedList cur = head;
        while (cur != null)
        {
            world.addPoint((Point) cur.value);
            cur = cur.next;
        }
    }

}

class InputController extends Thread
{
    public void run()
    {
        try
        {
            int inp = 0;
            RawConsoleInput.resetConsoleMode();
            while (true)
            {
                if(inp == 27)
                    return;
                inp = RawConsoleInput.read(true);
                System.out.print("detecting input : ");
                System.out.println(inp);
                switch (inp)
                {
                    case 'w', 'W' -> Controller.input = Controller.inputType.Up;
                    case 's', 'S' -> Controller.input = Controller.inputType.Down;
                    case 'a', 'A' -> Controller.input = Controller.inputType.Left;
                    case 'd', 'D' -> Controller.input = Controller.inputType.Right;
                    case 27 -> Controller.input = Controller.inputType.Exit; // 27 = escape key
                }
            }
        } catch (Exception e)
        {
        }
    }
}