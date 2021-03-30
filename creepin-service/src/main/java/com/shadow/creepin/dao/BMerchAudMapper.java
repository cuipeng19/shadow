package com.shadow.creepin.dao;

import com.shadow.common.bean.entity.BMerchAudDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BMerchAudMapper extends Mapper<BMerchAudDO> {

    int getMerchCount();

    List<String> getMerchId();
}