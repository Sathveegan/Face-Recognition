package com.face.Recognition.Service;

import org.springframework.web.multipart.MultipartFile;

public interface RecognitionService {

    String Recognizer(MultipartFile sourceImage, MultipartFile targetImage);
}