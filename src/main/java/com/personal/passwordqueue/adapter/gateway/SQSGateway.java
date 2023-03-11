package com.personal.passwordqueue.gateway;

import com.personal.passwordqueue.domain.model.PasswordMessage;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.PasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class SQSGateway {
    @Autowired // TODO-3: Refact those autowired for a better approach
    private QueueMessagingTemplate queueMessagingTemplate;

    public void sendMessageToQueue(PasswordMessage password, String queueArn) { // TODO-2: Make message generic to receive any model
        log.info("SQSGateway " + password.getPassword());

        queueMessagingTemplate.convertAndSend(queueArn, password);
    }
}
