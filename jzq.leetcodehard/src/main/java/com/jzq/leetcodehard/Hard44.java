package com.jzq.leetcodehard;

import java.util.ArrayList;
import java.util.List;

public class Hard44 {

	public static void main(String[] args) {

	}

	
	class Solution {
	    public boolean isMatch(String s, String p) {
	    	if((s == null || "".equals(s)) && (p == null || "".equals(p))) return true;
	    	if(!(s == null || "".equals(s)) && (p == null || "".equals(p))) return false;
	    	
			StringBuffer sb = new StringBuffer(p);
			while(replace(sb));
			
			boolean startWith = sb.charAt(0) == '*';
			boolean endWith = sb.charAt(sb.length() - 1) == '*';
			
			List<String> ps = new ArrayList<>();
			
			int start = -1, end = -1;
			boolean started = false;
			for(int i = 0; i < sb.length(); i++) {
				if(sb.charAt(i) == '*') {
					if(started == false) {
						if(startWith) {
							started = true;
							start = i + 1;
						} else {
							started = true;
							start = i + 1;
							ps.add(sb.substring(0, i));
						}
						
					} else {
						end = i;
						ps.add(sb.substring(start, end));
						start = i + 1;
						end = -1;
					}
				}
			}
			
			if(start == -1) ps.add(sb.toString());
			else if(end == -1) ps.add(sb.substring(start));
			
			char [] schar = s.toCharArray();
			start = 0;
			for(int i = 0; i < ps.size(); i++) {
				char [] pchar = ps.get(i).toCharArray();
				start = find(schar, pchar, start);
				if(start == -1) return false;
				else if(i == 0 && startWith == false) {
					if(start != 0) return false;
					if(ps.size() == 1 && schar.length != pchar.length) return false;
				}
	            else if(i == ps.size() - 1) {
					if(!endWith) {
						while(start != -1 && start != schar.length - pchar.length) start = find(schar, pchar, start + 1);
						if(start == -1) return false;
					}
				}
				
				start += pchar.length;
			}
			return true;
	    }
	    
	    private boolean replace(StringBuffer sb) {
			char p1, p2;
			int i = 0;
			boolean flag = false;
			while(i < sb.length() - 1) {
				p1 = sb.charAt(i);
				p2 = sb.charAt(i + 1);
				if(p1 == '*' && p2 == '*') {
					sb.deleteCharAt(i);
					flag = true;
				}
				else i++;
			}
			return flag;
	    }
		
	    private int find(char [] s, char [] p, int start) {
	    	for(int i = start; i < s.length - p.length + 1; i++) {
	    		int sstart = i;
	    		boolean flag = true;
	    		int j = 0;
	    		while(j < p.length) {
	    			if(p[j] == '?') {
	    				j += 1;
	    				sstart += 1;
	    			} else if(p[j] == s[sstart]) {
	    				j += 1;
	    				sstart += 1;
	    			} else {
	    				flag = false;
	    				break;
	    			}
	    		}
	    		if(flag) return i;
	    	}
	    	
	    	return -1;
	    }

	}
}
