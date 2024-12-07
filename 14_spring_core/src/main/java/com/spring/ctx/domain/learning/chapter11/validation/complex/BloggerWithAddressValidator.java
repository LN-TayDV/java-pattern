/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.spring.ctx.domain.learning.chapter11.validation.complex;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("bloggerWithAddressValidator")
public class BloggerWithAddressValidator implements Validator {

    private final Validator addressValidator;

    public BloggerWithAddressValidator(Validator addressValidator) {
        if (!addressValidator.supports(Address.class)) {
            throw new IllegalArgumentException(
                "The supplied [Validator] must support the validation of [Address] instances.");
        }
        this.addressValidator = addressValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BloggerWithAddress.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalSite", "personalSite.required");

        var b = (BloggerWithAddress) target;

        if (StringUtils.isEmpty(b.getFirstName()) && StringUtils.isEmpty(b.getLastName())) {
            errors.rejectValue("firstName", "firstNameOrLastName.required");
            errors.rejectValue("lastName", "firstNameOrLastName.required");
        }

        try {
            errors.pushNestedPath("address");
            ValidationUtils.invokeValidator(this.addressValidator, b.getAddress(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}
