package com.meinil.metms.server.mapper;

import com.meinil.metms.commons.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */

@Mapper
public interface UserMapper {
    @Select("SELECT id,account,username,power FROM tb_user WHERE account=#{account}&&password=#{password}")
    public User getUser(User user);

    @Select("SELECT COUNT(id) FROM tb_user")
    public int getTotalCount();

    @Select("SELECT id, username, account, power FROM tb_user LIMIT #{id}, 10")
    public List<User> getUsers(int id);
}
