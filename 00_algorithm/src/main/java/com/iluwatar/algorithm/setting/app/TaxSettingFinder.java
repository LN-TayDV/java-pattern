/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSettingDomain;
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
