package cn.zj.order.feign.fallback.factory;

import cn.zj.order.feign.ProductService;
import cn.zj.spa.bean.Product;
import cn.zj.spa.utils.resp.Result;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 15:26
 * @description 商品微服务容错factory
 */
@Component
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product getProduct(Long pid) {
                Product product = new Product();
                product.setId(-1L);
                return product;
            }

            @Override
            public Result<Integer> updateCount(Long pid, Integer count) {
                Result<Integer> result = new Result<>();
                result.setCode(1001);
                result.setCodeMsg("触发了容错逻辑");
                return result;
            }
        };
    }
}
