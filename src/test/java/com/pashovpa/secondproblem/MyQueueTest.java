package com.pashovpa.secondproblem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyQueueTest {

  @Test
  public void testIsEmpty() {
    MyQueue<Integer> emptyQueue = new MyQueue<>();
    MyQueue<Integer> notEmptyQueue = new MyQueue<>();
    notEmptyQueue.add(1);

    assertTrue(emptyQueue.isEmpty());
    assertFalse(notEmptyQueue.isEmpty());
  }

  @Test
  public void testPoll() {
    MyQueue<Integer> excepted = new MyQueue<>();
    MyQueue<Integer> actual = new MyQueue<>();

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
    MyQueue<Integer> emptyQueue = new MyQueue<>();
    assertNull(emptyQueue.poll());
  }

  @Test
  public void testAddNull() {
    MyQueue<Integer> test = new MyQueue<>();
    assertFalse(test.add(null));
  }
}
