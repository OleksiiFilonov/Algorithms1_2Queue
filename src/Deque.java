import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int size;
	private Node first;
	private Node last;

	// construct an empty deque
	public Deque() {
		size = 0;
		first = null;
		last = null;
	}

	private class Node {
		private Item item;
		private Node next;
		private Node prev;

		public Node(final Item item) {
			this.item = item;
		}
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(Item item) {
		checkForNull(item);
		if (isEmpty()) {
			addFirstNodeToEmptyQueue(item);
		} else {
			Node oldFirst = first;
			Node newFirst = new Node(item);
			newFirst.next = oldFirst;
			newFirst.prev = null;
			oldFirst.prev = newFirst;
			first = newFirst;
		}
		size++;
	}

	// add the item to the end
	public void addLast(Item item) {
		checkForNull(item);
		if (isEmpty()) {
			addFirstNodeToEmptyQueue(item);
		} else {
			Node oldLast = last;
			Node newLast = new Node(item);
			newLast.prev = oldLast;
			newLast.next = null;
			oldLast.next = newLast;
			last = newLast;
		}
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		checkForEmptyOnRemove();
		Node oldFirst = first;
		first = oldFirst.next;
		oldFirst.next = null;
		size--;
		if (isEmpty()) {
			last = null;
		}
		return oldFirst.item;
	}

	// remove and return the item from the end
	public Item removeLast() {
		checkForEmptyOnRemove();
		Node oldLast = last;
		last = oldLast.prev;
		oldLast.prev = null;
		size--;
		if (isEmpty()) {
			first = null;
		}
		return oldLast.item;
	}

	@Override
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new FrontToEndIterator();
	}

	private class FrontToEndIterator implements Iterator<Item> {

		private Node current;

		public FrontToEndIterator() {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No elements left in a queue to iterate");
			}
			Node prev = current;
			current = current.next;
			return prev.item;
		}

	}

	private void checkForEmptyOnRemove() {
		if (isEmpty())
			throw new UnsupportedOperationException("Can't remove from empty queue");
	}

	private void checkForNull(Item item) {
		if (item == null)
			throw new NullPointerException("Null item can't be added");
	}

	private void addFirstNodeToEmptyQueue(Item item) {
		Node node = new Node(item);
		node.next = null;
		node.prev = null;
		first = node;
		last = node;
	}
}
