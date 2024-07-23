package com.iluwatar.algorithm.setting.dom.income.tax;

import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.SalaryItem;
import lombok.Data;
import java.util.List;

@Data
public class SalaryTaxSettingDomain extends IncomeTaxSettingDomain<SalaryItem> {

    private List<SalaryItem> salaryItems;

    @Override
    public List<SalaryItem> get() {
        return this.salaryItems;
    }
}
