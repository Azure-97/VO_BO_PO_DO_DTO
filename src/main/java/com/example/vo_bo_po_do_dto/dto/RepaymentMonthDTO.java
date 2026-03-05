package com.example.vo_bo_po_do_dto.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

// 出参DTO：每月还款明细（仅返回前端需要的字段）
@Getter
@Setter
public class RepaymentMonthDTO {
    private Integer period;          // 期数
    private String repayDate;        // 还款日期（格式化后，前端友好）
    private BigDecimal principal;    // 当月应还本金
    private BigDecimal interest;     // 当月应还利息
    private BigDecimal totalRepay;   // 当月应还总额

    // getter/setter 省略
}