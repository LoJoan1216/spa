package cn.zj.order.feign;

import cn.zj.order.feign.fallback.ProductServiceFallBack;
import cn.zj.order.feign.fallback.factory.ProductServiceFallBackFactory;
import cn.zj.spa.bean.Product;
import cn.zj.spa.utils.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 14:11
 * @description 调用商品微服务接口
 */
@FeignClient(value = "server-product",fallbackFactory = ProductServiceFallBackFactory.class)
public interface ProductService {
    /**
     * 获取商品信息
     */
    @GetMapping(value = "/product/get/{pid}")
    Product getProduct(@PathVariable("pid") Long pid);

    /**
     * 更新库存数量
     */
    @GetMapping(value = "/product/update_count/{pid}/{count}")
    Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count);


}
