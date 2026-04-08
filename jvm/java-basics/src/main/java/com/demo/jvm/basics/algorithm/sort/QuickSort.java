package com.demo.jvm.basics.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QuickSort {

  public static void main(String[] args) {
    int[] nums = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};

    sort(nums, 0, nums.length - 1);

    System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
  }

  public static void sort(int[] nums, int left, int right) {

    if (left >= right) {
      return;
    }

    int tmp = nums[left];
    int l = left;
    int r = right;

    while (left < right) {

      while (left < right) {
        if (nums[right] < tmp) {
          nums[left++] = nums[right];
          break;
        }
        right--;
      }

      while (left < right) {
        if (nums[left] > tmp) {
          nums[right--] = nums[left];
          break;
        }
        left++;
      }
    }

    nums[left] = tmp;
    sort(nums, l, left - 1);
    sort(nums, left + 1, r);
  }
}
