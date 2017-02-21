import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int size;
	private Item[] items;

	// construct an empty randomized queue
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		items = (Item[]) new Object[2];
		size = 0;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the queue
	public int size() {
		return size;
	}

	// add the item
	public void enqueue(final Item item) {
		checkForNull(item);
		if (size == items.length) {
			resize(2 * items.length);
		}
		items[size++] = item;
	}

	// remove and return a random item
	public Item dequeue() {
		checkForEmpty();
		final int randomIndex = StdRandom.uniform(size);
		Item itemToRemove = items[randomIndex];
		if (randomIndex == size - 1) {
			items[size - 1] = null;
		} else {
			items[randomIndex] = items[size - 1];
			items[size - 1] = null;
		}
		size--;
		// shrink size of array if necessary
		if (size > 0 && size == items.length / 4)
			resize(items.length / 2);
		return itemToRemove;
	}

	// return (but do not remove) a random item
	public Item sample() {
		checkForEmpty();
		return items[StdRandom.uniform(size)];
	}

	// return an independent iterator over items in random order
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private int currentIndex;
		private int iteratorSize;
		private Item[] itemsToIterate;

		@SuppressWarnings("unchecked")
		public RandomizedQueueIterator() {
			iteratorSize = size;
			itemsToIterate = (Item[]) new Object[size];
			for (int i = 0; i < size; i++) {
				itemsToIterate[i] = items[i];
			}
			StdRandom.shuffle(itemsToIterate);
			currentIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return currentIndex < iteratorSize;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return itemsToIterate[currentIndex++];
		}

	}

	// resize the underlying array holding the elements
	@SuppressWarnings("unchecked")
	private void resize(final int capacity) {
		assert capacity >= size;
		// textbook implementation
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = items[i];
		}
		items = temp;
		// alternative implementation
		// a = java.util.Arrays.copyOf(a, capacity);
	}

	private void checkForEmpty() {
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty");
	}

	private void checkForNull(final Item item) {
		if (item == null)
			throw new NullPointerException("Null item can't be added");
	}

}
