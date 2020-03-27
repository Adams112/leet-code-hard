package com.jzq.leetcodehard;

public class Hard154 {
	class Solution {
	    public int findMin(int[] nums) {
	        if(nums.length == 1) return nums[0];
	        
	        int start = 0, end = nums.length - 1, middle;
	        
	    	while(start < end && nums[start] == nums[end] && nums[start] == nums[(start + end) / 2]) start++;
	    	if(start == end) return nums[end];
	    	if(start + 1 == end) return Math.min(nums[start], nums[end]);
	    	
	    	while(start + 1 < end) {
	    		middle = (start + end) / 2;
	    		if(nums[end] > nums[start]) {
	    			return nums[start];
	    		} else {
	        		if(nums[start] == nums[end]) {
	        			if(nums[middle] > nums[start]) start = middle;
	        			else end = middle;
	        		} else {
	        			if(nums[middle] >= nums[start]) start = middle;
	        			else end = middle;
	        		}
	    		}
	    	}
	    	
	    	return Math.min(nums[start], nums[end]);
	    }
	}
}
