package com.face.Recognition.Core;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.util.IOUtils;

import org.springframework.web.multipart.MultipartFile;

public class FaceRecognizer {

    public FaceRecognizer() {
    }

    public boolean getResult(MultipartFile sourceImage, MultipartFile targetImage) {

        Float similarityThreshold = 70F;
        ByteBuffer sourceImageBytes = null;
        ByteBuffer targetImageBytes = null;

        boolean result = false;

        try {

            AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

            // Load source and target images and create input parameters
            try (InputStream inputStream = sourceImage.getInputStream()) {
                sourceImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
            } catch (Exception e) {
                return result;
            }
            try (InputStream inputStream = targetImage.getInputStream()) {
                targetImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
            } catch (Exception e) {
                return result;
            }

            Image source = new Image().withBytes(sourceImageBytes);
            Image target = new Image().withBytes(targetImageBytes);

            CompareFacesRequest request = new CompareFacesRequest().withSourceImage(source).withTargetImage(target)
                    .withSimilarityThreshold(similarityThreshold);

            // Call operation
            CompareFacesResult compareFacesResult = rekognitionClient.compareFaces(request);

            // Detect results
            List<CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
            if (faceDetails.size() > 0)
                result = true;
        } catch (Exception e) {
            return result;
        }

        return result;
    }

}