package com.detection.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;


/**
 * @fileName OwnerUnitService.java
 * @author csk
 * @createTime 2017年3月3日 下午5:08:54
 * @version 1.0
 * @function
 */

public interface OwnerUnitService {
    
    public String getDataList() throws FileNotFoundException, IOException;

    public JSONObject getJSONDataList();
    
}
