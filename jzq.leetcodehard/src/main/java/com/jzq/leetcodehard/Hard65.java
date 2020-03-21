package com.jzq.leetcodehard;

public class Hard65 {

	public static void main(String[] args) {

	}
	
	class Solution {
	    public boolean isNumber(String s) {
	    	if(s == null || s.length() == 0) return false;
	    	s = s.trim();
	    	if(s.length() == 0) return false;
	    	
	    	int state = 0;
	    	char [] schar = s.toCharArray();
	    	char cur;
	    	
	    	for(int i = 0; i < schar.length; i++) {
	    		cur = schar[i];
	    		if(state == 0) {
	    			if('0' <= cur && cur <= '9') state = 1;
	    			else if(cur == '.') state = 5;
	    			else if(cur == '+' || cur == '-') state = 7;
	    			else return false;
	    		} else if(state == 1) {
	    			if('0' <= cur && cur <= '9') state = 1;
	    			else if(cur == '.') state = 2;
	    			else if(cur == 'e') state = 3;
	    			else return false;
	    		} else if(state == 2) {
	    			if('0' <= cur && cur <= '9') state = 2;
	    			else if(cur == 'e') state = 3;
	    			else return false;
	    		} else if(state == 3) {
	    			if('0' <= cur && cur <= '9') state = 4;
	    			else if(cur == '+' || cur == '-') state = 6;
	    			else return false;
	    		} else if(state == 4) {
	    			if('0' <= cur && cur <= '9') state = 4;
	    			else return false;
	    		} else if(state == 5) {
	    			if('0' <= cur && cur <= '9') state = 2;
	    			else return false;
	    		} else if(state == 6) {
	    			if('0' <= cur && cur <= '9') state = 4;
	    			else return false;
	    		} else if(state == 7) {
	    			if(cur == '.') state = 5;
	    			else if('0' <= cur && cur <= '9') state = 1;
	    			else return false;
	    		}
	    	}
	    	
	    	if(state == 1 || state == 2 || state == 4) return true;
	    	return false;
			
	        
	    }
	}

}
