/**
 * A test class required to verify the type of node in the HashMap.
 */
public class ClassWithWrongHash {
    /**
     * It can be equal to any value.
     * It is only necessary to create an object of the class.
     */
    int value;

    /**
     * Constructor of a class specifying any number.
     *
     * @param value
     */
    public ClassWithWrongHash(int value) {
        this.value = value;
    }

    /**
     * An incorrectly redefined method that always returns false.
     */
    @Override
    public boolean equals(Object o) {
        return false;
    }

    /**
     * An incorrectly redefined method that always returns 1.
     * This is necessary to create collisions in the HashMap.
     */
    @Override
    public int hashCode() {
        return 1;
    }
}
