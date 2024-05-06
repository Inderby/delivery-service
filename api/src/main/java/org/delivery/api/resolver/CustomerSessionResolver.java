package org.delivery.api.resolver;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.CustomerSession;
import org.delivery.api.domain.user.model.Customer;
import org.delivery.api.domain.user.service.CustomerService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class CustomerSessionResolver implements HandlerMethodArgumentResolver {

    private final CustomerService customerService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 어느테이션 체크 영역

        // 1. 어노테이션이 있는지 체크
        var annotation = parameter.hasParameterAnnotation(CustomerSession.class);

        // 2. 파라미터 타입 체크
        var parameterType = parameter.getParameterType().equals(Customer.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // support parameter에서  true 반환시 여기 실행

        // request context holder 에서 찾아오기
        var requestContext = RequestContextHolder.getRequestAttributes();

        var customerId = requestContext.getAttribute("customerId", RequestAttributes.SCOPE_REQUEST);

        var customerEntity = customerService.getCustomerWithThrow(Long.parseLong(customerId.toString()));

        // 사용자 정보 세팅
        var customer = Customer.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .status(customerEntity.getStatus())
                .password(customerEntity.getPassword())
                .address(customerEntity.getAddress())
                .registeredAt(customerEntity.getRegisteredAt())
                .unregisteredAt(customerEntity.getUnregisteredAt())
                .lastLoginAt(customerEntity.getLastLoginAt())
                .build();
        return null;
    }
}
