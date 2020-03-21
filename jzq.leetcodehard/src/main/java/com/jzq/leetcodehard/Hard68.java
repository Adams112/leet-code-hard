package com.jzq.leetcodehard;

import java.util.ArrayList;
import java.util.List;

public class Hard68 {

	class Solution {
	    public List<String> fullJustify(String[] words, int maxWidth) {
	    	List<String> out = new ArrayList<>();
	    	int start = 0;
	    	int end = 0;
	    	int totalLen = 0;
	    	while(true) {
	    		if(end == words.length) {
	    			//左对齐
	    			out.add(leftAlign(words, maxWidth, start, end, totalLen));
	    			break;
	    		} else {
	    			if(totalLen + words[end].length() + end - start <= maxWidth) {
	    				totalLen += words[end].length();
	    				end += 1;
	    			} else {
	    				if(end == start + 1) {
	    					out.add(leftAlign(words, maxWidth, start, end, totalLen));
	    					totalLen = 0;
	    					start = end;
	    				} else {
	    					out.add(leftRightAlign(words, maxWidth, start, end, totalLen));
	    					totalLen = 0;
	    					start = end;
	    				}
	    			}
	    		}
	    	}
	    	return out;
	       
	    }
	    
	    private String leftAlign(String [] words, int maxWidth, int start, int end, int totalLen) {
	    	StringBuilder sb = new StringBuilder();
	    	for(int i = start; i < end - 1; i++) {
	    		sb.append(words[i]);
	    		sb.append(" ");
	    	}
	    	sb.append(words[end - 1]);
	    	
	    	int appendBlanks = maxWidth - totalLen - (end - start - 1);
	    	while(appendBlanks-- > 0) {
	    		sb.append(" ");
	    	}
	    	
	    	return sb.toString();
	    }
	    
	    private String leftRightAlign(String [] words, int maxWidth, int start, int end, int totalLen) {
	    	StringBuilder sb = new StringBuilder();
	    	int blanks = maxWidth - totalLen;
	    	int average = blanks / (end - start - 1);
	    	int remains = blanks - average * (end - start - 1);
	    	
	    	for(int i = start; i < end - 1; i++) {
	    		sb.append(words[i]);
	    		sb.append(generateBlanks(i - start < remains ? average + 1 : average));
	    	}
	    	sb.append(words[end - 1]);
	    	return sb.toString();
	    }
	    
	    private String generateBlanks(int count) {
	    	StringBuilder sb = new StringBuilder();
	    	while(count-- > 0) sb.append(" ");
	    	return sb.toString();
	    }
	}
}
