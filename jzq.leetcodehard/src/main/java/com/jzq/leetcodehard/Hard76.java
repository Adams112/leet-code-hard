package com.jzq.leetcodehard;

import java.util.HashMap;

public class Hard76 {
	class Solution {
	    public String minWindow(String s, String t) {
	        if(s.length() == 0 || t.length() == 0) return "";
	        
	        HashMap<Character, Integer> mapt = new HashMap<Character, Integer>();
	        for(char c: t.toCharArray()) mapt.put(c, mapt.getOrDefault(c, 0) + 1);
	        
	        int required = mapt.size(), formed = 0;
	        
	        HashMap<Character, Integer> maps = new HashMap<Character, Integer>();
	        
	        int l = 0, r = -1, left = 0, right = Integer.MAX_VALUE;
	        
	        while(++r < s.length()) {
	        	char c = s.charAt(r);
	        	maps.put(c, maps.getOrDefault(c, 0) + 1);
	        	if(mapt.containsKey(c) && mapt.get(c).intValue() == maps.get(c).intValue()) formed++;
	        	
	        	while(l <= r && formed == required) {
	        		if(r - l < right - left) {
	        			right = r;
	        			left = l;
	        		}
	        		
	        		c = s.charAt(l);
	        		maps.put(c, maps.get(c) - 1);
	        		if(maps.get(c).intValue() < mapt.getOrDefault(c, 0).intValue()) formed--;
	        		l++;
	        	}
	        }
	    	
	    	return right == Integer.MAX_VALUE ? "" : s.substring(left, right + 1);
	    }
	}
}
