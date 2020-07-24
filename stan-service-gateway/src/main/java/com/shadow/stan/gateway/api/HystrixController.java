package com.shadow.stan.gateway.api;

import com.shadow.common.bean.ResultDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuipeng 2020/7/24 11:32
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    @RequestMapping("/fallback")
    public ResultDTO fallback() {
        return ResultDTO.timeout();
    }
}
