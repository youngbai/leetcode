package edu.neu.leetcode.day3Heap;

/*
E.g.
    0
 1    2
3 4  5

parent:         i
left child:  2 * i + 1
right child: 2 * i + 2

left or right child:    i
parent             :  (i-1)/2


size -> up next empty position

 */
public class MinPQ {

    int[] pq;
    int size;

    public MinPQ(int capacity) {
        pq = new int[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void offer(int num) {
        pq[size] = num;     // add to the end
        shiftUp(size);      // shift up
        size++;             // size++
    }

    public Integer poll() {
        int value = pq[0];      // return root
        pq[0] = pq[size - 1];   // put end to root
        size--;                 // size--
        if (size > 1) shiftDown(0); // shift down
        return value;
    }

    public Integer peek() {
        if (size == 0) return null;
        return pq[0];
    }

    private void shiftUp(int index) {
        int parent = (index - 1) / 2;
        while (parent >= 0 && pq[parent] > pq[index]) {
            swap(parent, index);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void shiftDown(int index) {
        int min = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && pq[left] < pq[min]) min = left;
        if (right < size && pq[right] < pq[min]) min = right;
        if (min != index) {
            swap(min, index);
            shiftDown(min);
        }
    }

    private void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }


    public static void main(String[] args) {

        MinPQ pq = new MinPQ(10);
        int[] arr = {3, 2, 1, 7, 8, 4, 5,};
        for (int n : arr) {
            pq.offer(n);
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
