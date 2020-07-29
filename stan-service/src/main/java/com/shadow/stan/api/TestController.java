package com.shadow.stan.api;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.stan.dao.BMerchAudMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author cuipeng 2020/7/20 16:23
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BMerchAudMapper bMerchAudMapper;

    @PostMapping("/test")
    public ResultDTO test(@Validated @RequestBody TestAO ao) {
//        bMerchAudMapper.selectByPrimaryKey("ZWNTLH0ZUFC5L5HBOGHWC4428CKKPLQI");

        return ResultDTO.success();
    }
}
