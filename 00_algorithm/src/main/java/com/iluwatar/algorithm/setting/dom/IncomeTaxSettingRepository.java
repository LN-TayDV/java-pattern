package com.iluwatar.algorithm.setting.dom;

import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistory;
import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistoryItem;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSettingDomain;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.BonusItem;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.DeductionItem;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.SalaryItem;

public interface IncomeTaxSettingRepository {

    IncomeTaxSettingHistory incomeTaxSettingHistories(String cid);

    IncomeTaxSettingDomain<BonusItem> getBonusTaxSetting (String cid , IncomeTaxSettingHistoryItem historyItem);

    IncomeTaxSettingDomain<SalaryItem> getSalaryTaxSetting (String cid , IncomeTaxSettingHistoryItem historyItem);

    IncomeTaxSettingDomain<DeductionItem> getDeductionSetting (String cid , IncomeTaxSettingHistoryItem historyItem);

}
