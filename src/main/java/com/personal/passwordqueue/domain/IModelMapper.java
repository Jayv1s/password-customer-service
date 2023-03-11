package com.personal.passwordqueue.domain;

import com.personal.passwordqueue.domain.model.PasswordMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.client.model.PasswordDTO;

@Mapper
public interface IModelMapper {

    @Mapping(target = "password", source = "password")
    PasswordMessage toPasswordDomainModel(PasswordDTO passwordDTO);

    @Mapping(target = "password", source = "password")
    PasswordDTO toPasswordApplicationModel(PasswordMessage password);
}
