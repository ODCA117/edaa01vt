
package queue;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		last = null;
		size = 0;
	}

	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E>{
		private QueueNode<E> pos;
		private int counter = 0;
		
		private QueueIterator(){
			if(last != null)
				pos = last.next;
			else 
				pos = null;
		}
		
		public boolean hasNext(){
			if(size == 0 || counter == size){
				return false;
			}
			else
				return true;
		}
		
		public E next(){
			if(!hasNext())
				throw new NoSuchElementException();
			E x = pos.element;
			pos = pos.next;
			counter++;
			return x;
			
		}
	}

	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {	
		return size;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	x the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E x) {
		QueueNode<E> n = new QueueNode<E>(x);
		if(size == 0){
			last = n;
			n.next = last;
			size++;
			return true;
		}
		if(size == 1){
			
			last.next = n;
			n.next = last;
			last = n; 
			size++;
			return true;
		}
		
		if(size > 1){
			n.next = last.next;
			last.next = n;
			last = n;
			size++;
			return true;
		}
		return false;
		
		
		
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		
		if(size == 0)
			return null;
		
		QueueNode<E> n = last.next;
		if(size == 1){
			size--;
			last = null;
			return n.element;
		}
		
		else{
			last.next = n.next;
			size--;
			return n.element;
			
		}
	}

	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		
		if(last == null)
			return null;
		
		else
			return last.next.element;
		
	}
	
	public void append(FifoQueue<E> q){
		
		if(this == q)
			throw new IllegalArgumentException();
		
		if (this.last == null && q.last == null){
			
		}
		else if(this.last == null){	//this tom
			this.last = q.last;
			this.last.next = q.last.next;
			this.size = q.size;
			q.last = null;
			q.size = 0;
		}
		
		else if(q.last == null){	//q tom
			
		}
		
		else {			//Ingen tom
			
			QueueNode<E> temp = q.last.next;
			q.last.next = this.last.next;
			this.last.next = temp;
			this.last = q.last;
			this.size += q.size();
			q.last = null;
			q.size = 0;
			}
		
		
//		Iterator<E> itr = q.iterator();
//		
//		while(itr.hasNext()){
//			E x = itr.next();
//			
//			this.offer(x);
//			q.poll();
//		}
		
	}


	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}

	}

}
