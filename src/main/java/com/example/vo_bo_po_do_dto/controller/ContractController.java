package com.example.vo_bo_po_do_dto.controller;

import com.example.vo_bo_po_do_dto.dto.ContractQueryDTO;
import com.example.vo_bo_po_do_dto.service.ContractService;
import com.example.vo_bo_po_do_dto.dto.RepaymentPlanResponseDTO;

/**
 * @description:
 * @author: Azure
 * @date: 2026/3/5 周四 16:41
 * @Version 1.0
 **/
public class ContractController {
    private ContractService contractService = new ContractService();

    // 模拟前端请求：查询还款计划
    public RepaymentPlanResponseDTO queryRepaymentPlan(ContractQueryDTO queryDTO) {
        // 直接调用Service，返回DTO给前端
        return contractService.getRepaymentPlan(queryDTO);
    }
}