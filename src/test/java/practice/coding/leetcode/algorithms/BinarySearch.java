package practice.coding.leetcode.algorithms;

public class BinarySearch {

    // Runtime: 0ms | space: 57.63%
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while(low <= high){
            if (nums[((low + high) / 2)] == target){
                return ((low + high) / 2);
            } else if(nums[((low + high) / 2)] < target){
                low = ((low + high) / 2) + 1;
            } else {
                high = ((low + high) / 2) - 1;
            }
        }

        return -1;
    }

    int[] nums;
    int target;
    public int search_recursive(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        return search( 0);
    }

    private int search(int index) {
        if(index == nums.length){
            return -1;
        }
        if (nums[index] == target){
            return index;
        } else {
            return search(index+1);
        }
    }
}
