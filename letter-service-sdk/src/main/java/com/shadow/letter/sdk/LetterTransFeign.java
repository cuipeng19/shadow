package com.shadow.letter.sdk;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.letter.sdk.hystrix.LetterTransFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author cuipeng 2021/4/16 16:05
 */
@FeignClient(name = "letter-service", fallback = LetterTransFallback.class)
public interface LetterTransFeign {

    @PostMapping("/test/test")
    ResultDTO test(@RequestBody TestAO ao);

}
