package simple.snake;

import java.util.Objects;

public class Point
{
    public int x = 0;
    public int y = 0;

    public char c = Settings.snakeBody;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, char c)
    {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y && c == point.c;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, c);
    }
}
