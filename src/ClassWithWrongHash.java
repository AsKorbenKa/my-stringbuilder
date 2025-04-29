public class ClassWithWrongHash {
    int value;

    public ClassWithWrongHash(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
