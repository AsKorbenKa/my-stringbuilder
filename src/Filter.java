import java.util.Arrays;

public interface Filter<T> {
    T apply(T data);

    default T[] filter(T[] array) {
        return (T[]) Arrays.stream(array).map(this::apply).toArray();
    }
}
