package org.delivery.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.converter.TokenConverter;
import org.delivery.api.domain.token.service.TokenService;
import org.delivery.db.BaseEntity;
import org.delivery.db.customer.CustomerEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /**
     * 1. Customer entity 의 CustomId 추출
     * 2. access, refresh token 발행
     * 3. converter -> token response로 변경
     * @param customerEntity
     * @return
     */
    public TokenResponse issueToken(CustomerEntity customerEntity){
        return Optional.ofNullable(customerEntity)
                .map(BaseEntity::getId)
                .map(id-> {
                    var acessToken = tokenService.issueAccessToken(id);
                    var refreshToken = tokenService.issueRefreshToken(id);
                    return tokenConverter.toResponse(acessToken, refreshToken);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    public Long validationAccessToken(String accessToken){
        var customerId = tokenService.validationToken(accessToken);
        return customerId;
    }
}
