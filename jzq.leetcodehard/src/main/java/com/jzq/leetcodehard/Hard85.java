package com.jzq.leetcodehard;

public class Hard85 {
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
	    
	    public int maximalRectangle(char[][] matrix) {
	        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
	        int [][] heights = new int[matrix.length][matrix[0].length];
	        
	        for(int i = 0; i < matrix.length; i++) {
	        	for(int j = 0; j < matrix[0].length; j++) {
	        		if(i == 0) heights[i][j] = matrix[i][j] == '0' ? 0 : 1;
	        		else heights[i][j] = matrix[i][j] == '0' ? 0 : heights[i - 1][j] + 1;
	        	}
	        }
	        int area = 0, temp;
	        for(int i = 0; i < matrix.length; i++) {
	        	area = (temp = largestRectangleArea(heights[i])) > area ? temp : area;
	        }
	        
	        return area;
	    }
	}
}
