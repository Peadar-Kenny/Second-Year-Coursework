import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 09/10/18 11:13:22
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *  TODO
     */
    public boolean isEmpty()
    {
      if(head ==null && tail == null)return true;
      else return false; 
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void insertBefore( int pos, T data ) 
    {
      
      if(isEmpty()){
		DLLNode node = new DLLNode(data, null, null);
		head = node;
		tail = node;
      }else if(pos <=0){
		DLLNode node = new DLLNode(data, null, head);
		head.prev = node;
		head = node;
      }else{
		int count =0;
        for (DLLNode iter = head; iter != null; iter = iter.next){
        	if(count == pos){
          		DLLNode node = new DLLNode(data, iter.prev,iter);
          		iter.prev.next = node;
          		iter.prev = node;
          		return;
        	}
        count++;
      }
	  DLLNode node = new DLLNode(data, tail, null);
	  tail.next = node;
	  tail = node;
      }
      return;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *  TODO
     *
     */
    public T get(int pos) 
    {
      if (isEmpty()) return null;
      int count = 0;
      for (DLLNode iter = head; iter != null; iter = iter.next){
        if(count == pos){
          return iter.data;
        }
        count++;
      }
      //TODO
      return null;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *  TODO
     */
    public boolean deleteAt(int pos) 
    {
		if(isEmpty()){
			return false;
		}
      int count = 0;
      for (DLLNode iter = head; iter != null; iter = iter.next){
        if (count == pos){
          if(iter == tail && iter == head){
		 	     head = null;
           tail = null;
         	 return true;
        }else if(iter == tail){
			 tail = iter.prev;
			 tail.next = null;
			 return true;
    }else if(( iter == head)){
       head = iter.next;
        head.prev = null;
        return true;
      }
    else {
			 iter.prev.next = iter.next;
          	 iter.next.prev = iter.prev;
          }
          
          return true;
        }
        count++;
      }
      
      return false;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void reverse()
    {
      int upperCount=0;
      T transfer;
      for (DLLNode iter = head; iter != null; iter = iter.next){
        upperCount++;
      }
      for(int count =0; count<upperCount; count++){
        transfer = get(count);
        insertBefore(count, get(upperCount));
        deleteAt(count+1);
        insertBefore(upperCount, transfer);
        deleteAt(upperCount+1);
        upperCount = upperCount-1;
      }
        
      
      
      //TODO
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: theta n^2
     *
     * Justification:
     *  TODO
     */
     public void makeUnique()
    {
       int uniqueCount;
       int count;
       
       if(!isEmpty()){
        for (DLLNode iter = head; iter != null; iter = iter.next){
          uniqueCount=0;
          count = 0;
          for (DLLNode node = head; node != null; node = node.next){
            if(iter.data == node.data){
              uniqueCount++;
              if(uniqueCount >1)deleteAt(count);
          }
          count++;
        }
      }
      //TODO
    }
  }


    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void push(T item) 
    {
      insertBefore(0, item);
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T pop() 
    {
      if(isEmpty()){
        return null;
      }
      T data = get(0);
      deleteAt(0);
      return data;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *  TODO
     */
    public void enqueue(T item) 
    {
      insertBefore(0, item);
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: TODO
     *
     * Justification:
     *  TODO
     */
    
    public T dequeue() {
      if(isEmpty()) {
          return null;
      }
      else {
          T theData = tail.data;
          if(head == tail) {
              head = null;
              tail = null;
          }
          else {
              tail = tail.prev;
              tail.next = null;
          }
          return theData;
      }
  }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}



