package com.isagiongo.studynotesia;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class StudyNotesChatGPTService {

    private static final String CHAT_GPT_MODEL = "gpt-3.5-turbo";
    private static final double TEMPERATURE = 0.2;
    private static final String ROLE = "user";
    private final WebClient webClient;

    public StudyNotesChatGPTService(WebClient.Builder builder, @Value("${openai.api.key}") String apiKey) {
        this.webClient = builder
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", String.format("Bearer %s", apiKey))
                .build();
    }

    public Mono<ChatGPTResponse> createStudyNotes(StudyNotesRequest studyNotesRequest) {
        ChatGPTRequest request = createStudyNotesRequest(studyNotesRequest);
        return webClient.post().bodyValue(request).retrieve().bodyToMono(ChatGPTResponse.class);
    }

    private ChatGPTRequest createStudyNotesRequest(StudyNotesRequest studyNotesRequest) {
        String question = "Quais são os pontos chave que devo estudar sobre o assunto: " + studyNotesRequest.content() + " em no máximo " + studyNotesRequest.lines() + "linhas";
        var messageToApi = new Message(ROLE, question);
        return new ChatGPTRequest(CHAT_GPT_MODEL, List.of(messageToApi), TEMPERATURE, studyNotesRequest.maxTokens());
    }
}

record StudyNotesRequest(String content, Integer lines, Integer maxTokens){}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
record ChatGPTRequest(String model, List<Message> messages, Double temperature, Integer maxTokens){
}

record Message(String role, String content){
}

record ChatGPTResponse(List<Choice> choices) {
}

record Choice(Message message) {
}