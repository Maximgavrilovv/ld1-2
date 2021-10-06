
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PerfectNumber {

    protected enum State {
        DEFICIENT, PERFECT, ABUNDANT
    }

    public static Set<Integer> Divisors(int number) {

        Set<Integer> divisors = IntStream.rangeClosed(1,number)
                .boxed()
                .filter(i -> number % i == 0)
                .collect(Collectors.toSet());

        return divisors;
    }

    public static State Process(int n) {

        int sum = Divisors(n).stream().mapToInt(Integer::intValue).sum();
        sum = sum / 2;

        boolean PERFECT = sum == n;
        boolean DEFICIENT = sum < n;
        boolean ABUNDANT = sum > n;

        HashMap map = new HashMap();
        map.put(State.PERFECT, PERFECT);
        map.put(State.DEFICIENT, DEFICIENT);
        map.put(State.ABUNDANT, ABUNDANT);

        var q = getKeysByValue(map, true);
        State w = (State) getKeyByValue(map, true);

        return w;
//        if (sum == n)
//        {
//            return State.PERFECT;
//        }
//        else if(sum < n)
//        {
//            return State.DEFICIENT;
//        }
//        else
//        {
//            return State.ABUNDANT;
//        }
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static <T, E> Set<T> getKeysByValue(HashMap<T, E> map, E value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(HashMap.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++){
            System.out.println(Process(i) + " : " + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
    }
}