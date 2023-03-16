package cn.zj.order.service;

import cn.zj.spa.bean.Product;
import cn.zj.spa.params.OrderParams;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:36
 * @description 订单业务接口
 */
public interface OrderService {


    /**
     * @description 保存订单
     * @param orderParams
     * @return void
     * @author zhangjin
     * @date 2023/3/16 11:22
    */
    void saveOrder(OrderParams orderParams);
}
