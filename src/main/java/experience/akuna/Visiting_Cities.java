package experience.akuna;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Visiting_Cities {

    /*
    Question: https://www.chegg.com/homework-help/questions-and-answers/2-visiting-cities-number-cities-row-two-bus-lines-go--visit-cities-order-one-may-take-long-q95722840

    test cases:
    red=[2, 3, 4], blue=[3, 1, 1], blueCost=2, ====> ans=[0, 2, 5, 6]
    red=[40, 20],  blue=[30, 25],  blueCost=5, ====> ans=[0, 35, 55]

    Thinking:
    - for each city, there are 4 ways
      - stay on red, stay on blue
      - red->blue, blue->red
    - problem is current best answer does not lead to global best answer
      so, we calculate best previous red cost, best previous blue cost,
      which are provided to calculate the cost of next city
    - therefore, current answer depends on two previous answers ===> DP problem

    Algo:
     initially, preRedCost = 0, preBlueCost = blueCost, because blue start with a blueCost

     curRedCost in ith city = min {
        preRedCost + red[i-1]       # stay on red
        preBlueCost + red[i-1]      # switch from blue to red
     }

     curBlueCost in ith city = min {
        preRedCost + blueCost + blue[i-1]   # switch from red to blue
        preBlueCost + blue[i-1]             # stay on blue
     }

     curCost in ith city = min(curRedCost, curBlueCost)

     then:
        preRedCost = curRedCost
        preBlueCost = curBlueCost

     Time:  O(N)
     Space: O(1)
     */
    class Solution1 {

        public int[] minCost(int[] red, int[] blue, int blueCost) {
            int N = red.length + 1;
            int[] res = new int[N];

            int preRedCost = 0, preBlueCost = blueCost;
            for (int i = 1; i < N; i++) {
                int curRedCost = Math.min(preRedCost + red[i - 1], preBlueCost + red[i - 1]);
                int curBlueCost = Math.min(preRedCost + blueCost + blue[i - 1], preBlueCost + blue[i - 1]);

                int curCost = Math.min(curRedCost, curBlueCost);
                res[i] = curCost;
                preRedCost = curRedCost;
                preBlueCost = curBlueCost;
            }

            return res;
        }
    }


    @Test
    public void test1() {
        int[] red = {2, 3, 4};
        int[] blue = {3, 1, 1};
        int blueCost = 2;
        int[] res = new Solution1().minCost(red, blue, blueCost);
        int[] ans = {0, 2, 5, 6};
        assertArrayEquals(res, ans);
    }

    @Test
    public void test2() {
        int[] red = {40, 20};
        int[] blue = {30, 25};
        int blueCost = 5;
        int[] res = new Solution1().minCost(red, blue, blueCost);
        int[] ans = {0, 35, 55};
        assertArrayEquals(res, ans);
    }

}
