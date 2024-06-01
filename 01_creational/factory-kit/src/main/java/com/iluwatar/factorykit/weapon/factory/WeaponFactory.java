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

package com.iluwatar.factorykit.weapon.factory;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Functional interface, an example of the factory-kit design pattern.
 * <br>Instance created locally gives an opportunity to strictly define
 * which objects types the instance of a factory will be able to create.
 * <br>Factory is a placeholder for {@link Builder}s
 * with {@link WeaponFactory#create(WeaponType)} method to initialize new objects.
 */
public interface WeaponFactory {

    /**
     * Creates factory - placeholder for specified {@link Builder}s.
     *
     * @param consumer for the new builder to the factory.
     * @return factory with specified {@link Builder}s
     */

     /*
     * Phương thức factory trong interface WeaponFactory thực sự tạo ra một đối tượng của chính interface WeaponFactory.
     * Điều đặc biệt ở đây là phương thức này được đặt là static,
     * điều này có nghĩa là nó có thể được gọi trực tiếp từ interface WeaponFactory

     * Thay vào đó, bạn có thể gọi trực tiếp phương thức factory từ interface WeaponFactory
     * để tạo ra một đối tượng WeaponFactory mới, chưa cần đến bất kỳ lớp triển khai cụ thể nào.
     */

    /*
     * Trong dòng code `consumer.accept(map::put);`, `consumer` là một đối tượng của kiểu `Consumer<Builder>`.
     * Phương thức `accept` của `Consumer` được sử dụng để chấp nhận một đối tượng và thực hiện một hành động
     * trên đối tượng đó.
     *
     * Trong trường hợp này, `consumer.accept(map::put)` đang chấp nhận một đối tượng và thực hiện một hành động
     * trên đối tượng đó. Đối tượng được chấp nhận ở đây là một đối tượng của kiểu `Builder`.
     *
     * Vì `Builder` là một functional interface, nó có một phương thức duy nhất cần được triển khai là
     * `add(WeaponType name, Supplier<Weapon> supplier)`.
     * Trong lời gọi `map::put`, `map` là một đối tượng của kiểu `Map<WeaponType, Supplier<Weapon>>`,
     * và `put` là một phương thức của `Map` được sử dụng để thêm một cặp key-value vào map.
     *
     * Do đó, khi `consumer.accept(map::put)` được gọi,
     * nó thực hiện việc thêm một cặp key-value vào map, trong đó key là một đối tượng `WeaponType`,
     * và value là một đối tượng `Supplier<Weapon>`.
     */

    /*
     * Phương thức create(WeaponType name) trong interface WeaponFactory được triển khai
     * thông qua một biểu thức lambda name -> map.get(name).get();.
     *
     * Trong biểu thức lambda này:
     *
     * name là tham số đầu vào của lambda, đại diện cho WeaponType mà bạn muốn tạo.
     * map.get(name) trả về giá trị tương ứng với name từ map, tức là một đối tượng Supplier<Weapon>.get() được gọi
     * để lấy ra đối tượng Weapon từ Supplier<Weapon>.
     *
     * Tóm lại, khi gọi phương thức create(WeaponType name) với một WeaponType cụ thể,
     * nó sẽ trả về đối tượng Weapon được tạo ra từ Supplier<Weapon> tương ứng với name từ map.
     */

    static WeaponFactory factory(Consumer<Builder> consumer) {
        var map = new HashMap<WeaponType, Supplier<Weapon>>();
        consumer.accept(map::put);
        return name -> map.get(name).get();
    }

    /**
     * Creates an instance of the given type.
     *
     * @param name representing enum of an object type to be created.
     * @return new instance of a requested class implementing {@link Weapon} interface.
     */
    Weapon create(WeaponType name);
}
