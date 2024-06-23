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

package com.iluwatar.monostate;


/**
 * The MonoState pattern ensures that all instances of the class will have the same state. This can
 * be used a direct replacement of the Singleton pattern.
 *
 * <p>In the following example, The {@link LoadBalancer} class represents the app's logic. It
 * contains a series of Servers, which can handle requests of type {@link Request}. Two instances of
 * LoadBalancer are created. When a request is made to a server via the first LoadBalancer the state
 * change in the first load balancer affects the second. So if the first LoadBalancer selects the
 * Server 1, the second LoadBalancer on a new request will select the Second server. If a third
 * LoadBalancer is created and a new request is made to it, then it will select the third server as
 * the second load balancer has already selected the second server.
 */

/**
 * Mẫu thiết kế MonoState đảm bảo rằng tất cả các thể hiện của lớp sẽ có cùng một trạng thái.
 * Điều này có thể được sử dụng như một thay thế trực tiếp của mẫu Singleton.
 *
 * Trong ví dụ sau, lớp LoadBalancer đại diện cho logic ứng dụng.
 * Nó chứa một loạt các Máy chủ, mà có thể xử lý các yêu cầu của kiểu Request.
 *
 * Hai thể hiện của LoadBalancer được tạo ra.
 *
 * Khi một yêu cầu được thực hiện đến một máy chủ thông qua LoadBalancer đầu tiên,
 * thay đổi trạng thái trong LoadBalancer đầu tiên ảnh hưởng đến thứ hai.
 *
 * Vì vậy, nếu LoadBalancer đầu tiên chọn Server 1,
 * thì LoadBalancer thứ hai trong một yêu cầu mới sẽ chọn Server thứ hai.
 *
 * Nếu một LoadBalancer thứ ba được tạo ra và một yêu cầu mới được thực hiện đến nó,
 * thì nó sẽ chọn máy chủ thứ ba vì LoadBalancer thứ hai đã chọn máy chủ thứ hai.
 */
public class App {
    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        var loadBalancer1 = new LoadBalancer();
        var loadBalancer2 = new LoadBalancer();
        loadBalancer1.serverRequest(new Request("Hello"));
        loadBalancer2.serverRequest(new Request("Hello World"));
    }

}
