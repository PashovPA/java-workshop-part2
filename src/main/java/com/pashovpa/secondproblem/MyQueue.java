package com.pashovpa.secondproblem;

import java.util.*;

public class MyQueue<E> implements Queue<E> {
  private Node<E> first;
  private int size;

  private static class Node<E> {
    E item;
    Node<E> next;

    Node(E element, Node<E> next) {
      this.item = element;
      this.next = next;
    }
  }

  public MyQueue() {
    first = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public E poll() {
    if (!isEmpty()) {
      E resultItem = first.item;
      first = first.next;
      size--;
      return resultItem;
    } else {
      return null;
    }
  }

  public boolean add(E newItem) {
    if (newItem != null) {
      Node<E> newNode = new Node<>(newItem, null);
      Node<E> currentNode = first;

      if (isEmpty()) {
        first = newNode;
      } else {
        while (currentNode.next != null) {
          currentNode = currentNode.next;
        }
        currentNode.next = newNode;
      }
      size++;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public Iterator<E> iterator() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public boolean offer(E e) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public E remove() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public E element() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public E peek() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }
}
