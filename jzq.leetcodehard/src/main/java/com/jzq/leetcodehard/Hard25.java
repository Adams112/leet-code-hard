package com.jzq.leetcodehard;

import java.util.ArrayDeque;
import java.util.Deque;

public class Hard25 {

	public static void main(String[] args) {
		ListNode head = new ListNode(1), tail = head;
		tail = tail.next = new ListNode(2);
		tail = tail.next = new ListNode(3);
		tail = tail.next = new ListNode(4);
		tail = tail.next = new ListNode(5);
		
		ListNode node = new Hard25().new Solution().reverseKGroup2(head, 2);
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
		
	}
	
	class Solution {
		
		// 栈
	    public ListNode reverseKGroup(ListNode head, int k) {
	    	if (head == null) return null;
	        Deque<ListNode> stack = new ArrayDeque<ListNode>(k);
	        
	        ListNode h = new ListNode(0), t = h;
	        while (head != null || !stack.isEmpty()) {
	        	if (head != null) {
	        		stack.addLast(head);
		        	head = head.next;
	        	}
	        	
	        	if (stack.size() == k) {
	        		while (!stack.isEmpty()) {
	        			t = t.next = stack.pollLast();
	        		}
	        		t.next = null;
	        	} else if (head == null) {
	        		while (!stack.isEmpty()) {
	        			t = t.next = stack.pollFirst();
	        		}
	        		t.next = null;
	        	}
	        }
	        
	        return h.next;
	    }
	    
	    // 手动翻转
	    public ListNode reverseKGroup2(ListNode head, int k) {
	    	ListNode nh = new ListNode(0), nt = nh, tail = head;
	    	int cnt = 0;
	    	while (tail != null || cnt != 0) {
	    		if (cnt == k) {
	    			// 头插法
	    			ListNode nh1 = head, nt1 = head, tmp = head = head.next;
	    			while (head != tail) {
	    				tmp = head.next;
	    				head.next = nh1;
	    				nh1 = head;
	    				head = tmp;
	    			}
	    			nt.next = nh1;
	    			nt = nt1;
	    			nt.next = null;
	    			cnt = 0;
	    		} else if (tail == null) {
	    			nt.next = head;
	    			cnt = 0;
	    		} else {
	    			tail = tail.next;
	    			cnt++;
	    		}
	    	}
	    	
	    	return nh.next;
	    }
	}
	
	
	 static class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }

}
