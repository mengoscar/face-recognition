package com.meng.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * http 工具类
 */
public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * http post
     */
    public static String post(String url, Map<String, Object> params) {
        return httpRestClient(url, HttpMethod.POST, params);
    }

    /**
     * http get
     */
    public static String get(String url) {
        return httpRestClient(url, HttpMethod.GET, new HashMap<>());
    }

    /**
     * HttpMethod  post/get
     */
    private static String httpRestClient(String url, HttpMethod method, Map<String, Object> params) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10 * 1000);
        requestFactory.setReadTimeout(10 * 1000);
        RestTemplate client = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);
        //  执行HTTP请求
        try {
            ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("http request error！", e);
            return null;
        }
    }

}
