package 数据结构;

import javafx.scene.effect.SepiaTone;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZhangCheng
 * @version 1.0
 * @date 2022/3/19 17:26
 * 数组相关算法
 */
public class Array {
//    给你一个整数数组 nums 。如果至少一值在数组中出现至少两次，返回 true ；如果数组中每个元素互不相同，返回 false 。
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for (int x:nums) {
            if (!set.contains(x))
                set.add(x);
            else
                return  true;
        }
        return false;
    }
//    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。子数组是数组中的一个连续部分。
    public int maxSubArray(int[] nums) {
        int maxAnswer=nums[0],pre=0;
        for (int x:nums) {
            pre=Math.max(pre+x,x);
            maxAnswer=Math.max(maxAnswer,pre);
        }
        return maxAnswer;
    }

//    给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
//    请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m--+--n;
        while(n>=0) {
            nums1[i--] = m>=0 && nums1[m]>nums2[n] ? nums1[m--] : nums2[n--];
        }
    }

//    给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
