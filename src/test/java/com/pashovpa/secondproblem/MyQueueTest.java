package com.pashovpa.secondproblem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyQueueTest {
  private MyQueue<Integer> testQueue;
  private MyQueue<Integer> emptyQueue;
  private int testSize;

  @BeforeEach
  public void setUp() {
    testQueue = new MyQueue<>();
    emptyQueue = new MyQueue<>();
    testSize = 5;
    for (int i = 0; i < testSize; i++) {
      testQueue.add(i);
    }
  }

  @Test
  public void testIsEmpty() {
    assertTrue(emptyQueue.isEmpty());
    assertFalse(testQueue.isEmpty());
  }

  @Test
  public void testPoll() {
    assertEquals(0, testQueue.poll());
  }

  @Test
  public void testPollEmpty() {
    assertNull(emptyQueue.poll());
  }

  @Test
  public void testAddNull() {
    assertFalse(testQueue.add(null));
  }

  @Test
  public void testContains() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.contains(0));
  }

  @Test
  public void testIterator() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.iterator());
  }

  @Test
  public void testToArray() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.toArray());
  }

  @Test
  public void testContainsAll() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.containsAll(emptyQueue));
  }

  @Test
  public void testAddAll() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.addAll(emptyQueue));
  }

  @Test
  public void testRemoveAll() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.removeAll(emptyQueue));
  }

  @Test
  public void testRetainAll() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.retainAll(emptyQueue));
  }

  @Test
  public void testClear() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.clear());
  }

  @Test
  public void testOffer() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.offer(0));
  }

  @Test
  public void testRemove() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.remove());
  }

  @Test
  public void testElement() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.element());
  }

  @Test
  public void testPeek() {
    assertThrows(UnsupportedOperationException.class, () -> testQueue.peek());
  }
}
