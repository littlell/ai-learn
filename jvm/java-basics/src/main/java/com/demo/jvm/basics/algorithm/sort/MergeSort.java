package com.demo.jvm.basics.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MergeSort {

  public static void main(String[] args) {
    int[] nums = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};

    int[] tmp = new int[nums.length];

    sort(nums, 0, nums.length - 1, tmp);

    System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
  }

  public static void sort(int[] nums, int left, int right, int[] tmp) {

    if (left + 1 > right) {
      return;
    }

    int mid = (left + right) / 2;

    sort(nums, left, mid, tmp);
    sort(nums, mid + 1, right, tmp);

    merge(nums, left, right, mid, tmp);
  }

  private static void merge(int[] result, int left, int right, int mid, int[] tmp) {
    int index = left;
    for (int i = left, j = mid + 1; i <= mid || j <= right; ) {
      if (i > mid) {
        tmp[index++] = result[j++];
      } else if (j > right) {
        tmp[index++] = result[i++];
      } else if (result[i] > result[j]) {
        tmp[index++] = result[j++];
      } else {
        tmp[index++] = result[i++];
      }
    }
    for (int i = left; i <= right; i++) {
      result[i] = tmp[i];
    }
  }
}
