package edu.neu.leetcode.day4StackQueue;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC895_Max_Frequency_Stack {

    /*
    Thinking:
    When we meet ordering elements by frequency, Priority Queue is a straightforward solution.

    Algo:
    - data structure:
        InsertSequence = 0
        PQ<[Element, Frequency, InsertSequence]>
        Map<Element, Frequency>
    - push(x): O(logn)
        Freq = map.get(x)
        Freq++
        PQ.offer((x, Freq, InsertSequence++))

    - poll(): O(logn)
        x = PQ.peek()[0]
        Freq = map.get(x)
        Freq--
        return PQ.poll()[0]

    Time : O(logn) - PQ.offer(), PQ.poll()
    Space: O(n) - PQ and Map store every element
     */
    class FreqStack1 {

        int seq;    // insertSequence
        PriorityQueue<int[]> pq;    // int[] - (element, frequency, insertSequence)
        Map<Integer, Integer> map;  // map<element, frequency>

        public FreqStack1() {
            seq = 0;
            pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? b[2] - a[2] : b[1] - a[1]);
            map = new HashMap<>();
        }

        // O(logn)
        public void push(int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            pq.offer(new int[]{x, map.get(x), seq++});
        }

        // O(logn)
        public int pop() {
            int x = pq.peek()[0];   // x is mostFreqElement
            map.put(x, map.get(x) - 1);
            return pq.poll()[0];
        }

    }

    @Test
    public void testFreqStack1() {
        FreqStack1 freqStack = new FreqStack1();
        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]

        int res;
        res = freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        assertEquals(5, res);
        res = freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
        assertEquals(7, res);
        res = freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        assertEquals(5, res);
        res = freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
        assertEquals(4, res);
    }

    /*
    Thinking:

    Algo:
    - data structure:
        maxFreq = 0
        Map<Element, Frequency> map
        Map<Frequency, Stack<Element>> freqToElemMap

    - push(x): O(1)
        freq = map.getOrDefault(x, 0) + 1
        map.put(x, freq)
        stack = freqToElemMap.computeIfAbsent(freq, v->new Stack())
        stack.push(x)
        maxFreq = max(freq, maxFreq)

    - poll(): O(1)
        stack = freqToElemMap.get(maxFreq)
        element = stack.pop()
        if stack is empty:
            maxFreq--
        map.get(element)--
        return element

    Time : O(1) - Stack<Element>.push(), pop()
    Space: O(n) - Map<Element, Frequency>, Map<Frequency, Stack<Element>>

     */
    class FreqStack2 {

        int maxFreq;
        Map<Integer, Integer> map;  // Map<Element, Frequency>
        Map<Integer, Deque<Integer>> freqToElementMap;  // Map<Frequency, Stack<Element>>

        public FreqStack2() {
            maxFreq = 0;
            map = new HashMap<>();
            freqToElementMap = new HashMap<>();
        }

        public void push(int x) {
            int freq = map.getOrDefault(x, 0) + 1;
            map.put(x, freq);
            freqToElementMap.computeIfAbsent(freq, v -> new ArrayDeque<Integer>()).push(x);
            maxFreq = Math.max(freq, maxFreq);
        }

        public int pop() {
            Deque<Integer> stack = freqToElementMap.get(maxFreq);
            int element = stack.pop();
            if (stack.isEmpty()) maxFreq--;
            map.put(element, map.get(element) - 1);
            return element;
        }

    }

    @Test
    public void testFreqStack2() {
        FreqStack2 freqStack = new FreqStack2();
        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]

        int res;
        res = freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        assertEquals(5, res);
        res = freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
        assertEquals(7, res);
        res = freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        assertEquals(5, res);
        res = freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
        assertEquals(4, res);
    }


}
