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

    public Object get (String historyId, int modeValue) {

        var require = new RequireImpl();

        IncomeTaxSettingMode mode = Arrays.stream(IncomeTaxSettingMode.values()).filter(e -> e.value == modeValue).findFirst().get();

        var historyItem = require.incomeTaxSettingHistories(cid)
            .getIncomeTaxSettingHistoryItems()
            .stream().filter(e -> e.equals(historyId)).findAny().get();

        var result =  switch (mode) {
            case BONUS -> new IncomeTaxSettingDto<BonusItem, BonusItemDto>((BonusTaxSetting) mode.incomeTaxSetting.apply(require, historyItem)) {
                @Override
                protected BonusItemDto toDTO(BonusItem domainObject) {
                    return new BonusItemDto(domainObject);
                }
            };

            case SALARY -> new IncomeTaxSettingDto<SalaryItem, SalaryItemDto>((SalaryTaxSetting) mode.incomeTaxSetting.apply(require, historyItem)) {
                @Override
                protected SalaryItemDto toDTO(SalaryItem domainObject) {
                    return new SalaryItemDto(domainObject);
                }
            };

            case DEDUCTION -> new IncomeTaxSettingDto<DeductionItem, DeductionItemDto>((DeductionSetting) mode.incomeTaxSetting.apply(require, historyItem)) {
                @Override
                protected DeductionItemDto toDTO(DeductionItem domainObject) {
                    return new DeductionItemDto(domainObject);
                }
            };
        };

        return result.get();
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
