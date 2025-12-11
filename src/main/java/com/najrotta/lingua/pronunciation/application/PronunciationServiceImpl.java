package com.najrotta.lingua.pronunciation.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PronunciationServiceImpl implements PronunciationService {

    @Override
    public PronunciationFeedbackDto evaluate(
            MultipartFile audioFile,
            String referenceText,
            String languageCode
    ) {
        // TODO: aquí irán las llamadas reales a STT + API de pronunciación

        PronunciationFeedbackDto dto = new PronunciationFeedbackDto();

        // Datos dummy para probar el flujo
        dto.setGlobalScore(82.5);
        dto.setRecognizedText(referenceText);

        Map<String, Double> wordScores = new HashMap<>();
        if (referenceText != null) {
            String[] words = referenceText.split("\\s+");
            double base = 80.0;
            for (int i = 0; i < words.length; i++) {
                wordScores.put(words[i], base + (i % 5)); // 80..84
            }
        }
        dto.setWordScores(wordScores);

        List<String> tips = Arrays.asList(
                "Buen ritmo general, cuida un poco más la entonación.",
                "Practica la pronunciación de vocales largas y cortas.",
                "Repite la frase enfatizando las palabras clave."
        );
        dto.setTips(tips);

        return dto;
    }
}
