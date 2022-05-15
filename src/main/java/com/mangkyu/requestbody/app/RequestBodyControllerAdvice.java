package com.mangkyu.requestbody.app;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

@RestControllerAdvice
public class RequestBodyControllerAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(final MethodParameter methodParameter, final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getContainingClass() == UserController.class && targetType.getTypeName().equals(User.class.getTypeName());
    }

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage, final MethodParameter parameter, final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(final Object body, final HttpInputMessage inputMessage, final MethodParameter parameter, final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) {
        final User user = (User) body;
        user.setDesc("desc");
        return user;
    }

    @Override
    public Object handleEmptyBody(final Object body, final HttpInputMessage inputMessage, final MethodParameter parameter, final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

}
