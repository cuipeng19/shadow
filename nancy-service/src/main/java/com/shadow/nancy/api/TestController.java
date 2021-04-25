package com.shadow.nancy.api;

import com.shadow.hhkb.sdk.HhkbTransFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuipeng 2021/4/19 16:57
 */
@RestController
@RequestMapping("/nancy")
public class TestController {

    @Autowired
    private HhkbTransFeign hhkbTransFeign;

    @GetMapping("/test")
    public Object test() {
        String o = hhkbTransFeign.test();

        return o;
    }
}
