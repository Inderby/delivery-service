package org.delivery.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.CustomerBusiness;
import org.delivery.api.domain.user.controller.model.CustomerLoginRequest;
import org.delivery.api.domain.user.controller.model.CustomerRegisterRequest;
import org.delivery.api.domain.user.controller.model.CustomerResponse;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class CustomerOpenApiController {
    private final CustomerBusiness customerBusiness;

    //사용자 가입 요청
    @PostMapping("/register")
    public Api<CustomerResponse> register(
            @Valid
            @RequestBody Api<CustomerRegisterRequest> request
    ){
        var response = customerBusiness.register(request.getBody());
        return Api.OK(response);
    }

    @PostMapping("/login")
    public Api<CustomerResponse> login(
        @Valid
        @RequestBody Api<CustomerLoginRequest> request
    ){
        var response = customerBusiness.login(request.getBody());
        return Api.OK(response);
    }
}


