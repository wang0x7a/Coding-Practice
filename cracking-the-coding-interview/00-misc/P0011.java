/* P0011
 *
 * Three Sum
 * Given an array S of n integers, are three elements a, b, c in S such that
 * a + b + c unique triplets in the array which gives the sum of zero.
 *
 * Note:
 * Elements in a triplet (a, b, c) must be in non-descending order.
 * (i.e., a <= b <= c)
 * The solution set must not contain duplicate triplets.
 *
 * For example,
 * given array S = {-1, 0, 1, 2, -1, -4}, a solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 */

import java.util.ArrayList;
import java.util.Arrays;

public class P0011 {
    public static ArrayList<int[]> threeSum(int[] numbers) {
        ArrayList<int[]> res = new ArrayList<int[]>();

        int len = numbers.length;

        if (len < 3)
            return res;

        Arrays.sort(numbers);
        
        return res;
    }

    public static void main(String args[]) {}
}
