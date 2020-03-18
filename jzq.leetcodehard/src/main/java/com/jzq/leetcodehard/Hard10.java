package com.jzq.leetcodehard;

public class Hard10 {
	public static void main(String[] args) {
		System.out.println(new Hard10().new Solution().isMatch2("a", "a*"));
		System.out.println(new Hard10().new Solution().isMatch2("a", "ab"));
		System.out.println(new Hard10().new Solution().isMatch2("a", "ab*"));
		System.out.println(new Hard10().new Solution().isMatch2("abc", "a.*"));
		System.out.println(new Hard10().new Solution().isMatch2("a", "b*a"));
		System.out.println(new Hard10().new Solution().isMatch2("abaac", "aba*."));
		System.out.println(new Hard10().new Solution().isMatch2("mississippi", "mis*is*p*."));
	}
	
	class Solution {
		// 动态规划
	    public boolean isMatch(String s, String p) {
	    	if (isEmpty(s) && isEmpty(p)) return true;
	    	if (isEmpty(p)) return false;
	    	
	    	char[] ch1 = s.toCharArray(), ch2 = p.toCharArray();
	    	boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];
	    	dp[0][0] = true;
	    	for (int i = 1; i < ch2.length + 1; i++) {
	    		char c2 = ch2[i - 1];
	    		if (c2 == '*') {
	    			dp[0][i] = dp[0][i - 2];
	    		} else {
	    			dp[0][i] = false;
	    		}
	    	}
	    	for (int i = 1; i < ch1.length + 1; i++) dp[i][0] = false;
	    	
	    	for (int i = 1; i < ch1.length + 1; i++) {
	    		for (int j = 1; j < ch2.length + 1; j++) {
	    			char c1 = ch1[i - 1], c2 = ch2[j - 1];
	    			if (c2 == '*') {
	    				char c2f = ch2[j - 2];
	    				// 情况1 .*
	    				if (c2f == '.') {
	    					dp[i][j] = dp[i][j - 2];
	    					int tmp = i - 1;
	    					while (!dp[i][j] && tmp >= 0) {
	    						dp[i][j] = dp[tmp--][j - 2];
	    					}
	    					// 情况2 x*
	    				} else {
	    					dp[i][j] = dp[i][j - 2];
	    					int tmp = i - 1;
	    					while (!dp[i][j] && tmp >= 0) {
	    						if (!(c2f == ch1[tmp])) break;
	    						dp[i][j] = dp[tmp][j - 2];
	    						tmp--;
	    					}
	    				}
	    				// 情况3 .
	    			} else if (c2 == '.') {
	    				dp[i][j] = dp[i - 1][j - 1];
	    				// 情况4 x
	    			} else {
	    				dp[i][j] = dp[i - 1][j - 1] && c1 == c2;
	    			}
	    		}
	    	}
	    	
	    	return dp[ch1.length][ch2.length];
	    }
	    
	    // 递归
	    public boolean isMatch2(String s, String p) {
	    	if (isEmpty(s) && isEmpty(p)) return true;
	    	if (isEmpty(p)) return false;
	    	
	    	return match0(s.toCharArray(), p.toCharArray(), 0, 0, s.length(), p.length());
	    }
	    
	    private boolean match0(char[] s, char[] p, int is, int ip, int lens, int lenp) {
	    	if (is == lens && ip == lenp) return true;
	    	if (ip == lenp) return false;
	    	
	    	char p1 = p[ip], p2 = 0;
	    	if (ip + 1 < lenp) p2 = p[ip + 1];
	    	
	    	if (p1 == '.') {
	    		if (p2 == '*') { // .*
	    			for (int i = is; i <= lens; i++) {
	    				if (match0(s, p, i, ip + 2, lens, lenp)) return true;
	    			}
	    			return false;
	    		} else {// .x .0
	    			if (is == lens) return false;
	    			else return match0(s, p, is + 1, ip + 1, lens, lenp);
	    		}
	    	} else {
	    		if (p2 == '*') { // x*
	    			boolean res = match0(s, p, is, ip + 2, lens, lenp);
	    			for (int i = is + 1; i <= lens && s[i - 1] == p1 && !res; i++) {
	    				res = match0(s, p, i, ip + 2, lens, lenp);
	    			}
	    			return res;
	    		} else { // xx
	    			if (is >= lens || s[is] != p[ip]) return false;
	    			return match0(s, p, is + 1, ip + 1, lens, lenp);
	    		}
	    	}
	    }
	    
	    private boolean isEmpty(String s) {
	    	return s == null || s.length() == 0;
	    }
	}
}