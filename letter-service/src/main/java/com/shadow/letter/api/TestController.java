package com.shadow.letter.api;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.common.exception.ShadowException;
import com.shadow.letter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cuipeng 2020/7/7 16:55
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;


    @PostMapping("/test")
    public ResultDTO<Object> test(@RequestBody TestAO ao) {
        testService.test();
        return ResultDTO.success().setData("letter");
    }

}
