package com.pashovpa.secondproblem;

import java.util.*;

public class MyQueue<E> implements Queue<E> {
  private MyLinkedList<E> itemsList;

  public MyQueue() {
    itemsList = new MyLinkedList<>();
  }

  public int size() {
    return itemsList.size();
  }

  public boolean isEmpty() {
    return itemsList.isEmpty();
  }

  public E poll() {
    return itemsList.remove(0);
  }

  public boolean add(E newItem) {
    return itemsList.add(newItem);
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
