package com.personal.passwordqueue.domain.usecase;

import com.personal.passwordqueue.adapter.dataprovider.SqsDataProvider;
import com.personal.passwordqueue.domain.model.PasswordMessage;
import com.personal.passwordqueue.adapter.gateway.SQSGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostMessageIntoQueueUseCase {

    @Autowired // TODO-3: Refact those autowired for a better approach
    private SQSGateway sqsGateway;

    @Autowired
    private SqsDataProvider sqsDataProvider;

    public void doExecute(PasswordMessage password, String queueArn) { // TODO-2: Make message generic to receive any model
        log.info("PostMessageIntoQueueUseCase " + password.getPassword());

        sqsDataProvider.callSendMessageGateway(password, queueArn);
    }
}
