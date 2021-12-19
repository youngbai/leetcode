package edu.neu.leetcode.day4StackQueue;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OA_Uber_HashMap_IncrKeyValue {

    /*
    Formulas:
    1. map.key + keyInc = myHashMap.key
    2. map.value + valueInc = myHashMap.value
     */
    class EnhancedHashMap {

        int keyInc;
        int valueInc;
        Map<Integer, Integer> map = new HashMap<>();

        public void insert(int k, int v) {
            map.put(k - keyInc, v - valueInc);
        }

        public int get(int k) {
            int key = k - keyInc;
            if (!map.containsKey(key)) return -1;
            int value = map.get(key);
            return value + valueInc;
        }

        public void addToKey(int x) {
            keyInc += x;
        }

        public void addToValue(int y) {
            valueInc += y;
        }
    }

    @Test
    public void test1() {
        EnhancedHashMap map = new EnhancedHashMap();
        map.insert(1, 2);
        map.insert(2, 3);
        map.addToValue(2);
        map.addToKey(1);
        assertEquals(5, map.get(3));
    }

    @Test
    public void test2() {
        EnhancedHashMap map = new EnhancedHashMap();
        map.insert(1, 2);
        map.addToValue(2);
        assertEquals(4, map.get(1));
        map.insert(2, 3);
        map.addToKey(1);
        map.addToValue(-1);
        assertEquals(2, map.get(3));
    }

}
