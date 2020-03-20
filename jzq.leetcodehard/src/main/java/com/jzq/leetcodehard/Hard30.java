package com.jzq.leetcodehard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hard30 {

	public static void main(String[] args) {
		System.out.println(new Hard30().new Solution().findSubstring("barfoothefoobarman", new String[] {"foo","bar"}));
	}
	
	class Solution {
	    public List<Integer> findSubstring(String s, String[] words) {
	    	List<Integer> list = new ArrayList<Integer>();
	    	if (s == null || s.length() == 0 || words == null || words.length == 0)
	    		return list;
	    	
	    	int wordHash = 0, wlen = 0;
	    	for (String w: words) {
	    		for (char c: w.toCharArray()) {
	    			wordHash += c;
	    		}
	    		wlen += w.length();
	    	}
	    	int[] stringHash = new int[s.length()];
	    	int hash = 0;
	    	char[] chs = s.toCharArray();
	    	for (int i = 0; i < chs.length; i++) {
	    		hash += chs[i];
	    		if (i >= wlen - 1) {
	    			stringHash[i - wlen + 1] = hash;
	    			hash -= chs[i - wlen + 1];
	    		}
	    	}
	    	
	    	Map<String, Integer> map = new HashMap<String, Integer>();
	    	for (String word: words) map.put(word, map.getOrDefault(word, 0) + 1);
	    	for (int i = 0; i < chs.length - wlen + 1; i++) {
	    		if (stringHash[i] != wordHash) continue;
	    		
	    		if(check(s, i, words.length, 0, map, new HashMap<String, Integer>())) list.add(i);
	    	}
	    	
	    	return list;
	    }
	    
	    private boolean check(String s, int from, int wcnt, int cnt,
	    		Map<String, Integer> wordMap, Map<String, Integer> findMap) {
	    	if (cnt == wcnt) return true;
	    	Set<String> set = wordMap.keySet();
	    	for (String word: set) {
	    		if (s.startsWith(word, from)) {
	    			findMap.put(word, findMap.getOrDefault(word, 0) + 1);
	    			if (findMap.get(word) > wordMap.get(word)) return false;
	    			if (check(s, from + word.length(), wcnt, cnt + 1, wordMap, findMap)) return true;
	    			findMap.put(word, findMap.get(word) - 1);
	    		}
	    	}
	    	return false;
	    }
	}

}
