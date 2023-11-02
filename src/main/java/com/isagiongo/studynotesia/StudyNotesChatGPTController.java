package com.isagiongo.studynotesia;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class StudyNotesChatGPTController {

    private final StudyNotesChatGPTService service;

    public StudyNotesChatGPTController(StudyNotesChatGPTService service) {
        this.service = service;
    }

    @PostMapping("study-notes")
    public Mono<String> createStudyNotes(@RequestBody StudyNotesRequest studyNotesRequest) {
        return service.createStudyNotes(studyNotesRequest).map(chatGPTResponse -> chatGPTResponse.choices().get(0).message().content());
    }
 }
