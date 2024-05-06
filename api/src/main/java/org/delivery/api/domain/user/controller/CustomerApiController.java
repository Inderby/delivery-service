package org.delivery.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.CustomerBusiness;
import org.delivery.api.domain.user.controller.model.CustomerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class CustomerApiController {
    private final CustomerBusiness customerBusiness;

    @GetMapping("/me")
    public Api<CustomerResponse> me(){
        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        var customerId = requestContext.getAttribute("customerId", RequestAttributes.SCOPE_REQUEST);
        var response = customerBusiness.me(Long.parseLong(customerId.toString()));
        return Api.OK(response);
    }
}
