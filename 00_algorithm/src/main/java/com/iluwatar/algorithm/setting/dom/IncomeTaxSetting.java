package com.iluwatar.algorithm.setting.dom;

import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistory;
import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistoryItem;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSettingDomain;
import com.iluwatar.algorithm.setting.dom.payments.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Arrays;

@Data
@AllArgsConstructor
public class IncomeTaxSetting {

    private IncomeTaxSettingHistory incomeTaxSettingHistory;

    private IncomeTaxSettingDomain<?> incomeTaxSetting;

    private Payment payment;

    public static IncomeTaxSetting create (Require require, String cid, IncomeTaxSettingMode mode, IncomeTaxSettingHistoryItem historyItem) {

        return new IncomeTaxSetting(
            require.incomeTaxSettingHistories(cid),
            mode.incomeTaxSetting.apply(require, historyItem),
            new Payment("testg")
        );
    }

    public interface Require extends IncomeTaxSettingMode.Require { }
}
