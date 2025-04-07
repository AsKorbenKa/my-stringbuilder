public class Main {
    public static void main(String[] args) {
        MyStringBuilder myStringBuilder = new MyStringBuilder();
        myStringBuilder.append("Hello, world! ");
        System.out.println(myStringBuilder.get());

        myStringBuilder.append("Hello, another world!");
        System.out.println(myStringBuilder.get());

        myStringBuilder.undo();
        System.out.println(myStringBuilder.get());
    }
}
