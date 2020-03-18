package com.jzq.leetcodehard;

import java.util.Arrays;
import java.util.Collections;


/*
 * 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空
 */

public class Hard4 {
	public static void main(String[] args) {
		System.out.println(new Hard4().new Solution().findMedianSortedArrays(new int[] {3}, new int[] {-2, -1}));
	}
	
	class Solution {
	    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	    	if (isEmpty(nums1) && isEmpty(nums2)) return 0d;
	    	if (isEmpty(nums1)) return mid(nums2);
	    	else if (isEmpty(nums2)) return mid(nums1);
	    	
	    	int[] arr1 = null, arr2 = null;
	    	if (nums1.length > nums2.length) {
	    		arr1 = nums2;
	    		arr2 = nums1;
	    	} else {
	    		arr1 = nums1;
	    		arr2 = nums2;
	    	}
	    	
	    	int l = -1, h = arr1.length + 1, m1 = (l + h) / 2, m2, t = arr1.length + arr2.length, n1, n2;
	    	boolean isOdd = (arr1.length + arr2.length) % 2 == 0;
	    	for (;;) {
	    		m2 = isOdd ? t / 2 - m1 : t / 2 - m1 + 1;
	    		
	    		if (m1 == 0) n2 = arr2[m2 - 1];
				else if (m2 == 0) n2 = arr1[m1 - 1];
				else n2 = Integer.max(arr1[m1 - 1], arr2[m2 - 1]);
				if (m1 == arr1.length) n1 = arr2[m2];
				else if (m2 == arr2.length) n1 = arr1[m1];
				else n1 = Integer.min(arr1[m1], arr2[m2]);
				
				if (n1 >= n2) {
					return isOdd ? (n1 + n2) / 2.0 : n2;
				} else if (m1 - 1 >= 0 && m2 < arr2.length && arr1[m1 - 1] > arr2[m2]) 
					h = m1;
				else
					l = m1;
				m1 = (l + h) / 2;
	    	}
	    }
	    
	    private double mid(int[] nums) {
	    	if (nums.length % 2 == 0) return mid2(nums);
	    	else return mid1(nums);
	    }
	    
	    private double mid1(int[] nums) {
	    	return nums[nums.length / 2];
	    }
	    
	    private double mid2(int[] nums) {
	    	return (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2.0;
	    }
	    
	    private boolean isEmpty(int[] nums) {
	    	return nums == null || nums.length == 0; 
	    }
	}
}