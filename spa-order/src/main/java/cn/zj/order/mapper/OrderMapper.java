package cn.zj.order.mapper;

import cn.zj.spa.bean.Order;
import cn.zj.spa.bean.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:23
 * @description 订单mapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * @description  扣减商品库存
     * @param count 扣除商品的数量
     * @param id 扣除商品的id
     * @return int
     * @author zhangjin
     * @date 2023/3/16 10:54
    */
    int updateProductStockById(@Param("count") Integer count,@Param("Id") Long id);
}
