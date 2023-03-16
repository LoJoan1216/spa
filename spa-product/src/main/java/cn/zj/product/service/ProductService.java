package cn.zj.product.service;

import cn.zj.spa.bean.Product;
import cn.zj.spa.bean.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:36
 * @description 商品业务接口
 */
public interface ProductService {

    /**
     * @description 根据商品id获取商品信息
     * @param pid 商品id
     * @return cn.zj.spa.bean.Product
     * @author zhangjin
     * @date 2023/3/16 10:57
    */
    Product getProductById(Long pid);

    /**
     * @description  扣减商品库存
     * @param count 扣除商品的数量
     * @param id 扣除商品的id
     * @return int
     * @author zhangjin
     * @date 2023/3/16 10:54
     */
    int updateProductStockById(Integer count,  Long id);

}
