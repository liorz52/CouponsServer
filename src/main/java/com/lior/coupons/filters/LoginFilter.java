package com.lior.coupons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lior.coupons.dto.data.UserLoginData;
import com.lior.coupons.logic.CacheController;

@Component
public class LoginFilter implements Filter {

	@Autowired
	private CacheController cacheController;


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String pageRequested = req.getRequestURL().toString();

		if(pageRequested.endsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}

		if(pageRequested.endsWith("/users") && req.getMethod().toString().equals("POST")) {
			chain.doFilter(request, response);
			return;
		}

		String token = req.getHeader("Authorization");
		UserLoginData userLoginData= (UserLoginData) cacheController.getData(token);
		if(userLoginData!= null) {
			request.setAttribute("userLoginData", userLoginData);
			chain.doFilter(request, response);
			return;
		}

		HttpServletResponse res = (HttpServletResponse) response;
		res.setStatus(401);

	}
}
