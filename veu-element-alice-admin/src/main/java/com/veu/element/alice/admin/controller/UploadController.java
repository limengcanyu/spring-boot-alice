package com.veu.element.alice.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RequestMapping("/upload")
@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("/elUpload")
    public String elUpload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
        String filename = uploadFile.getOriginalFilename();
        logger.debug("filename: {}", filename);

        if (uploadFile.isEmpty()) {
            return "file is empty.";
        }

        try {
            File file = new File(filename);
            uploadFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "upload ok";
    }
}
