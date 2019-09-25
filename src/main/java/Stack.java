/** File Header: This file's purpose is to implement a stack that can support more methods that can be used on a stack. They are expanding on the basic operations in a stack: pop, push, and isEmpty.
 * Name: Samruddhi Hande Email: shande@ucsd.edu **/
import java.util.EmptyStackException;

/** Class Header: As mentioned in the file header, this class implements multiple methods that supports multiple stack operations. There are variables such as loadFactor and shrinkFactor that enable our stack to grow or shrink based on certain conditions. The stack is represented by a generic element array. We also construct three types of stacks. **/
class Stack<E>
{
    float loadFactor; //load factor - once the stack is this percent full, the capacity will double.
    float shrinkFactor; //shrink factor - once the stack is this percent full, the capacity will half.
    int top; // Index of the top element
    E arr[]; //stack
    int capacity; //number of elements stack can hold.
    boolean canGrow; //array can grow (no fixed capacity, based on load factor)
    boolean canShrink; //array can shrink (based on shrink factor)

    /** Construct stack with a fixed capacity **/
    Stack(int capacity)
    {
        //To be implemented
        this.capacity = capacity;
        this.loadFactor = 0;
        this.shrinkFactor = 0;

        this.canGrow = false;
        this.canShrink = false;

        this.arr = (E[]) new Object[capacity];
        this.top = -1;
    }

    /** Construct stack with a growable capacity **/
    Stack(int capacity, float loadFactor)
    {
        //To be implemented
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.shrinkFactor = 0;
 
        this.canGrow = true;
        this.canShrink = false;

        this.arr = (E[]) new Object[capacity];
        this.top = -1;
    }

    /** Construct stack with growable as well as shrinkable capacity **/
    Stack(int capacity, float loadFactor, float shrinkFactor)
    {
        //To be implemented
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.shrinkFactor = shrinkFactor;
      
        this.canGrow = true;
        this.canShrink = true;

        this.arr = (E[]) new Object[capacity];
        this.top = -1;
    }

    /** @return boolean - Check to see if stack is empty. Return true if it is empty. Else return false.**/
    boolean isEmpty()
    {
        //To be implemented
        if (top == -1) {
          return true;
        }
        return false; // to be changed
    }

    /** @return capacity - Returns how many elements the stack can hold. **/
    int getCapacity(){
        //To be implemented
        return capacity; // to be changed
    }

    /** Adds an element to the top of the stack. Also can grow size of stack based on load factor.
     * @param x - element to be added to top of stack **/
    void push(E x) throws StackOverflowError, NullPointerException
    {
        //To be implemented
        //if stack is full, throw StackOverflowError
        //if user tries to push null, throw NullPointerException
        if (isFull() && canGrow == false) {
          throw new StackOverflowError();
        } else if (x == null) {
          throw new NullPointerException();
        }
        
        top++;
        
        int loadFactorIndex = (int) Math.ceil(capacity*loadFactor);
        if (canGrow) {
          if (top >= loadFactorIndex) {
            capacity = capacity * 2;
          }
        }
        E[] newArr = (E[]) new Object[capacity];
        for (int i = 0; i < currentSize(); i++) {
          newArr[i] = arr[i];
        }
        this.arr = newArr;
        arr[top] = x;
    }

    /** Removes object at top of stack and returns it. It can also shrink size of stack based on shrink factor.
     * @return E object (in this algorithm, variable temp) that was removed from top of stack. **/
    E pop() throws EmptyStackException
    {
        //To be implemented
        if (isEmpty()) {
          throw new EmptyStackException();
        }
        
        E temp = arr[top];
        arr[top] = null;
        top--;
        
        int shrinkFactorIndex = (int) Math.ceil(capacity*shrinkFactor);
        if (canShrink) {
          if (top >= shrinkFactorIndex) {
            capacity = capacity/2;
          }
        }
        E[] newArr = (E[]) new Object[capacity];
        for (int i = 0; i < capacity; i++) {
          newArr[i] = arr[i];
        }
        this.arr = newArr;
        return temp ;  // to be changed
    }
    
    /** Returns element that is at top of stack but does not remove it from stack
     * @return E (arr[top]) - top element **/
    E peek() throws EmptyStackException{
        if (isEmpty()) {
          throw new EmptyStackException();
        }
        
        return arr[top];  // to be changed
    }
    
    /** Remove all elements from stack. Does not change stack capacity **/
    void clear(){
        //To be implemented
        for (int i = 0; i < capacity; i++) {
          arr[i] = null;
        }

        top = -1;
    }

    /** @return boolean - Check is stack is full. Return true if it full. Else return false. **/
    boolean isFull(){
        //To be implemented
        if (top == capacity - 1) {
          return true;
        }
        return false;  // to be changed
    }

    /** @return the current number of elements of the stack **/
    int currentSize(){
        //To be implemented
        return (top + 1);
    }

    /** Pop a certain number of elements from the stack and return them as an array.
     * @param k - Pop this many elements from the stack and return them as an array. If k is greater than the current size, then return all the elements in the stack as an array.
     * @return array of popped elements **/
    E[] multiPop(int k) throws EmptyStackException{
        //To be implemented
        if (isEmpty()) {
          throw new EmptyStackException();
        }
        E[] poppedElementArray = (E[]) new Object[k];
        int index = 0;
        if (k > currentSize()) {
          for (int i = 0; i < currentSize(); i++) {
            poppedElementArray[index] = pop();
            index++;
          }
        } else {
            for (int i = 0; i < k; i++) {
              poppedElementArray[index] = pop() ;
              index++;
            }
        }
        return poppedElementArray; // to be changed
    }

    /** Add all elements from array at the top of the stack one by one
     * @param - the array of elements to be pushed to stack **/
    void multiPush(E[] arr) throws StackOverflowError, NullPointerException{
        //To be implemented
        if (isFull() && canGrow == false) {
          throw new StackOverflowError();
        }
        for (int i = 0; i < arr.length; i++) {
          if (arr[i] == null) {
            throw new NullPointerException();
          }
        }
        for (int i = 0; i < arr.length; i++) {
          push(arr[i]);
        }
    }

    /** Reverse all elements in the stack **/
    void reverse() throws EmptyStackException{
        //To be implemented
        if (isEmpty()) {
          throw new EmptyStackException();
        }
        for (int i = 0; i <= top/2; i++) {
          E temp = peek();
          arr[top - i] = arr[i];
          arr[i] = temp;
        }
    }

    /** Adds element to top of stack, but only if the current element at the top is not the same.
     * @param x - element to be pushed/not pushed.
     * @return true if the element is not the same and we can push it (success), and false if otherwise. **/
    boolean pushUnique(E x) throws StackOverflowError, NullPointerException{
        //To be implemented
        if (isFull() && (canGrow == false)) {
          throw new StackOverflowError();
        }
        for (int i = 0; i < currentSize(); i++) {
          if (arr[i].equals(null)) {
            throw new NullPointerException();
          }
        }
        if (isEmpty()) {
            push(x);
            return true;
        }
        if (!peek().equals(x)) {
          push(x);
          return true;
        }
        return false; // to be changed
    }

    /** Searches for an element based on its distance from the top element.
     * @param x - element to be searched for
     * @return distance from top (1-based position) **/
    int search(E x) throws EmptyStackException{
        //To be implemented
        if (isEmpty()) {
          throw new EmptyStackException();
        }
        int distance = 0;
        for (int i = 0; i < currentSize(); i++) {
          if (arr[i].equals(x)) {
            distance = top - i + 1;
            return distance;
          } 
        }
        return -1;   // to be changed
    }

}
