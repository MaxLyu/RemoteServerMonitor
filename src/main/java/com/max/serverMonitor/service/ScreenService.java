package com.max.serverMonitor.service;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service("screenService")
public class ScreenService {
    public BufferedImage getImg() throws AWTException {
        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return robot.createScreenCapture(new Rectangle(0, 0, screenSize.width,screenSize.height));
    }
}
