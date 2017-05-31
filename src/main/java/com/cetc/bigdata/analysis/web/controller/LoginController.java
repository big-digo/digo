package com.cetc.bigdata.analysis.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController extends BaseController {
	
	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
    @RequestMapping(value = "/logon", method = RequestMethod.POST)
    @ResponseBody
    public String logon(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
    	
        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authRequest); //调用loadUserByUsername
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
            logger.info("authentication.getName()========================================" + authentication.getName()); 
            return this.returnSuccess("登录成功");
        } catch (AuthenticationException ex) {
        	logger.info("用户名或密码错误======================================="); 
        	return this.returnError("用户名或密码错误");
        }
    }
}