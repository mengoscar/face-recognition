package com.meng.service;

import com.meng.bean.dto.FileDto;
import com.meng.utils.FileUtil;
import com.meng.utils.HttpUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class FaceRecognitionService {

    private static final Logger log = LoggerFactory.getLogger(FaceRecognitionService.class);

    @Value("${baidu.client-id}")
    private String clientId;
    @Value("${baidu.client-secret}")
    private String clientSecret;

    public FileDto recognition(MultipartFile file) {
        FileDto fileDto = new FileDto();
        fileDto.setFileName(file.getName());

        int number = 0;
        JSONObject result = faceDetect(file);
        if (result != null) {
            number = result.getInt("face_num");
        }
        fileDto.setNumber(number);
        return fileDto;
    }

    /**
     * 人像识别
     *
     * @param file 图片
     * @return 结果
     */
    private JSONObject faceDetect(MultipartFile file) {
        String accessToken = getAuth();
        if (!StringUtils.hasText(accessToken)) {
            log.error("get access token error");
            return null;
        }
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect?access_token=" + accessToken;
        Map<String, Object> param = new HashMap<>();
        param.put("image", FileUtil.generateBase64(file));
        param.put("image_type", "BASE64");
        param.put("max_face_num", 120);
        String responseBody = HttpUtil.post(url, param);
        log.info(responseBody);
        JSONObject json = new JSONObject(responseBody);
        return json.isNull("result") ? null : json.getJSONObject("result");
    }

    /**
     * 获取API访问token
     *
     * @return assess_token
     */
    private String getAuth() {
        // 获取token地址
        String getAccessTokenUrl = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials" + "&client_id=" + clientId + "&client_secret=" + clientSecret;
        String responseBody = HttpUtil.get(getAccessTokenUrl);
        log.info(responseBody);
        if (!StringUtils.hasText(responseBody)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject.getString("access_token");
    }

}
