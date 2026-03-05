package com.example.vo_bo_po_do_dto;

import com.example.vo_bo_po_do_dto.controller.ContractController;
import com.example.vo_bo_po_do_dto.dto.ContractQueryDTO;
import com.example.vo_bo_po_do_dto.dto.RepaymentMonthDTO;
import com.example.vo_bo_po_do_dto.dto.RepaymentPlanResponseDTO;

/**
 * @description:
 * @author: Azure
 * @date: 2026/3/5 周四 16:49
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        // 1. 模拟前端传入参数（DTO）
        ContractQueryDTO queryDTO = new ContractQueryDTO();
        queryDTO.setContractId(1001L);

        // 2. 控制器处理请求
        ContractController controller = new ContractController();
        RepaymentPlanResponseDTO responseDTO = controller.queryRepaymentPlan(queryDTO);

        // 3. 输出返回给前端的结果（DTO）
        System.out.println("合同ID：" + responseDTO.getContractId());
        System.out.println("借款总额：" + responseDTO.getTotalLoanAmount());
        System.out.println("还款计划：");
        for (RepaymentMonthDTO monthDTO : responseDTO.getMonthList()) {
            System.out.println("第" + monthDTO.getPeriod() + "期：");
            System.out.println("还款日期：" + monthDTO.getRepayDate());
            System.out.println("应还本金：" + monthDTO.getPrincipal());
            System.out.println("应还利息：" + monthDTO.getInterest());
            System.out.println("应还总额：" + monthDTO.getTotalRepay());
            System.out.println("---------------------");
        }
    }
}