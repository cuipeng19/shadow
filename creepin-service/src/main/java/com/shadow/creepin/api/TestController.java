package com.shadow.creepin.api;

import com.shadow.common.bean.TestAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuipeng 2019/12/19 11:12
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public Object test() {
        TestAO ao = new TestAO();
        ao.setName("aaa");
        return ao;
    }
}
