package com.example.vo_bo_po_do_dto.dao;

import com.example.vo_bo_po_do_dto.po.ContractPO;

import java.math.BigDecimal;
import java.util.Date;

// 合同DAO实现类（模拟MyBatis/JDBC操作）
public class ContractDAOImpl implements ContractDAO {
    @Override
    public ContractPO getContractById(Long contractId) {
        // 实际开发中用MyBatis/JDBC执行SQL：SELECT * FROM contract WHERE contract_id = ?
        // 这里模拟查询结果
        ContractPO po = new ContractPO();
        po.setContractId(contractId);
        po.setLoanAmount(new BigDecimal("100000")); // 借款10万
        po.setInterestRate(new BigDecimal("0.5"));  // 月利率0.5%
        po.setLoanTerm(12); // 12期
        po.setRepayType("EQUAL_PRINCIPAL_INTEREST"); // 等额本息
        po.setLoanStartDate(new Date()); // 借款起始日（当前时间）
        po.setCreateUser("admin"); // 敏感字段
        po.setCreateTime(new Date());
        return po;
    }
}