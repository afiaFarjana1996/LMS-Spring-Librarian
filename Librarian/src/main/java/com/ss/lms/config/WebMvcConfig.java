package com.ss.lms.config;

import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.ss.lms.messageConverter.BookCopiesMessageConverter;

@Controller
public class WebMvcConfig extends WebMvcConfigurationSupport{

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new BookCopiesMessageConverter());
	}
}
