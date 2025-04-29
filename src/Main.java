import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        MyLinkedList<Integer> intList = new MyLinkedList<>();
        intList.add(10);
        intList.add(20);
        intList.add(30);

        intList.printList();
        System.out.println("Contains 20? " + intList.contains(20));
        intList.remove(20);
        intList.printList();

        MyLinkedList<String> strList = new MyLinkedList<>();
        strList.add("Hello");
        strList.add("World");

        strList.printList();
        System.out.println("Contains 'Java'? " + strList.contains("Java"));

        Map<ClassWithWrongHash, Integer> experiment = new HashMap<>();
        for (int i = 0; i < 11; i++) {
            ClassWithWrongHash key = new ClassWithWrongHash(1);
            experiment.put(key, i);
        }

        Field tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);

        Object[] table = (Object[]) tableField.get(experiment);

        for (Object node : table) {
            if (node != null) {
                System.out.println("Тип ноды: " + node.getClass());
            }
        }
    }
}
