package cn.zj.order.feign.fallback.factory;

import cn.zj.order.feign.UserService;
import cn.zj.spa.bean.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 15:18
 * @description 用户微服务容错factory
 */
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public User getUser(Long uid) {
                User user = new User();
                user.setId(-1L);
                return user;
            }
        };
    }
}
