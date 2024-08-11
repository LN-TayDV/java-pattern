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
package com.spring.ctx.domain.chapter11.validation.complex;

import com.spring.ctx.domain.chapter11.PropertyEditors.Blogger;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ComplexBloggerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Blogger.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        var b = (Blogger) target;

        if (StringUtils.isEmpty(b.getFirstName()) && StringUtils.isEmpty(b.getLastName())) {

            errors.rejectValue("firstName", "firstNameOrLastName.required");

            errors.rejectValue("lastName", "firstNameOrLastName.required");
        }

        if (b.getBirthDate().isBefore(LocalDate.of(1983, 1, 1))) {

            errors.rejectValue("birthDate", "birthDate.greaterThan1983");
        }
    }
}
