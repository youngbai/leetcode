package edu.neu.leetcode.day10_Divide_and_Conquer;

public class LC50_Pow_x_n {

    /*
    Thinking:
    - recursive

    Algo:
    - n = 0, return 1
    - n > 0:
        if n is even, x^n = (x^2)^(n/2) = (x*x)^(n/2)
        if n is odd, x^n = x * x^(n-1), then x^(n-1) is back to even situation
    - n < 0:
        x^n = (1/x)^(-n)  // but -n can cause overflow
            = (1/x) * (1/x)^-(n+1)  // then (1/x)^-(n+1) is back to n>0 situation

    Corner Case: if n is Integer.MIN_VALUE, then -n is greater than Integer.MAX_VALUE
    Integer.MIN_VALUE = -2147483648
    Integer.MAX_VALUE =  2147483647

    Time:  O(logn)
    Space: O(logn) recursive stack
     */
    class Solution1_recursive {

        public double myPow(double x, int n) {
            if (n == 0) return 1;

            if (n < 0) {
                // because n could be Integer.MIN_VALUE, then -n is greater than Integer.MAX_VALUE
                return 1 / x * myPow(1 / x, -(n + 1));
            }

            if (n % 2 == 0) {
                // even
                return myPow(x * x, n / 2);
            } else {
                // odd
                return x * myPow(x * x, n / 2);
            }
        }
    }

}
