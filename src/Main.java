import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Filter<String> stringFilter = new StringFilter();
        String[] strings = {"dsfsfsdfs", "yfguyf", "dffchfchhhh", "hd"};
        System.out.println(Arrays.toString(stringFilter.filter(strings)));

        Filter<Integer> integerFilter = new IntegerFilter();
        Integer[] ints = {3, 55, 23, 56};
        System.out.println(Arrays.toString(integerFilter.filter(ints)));
    }
}
