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
package com.spring.ctx.domain.learning.chapter03.dependency.injection.setter;

import com.spring.ctx.domain.learning.chapter03.dependency.injection.setter.provider.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Đánh dấu lớp này là một Spring Component để Spring quản lý
@Component("renderer") // Đăng ký bean với tên "renderer" trong Application Context
public class StandardOutMessageRenderer implements MessageRenderer {

    // Dependency sẽ được inject thông qua setter
    private MessageProvider messageProvider;

    /**
     * Constructor mặc định.
     * Spring sẽ gọi constructor này khi khởi tạo bean.
     * Đây là nơi ta có thể thêm logic khởi tạo ban đầu (nếu cần).
     */
    public StandardOutMessageRenderer() {
        System.out.println(" --> StandardOutMessageRenderer: constructor called");
    }

    /**
     * Setter method để Spring inject dependency.
     * @Autowired: Cho phép Spring tự động gọi setter này để inject MessageProvider.
     * @param provider: Bean của kiểu MessageProvider được Spring cung cấp.
     */
    @Override
    @Autowired
    public void setMessageProvider(MessageProvider provider) {
        System.out.println(" --> StandardOutMessageRenderer: setting the provider");
        this.messageProvider = provider; // Lưu dependency được inject vào field
    }

    /**
     * Thực hiện hành động sử dụng dependency đã được inject.
     * Nếu dependency không được inject, sẽ ném RuntimeException.
     */
    @Override
    public void render() {
        if (messageProvider == null) {
            // Kiểm tra xem dependency đã được inject chưa
            throw new RuntimeException(
                "You must set the property messageProvider of class: "
                    + StandardOutMessageRenderer.class.getName());
        }
        // Sử dụng dependency để in thông điệp
        System.out.println(messageProvider.getMessage());
    }

    /**
     * Getter method trả về dependency đã được inject.
     * @return Dependency kiểu MessageProvider.
     */
    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
