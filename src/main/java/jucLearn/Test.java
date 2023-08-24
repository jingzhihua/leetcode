package jucLearn;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("a", "a");

//        List<Mall> malls = List.of(new Mall("jingdong"), new Mall("taobao"), new Mall("tianmao"));
//        String productName = "mysql";
//
//        long start = System.currentTimeMillis();
//        List<String> collect = malls.stream()
//                .map(mall -> String.format("%s in %s: %.2f", productName, mall.getMallName(), mall.calcPrice(productName)))
//                .collect(Collectors.toList());
//        collect.forEach(System.out::println);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//
//        start = System.currentTimeMillis();
//        List<String> res = malls.stream()
//                .map(mall -> CompletableFuture.supplyAsync(() -> String.format("%s in %s: %.2f", productName, mall.getMallName(), mall.calcPrice(productName))))
//                .collect(Collectors.toList())
//                .stream().map(CompletableFuture::join).collect(Collectors.toList());
//        res.forEach(System.out::println);
//        end = System.currentTimeMillis();
//        System.out.println(end - start);
    }
}

@AllArgsConstructor
@Getter
class Mall {
    private String mallName;

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(1);
    }
}
