package org.delivery.db.customer;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.customer.enums.CustomerStatus;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")
@Entity
public class CustomerEntity extends BaseEntity {


    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Column(length = 150, nullable = false)
    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

}
