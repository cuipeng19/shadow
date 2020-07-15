package com.shadow.creepin.api;

import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.creepin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cuipeng 2019/12/19 11:12
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("/test")
    public Object test(@RequestBody TestAO ao) {

        Object o = testService.test(ao);


        return o;
    }
}
