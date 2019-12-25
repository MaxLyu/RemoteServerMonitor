package com.max.serverMonitor.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
@Component("md5Util")
public class MD5Util {
    public String passwordEncryptor(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
