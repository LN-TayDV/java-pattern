package com.iluwatar.algorithm.setting.app.dto;

import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSetting;
import java.util.List;
import java.util.stream.Collectors;

public abstract class IncomeTaxSettingDto<D, R> {

    private IncomeTaxSetting<D> domain;

    public IncomeTaxSettingDto(IncomeTaxSetting<D> domain) {
        this.domain = domain;
    }

    public List<Object> get() {
        return this.domain.get()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    // Abstract method to be implemented in subclasses to map domain objects to DTOs
    protected abstract R toDTO(D domainObject);

    // Getter for the domain object
    public IncomeTaxSetting<D> getDomain() {
        return this.domain;
    }
}
