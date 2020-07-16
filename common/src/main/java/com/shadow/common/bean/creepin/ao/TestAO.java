package com.shadow.common.bean.creepin.ao;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author cuipeng 2020/7/9 16:56
 */
@Data
public class TestAO {

    @NotNull(message = "姓名不能为空")
    private String idCardName;

    private String idCardNo;

    private List<ImageAO> imageList;

}
