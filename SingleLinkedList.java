import java.util.*;

interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * 
     * @param index
     * @param element
     */
    public void add(int index, Object element);

    /**
     * Inserts the specified element at the end of the list.
     * 
     * @param element
     */
    public void add(Object element);

    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * 
     * @param index
     * @param element
     */
    public void set(int index, Object element);

    /**
     * Removes all of the elements from this list.
     */
    public void clear();

    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();

    /**
     * Removes the element at the specified position in this list.
     * 
     * @param index
     */
    public void remove(int index);

    /**
     * @return the number of elements in this list.
     */
    public int size();

    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex
     *         and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);

    /**
     * @param o
     * @return true if this list contains an element with the same value as the
     *         specified element.
     */
    public boolean contains(Object o);
}

public class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here */

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

    public void add(Object element) {
        Node newNode = new Node(element, null);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public void add(int index, Object element) {
        if (!((index > size) || (index < 0))) {
            Node newNode = new Node(element, null);
            if (index == 0) {
                newNode.next = head;
                head = newNode;
            } else {
                Node temp = head;
                int i = 0;
                while (i < index - 1) {
                    temp = temp.next;
                    i++;
                }
                newNode.next = temp.next;
                temp.next = newNode;
            }
            size++;
        }
    }

    public Object get(int index) {
        if ((index > size - 1) || (index < 0))
            return null;
        else {
            if (index == 0) {
                return head.value;
            } else {
                Node temp = head;
                int i = 0;
                while (i < index) {
                    temp = temp.next;
                    i++;
                }
                return temp.value;
            }
        }
    }

    public void set(int index, Object element) {
        if (!((index > size - 1) || (index < 0))) {
            if (index == 0) {
                head.value = element;
            } else {
                Node temp = head;
                int i = 0;
                while (i < index) {
                    temp = temp.next;
                    i++;
                }
                temp.value = element;
            }
        }
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        else
            return false;
    }

    public void remove(int index) {
        if (!((index > size - 1) || (index < 0))) {
            if (index == 0) {
                head = head.next;
            } else {
                Node temp = head;
                int i = 0;
                while (i < index - 1) {
                    temp = temp.next;
                    i++;
                }
                temp.next = temp.next.next;

            }
            size--;
        }

    }

    public int size() {
        return size;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        ILinkedList sub = new SingleLinkedList();
        if ((fromIndex > size - 1) || (fromIndex < 0) || (toIndex > size - 1) || (toIndex < 0) || (toIndex < fromIndex))
            System.out.println("Error");
        else {
            Node temp = head;
            int i = 0;
            while (i < toIndex + 1) {
                if (i >= fromIndex && i <= toIndex)
                    sub.add(temp.value);
                temp = temp.next;
                i++;
            }
        }
        return sub;
    }

    public boolean contains(Object o) {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.value.equals(o.toString()))
                return true;
            else
                temp = temp.next;
        }
        return false;
    }

    public void printlist(SingleLinkedList list) {
        if (list.size() > 1) {
            System.out.print("[");
            for (int i = 0; i < list.size() - 1; i++)
                System.out.print(list.get(i) + ", ");
            System.out.print(list.get(list.size() - 1) + "");
            System.out.print("]");
        } else if (list.size == 1) {
            System.out.print("[" + list.get(0) + "]");
        } else {
            System.out.print("[" + "]");
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        Scanner sc = new Scanner(System.in);
        SingleLinkedList sll = new SingleLinkedList();
        String listin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] list = listin.split(", ");
        if (list[0].equals(""))
            sll.head = null;
        else {
            for (String num : list) {
                sll.add(num.toString());
            }
        }
        String order = sc.next();
        int value;
        int index;
        int oldsize;
        Object temp;
        switch (order) {
            case "add":
                value = sc.nextInt();
                sll.add(value);
                sll.printlist(sll);
                break;

            case "addToIndex":
                index = sc.nextInt();
                value = sc.nextInt();
                oldsize = sll.size();
                sll.add(index, value);
                if (oldsize == sll.size())
                    System.out.println("Error");
                else
                    sll.printlist(sll);
                break;

            case "get":
                index = sc.nextInt();
                temp = sll.get(index);
                if (temp == null)
                    System.out.println("Error");
                else
                    System.out.println(temp);
                break;

            case "set":
                index = sc.nextInt();
                value = sc.nextInt();
                sll.set(index, value);
                if ((index > sll.size() - 1) || (index < 0))
                    System.out.println("Error");
                else
                    sll.printlist(sll);
                break;

            case "clear":
                sll.clear();
                sll.printlist(sll);
                break;

            case "isEmpty":
                if (sll.isEmpty())
                    System.out.println("True");
                else
                    System.out.println("False");
                break;

            case "contains":
                value = sc.nextInt();
                if (sll.contains(value))
                    System.out.println("True");
                else
                    System.out.println("False");
                break;

            case "size":
                System.out.println(sll.size());
                break;

            case "remove":
                index = sc.nextInt();
                oldsize = sll.size();
                sll.remove(index);
                if (oldsize == sll.size())
                    System.out.println("Error");
                else
                    sll.printlist(sll);
                break;

            case "sublist":
                index = sc.nextInt();
                value = sc.nextInt();
                ILinkedList sub = sll.sublist(index, value);
                if (sub.size() != 0) {
                    System.out.print("[");
                    for (int i = 0; i < sub.size() - 1; i++)
                        System.out.print(sub.get(i) + ", ");
                    System.out.print(sub.get(sub.size() - 1) + "");
                    System.out.print("]");
                }
                break;
        }
        sc.close();

    }
}