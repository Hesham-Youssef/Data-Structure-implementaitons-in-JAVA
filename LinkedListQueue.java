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

public class LinkedListQueue implements IQueue {
    Node front = null;
    Node rear = null;
    int size = 0;

    static class Node {
        Object value;
        Node next;

        Node(Object d, Node n) {
            value = d;
            next = n;
        }
    }

    public void enqueue(Object item) {
        if (isEmpty()) {
            Node node = new Node(item, null);
            front = node;
            rear = node;
            size++;
        } else {
            Node node = new Node(item, null);
            rear.next = node;
            rear = node;
            size++;
        }
    }

    public boolean isEmpty() {
        if (front == null) {
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
        Object x = front.value;
        front = front.next;
        size--;
        return x;
    }

    public static void p_queue(LinkedListQueue queue) {
        Object[] arr = new Object[queue.size()];
        for (int i = queue.size() - 1; i >= 0; i--) {
            arr[i] = queue.dequeue();
        }
        System.out.println(Arrays.toString(arr));

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedListQueue queue = new LinkedListQueue();
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
                if (queue.size != 0) {
                    queue.dequeue();
                    p_queue(queue);
                } else {
                    System.out.println(queue.dequeue());
                }
                ;
                break;

            case "enqueue":
                queue.enqueue(scan.next());
                p_queue(queue);
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