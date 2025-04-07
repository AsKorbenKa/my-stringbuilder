import java.util.ArrayList;
import java.util.List;

public class StringBuilderHistory {
    private final List<StringBuilderSnapshot> snapshots = new ArrayList<>();

    public void add(StringBuilderSnapshot snapshot) {
        snapshots.add(snapshot);
    }

    public StringBuilderSnapshot get() {
        return snapshots.getLast();
    }
}
