package cn.zj.order.feign.fallback;

import cn.zj.order.feign.UserService;
import cn.zj.spa.bean.User;
import org.springframework.stereotype.Component;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 14:44
 * @description 用户服务容器类
 */
@Component
public class UserServiceFallBack implements UserService {
    @Override
    public User getUser(Long uid) {
        User user= new User();
        user.setId(-1L);
        return user;
    }
}
