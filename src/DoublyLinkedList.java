import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements ListI<E>, Iterable<E> {
    /*
     * 5 Boundary Conditions to Think of When Creating Data Structures:
     *   1. Empty list
     *   2. Single element in list
     *   3. Working at the beginning of the list
     *   4. Working at the end of the list
     *   5. Working in the middle of the list
     */

    // Inner-Class Node<E>
    class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E obj) {
            data = obj;
            next = prev = null;
        }
    }

    // Properties
    private Node<E> head;
    private Node<E> tail;
    private int currentSize;

    // Constructor
    public DoublyLinkedList() {
        head = tail = null;
        currentSize = 0;
    }

    // Methods
    // Time: O(1)
    public void addFirst(E obj) {
        Node<E> node = new Node<E> (obj);
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) {
            tail = head;
        }
        currentSize++;
    }

    // Time: O(1)
    public void addLast(E obj) {
        Node<E> node = new Node<E> (obj);
        if (head == null) {
            head = tail = node;
            currentSize++;
            return;
        }

        tail.next = node;
        node.prev = tail;
        tail = tail.next;
        currentSize++;
    }

    // Time: O(1)
    public E removeFirst() {
        // currentSize = 0
        if (head == null) {
            return null;
        }

        E ret = head.data;

        // currentSize == 1
        if (head == tail) {
            head = tail = null;
        } else { // currentSize > 1
            head = head.next;
            head.prev = null;
        }
        currentSize--;
        return ret;
    }

    // Time: O(1)
    public E removeLast() {
        // currentSize = 0
        if (head == null) {
            return null;
        }

        E ret = tail.data;

        // currentSize == 1
        if (head == tail) {
            head = tail = null;
        } else { // currentSize > 1
            tail = tail.prev;
            tail.next = null;
        }
        currentSize--;
        return ret;
    }

    // Time: O(n)
    public E remove(E obj) {

        // if list is empty
        if (head == null) {
            return null;
        }

        Node<E> p = head;

        while (p != null) {
            if (((Comparable<E>) p.data).compareTo(obj) == 0) {
                E ret = p.data;

                // if there is only a single element
                if (head == tail) {
                    head = tail = null;
                    currentSize = 0;
                    return ret;
                }

                // if we are removing from beginning of list
                // or end of list
                if (p == head)
                {
                    return removeFirst();
                } else if (p == tail) {
                    return removeLast();
                }

                // remove in the middle of list
                Node<E> previous = p.prev;
                p = p.next;
                p.prev = previous;
                previous.next = p;
                currentSize--;
                return ret;
            }
            p = p.next;
        }

        return null;
    }

    public boolean contains(E obj) {
        Node<E> p = head;
        while (p != null) {
            if (((Comparable<E>) p.data).compareTo(obj) == 0) {
                return true;
            }
            p = p.next;
        }
        return false;
    }


    // PEEKING
    public E peekFirst() {
        return head != null ? head.data : null;
    }

    public E peekLast() {
        return tail != null ? tail.data : null;
    }

    public int size() {
        return currentSize;
    }

    // PRINTERS

    public void printList() {
        Node<E> p;
        int i;

        for (p = head, i = 0; p != null; p = p.next, i++) {
            System.out.printf("%d->%s\n", i, (p.data).toString());
        }
    }

    public void printListReverse() {
        Node<E> p;
        int i;

        for (p = tail, i = currentSize-1; p != null; p = p.prev, i--) {
            System.out.printf("%d->%s\n", i, (p.data).toString());
        }
    }

    // ITERATOR
    @Override
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    class IteratorHelper implements Iterator<E> {

        Node<E> index;

        public IteratorHelper() {
            index = head;
        }

        @Override
        public boolean hasNext() {
            return index != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E ret = index.data;
            index = index.next;
            return ret;
        }
    }

}
