package com.jzq.leetcodehard;

public class Hard115 {

	public static void main(String[] args) {
		Solution so = new Hard115().new Solution();
		System.out.println(so.numDistinct("rabbbit", "rabbit"));
		System.out.println(so.numDistinct("babgbag", "bag"));
	}
	
	class Solution {
	    public int numDistinct(String s, String t) {
	    	if (s == null || t == null || s.length() == 0 || t.length() == 0) return 0;
	    	int lens = s.length(), lent = t.length();
	    	int[][] dp = new int[lens + 1][lent + 1];
	    	for (int i = 0; i <= lens; i++) dp[i][0] = 1;
	    	
	    	for (int i = 1; i <= lens; i++) {
	    		for (int j = 1; j <= lent; j++) {
	    			char c1 = s.charAt(i - 1), c2 = t.charAt(j - 1);
	    			dp[i][j] = dp[i - 1][j];
	    			if (c1 == c2)
	    				dp[i][j] += dp[i - 1][j - 1];
	    		}
	    	}
	    	return dp[lens][lent];
	    }

	}

}
