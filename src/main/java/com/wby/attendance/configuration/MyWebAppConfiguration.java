package com.wby.attendance.configuration;

import com.wby.attendance.Interceptor.LogHandlerIntercepor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright ©2020 WangBoyi
 *
 * @Classname MyWebAppConfiguration
 * @Author WangBoyi
 * @Date 2020-2-15 17:04
 * @Description 配置类
 * @Version 1.0.0
 **/
@Configuration
public class MyWebAppConfiguration implements WebMvcConfigurer{

	@Bean
	public LogHandlerIntercepor logHandlerIntercepor(){
		return new LogHandlerIntercepor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logHandlerIntercepor())
				.excludePathPatterns("/static/**", "/log/**", "/log.html", "/log/**", "version.html", "/version", "/register", "/register/**");

	}

//	手动配置静态资源的url访问路径（逻辑上的）
//	实际目录是项目目录中的/static(这是spring boot默认存放静态资源的地方，
//	static 目录会被springboot放置在classes目录下，也就是classpath下，
//	classpath所处的文件夹路径（windows上的路径）与项目根目录的访问路径（域名路径）相映射)
//	这里是添加了一个新的静态资源访问路径，之前默认的/路径依然可用
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
				.addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
}
