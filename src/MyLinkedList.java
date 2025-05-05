/**
 * Represents a node in a singly linked list.
 *
 * @param <T> the type of the value stored in the node
 */
class Node<T> {
    T value;
    Node<T> next;

    /**
     * Constructs a new node with the given value.
     *
     * @param value the value to store in the node
     */
    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}

/**
 * A simple realisation of a singly linked list that supports generic types.
 *
 * @param <T> the type of elements held in this list
 */
public class MyLinkedList<T> {
    private Node<T> head;
    private int size;

    /**
     * Constructs an empty linked list.
     */
    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Adds a new element to the end of the list.
     *
     * @param value the element to be added
     */
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param value the element to be removed
     * @return {@code true} if the element was found and removed; {@code false} otherwise
     */
    public boolean remove(T value) {
        if (head == null) {
            return false;
        }

        if (head.value.equals(value)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;
        Node<T> previous = null;

        while (current != null && !current.value.equals(value)) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        previous.next = current.next;
        size--;
        return true;
    }

    /**
     * Checks whether the list contains the specified element.
     *
     * @param value the element to check for
     * @return {@code true} if the element is found; {@code false} otherwise
     */
    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Prints all elements of the list to the console, separated by spaces.
     */
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println("\n");
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }
}