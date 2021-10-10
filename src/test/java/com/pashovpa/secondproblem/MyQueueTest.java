package com.pashovpa.secondproblem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyQueueTest {

  @Test
  public void testIsEmpty() {
    MyQueue emptyQueue = new MyQueue();
    MyQueue notEmptyQueue = new MyQueue();
    notEmptyQueue.add(1);

    assertTrue(emptyQueue.isEmpty());
    assertFalse(notEmptyQueue.isEmpty());
  }

  @Test
  public void testPoll() {
    MyQueue excepted = new MyQueue();
    MyQueue actual = new MyQueue();

    for (int i = 0; i < 3; i++) {
      excepted.add(i);
      if (i == 0) continue;
      actual.add(i);
    }

    assertEquals(0, excepted.poll());
    assertEquals(actual.size(), excepted.size());
  }

  @Test
  public void testPollEmpty() {
    MyQueue emptyQueue = new MyQueue();
    assertEquals(null, emptyQueue.poll());
  }

  @Test
  public void testAddNull() {
    MyQueue test = new MyQueue();
    assertFalse(test.add(null));
  }
}
