//package storesim;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Thrown to indicate that an attempt has been made
 * to access a queue's elements when the queue has
 * no elements.
 */
class EmptyQueueException extends RuntimeException{}

/**
 * A generic last-in-last-out queue.
 * 
 * @version %I%, %G%
 */
public class Queue<T> implements Iterable<T>{
    private class Node<T> {
	private T element;
	private Node<T> next;

	private Node(T element) {
	    this.element = element;
	}
    }

    private class QueueIterator implements Iterator<T>{
	private Node<T> currentNode;

	private QueueIterator(Node<T> firstNode){
	    this.currentNode = firstNode;
	}
	
	public boolean hasNext(){
	    return this.currentNode != null;
	}

	public T next(){
	    if (this.currentNode != null) {
		T element = this.currentNode.element;
		this.currentNode = this.currentNode.next;
		return element;
	    } else {
		throw new NoSuchElementException();
	    }
	}
    }
    

    private Node<T> first;
    private Node<T> last;
    private int length;

    /** Empty constructor */
    public Queue(){}

    /**
     * Get the amount of elements currently in the queue.
     *
     * @return the current length of the queue
     */
    public int length(){
	return this.length;
    }

    /**
     * Add an element to the queue. The element will become
     * the last in the queue. If the queue is already empty, 
     * it will be the first element as well.
     *
     * @param element the element to add to the end of the queue
     */
    public void enqueue(T element){
	Node<T> newLastNode = new Node<T>(element);

	if (this.last != null) {
	    this.last.next = newLastNode;
	} else {
	    this.first = newLastNode;
	}

	this.last = newLastNode;
	this.length++;
    }

    /**
     * Remove the first element from the queue and return it.
     * After dequeueing, the second element in the queue will
     * become the new first element.
     *
     * @return the first element in the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public T dequeue(){
	if (this.first != null) {
	    T firstElement = this.first.element;
	    this.first = this.first.next;
	    this.length--;

	    if (this.length == 0) {
		this.last = null;
	    }
	    
	    return firstElement;
	} else {
	    throw new EmptyQueueException();
	}
    }

    /**
     * Get the first element in the queue.
     *
     * @return the first element in the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public T first(){
	if (this.first != null){
	    return this.first.element;
	} else {
	    throw new EmptyQueueException();
	}
    }

    /**
     * Get an iterator for the queue.
     *
     * @return an iterator over the queue
     * @see java.util.Iterator
     */
    public Iterator<T> iterator(){
	return new QueueIterator(this.first);
    }
}
