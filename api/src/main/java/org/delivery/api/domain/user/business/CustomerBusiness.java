package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.CustomerLoginRequest;
import org.delivery.api.domain.user.controller.model.CustomerRegisterRequest;
import org.delivery.api.domain.user.controller.model.CustomerResponse;
import org.delivery.api.domain.user.converter.CustomerConverter;
import org.delivery.api.domain.user.service.CustomerService;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class CustomerBusiness {
    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    /**
     * 사용자에 대한 가입처리 로직
     * 1. request -> entity
     * 2. entity -> save
     * 3. save Entity -> response
     * 4. response return;
     * @param request
     * @return CustomerResponse
     */
    public CustomerResponse register(CustomerRegisterRequest request) {
        var entity = customerConverter.toEntity(request);
        var newEntity = customerService.register(entity);
        var response = customerConverter.toResponse(newEntity);
        return response;
//        return Optional.ofNullable(request)
//                .map(customerConverter::toEntity)
//                .map(customerService::register)
//                .map(customerConverter::toResponse)
//                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "request null"));
    }

    /**
     * 1. email, password를 통한 사용자 체크
     * 2. customer entity 로그인 확인
     * 3. token 생성
     * 4. token response
     * @param request
     * @return
     */
    public CustomerResponse login(CustomerLoginRequest request) {
        var customerEntity = customerService.login(request.getEmail(), request.getPassword());
        //사용자가 없으면 throw

        // TODO : 사용자가 있다면 token 생성 구현 필요
        return customerConverter.toResponse(customerEntity);
    }
}
