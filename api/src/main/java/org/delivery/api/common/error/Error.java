package org.delivery.api.common.error;

public interface Error {
    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
