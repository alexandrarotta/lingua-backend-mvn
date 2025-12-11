package com.najrotta.lingua.pronunciation.application;

import org.springframework.web.multipart.MultipartFile;

public interface PronunciationService {
    
    PronunciationFeedbackDto evaluate(
        MultipartFile audioFile,
        String referenceText,
        String languageCode
    );
}
