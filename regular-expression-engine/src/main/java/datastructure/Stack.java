package datastructure;

/**
 * Interface for own Stack-data structures
 *
 * @author strajama
 */
public interface Stack<T> {

    /**
     * Pushes new object to the stack
     *
     * @param t - new object
     */
    void push(T t);

    /**
     * Pops the most recently pushed object from the stack
     *
     * @return object or null if stack is empty
     */
    T pop();

    /**
     * Peek which is the most recently pushed object in the stack
     *
     * @return object or null if stack is empty
     */
    T peek();

    /**
     * Check if stack is empty
     *
     * @return true if stack is empty, otherwise false;
     */
    boolean empty();
}
