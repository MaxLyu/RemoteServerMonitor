package com.max.serverMonitor.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service("deleteService")
public class DeleteService {
    public void delFile(File file){
        if (file.exists() && file.isDirectory()){
            File[] files = file.listFiles();
            for (File f: files){
                delFile(f);
            }
        }
        file.delete();
    }
}
