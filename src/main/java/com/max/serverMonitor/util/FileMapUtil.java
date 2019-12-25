package com.max.serverMonitor.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component("fileMap")
public class FileMapUtil {
    // 通过 Map 封装
    public Map<String, String> getFileMap(String id, String name, String state, String empty){
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("text", name);
        map.put("state", state);
        map.put("empty", empty);
        return map;
    }
}
