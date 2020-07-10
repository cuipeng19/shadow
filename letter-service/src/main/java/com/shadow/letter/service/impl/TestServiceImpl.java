package com.shadow.letter.service.impl;

import com.shadow.letter.dao.BMerchAudMapper;
import com.shadow.letter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cuipeng 2020/7/10 17:05
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private BMerchAudMapper merchAudMapper;


    @Override
    public void test() {
        merchAudMapper.selectByPrimaryKey("ZWNTLH0ZUFC5L5HBOGHWC4428CKKPLQI");
    }
}
