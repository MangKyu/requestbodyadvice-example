package com.mangkyu.requestbody.app;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final RequestResponseBodyMethodProcessor processor;

    public UserArgumentResolver(final List<HttpMessageConverter<?>> messageConverters) {
        processor = new RequestResponseBodyMethodProcessor(messageConverters);
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserAnnotation.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        final User user = (User) processor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        user.setDesc("desc");
        return user;
    }
}
