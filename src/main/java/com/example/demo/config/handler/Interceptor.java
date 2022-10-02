package com.example.demo.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class Interceptor  implements HandlerInterceptor {
	public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setHeader("Access-Control-Allow-Credentials","true");
		for (String method : ALLOWED_METHOD_NAMES.split(",")) {
			if(method.equals(request.getMethod())){
				return true;
			}
		}
		if (CorsUtils.isPreFlightRequest(request)) {
			return true;
		}
		return false;
	}

}
