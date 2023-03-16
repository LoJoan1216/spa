package cn.zj.product.service.impl;

import cn.zj.product.mapper.ProductMapper;
import cn.zj.product.service.ProductService;
import cn.zj.spa.bean.Product;
import cn.zj.spa.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:42
 * @description 商品业务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public Product getProductById(Long pid) {
        return productMapper.selectById(pid);
    }

    @Override
    public int updateProductStockById(Integer count, Long id) {
        return productMapper.updateProductStockById(count,id);
    }
}
