package com.spring.ctx.domain.chapter04.JavaBeans.PropertyEditors;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

@Component
@ComponentScan("com.spring.ctx.domain.chapter04.JavaBeans.PropertyEditors")
public class ValuesHolder {

    public List<String> stringList;
    public InputStream inputStream;

    public ValuesHolder(List<String> stringList) {

        this.stringList = List.of("Mayer", "Psihoza", "Mazikeen");

        // Sử dụng đường dẫn được cung cấp
        String directoryPath = "C:/Users/ADMIN/Documents/Java OCA Exam/Spring/Data/Temp/test.txt";
        try {
            this.inputStream = new FileInputStream(directoryPath);

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // we are not interested in this exception
        }
    }
// getters
}
