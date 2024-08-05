package com.spring.ctx.domain.chapter11.validation.complex;

import java.net.URL;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloggerWithAddress {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private URL personalSite;

    private Address address;
}
