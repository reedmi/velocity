package com.reedmi.framework.web.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController extends AbstractController {

    @RequestMapping(value = "hello", method = {
        RequestMethod.GET, RequestMethod.POST
    })
    public String hello(@RequestBody String body) throws IOException {
        System.out.println(">>>>>>>>>>");
        System.out.println(body);
        System.out.println("===============================");
        System.out.println(body.indexOf('\n'));
        return "hello";
    }

    public static void main(String[] args) {
        String content = "------WebKitFormBoundaryO7jEtEiMrmo5BLM6\n" +
                "Content-Disposition: form-data; name=\"name\"\n\n" +
                "12345\n" +
                "------WebKitFormBoundaryO7jEtEiMrmo5BLM6\n" +
                "Content-Disposition: form-data; name=\"age\"\n\n" + "67890\n" +
                "------WebKitFormBoundaryO7jEtEiMrmo5BLM6\n";
        String separator = content.substring(0, content.indexOf('\n'));
        System.out.println(separator);
        for(String s : content.replaceAll("\n","").split(separator)){
            
            System.out.println("-------------");
            for(String s2 : s.split("name=\"")){
                System.out.println(s2);
            }
        }
    }
}
