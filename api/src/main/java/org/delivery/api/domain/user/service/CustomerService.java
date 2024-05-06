package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.CustomerErrorCode;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.customer.CustomerEntity;
import org.delivery.db.customer.CustomerRepository;
import org.delivery.db.customer.enums.CustomerStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * User 도메인 로직을 처리하는 서비스
 */

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerEntity register(CustomerEntity customerEntity) {
        return Optional.ofNullable(customerEntity)
                .map(it -> {
                    customerEntity.setStatus(CustomerStatus.REGISTERED);
                    customerEntity.setRegisteredAt(LocalDateTime.now());
                    return customerRepository.save(customerEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Customer Entity Null"));
    }

    public CustomerEntity login(
            String email,
            String password
    ) {
        return getCustomerWithThrow(email, password);
    }

    public CustomerEntity getCustomerWithThrow(
            String email,
            String password
    ) {
        return customerRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                email,
                password,
                CustomerStatus.REGISTERED
        ).orElseThrow(() -> new ApiException(CustomerErrorCode.USER_NOT_FOUND));

    }

    public CustomerEntity getCustomerWithThrow(Long customerId) {
        return customerRepository.findFirstByIdAndStatusOrderByIdDesc(
                customerId,
                CustomerStatus.REGISTERED
        ).orElseThrow(() -> new ApiException(CustomerErrorCode.USER_NOT_FOUND));
    }
}
