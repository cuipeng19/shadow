package com.shadow.creepin.feign;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.creepin.feign.fallback.LetterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cuipeng 2020/7/7 16:57
 */
@FeignClient(name = "letter-service", fallback = LetterServiceFallback.class)
public interface LetterServiceFeign {

    @PostMapping("/test/test")
    ResultDTO test(@RequestBody TestAO ao);
}
