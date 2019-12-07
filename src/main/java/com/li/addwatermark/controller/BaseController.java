package com.li.addwatermark.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author likexue
 * @create 2019/11/2314:51
 **/
public class BaseController {
    Map<String,Object> map = new HashMap<>(5);

    public Map<String,Object> success(String msg,Object data){
        map.put("code",200);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }
    public Map<String,Object> success(String msg){
        map.put("code",200);
        map.put("msg",msg);
        return map;
    }
    public Map<String,Object> error(String errMsg){
        map.put("code",201);
        map.put("errMessage",errMsg);
        return map;
    }
}
