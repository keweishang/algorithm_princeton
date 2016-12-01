import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
// Find all unique triplets in the array which gives the sum of zero.
// Note: The solution set must not contain duplicate triplets.
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3)
            return res;

        // runtime : O(n*lgn)
        Arrays.sort(nums);

        // runtime : O(n^2)
        // find all pairs for a sum in sorted array. Two pointers, one goes from
        // low to high, another goes from high to low
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] == nums[i - 1]) { // skip same number
                int targetSum = 0 - nums[i];
                int lo = i + 1;
                int hi = nums.length - 1;
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == targetSum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        // skip same number to avoid duplicate
                        while (nums[lo + 1] == nums[lo])
                            lo++;
                        while (nums[hi - 1] == nums[hi])
                            hi--;
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < targetSum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return res;
    }
}