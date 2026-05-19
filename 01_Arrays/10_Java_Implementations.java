package Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * MASTERCLASS: Array Implementations
 * This file contains production-ready, highly optimized Java implementations
 * for the most critical array algorithms. Every class can be run independently
 * to see the output and test edge cases.
 */
public class ArrayMasterclass {

    public static void main(String[] args) {
        System.out.println("=== EXECUTING ARRAY MASTERCLASS TESTS ===");
        
        testLinearScan();
        testTwoPointers();
        testDutchNationalFlag();
        testKadanesAlgorithm();
        testTwoSum();
        testBoyerMooreVoting();
        testNextPermutation();
        testCyclicSort();
    }

    // =========================================================================
    // 1. LINEAR SCAN: Find Largest Element
    // =========================================================================
    static class LinearScan {
        public static int findLargest(int[] arr) {
            if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array is empty");
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) max = arr[i];
            }
            return max;
        }
    }

    private static void testLinearScan() {
        System.out.println("\n--- Testing Linear Scan (Find Largest) ---");
        int[] arr = {-5, -2, -9, -1};
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Largest: " + LinearScan.findLargest(arr) + " (Expected: -1)");
    }

    // =========================================================================
    // 2. TWO POINTERS: Reverse Array In-Place
    // =========================================================================
    static class TwoPointers {
        public static void reverse(int[] arr) {
            if (arr == null || arr.length <= 1) return;
            int left = 0, right = arr.length - 1;
            while (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
    }

    private static void testTwoPointers() {
        System.out.println("\n--- Testing Two Pointers (Reverse Array) ---");
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println("Original: " + Arrays.toString(arr));
        TwoPointers.reverse(arr);
        System.out.println("Reversed: " + Arrays.toString(arr) + " (Expected: [50, 40, 30, 20, 10])");
    }

    // =========================================================================
    // 3. DUTCH NATIONAL FLAG: Sort Colors (0s, 1s, 2s)
    // =========================================================================
    static class DutchNationalFlag {
        public static void sort(int[] nums) {
            int low = 0, mid = 0, high = nums.length - 1;
            while (mid <= high) {
                if (nums[mid] == 0) {
                    swap(nums, low++, mid++);
                } else if (nums[mid] == 1) {
                    mid++;
                } else {
                    swap(nums, mid, high--);
                }
            }
        }
        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
        }
    }

    private static void testDutchNationalFlag() {
        System.out.println("\n--- Testing Dutch National Flag ---");
        int[] arr = {2, 0, 2, 1, 1, 0, 1, 2, 0};
        System.out.println("Original: " + Arrays.toString(arr));
        DutchNationalFlag.sort(arr);
        System.out.println("Sorted:   " + Arrays.toString(arr));
    }

    // =========================================================================
    // 4. KADANE'S ALGORITHM: Maximum Subarray Sum
    // =========================================================================
    static class Kadane {
        public static int maxSubArray(int[] nums) {
            int maxSum = nums[0];
            int currentSum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                currentSum = Math.max(nums[i], currentSum + nums[i]);
                maxSum = Math.max(maxSum, currentSum);
            }
            return maxSum;
        }
    }

    private static void testKadanesAlgorithm() {
        System.out.println("\n--- Testing Kadane's Algorithm ---");
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Max Subarray Sum: " + Kadane.maxSubArray(arr) + " (Expected: 6 [4,-1,2,1])");
    }

    // =========================================================================
    // 5. HASHING: Two Sum
    // =========================================================================
    static class TwoSumHashing {
        public static int[] find(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>((int)(nums.length / 0.75f) + 1);
            for (int i = 0; i < nums.length; i++) {
                int comp = target - nums[i];
                if (map.containsKey(comp)) return new int[] { map.get(comp), i };
                map.put(nums[i], i);
            }
            return new int[]{};
        }
    }

    private static void testTwoSum() {
        System.out.println("\n--- Testing Two Sum ---");
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Array: " + Arrays.toString(arr) + ", Target: " + target);
        System.out.println("Indices: " + Arrays.toString(TwoSumHashing.find(arr, target)) + " (Expected: [0, 1])");
    }

    // =========================================================================
    // 6. BOYER-MOORE VOTING: Majority Element
    // =========================================================================
    static class BoyerMoore {
        public static int findMajority(int[] nums) {
            int count = 0;
            Integer candidate = null;
            for (int num : nums) {
                if (count == 0) candidate = num;
                count += (num == candidate) ? 1 : -1;
            }
            return candidate;
        }
    }

    private static void testBoyerMooreVoting() {
        System.out.println("\n--- Testing Boyer-Moore Voting ---");
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Majority Element: " + BoyerMoore.findMajority(arr) + " (Expected: 2)");
    }

    // =========================================================================
    // 7. LEXICOGRAPHICAL: Next Permutation
    // =========================================================================
    static class NextPermutationLex {
        public static void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) i--;
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i]) j--;
                swap(nums, i, j);
            }
            reverse(nums, i + 1, nums.length - 1);
        }
        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
        }
        private static void reverse(int[] arr, int start, int end) {
            while (start < end) swap(arr, start++, end--);
        }
    }

    private static void testNextPermutation() {
        System.out.println("\n--- Testing Next Permutation ---");
        int[] arr = {2, 1, 5, 4, 3, 0};
        System.out.println("Original: " + Arrays.toString(arr));
        NextPermutationLex.nextPermutation(arr);
        System.out.println("Next Perm: " + Arrays.toString(arr) + " (Expected: [2, 3, 0, 1, 4, 5])");
    }

    // =========================================================================
    // 8. CYCLIC SORT: Find Missing Number (Advanced In-Place Hashing)
    // =========================================================================
    static class CyclicSort {
        // Finds the missing number in an array containing numbers from 0 to N
        public static int findMissingNumber(int[] nums) {
            int i = 0;
            while (i < nums.length) {
                int correctIndex = nums[i];
                if (nums[i] < nums.length && nums[i] != nums[correctIndex]) {
                    swap(nums, i, correctIndex);
                } else {
                    i++;
                }
            }
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] != j) return j;
            }
            return nums.length;
        }
        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
        }
    }

    private static void testCyclicSort() {
        System.out.println("\n--- Testing Cyclic Sort (Missing Number) ---");
        int[] arr = {3, 0, 1}; // Missing 2
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Missing Number: " + CyclicSort.findMissingNumber(arr) + " (Expected: 2)");
    }
}
