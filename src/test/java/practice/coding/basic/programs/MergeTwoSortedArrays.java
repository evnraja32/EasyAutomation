package practice.coding.basic.programs;

import org.testng.annotations.Test;

public class MergeTwoSortedArrays {

    @Test
    public void testMethod() {

//        int[] arr1 = new int[]{2, 2, 4, 4};
//        int[] arr2 = new int[]{2, 2, 2, 4, 4};
//        mergeArrays(arr1, arr2);
//        mergeArrays(arr2, arr1);
//        mergeArrays(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8, 10});
//        mergeArrays(new int[]{1, 3, 5, 7}, new int[]{2});


        merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);


    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] merged = new int[m + n];
        int arr1Index = 0;
        int arr2Index = 0;
        int mergedIndex = 0;

        /**
         * Initial code to merge matching indices in arr1 & arr2
         */
        while (arr1Index < m && arr2Index < n) {
            if (nums1[arr1Index] < nums2[arr2Index]) {
                merged[mergedIndex++] = nums1[arr1Index++];
            } else {
                merged[mergedIndex++] = nums2[arr2Index++];
            }
        }

        /**
         * Code to merge the leftover elements of arr1 & arr2
         */
        while (arr1Index < m) {
            merged[mergedIndex++] = nums1[arr1Index++];
        }
        while (arr2Index < n) {
            merged[mergedIndex++] = nums2[arr2Index++];
        }

        for (int index = 0 ; index < mergedIndex ; index++) {
            nums1[index] = merged[index];
        }

        System.out.print("[");
        for (int i : nums1) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }

    public void mergeArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int arr1Index = 0;
        int arr2Index = 0;
        int mergedIndex = 0;

        /**
         * Initial code to merge matching indices in arr1 & arr2
         */
        while (arr1Index < arr1.length && arr2Index < arr2.length) {
            if (arr1[arr1Index] < arr2[arr2Index]) {
                merged[mergedIndex++] = arr1[arr1Index++];
            } else {
                merged[mergedIndex++] = arr2[arr2Index++];
            }
        }

        /**
         * Code to merge the leftover elements of arr1 & arr2
         */
        while (arr1Index < arr1.length) {
            merged[mergedIndex++] = arr1[arr1Index++];
        }
        while (arr2Index < arr2.length) {
            merged[mergedIndex++] = arr2[arr2Index++];
        }

        System.out.print(">>>> merged array: ");
        for (int i : merged) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
