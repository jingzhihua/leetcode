import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) throws Exception {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "111");

        new HashSet<Integer>().add(1);

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
        concurrentHashMap.put("aa", "a");


    }
}
