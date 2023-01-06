package examples;

import org.testng.annotations.Test;

public class MoveOddNumbersToEnd {

    @Test
    public void testMethod(){
        int[] arr = {3,5,1,8,3,78,2,5,1,8,2,9,10};
        int temp,count = 1;
        for(int i = 0; i<arr.length; i++){
            if(arr[i]%2 == 1){
                temp = arr[arr.length-count];
                arr[arr.length-count] = arr[i];
                arr[i] = temp;
            }
            if(arr[i]%2 == 1){
                i--;
            }
            count++;
            if(count == arr.length){
                break;
            }
        }

        for(int i = 0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }

    }
}
