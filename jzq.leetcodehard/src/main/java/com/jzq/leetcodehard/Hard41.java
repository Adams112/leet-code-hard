package com.jzq.leetcodehard;

public class Hard41 {

	public static void main(String[] args) {

	}
	
	class Solution {
	    public int firstMissingPositive(int[] nums) {
	        for(int i = 0; i < nums.length;) {
	        	if(nums[i] == i + 1) i++;
	        	else {
	        		if(nums[i] > 0 && nums[i] <= nums.length) {
	        			if(nums[i] == nums[nums[i] - 1]) {
	        				i++;
	        			} else {
	        			//swap put nums[i] int index nums[i] - 1
	        				int index = nums[i] - 1;
	        				int temp = nums[i];
	        				nums[i] = nums[index];
	        				nums[index] = temp;
	        			}
	        			
	        		}
	        		else {
	        			i++;
	        		}
	        	}
	        }
	        
	        for(int i = 0; i < nums.length; i++) {
	        	if(nums[i] != i + 1) return i + 1;
	        }
	        return nums.length + 1;
	    }
	}

}
