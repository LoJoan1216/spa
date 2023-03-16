package cn.zj.user.mapper;

import cn.zj.spa.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author joan
 * @version V1.0.0
 * @date 2023/3/16 10:23
 * @description 用户mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
