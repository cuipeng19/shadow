package com.shadow.common.bean.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "cjzf_bill")
public class CjzfBill {
    @Id
    private Long id;

    /**
     * 文件路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 文件类型:1-支付宝;2-微信
     */
    @Column(name = "file_type")
    private Integer fileType;

    /**
     * 文件状态:0-无效;1-有效
     */
    @Column(name = "file_status")
    private Integer fileStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 交易日期
     */
    @Column(name = "trans_date")
    private String transDate;

    /**
     * 商户号
     */
    @Column(name = "merch_no")
    private String merchNo;

    /**
     * appId
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 微信商户号
     */
    @Column(name = "wechat_mch_id")
    private String wechatMchId;
}