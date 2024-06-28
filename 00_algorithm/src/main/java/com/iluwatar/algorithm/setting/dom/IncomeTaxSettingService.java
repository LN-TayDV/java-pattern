package com.iluwatar.algorithm.setting.dom;

import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistory;
import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistoryItem;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSetting;
import java.util.List;

public class IncomeTaxSettingService {

    public static IncomeTaxSetting create (Require require, String cid,String historyId, IncomeTaxSettingMode taxSettingMode) {

        var historyItem = require.incomeTaxSettingHistories(cid)
            .getIncomeTaxSettingHistoryItems()
            .stream().findAny().get();

        return switch (taxSettingMode) {
            case SALARY, BONUS, DEDUCTION -> taxSettingMode.incomeTaxSetting.apply(require, historyItem);
        };

    }

    public interface Require extends IncomeTaxSettingMode.Require {

        IncomeTaxSettingHistory incomeTaxSettingHistories(String cid);
    }
}
