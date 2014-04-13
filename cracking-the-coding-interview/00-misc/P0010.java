/**
 * P0010
 *
 * Two Sum
 * Given an array of intergers, find two numbers such that they add up to a
 * specific target number.
 *
 * Example:
 * Input : numbers = {2, 7, 11, 15}
 *         target  = 9
 * Output: index1 = 1, index2 = 2
 */

public class P0010 {
    public static void twoSum(int[] numbers, int target) {
        int len = numbers.length;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[i] + numbers[j] == target)
                    System.out.println("index1 = " + (j + 1) + ", index2 = " + (i + 1));
            }
        }
    }

    public static void main(String args[]) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        
        P0010.twoSum(numbers, target);
    }
}
