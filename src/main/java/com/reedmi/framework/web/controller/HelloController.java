package com.reedmi.framework.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController extends AbstractController {

    @RequestMapping(value = "hello world")
    public String hello(@RequestBody String body, HttpServletRequest request) throws IOException {
        return "hello";
    }

    /**
     * 获取键值形式的参数
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String,String> readMapedParamsFromRequest(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        //Request parameters are extra information sent with the request. For HTTP servlets, parameters are contained in the query string or posted form data.
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            result.put(key, value);
        }
        return result;
    }

    /**
     * 从multipart/form-data读取数据
     * 数据格式：
     * ------WebKitFormBoundaryO7jEtEiMrmo5BLM6\r\n
     * Content-Disposition: form-data; name="filed1\r\n
     * value1\r\n
     * ------WebKitFormBoundaryO7jEtEiMrmo5BLM6\r\n
     * Content-Disposition: form-data; name="filed2\r\n
     * value2\r\n
     * ------WebKitFormBoundaryO7jEtEiMrmo5BLM6--\r\n
     * @param multiformBody
     */
    public Map<String, String> readMultipartFormdata(String multiformBody) {
        Map<String, String> params = new HashMap<>();
        int startPositon = multiformBody.indexOf('\r');
        String separator = multiformBody.substring(0, startPositon);// 获取分隔符
        int endPositon = multiformBody.lastIndexOf(separator);
        multiformBody = multiformBody.substring(startPositon + 2, endPositon);
        for(String s : multiformBody.replaceAll("\n","").replace("\r", "").split(separator)){
            String[] fileds = s.split("name=\"");//格式：filedName"value
            if(fileds.length > 1) {
                String[] filedOrValue = fileds[1].split("\"");
                String field = filedOrValue[0];
                String value = filedOrValue[1];
                params.put(field, value);
            }
        }
        return params;
    }
}
