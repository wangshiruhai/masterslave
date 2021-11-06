package com.crud.biz.impl;

import com.crud.biz.IUserService;
import com.crud.entity.User;
import com.crud.mapper.UserMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author tommy
 * @date 11/6/21
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<User> findUserList() {
        return userMapper.selectList(null);
    }
}
