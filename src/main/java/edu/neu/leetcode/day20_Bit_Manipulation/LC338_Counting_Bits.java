package edu.neu.leetcode.day20_Bit_Manipulation;

public class LC338_Counting_Bits {

    /*
    Thinking:
    - DP
    - transition function
      x>=2, k=2^1,2^2,2^3,...
      f(x)=1+f(x-k)

    Algo:
    f(0)=0, f(1)=1
    k=2,i=2
    while i < N+1:
        while i < N+1 && i < k*2:
            f(i)=1+f(i-k)
            i++
        k *= 2

    Example:
    f(0)=0
    f(1)=1

    f(2)=1+f(0)=1		k=2^1
    f(3)=1+f(1)=2

    f(4)=1+f(0)=1		k=2^2
    f(5)=1+f(1)=2
    f(6)=1+f(2)=2
    f(7)=1+f(3)=3

    f(8)=1+f(0)=1		k=2^3
    f(9)=1+f(1)=2
    f(10)=1+f(2)=1

    Time:  O(N)
    Space: O(1)
     */
    class Solution1_DP {
        public int[] countBits(int n) {
            if (n == 0) return new int[]{0};
            if (n == 1) return new int[]{0, 1};

            int[] res = new int[n + 1];
            res[0] = 0;
            res[1] = 1;
            int i = 2, k = 2;
            while (i < res.length) {
                while (i < res.length && i < k * 2) {
                    res[i] = 1 + res[i - k];
                    i++;
                }
                k *= 2;
            }
            return res;
        }
    }

    /*
    Thinking: (Best)
    - DP
    - transition function
      x =(1001011101)
      x'=(100101110)
      f(x)=f(x/2)+(x mod 2)

    Algo:
    f(0)=0
    for x=1 to N+1:
        f(x)=f(x/2)+(x mod 2)

    Time:  O(N)
    Space: O(1)
     */
    class Solution2_DP {
        public int[] countBits(int n) {
            int[] res = new int[n + 1];
            for (int x = 1; x < res.length; x++) {
                // x / 2 is x >> 1 and x % 2 is x & 1
                res[x] = res[x >> 1] + (x % 2);
            }
            return res;
        }
    }

    /*
    Thinking:
    - `x & (x - 1)` remove the right most 1 digit
    - res[original number] = res[original number removed the right most 1] + 1

    Time:  O(N)
    Space: O(1)
     */
    class Solution3_BitManipulation {
        public int[] countBits(int n) {
            int[] res = new int[n + 1];
            for (int x = 1; x < n + 1; x++) {
                res[x] = res[x & (x - 1)] + 1;
            }
            return res;
        }
    }

}
