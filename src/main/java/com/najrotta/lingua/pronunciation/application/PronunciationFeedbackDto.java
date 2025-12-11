package com.najrotta.lingua.pronunciation.application;

import java.util.List;
import java.util.Map;

public class PronunciationFeedbackDto {

    private double globalScore;              // 0..100
    private String recognizedText;           // lo que "oyó" el sistema
    private Map<String, Double> wordScores;  // palabra -> score
    private List<String> tips;               // recomendaciones

    public double getGlobalScore() {
        return globalScore;
    }

    public void setGlobalScore(double globalScore) {
        this.globalScore = globalScore;
    }

    public String getRecognizedText() {
        return recognizedText;
    }

    public void setRecognizedText(String recognizedText) {
        this.recognizedText = recognizedText;
    }

    public Map<String, Double> getWordScores() {
        return wordScores;
    }

    public void setWordScores(Map<String, Double> wordScores) {
        this.wordScores = wordScores;
    }

    public List<String> getTips() {
        return tips;
    }

    public void setTips(List<String> tips) {
        this.tips = tips;
    }
}
