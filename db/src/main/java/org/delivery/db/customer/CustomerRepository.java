package org.delivery.db.customer;

import org.delivery.db.customer.enums.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    Optional<CustomerEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, CustomerStatus status);

    Optional<CustomerEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, CustomerStatus status);
}
