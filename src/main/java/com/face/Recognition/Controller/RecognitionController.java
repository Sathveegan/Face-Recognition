package com.face.Recognition.Controller;

import com.face.Recognition.Service.RecognitionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/api/face/")
@RestController
public class RecognitionController {

    private final RecognitionService recognitionService;

    @Autowired
    public RecognitionController(RecognitionService recognitionService) {
        this.recognitionService = recognitionService;
    }

    @RequestMapping(value = "recognition", method = RequestMethod.POST, produces = "application/json")
    public String imageRecognizer(@RequestParam("sourceImage") MultipartFile sourceImage, @RequestParam("targetImage") MultipartFile targetImage) {
        return this.recognitionService.Recognizer(sourceImage, targetImage);
    }

}