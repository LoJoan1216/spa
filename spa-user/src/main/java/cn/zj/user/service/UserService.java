package cn.zj.user.service;

import cn.zj.spa.bean.User;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:36
 * @description 用户业务接口
 */
public interface UserService {

    /**
     * @description 根据id获取用户信息
     * @param userId 用户id
     * @return cn.zj.spa.bean.User
     * @author zhangjin
     * @date 2023/3/16 10:36
    */
    User getUserById(Long userId);
}
