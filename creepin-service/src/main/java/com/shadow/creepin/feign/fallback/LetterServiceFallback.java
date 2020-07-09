package com.shadow.creepin.feign.fallback;

import com.shadow.common.bean.ResultDTO;
import com.shadow.creepin.feign.LetterServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author cuipeng 2020/7/7 16:58
 */
@Component
@Slf4j
public class LetterServiceFallback implements LetterServiceFeign {


    @Override
    public ResultDTO test(String param) {
        log.error("LetterController::test熔断");
        return ResultDTO.timeout();
    }

}
