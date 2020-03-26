package com.jzq.leetcodehard;

public class Hard135 {
	class Solution {
	    public int candy(int[] ratings) {
	        int[] candy = new int[ratings.length];
	        int sum = 0;
	        candy[0] = 1;
	        for(int i = 1; i < candy.length; i++) {
	        	if(ratings[i] > ratings[i - 1]) candy[i] = candy[i - 1] + 1;
	        	else candy[i] = 1;
	        }
	        
	        boolean changed = true;
	        while(changed) {
	        	changed = false;
	        	for(int i = 0; i < candy.length - 1; i++) {
	        		if(ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) {
	        			candy[i] = candy[i + 1] + 1;
	        			changed = true;
	        		} else if(ratings[i] < ratings[i + 1] && candy[i] >= candy[i + 1]) {
	        			candy[i + 1] = candy[i] + 1;
	        			changed = true;
	        		}
	        		
	        	}
	        }
	        for(int c: candy) sum += c;
	    	
	    	return sum;
	    }
	}
}
