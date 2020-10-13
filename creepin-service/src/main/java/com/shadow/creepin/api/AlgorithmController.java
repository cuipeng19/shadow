package com.shadow.creepin.api;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cuipeng 2020/10/12 11:24
 */
@RestController
public class AlgorithmController {

    /**
     * 两数之和
     * 使用哈希表寻找target-x的时间复杂度为O(1)
     */
    public int[] twoSum(int[] nums, int target) {
        Map map = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{(int) map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 整数反转
     * x%10弹出原数字末尾，result*10 + x%10推入新数字末尾
     */
    public int reverse(int x) {
        int result = 0;
        while(x!=0) {
            if((result * 10 / 10)!=result) {
                result = 0;
                break;
            }
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result;
    }

    /**
     * 回文数
     * 反转一半数字，原数字<=新数字则结束
     * 原数字<0 或 非0且末尾=0，返回否
     */
    public boolean isPalindrome(int x) {
        if(x<0 || (x!=0 && x%10==0)) {
            return false;
        }
        int result = 0;
        while (x>result) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return x==result || x==result/10;
    }

    /**
     * 罗马转数字
     * 下一个数字决定上一个数字的正负
     * 下一个数字>上一个数字，上一个为负
     */
    public int romanToInt(String s) {
        int number = charToInt(s.charAt(0));
        int result = 0;
        for(int i = 1; i<s.length(); i++) {
            int nextNumber = charToInt(s.charAt(i));
            if(nextNumber > number) {
                result -= number;
            } else {
                result += number;
            }
            number = nextNumber;
        }

        result += number;
        return result;
    }

    private int charToInt(char c) {
        switch (c) {
            case 'I' : return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }


}
