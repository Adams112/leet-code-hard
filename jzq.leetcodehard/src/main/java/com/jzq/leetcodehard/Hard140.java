package com.jzq.leetcodehard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Hard140 {

	public static void main(String[] args) {
//		test("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
//		test("aaaaaaaaaaa", Arrays.asList("a", "aa"));
//		test("aaaaaaaaaaaa", Arrays.asList("a", "aa"));
//		test("aaaaaaaaaaaaa", Arrays.asList("a", "aa"));
//		test("aaaaaaaaaaaaaa", Arrays.asList("a", "aa"));
//		test("aaaaaaaaaaaaaaa", Arrays.asList("a", "aa"));
//		test("aaaaaaaaaaaaaaaa", Arrays.asList("a", "aa"));
//		test("aaaaaaaaaaaaaaaaa", Arrays.asList("a", "aa"));
		test("aaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa",
				"aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"));
	}

	public static void test(String s, List<String> wordDict) {
		System.out.println(new Hard140().new Solution().wordBreak(s, wordDict).size());
	}

	class Solution {

		class Node {
			List<Wrap> list;
		}

		class Wrap {
			String word;
			Node node;

			public Wrap(String word, Node node) {
				this.word = word;
				this.node = node;
			}
		}
		
		// 16ms 41.13%
		// 39.4MB 13.54% 
		public List<String> wordBreak(String s, List<String> wordDict) {
			HashMap<String, List<String>> wordMap = new HashMap<String, List<String>>(wordDict.size());
			HashSet<Character> startCharSet = new HashSet<Character>();
			int maxWordLen = 0, sLen = s.length();
			for (String word : wordDict) {
				int wordLen = word.length();
				maxWordLen = maxWordLen >= wordLen ? maxWordLen : wordLen;
				String key = "" + word.charAt(0) + wordLen;
				startCharSet.add(word.charAt(0));

				if (wordMap.containsKey(key))
					wordMap.get(key).add(word);
				else {
					List<String> list = new LinkedList<String>();
					list.add(word);
					wordMap.put(key, list);
				}
			}

			boolean[] mayHasWord = new boolean[s.length() + 1];
			mayHasWord[s.length()] = true;
			for (int i = 0; i < s.length(); i++) {
				if (startCharSet.contains(s.charAt(i)))
					mayHasWord[i] = true;
			}

			Node[] nodes = new Node[sLen + 1];
			Node end = new Node();
			nodes[s.length()] = end;

			for (int i = sLen - 1; i >= 0; i--) {
				if (!mayHasWord[i])
					continue;

				char ch = s.charAt(i);
				for (int j = i + 1; j <= sLen && j - i <= maxWordLen; j++) {
					if (!mayHasWord[j])
						continue;

					List<String> words = wordMap.get("" + ch + (j - i));
					if (words == null)
						continue;

					for (String word : words) {
						if (s.startsWith(word, i) && nodes[j] != null) {
							if (nodes[i] == null)
								nodes[i] = new Node();
							if (nodes[i].list == null)
								nodes[i].list = new LinkedList<Hard140.Solution.Wrap>();
							nodes[i].list.add(new Wrap(word, nodes[j]));
						}
					}
				}
			}

			List<String> result = new LinkedList<String>();
			if (nodes[0] == null)
				return result;

			for (Wrap wrap : nodes[0].list) {
				StringBuffer buffer = new StringBuffer(wrap.word);
				result(wrap.node, buffer, end, result);
			}

			return result;
		}

		private void result(Node node, StringBuffer buffer, Node end, List<String> result) {
			if (node != end) {
				for (Wrap wrap : node.list) {
					buffer.append(" ");
					buffer.append(wrap.word);
					result(wrap.node, buffer, end, result);
					buffer.delete(buffer.length() - 1 - wrap.word.length(), buffer.length());
				}
			} else {
				result.add(buffer.toString());
			}
		}
	}
}
