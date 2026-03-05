package com.example.vo_bo_po_do_dto.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

// 出参DTO：整体还款计划（返回给前端的最终数据）
@Setter
@Getter
public class RepaymentPlanResponseDTO {
    private Long contractId;                  // 合同ID
    private BigDecimal totalLoanAmount;       // 总借款金额
    private List<RepaymentMonthDTO> monthList; // 每月还款明细

}