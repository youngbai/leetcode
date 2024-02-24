package edu.neu.leetcode.day4StackQueue;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bloomberg_Candy_Crush_1D {

    /*
    Question:
    https://leetcode.com/discuss/interview-question/380650/Bloomberg-or-Phone-or-Candy-Crush-1D

    Thinking:
    - Stack
     */
    class Candy_Crush_1D {

        public String crush(String s) {
            Deque<int[]> stack = new LinkedList<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (stack.isEmpty()) {
                    stack.push(new int[]{c, 1});
                } else {
                    if (c == stack.peek()[0]) {
                        stack.peek()[1]++;
                    } else {
                        // pop consecutive chars
                        if (stack.peek()[1] >= 3) {
                            stack.pop();
                            i--;
                        } else {
                            // new char
                            stack.push(new int[]{c, 1});
                        }
                    }
                }
            }

            if (!stack.isEmpty() && stack.peek()[1] >= 3) {
                stack.pop();
            }

            StringBuilder sb = new StringBuilder();
            for (int[] item : stack) {
                char c = (char) item[0];
                int count = item[1];
                while (count > 0) {
                    sb.append(c);
                    count--;
                }
            }

            return sb.reverse().toString();
        }

    }


    @Test
    public void test1() {
        Candy_Crush_1D candy = new Candy_Crush_1D();
        assertEquals("ACC", candy.crush("ABBBCC"));
        assertEquals("A", candy.crush("ABCCCBB"));
        assertEquals("C", candy.crush("ABCCCBBAAC"));
        assertEquals("", candy.crush("aabbccddeeedcba"));
        assertEquals("cd", candy.crush("aabbbacd"));
        assertEquals("acd", candy.crush("aaabbbacd"));
        assertEquals("abbd", candy.crush("baaabbbabbccccd"));
    }


}
