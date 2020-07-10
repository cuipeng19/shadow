package com.shadow.letter.api;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import org.springframework.web.bind.annotation.*;

/**
 * @author cuipeng 2020/7/7 16:55
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/test")
    public ResultDTO<Object> test(@RequestBody TestAO ao) {
        return ResultDTO.success().setData("letter");
    }

}
