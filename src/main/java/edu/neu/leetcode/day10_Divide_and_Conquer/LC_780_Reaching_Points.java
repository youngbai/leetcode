package edu.neu.leetcode.day10_Divide_and_Conquer;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

// from Robin's Lab
public class LC_780_Reaching_Points {

    /*
    Thinking:
    - Brute Force
    - recursion tree
    - cause stackoverflow

    Time:  O(2^N), which N=max(tx-sx,ty-sy)
    - time complexity depends on the height of the recursion tree, which is max(tx-sx,ty-sy)
    - because we searched all nodes of the tree which has 2^N nodes, so O(2^N)
    Space: O(logN), which N=max(tx-sx,ty-sy)
    - space complexity is the call stack, which is the height of the recursion tree, which is max(tx-sx,ty-sy)
    - because recursion search will use DFS, its complexity will be the height of the recursion tree
    https://leetcode.com/problems/reaching-points/discuss/375429/Detailed-explanation.-or-full-through-process-or-Java-100-beat
     */
    class Solution1_BruteForce {
        public boolean reachingPoints(int sx, int sy, int tx, int ty) {
            if (sx > tx || sy > ty) return false;
            if (sx == tx && sy == ty) return true;
            return reachingPoints(sx + sy, sy, tx, ty) ||
                    reachingPoints(sx, sy + sx, tx, ty);
        }
    }

    /*
    Thinking:
    - Dynamic Programming
    - use HashSet to save all possible points
    - recursive search
    - Time Limit Exceeded

    Time:  O(2^N), N is height of recursion tree, N=max(tx-sx,ty-sy)
    - because we need to search and save all possible points to HashSet
    Space: O(2^N)
    - HashSet will save all the nodes of the recursion tree which has 2^N nodes
     */
    class Solution2_DP {
        Set<Point> seen;
        int tx, ty;
        public boolean reachingPoints(int sx, int sy, int tx, int ty) {
            seen = new HashSet();   // memorize all possible points
            this.tx = tx; this.ty = ty;
            search(new Point(sx, sy)); // search from (sx,sy), save all possible points
            return seen.contains(new Point(tx,ty));
        }

        private void search(Point p) {
            if (seen.contains(p)) return;   // if already searched, return
            if (p.x > tx || p.y > ty) return; // if beyond search scope, return
            seen.add(p);    // add possible point
            search(new Point(p.x + p.y, p.y)); // recursive search
            search(new Point(p.x, p.y + p.x)); // recursive search
        }
    }

    /*
    Thinking:
    - Work Backwards (Modulo Variant), search from target to source
    - there is only one way from source to target
      - because every point must be positive
      - if target is (x,y), its previous point is either (x,y-x) or (x-y,y)
      - if x < y, then previous point MUST be (x,y-x), its farthest point is (x, y-kx),
        which code is `if x < y then y %= x`
      - if x > y, then previous point MUST be (x-y,y), its farthest point is (x-ky, y)
        which code is `if x > y then x %= y`
      - if start.x == target.x and (target.y - start.y) % start.x == 0, then we find it
        if start.y == target.y and (target.x - start.x) % start.y == 0, then we find it
        else missed
    - we use Modulo to speed up
      because we don't have to traverse each point, we can skip many points by Modulo,
      like, ty %= tx or tx %= ty

    Time:  O(logN), N=max(tx-sx,ty-sy)
    - because there is only one way from source to target, it's like from root to leave
    - so Time is the height of the binary recursion tree, which is logN, N=max(tx-sx,ty-sy)
    - its Time is like that of Euclidean Algorithm

    Euclidean Algorithm (Proof)
    https://www.youtube.com/watch?v=H_2_nqKAZ5w

    Space: O(1)
     */
    class Solution3_Work_Backwards {
        public boolean reachingPoints(int sx, int sy, int tx, int ty) {
            while (sx < tx && sy < ty) {
                if (tx < ty) ty %= tx;
                else tx %= ty;
            }
            if (sx == tx && sy <= ty && (ty - sy) % sx == 0) return true;
            if (sy == ty && sx <= tx && (tx - sx) % sy == 0) return true;
            return false;

            // return sx == tx && sy <= ty && (ty - sy) % sx == 0 ||
            //       sy == ty && sx <= tx && (tx - sx) % sy == 0;
        }
    }
}
