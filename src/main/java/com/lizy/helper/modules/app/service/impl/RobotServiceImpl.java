package com.lizy.helper.modules.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lizy.helper.modules.app.service.IRobotService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author lzy
 * @date 2021/8/29
 */
@Service
public class RobotServiceImpl implements IRobotService {

    @Value("${robot.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getMessage(String message) {

        String requestUrl = url + getText(message);
        final String resStr = restTemplate.getForObject(requestUrl, String.class);
        final Map response = JSONObject.parseObject(resStr, Map.class);
        return response.get("content").toString();
    }

    public String getText(String html){
        Document document = Jsoup.parse(html);
        return document.getElementsByTag("div").text();
    }

    public static void main(String[] args) {
        String html = "<div style=\\\"display: flex;align-items: center;word-wrap:break-word;\\\">1</div>";
        final Document document = Jsoup.parse(html);
        document.getElementsByTag("div");
    }
}
