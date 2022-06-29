package edu.neu.leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

public class KeyMethods {

    @Test
    public void Inverse_Complement_Code() {
        int x = 100;
        System.out.printf("%d original code is %s\t\n", x, Integer.toBinaryString(x));
        System.out.printf("%d inverse code is %s\t\n", x, Integer.toBinaryString(~x));
        System.out.printf("%d complement code is %s\t\n", x, Integer.toBinaryString(-x));

        // complement code = inverse code + 1
        System.out.println((-x) == (~x + 1));
    }

    @Test
    public void Map_computeIfPresent() {
        // Create a HashMap and add some values
        HashMap<String, Integer> wordCount = new HashMap<>();
        wordCount.put("Geeks", 1);
        wordCount.put("for", 2);
        wordCount.put("geeks", 3);

        // print HashMap details
        System.out.println("Hashmap before operation :\n " + wordCount);

        // provide new value for keys which is present
        // using computeIfPresent method
        wordCount.computeIfPresent("Geeks", (key, val) -> val + 100);

        // print new mapping
        System.out.println("HashMap after operation :\n " + wordCount);
    }

    @Test
    public void Map_computeIfAbsent() {
        Map<String, List<Integer>> map = new HashMap<>(Map.of("A", new ArrayList<>()));
        map.computeIfAbsent("A", k -> new ArrayList<>()).add(1);
        map.computeIfAbsent("A", k -> new ArrayList<>()).add(2);
        map.computeIfAbsent("A", k -> new ArrayList<>()).add(3);

        System.out.println(map.get("A"));
    }

    @Test
    public void MOD_1e9() {
        // MOD_1e9 =  1 * 10^9
        long MOD = (long) 1e9 + 7;
        System.out.println("MOD:" + MOD);
        System.out.println(MOD == 1_000_000_007L);
    }

    @Test
    public void Listof() {
        // List.of
        Set<Integer> set = new HashSet<>(List.of(1, 2));
        System.out.println("set:" + set);

        // remove 1
        set.remove(1);
        System.out.println("set:" + set);

        // Caution: List.of returns an immutable List, so it can not be changed
        List list = List.of(1, 2);
        System.out.println("list:" + list);

        list.remove(0); // throw java.lang.UnsupportedOperationException
        System.out.println("list:" + list);

    }

    @Test
    public void Math_log() {
        // Math.log returns the natural logarithm (base e) of a double value.
        System.out.println(Math.log(16));   // 2.772588722239781
        System.out.println(Math.log(16) / Math.log(4)); //  2.0
        System.out.println(Math.log(16) / Math.log(2)); //  4.0
    }

    @Test
    public void Collections_reverse() {
        List<String> colors = new ArrayList<>(Arrays.asList("RED", "BLUE", "BLACK"));

        Collections.reverse(colors);
        System.out.println(colors);
    }

    // todo
    @Test
    public void String_join() {
        List<String> list = List.of("1", "2", "3");
        String res = String.join(",", list);
        System.out.println(res);
    }


    // todo
    @Test
    public void Arrays_sort() {

    }

    // todo
    @Test
    public void compare() {

    }
}
