package com.jzq.leetcodehard;

public class Hard32 {

	public static void main(String[] args) {

	}
	
	class Solution {
	    public int longestValidParentheses(String s) {
	    	if(s == null || s.length() == 0) return 0;
	    	int len = s.length();
	    	
	    	int [] number = new int[len + 1];
	    	char [] chars = s.toCharArray();
	    	number[0] = 0;
	    	for(int i = 0; i < len; i++) {
	    		if(chars[i] == '(') number[i + 1] = number[i] + 1;
	    		else number[i + 1] = number[i] - 1;
	    	}
	    	
	    	int max = 0;
	    	for(int i = 0; i < len; i++) {
	    		for(int j = i; j <= len; j++) {
	    			if(number[j] < number[i]) break;
	    			if(number[j] == number[i]) max = max > j - i ? max : j - i;
	    		}
	    	}
	    	return max;
	        
	    }
	}

}
