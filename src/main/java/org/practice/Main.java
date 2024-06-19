package org.practice;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    int[] numbersSet1 = {1,2,3,4,5,6,7,8,9,10,11,12};
    int[] numbersSet2 = {2,300,11,5,6,82,322,42,5,90};

    quickSort(numbersSet2, 0, numbersSet2.length - 1);
    printArray(numbersSet2);

  }

  // Linear Search. This algorithm is about looking for each cell in the array until it finds the element
  static int linearSearchAlgorithm(int array[], int x) {

    int n = array.length;

    // Passing through each element in the array
    for(int i = 0; i < n; i++) {
      if(array[i] == x) {
        return i;
      }
    }
    return -1;
  }

  // Selection sort
  static int[] selectionSortAlgorithm(int array[]) {

    int size = array.length;

    for(int step = 0; step < size - 1; step++) {
      int min_index = step;

      for(int i = step + 1; i < size; i++) {
        // If we want to sort the give array in descending order, we have to invert the symbol from > to <.
        // Keep in mind that i at this point is pointing to the left value
        if(array[i] < array[min_index]) {
          min_index = i;
        }
      }
      // Now is time to put the min index value in the correct position
      int temp = array[step];
      array[step] = array[min_index];
      array[min_index] = temp;
    }
    return array;
  }

  // * Merge sort. Being careful when implementing this is fundamental, because this sorting algorithm requires two blocks of code in performing the needed process.

  static void merge(int[] arr, int l, int m, int r) {
    // create L <- A[p..q] and M <- A[q+1..r]. At this point, we are creating a copy of the sub arrays, actually.
    int n1 = m - l + 1;
    int n2 = r - m;

    int L[] = new int[n1];
    int M[] = new int[n2];

    for(int i = 0; i < n1; i++) {
      L[i] = arr[l + i];
    }

    for(int j = 0; j < n2; j++) {
      M[j] = arr[m + 1 + j];
    }

    // maintain current index of sub array and main array. This means the first index on each sub array
    int i, j, k;
    i = 0;
    j = 0;
    k = l;

    // Merge the elements of L and M back into the original array
    while (i < n1 && j < n2) {
      // Comparing elements of L and M and place the smaller into array
      if(L[i] <= M[j]) {
        arr[k] = L[i];
        i++;
      } else {
        arr[k] = M[j];
        j++;
      }
      k++; // This helps us to move to the next position in the array
    }

    // Copy remain elements from L and M into arr
    while( i < n1) {
      arr[k] = L[i];
      i++;
      k++;
    }

    while (j < n2) {
      arr[k] = M[j];
      j++;
      k++;
    }

  }

  // Divide the array into two sub arrays
  static void mergeSortAlgorithm(int arr[], int l, int r) {

    if(l < r) {
      // this variable is the point at the center of the array, this is the point will split the array into two halves
      int m = (l + r) / 2;

      // recursion
      mergeSortAlgorithm(arr, l, m);
      mergeSortAlgorithm(arr, m + 1, r);

      // merge the sorted sub arrays
      merge(arr, l, m, r);

    }
  }

  // print array
  static void printArray(int array[]) {
    int n = array.length;
    for(int i = 0; i < n; i++) {
      System.out.println("This is the number: " + array[i]);
    }
  }
  private static int partition(int array[], int low, int high) {
    int pivot = array[high];

    // pointer for greater elements
    int i = (low - 1);

    //  This variable, "j", is for traversing through all elements stored at the array
    for(int j = low; j < high; j++) {
      if(array[j] <= pivot) {
        // If the current element is smaller than pivot, is time to increase the variable i in moving one index to right. If smaller element than pivot is found, swap it with the greater element.
        i++;
        // swapping the pivot element with the greater specified by i
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

      }
    }
    // swap bigger element specified by i with the pivot
    int temp = array[i + 1];
    array[i + 1] = array[high];
    array[high] = temp;

    // Time to return the position from where partition is done
    return (i + 1);
  }

  // Quicksort
  static void quickSort(int array[], int low, int high) {
    if(low < high){
      // Smaller elements than pivots must be placed at the left of the array and bigger elements must be placed at the right
      int pi = partition(array, low, high);

      // Recursive call on the left of pivot
      quickSort(array, low, pi - 1);

      // Recursive call on the right of pivot
      quickSort(array, pi + 1, high);

    }
  }


}
