package com.spring.ctx.domain.chapter03.IoC;

import com.spring.ctx.domain.chapter02.HelloWorldConfiguration;
import com.spring.ctx.domain.chapter02.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1. Dependency Pull
 * Mô tả: Đây là cách lấy dependency theo kiểu "pull" (kéo) từ container Spring khi cần thiết.
 * Cách hoạt động:
 * Các dependency không được cung cấp tự động.
 * Lập trình viên chủ động yêu cầu container Spring cung cấp dependency
 * thông qua cách gọi phương thức như ApplicationContext.getBean().
 *
 * Ưu điểm:
 * Rõ ràng và dễ hiểu trong các trường hợp đơn giản.
 *
 * Nhược điểm:
 * Tăng sự phụ thuộc vào framework (Spring-specific code).
 * Không tuân thủ nguyên tắc Inversion of Control (IoC), vì bạn cần tự yêu cầu dependency.
 */

public class DependencyPull {

    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}
