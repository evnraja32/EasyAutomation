package practice.coding.leetcode.algorithms;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstBadVersion extends VersionControl{

    @Test
    public void testMethod(){
        Assert.assertEquals(firstBadVersion(2126753390), 1702766719);
    }

    long low, mid, high;
    public int firstBadVersion(long n) {
        low = 1;
        high = n;
        return binarySearch();
    }

    private int binarySearch(){
        mid = (low + high) / 2;
        if (low == high){
            return Math.toIntExact(high);
        }

        if (isBadVersion(Integer.parseInt(mid + ""))) {
            high = mid;
            return binarySearch();
        } else {
            low = mid + 1;
            return binarySearch();
        }
    }

}

class VersionControl{
    int bad = 1702766719;
    protected boolean isBadVersion(int mid) {
        return mid >= 1702766719;
    }

}
