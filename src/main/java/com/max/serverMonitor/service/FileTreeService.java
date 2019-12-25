package com.max.serverMonitor.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.max.serverMonitor.util.FileMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("fileTreeService")
public class FileTreeService {
    @Autowired
    private FileMapUtil fileMapUtil;

    // 获得磁盘的所有根目录
    public JSONArray getRoot() throws IOException {
        ArrayList<Map<String, String>> list = new ArrayList<>();
        File[] files = File.listRoots();
        for (File file : files) {
            Map<String, String> map;
            String devName = file.getCanonicalPath();
            //String name = devName.substring(8, 10);
            map = fileMapUtil.getFileMap(devName, devName, "closed", "false");
            list.add(map);
        }
        return JSONArray.parseArray(JSON.toJSONString(list));
    }

    // 获得该目录下文件名
    public JSONArray getFileDir(String path) {
        File file = new File(path);
        File[] fileList = file.listFiles();
        ArrayList<Map<String, String>> list = new ArrayList<>();
        if (fileList != null) {
            for (File subfile : fileList) {
                Map<String, String> map;
                if (subfile.isHidden())
                    continue;
                if (subfile.isDirectory()) {
                    String id = subfile.getPath();
                    String name = subfile.getName();
                    if (subfile.list() == null)
                        map = fileMapUtil.getFileMap(id, name, "closed", "true");
                    else
                        map = fileMapUtil.getFileMap(id, name, "closed", "false");

                    list.add(map);
                }
            }

        }
        return JSONArray.parseArray(JSON.toJSONString(list));
    }
}
