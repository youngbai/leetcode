package edu.neu.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.ArrayDeque;

public class PlayGround {

    @Test
    public void test() {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        StringBuilder sb = new StringBuilder();
        for (int i : stack) sb.append(i);
        System.out.println(sb.reverse());
    }

}
