package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Solution {
    /**
     * Example 1:
     *
     * Input: nums1 = [1,3], nums2 = [2]
     * Output: 2.00000
     * Explanation: merged array = [1,2,3] and median is 2.
     * Example 2:
     *
     * Input: nums1 = [1,2], nums2 = [3,4]
     * Output: 2.50000
     * [0, 1, 2, 3]
     * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
     */

    public static void main(String[] args) {
        int[] num1 = { 1, 3};
        int[] num2 = { 2 };
        System.out.println(findMedianSortedArrays(num1, num2));

        int[] num3 = { 2, 4};
        System.out.println(findMedianSortedArrays(num1, num3));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        List<Integer> mergedList  = Stream.concat(
            Arrays.stream(nums1).boxed(),
            Arrays.stream(nums2).boxed()
        ).sorted().toList();

        if(mergedList.size() % 2 != 0) {
            int medianIndex = mergedList.size() / 2 ;
            return mergedList.get(medianIndex);
        }else {
            int medianIndex = mergedList.size() / 2 ;
            double median = (mergedList.get(medianIndex - 1) + mergedList.get(medianIndex)) / 2.0;
            return Math.round(median * 10.0) / 10.0;
        }

    }
}
