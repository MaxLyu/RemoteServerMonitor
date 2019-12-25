package com.max.serverMonitor.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.linux.LinuxFileSystem;
import oshi.software.os.windows.WindowsFileSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("diskService")
public class DiskService {

    public JSONArray getStoreUsage(){
        FileSystem fileSystem;
        ArrayList list;
        if (System.getProperty("file.separator").equals("/")){
            fileSystem = new LinuxFileSystem();
            list = getStoresCommon(fileSystem);
        }
        else {
            fileSystem = new WindowsFileSystem();
            list = getStoresCommon(fileSystem);
        }
        return JSONArray.parseArray(JSON.toJSONString(list));
    }

    // 获取相关信息并包装成 list 对象
    private ArrayList getStoresCommon(FileSystem fileSystem){
        ArrayList<Map> list = new ArrayList<>();

        OSFileStore[] osFileStores = fileSystem.getFileStores();
        for (OSFileStore osFileStore: osFileStores){
            Map<String, Object> map = new HashMap<>();
            String name = osFileStore.getName();
            long total = osFileStore.getTotalSpace();
            long free = osFileStore.getUsableSpace();
            long used = total - free;
            map.put("name", name);
            map.put("used", used);
            map.put("free", free);
            list.add(map);
        }
        System.out.println(list);
        return list;
    }
}
