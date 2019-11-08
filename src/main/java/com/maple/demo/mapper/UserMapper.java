package com.maple.demo.mapper;


import com.maple.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.naming.Name;


@Mapper
@Repository //声明这是一个bean，避免需要注入bean时因为没有具体实现类报错
public interface UserMapper {
    @Insert("insert into springBoot_user (account_id,name,token,gmt_create,gmt_modified) values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    void Insert(User user);

    @Select("select * from springBoot_user where token=#{token}")
    User findByToken(@Param("token") String token);
}
