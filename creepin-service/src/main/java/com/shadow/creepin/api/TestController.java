package com.shadow.creepin.api;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.creepin.service.TestService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.StopWatch;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author cuipeng 2019/12/19 11:12
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/test")
    public ResultDTO test() {

        try {
            Object o = testService.test(new TestAO());
        } catch (Exception e) {
            return ResultDTO.error().setMessage(e.getMessage());
        }

        return ResultDTO.success();
    }

    @GetMapping("/post")
    public Object post(@Validated @RequestBody TestAO ao) {

        try {
            Object o = testService.test(ao);
        } catch (Exception e) {
            return ResultDTO.error().setMessage(e.getMessage());
        }

        return ResultDTO.success();
    }

//    @GetMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux() {
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            return "flux data--" + i;
        }));
        return result;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
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


    public static void main(String[] args) {
//        ListNode head = new ListNode(4);
//        ListNode head2 = new ListNode(6);
//        ListNode head3 = new ListNode(1);
//        ListNode head4 = new ListNode(3);
//        ListNode head5 = new ListNode(6);
//        head.next = head2;
//        head2.next = head3;
//        head3.next = head4;
//        head4.next = head5;
//
//        ListNode result =removeElements(head, 6);
//        ListNode index = result;
//
//        while (index!=null) {
//            System.out.println(index.val);
//            index = index.next;
//        }
//        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,4,5,6,7,8,9,9}, 3));

        int[] nums1 = new int[]{1,6,2,7,3,4,5};
        Arrays.stream(sortArray(nums1)).forEach(System.out::println);
    }

    public static int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }
    private static void quickSort(int[] nums, int l, int r) {
        if(l<r) {
            int p = partition(nums, l, r);

            quickSort(nums, l, p-1);
            quickSort(nums, p+1, r);
        }
    }
    private static int partition(int[] nums, int l, int r) {
        int random = new Random().nextInt(r-l+1) + l;
        // 随机索引换到最左端
        swap(nums, l, random);

        int pivot = nums[l];
        int pivotIndex = l;
        // 小于基准数的排在左边
        for(int i=l+1; i<=r; i++) {
            if(nums[i] < pivot) {
                pivotIndex++;
                swap(nums, i, pivotIndex);
            }
        }
        // 最左端与基准索引交换
        swap(nums, l, pivotIndex);

        return pivotIndex;
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static int[] mergeSort(int[] array) {
        if(array==null || array.length<2) return array;

        int middle = array.length/2;
        int[] left = Arrays.copyOfRange(array,0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int i = 0, leftIndex = 0, rightIndex = 0;

        while (leftIndex<left.length && rightIndex<right.length) {
            if(left[leftIndex] < right[rightIndex]) {
                result[i++] = left[leftIndex++];
            } else {
                result[i++] = right[rightIndex++];
            }
        }
        while (i<result.length) {
            if(leftIndex>=left.length) {
                result[i++] = right[rightIndex++];
            } else {
                result[i++] = left[leftIndex++];
            }
        }

        return result;
    }

    public static int[] insertionSort(int[] array) {
        if(array==null || array.length==0) return array;

        for(int i=1; i<array.length; i++) {
            for(int j=0; j<=i; j++) {
                if(array[i] < array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        return array;
    }

    public static int[] selectionSort(int[] array) {
        if(array==null || array.length==0) return array;

        for(int i=0; i<array.length; i++) {
            int minIndex = i;

            for(int j=i+1; j<array.length; j++) {
                if(array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }

        return array;
    }


    public static int[] bubbleSort(int[] array) {
        if(array==null || array.length==0) return array;

        for(int i=0; i<array.length; i++) {
            for(int j=i+1; j<array.length; j++) {
                if(array[j] < array[i]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }

        return array;
    }


    /**
     * 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     */
    public static int search(int[] nums, int target) {
        int length = nums.length, left = 0, right = length-1;

        while (left<=right) {
            int middle = (right-left)/2 + left;

            if(target == nums[middle]) {
                return middle;
            }
            if(target < nums[middle]) {
                right = middle-1;
            }
            if(target > nums[middle]) {
                left = middle+1;
            }
        }

        return -1;
    }


    /**
     * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
     *
     * 实现 MyHashSet 类：
     * void add(key) 向哈希集合中插入值 key 。
     * bool contains(key) 返回哈希集合中是否存在这个值 key 。
     * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
     */
    class Bucket {

        private LinkedList<Integer> list;

        public Bucket() {
            list = new LinkedList<>();
        }

        public void insert(Integer key) {
            if(list.indexOf(key)==-1) list.addLast(key);
        }

        public void delete(Integer key) {
            list.remove(key);
        }

        public boolean exists(Integer key) {
            return list.indexOf(key) != -1;
        }
    }
    class MyHashSet {

        private Bucket[] buckets;
        private int length;

        public MyHashSet() {
            length = 769;
            buckets = new Bucket[769];
            for(int i=0; i<769; i++) {
                buckets[i] = new Bucket();
            }
        }

        private int hash(int key) {
            return key % length;
        }

        public void add(int key) {
            buckets[hash(key)].insert(key);
        }

        public void remove(int key) {
            buckets[hash(key)].delete(key);
        }

        public boolean contains(int key) {
            return buckets[hash(key)].exists(key);
        }
    }

    /**
     * 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     *
     * 递归
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null || root.val == val) return root;

        return val>root.val ? searchBST(root.right, val) : searchBST(root.left, val);
    }
    public TreeNode searchBST1(TreeNode root, int val) {
        while (root!=null && root.val!=val) {
            root = val>root.val ? root.right : root.left;
        }

        return root;
    }

    /**
     * 二叉搜索树中的插入操作
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     *
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null) return new TreeNode(val);

        TreeNode index = root;
        while (index!=null) {
            if(val>index.val) {
                if(index.right==null) {
                    index.right = new TreeNode(val);
                    break;
                } else {
                    index = index.right;
                }
            } else {
                if(index.left==null) {
                    index.left = new TreeNode(val);
                    break;
                } else {
                    index = index.left;
                }
            }
        }

        return root;
    }


    /**
     * 删除二叉搜索树中的节点
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return null;

        if(key>root.val) {
            root.right = deleteNode(root.right, key);
        }
        if(key<root.val) {
            root.left = deleteNode(root.left, key);
        }
        if(key == root.val) {
            if(root.left==null && root.right==null) {
                root = null;
            }
            if(root.left!=null) {
                root.val = previousNode(root);
                root.left = deleteNode(root.left, key);
            }
            if(root.right!=null) {
                root.val = previousNode(root);
                root.right = deleteNode(root.right, key);
            }
        }

        return root;
    }
    private int previousNode(TreeNode node) {
        node = node.left;
        while (node.right!=null) {
            node = node.right;
        }
        return node.val;
    }
    private int behindNode(TreeNode node) {
        node = node.right;
        while (node.left!=null) {
            node = node.left;
        }
        return node.val;
    }
}
