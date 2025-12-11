package com.najrotta.lingua.pronunciation.api;

import com.najrotta.lingua.pronunciation.application.PronunciationFeedbackDto;
import com.najrotta.lingua.pronunciation.application.PronunciationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pronunciation")
public class PronunciationController {

    private final PronunciationService pronunciationService;

    public PronunciationController(PronunciationService pronunciationService) {
        this.pronunciationService = pronunciationService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "Pronunciation API OK";
    }

    @PostMapping(
            value = "/evaluate",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<PronunciationFeedbackDto> evaluatePronunciation(
            @RequestParam("audio") MultipartFile audioFile,
            @RequestParam("referenceText") String referenceText,
            @RequestParam(value = "languageCode", defaultValue = "en-US") String languageCode
    ) {

        PronunciationFeedbackDto feedback =
                pronunciationService.evaluate(audioFile, referenceText, languageCode);

        return ResponseEntity.ok(feedback);
    }
}
