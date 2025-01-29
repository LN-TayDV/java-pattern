package com.spring.ctx.domain.configuration;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PackageCreationConfig {

    @Value("${spring.application.package.context.work}")
    private String workContext;

    @Value("${spring.application.package.context.payroll}")
    private String payrollContext;

    @Value("${spring.application.package.context.healthcare}")
    private String healthcareContext;

    // Cấu hình để tạo thư mục khi ứng dụng khởi động
    @PostConstruct
    public void createPackageStructure() {
        // Danh sách các context cần tạo
        List<String> contexts = Arrays.asList(workContext, payrollContext, healthcareContext);

        // Thư mục gốc
        String baseDir = "src/main/java/";

        // Tạo các thư mục con cho mỗi context
        for (String context : contexts) {
            createDirectories(baseDir + context);
        }

        System.out.println("Cấu trúc package đã được tạo thành công.");
    }

    // Phương thức tạo các thư mục con cho mỗi context
    private void createDirectories(String contextPath) {
        String[] subPackages = {"ws", "app", "dom", "infra", "adaptor", "pub", "pubimpl"};

        for (String subPackage : subPackages) {
            File dir = new File(contextPath + "/" + subPackage);
            if (!dir.exists()) {
                dir.mkdirs();  // Tạo thư mục nếu chưa tồn tại
            }
        }
    }
}
