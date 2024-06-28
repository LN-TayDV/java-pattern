package com.iluwatar.algorithm.setting.dom;

import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistory;
import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistoryItem;
import com.iluwatar.algorithm.setting.dom.income.tax.BonusTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.DeductionSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.SalaryTaxSetting;
import java.util.List;
import java.util.Optional;

public interface IncomeTaxSettingRepository {

    IncomeTaxSettingHistory incomeTaxSettingHistories(String cid);

    BonusTaxSetting getBonusTaxSetting (String cid , IncomeTaxSettingHistoryItem historyItem);

    SalaryTaxSetting getSalaryTaxSetting (String cid , IncomeTaxSettingHistoryItem historyItem);

    DeductionSetting getDeductionSetting (String cid , IncomeTaxSettingHistoryItem historyItem);

}
