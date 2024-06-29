package com.iluwatar.algorithm.setting.dom.income.tax;

import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.SalaryItem;
import lombok.Data;
import java.util.List;

@Data
public class SalaryTaxSetting extends IncomeTaxSetting<SalaryItem> {

    private List<SalaryItem> salaryItems;

    @Override
    public List<SalaryItem> get() {
        return this.salaryItems;
    }
}
