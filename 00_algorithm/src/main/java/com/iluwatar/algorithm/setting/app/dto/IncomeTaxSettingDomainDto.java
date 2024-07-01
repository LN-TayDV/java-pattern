package com.iluwatar.algorithm.setting.app.dto;

import com.iluwatar.algorithm.setting.dom.income.tax.IncomeTaxSettingDomain;
import java.util.List;
import java.util.stream.Collectors;

public abstract class IncomeTaxSettingDomainDto<D, R> {

    private final IncomeTaxSettingDomain<? extends D> domain;

    @SuppressWarnings("unchecked")
    public IncomeTaxSettingDomainDto(IncomeTaxSettingDomain<?> domain) {
        this.domain = (IncomeTaxSettingDomain<? extends D>) domain;
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
    public IncomeTaxSettingDomain<? extends D> getDomain() {
        return this.domain;
    }
}
