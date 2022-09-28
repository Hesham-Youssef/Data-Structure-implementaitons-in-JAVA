import java.util.*;

interface IQueue {
    /*** Inserts an item at the queue front. */
    public void enqueue(Object item);

    /*** Removes the object at the queue rear and returnsit. */
    public Object dequeue();

    /*** Tests if this queue is empty. */
    public boolean isEmpty();

    /*** Returns the number of elements in the queue */
    public int size();
}

public class ArrayQueue implements IQueue {
    static Object[] queue = new Object[1000];
    static int size = 0, f = 0, r = 0;

    public void enqueue(Object item) {
        queue[r] = item;
        r++;
        size++;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public Object dequeue() {
        if (isEmpty()) {
            return "Error";
        }
        Object x = queue[f];
        f++;
        size--;
        return x;
    }

    public static void p_queue(ArrayQueue queue) {
        Object[] arr = new Object[queue.size()];
        for (int i = queue.size() - 1; i >= 0; i--) {
            arr[i] = queue.dequeue();
        }
        System.out.println(Arrays.toString(arr));

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayQueue queue = new ArrayQueue();
        String arr = scan.nextLine().replaceAll("\\[|\\]", "");
        String[] array = arr.split(", ");
        if (!array[0].equals("")) {
            for (int i = array.length - 1; i >= 0; i--) {
                queue.enqueue(array[i]);
            }
        }
        String op = scan.nextLine();
        switch (op) {

            case "dequeue":
                if (queue.size() != 0) {
                    queue.dequeue();
                    p_queue(queue);
                } else {
                    System.out.println(queue.dequeue());
                }
                ;
                break;

            case "enqueue":
                if (queue.size() == 1000) {
                    System.out.println("Error");
                } else {
                    queue.enqueue(scan.next());
                    p_queue(queue);
                }
                break;

            case "size":
                System.out.println(queue.size());
                break;

            case "isEmpty":
                if (queue.isEmpty()) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
                break;

            default:
                System.out.println("Error");
        }
        scan.close();
    }
}