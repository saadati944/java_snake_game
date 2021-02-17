package simple.snake;

public class LinkedList
{
    public Object value = null;
    public LinkedList next = null;
    public LinkedList previous = null;

    public LinkedList()
    {

    }
    public LinkedList(Object value, LinkedList next)
    {
        this.value = value;
        this.next = next;
    }
}
