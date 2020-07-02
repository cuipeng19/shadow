package com.shadow.creepin.service.impl;

import com.shadow.common.bean.creepin.BMerchAudDO;
import com.shadow.creepin.dao.BMerchAudMapper;
import com.shadow.creepin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cuipeng 2020/7/2 16:33
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private BMerchAudMapper bMerchAudMapper;


    @Override
    public Object test() {
        return bMerchAudMapper.selectByPrimaryKey("ZWNTLH0ZUFC5L5HBOGHWC4428CKKPLQI");
    }
}
