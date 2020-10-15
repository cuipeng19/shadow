package com.shadow.creepin.api;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

    /**
     * 最长公共前缀（一）
     * 横向扫描
     * 设定一个前缀，与每个元素比对公共前缀
     */
    public static String longestCommonPrefix1(String[] strs) {
        if(strs==null || strs.length==0) {
            return "";
        }
        String prefix = strs[0];
        for(int i=1; i<strs.length; i++) {
            prefix = comparePrefix(prefix, strs[i]);
            if(prefix.length()==0) {
                break;
            }
        }

        return prefix;
    }

    public static String comparePrefix(String prefix, String s) {
        int index = 0;

        int length = Math.min(prefix.length(), s.length());
        for(int i=0; i<length; i++) {
            if(prefix.charAt(i) != s.charAt(i)) {
                break;
            }
            index++;
        }

        return prefix.substring(0, index);
    }

    /**
     * 最长公共前缀（二）
     * 纵向扫描
     * 所有元素的相同index比对
     */
    public String longestCommonPrefix2(String[] strs) {
        if(strs==null || strs.length==0) {
            return "";
        }

        int count = strs[0].length();
        int length = strs.length;
        for(int i=0; i<count; i++) {
            char c = strs[0].charAt(i);
            for(int j=1; j<length; j++) {
                if(i==strs[j].length() || strs[j].charAt(i)!=c) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }

    /**
     * 有效的括号
     * 栈，左括号push，右括号pop
     * 否：字符串长度为奇数，最后栈不为空，为右括号时栈为空或不匹配
     */
    public boolean isValid(String s) {
        int length = s.length();
        if(s.length()%2==1) {
            return false;
        }

        Stack<Character> stack = new Stack();
        for(int i=0; i<length; i++) {
            if(s.charAt(i)=='(' || s.charAt(i)=='[' || s.charAt(i)=='{') {
                stack.push(s.charAt(i));
            }
            if(s.charAt(i)==')' && (stack.empty() || stack.pop()!='(')) {
                return false;
            }
            if(s.charAt(i)==']' && (stack.empty() || stack.pop()!='[')) {
                return false;
            }
            if(s.charAt(i)=='}' && (stack.empty() || stack.pop()!='{')) {
                return false;
            }
        }

        if(!stack.empty()) {
            return false;
        }

        return true;
    }


}
