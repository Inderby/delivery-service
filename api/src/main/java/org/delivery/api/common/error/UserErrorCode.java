package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * User의 경우 1000번대 에러코드 사용
 */
@AllArgsConstructor
public enum UserErrorCode implements Error{
    USER_NOT_FOUND(400, 1404, "사용자를 찾을 수 없다."),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

    @Override
    public Integer getHttpStatusCode() {
        return this.httpStatusCode;
    }

    @Override
    public Integer getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
