package com.example.vo_bo_po_do_dto.service;

import com.example.vo_bo_po_do_dto.bo.RepaymentPlanBO;
import com.example.vo_bo_po_do_dto.dao.ContractDAO;
import com.example.vo_bo_po_do_dto.dao.ContractDAOImpl;
import com.example.vo_bo_po_do_dto.dto.ContractQueryDTO;
import com.example.vo_bo_po_do_dto.dto.RepaymentMonthDTO;
import com.example.vo_bo_po_do_dto.dto.RepaymentPlanResponseDTO;
import com.example.vo_bo_po_do_dto.po.ContractPO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ContractService {
    // 依赖DAO（数据访问）
    private ContractDAO contractDAO = new ContractDAOImpl();

    // 核心方法：计算并返回还款计划DTO
    public RepaymentPlanResponseDTO getRepaymentPlan(ContractQueryDTO queryDTO) {
        // 步骤1：通过DAO查询合同PO（操作数据库）
        ContractPO contractPO = contractDAO.getContractById(queryDTO.getContractId());
        if (contractPO == null) {
            throw new RuntimeException("合同不存在");
        }

        // 步骤2：PO转换为BO（仅保留业务需要的字段）
        RepaymentPlanBO repaymentPlanBO = new RepaymentPlanBO();
        repaymentPlanBO.setContractId(contractPO.getContractId());
        repaymentPlanBO.setLoanAmount(contractPO.getLoanAmount());
        repaymentPlanBO.setInterestRate(contractPO.getInterestRate());
        repaymentPlanBO.setLoanTerm(contractPO.getLoanTerm());
        repaymentPlanBO.setRepayType(contractPO.getRepayType());
        repaymentPlanBO.setLoanStartDate(contractPO.getLoanStartDate());

        // 步骤3：调用BO的业务方法，计算还款计划
        repaymentPlanBO.calculateRepaymentPlan();

        // 步骤4：BO转换为DTO（仅返回前端需要的字段，屏蔽冗余/敏感数据）
        RepaymentPlanResponseDTO responseDTO = new RepaymentPlanResponseDTO();
        responseDTO.setContractId(contractPO.getContractId());
        responseDTO.setTotalLoanAmount(contractPO.getLoanAmount());

        // 转换每月明细BO→DTO
        List<RepaymentMonthDTO> monthDTOList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (RepaymentPlanBO.RepaymentMonthBO monthBO : repaymentPlanBO.getRepaymentMonthList()) {
            RepaymentMonthDTO monthDTO = new RepaymentMonthDTO();
            monthDTO.setPeriod(monthBO.getPeriod());
            monthDTO.setRepayDate(sdf.format(monthBO.getRepayDate())); // 日期格式化，前端友好
            monthDTO.setPrincipal(monthBO.getPrincipal());
            monthDTO.setInterest(monthBO.getInterest());
            monthDTO.setTotalRepay(monthBO.getTotalRepay());
            monthDTOList.add(monthDTO);
        }
        responseDTO.setMonthList(monthDTOList);

        return responseDTO;
    }
}