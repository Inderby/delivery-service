package org.delivery.db.customer.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CustomerStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ;
    private final String description;
}
