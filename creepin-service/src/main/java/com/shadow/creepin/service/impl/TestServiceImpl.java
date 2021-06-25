package com.shadow.creepin.service.impl;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.common.exception.ShadowException;
import com.shadow.common.bean.entity.CjzfBill;
import com.shadow.creepin.dao.BMerchAudMapper;
import com.shadow.creepin.dao.CjzfBillMapper;
import com.shadow.creepin.service.TestService;
import com.shadow.letter.sdk.LetterTransFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author cuipeng 2020/7/2 16:33
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Value("${key}")
    private String key;
    @Autowired
    private BMerchAudMapper bMerchAudMapper;
    @Autowired
    private LetterTransFeign letterTransFeign;
    @Autowired
    private CjzfBillMapper billMapper;


    @Override
//    @GlobalTransactional
    @Transactional
    public Object test(TestAO ao) {

//        BMerchAudDO record = new BMerchAudDO();
//        record.setId("ZWNTLH0ZUFC5L5HBOGHWC4428CKKPLQI");
//        record.setAuditOperation("1");
//        record.setAuditCode("10000000");
//        bMerchAudMapper.updateByPrimaryKeySelective(record);

        ResultDTO dto = letterTransFeign.test(ao);

        if(dto.getCode()!=20000) {
            throw new ShadowException(dto.getCode(), dto.getMessage());
        }

//        String a = null;
//        try {
//            a.split(",");
//        } catch (Exception e) {
//            throw new ShadowException(ShadowStatus.ERROR_BUSINESS);
//        }

        return "";
    }

    @Override
    public void getBill() {
        CjzfBill bill = billMapper.selectByPrimaryKey(5L);
        log.info(bill.toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBill() {
        CjzfBill record = billMapper.selectByPrimaryKey(5L);
        log.info(record.toString());

        String a = null;
        a.equals("1");

        CjzfBill bill = new CjzfBill();
        bill.setId(5L);
        bill.setCreateTime(new Date());
        billMapper.updateByPrimaryKeySelective(bill);
    }
}
