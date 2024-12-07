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
package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.CreatingAdviceProgrammatically.detection;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {

    // Cache để lưu trữ các phương thức getter cho từng setter.
    private final Map<Method, Method> methodCache = new HashMap<>();

    // Predicate xác định liệu phương thức đang gọi có phải là setter không.
    private final Predicate<MethodInvocation> isSetter = invocation ->
        invocation.getMethod().getName().startsWith("set") && (invocation.getArguments().length == 1);

    // Biến dùng để kiểm tra xem đối tượng có bị thay đổi (modified) không.
    private boolean isModified = false;

    // Phương thức từ interface IsModified để trả về trạng thái "modified"
    @Override
    public boolean isModified() {
        return isModified;
    }

    // Phương thức chính của AOP để can thiệp vào các phương thức setter
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // Nếu chưa bị thay đổi (isModified == false)
        if (!isModified) {
            // Kiểm tra nếu phương thức là một setter
            if (isSetter.test(invocation)) {
                // Tìm phương thức getter tương ứng với setter
                Method getter = getGetter(invocation.getMethod());
                if (getter != null) {
                    // Lấy giá trị mới được truyền vào setter
                    Object newVal = invocation.getArguments()[0];
                    // Lấy giá trị cũ từ getter
                    Object oldVal = getter.invoke(invocation.getThis(), null);

                    // So sánh giá trị cũ và mới để xác định có thay đổi không
                    if (newVal == null && oldVal == null) {
                        isModified = false; // Không thay đổi nếu cả hai giá trị đều null
                    } else if ((newVal == null && oldVal != null) || (newVal != null && oldVal == null)) {
                        isModified = true; // Thay đổi nếu một trong hai giá trị là null
                    } else {
                        // Nếu cả hai giá trị khác nhau, thì là thay đổi
                        isModified = !newVal.equals(oldVal);
                    }
                }
            }
        }
        // Tiếp tục thực hiện phương thức đã được gọi
        return super.invoke(invocation);
    }

    // Tìm getter tương ứng với setter đã được gọi
    private Method getGetter(Method setter) {
        // Kiểm tra trong cache xem đã có getter chưa
        Method getter = methodCache.get(setter);
        if (getter != null) {
            return getter;
        }
        // Tạo tên getter từ tên setter, ví dụ: "setBrand" -> "getBrand"
        String getterName = setter.getName().replaceFirst("set", "get");
        try {
            // Tìm getter trong lớp của setter
            getter = setter.getDeclaringClass().getMethod(getterName, null);
            // Lưu getter vào cache để tái sử dụng
            synchronized (methodCache) {
                methodCache.put(setter, getter);
            }
            return getter;
        } catch (NoSuchMethodException ex) {
            return null; // Trả về null nếu không tìm thấy getter
        }
    }
}
