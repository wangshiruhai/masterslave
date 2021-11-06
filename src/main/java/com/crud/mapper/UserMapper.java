package com.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crud.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
