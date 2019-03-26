package datastructure;

/**
 * Stack data structure for characters
 *
 * @author strajama
 */
public class CharStack implements Stack<Character> {

    private int top;
    private char[] chars;

    /**
     * Creates new CharStack for 10 characters
     */
    public CharStack() {
        this.top = -1;
        this.chars = new char[10];
    }

    /**
     * Pushes new character to the stack. If stack's array is full it will
     * create new bigger array for the stack
     *
     * @param t - character
     */
    @Override
    public void push(Character t) {
        this.top++;
        if (this.top > this.chars.length - 1) {
            biggerArray();
        }
        this.chars[this.top] = t;
    }

    /**
     * Pops the most recently pushed character from the stack
     *
     * @return character or null if stack is empty
     */
    @Override
    public Character pop() {
        if (empty()) {
            return null;
        }
        this.top--;
        return this.chars[this.top + 1];
    }

    /**
     * Peek which is the most recently pushed character in the stack
     *
     * @return character or null if stack is empty
     */
    @Override
    public Character peek() {
        if (this.empty()) {
            return null;
        }
        return chars[top];
    }

    /**
     * Check if stack is empty
     *
     * @return true if stack is empty, otherwise false;
     */
    @Override
    public boolean empty() {
        return this.top < 0;
    }

    /**
     * Creates bigger array for the stack
     */
    private void biggerArray() {
        char[] big = new char[this.chars.length * 2];
        for (int i = 0; i < this.chars.length; i++) {
            big[i] = this.chars[i];
        }
        this.chars = big;
    }
}
