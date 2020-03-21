package com.jzq.leetcodehard;

public class Hard57 {

	public static void main(String[] args) {

	}
	
	class Solution {
	    public int[][] insert(int[][] intervals, int[] newInterval) {
	    	if(intervals == null || intervals.length == 0) return new int [][] {{newInterval[0], newInterval[1]}};
	    	if(newInterval == null || newInterval.length == 0) return intervals;
	    	
	    	boolean isLeftContain = false, isRightContain = false, leftChecked = false;
	    	int leftIndex = Integer.MAX_VALUE, rightIndex = Integer.MAX_VALUE;
	    	
	    	for(int i = 0; i < intervals.length; i++) {
	    		int [] interval = intervals[i];
	    		if(!leftChecked) {
	    			if(interval[0] > newInterval[0]) {
	    				isLeftContain = false;
	    				leftIndex = i;
	    				leftChecked = true;
	    			} else if(interval[0] <= newInterval[0] && interval[1] >= newInterval[0]) {
	    				isLeftContain = true;
	    				leftIndex = i;
	    				leftChecked = true;
	    			}
	    		}
	    		
	    		if(leftChecked) {
	    			if(interval[0] > newInterval[1]) {
	    				isRightContain = false;
	    				rightIndex = i - 1;
	    				break;
	    			} else if(interval[0] <= newInterval[1] && interval[1] >= newInterval[1]) {
	    				isRightContain = true;
	    				rightIndex = i;
	    				break;
	    			}
	    		}
	    	}
	    	
	    	int [][] ret = null;
	    	
	    	if(leftIndex != Integer.MAX_VALUE && rightIndex != Integer.MAX_VALUE) {
	        	ret = new int[intervals.length - (rightIndex - leftIndex)][2];
	        	int retIndex = 0;
	        	for(int i = 0; i < leftIndex; i++) {
	        		ret[retIndex][0] = intervals[i][0];
	        		ret[retIndex][1] = intervals[i][1];
	        		retIndex += 1;
	        	}
	        	if(isLeftContain && isRightContain) {
	            	ret[retIndex][0] = intervals[leftIndex][0];
	            	ret[retIndex][1] = intervals[rightIndex][1];
	            	retIndex += 1;
	        	} else if(!isLeftContain && isRightContain) {
	            	ret[retIndex][0] = newInterval[0];
	            	ret[retIndex][1] = intervals[rightIndex][1];
	            	retIndex += 1;
	        	} else if(isLeftContain && !isRightContain) {
	            	ret[retIndex][0] = intervals[leftIndex][0];
	            	ret[retIndex][1] = newInterval[1];
	            	retIndex += 1;
	        	}else {
	            	ret[retIndex][0] = newInterval[0];
	            	ret[retIndex][1] = newInterval[1];
	            	retIndex += 1;
	        	}
	        	for(int i = rightIndex + 1; i < intervals.length; i++) {
	        		ret[retIndex][0] = intervals[i][0];
	        		ret[retIndex][1] = intervals[i][1];
	        		retIndex += 1;
	        	}
	    	} else {
	        	if(leftIndex == Integer.MAX_VALUE) {
	        		ret = new int [intervals.length + 1][2];
	        		int retIndex = 0;
	        		for(int i = 0; i < intervals.length; i++) {
	            		ret[retIndex][0] = intervals[i][0];
	            		ret[retIndex][1] = intervals[i][1];
	            		retIndex += 1;
	        		}
	            	ret[retIndex][0] = newInterval[0];
	            	ret[retIndex][1] = newInterval[1];
	            	retIndex += 1;
	        	} else {
	        		if(isLeftContain) {
	            		ret = new int [leftIndex + 1][2];
	            		int retIndex = 0;
	            		for(int i = 0; i < leftIndex; i++) {
	                		ret[retIndex][0] = intervals[i][0];
	                		ret[retIndex][1] = intervals[i][1];
	                		retIndex += 1;
	            		}
	                	ret[retIndex][0] = intervals[leftIndex][0];
	                	ret[retIndex][1] = newInterval[1];
	                	retIndex += 1;
	        		} else {
	        			ret = new int [leftIndex + 1][2];
	        			int retIndex = 0;
	            		for(int i = 0; i < leftIndex; i++) {
	                		ret[retIndex][0] = intervals[i][0];
	                		ret[retIndex][1] = intervals[i][1];
	                		retIndex += 1;
	            		}
	                	ret[retIndex][0] = newInterval[0];
	                	ret[retIndex][1] = newInterval[1];
	                	retIndex += 1;
	        		}	
	        	}
	    	}
			return ret;
	        
	    }

	}

}
