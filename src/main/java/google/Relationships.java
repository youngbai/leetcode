package google;

import edu.neu.leetcode.day14_Sort_1.RadixSort;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Relationships {

/*
Question:
1. 给你一个relationship list， 以及一个pattern, 以及starting person和ending person, 需要回答有没有可能output一个符合pattern的person的list
例子：
relationships: [0,1,'F'], [1,2,'E'],[2,3,'E'](是bidirectional的)
pattern: "FEE" (F是friend的意思，E是enemy的意思)
starting person:0
ending person:3
expected output: true (因为[0, 1, 2, 3] 符合 "FEE")

Thinking:
- backtracking

e.g.
Graph:
0 - F: 1
  - E:

1 - F:
  - E: 2

2 - F:
  - E: 3

target = 3
current index  r   visited
0	      0	   F   1
1	      1    E   12
2		  2    E   123
3		  3

Time: O(n*(n-1)/2) ~ O(n^2)
Space: O(length of pattern)

 */
    class Solution1_Backtracking {

        public boolean findRelation(String[][] relationships, String pattern, String start, String end) {
            // build Graph
            Map<String, Map<String, List<String>>> G = new HashMap<>();
            for (String[] relation : relationships) {
                String s = relation[0], e = relation[1], r = relation[2];
                G.computeIfAbsent(s, x -> new HashMap<>()).computeIfAbsent(r, x -> new ArrayList<>()).add(e);
                G.computeIfAbsent(e, x -> new HashMap<>()).computeIfAbsent(r, x -> new ArrayList<>()).add(s);
            }

            // backtracking
            Set<String> visited = new HashSet<>();
            return dfs(G, visited, pattern, start, end, 0);
        }

        private boolean dfs(Map<String, Map<String, List<String>>> G, Set<String> visited, String pattern, String current, String target, int index) {
            if (pattern.length() == index) return current.equals(target);

            // try other candidates
            String r = pattern.substring(index, index + 1);
            for (String u : G.getOrDefault(current, new HashMap<>()).getOrDefault(r, new ArrayList<>())) {
                if (!visited.contains(u)) {
                    visited.add(u);
                    if (dfs(G, visited, pattern, u, target, index + 1)) return true;
                    visited.remove(u);
                }
            }
            return false;
        }
    }


    @Test
    public void test1() {
        String[][] relationships = {{"0", "1", "F"}, {"1", "2", "E"}, {"2", "3", "E"}};

        String pattern = "FEE";
        String starting = "0";
        String ending = "3";

        Solution1_Backtracking solution = new Solution1_Backtracking();
        assertTrue(solution.findRelation(relationships, pattern, starting, ending));
    }


    @Test
    public void test2() {
        String[][] relationships = {{"0", "1", "F"}, {"1", "2", "E"}, {"2", "3", "E"},
                {"0", "3", "F"}, {"0", "4", "F"}, {"3", "4", "E"}};

        String pattern = "FEE";
        String starting = "0";
        String ending = "2";

        Solution1_Backtracking solution = new Solution1_Backtracking();
        assertTrue(solution.findRelation(relationships, pattern, starting, ending));
    }


}
