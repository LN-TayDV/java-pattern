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
import java.util.List;
import java.util.Optional;

public interface IncomeTaxSettingRepository {

    IncomeTaxSettingHistory incomeTaxSettingHistories(String cid);

    IncomeTaxSetting<BonusItem> getBonusTaxSetting (String cid , IncomeTaxSettingHistoryItem historyItem);

    IncomeTaxSetting<SalaryItem> getSalaryTaxSetting (String cid , IncomeTaxSettingHistoryItem historyItem);

    IncomeTaxSetting<DeductionItem> getDeductionSetting (String cid , IncomeTaxSettingHistoryItem historyItem);

}
