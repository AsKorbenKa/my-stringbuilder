import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] strings = {"dsfsfsdfs", "yfguyf", "dffchfchhhh", "hd"};
        System.out.println(Arrays.toString(filter(strings, new FilterUtil())));
    }

    private static Object[] filter(Object[] array, FilterUtil filter) {
        return Arrays.stream(array).map(filter::apply).toArray();
    }
}
