package com.iluwatar.algorithm.setting.dom.income.tax;

import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.BonusItem;
import lombok.Data;
import java.util.List;

@Data
public class BonusTaxSettingDomain extends IncomeTaxSettingDomain<BonusItem> {

    private  List<BonusItem> bonusItems;

    @Override
    public List<BonusItem> get() {
        return this.bonusItems;
    }
}
