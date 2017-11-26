/**
 * Import Java Utility
 */
import java.util.*;

/**
 *
 * @author Michael Mangrobang
 *
 **/
public class ArrayStack<E> implements Stack211<E> {

  
  private E[] data;
  private int top;
  
  /**
   * emptyStack Method.
   * Calls empty() method. If empty throws new EmptyStackException
   * @throws new EmptyStackException
   */
  public void emptyStack() {
    
    if (empty()) {
      
      throw new EmptyStackException();
      
    }
    
  }
  
  
  /**
   * Empty Method.
   * Returns true if stack is empty, false if not.
   * @return top == 0
   */
  /* (non-Javadoc)
   * @see Stack211#empty()
   */
  @Override
  public boolean empty() {
    
    return top == 0;
  
  }

  
  /**
   * Peek Method.
   * Check and returns top element.
   * @return data[top-1]
   */
  /* (non-Javadoc)
   * @see Stack211#peek()
   */
  @Override
  public E peek() {
    
    emptyStack();
    return data[top-1];
    
  }

  
  /**
   * Pop Method.
   * Removes and returns top element.
   * @return popped 
   */
  /* (non-Javadoc)
   * @see Stack211#pop()
   */
  @Override
  public E pop() {
    
    emptyStack();
    E popped = data[top-1];
    top--;
    return popped;

  }

  
  /**
   * Push Method.
   * Adds a new element to the top of the stack.
   * @param item
   * @reutrn 
   */
  /* (non-Javadoc)
   * @see Stack211#push(java.lang.Object)
   */
  @Override
  public E push(E item) {
    
    if (top == data.length) {
      
      reallocate();
      
    }
    
    data[top++] = item;
    return data[top-1];
    
  } 
  
  
  /**
   * Reallocate Method.
   * Doubles the size of the stack.
   */
  public void reallocate() {

    E[] temp = (E[]) new Object[data.length * 2];
    System.arraycopy(data, 0, temp, 0, data.length);
    data = temp;

  }
  
  
  /**
   * ArrayStack Constructor.
   */
  public ArrayStack() {
    
    this.data = (E[]) new Object[10];
    this.top = 0;
    
  }
  

}
