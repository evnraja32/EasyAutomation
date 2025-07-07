package practice.coding.leetcode;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.TreeSet;

public class MedianOfTwoSortedArrays {

    @Test
    public void testMethod() {
//        Assert.assertEquals(findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{2, 4}), 2.5000);
//        Assert.assertEquals(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), 2.0000);
        Assert.assertEquals(findMedianSortedArrays(new int[]{2, 2, 4, 4}, new int[]{2, 2, 2, 4, 4}), 2.0000);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;

        // Merge the arrays
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            merged[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            merged[k++] = nums2[j++];
        }

        // Find the median
        int total = merged.length;
        if (total % 2 == 1) {
            return merged[total / 2];
        } else {
            return (merged[(total / 2) - 1] + merged[total / 2]) / 2.0;
        }
    }

    public double findMedianSortedArrays_v1(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet();

        for (int index = 0; index < nums1.length; index++) {
            set.add(nums1[index]);
        }

        for (int index = 0; index < nums2.length; index++) {
            set.add(nums2[index]);
        }

        int comibenedSet[] = new int[set.size()];
        int counter = 0;
        for (int i : set) {
            comibenedSet[counter++] = i;
            System.out.print(i + " ");
        }

        System.out.print(" - counter: " + counter);
        int halfSize = counter / 2;
        System.out.print(" - halfSize: " + halfSize);
        if (counter % 2 == 0) {
            System.out.print(" - block: even");
            return (comibenedSet[halfSize - 1] + comibenedSet[halfSize]) / 2.0;
        } else {
            System.out.print(" - block: odd");
            return comibenedSet[halfSize];
        }
    }
}
