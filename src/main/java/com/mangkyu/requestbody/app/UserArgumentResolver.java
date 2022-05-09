package com.mangkyu.requestbody.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

@Slf4j
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final RequestResponseBodyMethodProcessor requestBodyProcessor;

    public UserArgumentResolver(final List<HttpMessageConverter<?>> messageConverters) {
        requestBodyProcessor = new RequestResponseBodyMethodProcessor(messageConverters);
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(DefaultUserRequestBody.class);
    }

    @Override
    public Object resolveArgument(
            final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory) {

        final User user = resolveUserArgument(parameter, mavContainer, webRequest, binderFactory);
        user.setDesc("desc");
        return user;
    }

    private User resolveUserArgument(
            final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory) {

        try {
            return (User) requestBodyProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        } catch (Exception e) {
            log.warn("Fail to resolve Argument", e);
        }

        return new User();
    }

}
