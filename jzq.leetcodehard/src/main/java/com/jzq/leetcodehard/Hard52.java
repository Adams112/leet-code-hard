package com.jzq.leetcodehard;

import java.util.ArrayList;
import java.util.List;

public class Hard52 {

	public static void main(String[] args) {

	}
	class Solution {
	    public int totalNQueens(int n) {
	        return solveNQueens(n).size();
	    }
	    
	    public List<List<String>> solveNQueens(int n) {
	    	List<List<String>> lists = new ArrayList<List<String>>();
	    	
	        char [][] queens = new char [n][n];
	        for(int i = 0; i < n; i++)
	        	for(int j = 0; j < n; j++)
	        		queens[i][j] = 'Q';
	    	solveNQueens(lists, queens, 0);
	    	return lists;
	    }
	    
	    private void solveNQueens(List<List<String>> lists, char [][] queens, int index) {
	    	if(index == queens.length) {
	    		lists.add(convert(queens));
	    		return;
	    	}
	    	
	    	for(int i = 0; i < queens.length; i++) {
	    		if(queens[index][i] == 'Q') {
	    			char [][] newQueens = copy(queens);
	    			
	    			for(int j = 0; j < i; j++) {
	    				newQueens[index][j] = '.';
	    				if(index + i - j < queens.length) newQueens[index + i - j][j] = '.';
	    			}
	    			for(int j = i + 1; j < queens.length; j++) {
	    				newQueens[index][j] = '.';
	    				if(index + j - i < queens.length) newQueens[index + j - i][j] = '.';
	    			}
	    			for(int j = index + 1; j < queens.length; j++) {
	    				newQueens[j][i] = '.';
	    			}
	    			solveNQueens(lists, newQueens, index + 1);
	    		}
	    	}
	    }
	    
	    private char [][] copy(char [][] from) {
	    	char [][] ret = new char [from.length][from[0].length];
	    	for(int i = 0; i < from.length; i++)
	    		for(int j = 0; j < from[0].length; j++)
	    			ret[i][j] = from[i][j];
	    	return ret;
	    }
	    
	    private List<String> convert(char [][] queens) {
	    	List<String> list = new ArrayList<String>();
	    	for(int i = 0; i < queens.length; i++) {
	    		list.add(String.valueOf(queens[i]));
	    	}
	    	return list;
	    }
	}
}
