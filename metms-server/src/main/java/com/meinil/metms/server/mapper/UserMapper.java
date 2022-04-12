package com.meinil.metms.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meinil.metms.server.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Meinil
 * @Version 1.0
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT id,account,username,power, description FROM tb_user WHERE account=#{account}&&password=#{password}")
    public User getUser(User user);

    @Select("SELECT COUNT(id) FROM tb_user")
    public int getTotalCount();

    @Select("SELECT id, username, account, power FROM tb_user LIMIT #{id}, 10")
    public List<User> getUsers(int id);

    @Select("SELECT account, username, description FROM tb_user WHERE account = (SELECT teacher_id FROM tb_connect WHERE stu_id=4)")
    public User getUserByStudentAccount(String account);

    @Select("SELECT username, account FROM tb_user WHERE account IN (SELECT stu_id FROM tb_connect WHERE teacher_id=#{account})")
    public List<User> getStudents(String account);

    public boolean updateTeacher( @Param("teacherId") String teacherId, @Param("stuId") String stuId);
}
