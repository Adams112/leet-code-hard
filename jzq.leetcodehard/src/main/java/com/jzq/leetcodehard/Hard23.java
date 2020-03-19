package com.jzq.leetcodehard;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Hard23 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	
	
	
	class Solution {
		// 优先队列实现
	    public ListNode mergeKLists(ListNode[] lists) {
	    	if (lists == null || lists.length == 0) return null;
	    	
	    	PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, 
	    			(n1, n2) -> {return n1.val - n2.val;});
	    	for (ListNode node: lists) {
	    		if (node != null)
	    			queue.add(node);
	    	}
	    	ListNode head = new ListNode(0), tail = head;
	    	while (!queue.isEmpty()) {
	    		ListNode node = queue.poll();
	    		tail = tail.next = node;
	    		if (node.next != null) 
	    			queue.add(node.next);
	    	}
	    	
	    	return head.next;
	    }
	}
	
	
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

}
