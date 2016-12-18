import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while(!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }

        while (--k >= 0) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}