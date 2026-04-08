package com.demo.jvm.basics.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SelectionSort {

  public static void main(String[] args) {
    int[] nums = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};

    sort(nums);

    System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
  }

  public static void sort(int[] nums) {

    for (int i = 0; i < nums.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[j] < nums[min]) {
          min = j;
        }
      }

      if (i != min) {
        int tmp = nums[i];
        nums[i] = nums[min];
        nums[min] = tmp;
      }
    }

  }
}
