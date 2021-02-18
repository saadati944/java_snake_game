package simple.snake;

import java.util.ArrayList;

public class World
{
    public ArrayList<Point> points = new ArrayList<Point>();

    public World()
    {

    }

    public void addPoint(Point p)
    {
        if (!this.points.contains(p))
            this.points.add(p);
    }

    public void show()
    {
        StringBuilder sc = new StringBuilder();
        for (int j = 0; j < Settings.worldHeight; j++)
        {
            for (int i = 0; i < Settings.worldWidth; i++)
            {
                //System.out.print(getCharAt(i, j));
                sc.append(getCharAt(i, j));
            }
            //System.out.println();
            sc.append(System.lineSeparator());
        }
        clearScreen();
        System.out.println(sc.toString());
        System.out.flush();
    }

    public void clearScreen()
    {
        if (!Settings.isLinux)
            try
            {
                Runtime.getRuntime().exec("cls");
                return;
            } catch (Exception e)
            {
            }
        System.out.print("\033[0;0H");
        System.out.flush();
    }

    //bug : the clear command does not work !!!
    public void clearCommand()
    {
        try
        {
            if (Settings.isLinux)
                Runtime.getRuntime().exec("clear");
            else
                Runtime.getRuntime().exec("cls");
        } catch (Exception e)
        {
        }
    }

    public char getCharAt(int x, int y)
    {
        for (Point p : points)
            if (p != null && p.x == x && p.y == y)
                return p.c;
        return Settings.emptyChar;
    }

    public void reset()
    {
        this.points = new ArrayList<Point>();
    }
}