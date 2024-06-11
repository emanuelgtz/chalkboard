package org.practice;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    int[] unsortedNumbers = {1,2,3,4,5,6,7,8,9,10,11,12};

    binarySearchIterative(unsortedNumbers, 3, 1, 7);
    int value = binarySearchRecursive(unsortedNumbers, 2, 0, 12);
    System.out.println("value from binary search recursive: " + value);
  }

  // bubble sort implementation
  static void bubbleSort(int[] array) {
    int size = array.length; // 5

    // loop to get access to each element in the array
    for(int i = 0; i < size - 1; i++) {
      // loop to first, iterate each element and make comparisons about the stored elements
      for(int p = 0; p < size - i - 1; p++) {
        // At this point, is time to carry out the comparisons between elements
        if(array[p] > array[p + 1]) {
          int currentPosition = array[p];
          array[p] = array[p + 1];
          array[p + 1] = currentPosition;
        }

      }

    }

    System.out.println("Sorted by bubble sort algorithm" + Arrays.toString(array));
  }

  // Insertion sort
  static void insertionSort(int array[]) {
    int arraySize = array.length;

    // index 1 because it is supposed index 0 is already sorted.
    for(int i = 1; i < arraySize; i++) {
      int key = array[i];
      int leftValue = i - 1;

      while(leftValue >= 0 && key < array[leftValue]) {
        array[leftValue + 1] = array[leftValue];
        --leftValue;
      }

      // Place key at after the elements just smaller than that
      array[leftValue  + 1] = key;
    }
    System.out.println("Sorted array from insertion sort: " + Arrays.toString(array));
  }

  // Binary search(there are two types of methods to perform this algorithm) iterative and recursive
  // Binary search(iterative method)
  static int binarySearchIterative(int array[], int x, int lowest, int highest) {
    while(lowest <= highest) {
      int mid = lowest + (highest - lowest) / 2;

      if(array[mid] == x) {
        return mid;
      }

      if(array[mid] < x) {
        lowest = mid + 1;
      } else {
        highest = mid - 1;
      }

    }
    return -1;
  }

  // Binary search(recursive method)
  static int binarySearchRecursive(int array[], int x, int low, int high) {

    // x is the number we're looking for
    if(high >= low) {

      // Provide the center of the array
      int mid = low + (high - low) / 2;

      // If we found the value at the center, we have to return it
      if(array[mid] == x) {
        return mid;
      }

      // Search the left half
      if(array[mid] >= x) {
        return binarySearchRecursive(array, x, low, mid - 1);
      }

      // Search the right half
      return binarySearchRecursive(array, x, mid + 1, high);

      // we do not have to forget this is a recursive operation. Keeping it in mind is fundamental to understand the why of its behavior.
    }

    return -1;

  }

}
