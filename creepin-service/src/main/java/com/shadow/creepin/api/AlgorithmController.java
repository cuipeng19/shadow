package com.shadow.creepin.api;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
     * 循环结束：原数字=0
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
     * 反转一半数字，x%10弹出原数字末尾，result*10 + x%10推入新数字末尾
     * 循环结束：原数字<=新数字
     * 否：原数字<0 或 非0且末尾=0
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
     * 下一个数字决定上一个数字的正负，最后一个数字为正
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
     * 否：字符串长度为奇数，最后栈非空，为右括号时栈为空或不匹配
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


    /**
     * 合并两个有序数组
     * 合并后排序
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 合并两个有序数组
     * 双指针  前到后
     * 剩余的数组部分添加到nums1尾部
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p = 0, q = 0, i = 0;

        int[] temp = new int[m];
        System.arraycopy(nums1, 0, temp, 0, m);

        while ((p<m) && (q<n)) {
            nums1[i++] = temp[p] < nums2[q] ? temp[p++] : nums2[q++];
        }

        if(p < m) {
            System.arraycopy(temp, p, nums1, i, m-p);
        }
        if(q < n) {
            System.arraycopy(nums2, q, nums1, i, n-q);
        }
    }

    /**
     * 合并两个有序数组
     * 双指针  后到前
     * nums1剩余不用处理(nums1排好序且是插入到nums1)，nums2剩余的数组部分添加到nums1头部
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p = m-1, q = n-1, i = m+n-1;

        while ((p>=0) && (q>=0)) {
            nums1[i--] = nums1[p] > nums2[q] ? nums1[p--] : nums2[q--];
        }

        if(q >= 0) {
            System.arraycopy(nums2, 0, nums1, 0, q+1);
        }
    }


    /**
     * 合并两个有序链表
     * 指针指向l1与l2较小元素，较小链表后移一位，其中一条为空时指向非空链表
     * 结束循环：其中一条链表为空
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode index = head;

        while(l1!=null && l2!=null) {
            if(l1.val<=l2.val) {
                index.next = l1;
                l1 = l1.next;
            } else {
                index.next = l2;
                l2 = l2.next;
            }
            index = index.next;
        }

        index.next = l1==null ? l2 : l1;

        return head.next;
    }


    /**
     * 删除排序数组中的重复项
     * 双指针，num[i]==num[j]时直接跳过重复项，否则i++、num[i]=num[j]
     * 数组长度为0，直接返回0
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length==0) return 0;
        int i = 0;
        for(int j=1; j<nums.length; j++) {
            if(nums[i]!=nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


    /**
     * 删除排序链表中的重复元素
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode index = head;

        while(index!=null && index.next!=null) {
            if(index.val==index.next.val) {
                index.next = index.next.next;
            } else {
                index = index.next;
            }
        }

        return head;
    }


    /**
     * 移除元素
     * 循环计数，num[j]==val跳过，否则对i位置赋值并右移一位
     * 数组长度为0，直接返回0
     */
    public int removeElement(int[] nums, int val) {
        if(nums.length==0) return 0;

        int i = 0;
        for(int j=0; j<nums.length; j++) {
            if(nums[j]!=val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }


    /**
     * 实现strStr()
     * haystack为空返回-1，needle为空字符串返回0
     * 滑动窗口，匹配则返回i
     */
    public int strStr(String haystack, String needle) {
        if(needle==null || needle.length()==0) return 0;
        if(haystack==null || haystack.length()==0) return -1;
        int length = haystack.length(), slide = needle.length();

        for(int i=0; i<= length-slide; i++) {
            if(haystack.substring(i, slide+i).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 实现strStr()
     * haystack为空返回-1，needle为空字符串返回0，haystack长度<needle长度返回-1
     * 双指针，比较needle每一位字符，needle循环结束则返回i
     */
    public int strStr2(String haystack, String needle) {
        if(needle==null || needle.length()==0) return 0;
        if(haystack==null || haystack.length()==0) return -1;
        int length = haystack.length(), slide = needle.length();

        if(length<slide) return -1;

        for(int i=0; i<=length-slide; i++) {
            for(int j=0; j<slide; j++) {
                if(haystack.charAt(i+j)!=needle.charAt(j)) break;
                if(j+1==slide) return i;
            }
        }

        return -1;
    }


    /**
     * 搜索插入位置
     * 二分查找
     */
    public int searchInsert(int[] nums, int target) {
        int l = nums.length;

        int left = 0, right = l-1, result = l;
        while (left<=right) {
            int middle = ((right-left)>>1) + left;
            if(target<=nums[middle]) {
                result = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return result;
    }


    /**
     * 外观数列
     * 循环计数，更新基础数列(currentBuilder,prev,count)
     * 循环统计下一数列,prev==current count++,否则currentBuilder拼接count+pre,count初始化,prev=current
     */
    public String countAndSay(int n) {
        String base = "1";

        for(int i=1; i<n; i++) {
            StringBuilder currentBuilder = new StringBuilder();
            char prev = base.charAt(0);
            int count = 1;
            for(int j=1; j<base.length(); j++) {
                char current = base.charAt(j);
                if(prev==current) {
                    count++;
                } else {
                    currentBuilder.append(count).append(prev);
                    prev = current;
                    count = 1;
                }
            }
            currentBuilder.append(count).append(prev);
            base = currentBuilder.toString();
        }

        return base;
    }


    /**
     * 最大子序和
     * f(i) = max{f(i-1)+ai, ai}，只用一个变量pre维护f(i-1)
     */
    public int maxSubArray(int[] nums) {
        int pre = 0, current = nums[0];

        for(int i : nums) {
            pre = Math.max(pre + i, i);
            current = Math.max(current, pre);
        }

        return current;
    }


    /**
     * 最后一个单词的长度
     * 从右向左遍历，从不是空格的字符开始计数
     */
    public int lengthOfLastWord(String s) {
        int l, count = 0;
        if(s==null || (l=s.length())==0) return 0;

        for (int i=l-1; i>=0; i--) {
            if(s.charAt(i)==' ') {
                if(count==0) continue;
                break;
            }
            count++;
        }

        return count;
    }


    /**
     * 加一
     * 是9改0，否则加1，全9新建数组首位为1
     */
    public int[] plusOne(int[] digits) {
        for(int i=digits.length-1; i>=0; i--) {
            if(digits[i]==9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }

        digits = new int[digits.length+1];
        digits[0] = 1;
        return digits;
    }


    /**
     * 二进制求和
     * 转为十进制计算
     */
    public String addBinary(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a,2) + Integer.parseInt(b,2));
    }

    /**
     * 二进制求和
     * 同列相加进位, l-1-i取末位
     */
    public String addBinary2(String a, String b) {
        StringBuffer buffer = new StringBuffer();

        int l = Math.max(a.length(), b.length()), carry = 0, al = a.length(), bl = b.length();

        for(int i=0; i<l; i++) {
            int temp = (i<al ? (a.charAt(al-1-i)-'0') : 0) + (i<bl ? (b.charAt(bl-1-i)-'0') : 0) + carry;
            carry = temp>=2 ? 1 : 0;
            buffer.append(temp % 2);
        }

        if(carry>0) buffer.append("1");

        return buffer.reverse().toString();
    }


    /**
     * 只出现一次的数字
     * HashMap统计
     * 3倍HashSet和-原数组和
     * 位运算
     */
    public int singleNumber(int[] nums) {
        int once = 0, twice = 0;

        for(int num : nums) {
            once = ~twice & (once ^ num);
            twice = ~once & (twice ^ num);
        }

        return once;
    }


    /**
     * x的平方根
     * = x的1/2次幂 = e的lnx/2次幂
     */
    public int mySqrt(int x) {
        if(x==0) return 0;

        int result = (int) Math.exp(0.5 * Math.log(x));
        return (long)(result+1)*(result+1) <= x ? result+1 : result;
    }

    /**
     * x的平方根
     * 二分查找 k^2 <= x 的最大K值
     */
    public static int mySqrt2(int x) {
        int left = 0, right = x, result = 0;

        while(left <= right) {
            int middle = ((right-left)>>1) + left;
            if((long)middle*middle <= x) {
                result = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return result;
    }


    /**
     * 爬楼梯
     * 动态规划：f(x) = f(x-1) + f(x-2)
     */
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;

        for(int i=0; i<n; i++) {
            p = q;
            q = r;
            r = p + q;
        }

        return r;
    }


    /**
     * 相同的树
     * 相同：都为空
     * 不同：有一个为空，值不等
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        if(p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();

            if(p==null && q==null) continue;
            if(p==null || q==null) return false;
            if(p.val != q.val) return false;

            queue.offer(p.left);
            queue.offer(q.left);

            queue.offer(p.right);
            queue.offer(q.right);
        }

        return true;
    }



    /**
     * 对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return checkSymmetric(root.left, root.right);
    }
    /**
     * 递归
     */
    public boolean checkSymmetric(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        if(p.val != q.val) return false;

        return checkSymmetric(p.left, q.right) && checkSymmetric(p.right, q.left);
    }
    /**
     * 迭代
     */
    public boolean checkSymmetric2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();

            if(p==null && q==null) continue;
            if(p==null || q==null) return false;
            if(p.val != q.val) return false;

            queue.offer(p.left);
            queue.offer(q.right);

            queue.offer(p.right);
            queue.offer(q.left);
        }

        return true;
    }

}
