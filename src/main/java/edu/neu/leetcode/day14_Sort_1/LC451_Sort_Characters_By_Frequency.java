package edu.neu.leetcode.day14_Sort_1;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class LC451_Sort_Characters_By_Frequency {

    /*
    Thinking
    - bucket sort (freq as index)

    Example:
    s = ababcadd
    r = aaabbddc

    map(char->freq)
    a 3
    b 2
    d 2
    c 1

    bucket(freq as index)
    0 null
    1 [c]
    2 [b,d]
    3 [a]

    r = aaabbddc

    Time:  O(N)
    Space: O(N)
     */
    class Solution1_Bucket_Sort {
        public String frequencySort(String s) {
            // map(char->freq)
            Map<Character, Integer> map = new HashMap<>();  // S:O(N)
            for (char c : s.toCharArray())  // T:O(N)
                map.put(c, map.getOrDefault(c, 0) + 1);

            // bucket[freq] = [char,...]
            List<Character>[] buckets = new List[s.length() + 1];   // S:O(N)
            for (char key: map.keySet()) {  // T:O(N)
                int freq = map.get(key);
                if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
                buckets[freq].add(key);
            }

            // bucket sort
            StringBuilder sb = new StringBuilder();
            for (int i = buckets.length - 1; i >= 0; i--){  // T:O(N)
                if (buckets[i] != null) {
                    for (char c: buckets[i]) {
                        for (int freq = i; freq > 0; freq--) {
                            sb.append(c);
                        }
                        // we can change this for loop as follows with repeat()
                        // sb.append(String.valueOf(c).repeat(i));
                    }
                }
            }
            return sb.toString();
        }
    }
}
