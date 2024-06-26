package org.delivery.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);
        String temp = getRequestBody(req);
        log.info("###before : {} ", temp);
        chain.doFilter(req, res);

        // request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey->{
            var headerValue = req.getHeader(headerKey);
            headerValues.append(headerKey).append(" : ").append(headerValue + " , ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();
        log.info(">>> uri : {}, method : {}, header : {}, body : {}", uri, method, headerValues , requestBody);

        // response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);

             responseHeaderValues.append(headerKey).append(" : ").append(headerValue).append(" , ");
        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("<<< uri : {}, method : {}, header : {}, body : {}", uri, method, responseHeaderValues, responseBody);

        // 읽었던 body를 다시 복구 시켜줌
        res.copyBodyToResponse();
    }
    private String getRequestBody(ContentCachingRequestWrapper request) throws IOException {
        byte[] contentAsByteArray = request.getContentAsByteArray();
        if (contentAsByteArray.length > 0) {
            return new String(contentAsByteArray, request.getCharacterEncoding());
        }
        return "";
    }
}

