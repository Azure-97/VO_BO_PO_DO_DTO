package com.example.vo_bo_po_do_dto.dao;

import com.example.vo_bo_po_do_dto.po.ContractPO;

/**
 * @description:
 * @author: Azure
 * @date: 2026/3/5 周四 16:40
 * @Version 1.0
 **/
// 合同DAO接口：定义数据访问方法
public interface ContractDAO {
    // 根据合同ID查询合同PO
    ContractPO getContractById(Long contractId);
}
