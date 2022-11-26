package practice.coding.leetcode.algorithms;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchInsertPosition {

    int[] nums;
    int target, mid;
    public int searchInsert(int[] nums, int target) {
        if(target > nums[nums.length - 1]){
            return nums.length;
        }
        this.nums = nums;
        this.target = target;

        return binarySearch(0, nums.length - 1);
    }

    private int binarySearch(int low, int high){

        if (low == high){ // {1,3,5,6}, 2
            return ((nums[low] == target) ? low : (nums[low] < target) ? low+1 : low);
        }

        // nums = [1,3,5,6], target = 5
        mid = (low + high) / 2;
        if(nums[mid] == target){ // 3 == 5
            return mid;
        } else if (nums[mid] > target){ // 3 > 5
            return binarySearch(low, mid);
        } else { // 3 < 5
            return binarySearch(mid + 1, high);
        }
    }

    @Test
    public void testMethod(){
        Assert.assertEquals(searchInsert(new int[] {1,3,5,6}, 5), 2);
        Assert.assertEquals(searchInsert(new int[] {1,3,5,6}, 7), 4);
        Assert.assertEquals(searchInsert(new int[] {1,3,5,6}, 2), 1);
    }


}
