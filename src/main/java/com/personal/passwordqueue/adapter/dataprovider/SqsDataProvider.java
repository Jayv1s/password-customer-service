package com.personal.passwordqueue.adapter.dataprovider;

import com.personal.passwordqueue.domain.IModelMapper;
import com.personal.passwordqueue.domain.model.PasswordMessage;
import org.mapstruct.factory.Mappers;
import org.openapitools.client.model.PasswordDTO;

public interface SqsDataProvider {
    void sendMessageToQueue(PasswordDTO passwordDTO, String queueArn);

    default void callSendMessageGateway(PasswordMessage password, String queueArn) {
        PasswordDTO passwordDTO = Mappers.getMapper(IModelMapper.class).toPasswordApplicationModel(password);
        this.sendMessageToQueue(passwordDTO, queueArn);
    }
}
