package com.iluwatar.algorithm.setting.app.dto;

import com.iluwatar.algorithm.setting.app.dto.itemstype.DeductionItemDto;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.DeductionItem;
import lombok.Data;
import java.util.List;

@Data
public class DeductionDto extends IncomeTaxSettingDto<DeductionItem, List<DeductionItemDto>>{

    List<DeductionItemDto> deductionItemDtos;

    public DeductionDto(IncomeTaxSetting<DeductionItem> domain) {
        this.deductionItemDtos = this.get(domain);
    }

    @Override
    public List<DeductionItemDto> get(IncomeTaxSetting<DeductionItem> domain) {
        return domain.get().stream().map(e -> new DeductionItemDto()).toList();
    }
}
