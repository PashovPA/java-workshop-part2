package com.pashovpa.secondproblem;

import java.util.*;

public class MyLinkedList<E> implements List<E> {
  private Node<E> first;
  private Node<E> last;
  private int size;

  private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }

  public MyLinkedList() {
    first = null;
    last = null;
    size = 0;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public int size() {
    return size;
  }

  public boolean add(E newItem) {
    if (newItem == null) return false;

    Node<E> newNode = new Node<>(null, newItem, null);
    if (isEmpty()) {
      first = newNode;
    } else if (last == null) {
      last = newNode;
      last.prev = first;
      first.next = last;
    } else {
      last.next = newNode;
      newNode.prev = last;
      last = newNode;
    }

    size++;
    return true;
  }

  private int verifyIndex(int index) {
    if (index < (-1 * size) || index >= size) return -1;

    if (index >= (-1 * size) && index < 0) {
      index += size;
      return index;
    }
    return index;
  }


  public E get(int index) {
    int verifyIndex = verifyIndex(index);
    if (isEmpty() || verifyIndex == -1) return null;

    Node<E> currentNode = first;
    int counter = 0;

    while (counter != verifyIndex) {
      currentNode = currentNode.next;
      counter++;
    }
    return currentNode.item;
  }

  public boolean add(E newItem, int index) {
    if (newItem == null || index < 0 || index > size) return false;

    Node<E> newNode = new Node<>(null, newItem, null);
    if (isEmpty()) {
      first = newNode;
    } else if (index == size) {
      this.add(newItem);
    } else {
      Node<E> currentNode = first;
      int counter = 0;

      while (counter != index) {
        currentNode = currentNode.next;
        counter++;
      }

      currentNode.prev.next = newNode;
      newNode.prev = currentNode.prev;
      currentNode.prev = newNode;
      newNode.next = currentNode;
    }

    size++;
    return true;
  }

  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  public int indexOf(Object o) {
    int index = 0;

    if (o != null) {
      for (Node<E> currentNode = first; currentNode != null; currentNode = currentNode.next) {
        if (o.equals(currentNode.item)) return index;
        index++;
      }
    }
    return -1;
  }

  public E remove(int index) {
    int verifyIndex = verifyIndex(index);
    if (isEmpty() || verifyIndex == -1) return null;

    Node<E> currentNode = first;
    int counter = 0;

    while (counter != verifyIndex) {
      currentNode = currentNode.next;
      counter++;
    }

    if (currentNode == first) {
      removeFirst();
      return currentNode.item;
    }
    if (currentNode == last) {
      removeLast();
      return currentNode.item;
    }

    currentNode.prev.next = currentNode.next;
    currentNode.next.prev = currentNode.prev;

    size--;
    return currentNode.item;
  }

  private void removeFirst() {
    if (first.next == null) {
      last = null;
    } else {
      first.next.prev = null;
    }
    first = first.next;
  }

  private void removeLast() {
    if (first.next == null) {
      first = null;
    } else {
      last.prev.next = null;
    }
    last = last.prev;
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
  public boolean addAll(int index, Collection<? extends E> c) {
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
  public E set(int index, E element) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public void add(int index, E element) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public ListIterator<E> listIterator() {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException("This operation is unsupported!");
  }
}
