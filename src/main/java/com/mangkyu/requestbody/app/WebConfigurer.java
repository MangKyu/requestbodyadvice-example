package com.mangkyu.requestbody.app;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfigurer implements WebMvcConfigurer {

    private final List<HttpMessageConverter<?>> messageConverters;

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        System.out.println(messageConverters.size());
        resolvers.add(new UserArgumentResolver(messageConverters));
    }
}
