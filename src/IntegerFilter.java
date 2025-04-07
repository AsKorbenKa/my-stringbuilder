public class IntegerFilter implements Filter<Integer> {
    @Override
    public Integer apply(Integer data) {
        return data * 2;
    }
}
