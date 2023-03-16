package cn.zj.spa.utils.psswd;

import cn.zj.spa.utils.md5.MD5Hash;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:18
 * @description 密码工具类
 */
public class PasswordUtils {
    public static String getPassword(String password){
        if (password == null || password.trim().isEmpty()) return password;
        for (int i = 0; i < 5; i++){
            password = MD5Hash.md5Java(password);
        }
        return password;
    }
}
