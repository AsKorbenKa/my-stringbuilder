public class StringFilter implements Filter<String> {
    @Override
    public String apply(String data) {
        return data.length() > 5 ? data : null;
    }
}
