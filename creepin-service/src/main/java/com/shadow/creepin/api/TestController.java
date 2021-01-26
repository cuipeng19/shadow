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
    public Object test(@Validated @RequestBody TestAO ao) {

        try {
            Object o = testService.test(ao);
        } catch (Exception e) {
            return ResultDTO.error().setMessage(e.getMessage());
        }
//        stringRedisTemplate.opsForValue().set("creepin","aaa", 1, TimeUnit.MINUTES);
//        System.out.println(stringRedisTemplate.opsForValue().get("creepin"));


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

        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{1,1};
        Arrays.stream(intersect(nums1,nums2)).forEach(System.out::println);
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1==null || nums2==null) return null;

        int l1 = nums1.length, l2 = nums2.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num : l1 <= l2 ? nums1 : nums2) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count+1);
        }

        int[] intersect = new int[Math.min(l1, l2)];
        int index = 0;
        for(int num : l1 > l2 ? nums1 : nums2) {
            int count = map.getOrDefault(num, 0);

            if(count>0) {
                intersect[index++] = num;
                count--;

                if(count>0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }

        return Arrays.copyOfRange(intersect, 0, index);
    }


    /**
     * 两个数组的交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1==null || nums2==null) return null;

        Set<Integer> s1 = new HashSet<>(), s2 = new HashSet<>();
        int l1 = nums1.length, l2 = nums2.length;

        for(int num : l1 > l2 ? nums1 : nums2) {
            s2.add(num);
        }

        for(int num : l1 < l2 ? nums1 : nums2) {
            if(s2.contains(num)) {
                s1.add(num);
            }
        }

        int[] intersection = new int[s1.size()];
        int index = 0;
        for(int num : s1) {
            intersection[index] = num;
            index++;
        }

        return intersection;
    }

}
