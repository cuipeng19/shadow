package com.shadow.hhkb.sdk.fallback;

import com.shadow.hhkb.sdk.HhkbTransFeign;
import org.springframework.stereotype.Component;

/**
 * @author cuipeng 2021/4/20 15:07
 */
@Component
public class HhkbTransFeignFallback implements HhkbTransFeign {


    @Override
    public String test() {
        return "超时";
    }
}
