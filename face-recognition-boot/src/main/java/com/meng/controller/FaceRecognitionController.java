package com.meng.controller;

import com.meng.bean.dto.FileDto;
import com.meng.bean.dto.ResultData;
import com.meng.service.FaceRecognitionService;
import com.meng.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/face/recognition")
public class FaceRecognitionController {

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    /**
     * 通用上传请求
     */
    @PostMapping("")
    public ResultData recognition(MultipartFile file) {
        FileDto fileDto = faceRecognitionService.recognition(file);
        return ResultUtil.success(fileDto);
    }

}
