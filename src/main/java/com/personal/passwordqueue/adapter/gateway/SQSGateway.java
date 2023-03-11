package com.personal.passwordqueue.adapter.gateway;

import com.personal.passwordqueue.adapter.dataprovider.SqsDataProvider;
import com.personal.passwordqueue.domain.model.PasswordMessage;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.PasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SQSGateway implements SqsDataProvider {

    @Autowired // TODO-3: Refact those autowired for a better approach
    private QueueMessagingTemplate queueMessagingTemplate;

    @Override
    public void sendMessageToQueue(PasswordDTO passwordDTO, String queueArn) {
        log.info("SQSGateway " + passwordDTO.getPassword());

        queueMessagingTemplate.convertAndSend(queueArn, passwordDTO);
    }
}
