package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 * author : lzw
 * date   : 2022-11-22 18:38
 */
public class Test {
    @org.junit.Test
    public void addition_isCorrect() {

        // long startTime = System.currentTimeMillis();
        int abbbb = 10;
        int bbbb = abbbb + 10;
        // long endTime = System.currentTimeMillis() - startTime;
        System.out.println(abbbb);
        // System.out.println(endTime);
    }

    //     public int threeSumClosest(int[] nums, int target) {
    //
    //         if (nums.length == 0) {
    //             return 0;
    //         }
    //         if (nums.length <= 3) {
    //             int res = 0;
    //             for (int num : nums) {
    //                 res += num;
    //             }
    //             return res;
    //         }
    //         Arrays.sort(nums);
    //         int res = Integer.MAX_VALUE;
    //         for (int i = 0; i < nums.length; i++) {
    //             int sum = 0;
    //             int start = 0, end = nums.length - 1;
    //             while (start < end) {
    //                 if (start == i) {
    //                     start++;
    //                     continue;
    //                 } else if (end == i) {
    //                     end--;
    //                     continue;
    //                 }
    //                 sum = nums[i] + nums[start] + nums[end];
    //                 if (sum == target) {
    //                     return sum;
    //                 } else if (sum < target) {
    //                     start++;
    //                 } else if (sum > target) {
    //                     end--;
    //                 }
    //             }
    //             if (Math.abs(target - sum) < Math.abs(target - res)) {
    //                 res = sum;
    //             }
    //         }
    //         return res;
    //     }
}
