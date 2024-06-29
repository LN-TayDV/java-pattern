package com.iluwatar.algorithm.setting.app.dto;

import com.iluwatar.algorithm.setting.app.dto.itemstype.BonusItemDto;
import com.iluwatar.algorithm.setting.dom.income.tax.BonusTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSetting;
import com.iluwatar.algorithm.setting.dom.income.tax.domain.onbjects.BonusItem;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BonusDto extends IncomeTaxSettingDto<BonusItem, List<BonusItemDto>>{

    private List<BonusItemDto> bonusItemDtos;

    public BonusDto(IncomeTaxSetting<?> domain) {
        this.bonusItemDtos = this.get((BonusTaxSetting) domain);
    }


    @Override
    public List<BonusItemDto> get(IncomeTaxSetting<BonusItem> domain) {
        return domain.get().stream().map(e -> new BonusItemDto()).toList();
    }
}
