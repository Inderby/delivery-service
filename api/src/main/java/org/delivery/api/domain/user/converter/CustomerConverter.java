package org.delivery.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.CustomerRegisterRequest;
import org.delivery.api.domain.user.controller.model.CustomerResponse;
import org.delivery.db.customer.CustomerEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class CustomerConverter {
    public CustomerEntity toEntity(CustomerRegisterRequest request){
        return Optional.ofNullable(request)
                .map(it -> {
                    //to entity
                    return CustomerEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    public CustomerResponse toResponse(CustomerEntity customerEntity) {
        return Optional.ofNullable(customerEntity)
                .map(it-> CustomerResponse.builder()
                        .id(customerEntity.getId())
                        .name(customerEntity.getName())
                        .email(customerEntity.getEmail())
                        .status(customerEntity.getStatus())
                        .registeredAt(customerEntity.getRegisteredAt())
                        .unregisteredAt(customerEntity.getUnregisteredAt())
                        .lastLoginAt(customerEntity.getLastLoginAt())
                        .build())
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }
}
