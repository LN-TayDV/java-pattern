package com.spring.ctx.domain.chapter04.JSR.preDestroy;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileManager  {

    private String file;

    public FileManager() {
        log.info("Creating bean of type {}", com.spring.ctx.domain.chapter04.executing.method.bean.destroyed.FileManager.class.getName());

        try {
            file = "tay.zip";
        } catch (Exception e) {
            log.error("Could not create file");
        }
    }

    @PreDestroy
    private void preDestroy() throws IOException {
        log.info("Calling PreDestroy.destroy() on bean of type {}", FileManager.class.getName());
        if (file != null) {
            file = "";
        }
    }

}
