package cn.zj.order.service.impl;

import cn.zj.order.mapper.OrderItemMapper;
import cn.zj.order.mapper.OrderMapper;
import cn.zj.order.service.OrderService;
import cn.zj.spa.bean.Order;
import cn.zj.spa.bean.OrderItem;
import cn.zj.spa.bean.Product;
import cn.zj.spa.bean.User;
import cn.zj.spa.params.OrderParams;
import cn.zj.spa.utils.constants.HttpCode;
import cn.zj.spa.utils.resp.Result;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:42
 * @description 商品业务实现类
 */
@Service
public class OrderServiceV2Impl implements OrderService {

    private static final Logger loggger = LoggerFactory.getLogger(OrderServiceV2Impl.class);
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private RestTemplate restTemplate;

    private String userServer = "server-user";

    private String productServer = "server-product";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        String userUrl = this.getServiceUrl(userServer);
        String productUrl = this.getServiceUrl(productServer);

        if (orderParams.isEmpty()){
            throw new RuntimeException("参数异常: " + JSONObject.toJSONString(orderParams));
        }

        User user = restTemplate.getForObject("http://"+userUrl+"/user/get/" + orderParams.getUserId(), User.class);
        if (user == null){
            throw new RuntimeException("未获取到用户信息: " + JSONObject.toJSONString(orderParams));
        }
        Product product = restTemplate.getForObject("http://"+productUrl+"/product/get/" + orderParams.getProductId(), Product.class);
        if (product == null){
            throw new RuntimeException("未获取到商品信息: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()){
            throw new RuntimeException("商品库存不足: " + JSONObject.toJSONString(orderParams));
        }
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        Result<Integer> result = restTemplate.getForObject("http://"+productUrl+"/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        if (result.getCode() != HttpCode.SUCCESS){
            throw new RuntimeException("库存扣减失败");
        }
        loggger.info("库存扣减成功");
    }

    /**
     * @description  通过接口名称动态获取服务地址
     * @param serviceName
     * @return java.lang.String
     * @author zhangjin
     * @date 2023/3/16 13:53
    */
    private String getServiceUrl(String serviceName){
        ServiceInstance serviceInstance = discoveryClient.getInstances(serviceName).get(0);
        return serviceInstance.getHost()+":"+serviceInstance.getPort();
    }
}
