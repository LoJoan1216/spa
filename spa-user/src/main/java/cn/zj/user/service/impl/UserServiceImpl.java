package cn.zj.user.service.impl;

import cn.zj.spa.bean.User;
import cn.zj.user.mapper.UserMapper;
import cn.zj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:42
 * @description 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
}
