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

package com.iluwatar.prototype;

import com.iluwatar.prototype.factory.HeroFactoryImpl;
import com.iluwatar.prototype.kingdom.elf.ElfBeast;
import com.iluwatar.prototype.kingdom.elf.ElfMage;
import com.iluwatar.prototype.kingdom.elf.ElfWarlord;
import com.iluwatar.prototype.kingdom.orc.OrcBeast;
import com.iluwatar.prototype.kingdom.orc.OrcMage;
import com.iluwatar.prototype.kingdom.orc.OrcWarlord;

import lombok.extern.slf4j.Slf4j;

/**
 * The Prototype pattern is a creational design pattern in software development.
 * It is used when the
 * type of objects to create is determined by a prototypical instance, which is
 * cloned to produce
 * new objects. This pattern is used to: - avoid subclasses of an object creator
 * in the client
 * application, like the abstract factory pattern, does. - avoid the inherent
 * cost of creating a new
 * object in the standard way (e.g., using the 'new' keyword)
 *
 * <p>
 * In this example we have a factory class ({@link HeroFactoryImpl}) producing
 * objects by
 * cloning the existing ones. The factory's prototype objects are given as
 * constructor parameters.
 */

/**
 * Mẫu Prototype là một mẫu thiết kế trong phát triển phần mềm.
 * Nó được sử dụng khi loại đối tượng cần tạo ra được xác định bởi một đối tượng mẫu (prototype),
 * đối tượng này được sao chép để tạo ra các đối tượng mới. Mẫu này được sử dụng để:
 *
 * Tránh việc tạo ra các lớp con của một đối tượng tạo (object creator) trong ứng dụng khách,
 * tương tự như mẫu abstract factory.
 *
 * Tránh chi phí cố hữu của việc tạo ra một đối tượng mới
 * theo cách thông thường (ví dụ: sử dụng từ khóa 'new').
 *
 * Trong ví dụ này, chúng ta có một lớp nhà máy ({@link HeroFactoryImpl})
 * sản xuất các đối tượng bằng cách sao chép các đối tượng hiện có.
 * Các đối tượng mẫu của nhà máy này được cung cấp dưới dạng các tham số của constructor.
 */
@Slf4j
public class App {

    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        var factory = new HeroFactoryImpl(
            new ElfMage("cooking"),
            new ElfWarlord("cleaning"),
            new ElfBeast("protecting")
        );

        var mage = factory.createMage();
        var warlord = factory.createWarlord();
        var beast = factory.createBeast();

        LOGGER.info(mage.toString());
        LOGGER.info(warlord.toString());
        LOGGER.info(beast.toString());

        /* -------------------------------------------- */
        factory = new HeroFactoryImpl(
            new OrcMage("axe"),
            new OrcWarlord("sword"),
            new OrcBeast("laser")
        );

        mage = factory.createMage();
        warlord = factory.createWarlord();
        beast = factory.createBeast();

        LOGGER.info(mage.toString());
        LOGGER.info(warlord.toString());
        LOGGER.info(beast.toString());
    }
}
