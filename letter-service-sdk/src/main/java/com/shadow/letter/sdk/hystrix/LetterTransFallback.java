package com.shadow.letter.sdk.hystrix;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.letter.sdk.LetterTransFeign;
import org.springframework.stereotype.Component;

/**
 * @author cuipeng 2021/4/16 16:08
 */
@Component
public class LetterTransFallback implements LetterTransFeign {

    @Override
    public ResultDTO test(TestAO ao) {
        return ResultDTO.timeout();
    }
}
