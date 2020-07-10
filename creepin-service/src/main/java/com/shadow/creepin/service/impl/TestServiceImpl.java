package com.shadow.creepin.service.impl;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.creepin.dao.BMerchAudMapper;
import com.shadow.creepin.feign.LetterServiceFeign;
import com.shadow.creepin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author cuipeng 2020/7/2 16:33
 */
@Service
public class TestServiceImpl implements TestService {

    @Value("${key}")
    private String key;
    @Autowired
    private BMerchAudMapper bMerchAudMapper;
    @Autowired
    private LetterServiceFeign letterServiceFeign;


    @Override
    public Object test(TestAO ao) {
        bMerchAudMapper.selectByPrimaryKey("ZWNTLH0ZUFC5L5HBOGHWC4428CKKPLQI");

        ResultDTO dto = letterServiceFeign.test(ao);

        return dto;
    }
}
