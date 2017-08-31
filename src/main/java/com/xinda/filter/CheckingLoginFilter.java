package com.xinda.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.xinda.entity.User;

public class CheckingLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsrequest=(HttpServletRequest)request;
		User loginUser=(User)hsrequest.getSession().getAttribute("loginUser");
		//只有登陆用户角色为0或1才能正常访问
		if(null!=loginUser&&(loginUser.getUserRole()==0||loginUser.getUserRole()==1)){
			chain.doFilter(request, response);
		}
		else{
			hsrequest.getRequestDispatcher("/").forward(request, response);//返回登陆页面
			//return;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
