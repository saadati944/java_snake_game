package simple.snake;


public class Controller
{
    // variables
    private LinkedList head;
    private LinkedList tail;

    private World world;

    public static inputType input = inputType.Empty;
    private snakeDirection direction = snakeDirection.Paused;
    public static boolean isLive = true;


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
        Down,
        Paused
    }

    // constructor (initialization)
    public Controller()
    {
        this.world = new World();

        head = new LinkedList();
        head.value = Settings.snakeStartPoint;

        direction = Settings.snakeStartDirection;

        InputController inputController = new InputController();
        inputController.start();
    }


    public void start()
    {
        long startTime;
        while (isLive)
        {
            startTime = System.nanoTime();
            if(input == inputType.Exit)
            {
                System.out.println("exiting ...");
                return;
            }

            executeInput();

            world.reset();
            addSnakeToWorld();
            world.show();

            try
            {
                Thread.sleep(Math.abs((System.nanoTime() - startTime)/10000 - Settings.frameDelay));
            }catch (Exception e){}
        }
    }

    private void executeInput()
    {
        Point newHeadPoint = new Point(0, 0);

        switch (input)
        {
            case Right:
                if (direction!= snakeDirection.Left)
                    direction = snakeDirection.Right;
                break;
            case Left :
                if (direction!= snakeDirection.Right)
                    direction = snakeDirection.Left;
                break;
            case Up :
                if (direction!= snakeDirection.Down)
                    direction = snakeDirection.Up;
                break;
            case Down :
                if (direction!= snakeDirection.Up)
                    direction = snakeDirection.Down;
                break;
            case Menu :
                direction = snakeDirection.Paused;
                break;
        }
        switch (direction)
        {
            case Right:
                newHeadPoint = new Point(((Point) head.value).x + 1, ((Point) head.value).y, Settings.snakeHead);
                break;
            case Left:
                newHeadPoint = new Point(((Point) head.value).x - 1, ((Point) head.value).y, Settings.snakeHead);
                break;
            case Up:
                newHeadPoint = new Point(((Point) head.value).x, ((Point) head.value).y - 1, Settings.snakeHead);
                break;
            case Down:
                newHeadPoint = new Point(((Point) head.value).x, ((Point) head.value).y + 1, Settings.snakeHead);
                break;
            case Paused:
                return;
        }

        //the snake must not be able to move in the opposite direction
        if(head.next != null && newHeadPoint.x ==((Point)head.next.value).x && newHeadPoint.y ==((Point)head.next.value).y)
        {
            return;
        }

        //the snake must not be able to cross itself
        if (!checkPoint(newHeadPoint))
        {
            isLive = false; // the snake bit itself
            return;
        }

        // change head to new head
        LinkedList newHead = new LinkedList();
        ((Point) head.value).c = Settings.snakeBody;
        newHead.value = newHeadPoint;
        newHead.next = head;
        head = newHead;
    }

    /**
     * return true if the point is empty
     */
    private boolean checkPoint(Point p)
    {
        // the snake can't move out of the world
        if(p.x < 0 || p.x >= Settings.worldWidth || p.y < 0 || p.y >= Settings.worldHeight)
            return false;

        // the snake can't move over itself
        LinkedList cur = head;
        while (cur != null)
        {
            if(((Point)cur.value).x == p.x && ((Point)cur.value).y == p.y)
                return false;
            cur = cur.next;
        }
        return true;
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
                if (inp == 27 || !Controller.isLive)
                    return;
                inp = RawConsoleInput.read(true);
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