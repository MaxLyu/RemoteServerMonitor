package com.max.serverMonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class ValidateCodeController {
    private static final long serialVersionUID = 1L;
    private static int WIDTH = 70;                // 验证码图片的宽度
    private static int HEIGHT = 35;            // 验证码图片的高度

    @RequestMapping("/validateCode")
    @ResponseBody
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();
        // 设置浏览器不要缓存该图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 创建图片缓存区
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();					// 获取画笔
        char [] rands = generateCheckCode();			// 产生随机的验证码
        // 产生图像
        drawBackground(g);
        drawRands(g, rands);
        // 结束绘画过程，完成图像
        g.dispose();
        // 将图像输出到客户端
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", bos);
        byte [] buf = bos.toByteArray();
        response.setContentLength(buf.length);
        sos.write(buf);
        bos.close();
        sos.close();
        // 将当前验证码存入 Session 中
        session.setAttribute("validateCode", new String(rands));
        //System.out.println("1:"+request.getSession().getId());
        System.out.println(session.getAttribute("validateCode"));
    }

    private void drawRands(Graphics g, char[] rands) {
        g.setColor(new Color(41, 41, 41));
        g.setFont(new Font(null, Font.ITALIC|Font.BOLD, 18));
        g.drawString("" + rands[0], 2, 17);
        g.drawString(" " + rands[1], 16, 20);
        g.drawString(" " + rands[2], 31, 13);
        g.drawString(" " + rands[3], 46, 18);
        //System.out.println(rands);
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // 随机产生 120 个干扰点
        for(int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
    }

    // 生成四个字符的验证码
    private char[] generateCheckCode() {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
        char [] rands = new char[4];
        for(int i = 0; i <4; i++) {
            int rand = (int) (Math.random() * 36);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }
}

