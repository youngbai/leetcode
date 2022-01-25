package edu.neu.leetcode;

import java.util.*;

public class KeyMethods {


    public static void main(String[] args) {

        Listof();

        MOD_1e9();

        Map_computeIfAbsent();

        Map_computeIfPresent();

        Inverse_Complement_Code();

    }

    private static void Inverse_Complement_Code() {
        int x = 100;
        System.out.printf("%d original code is %s\t\n", x, Integer.toBinaryString(x));
        System.out.printf("%d inverse code is %s\t\n", x, Integer.toBinaryString(~x));
        System.out.printf("%d complement code is %s\t\n", x, Integer.toBinaryString(-x));

        // complement code = inverse code + 1
        System.out.println((-x) == (~x + 1));
    }

    private static void Map_computeIfPresent() {
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

    private static void Map_computeIfAbsent() {
        Map<String, List<Integer>> map = new HashMap<>(Map.of("A", new ArrayList<>()));
        map.computeIfAbsent("A", k -> new ArrayList<>()).add(1);
        map.computeIfAbsent("A", k -> new ArrayList<>()).add(2);
        map.computeIfAbsent("A", k -> new ArrayList<>()).add(3);

        System.out.println(map.get("A"));
    }

    private static void MOD_1e9() {
        // MOD_1e9
        long MOD = (long) 1e9 + 7;
        System.out.println("MOD:" + MOD);
    }

    private static void Listof() {
        // List.of
        Set<Integer> set = new HashSet<>(List.of(1, 2));
        System.out.println("set:" + set);
    }

}
