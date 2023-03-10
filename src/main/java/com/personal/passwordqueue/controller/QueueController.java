package com.personal.passwordqueue.controller;

import com.personal.passwordqueue.PasswordQueueApplication;
import com.personal.passwordqueue.usecase.PostMessageIntoQueueUseCase;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import org.openapitools.client.api.PasswordServiceApi;
import org.openapitools.client.model.PasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@NoArgsConstructor
public class QueueController {

    @Autowired // TODO-3: Refact those autowired fora better approach
    private QueueMessagingTemplate queueMessagingTemplate;

    @Autowired // TODO-3: Refact those autowired fora better approach
    private PostMessageIntoQueueUseCase postMessageIntoQueueUseCase;

    @Value("${cloud.aws.queue}")  // TODO-1: Refact this env. stdin for constant file/layer
    public String queueArn;

    @PostMapping(path = "/password", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> postMessageToPasswordQueue(@RequestBody PasswordDTO passwordDTO) {
        // TODO-2: Add a model mapper approach
        try {
            postMessageIntoQueueUseCase.doExecute(passwordDTO, queueArn);
            return ResponseEntity.ok("Deu certo.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @GetMapping(path = "/password", produces = "application/json")
    public ResponseEntity<PasswordDTO> getMessage() {
        var response = queueMessagingTemplate.receiveAndConvert(queueArn, PasswordDTO.class);
        return ResponseEntity.ok(response);
    }
}
