package org.delivery.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url : {}", request.getRequestURL());

        //WEB, chrome의 경우 GET, POST API를 요청하기 전에 OPTION이라는 해당 메소드 지원 체크 API가 존재한다. 이를 통과 시켜준다.
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }
        // js, html, png resource를 요청하는 경우 pass
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // TODO header 검증


        return true; // 일단 통과 처리
    }
}
