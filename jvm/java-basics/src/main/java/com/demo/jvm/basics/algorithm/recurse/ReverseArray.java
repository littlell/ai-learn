package com.demo.jvm.basics.algorithm.recurse;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseArray {

  public static void main(String[] args) {
    int[] nums = new int[]{1, 2, 3, 4, 5};

    reverse(nums, 0, nums.length - 1);

    System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
  }


  public static void reverse(int[] nums, int from, int to) {

    if (from >= to) {
      return;
    }

    int tmp = nums[from];
    nums[from] = nums[to];
    nums[to] = tmp;

    reverse(nums, from + 1, to - 1);
  }
}
