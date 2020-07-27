package com.shadow.creepin.api;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.creepin.service.TestService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

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

//        Object o = testService.test(ao);
        stringRedisTemplate.opsForValue().set("creepin","aaa", 1, TimeUnit.MINUTES);
        System.out.println(stringRedisTemplate.opsForValue().get("creepin"));

        return ResultDTO.success();
    }

}
