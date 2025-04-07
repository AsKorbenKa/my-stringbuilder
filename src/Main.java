public class Main {
    public static void main(String[] args) {
        // Добавляем первую строку и сохраняем ее как snapshot
        MyStringBuilder myStringBuilder = new MyStringBuilder();
        myStringBuilder.append("Hello, world! ");
        myStringBuilder.save();

        // Добавляем еще одну строку и выводим получившееся значение
        myStringBuilder.append("Hello, another world!");
        System.out.println(myStringBuilder.get());

        // Откатываемся к перовой версии
        myStringBuilder.undo();
        System.out.println(myStringBuilder.get());
    }
}
