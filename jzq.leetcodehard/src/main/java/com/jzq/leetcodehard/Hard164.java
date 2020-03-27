package com.jzq.leetcodehard;

public class Hard164 {
	class Solution {
	    public int maximumGap(int[] nums) {
	        if(nums.length < 2) return 0;
	        
	        int minValue = nums[0], maxValue = nums[0];
	        for(int i = 1; i < nums.length; i++) {
	        	minValue = Math.min(minValue, nums[i]);
	        	maxValue = Math.max(maxValue, nums[i]);
	        }
	        
	        int t = (maxValue - minValue) / (nums.length - 1);
	        t = t > 1 ? t : 1;
	        int numsOfBucket = (maxValue - minValue) / t + 1;
	        int[] bucketLow = new int[numsOfBucket], bucketHigh = new int[numsOfBucket];
	        for(int i = 0; i < numsOfBucket; i++) {
	        	bucketLow[i] = -1;
	        	bucketHigh[i] = -1;
	        }
	        
	        for(int n: nums) {
	        	int index = (n - minValue) / t;
	        	if(bucketLow[index] == -1 || n < bucketLow[index]) bucketLow[index] = n;
	        	if(bucketHigh[index] == -1 || n > bucketHigh[index]) bucketHigh[index] = n;
	        }
	        
	        int maxGap = 0;
	        
	        int preBucketIndex = 0, nextBucketIndex = 0;
	        while(nextBucketIndex < numsOfBucket) {
	        	preBucketIndex = nextBucketIndex;
	        	while(preBucketIndex < numsOfBucket && bucketHigh[preBucketIndex] == -1) {
	        		preBucketIndex++;
	        	}
	        	nextBucketIndex = preBucketIndex + 1;
	        	while(nextBucketIndex < numsOfBucket && bucketLow[nextBucketIndex] == -1) {
	        		nextBucketIndex++;
	        	}
	        	if(preBucketIndex < numsOfBucket && nextBucketIndex < numsOfBucket)
	        		maxGap = Math.max(maxGap, bucketLow[nextBucketIndex] -  bucketHigh[preBucketIndex]);
	        }
	    	
	    	return maxGap;
	    }
	}
}
