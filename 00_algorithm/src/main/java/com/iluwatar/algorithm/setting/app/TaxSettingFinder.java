package com.iluwatar.algorithm.setting.app;

import com.iluwatar.algorithm.setting.app.dto.IncomeTaxSettingDto;
import com.iluwatar.algorithm.setting.app.dto.itemstype.BonusItemDto;
import com.iluwatar.algorithm.setting.app.dto.itemstype.DeductionItemDto;
import com.iluwatar.algorithm.setting.app.dto.itemstype.SalaryItemDto;
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
import java.util.List;

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
            case BONUS -> new IncomeTaxSettingDto<BonusItem, List<BonusItemDto>>((BonusTaxSetting) mode.incomeTaxSetting.apply(require, historyItem)) {
                @Override
                public List<BonusItemDto> get(IncomeTaxSetting<BonusItem> domain) {
                    return domain.get().stream().map(e -> new BonusItemDto()).toList();
                }
            };

            case SALARY -> new IncomeTaxSettingDto<SalaryItem, List<SalaryItemDto>>((SalaryTaxSetting) mode.incomeTaxSetting.apply(require, historyItem)) {
                @Override
                public List<SalaryItemDto> get(IncomeTaxSetting<SalaryItem> domain) {
                    return domain.get().stream().map(e -> new SalaryItemDto()).toList();
                }
            };

            case DEDUCTION -> new IncomeTaxSettingDto<DeductionItem, List<DeductionItemDto>>((DeductionSetting) mode.incomeTaxSetting.apply(require, historyItem)) {
                @Override
                public List<DeductionItemDto> get(IncomeTaxSetting<DeductionItem> domain) {
                    return domain.get().stream().map(e -> new DeductionItemDto()).toList();
                }
            };
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
