package com.face.Recognition.Service;

import com.face.Recognition.Core.FaceRecognizer;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RecognitionServiceImpl implements RecognitionService {

    @Override
    public String Recognizer(MultipartFile sourceImage, MultipartFile targetImage) {
        JSONObject json = new JSONObject();
        if (sourceImage.isEmpty() || targetImage.isEmpty()) {
            json.put("result", "Invalid Request");
            return json.toString();
        }

        FaceRecognizer faceRecognizer = new FaceRecognizer();
        json.put("result", faceRecognizer.getResult(sourceImage, targetImage));
        return json.toString();
	}

}