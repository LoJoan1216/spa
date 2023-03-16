package cn.zj.spa.params;

import lombok.Data;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 11:14
 * @description 订单参数
 */
@Data
public class OrderParams {
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 购买的商品数量
     */
    private Integer count;

    public boolean isEmpty(){
        return (productId == null || productId <= 0) ||
                (userId == null || productId <= 0) ||
                (count == null || count <= 0);
    }
}
