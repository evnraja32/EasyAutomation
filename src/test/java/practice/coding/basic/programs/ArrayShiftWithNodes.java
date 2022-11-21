package practice.coding.basic.programs;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ArrayShiftWithNodes {

    @Test
    public void testMethod(){
        Assert.assertEquals(moveEvenNumberToLast(new int[]{2,1,3,6,8,10,9}), new int[]{1, 3, 9, 2, 6, 8, 10});
    }

    @Test
    public void testMethod2(){
        Assert.assertEquals(moveEvenNumberToLast(new int[]{13, 8, 20, 10, 7, 4, 19, 11, 12, 9, 16, 1, 6, 3, 18, 14, 5, 15, 17, 2}), new int[]{13, 7, 19, 11, 9, 1, 3, 5, 15, 17, 8, 20, 10, 4, 12, 16, 6, 18, 14, 2});
    }

    private int[] moveEvenNumberToLast(int[] nums) {
        Node lastEvenNode = null, lastOddNode = null, currentNode = null;
        Node firstNode = null;

        for(int i = 0 ; i < nums.length ; i++){
            currentNode = new Node(nums[i], null);
            if (nums[i] % 2 == 0) {
               if (lastEvenNode == null){
                   if(firstNode == null) {
                       firstNode = currentNode;
                   } else {
                       lastOddNode.next = currentNode;
                   }
               } else {
                   lastEvenNode.next = currentNode;
               }
                lastEvenNode = currentNode;
            }
            else {
                if (lastOddNode == null) {
                    if (lastEvenNode != null){
                        currentNode.next = lastEvenNode;
                    }
                    firstNode = currentNode;
                } else {
                    currentNode.next = lastOddNode.next;
                    lastOddNode.next = currentNode;
                }
                lastOddNode = currentNode;
            }
//            System.out.println(firstNode.toString());
        }

        int[] processedArray = new int[nums.length];
        Node nextNode = firstNode;
        int index = -1;
        while(nextNode != null){
            processedArray[++index] = nextNode.num;
            nextNode = nextNode.next;
        }
        return processedArray;
    }

}

class Node {
    public int num;
    public Node next;

    public Node(){

    }
    public Node(int num, Node next){
        this.num = num;
        this.next = next;
    }

    @Override
    public String toString() {
        return (num + ", " + next);
    }
}
