package com.shadow.hhkb.sdk;

import com.shadow.hhkb.sdk.fallback.HhkbTransFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cuipeng 2021/4/20 15:05
 */
@FeignClient(name = "hhkb-service", fallback = HhkbTransFeignFallback.class)
public interface HhkbTransFeign {

    @GetMapping("/hhkb/test")
    String test();
}
