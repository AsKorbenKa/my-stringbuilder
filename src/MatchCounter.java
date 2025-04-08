import java.util.HashMap;
import java.util.Map;

public class MatchCounter {
    public static <T> Map<T, Integer> countOccurrences(T[] array) {
        Map<T, Integer> matchMap = new HashMap<>();
        for (T element : array) {
            matchMap.put(element, matchMap.getOrDefault(element, 0) + 1);
        }
        return matchMap;
    }
}
