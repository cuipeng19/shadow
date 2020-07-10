package com.shadow.common.bean.creepin.ao;

import lombok.Data;

import java.util.List;

/**
 * @author cuipeng 2020/7/9 16:56
 */
@Data
public class TestAO {

    private String idCardName;

    private String idCardNo;

    private List<ImageAO> imageList;

}
