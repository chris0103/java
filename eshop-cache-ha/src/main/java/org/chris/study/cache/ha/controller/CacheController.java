package org.chris.study.cache.ha.controller;

import org.chris.study.cache.ha.http.HttpClientUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CacheController {

    @RequestMapping("/change/product")
    @ResponseBody
    public String changeProduct(Long productId) {
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println("response: " + response);
        return "success";
    }
}
