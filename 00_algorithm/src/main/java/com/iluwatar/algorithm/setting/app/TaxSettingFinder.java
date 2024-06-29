package com.iluwatar.algorithm.setting.app;

import com.iluwatar.algorithm.setting.app.dto.BonusDto;
import com.iluwatar.algorithm.setting.app.dto.DeductionDto;
import com.iluwatar.algorithm.setting.app.dto.SalaryDto;
import com.iluwatar.algorithm.setting.dom.IncomeTaxSettingMode;
import com.iluwatar.algorithm.setting.dom.IncomeTaxSettingRepository;
import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistory;
import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistoryItem;
import com.iluwatar.algorithm.setting.dom.income.tax.BonusTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.DeductionSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.SalaryTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.BonusItem;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.DeductionItem;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.SalaryItem;
import java.util.Arrays;

public class TaxSettingFinder {

    public static final String cid = "BFBHIbhifg";


    private IncomeTaxSettingRepository incomeTaxSettingRepository;

    @SuppressWarnings("unchecked")
    public Object get (String historyId, int modeValue) {

        var require = new RequireImpl();

        IncomeTaxSettingMode mode = Arrays.stream(IncomeTaxSettingMode.values()).filter(e -> e.value == modeValue).findFirst().get();

        var historyItem = require.incomeTaxSettingHistories(cid)
            .getIncomeTaxSettingHistoryItems()
            .stream().filter(e -> e.equals(historyId)).findAny().get();

        return switch (mode) {
            case BONUS -> new BonusDto(mode.incomeTaxSetting.apply(require, historyItem));
            case SALARY -> new SalaryDto(mode.incomeTaxSetting.apply(require, historyItem));
            case DEDUCTION -> new DeductionDto(mode.incomeTaxSetting.apply(require, historyItem));
        };
    }

    private class RequireImpl implements IncomeTaxSettingMode.Require {

        @Override
        public IncomeTaxSettingHistory incomeTaxSettingHistories(String cid) {
            return incomeTaxSettingRepository.incomeTaxSettingHistories(cid);
        }

        @Override
        public IncomeTaxSetting<SalaryItem> getSalaryTaxSettings(IncomeTaxSettingHistoryItem historyItem) {
            return incomeTaxSettingRepository.getSalaryTaxSetting(cid, historyItem);
        }

        @Override
        public IncomeTaxSetting<BonusItem> getBonusTaxSettings(IncomeTaxSettingHistoryItem historyItem) {
            return incomeTaxSettingRepository.getBonusTaxSetting(cid, historyItem);
        }

        @Override
        public IncomeTaxSetting<DeductionItem> getDeductionSettings(IncomeTaxSettingHistoryItem historyItem) {
            return incomeTaxSettingRepository.getDeductionSetting(cid, historyItem);
        }
    }

}
