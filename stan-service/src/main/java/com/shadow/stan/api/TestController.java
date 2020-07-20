package com.shadow.stan.api;

import com.shadow.common.bean.ResultDTO;
import com.shadow.stan.dao.BMerchAudMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuipeng 2020/7/20 16:23
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BMerchAudMapper bMerchAudMapper;

    @GetMapping("/test")
    public ResultDTO test() {
        bMerchAudMapper.selectByPrimaryKey("ZWNTLH0ZUFC5L5HBOGHWC4428CKKPLQI");
        return ResultDTO.success();
    }
}
