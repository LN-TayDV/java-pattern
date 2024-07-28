package com.spring.ctx.domain.chapter04.executing.method.bean.destroyed;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FileManager {

    private String file;

    public FileManager() {
        log.info("Creating bean of type {}", FileManager.class.getName());

        try {
            file = "tay.zip";
        } catch (Exception e) {
            log.error("Could not create file");
        }
    }

    private void destroyMethod() throws  IOException {
        log.info("Destroying bean of type {}", FileManager.class.getName());
        if (file != null) {
            file = "";
        }
    }
}
