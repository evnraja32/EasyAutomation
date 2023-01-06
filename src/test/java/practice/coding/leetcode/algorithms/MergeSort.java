package practice.coding.leetcode.algorithms;

public class MergeSort {
    public void merge(int arr[], int left, int middle, int right)
    {
        System.out.println("merge: " + left + " - " + middle + " - " + right);
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


    public void mergeSort(int arr[], int left, int right)       //helper function that creates the sub cases for sorting
    {
        int middle;
        if (left < right) {                             //sort only if the left index is lesser than the right index (meaning that sorting is done)
            middle = (left + right) / 2;
            System.out.println("Left sub: " + left + " - " + middle);
            mergeSort(arr, left, middle);                    //left subarray

            System.out.println("Right sub: " + (middle + 1) + " - " + right);
            mergeSort(arr, middle + 1, right);               //right subarray

            merge(arr, left, middle, right);                //merge the two subarrays
        }
    }

    public void display(int arr[])                 //display the array
    {
        for (int i=0; i<arr.length; ++i)
        {
            System.out.print(arr[i]+" ");
        }
    }


    public static void main(String args[])
    {
        int arr[] = { 9, 3, 1, 5, 13, 12 };
        MergeSort ob = new MergeSort();
        ob.mergeSort(arr, 0, arr.length - 1);
//        ob.display(arr);
    }
}

