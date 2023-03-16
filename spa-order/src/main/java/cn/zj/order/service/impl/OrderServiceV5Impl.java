package cn.zj.order.service.impl;

import cn.zj.order.feign.ProductService;
import cn.zj.order.feign.UserService;
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
public class OrderServiceV5Impl implements OrderService {

    private static final Logger loggger = LoggerFactory.getLogger(OrderServiceV5Impl.class);
    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private OrderItemMapper orderItemMapper;



    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {


        if (orderParams.isEmpty()) {
            throw new RuntimeException("参数异常: " + JSONObject.toJSONString(orderParams));
        }
        User user = userService.getUser(orderParams.getUserId());
        if (user == null) {
            throw new RuntimeException("未获取到用户信息: " + JSONObject.toJSONString(orderParams));
        }
        Product product = productService.getProduct(orderParams.getProductId());
        if (product == null) {
            throw new RuntimeException("未获取到商品信息: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()) {
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
        Result<Integer> result =  productService.updateCount(orderParams.getProductId(),orderParams.getCount());
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("库存扣减失败");
        }
        loggger.info("库存扣减成功");
    }


}
