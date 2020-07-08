package com.shadow.creepin.feign;

import com.shadow.common.bean.ResultDTO;
import com.shadow.creepin.feign.fallback.LetterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cuipeng 2020/7/7 16:57
 */
@FeignClient(name = "letter-service", fallback = LetterServiceFallback.class)
public interface LetterServiceFeign {

    @GetMapping("/test/test")
    ResultDTO test();
}
