package com.jzq.leetcodehard;

public class Hard72 {
	class Solution {
	    public int minDistance(String word1, String word2) {
	        int len1 = word1 == null ? 0 : word1.length();
	        int len2 = word2 == null ? 0 : word2.length();
	        if(len1 == 0) return len2;
	        if(len2 == 0) return len1;
	        
	        int [][] dist = new int[len1 + 1][len2 + 1];
	        int a, b, c;
	        dist[0][0] = 0;
	        for(int i = 1; i < len2 + 1; i++) dist[0][i] = i;
	        for(int i = 1; i < len1 + 1; i++) dist[i][0] = i;
	        
	        for(int i = 2; i < len1 + len2 + 2; i++) 
	        	for(int m = 1; m < i; m++) 
	        		if(m <= len1 && i - m <= len2)
	        			dist[m][i - m] = word1.charAt(m - 1) == word2.charAt(i - m - 1) ? dist[m - 1][i - m - 1] :
	        				1 + (((a = dist[m - 1][i - m]) < (b = dist[m - 1][i - m - 1])) ? (a < (c = dist[m][i - m - 1]) ? a : c) : (b < (c = dist[m][i - m - 1]) ? b : c));
	    	
	    	return dist[len1][len2];
	    }
	}
}
