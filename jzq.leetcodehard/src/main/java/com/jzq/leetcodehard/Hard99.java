package com.jzq.leetcodehard;

import java.util.ArrayDeque;
import java.util.Deque;

public class Hard99 {

	public static void main(String[] args) {

	}
	
	
	 static class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
	class Solution {
		// 中序遍历，找到两个逆序对
	    public void recoverTree(TreeNode root) {
	    	if (root == null) return;
	    	TreeNode n1 = null, n2 = null;
	    	
	    	Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
	    	TreeNode cur = root, preNode = null;
	    	while (cur != null || !stack.isEmpty()) {
	    		if (cur == null) {
	    			cur = stack.pollLast();
	    			if (preNode != null && preNode.val > cur.val) {
	    				if (n1 == null) 
	    					n1 = preNode;
	    				n2 = cur;
	    			}

	    			preNode = cur;
	    			cur = cur.right;
	    		} else {
	    			stack.addLast(cur);
	    			cur = cur.left;
	    		}
	    	}
	    	
	    	int tmp = n1.val;
	    	n1.val = n2.val;
	    	n2.val = tmp;
	    }
	    
	    // morris
	    public void recoverTree2(TreeNode root) {
	    	if (root == null) return;
	    	TreeNode n1 = null, n2 = null;
	    	TreeNode cur = root, preNode = null, pre = null;
	    	
	    	while (cur != null) {
	    		pre = cur.left;
	    		if (pre != null) {
	    			while (pre.right != null && pre.right != cur) pre = pre.right;
		    		if (pre.right == null) {
		    			pre.right = cur;
		    			cur = cur.left;
		    			continue;
		    		} else {
		    			pre.right = null;
		    		}
	    		}
	    		
	    		if (preNode != null && preNode.val > cur.val) {
	    			if (preNode != null && preNode.val > cur.val) {
	    				if (n1 == null) 
	    					n1 = preNode;
	    				n2 = cur;
	    			}
	    		}
	    		preNode = cur;
	    		cur = cur.right;
	    	}
	    	
	    	int tmp = n1.val;
	    	n1.val = n2.val;
	    	n2.val = tmp;
	    }
	}

}
