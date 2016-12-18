import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_SIZE = 64;
    private int size = 0;
    private Item[] items = (Item[]) new Object[DEFAULT_SIZE];


    public RandomizedQueue() {
        // construct an empty randomized queue
    }

    public boolean isEmpty() {
        // is the queue empty?
        return size == 0;
    }

    public int size() {
        // return the number of items on the queue
        return size;
    }

    public void enqueue(Item item) {
        // add the item
        if (item == null) throw new NullPointerException();
        // ensure capacity
        if (size == items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }
        items[size++] = item;
    }

    public Item dequeue() {
        if (size == 0)
            throw new NoSuchElementException();

        // remove and return a random item
        int idx = StdRandom.uniform(size);
        Item res = items[idx];
        items[idx] = items[--size];
        // free memory
        items[size] = null;

        // half the capacity of array
        if (size <= items.length / 4 && size != 0) {
            items = Arrays.copyOf(items, items.length / 2);
        }
        return res;
    }

    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException();
        return items[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private Item[] iterItems = Arrays.copyOf(items, size);
        private int iterSize = size;

        @Override
        public boolean hasNext() {
            return iterSize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int idx = StdRandom.uniform(iterSize);
            Item res = iterItems[idx];
            iterItems[idx] = iterItems[--iterSize];
            // free memory
            iterItems[iterSize] = null;
            return res;
        }

    }

}