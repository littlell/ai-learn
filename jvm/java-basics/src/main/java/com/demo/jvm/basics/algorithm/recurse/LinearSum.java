package com.demo.jvm.basics.algorithm.recurse;

public class LinearSum {

  public static void main(String[] args) {
    int[] nums = new int[]{1, 2, 3, 4};

    int sum = sum(nums, nums.length - 1);

    System.out.println(sum);
  }

  public static int sum(int[] nums, int n) {
    if (n == 0) {
      return nums[0];
    }

    return nums[n] + sum(nums, n - 1);
  }
}
