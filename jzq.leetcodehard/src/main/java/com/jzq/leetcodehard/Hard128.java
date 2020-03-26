package com.jzq.leetcodehard;

import java.util.HashSet;

public class Hard128 {
	class Solution {
	    public int longestConsecutive(int[] nums) {
	        HashSet<Integer> set = new HashSet<Integer>();
	        for(int n: nums) set.add(n);
	    	int len = 0, lenTemp = 0, number;
	        for(int n: set) {
	        	if(!set.contains(n - 1)) {
	        		number = n;
	        		lenTemp = 1;
	        		while(set.contains(++number)) lenTemp++;
	        		len = len > lenTemp ? len : lenTemp;
	        	}
	        }
	    	
	    	return len;
	    }
	}
}
