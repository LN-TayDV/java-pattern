package com.spring.ctx.domain.chapter04.disposable.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileManager implements DisposableBean {

    private Path file;

    public FileManager() {
        log.info("Creating bean of type {}", FileManager.class.getName());

        try {
            file = Files.createFile(Path.of("sample"));
        } catch (IOException e) {
            log.error("Could not create file");
        }
    }

    @Override
    public void destroy() throws Exception {
        log.info("Calling DisposableBean.destroy() on bean of type {}", FileManager.class.getName());
        if (file != null) {
            Files.deleteIfExists(file);
        }
    }
}
