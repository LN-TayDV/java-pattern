package com.iluwatar.algorithm.setting.app.dto;

import com.iluwatar.algorithm.setting.app.dto.itemstype.SalaryItemDto;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.DeductionItem;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.SalaryItem;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SalaryDto extends IncomeTaxSettingDto<SalaryItem, List<SalaryItemDto>>{

    private  List<SalaryItemDto> salaryItems;

    public SalaryDto(IncomeTaxSetting<SalaryItem> domains) {
        this.salaryItems = this.get(domains);
    }

    @Override
    public List<SalaryItemDto> get(IncomeTaxSetting<SalaryItem> domain) {
        return domain.get().stream().map(e -> new SalaryItemDto()).toList();
    }
}
