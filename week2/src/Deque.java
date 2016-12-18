import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item value;
        private Node next;
        private Node prev;

        public Node(Item value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node cur;

        public DequeIterator() {
            this.cur = head;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException(DEQUE_IS_EMPTY);
            Item res = cur.value;
            cur = cur.next;
            return res;
        }

    }

    private static final String NULL_NOT_PERMITTED = "Cannot add null";
    private static final String DEQUE_IS_EMPTY = "Deque is empty";
    private int size = 0;
    private Node head = null;
    private Node tail = null;

    public Deque() {
    }

    public boolean isEmpty() {
        // is the deque empty?
        return size == 0;
    }

    public int size() {
        // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {
        // add the item to the front
        if (item == null)
            throw new NullPointerException(NULL_NOT_PERMITTED);

        Node original = head;
        head = new Node(item);
        head.next = original;
        if (original != null)
            original.prev = head;

        // in case deque was empty
        if (tail == null)
            tail = head;

        size++;
    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null)
            throw new NullPointerException(NULL_NOT_PERMITTED);

        Node original = tail;
        tail = new Node(item);
        tail.prev = original;
        if (original != null)
            original.next = tail;

        // in case deque was empty
        if (head == null)
            head = tail;

        size++;
    }

    public Item removeFirst() {
        // remove and return the item from the front
        if (size == 0)
            throw new NoSuchElementException(DEQUE_IS_EMPTY);
        Item res = head.value;
        head = head.next;
        if (head != null)
            head.prev = null;

        // in case deque is now empty
        if (head == null)
            tail = null;

        size--;
        return res;
    }

    public Item removeLast() {
        // remove and return the item from the end
        if (size == 0)
            throw new NoSuchElementException(DEQUE_IS_EMPTY);
        Item res = tail.value;
        tail = tail.prev;
        if (tail != null)
            tail.next = null;

        // in case deque is now empty
        if (tail == null)
            head = null;

        size--;
        return res;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

}