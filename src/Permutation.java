import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		int k = Integer.valueOf(args[0]);
		if (k == 0)
			return;
		RandomizedQueue<String> queue = new RandomizedQueue<>();

		// System.out.println(Arrays.toString(StdIn.readAllStrings()));
		while (!StdIn.isEmpty()) {
			queue.enqueue(StdIn.readString());
		}
		int index = 0;
		Iterator<String> iterator = queue.iterator();
		while (iterator.hasNext() && index < k) {
			System.out.println(iterator.next());
			index++;
		}
	}

}
