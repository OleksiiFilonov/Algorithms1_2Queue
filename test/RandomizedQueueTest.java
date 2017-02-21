import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class RandomizedQueueTest {

	@Test
	public void testEnqueueAndDequeue() {
		final RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
		randomizedQueue.enqueue(1);
		randomizedQueue.enqueue(2);
		randomizedQueue.dequeue();
		randomizedQueue.sample();
		randomizedQueue.enqueue(3);
		randomizedQueue.dequeue();
		randomizedQueue.dequeue();
		randomizedQueue.enqueue(4);
		assertThat(randomizedQueue.size(), equalTo(1));
		assertThat(randomizedQueue.sample(), equalTo(4));
		assertThat(randomizedQueue.dequeue(), equalTo(4));
	}

	@Test
	public void compareIterators() {
		final RandomizedQueue<Integer> queue = createQueueExample();
		int[] firstRandomIterator = createArrayFromRandomIterator(queue);
		int[] secondRandomIterator = createArrayFromRandomIterator(queue);
		int size = queue.size();
		boolean iteratorsIdentical = true;
		for (int i = 0; i < size; i++) {
			if (firstRandomIterator[i] != secondRandomIterator[i]) {
				iteratorsIdentical = false;
				break;
			}
		}
		Assert.assertFalse("Iterators have the same sequence", iteratorsIdentical);
		Arrays.sort(firstRandomIterator);
		Arrays.sort(secondRandomIterator);
		Assert.assertTrue(Arrays.equals(firstRandomIterator, secondRandomIterator));
	}

	private int[] createArrayFromRandomIterator(final RandomizedQueue<Integer> queue) {
		Iterator<Integer> firstIterator = queue.iterator();
		int[] firstRandomIterator = new int[queue.size()];
		for (int i = 0; i < firstRandomIterator.length; i++) {
			firstRandomIterator[i] = firstIterator.next();
		}
		return firstRandomIterator;
	}

	private RandomizedQueue<Integer> createQueueExample() {
		final RandomizedQueue<Integer> queue = new RandomizedQueue<>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(8);
		queue.enqueue(9);
		queue.enqueue(10);
		return queue;
	}

}
