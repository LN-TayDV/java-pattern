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

package com.iluwatar.factory.method.weapon.impl;

import com.iluwatar.factory.method.factory.WeaponType;
import com.iluwatar.factory.method.weapon.Weapon;

/**
 * ElfWeapon.
 */

/**
 * Những điểm khác biệt chính giữa record và class truyền thống bao gồm:
 *
 * ### Từ khóa và cấu trúc:
 * Record sử dụng từ khóa `record` và định nghĩa các trường (component) trong phần khai báo của record.
 * Trong khi đó, class sử dụng từ khóa `class` và yêu cầu người lập trình phải định nghĩa các trường,
 * constructor, và các phương thức khác một cách rõ ràng.
 *
 * ### Tính bất biến:
 * Các trường trong record mặc định là `final` và không thể thay đổi sau khi khởi tạo,đảm bảo tính bất biến.
 * Trong class, bạn phải tự định nghĩa các trường là `final` để đạt được tính bất biến tương tự.
 *
 * ### Tự động sinh mã:
 * Record tự động sinh ra các phương thức như `equals()`, `hashCode()`, `toString()`, và các getter cho các trường.
 * Trong class, bạn phải tự viết hoặc dùng các công cụ hỗ trợ để sinh ra các phương thức này,
 * điều này có thể làm mã nguồn trở nên phức tạp và dễ xảy ra lỗi.
 *
 * Với những cải tiến này, record giúp việc tạo ra các cấu trúc dữ liệu đơn giản trở nên dễ dàng và ngắn gọn hơn,
 * giảm thiểu lỗi lập trình và tiết kiệm thời gian, cho phép lập trình viên tập trung vào logic chính của ứng dụng.

 */
public record ElfWeapon(WeaponType weaponType) implements Weapon {

    @Override
    public String toString() {
        return "an elven " + weaponType;
    }
}
