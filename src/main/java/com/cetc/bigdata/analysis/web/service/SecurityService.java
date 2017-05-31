package com.cetc.bigdata.analysis.web.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
