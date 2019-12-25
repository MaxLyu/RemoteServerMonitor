package com.max.serverMonitor.controller;

import com.max.serverMonitor.service.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CPUController {
    @Autowired
    private CPUService cpuService;
    @RequestMapping("/cpu")
    public void responseCPUInfo (HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print(cpuService.getCPUInfo());
    }
}
