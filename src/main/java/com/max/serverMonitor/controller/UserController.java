package com.max.serverMonitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.max.serverMonitor.domain.User;
import com.max.serverMonitor.service.UserService;
import com.max.serverMonitor.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService impUserService;
    @Autowired
    private MD5Util md5Util;

    @RequestMapping("/login")
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String salt = impUserService.findSalt(username);
        String mdPassword = md5Util.passwordEncryptor(password+salt);
        // 从 session 中获取生成的验证码
        String savedCode = (String) session.getAttribute("validateCode");

        // 将 username 和 password 封装成 User 对象
        String jsonStr = "{\"username\":"+"\""+username+"\""+","+"\"password\":"+"\""+mdPassword+"\""+"}";
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        User user = (User) JSONObject.toJavaObject(jsonObject, User.class);

        User getUser = impUserService.findUser(user);
        if (getUser != null && !getUser.toString().isEmpty() && code.equals(savedCode)){
            session.setAttribute("user", user);
            response.getWriter().print("correct");
        }
        else if (!code.equals(savedCode))
            response.getWriter().print("wrongCode");
        else response.getWriter().print("false");
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将 session user的值删除并重定向到登陆页面
        session.removeAttribute("user");
        response.sendRedirect("login.jsp");
    }
}
