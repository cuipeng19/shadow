package com.shadow.creepin.service.impl;

import com.shadow.common.bean.ResultDTO;
import com.shadow.common.bean.creepin.ao.TestAO;
import com.shadow.common.bean.entity.BMerchAudDO;
import com.shadow.common.exception.ShadowException;
import com.shadow.common.exception.ShadowStatus;
import com.shadow.creepin.dao.BMerchAudMapper;
import com.shadow.creepin.service.TestService;
import com.shadow.letter.sdk.LetterTransFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void getMerch() {
        BMerchAudDO bMerchAudDO = bMerchAudMapper.selectByPrimaryKey("IXXG9MD5LMUDO8D6FBG92XL5OHHOOX2S");
        log.info(bMerchAudDO.toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMerch() {
        BMerchAudDO updateMerchAud = new BMerchAudDO();
        updateMerchAud.setId("PC2S4LRA9PPKDT98J8OTOJETM68DGJ5X");
        updateMerchAud.setAuditOpinion("ccc");
        bMerchAudMapper.updateByPrimaryKeySelective(updateMerchAud);
    }
}
