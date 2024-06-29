package com.iluwatar.algorithm.setting.dom;

import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistory;
import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistoryItem;
import com.iluwatar.algorithm.setting.dom.income.tax.BonusTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.DeductionSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.SalaryTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.BonusItem;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.DeductionItem;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.SalaryItem;
import lombok.RequiredArgsConstructor;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public enum IncomeTaxSettingMode {

    SALARY (0, Require::getSalaryTaxSettings),

    BONUS (1, Require::getBonusTaxSettings),

    DEDUCTION (2, Require::getDeductionSettings)

    ;

    public final int value;

    public final BiFunction<Require, IncomeTaxSettingHistoryItem, IncomeTaxSetting> incomeTaxSetting;

    public interface Require {

        IncomeTaxSettingHistory incomeTaxSettingHistories(String cid);

        SalaryTaxSetting getSalaryTaxSettings (IncomeTaxSettingHistoryItem historyItem);

        BonusTaxSetting getBonusTaxSettings (IncomeTaxSettingHistoryItem historyItem);

        DeductionSetting getDeductionSettings (IncomeTaxSettingHistoryItem historyItem);

    }
}
