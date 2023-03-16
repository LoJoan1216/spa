package cn.zj.order.feign;

import cn.zj.order.feign.fallback.UserServiceFallBack;
import cn.zj.order.feign.fallback.factory.UserServiceFallBackFactory;
import cn.zj.spa.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 14:09
 * @description 调用用户微服务接口
 */
@FeignClient(value = "server-user",fallbackFactory = UserServiceFallBackFactory.class)
public interface UserService {

    @GetMapping("/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
