package datastructure;

import domain.State;

/**
 * List is a own data structure to replace ArrayList in NfaClass
 *
 * @author strajama
 */
public class StateList {

    private State[] states;

    /**
     * Creates new List with array of the size 0
     */
    public StateList() {
        this.states = new State[0];
    }

    /**
     * Adds a new State to the List by creating a new array of States which is
     * one bigger than the current array, copying all the States there and
     * adding given State to the end of the new array. Finally array of States
     * is replaced by new array
     *
     * @param state - State that is added to the List
     */
    public void add(State state) {
        State[] newStates = new State[states.length + 1];
        for (int i = 0; i < this.states.length; i++) {
            newStates[i] = this.states[i];
        }
        newStates[states.length] = state;
        this.states = newStates;
    }

    /**
     * Returns array of States on the List
     *
     * @return array of States
     */
    public State[] getStates() {
        return states;
    }

    /**
     * Checks if given State is on the List
     *
     * @param state - State that is checked
     * @return true if State is on the List and otherwise false
     */
    public boolean contains(State state) {
        for (State s : this.states) {
            if (s == state) {
                return true;
            }
        }
        return false;
    }

}
