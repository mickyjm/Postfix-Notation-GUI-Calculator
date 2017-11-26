/**
 * Stack 211 Interface
 * @author Michael Mangrobang
 *
 **/
public interface Stack211<E> {
  
  boolean empty(); // Checks if stack is empty. Returns true or false.
  
  E peek(); // Returns value at top of the stack.
  
  E pop(); // Removes and returns top value.
  
  E push(E item); // Adds a value to to the top of the stack.
  
} // end Stack211 interface.
