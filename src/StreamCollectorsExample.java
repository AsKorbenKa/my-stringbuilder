import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        System.out.println(groupByOrderName(orders));
        System.out.println(groupByOrderNameAndGetSUM(orders));
        System.out.println(groupByOrderNameAndGetSUMAndSort(orders));
        System.out.println(mostExpansiveProduct(orders));
        mostExpansiveProductAndSUM(orders);
    }

    private static Map<String, List<Order>> groupByOrderName(List<Order> orders) {
        return orders.stream().collect(Collectors.groupingBy(Order::getProduct));
    }

    private static Map<String, Double> groupByOrderNameAndGetSUM(List<Order> orders) {
        return orders.stream().collect(Collectors.groupingBy(Order::getProduct,
                Collectors.summingDouble(Order::getCost)));
    }

    private static LinkedHashMap<String, Double> groupByOrderNameAndGetSUMAndSort(List<Order> orders) {
        return orders.stream().collect(Collectors.groupingBy(Order::getProduct,
                Collectors.summingDouble(Order::getCost)))
                .entrySet().stream()
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    private static List<Order> mostExpansiveProduct(List<Order> orders) {
        return orders.stream().sorted(Comparator.comparing(Order::getCost).reversed()).limit(3).toList();
    }

    private static void mostExpansiveProductAndSUM(List<Order> orders) {
        List<Order> orders1 = orders.stream().sorted(Comparator.comparing(Order::getCost).reversed()).limit(3)
                .toList();

        double sumOfOrders = orders1.stream().map(Order::getCost).reduce(0.0, Double::sum);

        System.out.println(orders1);
        System.out.println(sumOfOrders);
    }
}
