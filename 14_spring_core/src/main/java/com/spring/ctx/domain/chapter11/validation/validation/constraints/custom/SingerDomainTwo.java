package com.spring.ctx.domain.chapter11.validation.validation.constraints.custom;

import com.spring.ctx.domain.chapter11.validation.validation.constraints.SingerDomain;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingerDomainTwo {

    @NotNull
    @Size(min = 2, max = 60)
    private String firstName;

    private String lastName;

    @NotNull
    private SingerDomain.Genre genre;

    private SingerDomain.Gender gender;

    @AssertTrue(message = "ERROR => [SingerDomainTwo]]! Individual singer should have gender and last name defined")
    public boolean isCountrySinger() {
        return genre == null
            || (
            !genre.equals(SingerDomain.Genre.COUNTRY)
                || (gender != null && lastName != null)
        );
    }
}
