package com.jzq.leetcodehard;

public class Hard84 {
	class Solution {
	    public int largestRectangleArea(int[] heights) {
	        if(heights == null || heights.length == 0) return 0;
	        int area = 0;
	        
	        for(int i = 0; i < heights.length; i++) {
	        	int start = i, end = i;
	        	while(start > 0)
	        		if(heights[start - 1] >= heights[i]) start--;
	        		else break;
	        	while(end < heights.length - 1)
	        		if(heights[end + 1] >= heights[i]) end++;
	        		else break;
	        	
	        	area = heights[i] * (end - start + 1) > area ? heights[i] * (end - start + 1) : area;
	        }
	    	
	    	return area;
	    }
	}
}
