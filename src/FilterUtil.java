public class FilterUtil implements Filter {
    @Override
    public Object apply(Object o) {
        if (o instanceof String str) {
            return str.length() > 5 ? str : null;
        } else if (o instanceof Integer num) {
            return num > 10 ? num : null;
        } else if (o instanceof Double doub) {
            return doub += 1;
        } else if (o instanceof Boolean bool) {
            return !bool;
        }
        return null;
    }
}
