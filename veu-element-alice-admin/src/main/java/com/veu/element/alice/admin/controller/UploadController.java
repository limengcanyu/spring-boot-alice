package com.veu.element.alice.admin.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/upload")
@RestController
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    /**
     * 上传文件
     *
     * @param uploadFile
     * @param request
     * @return
     */
    @RequestMapping("/elUploadFile")
    public String elUploadFile(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
        if (uploadFile.isEmpty()) {
            return "file is empty.";
        }

        String filename = uploadFile.getOriginalFilename();
        if (StringUtils.isEmpty(filename)) {
            return "上传文件名为空！";
        }
        logger.debug("filename: {}", filename);

        try {
            File file = new File(filename);
            uploadFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "上传文件成功！";
    }

    /**
     * 上传文件内容
     *
     * @param sheetList
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/elUploadContent")
    public String elUploadContent(@RequestBody List<Map<String, Object>> sheetList) {
        if (!CollectionUtils.isEmpty(sheetList)) {
            sheetList.forEach(sheet -> {
                logger.debug("sheetIndex: {} sheetName: {}", sheet.get("sheetIndex"), sheet.get("sheetName"));
                List<String> headers = (List<String>) sheet.get("headers");
                List<Map<String, Object>> results = (List<Map<String, Object>>) sheet.get("results");
                logger.debug("headers: {} \nresults: {}", JSONObject.toJSONString(headers), JSONObject.toJSONString(results));
            });
        }

        return "上传文件成功！";
    }
}
