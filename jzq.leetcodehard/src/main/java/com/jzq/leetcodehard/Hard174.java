package com.jzq.leetcodehard;

public class Hard174 {
	class Solution {
	    public int calculateMinimumHP(int[][] dungeon) {
	    	if(dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0) return 1;
	    	
	    	int r = dungeon.length, c = dungeon[0].length;
	    	if(r == 1 && c == 1) return dungeon[0][0] > 0 ? 1 : 1 - dungeon[0][0];
	    	
	    	dungeon[r - 1][c - 1] = dungeon[r - 1][c - 1] > 0 ? 1 : 1 - dungeon[r - 1][c - 1];
	        for(int s = r + c - 3; s >= 0; s--) {
	        	for(int i = 0; i < dungeon.length; i++) {
	        		int j = s - i;
	        		if(j >= 0 && j < dungeon[0].length) {
	        			int b1 = i < r - 1 ? (dungeon[i + 1][j] - dungeon[i][j] > 0 ? dungeon[i + 1][j] - dungeon[i][j] : 1) : Integer.MAX_VALUE;
	        			int b2 = j < c - 1 ? (dungeon[i][j + 1] - dungeon[i][j] > 0 ? dungeon[i][j + 1] - dungeon[i][j] : 1) : Integer.MAX_VALUE;
	        			dungeon[i][j] = b1 < b2 ? b1 : b2;
	        		}
	        	}
	        }
	    	
	    	return dungeon[0][0];
	    }
	}
}
