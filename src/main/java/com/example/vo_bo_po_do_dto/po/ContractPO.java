package com.example.vo_bo_po_do_dto.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: Azure
 * @date: 2026/3/5 周四 16:40
 * @Version 1.0
 **/
// 合同PO：ContractPO（对应数据库contract表）
@Setter
@Getter
public class ContractPO {
    // 数据库表字段一一对应（全量字段）
    private Long contractId;      // 合同ID
    private BigDecimal loanAmount; // 借款金额
    private BigDecimal interestRate; // 月利率（%）
    private Integer loanTerm;     // 借款期限（月数）
    private String repayType;     // 还款方式：EQUAL_PRINCIPAL_INTEREST（等额本息）、EQUAL_PRINCIPAL（等额本金）
    private Date loanStartDate;   // 借款起始日期
    private String createUser;    // 创建人（敏感字段，前端不需要）
    private Date createTime;      // 创建时间（冗余字段，前端不需要）

    // 仅生成 getter/setter，无任何业务逻辑
    // getter/setter 省略
}