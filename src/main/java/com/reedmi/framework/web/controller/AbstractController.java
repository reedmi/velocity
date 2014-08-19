package com.reedmi.framework.web.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AbstractController extends HandlerInterceptorAdapter {

	private static final ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<HttpServletRequest>();

	private static final ThreadLocal<HttpServletResponse> RESPONSE = new ThreadLocal<HttpServletResponse>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		REQUEST.set(request);
		RESPONSE.set(response);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		REQUEST.remove();
		RESPONSE.remove();
		if (ex != null){
			response.sendError(500);
		}
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 获取当前请求中的HttpServletRequest实例。
	 */
	public HttpServletRequest request(){
		return REQUEST.get();
	}

	/**
	 * 获取当前请求中的HttpServletRequest实例。
	 */
	public HttpServletResponse response(){
		return RESPONSE.get();
	}

	/**
	 * 请求不合法的后续处理。
	 */
	public void badRequest(String info){
		try {
			response().sendError(500);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端反馈状态码。
	 * @param status 200/500...
	 */
	public void sendStatus(int status){
		response().setStatus(status);
		try {
			response().getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}