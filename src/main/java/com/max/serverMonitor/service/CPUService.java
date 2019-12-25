package com.max.serverMonitor.service;

import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

import java.text.DecimalFormat;
import java.util.ArrayList;

@Service("cpuService")
public class CPUService {
    private static SystemInfo systemInfo = new SystemInfo();

    public ArrayList getCPUInfo() {
        ArrayList<Double> list = new ArrayList<Double>();

        HardwareAbstractionLayer hal = systemInfo.getHardware();
        CentralProcessor processor = hal.getProcessor();
        // 获取逻辑处理器的个数
        int cpuCount = processor.getLogicalProcessorCount();
        // 逻辑处理器最近的使用情况
        double[] cpuUsed = processor.getProcessorCpuLoadBetweenTicks();

        for (int i=0; i < cpuCount; i++){
            // 数据保留两位小数点并加入到数组list中
            DecimalFormat df = new DecimalFormat("#.00");
            list.add(Double.parseDouble(df.format(cpuUsed[i])));
        }
        return list;
    }
}
