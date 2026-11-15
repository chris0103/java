package org.chris.study.cache.ha.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixObservableCommand;
import org.chris.study.cache.ha.http.HttpClientUtils;
import org.chris.study.cache.ha.hystrix.command.GetProductInfoCommand;
import org.chris.study.cache.ha.hystrix.command.GetProductInfosCommand;
import org.chris.study.cache.ha.model.ProductInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rx.Observable;
import rx.Observer;

@Controller
public class CacheController {

    @RequestMapping("/getProductInfo")
    @ResponseBody
    public String getProductInfo(Long productId) {
        HystrixCommand<ProductInfo> command = new GetProductInfoCommand(productId);
        ProductInfo productInfo = command.execute();

        Long cityId = productInfo.getCityId();

        System.out.println(productInfo);
        return "success";
    }

    @RequestMapping("/getProductInfos")
    @ResponseBody
    public String getProductInfos(String productIds) {
        HystrixObservableCommand<ProductInfo> getProductInfosCommand = new GetProductInfosCommand(productIds.split(","));
        Observable<ProductInfo> observable = getProductInfosCommand.observe();
        observable.subscribe(new Observer<ProductInfo>() {

            @Override
            public void onCompleted() {
                System.out.println("获取完了所有的商品数据");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(ProductInfo productInfo) {
                System.out.println(productInfo);
            }
        });
        return "success";
    }

    @RequestMapping("/change/product")
    @ResponseBody
    public String changeProduct(Long productId) {
        String url = "http://127.0.0.1:8082/getProductInfo?productId=" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println("response: " + response);
        return "success";
    }


}
