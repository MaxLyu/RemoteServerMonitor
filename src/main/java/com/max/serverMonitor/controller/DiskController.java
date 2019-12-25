package com.max.serverMonitor.controller;

import com.max.serverMonitor.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DiskController {
    @Autowired
    DiskService diskService;
    @RequestMapping("/disk")
    @ResponseBody
    public void responseStoreUsage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().print(diskService.getStoreUsage());
    }
}
