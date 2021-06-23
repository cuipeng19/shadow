package com.shadow.creepin.api;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.creepin.service.TestService;
import com.shadow.letter.sdk.LetterTransFeign;
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
import java.util.concurrent.*;
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
    @Autowired
    private LetterTransFeign letterTransFeign;


    @GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO test() {

//        try {
//            Object o = testService.test(new TestAO());
//        } catch (Exception e) {
//            return ResultDTO.error().setMessage(e.getMessage());
//        }
        ResultDTO dto = letterTransFeign.test(new TestAO());

//        return ResultDTO.success();
        return dto;
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



    @GetMapping("/master")
    public ResultDTO master() {
        testService.updateMerch();

        return ResultDTO.success();
    }

    @GetMapping("/slave")
    public ResultDTO slave() {
        testService.getMerch();

        return ResultDTO.success();
    }


}
