package org.chris.study.cache.ha.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.chris.study.cache.ha.http.HttpClientUtils;
import org.chris.study.cache.ha.model.ProductInfo;

public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    private Long productId;

    public GetProductInfoCommand(Long proudctId) {
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"));
        this.productId = proudctId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        return JSONObject.parseObject(response, ProductInfo.class);
    }
}
