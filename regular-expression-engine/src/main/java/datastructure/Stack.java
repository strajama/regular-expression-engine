package datastructure;

/**
 *
 * @author strajama
 */
public interface Stack <T> {

    void push(T t);

    T pop();

    T peek();
    
    boolean empty();
}
