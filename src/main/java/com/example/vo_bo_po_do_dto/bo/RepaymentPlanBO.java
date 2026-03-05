package com.example.vo_bo_po_do_dto.bo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Azure
 * @date: 2026/3/5 周四 16:41
 * @Version 1.0
 **/
// 还款计划BO：RepaymentPlanBO（封装业务逻辑）
@Setter
@Getter
public class RepaymentPlanBO {
    // 1. 依赖的基础数据（从ContractPO转换而来）
    private Long contractId;
    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private Integer loanTerm;
    private String repayType;
    private Date loanStartDate;

    // 2. 业务计算结果：还款明细列表（每个月的还款计划）
    private List<RepaymentMonthBO> repaymentMonthList;

    // 3. 核心业务方法：计算还款计划（等额本息）
    public void calculateEqualPrincipalInterest() {
        List<RepaymentMonthBO> list = new ArrayList<>();
        BigDecimal monthlyRate = interestRate.divide(new BigDecimal("100"), 6, BigDecimal.ROUND_HALF_UP); // 月利率转小数
        // 等额本息公式：每月还款额 = 本金×月利率×(1+月利率)^期数 / [(1+月利率)^期数 - 1]
        BigDecimal monthRepayAmount = loanAmount.multiply(monthlyRate)
                .multiply(monthlyRate.add(BigDecimal.ONE).pow(loanTerm))
                .divide((monthlyRate.add(BigDecimal.ONE).pow(loanTerm).subtract(BigDecimal.ONE)), 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal remainPrincipal = loanAmount; // 剩余本金
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loanStartDate);

        for (int i = 1; i <= loanTerm; i++) {
            RepaymentMonthBO monthBO = new RepaymentMonthBO();
            monthBO.setPeriod(i); // 期数
            // 计算当月利息：剩余本金 × 月利率
            BigDecimal monthInterest = remainPrincipal.multiply(monthlyRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            // 当月本金：每月还款额 - 当月利息
            BigDecimal monthPrincipal = monthRepayAmount.subtract(monthInterest).setScale(2, BigDecimal.ROUND_HALF_UP);
            // 剩余本金
            remainPrincipal = remainPrincipal.subtract(monthPrincipal).setScale(2, BigDecimal.ROUND_HALF_UP);
            // 还款日期（每月同一天）
            calendar.add(Calendar.MONTH, 1);
            monthBO.setRepayDate(calendar.getTime());
            // 赋值
            monthBO.setPrincipal(monthPrincipal);
            monthBO.setInterest(monthInterest);
            monthBO.setTotalRepay(monthRepayAmount);
            monthBO.setRemainPrincipal(remainPrincipal);
            list.add(monthBO);
        }
        this.repaymentMonthList = list;
    }

    // 4. 入口方法：根据还款方式选择计算逻辑
    public void calculateRepaymentPlan() {
        if ("EQUAL_PRINCIPAL_INTEREST".equals(repayType)) {
            calculateEqualPrincipalInterest();
        } else if ("EQUAL_PRINCIPAL".equals(repayType)) {
            // 等额本金计算逻辑（可补充）
        }
    }

    // 内部BO：每月还款明细（业务层内部使用）
    @Setter
    @Getter
    public static class RepaymentMonthBO {
        private Integer period;          // 期数
        private Date repayDate;          // 还款日期
        private BigDecimal principal;    // 当月应还本金
        private BigDecimal interest;     // 当月应还利息
        private BigDecimal totalRepay;   // 当月应还总额
        private BigDecimal remainPrincipal; // 剩余本金

    }

}