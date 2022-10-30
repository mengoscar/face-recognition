package com.meng.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static String generateBase64(MultipartFile file) {
        String baseStr = null;
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            baseStr = encoder.encodeToString(file.getBytes());
            baseStr = baseStr.replaceAll("\r\n", "");
        } catch (Exception e) {
            log.error("generate base64 error", e);
        }
        return baseStr;
    }

}
