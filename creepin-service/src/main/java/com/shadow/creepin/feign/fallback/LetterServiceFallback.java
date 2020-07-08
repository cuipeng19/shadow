package com.shadow.creepin.feign.fallback;

import com.shadow.common.bean.ResultDTO;
import com.shadow.creepin.feign.LetterServiceFeign;
import org.springframework.stereotype.Component;

/**
 * @author cuipeng 2020/7/7 16:58
 */
@Component
public class LetterServiceFallback implements LetterServiceFeign {


    @Override
    public ResultDTO test() {
        return ResultDTO.build().setCode(500).setMessage("服务超时");
    }

}
