package com.jzq.leetcodehard;

import java.util.Deque;

public class Hard87 {

	public static void main(String[] args) {
		Solution so = new Hard87().new Solution();
		System.out.println(so.isScramble("great", "rgeat"));
		System.out.println(so.isScramble("great", "rgate"));
		System.out.println(so.isScramble("abcde", "caebd"));
	}
	
	class Solution {
	    public boolean isScramble(String s1, String s2) {
	    	if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
	    	int hash1 = 0, hash2 = 0;
	    	for (int i = 0; i < s1.length(); i++) {
	    		hash1 += s1.charAt(i);
	    		hash2 += s2.charAt(i);
	    	}
	    	if (hash1 != hash2) return false;
	    	
//	    	return check(s1, s2);
	    	return check(s1.toCharArray(), 0, s1.length(), s2.toCharArray(), 0, s2.length());
	    }
	    
	    // 递归实现，使用数组减少字符串复制
	    private boolean check(char[] ch1, int s1, int e1, char[] ch2, int s2, int e2) {
	    	if (e1 - s1 == 1) return true;
	    	int h1 = 0, h2l = 0, h2r = 0, len1 = e1 - s1, len2 = e2 - s2;
	    	for (int i = 0; i < len1 - 1; i++) {
	    		h1 += ch1[s1 + i];
	    		h2l += ch2[s2 + i];
	    		h2r += ch2[e2 - i - 1];
	    		
	    		if (h1 == h2l) {
	    			if (check(ch1, s1, s1 + i + 1, ch2, s2, s2 + i + 1)
	    					&& check(ch1, s1 + i + 1, e1, ch2, s2 + i + 1, e2))
	    				return true;
	    		}
	    		if (h1 == h2r) {
	    			if (check(ch1, s1, s1 + i + 1, ch2, e2 - i - 1, e2)
	    					&& check(ch1, s1 + i + 1, e1, ch2, s2, e2 - i - 1))
	    				return true;
	    		}
	    	}
	    	return false;
	    }
	    
	    // 递归实现
	    private boolean check(String s1, String s2) {
	    	if (s1.length() == 1) return true;
	    	
	    	int h1 = 0, h2l = 0, h2r = 0, len1 = s1.length(), len2 = s2.length();
	    	for (int i = 0; i < len1 - 1; i++) {
	    		h1 += s1.charAt(i);
	    		h2l += s2.charAt(i);
	    		h2r += s2.charAt(len2 - i - 1);
	    		
	    		if (h1 == h2l) {
	    			if (check(s1.substring(0, i + 1), s2.substring(0, i + 1))
	    					&& check(s1.substring(i + 1), s2.substring(i + 1)))
	    				return true;
	    		}
	    		if (h1 == h2r) {
	    			if (check(s1.substring(0, i + 1), s2.substring(len2 - i - 1))
	    					&& check(s1.substring(i + 1), s2.substring(0, len2 - i - 1)))
	    				return true;
	    		}
	    	}
	    	return false;
	    }
	    
	    
	}

}
