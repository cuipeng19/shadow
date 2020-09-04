package com.shadow.creepin.service.impl;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.common.bean.entity.BMerchAudDO;
import com.shadow.common.exception.ShadowException;
import com.shadow.common.exception.ShadowStatus;
import com.shadow.creepin.dao.BMerchAudMapper;
import com.shadow.creepin.feign.LetterServiceFeign;
import com.shadow.creepin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//    @GlobalTransactional
    @Transactional
    public Object test(TestAO ao) {

        BMerchAudDO record = new BMerchAudDO();
        record.setId("ZWNTLH0ZUFC5L5HBOGHWC4428CKKPLQI");
        record.setAuditOperation("1");
        record.setAuditCode("10000000");
        bMerchAudMapper.updateByPrimaryKeySelective(record);

//        ResultDTO dto = letterServiceFeign.test(ao);

        String a = null;
        try {
            a.split(",");
        } catch (Exception e) {
            throw new ShadowException(ShadowStatus.ERROR_BUSINESS);
        }

        return ResultDTO.success();
    }
}
