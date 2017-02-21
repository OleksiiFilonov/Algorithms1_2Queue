
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DequeTest {

	private Deque<Integer> deque = new Deque<>();

	@Test
	public void addFirstAndRemove() {
		deque.addFirst(1);
		assertThat(deque.removeFirst(), equalTo(1));
		deque.addFirst(2);
		assertThat(deque.removeFirst(), equalTo(2));
		deque.addFirst(3);
		assertThat(deque.removeFirst(), equalTo(3));
	}

	@Test
	public void addLatAndRemove() {
		deque.addLast(1);
		assertThat(deque.removeLast(), equalTo(1));
		deque.addLast(2);
		assertThat(deque.removeLast(), equalTo(2));
		deque.addLast(3);
		assertThat(deque.removeLast(), equalTo(3));
	}

	@Test
	public void addFirst3RemoveFirst3() {
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addFirst(3);
		assertThat(deque.removeFirst(), equalTo(3));
		assertThat(deque.removeFirst(), equalTo(2));
		assertThat(deque.removeFirst(), equalTo(1));
	}

	@Test
	public void addLast3RemoveLast3() {
		deque.addLast(1);
		deque.addLast(2);
		deque.addLast(3);
		assertThat(deque.removeLast(), equalTo(3));
		assertThat(deque.removeLast(), equalTo(2));
		assertThat(deque.removeLast(), equalTo(1));
	}

	@Test
	public void addLast3RemoveFirst3() {
		deque.addLast(1);
		deque.addLast(2);
		deque.addLast(3);
		assertThat(deque.removeFirst(), equalTo(1));
		assertThat(deque.removeFirst(), equalTo(2));
		assertThat(deque.removeFirst(), equalTo(3));
	}

	@Test
	public void addFirst3RemoveLast3() {
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addFirst(3);
		assertThat(deque.removeLast(), equalTo(1));
		assertThat(deque.removeLast(), equalTo(2));
		assertThat(deque.removeLast(), equalTo(3));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void randomAddRemove() {
		deque.addFirst(1);
		deque.addLast(2);
		assertThat(deque.removeFirst(), equalTo(1));
		deque.addFirst(3);
		deque.addFirst(4);
		assertThat(deque.removeLast(), equalTo(2));
		assertThat(deque.removeFirst(), equalTo(4));
		assertThat(deque.removeFirst(), equalTo(3));
		deque.removeFirst();
	}

	@Test(expected = NullPointerException.class)
	public void cantAddNull() {
		deque.addFirst(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void iterateOverQueueFronTheFront() {
		deque.addFirst(1);
		deque.addLast(2);
		deque.addFirst(3);
		deque.addLast(4);
		// 3->1->2->4
		Iterator<Integer> iterator = deque.iterator();
		assertTrue(iterator.hasNext());
		assertThat(iterator.next(), equalTo(3));
		assertThat(iterator.next(), equalTo(1));
		assertThat(iterator.next(), equalTo(2));
		assertThat(iterator.next(), equalTo(4));
		assertFalse(iterator.hasNext());
		iterator.next();
	}

}
