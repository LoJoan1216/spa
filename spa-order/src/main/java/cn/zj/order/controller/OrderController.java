package cn.zj.order.controller;

import cn.zj.order.service.OrderService;
import cn.zj.spa.bean.Product;
import cn.zj.spa.params.OrderParams;
import cn.zj.spa.utils.constants.HttpCode;
import cn.zj.spa.utils.resp.Result;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:44
 * @description 订单接口
 */
@Slf4j
@RestController
public class OrderController {
    @Qualifier("orderServiceV6Impl")
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/submit_order")
    public String submitOrder(OrderParams orderParams){
        log.info("提交订单时传递的参数:{}", JSONObject.toJSONString(orderParams));
        orderService.saveOrder(orderParams);
        return "success";
    }

    @GetMapping("/concurrent_request")
    public String concurrentRequest(){
        log.info("测试业务在高并发场景下是否存在问题");
        return "test";
    }

    @GetMapping("/test_sentinel")
    public String testSentinel(){
        log.info("测试sentinel");
        return "sentinel";
    }


}
