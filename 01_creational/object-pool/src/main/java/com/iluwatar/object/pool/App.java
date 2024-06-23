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

package com.iluwatar.object.pool;

import lombok.extern.slf4j.Slf4j;

/**
 * Khi cần làm việc với một số lượng lớn đối tượng mà việc khởi tạo chúng đặc biệt tốn kém
 * và mỗi đối tượng chỉ cần trong một khoảng thời gian ngắn,
 * hiệu suất của toàn bộ ứng dụng có thể bị ảnh hưởng xấu.
 * Mẫu thiết kế đối tượng pool có thể được coi là mong muốn trong các trường hợp như vậy.
 *
 * Mẫu thiết kế đối tượng pool tạo ra một tập hợp các đối tượng có thể được tái sử dụng.
 * Khi cần một đối tượng mới, nó được yêu cầu từ pool.
 * Nếu có một đối tượng đã được chuẩn bị trước có sẵn, nó sẽ được trả về ngay lập tức,
 * tránh được chi phí khởi tạo.
 *
 * Nếu không có đối tượng nào có sẵn trong pool, một đối tượng mới sẽ được tạo ra và trả về.
 * Khi đối tượng đã được sử dụng và không còn cần thiết nữa, nó sẽ được trả về pool,
 * cho phép nó được sử dụng lại trong tương lai mà không cần lặp lại quá trình khởi tạo tốn kém.
 * Quan trọng là phải lưu ý rằng một khi đối tượng đã được sử dụng và trả về,
 * các tham chiếu hiện có sẽ trở nên không hợp lệ.
 *
 * Trong ví dụ này, chúng tôi đã tạo ra `OliphauntPool` kế thừa từ `ObjectPool` tổng quát.
 * Các đối tượng `Oliphaunt` có thể được mượn từ pool và sau đó trả lại cho nó.
 * Pool theo dõi các đối tượng đã được tạo và trạng thái của chúng (có sẵn, đang sử dụng).
 *
 * ---
 *
 * ### Giải Thích Chi Tiết về Mẫu Thiết Kế Object Pool
 *
 * #### Bối Cảnh Vấn Đề
 * 1. **Vấn Đề Hiệu Suất với Nhiều Đối Tượng:**
 *    - Khi một ứng dụng cần tạo ra một số lượng lớn đối tượng mà việc khởi tạo chúng rất tốn kém
 *    và mỗi đối tượng chỉ cần trong một khoảng thời gian ngắn, hiệu suất có thể bị giảm.
 *    Điều này là do việc lặp đi lặp lại quá trình tạo và hủy đối tượng tốn kém về thời gian và tài nguyên.
 *
 * #### Giải Pháp
 * 2. **Mẫu Thiết Kế Object Pool:**
 *    - Mẫu thiết kế này tạo ra một tập hợp các đối tượng mà có thể tái sử dụng.
 *    Khi một đối tượng mới cần thiết, nó được yêu cầu từ pool.
 *    Nếu có một đối tượng đã chuẩn bị sẵn có trong pool, nó sẽ được trả về ngay lập tức,
 *    tránh chi phí khởi tạo. Nếu không có đối tượng nào có sẵn, một đối tượng mới sẽ được tạo ra.
 *    Khi đối tượng đã sử dụng xong và không còn cần thiết,
 *    nó sẽ được trả lại pool để có thể tái sử dụng mà không cần khởi tạo lại.
 *
 * #### Lưu Ý
 * 3. **Lưu Ý Quan Trọng:**
 *    - Một khi đối tượng đã được sử dụng và trả lại pool, các tham chiếu hiện tại đến đối tượng đó sẽ trở nên không hợp lệ.
 *
 * ### Ví Dụ
 * - Trong đoạn mã này, `OliphauntPool` kế thừa từ `ObjectPool` tổng quát.
 *  Các đối tượng `Oliphaunt` có thể được mượn từ pool và sau đó trả lại.
 *  Pool theo dõi các đối tượng đã được tạo ra và trạng thái của chúng (có sẵn, đang sử dụng).
 *
 * Bằng cách sử dụng mẫu thiết kế này, bạn có thể cải thiện hiệu suất của ứng dụng
 * bằng cách tái sử dụng các đối tượng đắt đỏ thay vì tạo mới chúng mỗi lần cần.
 */
@Slf4j
public class App {

    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {

        var pool = new OliphauntPool();
        LOGGER.info(pool.toString());

        var oliphaunt1 = pool.checkOut(); // Mượn một đối tượng từ bể
        String checkedOut = "Checked out {}";
        LOGGER.info(checkedOut, oliphaunt1);
        LOGGER.info(pool.toString());

        var oliphaunt2 = pool.checkOut(); // Mượn đối tượng thứ hai
        LOGGER.info(checkedOut, oliphaunt2);

        var oliphaunt3 = pool.checkOut(); // Mượn đối tượng thứ ba
        LOGGER.info(checkedOut, oliphaunt3);
        LOGGER.info(pool.toString());

        LOGGER.info("Checking in {}", oliphaunt1); // Trả lại đối tượng đầu tiên
        pool.checkIn(oliphaunt1);
        LOGGER.info("Checking in {}", oliphaunt2); // Trả lại đối tượng thứ hai
        pool.checkIn(oliphaunt2);
        LOGGER.info(pool.toString());

        var oliphaunt4 = pool.checkOut(); // Mượn đối tượng thứ tư
        LOGGER.info(checkedOut, oliphaunt4);
        var oliphaunt5 = pool.checkOut(); // Mượn đối tượng thứ năm
        LOGGER.info(checkedOut, oliphaunt5);
        LOGGER.info(pool.toString());
    }
}
