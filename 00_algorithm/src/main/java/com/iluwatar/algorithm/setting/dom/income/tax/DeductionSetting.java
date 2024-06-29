package com.iluwatar.algorithm.setting.dom.income.tax;

import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.DeductionItem;
import lombok.Data;
import java.util.List;

@Data
public class DeductionSetting implements IncomeTaxSetting<DeductionItem> {

    List<DeductionItem> deductionItems;

    @Override
    public List<DeductionItem> get() {
        return this.deductionItems;
    }
}
