package com.iluwatar.algorithm.setting.app.dto;

import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSetting;
import java.util.List;

public abstract class IncomeTaxSettingDto<D, R> {

    public IncomeTaxSettingDto(IncomeTaxSetting<D> domain) {}

    public abstract List<R> get(IncomeTaxSetting<D> domain);
}
