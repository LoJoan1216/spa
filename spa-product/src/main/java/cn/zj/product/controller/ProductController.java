package cn.zj.product.controller;

import cn.zj.spa.bean.Product;
import cn.zj.spa.bean.User;
import cn.zj.product.service.ProductService;
import cn.zj.spa.utils.constants.HttpCode;
import cn.zj.spa.utils.resp.Result;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:44
 * @description 商品接口
 */
@Slf4j
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;


    /**
     * @description 根据商品id 获取商品信息
     * @param pid 商品id
     * @return cn.zj.spa.bean.Product
     * @author zhangjin
     * @date 2023/3/16 11:04
    */
    @GetMapping("/get/{pid}")
    public Product getProduct(@PathVariable("pid") Long pid){
        Product product = productService.getProductById(pid);
        log.info("获取到的商品信息为:{}",JSONObject.toJSONString(product));
        return product;
    }

    @GetMapping("/update_count/{pid}/{count}")
    public Result<Integer> updateCount(@PathVariable("pid") Long pid,@PathVariable("count") Integer count){
        log.info("更新商品库存传递的参数为:商品id:{},购买的数量:{}",pid,count);
        int updateCount = productService.updateProductStockById(count,pid);
        Result<Integer> result = new Result<>(HttpCode.SUCCESS,"执行成功",updateCount);
        return result;
    }
}
