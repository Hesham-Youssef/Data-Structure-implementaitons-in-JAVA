import java.util.*;

interface IStack {

    /***
     * Removes the element at the top of stack and returnsthat element.
     * 
     * @return top of stack element, or through exception if empty
     */

    public Object pop();

    /***
     * Get the element at the top of stack without removing it from stack.
     * 
     * @return top of stack element, or through exception if empty
     */

    public Object peek();

    /***
     * Pushes an item onto the top of this stack.
     * 
     * @param object to insert*
     */

    public void push(Object element);

    /***
     * Tests if this stack is empty
     * 
     * @return true if stack empty
     */
    public boolean isEmpty();

    public int size();
}

public class MyStack implements IStack {
    Node head = null;
    int size = 0;

    static class Node {
        Object value;
        Node next;

        Node(Object d, Node n) {
            value = d;
            next = n;
        }

        public String toString() {
            return this.value + "";
        }
    }

    public void push(Object element) {
        Node newNode = new Node(element, head);
        head = newNode;
        size++;
    }

    public Object pop() {
        if (head != null) {
            Node temp = head;
            head = head.next;
            size--;
            return temp.value;
        } else
            return null;
    }

    public Object peek() {
        if (head != null)
            return head.value;
        else
            return null;
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    public int size() {
        return size;
    }

    public void printlist(MyStack list) {
        if (list.size() > 1) {
            System.out.print("[");
            while (list.peek() != null && list.size() > 1) {
                System.out.print(list.pop() + ", ");
            }
            System.out.print(list.pop() + "");
            System.out.print("]");
        } else if (list.size == 1) {
            System.out.print("[" + list.pop() + "]");
        } else {
            System.out.print("[" + "]");
        }
    }

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        Scanner sc = new Scanner(System.in);
        MyStack sll = new MyStack();
        String listin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] list = listin.split(", ");
        if (list[0].equals(""))
            sll.head = null;
        else {
            for (int i = list.length - 1; i > -1; i--) {
                sll.push(list[i].toString());
            }
        }
        String order = sc.next();
        int value;
        Object temp;
        switch (order) {
            case "push":
                value = sc.nextInt();
                sll.push(value);
                sll.printlist(sll);
                break;

            case "pop":
                temp = sll.pop();
                if (temp == null)
                    System.out.println("Error");
                else
                    sll.printlist(sll);
                break;

            case "peek":
                temp = sll.peek();
                if (temp == null)
                    System.out.println("Error");
                else
                    System.out.println(temp);
                break;

            case "isEmpty":
                if (sll.isEmpty())
                    System.out.println("True");
                else
                    System.out.println("False");
                break;

            case "size":
                System.out.println(sll.size());
                break;
            default:
                System.out.println("Error");

        }

        sc.close();
    }
}