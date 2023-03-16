package cn.zj.user.controller;

import cn.zj.spa.bean.User;
import cn.zj.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import jdk.nashorn.api.scripting.JSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:44
 * @description 用户接口
 */
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{uid}")
    public User getUser(@PathVariable("uid") Long uid){
        User user = userService.getUserById(uid);
        log.info("获取到的用户信息为:{}", JSONObject.toJSONString(user));
        return user;
    }
}
