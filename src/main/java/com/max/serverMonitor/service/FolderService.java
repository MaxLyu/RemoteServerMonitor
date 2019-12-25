package com.max.serverMonitor.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.max.serverMonitor.util.FileMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Component("folderService")
public class FolderService {
    @Autowired
    private FileMapUtil fileMapUtil;

    public JSONArray getFile(String path){
        ArrayList<Map<String, String>> list = new ArrayList<>();

        if(path != null) {
            File file = new File(path);
            File[] fileList = file.listFiles();
            if(fileList != null)
            {
                for(File subfile : fileList)
                {
                    Map<String, String> map;
                    if(subfile.isDirectory())
                    {
                        String id = subfile.getPath();
                        String name = subfile.getName();
                        if(subfile.isHidden() || (subfile.listFiles()==null))
                            continue;
                        if(Objects.requireNonNull(subfile.list()).length == 0)
                            map = fileMapUtil.getFileMap(id,name,"closed","true");
                        else
                            map = fileMapUtil.getFileMap(id,name,"closed","false");
                        list.add(map);
                    }
                    else {
                        String id = subfile.getPath();
                        String name = subfile.getName();
                        map = fileMapUtil.getFileMap(id,name,"open","true");
                        list.add(map);
                    }
                }
            }
        }
        return JSONArray.parseArray(JSON.toJSONString(list));
    }
}
