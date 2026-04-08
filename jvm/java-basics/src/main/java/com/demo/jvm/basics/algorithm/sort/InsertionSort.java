package com.demo.jvm.basics.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InsertionSort {

  public static void main(String[] args) {
    int[] nums = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};

    sort(nums);

    System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
  }

  public static void sort(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[j] > nums[i]) {
          int tmp = nums[i];
          for (int k = i; k > j; k--) {
            nums[k] = nums[k - 1];
          }
          nums[j] = tmp;
        }
      }
    }
  }
}