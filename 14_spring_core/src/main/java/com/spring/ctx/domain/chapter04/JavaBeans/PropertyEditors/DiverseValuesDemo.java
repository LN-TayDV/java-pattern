package com.spring.ctx.domain.chapter04.JavaBeans.PropertyEditors;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DiverseValuesDemo {

    public static void main(String... args) throws Exception {
        // Sử dụng đường dẫn được cung cấp
        String directoryPath = "C:/Users/ADMIN/Documents/Java OCA Exam/Spring/Data/Temp";

        File baseDir = new File(directoryPath);

        // Tạo tệp "test.txt" trong thư mục được chỉ định
        Path path = Files.createFile(Paths.get(baseDir.getAbsolutePath(), "test.txt"));

        // Ghi nội dung vào tệp
        Files.writeString(path, "Hello World!");

        path.toFile().deleteOnExit();

        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(ValuesHolder.class, DiverseValuesContainer.class);

        ctx.refresh();
        ctx.close();
    }
}
