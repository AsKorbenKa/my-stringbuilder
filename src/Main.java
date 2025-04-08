public class Main {
    public static void main(String[] args) {
        String[] strings = {"a", "b", "b", "b", "c", "d", "d"};
        Integer[] ints = {1, 1, 1, 2, 3, 5, 5, 6};

        System.out.println(MatchCounter.countOccurrences(strings));
        System.out.println(MatchCounter.countOccurrences(ints));
    }
}
