package com.jzq.leetcodehard;

public class Hard97 {
	class Solution {
	    public boolean isInterleave(String s1, String s2, String s3) {
	        if(s1.length() + s2.length() != s3.length()) return false;
	        
	        int len1 = s1.length(), len2 = s2.length();
	        char[] ch1 = s1.toCharArray(), ch2 = s2.toCharArray(), ch3 = s3.toCharArray();
	        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
	        dp[0][0] = true;
	        for(int i = 0; i <= len1; i++) {
	        	for(int j = 0; j <= len2; j++) {
	        		if(i != 0 && j != 0) dp[i][j] = (dp[i - 1][j] && ch1[i - 1] == ch3[i + j - 1]) || (dp[i][j - 1] && ch2[j - 1] == ch3[i + j - 1]);
	        		else if(i == 0 && j != 0) dp[i][j] = dp[i][j - 1] && ch2[j - 1] == ch3[i + j - 1];
	        		else if(i != 0 && j == 0) dp[i][j] = dp[i - 1][j] && ch1[i - 1] == ch3[i + j - 1];
	        		else dp[i][j] = true;
	        	}
	        }
	        return dp[len1][len2];
	    }
	}
}
