package com.meinil.metms.server.mapper;

import com.meinil.metms.server.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author Meinil
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM tb_user WHERE username=#{username}&password=#{password}")
    public User getUser(User user);
}
