package com.example.vo_bo_po_do_dto.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: Azure
 * @date: 2026/3/5 周四 16:41
 * @Version 1.0
 **/
@Setter
@Getter
// 入参DTO：前端查询还款计划的参数（仅传合同ID）
public class ContractQueryDTO {
    private Long contractId; // 仅需要合同ID，无其他字段

    // getter/setter 省略
}



