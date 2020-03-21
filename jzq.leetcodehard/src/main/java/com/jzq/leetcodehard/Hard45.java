package com.jzq.leetcodehard;

public class Hard45 {

	public static void main(String[] args) {

	}
	
	class Solution {
	    public int jump(int[] nums) {
	    	if(nums == null || nums.length <= 1) return 0;
	    	
	    	int destIndex = nums.length - 1;
	    	int step = 0;
	    	while(destIndex != 0) {
	    		for(int i = 0; i < destIndex; i++) {
	    			if(nums[i] - destIndex + i >= 0) {
	    				destIndex = i;
	    				step += 1;
	    				break;
	    			}
	    		}
	    	}
	    	
			return step;
	        
	    }
	} 

}
