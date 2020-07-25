package com.shadow.stan.gateway.api;

import com.shadow.common.bean.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuipeng 2020/7/24 11:32
 */
@RestController
@RequestMapping("/hystrix")
@Slf4j
public class HystrixController {

    @RequestMapping("/fallback")
    public ResultDTO fallback() {
        log.error("熔断降级");
        return ResultDTO.timeout();
    }
}
