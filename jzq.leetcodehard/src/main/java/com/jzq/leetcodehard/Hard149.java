package com.jzq.leetcodehard;

import java.util.HashMap;

public class Hard149 {
	class Solution {
	    public int maxPoints(int[][] points) {
	    	if(points == null) return 0;
	        if(points.length < 3) return points.length;
	    	
	        int maxCount = 0;
	        for(int i = 0; i < points.length - 1; i++) {
	        	int dup = 0, count = 1, x = points[i][0], y = points[i][1];
	        	HashMap<Double, Integer> map = new HashMap<Double, Integer>();
	        	
	        	for(int j = i + 1; j < points.length; j++) {
	        		if(points[j][0] == x && points[j][1] == y) dup++;
	        		else {
	        			double slope = points[j][1] == y ? y : 1.0 * (points[j][0] - x) / (points[j][1] - y) + 0.0;
	        			int cnt = map.getOrDefault(slope, 1) + 1;
	        			map.put(slope, cnt);
	        			count = count > cnt ? count : cnt;
	        		}
	        	}
	        	maxCount = Math.max(maxCount, count + dup);
	        }
	    	return maxCount;
	    }
	}
}
