package practice.coding.leetcode;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * https://leetcode.com/problems/maximum-ice-cream-bars/
 */
public class MaxIceCreams {

    @Test
    public void testMethod(){
        int output = MaxIceCreams.maxIceCream(new int[] {27,23,33,26,46,86,70,85,89,82,57,66,42,18,18,5,46,56,42,82,
                52,78,4,27,96,74,74,52,2,24,78,18,42,10,12,10,80,30,60,38,32,7,98,26,18,
                62,50,42,15,14,32,86,93,98,47,46,58,42,74,69,51,53,58,40,66,46,65,2,10,82,94,26,6,78,
                2,101,97,16,12,18,71,5,46,22,58,12,18,62,61,51,2,18,34,12,36,58,20,12,17,70}, 241);

        Assert.assertEquals(output, 24);
    }

    public static int maxIceCream1(int[] costs, int coins) {
        int totalIcecreams = 0;
        int index = 0;

        mergeSort(costs, 0, costs.length - 1);

        while(coins > 0 && index < costs.length) {
//            System.out.println("Coins: " + coins + " => " + index + ". Cost: " + costs[index] );
            if (coins >= costs[index]){
                ++totalIcecreams;
                coins = coins - costs[index];
            }
            ++index;
        }
        return totalIcecreams;
    }

    public static int maxIceCream(int[] costs, int coins) {
        int totalIcecreams = 0;
        int index = 0;

        Arrays.sort(costs);

        while(coins > 0 && index < costs.length) {
            if (coins >= costs[index]){
                ++totalIcecreams;
                coins = coins - costs[index];
            }
            ++index;
        }
        return totalIcecreams;
    }

    public static void merge(int arr[], int left, int middle, int right)
    {
//        System.out.println("merge: " + left + " - " + middle + " - " + right);
        int low = middle - left + 1;                    //size of the left subarray
        int high = right - middle;                      //size of the right subarray

        int L[] = new int[low];                             //create the left and right subarray
        int R[] = new int[high];

        int i = 0, j = 0;

        for (i = 0; i < low; i++)                               //copy elements into left subarray
        {
            L[i] = arr[left + i];
        }
        for (j = 0; j < high; j++)                              //copy elements into right subarray
        {
            R[j] = arr[middle + 1 + j];
        }


        int k = left;                                           //get starting index for sort
        i = 0;                                             //reset loop variables before performing merge
        j = 0;

        while (i < low && j < high)                     //merge the left and right subarrays
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < low)                             //merge the remaining elements from the left subarray
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < high)                           //merge the remaining elements from right subarray
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    public static void mergeSort(int arr[], int left, int right)       //helper function that creates the sub cases for sorting
    {
        int middle;
        if (left < right) {                             //sort only if the left index is lesser than the right index (meaning that sorting is done)
            middle = (left + right) / 2;
//            System.out.println("Left sub: " + left + " - " + middle);
            mergeSort(arr, left, middle);                    //left subarray

//            System.out.println("Right sub: " + (middle + 1) + " - " + right);
            mergeSort(arr, middle + 1, right);               //right subarray

            merge(arr, left, middle, right);                //merge the two subarrays
        }
    }


    public static void display(int arr[])                 //display the array
    {
        for (int i=0; i<arr.length; ++i)
        {
            System.out.print(arr[i]+" ");
        }
    }
}