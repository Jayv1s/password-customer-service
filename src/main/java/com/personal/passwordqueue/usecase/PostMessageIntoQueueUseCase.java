package com.personal.passwordqueue.usecase;

import com.personal.passwordqueue.gateway.SQSGateway;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.PasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class PostMessageIntoQueueUseCase {

    @Autowired // TODO-3: Refact those autowired for a better approach
    private SQSGateway sqsGateway;

    public void doExecute(PasswordDTO passwordDTO, String queueArn) { // TODO-2: Make message generic to receive any model
        log.info("PostMessageIntoQueueUseCase " + passwordDTO.getPassword());

        sqsGateway.sendMessageToQueue(passwordDTO,  queueArn);
    }
}
