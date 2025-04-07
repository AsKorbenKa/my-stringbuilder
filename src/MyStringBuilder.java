public final class MyStringBuilder {
    private final StringBuilder stringBuilder;
    private final StringBuilderHistory stringBuilderHistory;

    public MyStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.stringBuilderHistory = new StringBuilderHistory();
    }

    public void append(String string) {
        stringBuilder.append(string);
    }

    public String get() {
        return stringBuilder.toString();
    }

    // Сохраняем snapshot
    public void save() {
        stringBuilderHistory.add(new StringBuilderSnapshot(stringBuilder.toString()));
    }

    // Получаем последний snapshot и устанавливаем значение в stringBuilder
    public void undo() {
        stringBuilder.setLength(0);
        String value = stringBuilderHistory.get().value();
        stringBuilder.append(value);
    }
}
