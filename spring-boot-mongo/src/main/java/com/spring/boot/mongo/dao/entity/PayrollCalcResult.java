package com.spring.boot.mongo.dao.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>Description: MongoDB对应Collection Entity类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/21 0021
 */
@Data
public class PayrollCalcResult {
    private String emp_id;
    private String emp_name;
    private String payroll_type;
    private String company_id;
    private String batch_type;
    private String mgr_id;
    private String mgr_name;
    private String department;
    private String income_year_month;
    private String batch_id;
    private String country_code;
    private String ref_batch_id;
    private String leaving_years;
    private String net_pay;
    private String tax;
    private String wage_before_tax;
    private String wage_after_tax;
    private String bonus_before_tax;
    private String bonus_after_tax;
    private String labor_before_tax;
    private String labor_after_tax;
    private String accident_before_tax;
    private String accident_after_tax;
    private String compension_before_tax;
    private String compension_after_tax;
    private String interest_before_tax;
    private String interest_after_tax;
    private String stock_option_before_tax;
    private String stock_option_after_tax;
    private String transfer_before_tax;
    private String transfer_after_tax;
    private String social_security;
    private String provident_fund;
    private String tax_exemption;
    private String tax_year_month;
    private String annuity;
    private String contract_first_party;

    private List<SalaryCalcResultItem> salary_calc_result_items;
}
