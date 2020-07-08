package com.shadow.letter.api;

import com.shadow.common.bean.ResultDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuipeng 2020/7/7 16:55
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public ResultDTO<Object> test() {
        return ResultDTO.build().setData("letter");
    }
}
