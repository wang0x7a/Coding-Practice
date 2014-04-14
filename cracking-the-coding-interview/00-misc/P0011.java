/* P0011
 *
 * Three Sum
 * Given an array S of n integers, are three elements a, b, c in S such that
 * a + b + c unique triplets in the array which gives the sum of zero.
 *
 * For example,
 * given array S = {-1, 0, 1, 2, -1, -4}, a solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 *
 * Note:
 * Elements in a triplet (a, b, c) must be in non-descending order.
 * (i.e., a <= b <= c)
 * The solution set must not contain duplicate triplets.
 *
 * 1) What's the difference between sorted and unsorted input array? How can we
 *    make use of this info of sorted input?
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

        for (int i = 0; i < len - 2; i++) {
            if (i == 0 || numbers[i] > numbers[i - 1]) {
                int negate = -numbers[i];
                
                int start = i + 1;
                int end   = len - 1;

                while (start < end) {
                    if (numbers[start] + numbers[end] == negate) {
                        int[] tmp = new int[]{-negate, numbers[start], numbers[end]};
                        res.add(tmp);
                        start++;
                        end--;

                        while (start < end && numbers[end] == numbers[end + 1])
                            end--;
                        while (start < end && numbers[start] == numbers[start - 1])
                            start++;
                    }
                    else if (numbers[start] + numbers[end] < negate)
                        start++;
                    else
                        end--;
                }
            }
        }
        
        return res;
    }

    public static void print(ArrayList<int[]> list) {
        for (int[] a : list) {
            System.out.print("( ");
            for (int i = 0; i < 3; i++)
                System.out.print(a[i] + " ");

            System.out.println(")");
        }
    }

    public static void main(String args[]) {
        int[] numbers = {-1, 0, 1, 2, -1, -4};

        ArrayList<int[]> res = P0011.threeSum(numbers);
        P0011.print(res);
    }
}
