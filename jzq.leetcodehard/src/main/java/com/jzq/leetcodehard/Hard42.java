package com.jzq.leetcodehard;

public class Hard42 {

	public static void main(String[] args) {

	}
	
	class Solution {
	    public int trap(int[] height) {
	    	if(height == null || height.length < 3) return 0;
	    	
	    	int maxIndex = 0;
	    	for(int i = 0; i < height.length; i++) {
	    		if(height[maxIndex] < height[i]) maxIndex = i;
	    	}
	    	
	    	int rain = 0;
	    	int leftHeight = height[0];
	    	for(int i = 1; i < maxIndex; i++) {
	    		if(height[i] < leftHeight) rain += leftHeight - height[i];
	    		else leftHeight = height[i];
	    	}
	    	int rightHeight = height[height.length - 1];
	    	for(int i = height.length - 2; i > maxIndex; i--) {
	    		if(height[i] < rightHeight) rain += rightHeight - height[i];
	    		else rightHeight = height[i];
	    	}
	    	
			return rain;
	        
	    }
	}

}
