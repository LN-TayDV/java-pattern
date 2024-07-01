package com.iluwatar.algorithm.setting.app;

import com.iluwatar.algorithm.setting.app.dto.IncomeTaxSettingDomainDto;
import com.iluwatar.algorithm.setting.app.dto.itemstype.BonusItemDto;
import com.iluwatar.algorithm.setting.app.dto.itemstype.DeductionItemDto;
import com.iluwatar.algorithm.setting.app.dto.itemstype.SalaryItemDto;
import com.iluwatar.algorithm.setting.dom.IncomeTaxSetting;
import com.iluwatar.algorithm.setting.dom.IncomeTaxSettingMode;
import com.iluwatar.algorithm.setting.dom.IncomeTaxSettingRepository;
import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistory;
import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistoryItem;
import com.iluwatar.algorithm.setting.dom.income.tax.BonusTaxSettingDomain;
import com.iluwatar.algorithm.setting.dom.income.tax.DeductionSettingDomain;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSettingDomain;
import com.iluwatar.algorithm.setting.dom.income.tax.SalaryTaxSettingDomain;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.BonusItem;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.DeductionItem;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.SalaryItem;
import java.util.Arrays;

public class TaxSettingFinder {

    public static final String cid = "BFBHIbhifg";


    private IncomeTaxSettingRepository incomeTaxSettingRepository;

    public IncomeTaxSettingDto get (String historyId, int modeValue) {

        var require = new RequireImpl();

        IncomeTaxSettingMode mode = Arrays.stream(IncomeTaxSettingMode.values()).filter(e -> e.value == modeValue).findFirst().get();

        var historyItem = require.incomeTaxSettingHistories(cid)
            .getIncomeTaxSettingHistoryItems()
            .stream().filter(e -> e.equals(historyId)).findAny().get();

        var setting = IncomeTaxSetting.create(require, cid, mode, historyItem);

        var domainItem =  switch (mode) {
            case BONUS -> new IncomeTaxSettingDomainDto<BonusItem, BonusItemDto>(setting.getIncomeTaxSetting()) {
                @Override
                protected BonusItemDto toDTO(BonusItem domainObject) {
                    return new BonusItemDto(domainObject);
                }
            };

            case SALARY -> new IncomeTaxSettingDomainDto<SalaryItem, SalaryItemDto>(setting.getIncomeTaxSetting()) {
                @Override
                protected SalaryItemDto toDTO(SalaryItem domainObject) {
                    return new SalaryItemDto(domainObject);
                }
            };

            case DEDUCTION -> new IncomeTaxSettingDomainDto<DeductionItem, DeductionItemDto>(setting.getIncomeTaxSetting()) {
                @Override
                protected DeductionItemDto toDTO(DeductionItem domainObject) {
                    return new DeductionItemDto(domainObject);
                }
            };
        };

        return new IncomeTaxSettingDto(
            setting.getIncomeTaxSettingHistory(),
            domainItem.get(),
            setting.getPayment()
        );
    }

    private class RequireImpl implements IncomeTaxSetting.Require {

        @Override
        public IncomeTaxSettingHistory incomeTaxSettingHistories(String cid) {
            return incomeTaxSettingRepository.incomeTaxSettingHistories(cid);
        }

        @Override
        public IncomeTaxSettingDomain<SalaryItem> getSalaryTaxSettings(IncomeTaxSettingHistoryItem historyItem) {
            return incomeTaxSettingRepository.getSalaryTaxSetting(cid, historyItem);
        }

        @Override
        public IncomeTaxSettingDomain<BonusItem> getBonusTaxSettings(IncomeTaxSettingHistoryItem historyItem) {
            return incomeTaxSettingRepository.getBonusTaxSetting(cid, historyItem);
        }

        @Override
        public IncomeTaxSettingDomain<DeductionItem> getDeductionSettings(IncomeTaxSettingHistoryItem historyItem) {
            return incomeTaxSettingRepository.getDeductionSetting(cid, historyItem);
        }
    }

}
