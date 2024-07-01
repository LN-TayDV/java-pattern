package com.iluwatar.algorithm.setting.app;

import com.iluwatar.algorithm.setting.dom.history.IncomeTaxSettingHistory;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSettingDomain;
import com.iluwatar.algorithm.setting.dom.payments.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
public class IncomeTaxSettingDto {

    private IncomeTaxSettingHistory incomeTaxSettingHistory;

    private List<Object> incomeTaxSetting;

    private Payment payment;
}
