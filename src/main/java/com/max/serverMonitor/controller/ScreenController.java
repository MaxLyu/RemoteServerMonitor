package com.max.serverMonitor.controller;

import com.max.serverMonitor.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Controller
public class ScreenController {
    @Autowired
    private ScreenService screenService;
    @RequestMapping("/screen")
    public void getScreen(HttpServletResponse response) throws IOException, AWTException {
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(screenService.getImg(), "png", out);
    }
}
