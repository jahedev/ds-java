import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements ListI<E>, Iterable<E> {
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

        public Node(E obj) {
            data = obj;
            next = null;
        }
    }

    // Properties
    private Node<E> head;
    private Node<E> tail;
    private int currentSize;

    // Constructor
    public LinkedList() {
        head = tail = null;
        currentSize = 0;
    }

    // Methods
    public void addFirst(E obj) {
        Node<E> node = new Node<E> (obj);
        node.next = head;
        head = node;
        // if list was empty before then tail is currently null
        // set tail to equal head
        if (tail == null) {
            tail = head;
        }
        currentSize++;
    }

    public void addLast(E obj) {
       Node<E> node = new Node<E> (obj);
       if (head == null) {
           head = tail = node;
           currentSize++;
           return;
       }

       tail.next = node;
       tail = tail.next;
       currentSize++;
    }

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
        }
        currentSize--;
        return ret;
    }

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
            Node<E> p = head;
            while (p.next.next != null) {
                p = p.next;
            }
            tail = p;
            tail.next = null;
        }
        currentSize--;
        return ret;
    }

    public E remove(E obj) {

        // if list is empty
        if (head == null) {
            return null;
        }

        Node<E> p = head;
        Node<E> previous = null;

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
                p = p.next;
                previous.next = p;
                currentSize--;
                return ret;
            }
            previous = p;
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
