package com.jzq.leetcodehard;

public class Hard132 {
	class Solution {
	    public int minCut(String s) {
	        char[] ch = s.toCharArray();
	        int[] cutCnt = new int[ch.length];
	        for(int i = 0; i < ch.length; i++) cutCnt[i] = i;
	        
	        int start, end;
	        for(int i = 1; i < ch.length; i++) {
	        	start = i - 1;
	        	end = i;
	            cutCnt[i] = Math.min(cutCnt[i - 1] + 1, cutCnt[i]);
	        	while(start >= 0 && end < ch.length && ch[start] == ch[end]) {
	        		cutCnt[end] = start == 0 ? 0 : Math.min(cutCnt[end], cutCnt[start - 1] + 1);
	        		end++;
	        		start--;
	        	}
	        	
	        	start = i - 2;
	        	end = i;
	        	while(start >= 0 && end < ch.length && ch[start] == ch[end]) {
	        		cutCnt[end] = start == 0 ? 0 : Math.min(cutCnt[end], cutCnt[start - 1] + 1);
	        		end++;
	        		start--;
	        	}
	        }
	    	
	    	return cutCnt[cutCnt.length - 1];
	    }
	}
}
